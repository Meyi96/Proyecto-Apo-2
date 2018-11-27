package MyException;

public class TextoVacioException extends Exception {

	public TextoVacioException() {
		super("No ingresó texto alguno a la aplicación.");
	}
	
}
