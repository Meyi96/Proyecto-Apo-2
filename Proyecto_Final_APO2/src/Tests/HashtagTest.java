package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Hashtag;

class HashtagTest {

	private Hashtag h1;
	private Hashtag h2;
	private Hashtag h3;
	
	void setUpScenario1() {
		h1 = new Hashtag("#Java", null, 2);
		h2 = new Hashtag("#JavaFx", null, 1);
		h3 = new Hashtag("#Hola", null, 1);
	}

	@Test
	void testAgregar() {
		setUpScenario1();
		h1.agregar(h2);
		assertNotNull(h1.getSiguienteHashtag());
	}
	
	@Test
	void testAgregarUltimo() {
		setUpScenario1();
		h1.agregar(h2);
		h1.agregar(h3);
		assertEquals(h3, h1.getSiguienteHashtag().getSiguienteHashtag());
	}

	@Test
	void testContiene() {
		setUpScenario1();
		h1.agregar(h2);
		h1.agregar(h3);
		assertTrue(h1.contiene("#Hola"));
		assertFalse(h1.contiene("Prueba"));
	}
	
	@Test
	void testDar() {
		setUpScenario1();
		h1.agregar(h2);
		h1.agregar(h3);
		assertEquals(h3, h1.dar("#Hola"));
	}
	
	@Test
	void testDarHashtags() {
		setUpScenario1();
		h1.agregar(h2);
		h1.agregar(h3);
		ArrayList<Object> objeto = new ArrayList<>();
		h1.darHashtags(objeto);
		assertEquals(h1, objeto.get(0));
		assertEquals(h2, objeto.get(1));
		assertEquals(h3, objeto.get(2));
	}
	
	@Test
	void testCompareTo() {
		setUpScenario1();
		assertTrue(h1.compareTo(h2) > 0);
		assertTrue(h2.compareTo(h3) < 0);
	}
	
}
