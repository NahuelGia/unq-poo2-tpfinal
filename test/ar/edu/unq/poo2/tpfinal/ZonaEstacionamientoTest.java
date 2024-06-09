package ar.edu.unq.poo2.tpfinal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ZonaEstacionamientoTest {
	private ZonaEstacionamiento zona;
	private PuntoDeVenta punto1;
	
	
	
	@BeforeEach
	public void setUp() {
		this.zona= new ZonaEstacionamiento();
		this.punto1 = mock(PuntoDeVenta.class);
		
		
	}
	@Test
	public void test000UnaZonaDeEstacionamientoInicialmenteNoTienePuntosDeVenta() {
		assertEquals(0,this.zona.getCantidadDePv());
	}
	@Test
	public void test005UnaZonaDeEstacionamientoPuedeAñadirPuntosDeVenta() {
		this.zona.registrarPuntoDeVenta(this.punto1);
		assertTrue(this.zona.getPvs().contains(this.punto1));
	}
	
}
