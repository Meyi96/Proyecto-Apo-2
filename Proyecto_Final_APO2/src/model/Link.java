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
	
	public void agregarUltimoL(Link temp) {
		if(this.getSiguienteLink()== null) {
			this.setSiguienteLink(temp);
		}else {
			this.getSiguienteLink().agregarUltimoL(temp);
		}
		
	}

	public Link(String palabra, int[] puntuaciones) throws URISyntaxException {
		super(palabra, puntuaciones);
		identificador = new URI(palabra);
	}
	
	public Link getSiguienteLink() {
		return siguienteLink;
	}

	public void setSiguienteLink(Link siguienteLink) {
		this.siguienteLink = siguienteLink;
	}

	public URI getIdentificador() {
		return identificador;
	}

	public void setIdentificador(URI identificador) {
		this.identificador = identificador;
	}
	
	public void darLinks(ArrayList<Object> links) {
		if(siguienteLink == null)
			links.add(this);
		else {
			links.add(this);
			siguienteLink.darLinks(links);
		}
	}
	
	public int compareTo(Link nuevo) {
		return super.palabra.compareTo(nuevo.palabra);
	}

}
