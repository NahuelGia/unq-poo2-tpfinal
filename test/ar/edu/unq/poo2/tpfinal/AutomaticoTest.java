package ar.edu.unq.poo2.tpfinal;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutomaticoTest {
	
private Automatico automaticoTest;
	
	@BeforeEach
	public void Setup() {
		String patente = "B03";
		automaticoTest = new Automatico(patente);
	}
	
	@Test
	public void constructorAutomaticoTest() {
		String patente = "A03";
		automaticoTest = new Automatico(patente);

		assertEquals(patente, automaticoTest.getPatente());
	}
	
	@Test
	public void unModoAutomaticoPuedeIniciarUnEstacionamiento() {
		AppSEM appMock = mock(AppSEM.class);
		
		automaticoTest.inicioEstacionamiento(appMock);
		
		verify(appMock).iniciarEstacionamiento(automaticoTest.getPatente());
	}
	
	@Test
	public void unModoAutomaticoPuedeFinalizarUnEstacionamiento() {
		AppSEM appMock = mock(AppSEM.class);
		
		automaticoTest.finEstacionamiento(appMock);
		
		verify(appMock).finalizarEstacionamiento();
	}
	
	 @Test
	    public void unAutomaticoNoHaceNadaCuandoLeDicenQueActiveODesactiveLaNotificacion() {
	        assertDoesNotThrow(() -> automaticoTest.setNotifActiva(true));
	        assertDoesNotThrow(() -> automaticoTest.setNotifActiva(false));
	    }
	
}
