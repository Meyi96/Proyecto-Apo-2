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
	
	/**
	 * Tweet - Metodo Constructor de la clase Tweet
	 * @param Fecha - Un String Representa la fecha en la que fue publicado el Tweet Fecha != null
	 * @param parrafo - Un Lista de palabras con el contenido del Tweet parrafo != null
	 * @param likes2 - Un String con los likes que obtuvo el Tweet likes2 != null 
	 * @param reTweets2 - Un String con las veces que fue ReTweetiado este Tweet reTweets2 != null reTweets 
	 * @param puntajes - Un arreglo con los puntajes que obtuvo el Tweet en cada categoria
	 * pos: Fecha fue inicializada
	 * pos:	primer_Palabra fue inicializado
	 * pos: likes fue inicializada
	 * pos: reTweets fue inicializada
	 * pos: puntajes fue inicializado
	 */
	public Tweet(String Fecha,Palabra parrafo,String likes2,String reTweets2,int puntajes[]) {
		this.Fecha = Fecha;
		this.primera_Palabra = parrafo;
		this.likes = cambiarLikes(likes2);
		this.reTweets = reTweets2;
		if(puntajes == null) {
			calcularPuntajes(primera_Palabra);
		}else {
			this.puntajes = puntajes;
		}
	}
	
	/**
	 * getSiguiente - Metodo para obtener el siguiente elemento de la lista
	 * @return siguiente - La sguiente {@link Palabra} en la lista siguiente != null
	 */
	public Tweet getSiguiente() {
		return siguiente;
	}
	
	/**
	 * setSiguiente - Metodo para modificar el siguiente elemento en la lista
	 * @param siguiente - La {@link Tweet} por la que se quiere remplazar
	 */
	public void setSiguiente(Tweet siguiente) {
		this.siguiente = siguiente;
	}
	
	/**
	 * primer_Palabra - Un metodo para obtener la lista de palbras
	 * @return	primera_Palabra - La primera {@link Palabra} de la lista de palabras
	 */
	public Palabra primera_Palabra() {
		return primera_Palabra;
	}
	
	/**
	 * setParrafo - Metodo para modificar la primera palabra de la lista
	 * @param parrafo - La nueva {@link Palabra} por la que se quiere actualizar parrafo != null
	 */
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
	
	/**
	 * agregarUltimo - Un metodo para agregar en la ultima posicion de lista un {@link Tweet}
	 * @param temp - El {@link Tweet} que se quiere agregar temp != null
	 */
	public void agregarUltimo(Tweet temp) {
		if(this.getSiguiente() == null) {
			this.setSiguiente(temp); ;
		}else {
			this.getSiguiente().agregarUltimo(temp);
		}
		
	}
	
	/**
	 * calcularPuntaje - Un metodo para calcular el puntaje de todas las palabras 
	 * @param p - Una {@link Palabra} de pivote para recorrer todas las palabras de la lista	p != null
	 */
	private void calcularPuntajes(Palabra p) {
		if(p != null) {
			puntajes[0] += p.getPuntuacion_depor();
			puntajes[1] += p.getPuntuacion_poli();
			puntajes[2] += p.getPuntuacion_tecno();
			calcularPuntajes(p.getSiguientePalabra());
		}
	}
	
	/**
	 * getPuntajes - Un metodo para obtener el arreglo de puntajes del {@link Tweet}
	 * @return puntajes - un arreglo con todos los puntajes 
	 */
	public int[] getPuntajes() {
		return puntajes;
	}
	
	/**
	 * setPuntajes - Un metodo para modificar los puntajes del tweet
	 * @param puntajes - El nuevo arreglo de enteros que va a remplazar los puntajes puntajes != null
	 */
	public void setPuntajes(int[] puntajes) {
		this.puntajes = puntajes;
	}
	
	/**
	 * calcularPuntajeTotal - Un metodo para obtener el puntaje total del Tweet
	 * @return Un {@link String} con el puntaje obtenido
	 */
	public String calcularPuntajeTotal() {
		return Integer.toString(puntajes[0]+puntajes[1]+puntajes[2]);
	}
	
	/**
	 * compareTo - Metodo para comparar objetos de tipo {@link Tweet}
	 * @param t -  El Tweet a comparar t != null
	 * @return un entero mayor que 0 si el objeto comparado es menor, menor que 0 si es mayor, igual a 0 si es igual
	 */
	public int compareTo(Tweet t) {
		if(this.calcularPuntajeTotal().compareTo(t.calcularPuntajeTotal()) == 0) {
			int a =(likes.equals(""))?0: Integer.parseInt(likes);
			int b = (t.likes.equals(""))?0:Integer.parseInt(t.likes);
			return a-b;
		}else {
			return this.calcularPuntajeTotal().compareTo(t.calcularPuntajeTotal());
		}
	}
	
	/**
	 * obtenerTweets - Metodo para obtener un Arraylist con los objetos tipo Tweet
	 * @param object - Un ArrayList de objetos de tipo Tweet object != null
	 */
	public void obtenerTweets(ArrayList<Object> object) {
		if(this.getSiguiente() == null) {
			object.add(this);
		}else {
			object.add(this);
			this.getSiguiente().obtenerTweets(object);
		}
	}
	 /**
	  * getTweetEntero - Metodo para obtener todo el texto en bruto
	  * @param s - Cadena vacía que se recibe para posteriormente ser enviada al método getTweetEntero de la clase palabra.
	  * @return String - Retorna una cadena de texto que contiene todas las palabras del tweet.
	  */
	public String getTweetEntero(String s) {
		return primera_Palabra.getTweetEntero(s);
	}
	
	/**
	 * cambiarLikes - Método que modifica el formato en el que se muestran los likes en twitter a uno que java pueda comparar facilmente.
	 * @param dato - El número de likes del tweet en formato de twitter.
	 * @return String - El número de likes en un String limpio. 
	 */
	public String cambiarLikes(String dato) {
		System.out.println(dato);
		String[] n = dato.split(",");
		dato="";
		for (int i = 0; i < n.length; i++) {
			dato += n[i]; 
		}
		System.out.println(dato);
		return dato;
	}
}
