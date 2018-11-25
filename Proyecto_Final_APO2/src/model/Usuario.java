package model;

import java.io.Serializable;

public class Usuario implements Serializable{
	private String Nombre;
	private Tweet lista_tweets;
	private String seguidores;
	private String  seguidos;
	private int Cantidad;
	private Usuario izq;
	private Usuario der;
	
	public Usuario(String nombre, String seguidores, String seguidos, Tweet lista_tweets, int Cantidad) {
		super();
		Nombre = nombre;
		this.seguidores = seguidores;
		this.seguidos = seguidos;
		this.lista_tweets = lista_tweets;
		this.Cantidad = Cantidad;
	}
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getSeguidores() {
		return seguidores;
	}
	public void setSeguidores(String seguidores) {
		this.seguidores = seguidores;
	}
	public String getSeguidos() {
		return seguidos;
	}
	public void setSeguidos(String seguidos) {
		this.seguidos = seguidos;
	}
	public Tweet getLista_tweets() {
		return lista_tweets;
	}
	public void setLista_tweets(Tweet lista_tweets) {
		this.lista_tweets = lista_tweets;
	}
	
	public void agregarTweet(Tweet agregar) {
		lista_tweets.agregarUltimo(agregar);
		Cantidad++;
	}
	public int getCantidad() {
		return Cantidad;
	}

	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}

	public void agregarArbol(Usuario creado) {
		if(this.getNombre().compareTo(creado.getNombre()) > 0) {
			if(this.izq != null) {
				izq.agregarArbol(creado);
			}else {
				izq = creado;
			}
		}else if(this.getNombre().compareTo(creado.getNombre()) <= 0){
			if(this.der != null) {
				der.agregarArbol(creado);
			}else {
				der = creado;
			}
		}
		
	}
	
	
}
