package ar.edu.unq.poo2.tpfinal;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

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
	
	
	
}
