package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Clase {@link Usuario} / Clase para modelar un Usuario
 * @author Nelson Quiñones Virgen - Fabio Andres Mejía - Marco Antonio Perez
 * Version 1.0
 * 27/Noviembre/2018
 */

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
	private ArrayList<Mencion> menciones;
	
	/**
	 * Usuario - Metodo constructor de la clase Usuario 
	 * @param nombre - Un {@link String} con el nombre del usuario nombre != null nombre != ""
	 * @param seguidores - Un {@link String} representando la cantidad de seguidores que tiene el usuario
	 * @param seguidos - Un {@link String} representando la cantidad de personas que sigue el usuario	seguidos != null	seguidos != ""
	 * @param lista_tweets - Una lista de {@link Tweet} con algunos de los Tweets publicados por el {@link Usuario} 
	 * @param Cantidad - Un entero con la cantidad de Tweets cargados en la aplicacion del usuario
	 * @param Puntaje - Un arreglo con todas las puntuaciones del usuario en tres categorías
	 * @param ArrayList menciones - Un ArrayList con las menciones 
	 * pos : Se incializa el nombre
	 * pos : Se inicializa seguidores
	 * pos : se inicializa seguidos
	 * pos : Se inicializa la lista_tweets
	 * pos : Se inicializa la Cantidad 
	 * pos : Se inicializa Puntaje
	 * pos : Se incializan las menciones
	 */
	public Usuario(String nombre, String seguidores, String seguidos, Tweet lista_tweets, int Cantidad, int Puntaje[],ArrayList<Mencion> menciones) {
		super();
		Nombre = nombre;
		this.seguidores = seguidores;
		this.seguidos = seguidos;
		this.lista_tweets = lista_tweets;
		this.Cantidad = Cantidad;
		this.menciones = menciones;
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
	/**
	 * getLista_tweets - Metodo para obtener la lista de Tweets
	 * @return lista_tweets - La lista de {@link Tweet}
	 */
	public Tweet getLista_tweets() {
		return lista_tweets;
	}
	/**
	 * setLista_tweets - Actualiza la lista de {@link Tweet}
	 * @param lista_tweets - La nueva lista de {@link Tweet} lista_tweets != null
	 */
	public void setLista_tweets(Tweet lista_tweets) {
		this.lista_tweets = lista_tweets;
	}
	/**
	 * agregarTweet - Se agrega un Tweet a lista de {@link Tweet}
	 * @param agregar - Un {@link Tweet} a agregar en lista  agregar != null
	 * pos : Se agrega un elemento a la lista de {@link Tweet}
	 */
	public void agregarTweet(Tweet agregar) {
		lista_tweets.agregarUltimo(agregar);
		Cantidad++;
	}
	
	/**
	 * getCantidad - Un metododo para retornar la cantidad de Tweet que tiene un Usuario
	 * @return Cantidad - Un entero con la cantidad de Tweets
	 */
	public int getCantidad() {
		return Cantidad;
	}

	/**
	 * setCantidad - Un metododo para actualizar la cantidad de Tweet que tiene un Usuario
	 * @param cantidad - Un entero con la nueva cantidad de Tweets
	 */
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
	/**
	 * agregarArbol - Metodo para agregar un usuario al arbol de busqueda 
	 * @param creado - El {@link Usuario} a agregar en el arbol creado != null
	 * pos : der o izq se inicializacian o en su defecto se agrega un elemento en el arbol
	 */
	public void agregarArbol(Usuario creado) {
		if(this.getNombre().compareToIgnoreCase(creado.getNombre()) > 0) {
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
	/**
	 * compareTo - Metodo para comparar dos objetos de tipo Usuario
	 * @param nuevo - El objeto {@link Usuario} que se va a comparar nuevo != null
	 * @return un entero representando si el objeto a comparar es mayor (1) o menor (-
	 */
	public int compareTo(Usuario nuevo) {
		if(Cantidad-nuevo.getCantidad()==0) 
			return Nombre.compareToIgnoreCase(nuevo.getNombre());
		else
			return Cantidad-nuevo.getCantidad();
	}
	
	/**
	 * getPuntaje - Metodo que retorna un arreglo de enteros con todos los puntajes de los usurios
	 * @return Puntaje - Un arreglo de enteros con los puntajes en las 3 categorias
	 */
	public int[] getPuntaje() {
		return Puntaje;
	}
	
	/**
	 * setPuntaje - Actuliza el valor de los puntajes
	 * @param puntaje -  Un arreglo con el nuevo puntaje para el {@link Usuario} puntaje != null
	 */
	public void setPuntaje(int[] puntaje) {
		Puntaje = puntaje;
	}

	/**
	 * inorden - Lista todos los usuarios del arbol en el orden natural del arbol 
	 * @param objeto - El arreglo de objetos en el que se guardan los objetos organizados
	 */
	public void inorden(ArrayList<Object> objeto) {
			if(izq != null)
				izq.inorden(objeto);
			objeto.add(this);
			if(der != null)
				der.inorden(objeto);
	}
	
	/**
	 * ordenamiento - Un metodo que obtiene una colleccion de Usuarios ordenada por varios criterios 
	 * @param objeto - El arreglo de objetos en el que se guardan los objetos organizados	objeto != null
	 * @param tipo - Un caracter para definir el tipo de operacion que se va a ejecutar sobre el arreglo de objetos tipo != null tipo = 'a' tipo = 'o'
	 */
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
	
	/**
	 * OrdenamientoNumeroTweets - Un metodo para ordenar los Tweets basado en su orden natural
	 * @param objeto - Un Arraylist con los elementos a ordenar  objeto != null
	 */
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
	
	/**
	 * buscarUsuario - Se busca un usuario a partir de de un identificador de String
	 * @param usuario - Un {@link String} con el nombre	 usuario != null	usuario != ""
	 * @return Se retorna un {@link Usuario} con el nombre buscado , de no encontrarlo se retorna null
	 */
	public Usuario buscarUsuario(String usuario) {
		if (Nombre.compareTo(usuario) == 0) {
			return this;
		} else if (Nombre.compareTo(usuario) > 0) {
			return (izq == null) ? null : izq.buscarUsuario(usuario);
		} else {
			return (der == null) ? null : der.buscarUsuario(usuario);
		}
	}
	
	/**
	 * calcularPuntaje - Metodo para calcular el puntaje obtenido de una Lista De Tweet en todas las categorias
	 * @param l - El {@link Tweet} que se esta evaluando
	 */
	private void calcularPuntaje(Tweet l) {
		if(l != null) {
			Puntaje[0] += l.getPuntajes()[0];
			Puntaje[1] += l.getPuntajes()[1];
			Puntaje[2] += l.getPuntajes()[2];
			calcularPuntaje(l.getSiguiente());
		}
	}
	
	/**
	 * getMenciones - Metodo que retorna un Arraylist de {@link Mencion} con todos las menciones del usurio
	 * @return menciones - Un Arraylist de {@link Mencion} con las menciones del usuario
	 */
	public ArrayList<Mencion> getMenciones() {
		return menciones;
	}
	/**
	 * setMenciones - Metodo que modifica un Arraylist de {@link Mencion}
	 * @param menciones - Un Arraylist de {@link Mencion} con las nuevas menciones del usuario menciones != null
	 */
	public void setMenciones(ArrayList<Mencion> menciones) {
		this.menciones = menciones;
	}
	
	
	
}
