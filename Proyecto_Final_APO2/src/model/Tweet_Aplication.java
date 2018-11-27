package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import javax.tools.FileObject;
import Hilos.Hilo_ProcesarDatos;
import MyException.TextoVacioException;

/**
 *Clase {@link Tweet_Aplication} / Maneja las operaciones de serializar,cargar,agregar,ordenar informacion de todo el modelo.
 *@author Nelson Quiñones Virgen - Fabio Andres Mejía - Marco Antonio Perez 
 *Version 1.0 
 *27/Noviembre/2018 
*/

public class Tweet_Aplication implements Serializable, Ordenamiento{
	static final long serialVersionUID = 42L;
	private Usuario usuarioRaiz;
	private PalabraRelevante raizRelevante;
	private Hashtag tendencias;
	private Link tendenciasLinks;
	private ArrayList<Hilo_ProcesarDatos> Datos;
	private ArrayList<String> NombreArchivos;
	private Usuario usuarioActual;
	
	/**
	 * Tweet_Aplication - Metodo constructor de la clase, se cargan los datos de prueba
	 * @throws IOException	Se arroja si no se encuentra el archivo o hay errores al escribir o leer el archivo
	 * @throws ClassNotFoundException Se arroja si no se encuentra 
	 * pos: Se instancia y se agregan elementos al arbol usuarioRaiz
	 * pos: Se instancia y se agregan elementos al arbol raizRelevante
	 * pos: Se instancia y se agregan elementos a la lista de tendencias
	 * pos: Se instancia y se agregan elementos a la lista de tendenciasLinks
	 * pos: Se instancia y se agregan elementos al ArrayList de Datos
	 * pos: Se instancia y se agregan elementos al ArrayList de NombresAechivos 
	 * @throws URISyntaxException Se arroja si hay problemas con el link
	 */
	public Tweet_Aplication() throws IOException, ClassNotFoundException, URISyntaxException {
		usuarioRaiz = new Usuario("", "", "", null,0,null,null);
		usuarioActual = null;
		try {
			File wr = new File("./Persistencia/Aplicacion/Aplicacion_persistente");
			FileInputStream wow = new FileInputStream(wr);
			ObjectInputStream entrada = new ObjectInputStream(wow);
			Tweet_Aplication temp = (Tweet_Aplication)entrada.readObject();
			tendencias = temp.tendencias;
			tendenciasLinks = temp.tendenciasLinks;
			NombreArchivos = temp.NombreArchivos;
			entrada.close();
			Datos = new ArrayList<>();
			Thread[] a = new Thread[NombreArchivos.size()];
			cargarPalabrasRelevantes();
			for (int I = 0; I < NombreArchivos.size(); I++) {
				System.out.println("Se agregaron usuarios ");
				Datos.add(new Hilo_ProcesarDatos(tendenciasLinks, tendencias,raizRelevante,NombreArchivos.get(I)));
				a[I] = new Thread(Datos.get(I));
				a[I].start();
				while(Datos.get(I).getCreado() == null) {
					System.out.println("Creando");
				}
				usuarioRaiz.agregar(Datos.get(I).getCreado());
				tendencias = Datos.get(I).getHashtags();
				tendenciasLinks = Datos.get(I).getPrimer_link();
				System.out.println("                                        "+I);
			}    

			System.out.println(NombreArchivos.size());
		} catch (Exception e) {
			e.printStackTrace();
			tendencias = new Hashtag("", null, 0);
			tendenciasLinks = new Link("", null);
			NombreArchivos = new ArrayList<>();
			Thread[] a = new Thread[1];
			Datos = new ArrayList<>();
			Datos.add(new Hilo_ProcesarDatos(tendenciasLinks, tendencias,raizRelevante,"./Persistencia/Usuarios/Elon Musk"));
			a[0] = new Thread(Datos.get(0));
			a[0].start();
			usuarioRaiz = Datos.get(0).getCreado();
			guardarProgreso();
		}
	}
	/**
	 * Tweet_Aplication - Metodo constructor de la clase, usado en las pruebas
	 * @param raiz
	 * @param links
	 * @param tendencias
	 * @param relevante
	 */
	public Tweet_Aplication(Usuario raiz, Link links, Hashtag tendencias, PalabraRelevante relevante) {
		usuarioActual = null;
		usuarioRaiz = raiz;
		this. tendencias= tendencias;
		tendenciasLinks = links;
		raizRelevante = relevante;
	}
	
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(Usuario usuarioActual) {
		this.usuarioActual = usuarioActual;
	}

