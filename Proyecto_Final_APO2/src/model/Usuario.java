package model;

import java.io.Serializable;

public class Usuario implements Serializable{
	String Nombre;
	Tweet lista_tweets;
	boolean verificado;
	String seguidores;
	String  seguidos;
	
	public Usuario(String nombre, String seguidores, String seguidos, Tweet lista_tweets) {
		super();
		Nombre = nombre;
		this.seguidores = seguidores;
		this.seguidos = seguidos;
		this.lista_tweets = lista_tweets;
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
	
	
}
