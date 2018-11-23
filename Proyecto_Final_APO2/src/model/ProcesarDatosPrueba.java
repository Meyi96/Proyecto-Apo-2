package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.AbstractDocument.BranchElement;

public class ProcesarDatosPrueba {
	public static FileReader read;
	public static BufferedReader rd;
	public static void main(String args[]) throws IOException {
		File fl = new File("./Persistencia/Usuarios/Elon Musk");
		FileReader read = new FileReader(fl);
		BufferedReader rd = new BufferedReader(read);
		String nombre_Usuario = "";
		String nombre_real = "";
		String dato = "";
		String reTweet = "";
		String seguidos = "";

		
		int contador = 0;
		
		ArrayList<String> Texto_Bruto = new ArrayList<>();
		while(dato != null && dato.compareTo("Who to follow ·  Refresh · View all") != 0) {
			if(contador == 19) {
				nombre_Usuario = dato;
			}else if(contador == 26) {
				reTweet = dato;
			}else if(contador == 29) {
				seguidos = dato;
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
		System.out.println(reTweet);
		System.out.println(seguidos);
		rd.close();
	}
	
	private static Object[] recopilarTweet(ArrayList<String> t, int c, String n) {
		String dato = t.get(c);
		Tweet temp;
		Palabra salida = new Palabra("");
		int Cont_en_Tweet = 0;
		
		String fecha = "";
		String likes = "";
		String reTweets = "";
		boolean seguir = false;
		
		while(dato != null && dato.compareTo("cuenta verificada") != 0) {
			String herramienta[] = dato.split(" ");
			if(herramienta[herramienta.length-2].compareTo("Mensaje") == 0 && herramienta[herramienta.length-1].compareTo("directo") == 0) {
				reTweets = herramienta[3];
				likes = herramienta[6];
				seguir = true;
			}else if(Cont_en_Tweet == 0) {
				fecha = dato;
			}else if(!seguir){
				for (int i = 0; i < herramienta.length; i++) {
					salida.agregarUltimo(new Palabra(herramienta[i]));
				}	
				
			}
			salida.agregarUltimo(new Palabra("\n"));
			c++;
			Cont_en_Tweet++;
			dato = t.get(c);
			
		}
		
		temp = new Tweet(fecha, salida, likes, reTweets);
		
		Object fin[] = {temp,c};
		
		return fin;
	}
	
}
