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
