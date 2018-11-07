package ejemplo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.sun.javafx.iio.ImageFormatDescription.Signature;

public class Salon {
	
	private Estudiante primero;
	private int CantidadE;
	
	public Salon() {
		CantidadE=0;
	}
	public void agregarEstudiante1(Estudiante p) {
		p.setSiguiente(primero);
		primero = p;
		CantidadE++;
	}
	
	public void agregarEstudiante2(Estudiante p) {
		Estudiante anterior = primero;
		Estudiante actual = primero;
		if(actual !=null) {
			while(actual!=null) {
				anterior = actual;
				actual = actual.getSiguiente();
			}
			anterior.setSiguiente(p);
		}else {
			primero = p; 
		}
		CantidadE++;
	}
	
	public void borrar(String codigo) {
		Estudiante actual = primero;
		Estudiante anterior = primero;
		boolean encontre = false;
		if(actual != null && actual.getCodigo().equals(codigo)) {
			primero = actual.getSiguiente();
		}
		else if(actual != null) {
			actual = actual.getSiguiente();
			while(!encontre && actual != null) {
				if(actual.getCodigo().equals(codigo)) {
					anterior.setSiguiente(actual.getSiguiente());
					encontre = true;
				}
				else {
					anterior = actual;
					actual = actual.getSiguiente();
				}
			}
		}
	}
	
	public void mostrarCod1() {
		Estudiante actual = primero;
		while(actual != null) {
			System.out.println(actual.getCodigo());
			actual = actual.getSiguiente();
		}
		System.out.println("===================\n" + CantidadE);
	}
	
	public void mostrarCod() {
		Estudiante actual = primero;
		while(actual != null) {
			System.out.println(actual.getEdad());
			actual = actual.getSiguiente();
		}
		System.out.println("===================\nTamano: " + CantidadE);
	}
	
	public void ordenar() {
		Estudiante actual1 = primero;
		while(actual1 != null) {
			Estudiante actual = primero;
			Estudiante anterior = primero;
			Estudiante despues = actual.getSiguiente();
			while(despues != null) {
				if(actual.getEdad() < despues.getEdad()) {
					if(primero.equals(actual)) {
						primero = despues;
						actual.setSiguiente(despues.getSiguiente());
						despues.setSiguiente(actual);
					}else {
						anterior.setSiguiente(despues);
						actual.setSiguiente(despues.getSiguiente());
						despues.setSiguiente(actual);
					}
				}
				anterior = actual;
				actual = despues;
				despues = despues.getSiguiente();
			}
			actual1 = actual1.getSiguiente();
			
		}
	}
	
	
	
	
}
