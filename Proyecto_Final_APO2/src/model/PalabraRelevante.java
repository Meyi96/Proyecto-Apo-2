package model;

import java.io.Serializable;
/**
 * Clase {@link PalabraRelevante} / Clase para modelar una palabra tipo PalabraRelevante
 * @author Nelson Quiñones Virgen - Fabio Andres Mejía - Marco Antonio Perez
 * Version 1.0
 * 27/Noviembre/2018
 */
public class PalabraRelevante  extends Palabra implements Serializable,Agregar{

	static final long serialVersionUID = 42L;
	PalabraRelevante izq;
	PalabraRelevante der;
	
	/**
	 * PalabraRelevante - Metodo constructor de la clase
	 * @param palabra - Un {@link String} con la informacion de la palabra	palabra != null palabra != ""
	 * @param puntuaciones - Una arreglo de int con la puntuacion en cada categoria puntuaciones != null
	 * pos : palabra se inicializa
	 * pos : puntucion se inicializa
	 * pos : der se incializa
	 * pos : izq se inicializa
	 */
	public PalabraRelevante(String palabra, int[] puntuaciones) {
		super(palabra, puntuaciones);
		izq = null;
		der = null;
	}
	
	/**
	 * getDer - Metodo que da La palabra relavante que esta a la izquierda de su raiz 
	 * @return el objeto de la izquierda de la raiz
	 */
	public PalabraRelevante getIzq() {
		return izq;
	}
	
	/**
	 * getDer - Metodo que da La palabra relavante que esta a la derecha de su raiz 
	 * @return el objeto de la dercha de la raiz
	 */
	public PalabraRelevante getDer() {
		return der;
	}

	/**
	 * buscarPalabra - Metodo para encontrar una {@link PalabraRelevante} a partir de un String
	 * @param s - El String con el que se buscara la {@link PalabraRelevante} s != null s != ""
	 * @return Una {@link PalabraRelevante} con la palabra buscada, si no se encuentra retorna null
	 */
	public PalabraRelevante buscarPalabra(String s) {
		if(this.palabra.compareTo(s) == 0) {
			return this;
		}else if(this.palabra.compareTo(s) <= 0){
			if(der != null) {
				return der.buscarPalabra(s);
			}else {
				return null;
			}
		}else {
			if(izq != null) {
				return izq.buscarPalabra(s);
			}else {
				return null;
			}
		}
	}
	/**
	 * AgregarRelevante - Metodo para agregar la {@link PalabraRelevante} a al arbol
	 * @param a La {@link PalabraRelevante} que se quiere agregar a != null
	 * pos : Se agrega una {@link PalabraRelevante} al arbol de busqueda
	 */
	public void AgregarRelevante(PalabraRelevante a) {
		if(this.palabra.compareTo(a.getPalabra()) <= 0){
			if(der != null) {
				der.AgregarRelevante(a);;
			}else {
				der = a;
			}
		}else {
			if(izq != null) {
				izq.AgregarRelevante(a);
			}else {
				izq = a;
			}
		}
		
	}

}
