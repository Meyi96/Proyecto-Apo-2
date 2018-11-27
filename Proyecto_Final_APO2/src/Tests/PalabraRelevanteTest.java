package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.PalabraRelevante;

class PalabraRelevanteTest {
	
	private PalabraRelevante raiz;
	
	void setUpScenario1() {
		int[] a  = {0,1,0};
		raiz = new PalabraRelevante("Caso",a );
	}
	
	void setUpScenario2() {
		setUpScenario1();
		int[] a  = {0,1,0};
		PalabraRelevante n1= new PalabraRelevante("Abogado",a );
		PalabraRelevante n2= new PalabraRelevante("Juez",a );
		raiz.AgregarRelevante(n1);
		raiz.AgregarRelevante(n2);
	}
	
	void setUpScenario3() {
		setUpScenario2();
		int[] a  = {0,1,0};
		PalabraRelevante n1= new PalabraRelevante("Secretario",a );
		raiz.AgregarRelevante(n1);
	}
	

	@Test
	void testAgregar() {
		setUpScenario2();
		assertEquals(raiz.getIzq().getPalabra(), "Abogado");
		assertEquals(raiz.getDer().getPalabra(), "Juez");
	}

	@Test
	void testBuscar() {
		setUpScenario3();
		assertNull(raiz.buscarPalabra("Zapato"));
		assertNotNull(raiz.buscarPalabra("Secretario"));
		
	}
}
