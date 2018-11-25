package model;

import java.io.Serializable;

public class Tweet implements Serializable{
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
	
}
