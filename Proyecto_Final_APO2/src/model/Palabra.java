package model;

import java.io.Serializable;

public class Palabra implements Serializable, Agregar{
	static final long serialVersionUID = 42L;
	protected String palabra;
	protected Palabra siguiente_palabra;
	protected int puntuaciones[];

	/**
	 * Palabra - Metodo constructor de Palabra
	 * @param palabra - Un {@link String} con la informacion de la palabra	palabra != null palabra != ""
	 * @param puntuaciones - Una arreglo de int con la puntuacion en cada categoria puntuaciones != null
	 * pos : palabra se inicializa
	 * pos : puntucion se inicializa
	 */
	public Palabra(String palabra,int puntuaciones[]) {
		this.palabra = palabra;
		this.puntuaciones = puntuaciones;
	}
	
	/**
	 * getPalabra - Metodo para retornar un {@link String} con la palabra caracteristica de la {@link Palabra}
	 * @return palabra - un {@link String} con la palabra caracteristica
	 */
	public String getPalabra() {
		return palabra;
	}
	
	/**
	 * setPalabra - Metodo para cambiar el valor de la palabra caracteristica
	 * @param palabra - El {@link String} con el que se reemplazara el valor bu
	 */
	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}
	
	/**
	 * getSiguientePalabra - El metodo permite obtener la siguiente palabra de la lista
	 * @return siguiente_palabra : La {@link Palabra} que se devuelva
	 */
	public Palabra getSiguientePalabra() {
		return siguiente_palabra;
	}
	/**
	 * setSiguiente_palabra - El metodo cambia el valor de la siguiente palabra
	 * @param siguiente_palabra - Es la {@link Palabra} nueva
	 */
	public void setSiguiente_palabra(Palabra siguiente_palabra) {
		this.siguiente_palabra = siguiente_palabra;
	}
	
	/**
	 * getPuntuacion_tecno - Es un metodo que retorna un entero con el valor de la puntuacion en tecnología
	 * @return Un entero con la puntuacion en tecnología
	 */
	public int getPuntuacion_tecno() {
		return puntuaciones[0];
	}
	
	/**
	 * setPuntuacion_tecno - El metodo permite cambiar la puntuacion de tecnologia
	 * @param puntuacion_tecno - Un entero con la puntuacion de tecnologia
	 */
	public void setPuntuacion_tecno(int puntuacion_tecno) {
		this.puntuaciones[0] = puntuacion_tecno;
	}
	
	/**
	 * getPuntuacion_poli - Metodo que retorna un entero con el valor de la puntuacion de politica
	 * @return Un entero con la puntuacion de politica
	 */
	public int getPuntuacion_poli() {
		return puntuaciones[1];
	}
	
	/**
	 * setPuntuacion_poli - Metodo para cambiar la puntuacion actual en politica
	 * @param puntuacion_poli - Un entero representado el nuevo puntaje en politica
	 */
	public void setPuntuacion_poli(int puntuacion_poli) {
		this.puntuaciones[1] = puntuacion_poli;
	}
	
	/**
	 * getPuntuacion_depor - Metodo que retorna un entero con el valor de la puntuacion de deportes
	 * @return Un entero con la puntuacion de deportes
	 */
	public int getPuntuacion_depor() {
		return puntuaciones[2];
	}
	/**
	 * setPuntuacion_depor - Metodo para cambiar la puntuacion actual en deportes
	 * @param puntuacion_depor - Un entero representado el nuevo puntaje en deportes
	 */
	public void setPuntuacion_depor(int puntuacion_depor) {
		this.puntuaciones[2] = puntuacion_depor;
	}


	@Override
	public void agregar(Object objeto) {
		if (objeto instanceof Palabra) {
			Palabra temp = (Palabra) objeto;
			agregarUltimo(temp);
		}
		
	}
	
	/**
	 * agregarUltimo - Un metodo que agrega en la ultima posicion de la lista un elemento de tipo {@link Palabra}
	 * @param palabra2 - La {@link Palabra} a agregar
	 */
	public void agregarUltimo(Palabra palabra2) {
		if(this.getSiguientePalabra() == null) {
			this.setSiguiente_palabra(palabra2);
		}else {
			this.getSiguientePalabra().agregarUltimo(palabra2);
		}
	}
	
	/**
	 * getTweetEntero - Un metodo que retorna toda la informacion de la Lista
	 * @param s - Un {@link String} que se va acumulando tosdos los elementos de la lista en el llamado recursivo 
	 * @return Un {@link String} con todas las palabras de lista
	 */
	public String getTweetEntero(String s) {
		if(siguiente_palabra == null) {
			return s += this.palabra;
		}else {
			return s += this.palabra + " " + siguiente_palabra.getTweetEntero(s);
		}
	}
	
}
