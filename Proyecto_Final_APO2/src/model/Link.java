package model;
import java.io.Serializable;
import java.net.*;
import java.util.ArrayList;

public class Link extends Palabra implements Serializable,Agregar{
	static final long serialVersionUID = 42L;
	private URI identificador;
	private Link siguienteLink;

	@Override
	public void agregar(Object objeto) {
		if (objeto instanceof Link) {
			Link temp = (Link) objeto;
			agregarUltimoL(temp);
		}
		
	}
	
	/**
	 * agregarUltimoL - Método que agrega un link en el último lugar de la lista enlazada.
	 * @param temp - El link por agregar.
	 */
	public void agregarUltimoL(Link temp) {
		if(this.getSiguienteLink()== null) {
			this.setSiguienteLink(temp);
		}else {
			this.getSiguienteLink().agregarUltimoL(temp);
		}
		
	}

	/**
	 * Link - método constructor de la clase. Posteriormente el link lo usa para instanciar un objeto URI.
	 * @param palabra - Link en cadena de texto como tal de la clase.
	 * @param puntuaciones - arreglo de puntuaciones que puede tener el link.
	 * @throws URISyntaxException 
	 */
	public Link(String palabra, int[] puntuaciones) throws URISyntaxException {
		super(palabra, puntuaciones);
		identificador = new URI(palabra);
	}
	
	/**
	 * getSiguienteLink - Método que obtiene el siguiente link al que se está usando en el momento de llamar al método.  
	 * @return Link - el siguiente Link en la lista enlazada.
	 */
	public Link getSiguienteLink() {
		return siguienteLink;
	}

	/**
	 * setSiguienteLink - Método que modifica el valor del siguiente link al que se está usando en el momento de llamar al método.
	 * @param siguienteLink
	 */
	public void setSiguienteLink(Link siguienteLink) {
		this.siguienteLink = siguienteLink;
	}
	
	/**
	 * getIdentificador - Método que obtiene el valor del identificador.
	 * @return URI - retorna el objeto URI.
	 */
	public URI getIdentificador() {
		return identificador;
	}

	/**
	 * setIdentificador - Método que modifica el valor del identificador.
	 * @param identificador - Nuevo URI que se desea guardar.
	 */
	public void setIdentificador(URI identificador) {
		this.identificador = identificador;
	}
	
	/**
	 * darLinks - Método que recorre toda la lista enlazada guardando todas las instancias de la clase Link encontradas.
	 * @param links - ArrayList de tipo objeto en el que se guardarán todas las instancias.
	 */
	public void darLinks(ArrayList<Object> links) {
		if(siguienteLink == null)
			links.add(this);
		else {
			links.add(this);
			siguienteLink.darLinks(links);
		}
	}
	
	/**
	 * compareTo - Método que compara dos objetos Link según sus valores lexicográgricos de sus palabras o links en cadena de texto en sí.
	 * @param nuevo - El link con el cual se quiere comparar la instancia actual.
	 * @return - Valor negativo, positivo o 0 dependiendo de la diferencia 'actual-nuevo' lexicográficamente. 
	 */
	public int compareTo(Link nuevo) {
		return super.palabra.compareTo(nuevo.palabra);
	}

}
