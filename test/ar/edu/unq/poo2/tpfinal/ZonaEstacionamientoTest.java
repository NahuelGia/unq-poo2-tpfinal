package ar.edu.unq.poo2.tpfinal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ZonaEstacionamientoTest {
	
	private ZonaEstacionamiento zona;
	private PuntoDeVenta punto1;
	private PuntoDeVenta punto2;
	
	
	
	@BeforeEach
	public void setUp() {
		String nombre = "test";
		this.zona= new ZonaEstacionamiento(nombre);
		this.punto1 = mock(PuntoDeVenta.class);
		this.punto2 = mock(PuntoDeVenta.class);
	}
	
	@Test
	public void test0000ConstructorZonaEstacionamiento() {
		String nombre = "test";
		ZonaEstacionamiento zonaTest= new ZonaEstacionamiento(nombre);
		assertEquals(nombre, zonaTest.getNombre());
		assertEquals(0,this.zona.getCantidadDePv());
	}
	
	@Test
	public void test005UnaZonaDeEstacionamientoPuedeAñadirPuntosDeVenta() {
		this.zona.registrarPuntoDeVenta(this.punto1);
		assertTrue(this.zona.getPvs().contains(this.punto1));
	}
	@Test
	public void test010UnaZonaDeEstacionamientoSabeResponderQuienesSonSusPuntosDeVenta() {
		this.zona.registrarPuntoDeVenta(this.punto1);
		this.zona.registrarPuntoDeVenta(this.punto2);
		assertTrue(this.zona.getPvs().contains(this.punto1));
		assertTrue(this.zona.getPvs().contains(this.punto2));
	}
	@Test
	public void test015UnaZonaDeEstacionamientoPuedeEliminarPuntosDeVenta() {
		this.zona.registrarPuntoDeVenta(this.punto2);
		this.zona.removerPuntoDeVenta(this.punto2);
		assertFalse(this.zona.getPvs().contains(this.punto2));
	}
	
}
