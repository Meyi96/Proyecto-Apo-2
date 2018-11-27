package model;

import java.io.IOException;
import java.net.URISyntaxException;

public interface AbrirLink {
	static final long serialVersionUID = 42L;
	
	/**
	 * goLink - Metodo para acceder a un link a partir de un String
	 * @param link - El String que representa el Hipervinculo link != null
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void goLink(String link) throws IOException, URISyntaxException;
}
