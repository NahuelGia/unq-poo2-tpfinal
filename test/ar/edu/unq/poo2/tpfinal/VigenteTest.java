package ar.edu.unq.poo2.tpfinal;

import static org.mockito.ArgumentMatchers.argThat;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class VigenteTest {

	Vigente estadoVigente;
	AppSEM mockApp;

	@BeforeEach
	void setUp() throws Exception {

		estadoVigente = new Vigente();
		mockApp = Mockito.mock(AppSEM.class);

	}

	@Test
	void test1_CuandoEsEstadoVigenteIniciarNoInteractuaConApp() {

		estadoVigente.iniciar(mockApp, "AAA-111");

		verifyNoInteractions(mockApp);

	}

	@Test
	void test2_CuandoEsEstadoVigenteFinalizarInteractuaConSEMYApp() {

		SEM mockSem = Mockito.mock(SEM.class);

		when(mockApp.getSistema()).thenReturn(mockSem);

		estadoVigente.finalizar(mockApp);

		verify(mockSem, times(1)).finalizarEstacionamiento(mockApp);
		verify(mockApp, times(1)).setEstado(argThat(estado -> estado instanceof NoVigente));

	}

	@Test
	void test3_CuandoEsEstadoVigenteInicioNotificadoNoIntercatuaConApp() {

		estadoVigente.inicioNotificado(mockApp);

		verifyNoInteractions(mockApp);
	}

	@Test
	void test4_CuandoEsEstadoVigenteFinNotificadoInteractuaConEstrategiaDeLaApp() {

		Manual mockManual = Mockito.mock(Manual.class);

		when(mockApp.estaEnZonaEstacionamiento()).thenReturn(true);
		when(mockApp.getModo()).thenReturn(mockManual);

		estadoVigente.finNotificado(mockApp);

		verify(mockApp, times(1)).estaEnZonaEstacionamiento();
		verify(mockManual, times(1)).finEstacionamiento(mockApp);

	}

}
