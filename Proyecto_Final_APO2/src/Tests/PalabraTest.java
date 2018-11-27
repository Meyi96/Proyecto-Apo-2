package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.Palabra;
import model.PalabraRelevante;

public class PalabraTest {
	
	private Palabra primeraPalabra;
	
	void setUpScenario1() {
		int[] a  = {10,1,20};
		primeraPalabra = new Palabra("Seguridad", a);
	}
	
	void setUpScenario2() {
		setUpScenario1();
		int[] a  = {14,12,12};
		Palabra tem = new Palabra("es", a);
		primeraPalabra.agregarUltimo(tem);
	}
	
	void setUpScenario3() {
		setUpScenario2();
		int[] a  = {10,1,23};
		Palabra tem = new Palabra("mejor", a);
		Palabra tem1 = new Palabra("que", a);
		Palabra tem2 = new Palabra("todo", a);
		primeraPalabra.agregarUltimo(tem);
		primeraPalabra.agregarUltimo(tem1);
		primeraPalabra.agregarUltimo(tem2);
	}
	
	@Test
	void testAgregarUltimo() {
		setUpScenario3();
		Palabra prueba = primeraPalabra;
		while(prueba != null) {
			if(prueba.getSiguientePalabra() ==null)
				assertEquals(prueba.getPalabra(), "todo");
			prueba = prueba.getSiguientePalabra();
		}
	}
	
	@Test
	void testTweetEntero() {
		setUpScenario3();
		String dato = "";
		dato = primeraPalabra.getTweetEntero(dato);
		assertTrue(dato.equals("Seguridad es mejor que todo"));
	}
	
	
	
	
	
}
