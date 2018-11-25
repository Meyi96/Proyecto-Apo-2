package model;

import java.io.Serializable;

public class PalabraRelevante  extends Palabra implements Serializable{
	
	PalabraRelevante izq;
	PalabraRelevante der;
	
	public PalabraRelevante(String palabra, int[] puntuaciones) {
		super(palabra, puntuaciones);
		izq = null;
		der = null;
	}

	public String buscarPalabra(String s) {
		
		return null;
	}

}