	/**
	 * RegistrarUsuario - Metodo para agregar usuarios apartir de texto en bruto
	 * @throws IOException - Se arroja si no se encuentra el archivo o hay errores al escribir o leer el archivo
	 * @param data : Texto en bruto traido desde Twiter data != null data != ""
	 * pre: Arraylist de datos debe esta inicializado
	 * pre: usuarioRaiz debe estar inicializado
	 * pos: Se agrega un objeto al arbol de usuarios usuarioRaiz
	 * @throws TextoVacioException Se lanza cuando no hay texto para agregar usuario
	*/

	public void RegistrarUsuario(String data) throws IOException, TextoVacioException {
		if(!data.equals("")) {
			//System.out.println(data);
			String info[] = data.split("\n");
			String nombre_archivo = "./Persistencia/Usuarios/"+info[16];
			File newF = new File(nombre_archivo);
			NombreArchivos.add(nombre_archivo);
			FileWriter write = new FileWriter(newF);
			BufferedWriter writer = new BufferedWriter(write);
			for (int i = 0; i < info.length; i++) {
				//System.out.println("Escribiendo");
				writer.write(info[i]);
				writer.write("\n");
			}
			writer.close();
			Datos.add(new Hilo_ProcesarDatos(tendenciasLinks, tendencias,raizRelevante,nombre_archivo));
			Thread a = new Thread(Datos.get(Datos.size()-1));
			a.start();
			Usuario temp = null;
			while(temp == null) {
				temp = Datos.get(Datos.size()-1).getCreado();
				//System.out.println("Creando");
			}
			usuarioRaiz.agregar(temp);
			guardarProgreso();
		}else {
			throw new TextoVacioException();
		}
		
	}
	/**
	 * guardarProgreso - Metodo para serializar el estado actual del modelo
	 * @throws IOException
	 * pre: el archivo Aplicacion_persistente debería existir
	 */
	private void guardarProgreso() throws IOException {
		File wr = new File("./Persistencia/Aplicacion/Aplicacion_persistente");
		FileOutputStream wr2 = new FileOutputStream(wr);
		ObjectOutputStream write = new ObjectOutputStream(wr2);
		write.writeObject(this);
		write.close();
	}
	/**
	 * cargarPalabrasRelevantes - Metodo para inicializar las palabras relevantes en cada categoria y añadirlas a un arbol de busqueda
	 * @throws IOException
	 * pre: El archivo Deporte.txt debería existir
	 * pre: El archivo Politica.txt debería existir
	 * pre: El archivo Tecnologia.txt debería existir
	 * pos: Se inicializa y agregan objetos al arbol raizRelevante
	 */
	private void cargarPalabrasRelevantes() throws IOException {
		File fl = new File("./Persistencia/Glosario/Deporte.txt");
		FileReader read = new FileReader(fl);
		BufferedReader rd = new BufferedReader(read);
		
		String temp = rd.readLine();
		while(temp != null) {

			//System.out.println("Agregando "+ temp);
			int Puntos[] = {1,0,0};
			PalabraRelevante a = new PalabraRelevante(temp.trim(), Puntos);
			if(raizRelevante == null) {
				raizRelevante = a;
			}else {
				raizRelevante.AgregarRelevante(a);
			}
			temp = rd.readLine();
		}
		
		fl = new File("./Persistencia/Glosario/Politica.txt");
		read = new FileReader(fl);
		rd = new BufferedReader(read);
		
		temp = rd.readLine();
		while(temp != null) {
			//System.out.println("Agregando "+ temp);
			int Puntos[] = {0,1,0};
			PalabraRelevante a = new PalabraRelevante(temp.trim(), Puntos);
			if(raizRelevante == null) {
				raizRelevante = a;
			}else {
				raizRelevante.AgregarRelevante(a);
			}
			temp = rd.readLine();
		}
		
		fl = new File("./Persistencia/Glosario/Tecnologia.txt");
		read = new FileReader(fl);
		rd = new BufferedReader(read);
		
		temp = rd.readLine();
		while(temp != null) {
			//System.out.println("Agregando "+ temp);
			int Puntos[] = {0,0,1};
			PalabraRelevante a = new PalabraRelevante(temp.trim(), Puntos);
			if(raizRelevante == null) {
				raizRelevante = a;
			}else {
				raizRelevante.AgregarRelevante(a);
			}
			temp = rd.readLine();
		}
	}

