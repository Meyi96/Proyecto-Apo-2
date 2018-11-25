package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Usuario implements Serializable, Ordenamiento{
	private String Nombre;
	private Tweet lista_tweets;
	private String seguidores;
	private String  seguidos;
	private int Cantidad;
	private int Puntaje[];
	private Usuario izq;
	private Usuario der;
	
	public Usuario(String nombre, String seguidores, String seguidos, Tweet lista_tweets, int Cantidad, int Puntaje[]) {
		super();
		Nombre = nombre;
		this.seguidores = seguidores;
		this.seguidos = seguidos;
		this.lista_tweets = lista_tweets;
		this.Cantidad = Cantidad;
		if(Puntaje == null) {
			calcularPuntaje(lista_tweets);
		}else {
			this.Puntaje = Puntaje; 
		}
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
	
	public int compareTo(Usuario nuevo) {
		if(Cantidad-nuevo.getCantidad()==0) 
			return Nombre.compareTo(nuevo.getNombre());
		else
			return Cantidad-nuevo.getCantidad();
	}

	public int[] getPuntaje() {
		return Puntaje;
	}

	public void setPuntaje(int[] puntaje) {
		Puntaje = puntaje;
	}

	public void inorden(ArrayList<Object> objeto) {
		if(izq != null)
			izq.inorden(objeto);
		objeto.add(this);
		if(der != null)
			der.inorden(objeto);
	}
	

	public void ordenamiento(ArrayList<Object> objeto, char tipo) {
		switch (tipo) {
		case 'n': lista_tweets.obtenerTweets(objeto);
			break;
		case 'o': OrdenamientoNumeroTweets(objeto);

		default:
			break;
		}
	}
	
	private void OrdenamientoNumeroTweets(ArrayList<Object> objeto) {
		lista_tweets.obtenerTweets(objeto);
		for (int i = 0; i < objeto.size(); i++) {
			for (int j = 0; j+1 < objeto.size(); j++) {
				Usuario a = (Usuario)objeto.get(j);
				Usuario b = (Usuario)objeto.get(j+1);
				if(a.compareTo(b)<0) {
					objeto.set(j, b);
					objeto.set(j+1, a);
				}
			}
		}
	}
	
	private void calcularPuntaje(Tweet l) {
		if(l != null) {
			Puntaje[0] += l.getPuntajes()[0];
			Puntaje[1] += l.getPuntajes()[1];
			Puntaje[2] += l.getPuntajes()[2];
			calcularPuntaje(l.getSiguiente());
		}
	}
	
}
