package ar.edu.unq.poo2.tpfinal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
