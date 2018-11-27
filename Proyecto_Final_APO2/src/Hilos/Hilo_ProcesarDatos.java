package Hilos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;

import model.Hashtag;
import model.Link;
import model.Mencion;
import model.Palabra;
import model.PalabraRelevante;
import model.Tweet;
import model.Usuario;


public class Hilo_ProcesarDatos implements Runnable,Serializable{
	static final long serialVersionUID = 42L;
	public  FileReader read;
	public  BufferedReader rd;
	private String nombre_archivo;
	private Usuario creado;
	private Link primer_link;
	private Hashtag hashtags;
	private PalabraRelevante raiz_relevantes;
	private ArrayList<Mencion> menciones;
	/**
	 * Hilo_ProcesarDatos - Metodo constructor de la clase
	 * @param primer_link Una lista de tipo Link con todos los links del Usuario 	primer_link != null
	 * @param hashtags	Una lista de Hashtag con todos los hashtag del Usuario	hashtags != null
	 * @param raiz_relevantes Un arbol binario de busqueda de PalabraRelevante raiz_relevante != null
	 * @param nombre_archivo Un String indicando el nombre del archivo donde se guardo la información del usuario
	 * pos : primer_link queda inicializado
	 * pos : hashtags queda inicializado
	 * pos : raiz_relevante queda inicializado
	 * pos : nombre_archivo queda inicializado
	 */
	public Hilo_ProcesarDatos(Link primer_link, Hashtag hashtags, PalabraRelevante raiz_relevantes, String nombre_archivo) {
		super();
		this.primer_link = primer_link;
		this.hashtags = hashtags;
		this.raiz_relevantes = raiz_relevantes;
		this.nombre_archivo = nombre_archivo;
		menciones = new ArrayList<>();
	}
	
