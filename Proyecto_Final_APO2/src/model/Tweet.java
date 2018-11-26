package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Tweet implements Serializable,Agregar{
	static final long serialVersionUID = 42L;
	private Tweet siguiente;
	private String Fecha;
	private Palabra primera_Palabra;
	private String likes;
	private String reTweets;
	private int puntajes[];
	
	
	public Tweet(String Fecha,Palabra parrafo,String likes2,String reTweets2,int puntajes[]) {
		this.Fecha = Fecha;
		this.primera_Palabra = parrafo;
		this.likes = likes2;
		this.reTweets = reTweets2;
		if(puntajes == null) {
			calcularPuntajes(primera_Palabra);
		}else {
			this.puntajes = puntajes;
		}
	}

	public Tweet getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Tweet siguiente) {
		this.siguiente = siguiente;
	}

	public Palabra primera_Palabra() {
		return primera_Palabra;
	}

	public void setParrafo(Palabra parrafo) {
		this.primera_Palabra = parrafo;
	}


	@Override
	public void agregar(Object objeto) {
		if (objeto instanceof Tweet) {
			Tweet temp = (Tweet) objeto;
			agregarUltimo(temp);
		}
		
	}

	public void agregarUltimo(Tweet temp) {
		if(this.getSiguiente() == null) {
			this.setSiguiente(temp); ;
		}else {
			this.getSiguiente().agregarUltimo(temp);
		}
		
	}
	
	private void calcularPuntajes(Palabra p) {
		if(p != null) {
			puntajes[0] = p.getPuntuacion_depor();
			puntajes[1] = p.getPuntuacion_poli();
			puntajes[2] = p.getPuntuacion_tecno();
			calcularPuntajes(p.getSiguiente_palabra());
		}
	}

	public int[] getPuntajes() {
		return puntajes;
	}

	public void setPuntajes(int[] puntajes) {
		this.puntajes = puntajes;
	}
	
	public String calcularPuntajeTotal() {
		return Integer.toString(puntajes[0]+puntajes[1]+puntajes[2]);
	}
	
	public int compareTo(Tweet t) {
		if(this.calcularPuntajeTotal().compareTo(t.calcularPuntajeTotal()) == 0) {
			return likes.compareTo(t.likes);
		}else {
			return this.calcularPuntajeTotal().compareTo(t.calcularPuntajeTotal());
		}
	}
	
	public void obtenerTweets(ArrayList<Object> object, Tweet actual) {
		if(actual.getSiguiente() == null) {
			object.add(actual);
		}else {
			object.add(actual);
			obtenerTweets(object, actual.getSiguiente());
		}
	}
	
	public void tweetsOrdenados(ArrayList<Object> objeto, char tipo, Usuario actual) {
		actual.ordenamiento(objeto, tipo);
	}
	
	public String getTweetEntero(String s) {
		return primera_Palabra.getTweetEntero(s);
	}
}
