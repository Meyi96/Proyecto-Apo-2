package model;

public class Tweet {
	Tweet siguiente;
	String Fecha;
	Palabra parrafo;
	int likes;
	int reTweets;
	
	public Tweet(String Fecha,Palabra parrafo,int likes,int reTweets) {
		this.Fecha = Fecha;
		this.parrafo = parrafo;
		this.likes = likes;
		this.reTweets = reTweets;
	}

	public Tweet getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Tweet siguiente) {
		this.siguiente = siguiente;
	}

	public Palabra getParrafo() {
		return parrafo;
	}

	public void setParrafo(Palabra parrafo) {
		this.parrafo = parrafo;
	}

	public void agregarUltimo(Tweet temp) {
		if(this.getSiguiente() == null) {
			this.setSiguiente(temp); ;
		}else {
			this.getSiguiente().agregarUltimo(temp);
		}
		
	}
	
	
}
