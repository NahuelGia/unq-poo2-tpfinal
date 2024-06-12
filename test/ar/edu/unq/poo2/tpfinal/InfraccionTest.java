package ar.edu.unq.poo2.tpfinal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

public class InfraccionTest {
	private LocalDateTime fechayHora;
	private AppInspector appInspector;
	private Infraccion infraccion;
	private ZonaEstacionamiento zona;
	
	@BeforeEach
	public void setUp() {
		this.fechayHora=  LocalDateTime.now();
		this.appInspector = mock(AppInspector.class);
		this.zona= mock(ZonaEstacionamiento.class);
		this.infraccion = new Infraccion(this.appInspector,this.fechayHora,"0001001",this.zona);
		
}
	@Test
	public void test000UnaInfraccionSabeResponderCualEsSuFechaYHora() {
		assertEquals(this.infraccion.getFechayHora(),this.fechayHora);
	}
	@Test
	public void test005UnainfraccionSabeResponderCualEsLaPatenteRegistrada() {
		assertEquals(this.infraccion.getPatente(),"0001001");
	}
	@Test
	public void test010UnainfraccionSabeResponderCualEsELEstacionamientoEnElCualSeRegistra() {
		assertEquals(this.infraccion.getZona(),this.zona);
	}
	@Test
	public void test015UnainfraccionSabeResponderQuienRealizoLaInfraccion() {
		assertEquals(this.infraccion.getAppInspector(),this.appInspector);
	}
}
