package model;
import java.net.*;

public class Link extends Palabra{
	
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
