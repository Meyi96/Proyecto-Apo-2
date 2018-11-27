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
	 * agregarUltimoL - M�todo que agrega un link en el �ltimo lugar de la lista enlazada.
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
	 * Link - m�todo constructor de la clase. Posteriormente el link lo usa para instanciar un objeto URI.
	 * @param palabra - Link en cadena de texto como tal de la clase.
	 * @param puntuaciones - arreglo de puntuaciones que puede tener el link.
	 * @throws URISyntaxException 
	 */
	public Link(String palabra, int[] puntuaciones) throws URISyntaxException {
		super(palabra, puntuaciones);
		identificador = new URI(palabra);
	}
	
	/**
	 * getSiguienteLink - M�todo que obtiene el siguiente link al que se est� usando en el momento de llamar al m�todo.  
	 * @return Link - el siguiente Link en la lista enlazada.
	 */
	public Link getSiguienteLink() {
		return siguienteLink;
	}

	/**
	 * setSiguienteLink - M�todo que modifica el valor del siguiente link al que se est� usando en el momento de llamar al m�todo.
	 * @param siguienteLink
	 */
	public void setSiguienteLink(Link siguienteLink) {
		this.siguienteLink = siguienteLink;
	}
	
	/**
	 * getIdentificador - M�todo que obtiene el valor del identificador.
	 * @return URI - retorna el objeto URI.
	 */
	public URI getIdentificador() {
		return identificador;
	}

	/**
	 * setIdentificador - M�todo que modifica el valor del identificador.
	 * @param identificador - Nuevo URI que se desea guardar.
	 */
	public void setIdentificador(URI identificador) {
		this.identificador = identificador;
	}
	
	/**
	 * darLinks - M�todo que recorre toda la lista enlazada guardando todas las instancias de la clase Link encontradas.
	 * @param links - ArrayList de tipo objeto en el que se guardar�n todas las instancias.
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
	 * compareTo - M�todo que compara dos objetos Link seg�n sus valores lexicogr�gricos de sus palabras o links en cadena de texto en s�.
	 * @param nuevo - El link con el cual se quiere comparar la instancia actual.
	 * @return - Valor negativo, positivo o 0 dependiendo de la diferencia 'actual-nuevo' lexicogr�ficamente. 
	 */
	public int compareTo(Link nuevo) {
		return super.palabra.compareTo(nuevo.palabra);
	}

}
