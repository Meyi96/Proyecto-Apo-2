package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Palabra;
import model.Tweet;

class TweetTest {

	private Tweet t1;
	private Tweet t2;
	private Tweet t3;
	private Palabra palabra1;
	private Palabra palabra2;
	
	public void setUpScenario1() {
		int[] puntuaciones = {1, 1, 0};
		palabra1 = new Palabra("Soy", puntuaciones);
		puntuaciones[0] = 0;
		puntuaciones[1] = 0;
		palabra2 = new Palabra("nuevo", puntuaciones);
		palabra1.agregar(palabra2);
		t1 = new Tweet("Nov 26", palabra1, "10", "10", puntuaciones);
		t2 = new Tweet("Nov 25", null, "9", "9", puntuaciones);
		t3= new Tweet("Nov 24", null, "11", "10", puntuaciones);
	}
	
	@Test
	void testAgregar() {
		setUpScenario1();
		t1.agregar(t2);
		assertNotNull(t1.getSiguiente());
	}

	@Test
	void testAgregarUltimo() {
		setUpScenario1();
		t1.agregar(t2);
		t1.agregar(t3);
		assertEquals(t3, t1.getSiguiente().getSiguiente());
	}
	
	@Test
	void testCalcularPuntajes() {
		setUpScenario1();
		t1.calcularPuntajes(t1.primera_Palabra());
		int[] obtenido = t1.getPuntajes();
		assertEquals(t1.primera_Palabra().getPuntuacion_depor(), obtenido[0]);
		assertEquals(t1.primera_Palabra().getPuntuacion_poli(), obtenido[1]);
		assertEquals(t1.primera_Palabra().getPuntuacion_tecno(), obtenido[2]);
	}
	
	@Test
	void testCalcularPuntajeTotal() {
		setUpScenario1();
		String esperado = Integer.toString(palabra1.getPuntuacion_depor() + palabra1.getPuntuacion_poli() + palabra1.getPuntuacion_tecno());
		assertEquals(esperado, t1.calcularPuntajeTotal());
	}
	
	@Test
	void testObtenerTweets() {
		setUpScenario1();
		t1.agregar(t2);
		t1.agregar(t3);
		ArrayList<Object> objeto = new ArrayList<>();
		t1.obtenerTweets(objeto);
		assertEquals(t1, (Tweet)objeto.get(0));
		assertEquals(t2, (Tweet)objeto.get(1));
		assertEquals(t3, (Tweet)objeto.get(2));
	}
	
	@Test
	void testObtenerTweetEntero() {
		setUpScenario1();
		assertEquals("Soy nuevo", t1.getTweetEntero(""));
	}
	
}
