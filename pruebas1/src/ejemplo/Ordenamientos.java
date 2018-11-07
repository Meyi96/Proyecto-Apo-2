package ejemplo;

import java.util.ArrayList;

public class Ordenamientos {

	ArrayList <Integer>a = new ArrayList();
	int index = 0;
	
	public Ordenamientos() {
		for (int i = 0; i <999; i++) {
			a.add((int)(Math.random()*5)+1);
		}
		for (int i = 0; i < a.size();i++) {
			System.out.println(i + " " + a.get(i));
		}
		System.out.println("===================================");
	}
	public static void main(String[] args) {
		Ordenamientos or = new Ordenamientos();
		or.OrdenamientoSelection();
		System.out.println("Esta en la posicion: " + or.buscar(4));
		
	}
	
	public void print() {
		for (int i = 0; i < a.size();i++) {
			System.out.println(i + " " + a.get(i));
		}
	}
	
	public void OrdenamientoBurbuja() {
		for (int i = 0; i < a.size(); i++) {
			for (int j = 0; j+1 < a.size(); j++) {
				if(a.get(j)> a.get(j+1)) {
					int b = a.get(j);
					a.set(j, a.get(j+1));
					a.set(j+1, b);
				}
			}
		}
		print();
	}
	
	public void OrdenamientoInserccion() {
		for (int i = 1; i < a.size(); i++) {
			for (int j = i; j > 0 && a.get(j)< a.get(j-1); j--) {
				
				int b = a.get(j);
				a.set(j, a.get(j-1));
				a.set(j-1, b);
			}
			
		}
		print();
	}
	public void OrdenamientoSelection() {
		for (int i = 0; i < a.size()-1; i++) {
			index = i;
			int menor = a.get(i);
			for (int j = i+1; j < a.size();j++) {
				if(menor > a.get(j)) {
					index = j;
					menor = a.get(j);
				}
			}
			if(index != i) {
				int b = a.get(i);
				a.set(i, a.get(index));
				a.set(index, b);
			}
			
		}
		print();
	}
	
	public int buscar(int este) {
		int tome = -1;
		int inicio =0;
		int fin = a.size()-1;
		boolean encontre = false;
		while(!encontre && inicio <= fin) {
			int medio = (inicio + fin)/2;
			if (a.get(medio) == este) {
				tome = medio;
				encontre = true;
				System.out.println("Se repite: " + contar(a, medio));;
			}
			else if(este < a.get(medio)) {
				fin = medio-1;
			}
			else {
				inicio = medio+1;
			}
		}
		return tome;
	}
	
	public int contar (ArrayList n, int poscion) {
		boolean izquierda = false;
		boolean termine = false;
		int recorridoD = poscion+1;
		int recorridoI = poscion-1;
		int contador = 1;
		while(!termine) {
			if (!izquierda) {
				if(n.get(recorridoI) == n.get(poscion)) {
					contador++;
					recorridoI--;
				}else {
					izquierda = true;
				}
			}else {
				if(n.get(recorridoD) == n.get(poscion)) {
					contador++;
					recorridoD++;
				}else {
					termine= true;
				}
			}
		}
		return contador;
	} 
	
	
	

}
