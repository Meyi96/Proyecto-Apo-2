package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Hashtag extends Palabra implements Serializable, Agregar{
	static final long serialVersionUID = 42L;
	private int repeticiones;
	private Hashtag siguienteHashtag;
	
	public Hashtag(String palabra, int[] puntuaciones,int repeticiones) {
		super(palabra, puntuaciones);
		this.repeticiones = repeticiones;
	}

	public Hashtag getSiguienteHashtag() {
		return siguienteHashtag;
	}
	
	public void setSiguienteHashtag(Hashtag siguiente_palabra) {
		this.siguiente_palabra = siguiente_palabra;
	}
	
	public int compareTo(Hashtag temp) {
		if(this.repeticiones == temp.repeticiones) {
			return this.palabra.compareTo(temp.palabra);
		}else {
			return this.repeticiones - temp.repeticiones;
		}
	}
	
	@Override
	public void agregar(Object o) {
		if(o instanceof Hashtag) {
				Hashtag temp = (Hashtag) o;
				this.agregarUltimo(temp);
		}
	}
	
	public void agregarUltimo(Hashtag p) {
		if(this.getSiguientePalabra() == null) {
			this.setSiguiente_palabra(p);
		}else {
			this.getSiguientePalabra().agregarUltimo(p);
		}
	}
	
	public boolean contiene(String s) {
		if(this.getPalabra().compareTo(s) == 0) {
			return true;
		}else {
			if(this.getSiguienteHashtag() != null) {
				return ((Hashtag)this.getSiguienteHashtag()).contiene(s);
			}
		}
		return false;
	}

	public Hashtag dar(String s) {
		if(this.getPalabra().compareTo(s) == 0) {
			return this;
		}else {
			if(this.getSiguienteHashtag() != null) {
				return ((Hashtag)this.getSiguienteHashtag()).dar(s);
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
	
	public void darHashtags(ArrayList<Object> objeto){
		if(getSiguienteHashtag() == null) {
			objeto.add(this);
		}else{
			objeto.add(this);
			this.getSiguienteHashtag().darHashtags(objeto);
		}
	}


}
