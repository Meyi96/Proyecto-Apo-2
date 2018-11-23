package model;

public class Palabra {
	String palabra;
	Palabra siguiente_palabra;
	int puntuacion_tecno;
	int puntuacion_poli;
	int puntuacion_depor;
	
	public Palabra(String palabra) {
		this.palabra = palabra;
	}

	public Palabra getSiguiente_palabra() {
		return siguiente_palabra;
	}

	public void setSiguiente_palabra(Palabra siguiente_palabra) {
		this.siguiente_palabra = siguiente_palabra;
	}

	public int getPuntuacion_tecno() {
		return puntuacion_tecno;
	}

	public void setPuntuacion_tecno(int puntuacion_tecno) {
		this.puntuacion_tecno = puntuacion_tecno;
	}

	public int getPuntuacion_poli() {
		return puntuacion_poli;
	}

	public void setPuntuacion_poli(int puntuacion_poli) {
		this.puntuacion_poli = puntuacion_poli;
	}

	public int getPuntuacion_depor() {
		return puntuacion_depor;
	}

	public void setPuntuacion_depor(int puntuacion_depor) {
		this.puntuacion_depor = puntuacion_depor;
	}

	public void agregarUltimo(Palabra palabra2) {
		if(this.getSiguiente_palabra() == null) {
			this.setSiguiente_palabra(palabra2);
		}else {
			this.getSiguiente_palabra().agregarUltimo(palabra2);
		}
	}
	
	
}