	/**
	 * run - Metodo para proceasar la informacion del usuario y crear un nuevo Usuario
	 * pre : primer_link queda inicializado
	 * pre : hashtags queda inicializado
	 * pre : raiz_relevante queda inicializado
	 * pre : nombre_archivo queda inicializado
	 * pos : Se crea un nuevo Usuario con una lista de Tweet inicializada
	 * pos : Se añaden objetos a primer_link
	 * pos : Se añaden objetos a hashtags
	 */
	@Override
	public void run(){
		try {
			ArrayList<String> Texto_Bruto = new ArrayList<>();
			
			File fl = new File(nombre_archivo);
			FileReader read = new FileReader(fl);
			BufferedReader rd = new BufferedReader(read);
			String nombre_Usuario = "";
			String dato = "";
			String seguidores = "";
			String seguidos = "";
	
			
			int contador = 0;
			while(dato != null && dato.compareTo("Who to follow Â·  Refresh Â· View all") != 0) {
				if(contador == 19) {
					nombre_Usuario = dato;
				}else if(contador == 26) {
					seguidos = dato;
				}else if(contador == 29) {
					seguidores = dato;
				}
				
				dato = rd.readLine();
				Texto_Bruto.add(dato);
				System.out.println(dato);
				contador++;
			}
	
			
			Tweet lista_t = new Tweet("", null, "0", "0",null);
			contador = 0;
			dato = Texto_Bruto.get(contador);
			
			Object a[] = new Object[2];
			
			int cantidad = 0;
			
			int Puntaje_Usuario[] = new int[3];
			
			while(dato != null && dato.compareTo("Who to follow Â·  Refresh Â· View all") != 0) {
				Tweet temp;
				if(dato.compareTo("Verified account") == 0) {
					a = recopilarTweet(Texto_Bruto,contador+1,nombre_Usuario);
					temp = (Tweet)a[0];
					contador = (int)a[1]; 
					Puntaje_Usuario[0] += temp.getPuntajes()[0];
					Puntaje_Usuario[1] += temp.getPuntajes()[1];
					Puntaje_Usuario[2] += temp.getPuntajes()[2];
					lista_t.agregar(temp);
					cantidad++;
				}
				contador++;
				dato = Texto_Bruto.get(contador);
			}
			
			System.out.println();
			System.out.println(nombre_Usuario);
			System.out.println(seguidores);
			System.out.println(seguidos);
			
			creado = new Usuario(nombre_Usuario, seguidores, seguidos, lista_t ,cantidad,Puntaje_Usuario,menciones);
			rd.close();
		}catch(IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
			
	}
	
	/**
	 * getCreado - Metodo que retorna el {@link Usuario} creado por el hilo
	 * @return creado - El usuario creado por el hilo
	 */
	public Usuario getCreado() {
		return creado;
	}
	
	/**
	 * getPrimer_link - Metodo que retorna la lista de {@link Link} 
	 * @return primer_link  - La lista de {@link Link}
	 */
	public Link getPrimer_link() {
		return primer_link;
	}
	
	/**
	 * getHashtags - Metodo que retorna la lista de {@link Hashtag}
	 * @return hashtags - La lis de {@link Hashtag}
	 */
	public Hashtag getHashtags() {
		return hashtags;
	}
	
	/**
	 * recopilarTweet - Metodo para crear un Tweet
	 * @param t - El ArrayList de String con todo el texto plano del usurio separado por " "	t != null
	 * @param c - Un numero entero con el numero de linea que se está procesando en Arraylist	c != null c >= 15
	 * @param n - Un String con el nombre del usuario n != null	n != ""
	 * @return fin - Un arreglo de tipo {@link Object} que contiene el numero de la ultima linea que se estaba procesando y el Tweet creado
	 * @throws URISyntaxException 
	 */
	private Object[] recopilarTweet(ArrayList<String> t, int c, String n) throws URISyntaxException {
		String dato = t.get(c);
		Tweet temp;
		Palabra salida = new Palabra("", null);
		int Cont_en_Tweet = 0;
		
		String fecha = "";
		String likes = "";
		String reTweets = "";
		boolean seguir = false;
		
		int Puntaje_Tweet[] = new int[3];
		
		while(!seguir && dato != null && dato.compareTo("Verified account") != 0 && dato.compareTo("Who to follow Â·  Refresh Â· View all") != 0) {
			String herramienta[] = dato.split(" ");
			if(herramienta.length >= 6 && herramienta[herramienta.length-1].compareTo("message") == 0 && herramienta[herramienta.length-2].compareTo("Direct") == 0) {
				herramienta = t.get(c-1).split(" ");
				reTweets = herramienta[2];
				likes = herramienta[4];
				seguir = true;	
				//System.out.println("No agregado por Herramienta   //// "+dato+ " LOL "+ herramienta[5] + " LOL "+herramienta[9]);
			}else if(Cont_en_Tweet == 2) {
				fecha = dato;
				int puntos[] = identificarPalabra(dato);
				Puntaje_Tweet[0] += puntos[0];
				Puntaje_Tweet[1] += puntos[1];
				Puntaje_Tweet[2] += puntos[2];
				salida.agregar(new Palabra(dato,puntos));
			}else if(!seguir && dato.compareTo("Who to follow Â·  Refresh Â· View all") != 0){
				for (int i = 0; i < herramienta.length; i++) {
					int puntos[] = identificarPalabra(herramienta[i]);
					Puntaje_Tweet[0] += puntos[0];
					Puntaje_Tweet[1] += puntos[1];
					Puntaje_Tweet[2] += puntos[2];
					salida.agregar(new Palabra(herramienta[i],puntos));
				}
			}else if(herramienta.length >= 2 && herramienta[herramienta.length-1].compareTo("Retweeted") == 0){
				seguir = true;
			}
			
			salida.agregar(new Palabra("\n",null));
			c++;
			Cont_en_Tweet++;
			dato = t.get(c);
		}
		
		
		temp = new Tweet(fecha, salida, likes, reTweets, Puntaje_Tweet);
		
		Object fin[] = {temp,c-1};
		
		return fin;
	}
	
	/**
	 * identificarPalabra - Metodo para identificar que tipo de palabra se va a añadir al tweet y su respectivo puntaje
	 * @param s - Es un String con la palabra a identificar
	 * @return salida un arreglo de enteros 
	 * @throws URISyntaxException 
	 */
	private int[] identificarPalabra(String s) throws URISyntaxException {
		int salida[] = new int[3];
		salida[0] = 0;
		salida[1] = 0;
		salida[2] = 0;
		if(s.length()>=2 && s.substring(0, 1).compareToIgnoreCase("@") == 0) {
			Mencion temp = new Mencion(s,salida, s.substring(1,s.length()));
			menciones.add(temp);
		}else if(s.length()>=6 && s.substring(0, 5).compareToIgnoreCase("http:") == 0) {
			Link temp = new Link(s,null);
			primer_link.agregarUltimoL(temp);
		}else if(s.length()>=1 && s.substring(0, 1).compareToIgnoreCase("#") == 0){
			Hashtag temp = new Hashtag(s, null, 0);
			if(hashtags.contiene(s)) {
				hashtags.dar(s).setRepeticion(hashtags.dar(s).getRepeticiones()+1);
			}else {
				hashtags.agregar(temp);
			}
		}else {
			PalabraRelevante temp = raiz_relevantes.buscarPalabra(s);
			if(temp != null) {
				salida[0] = temp.getPuntuacion_depor();
				salida[1] = temp.getPuntuacion_poli();
				salida[2] = temp.getPuntuacion_tecno();
			}
		}                
		
		return salida;
	}

	
	
}
