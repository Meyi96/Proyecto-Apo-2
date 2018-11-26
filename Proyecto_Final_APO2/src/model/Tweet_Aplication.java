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
import java.util.ArrayList;
import java.util.Collection;

import javax.tools.FileObject;

import Hilos.Hilo_ProcesarDatos;

public class Tweet_Aplication implements Serializable, Ordenamiento{
	static final long serialVersionUID = 42L;
	private Usuario usuarioRaiz;
	private PalabraRelevante raizRelevante;
	private Hashtag tendencias;
	private Link tendenciasLinks;
	private ArrayList<Hilo_ProcesarDatos> Datos;
	private ArrayList<String> NombreArchivos;
	
	public Tweet_Aplication() throws IOException, ClassNotFoundException {
		usuarioRaiz = new Usuario("", "", "", null,0,null);
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

	public void RegistrarUsuario(String data) throws IOException {
		System.out.println(data);
		String info[] = data.split("\n");
		String nombre_archivo = "./Persistencia/Usuarios/"+info[16];
		File newF = new File(nombre_archivo);
		NombreArchivos.add(nombre_archivo);
		FileWriter write = new FileWriter(newF);
		BufferedWriter writer = new BufferedWriter(write);
		for (int i = 0; i < info.length; i++) {
			System.out.println("Escribiendo");
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
			System.out.println("Creando");
		}
		usuarioRaiz.agregar(temp);
		guardarProgreso();
	}

	private void guardarProgreso() throws IOException {
		File wr = new File("./Persistencia/Aplicacion/Aplicacion_persistente");
		FileOutputStream wr2 = new FileOutputStream(wr);
		ObjectOutputStream write = new ObjectOutputStream(wr2);
		write.writeObject(this);
		write.close();
	}
	
	private void cargarPalabrasRelevantes() throws IOException {
		File fl = new File("./Persistencia/Glosario/Deporte.txt");
		FileReader read = new FileReader(fl);
		BufferedReader rd = new BufferedReader(read);
		
		String temp = rd.readLine();
		while(temp != null) {

			System.out.println("Agregando "+ temp);
			int Puntos[] = {1,0,0};
			PalabraRelevante a = new PalabraRelevante(temp.trim(), Puntos);
			if(raizRelevante == null) {
				raizRelevante = a;
			}else {
				raizRelevante.agregar(a);
			}
			temp = rd.readLine();
		}
		
		fl = new File("./Persistencia/Glosario/Politica.txt");
		read = new FileReader(fl);
		rd = new BufferedReader(read);
		
		temp = rd.readLine();
		while(temp != null) {
			System.out.println("Agregando "+ temp);
			int Puntos[] = {0,1,0};
			PalabraRelevante a = new PalabraRelevante(temp.trim(), Puntos);
			if(raizRelevante == null) {
				raizRelevante = a;
			}else {
				raizRelevante.agregar(a);
			}
			temp = rd.readLine();
		}
		
		fl = new File("./Persistencia/Glosario/Tecnologia.txt");
		read = new FileReader(fl);
		rd = new BufferedReader(read);
		
		temp = rd.readLine();
		while(temp != null) {
			System.out.println("Agregando "+ temp);
			int Puntos[] = {0,0,1};
			PalabraRelevante a = new PalabraRelevante(temp.trim(), Puntos);
			if(raizRelevante == null) {
				raizRelevante = a;
			}else {
				raizRelevante.agregar(a);
			}
			temp = rd.readLine();
		}
	}

	@Override
	public void ordenamiento(ArrayList<Object> objeto, char tipo) {
		switch (tipo) {
		case 'n': usuarioRaiz.inorden(objeto);
			break;
		case 't': OrdenamientoNumeroTweets(objeto);

		default:
			break;
		}
		
		
	}

	private void OrdenamientoNumeroTweets(ArrayList<Object> objeto) {
		usuarioRaiz.inorden(objeto);
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
	
	public void tweetsOrdenados(ArrayList<Object> objeto, char tipo, Usuario actual) {
		actual.ordenamiento(objeto, tipo);
	}
	
	public Usuario buscarUsuario(String usuario) {
		return usuarioRaiz.buscarUsuario(usuario);
	}
	
}
