package MyException;

public class PosicionNoExistenteException extends Exception{
	
	public PosicionNoExistenteException() {
		super("No hay elementos en esa parte de la lista");
	}
}
