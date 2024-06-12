package ar.edu.unq.poo2.tpfinal;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ManualTest {
	
	private Manual manualTest;
	
	@BeforeEach
	public void Setup() {
		manualTest = new Manual();
	}
	
	@Test
	public void testConstructorManual() {
	    Manual manual = new Manual();
		
		assertFalse(manual.isNotifActiva());
		
	}
	
	@Test
	public void unModoManualNotificaDeUnPosibleInicioDeEstacionamientoSoloSiLasNotificacionesEstanActivas() {
		
		AppSEM appMock = mock(AppSEM.class) ;
		
		manualTest.setNotifActiva(true);
		
		manualTest.inicioEstacionamiento(appMock);
		
		assertTrue(manualTest.isNotifActiva());
		verify(appMock).notificarPosibleInicio();
		
	}
	
	@Test
	public void unModoManualConLaNotificacionDesactivadaSabeQueNoDebeNotificarDeUnPosibleInicioDeEstacionamiento() {
		
		AppSEM appMock = mock(AppSEM.class) ;
		
		manualTest.inicioEstacionamiento(appMock);
		
		assertFalse(manualTest.isNotifActiva());
		verifyNoInteractions(appMock);
		
	}
	
	@Test
	public void unModoManualConLaNotificacionActivaSabeQueDebeNotificarDeUnPosibleFinDeEstacionamiento() {
		AppSEM appMock = mock(AppSEM.class) ;
		manualTest.setNotifActiva(true);
		
		manualTest.finEstacionamiento(appMock);
		
		assertTrue(manualTest.isNotifActiva());
		verify(appMock).notificarPosibleFin();
	}
	
	@Test
	public void unModoManualConLaNotificacionDesactivadaSabeQueNoDebeNotificarDeUnPosibleFinDeEstacionamiento() {
		AppSEM appMock = mock(AppSEM.class) ;
		
		manualTest.finEstacionamiento(appMock);
		
		assertFalse(manualTest.isNotifActiva());
		verifyNoInteractions(appMock);
	}
	
	
	
	
}
