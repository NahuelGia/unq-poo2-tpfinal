package ar.edu.unq.poo2.tpfinal;


import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NoVigenteTest {

	NoVigente estadoNoVigente;
	AppSEM mockApp;
	SEM mockSEM;

	@BeforeEach
	void setUp() throws Exception {

		estadoNoVigente = new NoVigente();
		mockApp = Mockito.mock(AppSEM.class);
		mockSEM = Mockito.mock(SEM.class);
	}

	@Test
	void test1_CuandoEsEstadoNoVigenteIniciarInteractuaConLaAppConSaldoPositivo() {

		when(mockApp.tieneSaldoMinimo()).thenReturn(true);
		when(mockApp.getSistema()).thenReturn(mockSEM);

		estadoNoVigente.iniciar(mockApp, "AAA-111");

		verify(mockSEM, times(1)).estacionamientoIniciado(mockApp, "AAA-111");
		verify(mockApp, times(1)).setEstado(argThat(estacionado -> estacionado instanceof Vigente));
		verify(mockApp, times(0)).notificarSaldoInsuficiente();

	}

	@Test
	void test2_CuandoEsEstadoNoVigenteIniciarInteractuaConLaAppConSaldoInsuficiente() {

		when(mockApp.tieneSaldoMinimo()).thenReturn(false);
		when(mockApp.getSistema()).thenReturn(mockSEM);

		estadoNoVigente.iniciar(mockApp, "AAA-111");

		verify(mockSEM, times(0)).estacionamientoIniciado(mockApp, "AAA-111");
		verify(mockApp, times(0)).setEstado(argThat(estacionado -> estacionado instanceof Vigente));
		verify(mockApp, times(1)).notificarSaldoInsuficiente();

	}

	@Test
	void test3_CuandoEsEstadoNoVigenteFinalizarNoInteractuaConApp() {
		estadoNoVigente.finalizar(mockApp);

		verifyNoInteractions(mockApp);
	}

	@Test
	void test4_CuandoEsEstadoNoVigenteInicioNotificadoInteractuaConApp() {
		
		Manual mockManual = Mockito.mock(Manual.class);
		
		when(mockApp.estaEnZonaEstacionamiento()).thenReturn(true);
		when(mockApp.getModo()).thenReturn(mockManual);
	
		estadoNoVigente.inicioNotificado(mockApp);
		
		verify(mockManual,times(1)).inicioEstacionamiento(mockApp);
		
	
	}
	
	@Test
	void test5_CuandoEsEstadoVigenteFinNotificadoNoInteractuaConApp() {
		estadoNoVigente.finNotificado(mockApp);
		
		verifyNoInteractions(mockApp);
				
	}

}
