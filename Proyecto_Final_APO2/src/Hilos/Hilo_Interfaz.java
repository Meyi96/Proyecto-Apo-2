package Hilos;

import controller.StartController;

public class Hilo_Interfaz extends Thread{
	
	public String[] COLORS = {"#7BC0E3", "#9fd3ee", "#d5e6ee"};
	private StartController start;
	
	public Hilo_Interfaz(StartController start) {
		this.start = start;
	}
	
	@Override
	public void run() {
		int a =1;
		while(true) {
			start.cambioColores(COLORS, a);
			try {
				sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			a++;
			if(a==4) {
				a =1;
			}
		}
	}
	
	
	
}
