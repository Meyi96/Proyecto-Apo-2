package model;

import java.io.Serializable;

public class Tweet implements Serializable{
	private Tweet siguiente;
	private String Fecha;
	private Palabra primera_Palabra;
	private String likes;
	private String reTweets;
	private int puntajes[];
	
	
	public Tweet(String Fecha,Palabra parrafo,String likes2,String reTweets2) {
		this.Fecha = Fecha;
		this.primera_Palabra = parrafo;
		this.likes = likes2;
		this.reTweets = reTweets2;
		calcularPuntajes();
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
	


	private void calcularPuntajes() {
		// TODO Auto-generated method stub
		
	}
	
}
