package model;
import java.io.Serializable;
import java.net.*;

public class Link extends Palabra implements Serializable{
	
	private URI identificador;
	
	public Link(String palabra, int[] puntuaciones) {
		super(palabra, puntuaciones);
	}

	public URI getIdentificador() {
		return identificador;
	}

	public void setIdentificador(URI identificador) {
		this.identificador = identificador;
	}

}
