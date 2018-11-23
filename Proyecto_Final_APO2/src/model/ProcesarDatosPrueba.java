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
		String nombbre_real = "";
		String dato = "";
		String reTweet = "";
		String seguidos = "";

		
		int contador = 0;
		
		ArrayList<String> Texto_Bruto = new ArrayList<>();
		while(dato != null && !dato.equals("Who to follow ·  Refresh · View all")) {
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

		
		Tweet lista_t = new Tweet("", null, 0, 0);
		contador = 0;
		dato = Texto_Bruto.get(contador);
		
		while(dato != null && dato.compareTo("Who to follow ·  Refresh · View all") != 0) {
			Tweet temp;
			if(dato.compareTo(nombre_Usuario) == 0) {
				temp = recopilarTweet(Texto_Bruto,contador+1,nombre_Usuario);
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
	
	private static Tweet recopilarTweet(ArrayList<String> t, int c, String n) {
		String dato = t.get(c);
		Tweet temp;
		Palabra salida = new Palabra("");
		int Cont_en_Tweet = 0;
		while(dato != null && dato.compareTo(n) != 0) {
			if(Cont_en_Tweet < 2) {
				
			}
			salida.agregarUltimo(new Palabra(dato));
			c++;
			Cont_en_Tweet++;
			dato = t.get(c);
		}
		
		//temp = new Tweet(Fecha, parrafo, likes, reTweets);
		return null;
	}
	
}
