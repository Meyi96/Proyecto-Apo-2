package model;

import java.io.IOException;
import java.net.URISyntaxException;

public interface AbrirLink {
	static final long serialVersionUID = 42L;
	
	/**
	 * goLink - Metodo para acceder a un link a partir de un String
	 * @param link - El String que representa el Hipervinculo link != null
	 * @throws IOException Se arroja si no se encuentra el archivo o hay errores al escribir o leer el archivo
	 * @throws URISyntaxException  Se arroja si hay problemas con el link
	 */
	public void goLink(String link) throws IOException, URISyntaxException;
}
