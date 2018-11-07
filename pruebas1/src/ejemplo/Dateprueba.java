package ejemplo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dateprueba {

	public static void main(String[] args) throws IOException {
		int[]datos = new  int[20];
		
		for (int i = 0; i < datos.length; i++) {
			datos[i]= (int)(Math.random()*20+1);
		}
		
		int dato[] = datos.clone();
		
		// ordenar burbuja
		for (int i = 0; i < datos.length; i++) {
			for (int j = 0; j+1 < datos.length; j++) {
				if(datos[j]>datos[j+1]) {
					int auxil = datos[j];
					datos[j] = datos[j+1];
					datos[j+1]= auxil;
					
				}
			}
		} 
		
		// oredenar seleccion
		for (int i = 0; i+1 < datos.length; i++) {
			int menor = datos[i];
			int index = i;
			for (int j = i+1; j < datos.length; j++) {
				if(menor > datos[j]) {
					menor = datos[j];
					index = j;
				}
			}
			int auxil = datos[i];
			datos[i] = menor;
			datos[index]= auxil;
		}
		
		// ordenar inserccion
		for (int i = 1; i < datos.length; i++) {
			for (int j = i; j > 0 && datos[j] < datos[j-1]; j--) {
				int auxil = datos[j];
				datos[j] = datos[j-1];
				datos[j-1]= auxil;
			}
		}
		
		
		
		// Busqueda binaria
		int buscar = 12;
		int inicio =0;
		int fin = datos.length-1;
		boolean encontre = false;
		while(inicio <=fin && !encontre) {
			int medio = (inicio + fin)/2;
			if(buscar == datos[medio]) {
				encontre = true;
			}else if(buscar < datos[medio]) {
				fin = medio-1;
			}else {
				inicio = medio+1;
			}
		}
		
		
		
//		for (int i = 0; i < datos.length; i++) {
//			System.out.println(dato[i]+"   "+datos[i]);
//		}
		File file = new File("./datos/ejem.txt");
		FileReader reader = new FileReader(file);
		BufferedReader buffer = new BufferedReader(reader);
		String info = buffer.readLine();
		
		File file2 = new File("./datos/ejem2.txt");
		FileWriter write =new FileWriter(file2);
		BufferedWriter w = new BufferedWriter(write);
		PrintWriter wr = new PrintWriter(w);
		int cantidad = 0;
		while(info != null) {
			if (info.equals("Fabio Andres")) {
				cantidad++;
			}else {
				w.write(info);
			}
			info = buffer.readLine();
		}
		wr.close();
		w.close();
		write.close();
		System.out.println(cantidad);
	}
}
