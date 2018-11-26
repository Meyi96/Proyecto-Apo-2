package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Usuario implements Serializable, Ordenamiento,Agregar{
	static final long serialVersionUID = 42L;
	private String Nombre;
	private Tweet lista_tweets;
	private String seguidores;
	private String  seguidos;
	private int Cantidad;
	private int Puntaje[];
	private Usuario izq;
	private Usuario der;
	
	/**
	 * Usuario - Metodo constructor de la clase Usuario 
	 * @param nombre - Un {@link String} con el nombre del usuario nombre != null nombre != ""
	 * @param seguidores - Un {@link String} representando la cantidad de seguidores que tiene el usuario
	 * @param seguidos - Un {@link String} representando la cantidad de personas que sigue el usuario	seguidos != null	seguidos != ""
	 * @param lista_tweets - Una lista de {@link Tweet} con algunos de los Tweets publicados por el {@link Usuario} 
	 * @param Cantidad - Un entero con la cantidad de Tweets cargados en la aplicacion del usuario
	 * @param Puntaje - Un arreglo con todas las puntuaciones del usuario en tres categorías
	 * pos : Se incializa el nombre
	 * pos : Se inicializa seguidores
	 * pos : se inicializa seguidos
	 * pos : Se inicializa la lista_tweets
	 * pos : Se inicializa la Cantidad 
	 * pos : Se inicializa Puntaje
	 */
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
	
	/**
	 * getNombre - Metodo para retornar el nombre del Usuario
	 * @return Un {@link String} con el nombre del usuario
	 */
	public String getNombre() {
		return Nombre;
	}
	
	/**
	 * serNombre - Actualiza el nombre del Usuario
	 * @param nombre - Un metodo con el nuevo nombre del Usuario
	 */
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	/**
	 * getSeguidores - Metodo para obtener la cantidad de seguidores del usuario
	 * @return Un {@link String} con la cantidad de seguidores
	 */
	public String getSeguidores() {
		return seguidores;
	}
	/**
	 * setSeguidores -  Un metodo con para actualizar el valor de los seguidores
	 * @param seguidores - El nuevo valor para los seguidores	seguidores != null seguidores != ""
	 */
	public void setSeguidores(String seguidores) {
		this.seguidores = seguidores;
	}
	/**
	 * getSeguidos - Un metodo que retorna el valor de los seguidores
	 * @return Un {@link String} con el valor de los seguidores
	 */
	public String getSeguidos() {
		return seguidos;
	}
	/**
	 * setSeguidos - Metodo para actualizar el valor de seguidos
	 * @param seguidos - Un {@link String}
	 */
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

	@Override
	public void agregar(Object objeto) {
		if (objeto instanceof Usuario) {
			Usuario temp = (Usuario) objeto;
			agregarArbol(temp);
		}
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
		case 'a': {
			lista_tweets.obtenerTweets(objeto);
		}
			break;
		case 'o': OrdenamientoNumeroTweets(objeto);

		default:
			break;
		}
	}
	
	private void OrdenamientoNumeroTweets(ArrayList<Object> objeto) {
		lista_tweets.obtenerTweets(objeto);
		for (int i = 1; i < objeto.size()-1; i++) {
			Tweet mayor = (Tweet)objeto.get(i);
			int cual = i;
			for (int j = i+1; j < objeto.size(); j++) {
				Tweet actual = (Tweet)objeto.get(j);
				if(actual.compareTo(mayor) > 0) {
					mayor = actual;
					cual = j;
				}
			}
			Tweet temp = (Tweet)objeto.get(i);
			objeto.set(i, mayor);
			objeto.set(cual, temp);
		}
	}
	
	public Usuario buscarUsuario(String usuario) {
		if (Nombre.compareTo(usuario) == 0) {
			return this;
		} else if (Nombre.compareTo(usuario) > 0) {
			return (izq == null) ? null : izq.buscarUsuario(usuario);
		} else {
			return (der == null) ? null : der.buscarUsuario(usuario);
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