	@Override
	public void ordenamiento(ArrayList<Object> objeto, char tipo) {
		switch (tipo) {
		case 'n': {
			usuarioRaiz.inorden(objeto);
			objeto.remove(0);
		}
			break;
		case 't': OrdenamientoNumeroTweets(objeto);
			break;
		case 'o': usuarioActual.ordenamiento(objeto, tipo);
			break;
		case 'a': usuarioActual.ordenamiento(objeto, tipo);
			break;
		case 'e': {
			tendencias.darHashtags(objeto);
			objeto.remove(0);
		}
			break;
		case 'i': ordernarHashtags(objeto);
			break;
		case 'k':{ tendenciasLinks.darLinks(objeto);
				objeto.remove(0);}
			break;
		case 'm': ordenarLinks(objeto);
			break;
		default:
			break;
		}
		
		
	}
	
	private void ordenarLinks(ArrayList<Object> objeto) {
		tendenciasLinks.darLinks(objeto);
		objeto.remove(0);
		for (int i = 0; i < objeto.size(); i++) {
			for (int j = 0; j+1 < objeto.size() ; j++) {
				Link a = (Link)objeto.get(j);
				Link b = (Link)objeto.get(j+1);
				if(a.compareTo(b)>0) {
					objeto.set(j, b);
					objeto.set(j+1, a);
				}
			}
		}
	}

	/**
	 * OrdenamientoNumeroTweets - Metodo para ordenar una coleccion de Usuarios por el metodo burbuja
	 * @param objeto : Es el Arraylist de usuarios a organizar	objeto != null
	 * pre: usuarioRaiz debe estar inicializado
	*/
	private void OrdenamientoNumeroTweets(ArrayList<Object> objeto) {
		usuarioRaiz.inorden(objeto);
		objeto.remove(0);
		for (int i = 0; i < objeto.size(); i++) {
			for (int j = 0; j+1 < objeto.size(); j++) {
				Usuario a = (Usuario)objeto.get(j);
				Usuario b = (Usuario)objeto.get(j+1);
				if(a.compareTo(b)<0) {
					objeto.set(j, b);
					objeto.set(j+1, a);
				}
			}
		}
	}
	
	/**
	 * buscarUsuario - Metodo para buscar usuarios en el arbol de Usuarios
	 * @param usuario : Nombre del usuario a buscar en el el arbol de usuarios	usuario != null	usuario != ""
	 * @return  El usuario que posee ese nombre, en caso de no encontrarlo devulve null
	 * pre: usuarioRaiz debe estar incializado
	 */
	
	public Usuario buscarUsuario(String usuario) {
		return usuarioRaiz.buscarUsuario(usuario);
	}
	
	
	/**
	 * ordernarHashtags - Metodo que ordena con el metodo burbuja los Hashtags
	 * @param objeto objeto Un Arraylist de de objetos Hashtags objeto != null objeto solo contiene Hashtags
	 * pre : tendencias esta incializado
	 * pos : El Arraylist de objeto queda ordenado
	 */
	private void ordernarHashtags(ArrayList<Object> objeto) {
		tendencias.darHashtags(objeto);
		objeto.remove(0);
		for (int i = 1; i < objeto.size(); i++) {
			Hashtag temp = (Hashtag)objeto.get(i);
			for(int j = i; j > 0 && temp.compareTo((Hashtag)objeto.get(j-1)) > 0; j--) {
				Hashtag temp2 = (Hashtag)objeto.get(j);
				objeto.set(j, objeto.get(j-1));
				objeto.set(j-1, temp2);
			}
		}
	}
	
}
