package ejemplo;

public class BurbujaAmor {

	private int arreglo[];
	
	
	public BurbujaAmor() {
		arreglo = new int[10];
		for (int i = 0; i < arreglo.length; i++) {
			arreglo[i] = (int)(Math.random()*10)+1;
			System.out.println(arreglo[i]);
		}
	}
	
	public void ordenar() {
		for (int i = 0; i < arreglo.length; i++) {
			for (int j = 0; j+1< arreglo.length; j++) {
				if(arreglo[j] > arreglo[j+1]) {
					int axuciliar = arreglo[j];
					arreglo[j] = arreglo[j+1];
					arreglo[j+1] = axuciliar;
				}
			}
		}
		System.out.println("============================ ordenado ====================");
		for (int i = 0; i < arreglo.length; i++) {
			System.out.println(arreglo[i]);
		}
	}
	
	public static void main(String []arg) {
		Salon salon = new Salon();
//		salon.agregarEstudiante2(new Estudiante("a","001"));
//		salon.agregarEstudiante2(new Estudiante("b","002"));
//		salon.agregarEstudiante2(new Estudiante("c","003"));
//		salon.agregarEstudiante2(new Estudiante("d","004"));
//		salon.agregarEstudiante2(new Estudiante("e","005"));
		for (int i = 0; i < 15; i++) {
			salon.agregarEstudiante2(new Estudiante());
		}
		
		salon.mostrarCod();
//		salon.borrar("004"); 
//		salon.borrar("001");
//		salon.borrar("001");
//		salon.borrar("004");
//		salon.borrar("002");
		salon.ordenar();
		salon.mostrarCod();
	}
	
}
