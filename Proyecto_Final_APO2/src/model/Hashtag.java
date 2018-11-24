package model;

public class Hashtag extends Palabra{
	private int repeticiones;
	
	public Hashtag(String palabra, int[] puntuaciones,int repeticiones) {
		super(palabra, puntuaciones);
		this.repeticiones = repeticiones;
	}

	public boolean contiene(String s) {
		return false;
	}

	public Hashtag dar(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPuntuacion() {
		return 0;
	}

	public void setPuntuaion(int i) {
		repeticiones = i;
		
	}

}
