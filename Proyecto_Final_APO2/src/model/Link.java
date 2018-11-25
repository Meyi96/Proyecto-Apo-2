package model;
import java.io.Serializable;
import java.net.*;

public class Link extends Palabra implements Serializable{
	static final long serialVersionUID = 42L;
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
