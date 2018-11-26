package model;

import java.io.Serializable;

public class Palabra implements Serializable, Agregar{
	static final long serialVersionUID = 42L;
	protected String palabra;
	protected Palabra siguiente_palabra;
	protected int puntuaciones[];

	
	public Palabra(String palabra,int puntuaciones[]) {
		this.palabra = palabra;
		this.puntuaciones = puntuaciones;
	}
	
	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public Palabra getSiguientePalabra() {
		return siguiente_palabra;
	}

	public void setSiguiente_palabra(Palabra siguiente_palabra) {
		this.siguiente_palabra = siguiente_palabra;
	}

	public int getPuntuacion_tecno() {
		return puntuaciones[0];
	}

	public void setPuntuacion_tecno(int puntuacion_tecno) {
		this.puntuaciones[0] = puntuacion_tecno;
	}

	public int getPuntuacion_poli() {
		return puntuaciones[1];
	}

	public void setPuntuacion_poli(int puntuacion_poli) {
		this.puntuaciones[1] = puntuacion_poli;
	}

	public int getPuntuacion_depor() {
		return puntuaciones[2];
	}

	public void setPuntuacion_depor(int puntuacion_depor) {
		this.puntuaciones[2] = puntuacion_depor;
	}


	@Override
	public void agregar(Object objeto) {
		if (objeto instanceof Palabra) {
			Palabra temp = (Palabra) objeto;
			agregarUltimo(temp);
		}
		
	}
	

	public void agregarUltimo(Palabra palabra2) {
		if(this.getSiguientePalabra() == null) {
			this.setSiguiente_palabra(palabra2);
		}else {
			this.getSiguientePalabra().agregarUltimo(palabra2);
		}
	}
	
	public String getTweetEntero(String s) {
		if(siguiente_palabra == null) {
			return s += this.palabra;
		}else {
			return s += this.palabra + " " + siguiente_palabra.getTweetEntero(s);
		}
	}
	
}
