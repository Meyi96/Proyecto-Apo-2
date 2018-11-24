package model;

import java.io.Serializable;

public class Hashtag extends Palabra implements Serializable{
	private int repeticiones;
	
	public Hashtag(String palabra, int[] puntuaciones,int repeticiones) {
		super(palabra, puntuaciones);
		this.repeticiones = repeticiones;
	}

	public boolean contiene(String s) {
		if(this.getPalabra().compareTo(s) == 0) {
			return true;
		}else {
			if(this.getSiguiente_palabra() != null) {
				return ((Hashtag)this.getSiguiente_palabra()).contiene(s);
			}
		}
		return false;
	}

	public Hashtag dar(String s) {
		if(this.getPalabra().compareTo(s) == 0) {
			return this;
		}else {
			if(this.getSiguiente_palabra() != null) {
				return ((Hashtag)this.getSiguiente_palabra()).dar(s);
			}
		}
		return null;
	}

	public int getPuntuacion() {
		return 0;
	}

	public void setPuntuaion(int i) {
		repeticiones = i;
		
	}

}
