package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Link;

class LinkTest {

	private Link l1;
	private Link l2;
	
	void setUpScenario1() {
		try {
			l1 = new Link("https://www.facebook.com/", null);
			l2 = new Link("https://www.youtube.com/", null);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testAgregar() {
		setUpScenario1();
		l1.agregar(l2);
		assertNotNull(l1.getSiguienteLink());
	}

	@Test 
	void testAgregarUltimo() {
		setUpScenario1();
		try {
			Link temp = new Link("asd", null);
			l1.agregar(l2);
			l1.agregar(temp);
			assertEquals(temp, l1.getSiguienteLink().getSiguienteLink());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testDarLinks() {
		setUpScenario1();
		l1.agregar(l2);
		ArrayList<Object> objeto = new ArrayList<>();
		l1.darLinks(objeto);
		assertEquals(l1, objeto.get(0));
		assertEquals(l2, objeto.get(1));
	}
	
	@Test
	void testCompareTo() {
		setUpScenario1();
		assertTrue(l1.compareTo(l2) < 0);
		assertTrue(l2.compareTo(l1) > 0);
	}
	
}
