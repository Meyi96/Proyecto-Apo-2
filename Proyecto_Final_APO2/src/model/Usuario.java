package model;

public class Usuario {
	String Nombre;
	Tweet lista_tweets;
	int seguidores;
	int seguidos;
	
	public Usuario(String nombre, int seguidores, int seguidos, Tweet lista_tweets) {
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
	public int getSeguidores() {
		return seguidores;
	}
	public void setSeguidores(int seguidores) {
		this.seguidores = seguidores;
	}
	public int getSeguidos() {
		return seguidos;
	}
	public void setSeguidos(int seguidos) {
		this.seguidos = seguidos;
	}
	public Tweet getLista_tweets() {
		return lista_tweets;
	}
	public void setLista_tweets(Tweet lista_tweets) {
		this.lista_tweets = lista_tweets;
	}
	
	
}
