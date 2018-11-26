package model;

import java.io.IOException;
import java.net.URISyntaxException;

public interface AbrirLink {
	static final long serialVersionUID = 42L;
	
	public void goLink(String link) throws IOException, URISyntaxException;
}
