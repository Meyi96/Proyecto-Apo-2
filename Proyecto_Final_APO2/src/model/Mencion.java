package model;

import java.io.Serializable;

/**
 * Clase {@link Mencion} / Clase para modelar una palabra tipo Mencion
 * @author Nelson Quiñones Virgen - Fabio Andres Mejía - Marco Antonio Perez
 * Version 1.0
 * 27/Noviembre/2018
 */
public class Mencion extends Palabra implements Serializable,Agregar{
	private String nombreMencionado;
	private Usuario usuarioMencionado;
	/**
	 * Mencion - Metodo constructor de la clase {@link Mencion}
	 * @param palabra - Un {@link String} con la informacion de la palabra	palabra != null palabra != ""
	 * @param puntuaciones - Una arreglo de int con la puntuacion en cada categoria puntuaciones != null
	 * @param nombreMencionado - Un String identificando el usuario mencionado	nombreMencionado != null
	 * pos : palabra se inicializa
	 * pos : puntucion se inicializa
	 */
	public Mencion(String palabra, int[] puntuaciones, String nombreMencionado) {
		super(palabra, puntuaciones);
		this.nombreMencionado = nombreMencionado;
	}
	/**
	 * getNombreMencionado - Metodo retorna un {@link String} con el nombre del usuario mencionado
	 * @return Un {@link String} con el nombre del usuario mencionado
	 */
	public String getNombreMencionado() {
		return nombreMencionado;
	}
	
	/**
	 * getNombreMencionado - Metodo modifica el {@link String} con el nombre del usuario mencionado
	 * @param nombreMencionado Un {@link String} con el nuevo nombre del usuario mencionado
	 */
	public void setNombreMencionado(String nombreMencionado) {
		nombreMencionado = nombreMencionado;
	}
	
	/**
	 * getUsuarioMencionado - Metodo retorna el {@link Usuario} mencionado
	 * @return Un {@link Usuario} con el nombre del usuario mencionado
	 */
	public Usuario getUsuarioMencionado() {
		return usuarioMencionado;
	}
	
	/**
	 * setUsuarioMencionado - Metodo modificar el {@link Usuario} mencionado
	 * @param usuarioMencionado - El {@link Usuario} nuevo usuario mencionado
	 */
	public void setUsuarioMencionado(Usuario usuarioMencionado) {
		this.usuarioMencionado = usuarioMencionado;
	}
	
	
}
