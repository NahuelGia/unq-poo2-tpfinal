package ar.edu.unq.poo2.tpfinal;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;

public class AppSEMTest {
	
	private AppSEM appTest;
	
	@BeforeEach
	public void setUp() {
		int nroTel = 111;
		SEM semMock = mock(SEM.class);
		
		appTest = new AppSEM(nroTel, semMock);
	}
	
	@Test
	public void testConstructorAPPSEM() {
		
		int nroTel = 123;
		SEM semMock = mock(SEM.class);
		
		AppSEM app = new AppSEM(nroTel, semMock);
		
		assertEquals(nroTel, app.getNroTelefono());
		assertEquals(0.0, app.getSaldo());
		assertTrue(app.getEstado() instanceof NoVigente);
		assertTrue(app.getModo() instanceof Manual); 
		assertEquals(semMock, app.getSistema());
		verify(semMock,times(1)).registrarUsuario(app);
	}
	
	@Test
	public void unaAppSEMPuedeAumentarSuSaldoActual() {
		
		assertEquals(0.0, appTest.getSaldo());
		
		appTest.aumentarSaldo(100.0);
		
		assertEquals(100.0, appTest.getSaldo());
	}
	
	@Test
	public void unaAppSEMPuedeIniciarUnEstacionamiento() {
		
		NoVigente estadoMock = mock(NoVigente.class);
		
		// Para verificar que el estado recibe correctamente el mensaje asigno al mock como nuevo
		// estado de la app.
		appTest.setEstado(estadoMock); 
		
		String patente = "X01" ;
		appTest.iniciarEstacionamiento(patente);
		
		verify(estadoMock).iniciar(appTest, patente);
		
	}
	
	@Test
	public void unaAppSEMPuedeFinalizarUnEstacionamiento() {
		
		NoVigente estadoMock = mock(NoVigente.class);
		
		appTest.setEstado(estadoMock); 
		
		appTest.finalizarEstacionamiento();
		
		verify(estadoMock).finalizar(appTest);
	}
	
	@Test
	public void unaAppSEMEsNotificadaDeUnPosibleInicioDeEstacionamiento() {
		NoVigente estadoMock = mock(NoVigente.class);
		
		appTest.setEstado(estadoMock); 
		
		appTest.posibleInicioEstacionamiento();
		
		verify(estadoMock).inicioNotificado(appTest);
	}
	
	@Test
	public void unaAppSEMEsNotificadaDeUnPosibleFinDeEstacionamiento() {
		NoVigente estadoMock = mock(NoVigente.class);
		
		appTest.setEstado(estadoMock); 
		
		appTest.posibleFinEstacionamiento();
		
	    verify(estadoMock).finNotificado(appTest);
	}
	
	@Test
	public void unaAppSEMPuedeActivarLasNotificacionesDeEstacionamiento() {
		Manual modoMock = mock(Manual.class);
		
		appTest.setModo(modoMock);
		
		appTest.activarNotificaciones();
		
		verify(modoMock).setNotifActiva(true);
	}
	
	@Test
	public void unaAppSEMPuedeDesactivarLasNotificacionesDeEstacionamiento() {
		Manual modoMock = mock(Manual.class);
		
		appTest.setModo(modoMock);
		
		appTest.desactivarNotificaciones();
		
		verify(modoMock).setNotifActiva(false);
	}
	
	@Test
	public void unaAppSEMPuedeNotificarUnPosibleInicioDeEstacionamiento() {
		// TODO
	}
	
	@Test
	public void unaAppSEMPuedeNotificarUnPosibleFinDeEstacionamiento() {
		// TODO
	}
	
	@Test
	public void unaAppSEMPuedeNotificarQueTieneSaldoInsuficiente() {
		// TODO
	}
	
	@Test
	public void unaAppSEMSabeQueNoTieneElSaldoMinimoParaRealizarUnEstacionamiento() {
		SEM sistemaMock = mock(SEM.class);
		
		appTest.setSistema(sistemaMock);
		
		when(sistemaMock.getPrecioPorHora()).thenReturn(40.0);
		
		boolean resultado = appTest.tieneSaldoMinimo() ;
		
		assertFalse(resultado);
	}
	
	@Test
	public void unaAppSEMSabeQueTieneElSaldoMinimoParaRealizarUnEstacionamiento() {
		SEM sistemaMock = mock(SEM.class);
		
		appTest.aumentarSaldo(40.0);
		appTest.setSistema(sistemaMock);
		
		when(sistemaMock.getPrecioPorHora()).thenReturn(40.0);
		
		boolean resultado = appTest.tieneSaldoMinimo() ;
		
		assertTrue(resultado);
	}
	
	@Test
	public void unaAppSEMQueSeEncuentraWalkingAlCambiarADrivingNotificaElPosibleFin() {
		appTest.setWalking(true);
		
		Vigente estadoMock = mock(Vigente.class);
		
		appTest.setEstado(estadoMock);
		
		appTest.driving();
		
		verify(estadoMock).finNotificado(appTest);
		
	}
	
	@Test
	public void unaAppSEMQueNoSeEncuentraWalkingAlRecibirDrivingNoHaceNada() {
		appTest.setWalking(false);
		
		Vigente estadoMock = mock(Vigente.class);
		
		appTest.setEstado(estadoMock);
		
		appTest.driving();
		
		verifyNoInteractions(estadoMock);
		
	}
	
	@Test
	public void unaAppSEMQueNoSeEncuentraWalkingAlCambiarAWalkingNotificaElPosibleInicio() {
		appTest.setWalking(false);
		
		Vigente estadoMock = mock(Vigente.class);
		
	    appTest.setEstado(estadoMock);
		
		appTest.walking();
		
		verify(estadoMock).inicioNotificado(appTest);
	}
	
	@Test
	public void unaAppSEMQueSeEncuentraWalkingAlRecibirWalkingNoHaceNada() {
		appTest.setWalking(true);
		
		Vigente estadoMock = mock(Vigente.class);
		
		appTest.setEstado(estadoMock);
		
		appTest.walking();
		
		verifyNoInteractions(estadoMock);	
	}
	
	  @Test
	    public void testNotificarEstacionamientoExitoso() {

	        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	        PrintStream originalOut = System.out;
	        System.setOut(new PrintStream(outContent));

	        LocalTime horaInicio = LocalTime.of(8, 0);
	        LocalTime horaFin = LocalTime.of(18, 0);
	        
	        appTest.notificarEstacionamientoExitoso(horaInicio, horaFin);

	        System.setOut(originalOut);

	        String expectedOutput = "El estacionamiento se ha iniciado con éxito. "
	                              + "La hora de inicio es:" + horaInicio.toString()
	                              + "La hora máxima de fin posible:" + horaFin.toString();
	        assertEquals(expectedOutput, outContent.toString().trim());
	    }
	
	
}
