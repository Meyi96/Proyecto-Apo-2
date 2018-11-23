package model;

public class Tweet {
	Tweet siguiente;
	String Fecha;
	Palabra primera_Palabra;
	String likes;
	String reTweets;
	
	public Tweet(String Fecha,Palabra parrafo,String likes2,String reTweets2) {
		this.Fecha = Fecha;
		this.primera_Palabra = parrafo;
		this.likes = likes2;
		this.reTweets = reTweets2;
	}

	public Tweet getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Tweet siguiente) {
		this.siguiente = siguiente;
	}

	public Palabra primera_Palabra() {
		return primera_Palabra;
	}

	public void setParrafo(Palabra parrafo) {
		this.primera_Palabra = parrafo;
	}

	public void agregarUltimo(Tweet temp) {
		if(this.getSiguiente() == null) {
			this.setSiguiente(temp); ;
		}else {
			this.getSiguiente().agregarUltimo(temp);
		}
		
	}
	
	
}
