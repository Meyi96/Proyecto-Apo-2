package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase {@link Hashtag} / Clase para modelar una palabra tipo HashTag
 * @author Nelson Quiñones Virgen - Fabio Andres Mejía - Marco Antonio Perez
 * Version 1.0
 * 27/Noviembre/2018
 */
public class Hashtag extends Palabra implements Serializable, Agregar{
	static final long serialVersionUID = 42L;
	private int repeticiones;
	private Hashtag siguienteHashtag;
	
	/**
	 * Hashtag - Metodo constructor de la clase Hashtag
	 * @param palabra - Un {@link String} con la informacion de la palabra	palabra != null palabra != ""
	 * @param puntuaciones - Una arreglo de int con la puntuacion en cada categoria puntuaciones != null
	 * @param repeticiones - Un entero que representa la cantidad de repeticiones de la palabra	repeticiones != null
	 * pos : palabra se inicializa
	 * pos : puntucion se inicializa
	 * pos : palabra se inicializa
	 */
	public Hashtag(String palabra, int[] puntuaciones,int repeticiones) {
		super(palabra, puntuaciones);
		this.repeticiones = repeticiones;
	}
	
	/**
	 * getSiguienteHashtag - Metodo para retornar el siguiente elemento de lista
	 * @return siguienteHashtag - El siguiente {@link Hashtag} de la lista
	 */
	public Hashtag getSiguienteHashtag() {
		return siguienteHashtag;
	}
	
	/**
	 * setSiguienteHashtag - Metodo para cambiar el siguiente valor de la lista del elemento actual
	 * @param siguiente_palabra - Un {@link Hashtag} con el nuevo valor siguiente_palabra != null
	 */
	public void setSiguienteHashtag(Hashtag siguiente_palabra) {
		this.siguienteHashtag = siguiente_palabra;
	}
	
	/**
	 * compareTo - Metodo para comparar objetos tipo {@link Hashtag}
	 * @param temp - El elemento de tipo {@link Hashtag} que se va a comparar temp != null
	 * @return un entero indicando Si el elemento es mayor o menor
	 * pre : repeticiones esta inicializado
	 */
	public int compareTo(Hashtag temp) {
		if(this.repeticiones == temp.repeticiones) {
			return this.palabra.compareToIgnoreCase(temp.palabra)*-1;
		}else {
			return this.repeticiones - temp.repeticiones;
		}
	}
	
	@Override
	public void agregar(Object o) {
		if(o instanceof Hashtag) {
				Hashtag temp = (Hashtag) o;
				this.agregarUltimo(temp);
		}
	}
	
	/**
	 * agregarUltimo - Un metodo para agregar el ultimo elemento a la lista
	 * @param p - Un elemento de tipo {@link Hashtag} que se va agregar a lista p != null
	 * pos : se agrego un elelmento a la lista de {@link Hashtag}
	 */
	public void agregarUltimo(Hashtag p) {
		if(this.getSiguienteHashtag() == null) {
			this.setSiguienteHashtag(p);
		}else {
			this.getSiguienteHashtag().agregarUltimo(p);
		}
	}
	
	/**
	 * contiene - Un metodo para determinar si un elemento con un {@link String} s en la lista
	 * @param s : El {@link String} que se va verificar la existencia del objeto  s != null s!= ""
	 * @return un {@link Boolean} representado si se encontro o no el elemento en la lista
	 */
	public boolean contiene(String s) {
		if(this.getPalabra().compareTo(s) == 0) {
			return true;
		}else {
			if(this.getSiguienteHashtag() != null) {
				return ((Hashtag)this.getSiguienteHashtag()).contiene(s);
			}
		}
		return false;
	}
	
	/**
	 * dar - Un metodo para obtener un objeto de tipo {@link Hashtag} a partir de un String s 
	 * @param s : El {@link String} que se va retornar el objeto  s != null s!= ""
	 * @return Retorna un objeto de tipo {@link Hashtag} identificado con el {@link String} s, si no lo encuentra retorna null
	 */
	public Hashtag dar(String s) {
		if(this.getPalabra().compareTo(s) == 0) {
			return this;
		}else {
			if(this.getSiguienteHashtag() != null) {
				return ((Hashtag)this.getSiguienteHashtag()).dar(s);
			}
		}
		return null;
	}
	
	/**
	 * getRepeticiones - Un metodo para obtener un entero con las repiticiones
	 * @return Un entero con todas las repeticiones del {@link Hashtag}
	 */
	public int getRepeticiones() {
		return repeticiones;
	}
	
	/**
	 * setRepeticion - Un metodo para actualizar la cantidad de repeticiones dell metodo
	 * @param i - La nueva cantidad de repeticiones del objeto
	 */
	public void setRepeticion(int i) {
		repeticiones = i;
	}
	
	/**
	 * darHashtags - Un metodo para retornar un Arraylist de {@link Hashtag} con todos los elementos de la lista
	 * @param objeto - El Arraylist de objetos en el que se almacenaran los objetos de la lista
	 */
	public void darHashtags(ArrayList<Object> objeto){
		if(getSiguienteHashtag() == null) {
			objeto.add(this);
		}else{
			objeto.add(this);
			this.getSiguienteHashtag().darHashtags(objeto);
		}
	}


}
