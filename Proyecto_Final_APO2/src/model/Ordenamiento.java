package model;

import java.util.ArrayList;

public interface Ordenamiento {
	static final long serialVersionUID = 42L;
	
	/**
	 * ordenamiento - Metodo para ordenar un Arraylist de objetos
	 * @param objeto - el ArrayList de Objects objeto que se va organizar objeto != null
	 * @param tipo - Un caracter representando el tipo de objeto a organizar
	 */
	public void ordenamiento(ArrayList<Object> objeto, char tipo);
}
