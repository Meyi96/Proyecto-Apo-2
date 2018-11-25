package Hilos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import model.Hashtag;
import model.Link;
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
	
	public Hilo_ProcesarDatos(Link primer_link, Hashtag hashtags, PalabraRelevante raiz_relevantes, String nombre_archivo) {
		super();
		this.primer_link = primer_link;
		this.hashtags = hashtags;
		this.raiz_relevantes = raiz_relevantes;
		this.nombre_archivo = nombre_archivo;
	}
	
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
			while(dato != null && dato.compareTo("Who to follow ·  Refresh · View all") != 0) {
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
	
			
			Tweet lista_t = new Tweet("", null, "0", "0");
			contador = 0;
			dato = Texto_Bruto.get(contador);
			
			Object a[] = new Object[2];
			
			while(dato != null && dato.compareTo("Who to follow ·  Refresh · View all") != 0) {
				Tweet temp;
				if(dato.compareTo("Verified account") == 0) {
					a = recopilarTweet(Texto_Bruto,contador+1,nombre_Usuario);
					temp = (Tweet)a[0];
					contador = (int)a[1];
					lista_t.agregarUltimo(temp);
				}
				contador++;
				dato = Texto_Bruto.get(contador);
			}
			
			System.out.println();
			System.out.println(nombre_Usuario);
			System.out.println(seguidores);
			System.out.println(seguidos);
			
			creado = new Usuario(nombre_Usuario, seguidores, seguidos, lista_t);
			rd.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
			
	}
	
	public Usuario getCreado() {
		return creado;
	}
	
	private Object[] recopilarTweet(ArrayList<String> t, int c, String n) {
		String dato = t.get(c);
		Tweet temp;
		Palabra salida = new Palabra("", null);
		int Cont_en_Tweet = 0;
		
		String fecha = "";
		String likes = "";
		String reTweets = "";
		boolean seguir = false;
		
		while(!seguir && dato != null && dato.compareTo("Verified account") != 0 && dato.compareTo("Who to follow ·  Refresh · View all") != 0) {
			String herramienta[] = dato.split(" ");
			if(herramienta.length >= 6 && herramienta[herramienta.length-1].compareTo("message") == 0 && herramienta[herramienta.length-2].compareTo("Direct") == 0) {
				reTweets = herramienta[3];
				likes = herramienta[6];
				seguir = true;	
				System.out.println("No agregado por Herramienta   //// "+dato);
			}else if(Cont_en_Tweet == 3) {
				fecha = dato;
			}else if(!seguir && dato.compareTo("Who to follow ·  Refresh · View all") != 0){
				for (int i = 0; i < herramienta.length; i++) {
					int puntos[] = identificarPalabra(herramienta[i]);
					salida.agregarUltimo(new Palabra(herramienta[i],puntos));
				}
			}else if(herramienta.length >= 2 && herramienta[herramienta.length-1].compareTo("Retweeted") == 0){
				seguir = true;
				System.out.println("No agregado ");
			}
			
			salida.agregarUltimo(new Palabra("\n",null));
			c++;
			Cont_en_Tweet++;
			dato = t.get(c);
			System.out.println();
			System.out.println(dato+" //  "+c+"   // Procesando TWEET");
			System.out.println();
		}
		
		System.out.println();
		System.out.println(dato+" "+c+" TWEET ACABANDO de procesar");
		System.out.println();
		temp = new Tweet(fecha, salida, likes, reTweets);
		
		Object fin[] = {temp,c-1};
		
		return fin;
	}

	private int[] identificarPalabra(String s) {
		int salida[] = new int[3];
		
		if(s.length()>=6 && s.substring(0, 5).compareToIgnoreCase("http:") == 0) {
			Link temp = new Link(s,null);
			primer_link.agregarUltimo(temp);
			System.out.println(s+"                                       //// Es un LINK");
		}else if(s.length()>=1 && s.substring(0, 1).compareToIgnoreCase("#") == 0){
			Hashtag temp = new Hashtag(s, null, 0);
			if(hashtags.contiene(s)) {
				hashtags.dar(s).setPuntuaion(hashtags.dar(s).getPuntuacion()+1);
			}else {
				hashtags.agregarUltimo(temp);
			}
			System.out.println(s+"                                        //// Es un HASHTAG");
		}               
		
		return salida;
	}

	
	
}
