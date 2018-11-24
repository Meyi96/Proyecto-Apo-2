package Hilos;

import controller.NewUserController;

public class Hilo_Interfaz1  extends Thread{
	
	private boolean movimiento;
	private NewUserController new1;
	
	public Hilo_Interfaz1(NewUserController new1) {
		this.new1= new1;
		movimiento = true;
	}
	
	@Override
	public void run() {
		while(true) {
			movimiento = new1.moverCirculos(movimiento);
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
