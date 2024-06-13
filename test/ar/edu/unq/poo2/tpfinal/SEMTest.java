package ar.edu.unq.poo2.tpfinal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class SEMTest {

	SEM sem;
	EstacionadoPV estacionadoPV;
	EstacionadoPV estacionadoPV2;
	TicketRecarga ticket;
	AppSEM app;
	ZonaEstacionamiento zona;

	@BeforeEach
	public void setUp() {

		sem = new SEM();
		estacionadoPV = new EstacionadoPV("AAA-111", 6);
		estacionadoPV2 = new EstacionadoPV("AAA-222", 6);
		zona = new ZonaEstacionamiento("Quilmes");

	}

	@Test
	public void cuandoUnSEMEsInicializadoSuListaDeSuscriptoresEsVacia() {
		assertTrue(sem.getSuscriptores().isEmpty());
	}

	@Test
	public void test1_CuandoUnSEMEsInicializadoSuListaDeEstacionadosEsVacia() {
		assertTrue(sem.getEstacionados().isEmpty());
	}

	@Test
	public void test2_UnSEMPuedeLiberarSuListaDeEstacionados() {

		sem.registrarEstacionamiento(estacionadoPV);
		sem.registrarEstacionamiento(estacionadoPV2);

		sem.liberarEstacionados();

		assertTrue(sem.getEstacionados().isEmpty());

	}

	@Test
	public void test3_UnSEMPuedeAgregarUnEstacionamientoASuListaDeEstacionados() {

		sem.registrarEstacionamiento(estacionadoPV);

		assertFalse(sem.getEstacionados().isEmpty());

	}

	@Test
	public void test4_CuandoUnSEMEsInicializadoSuListaDeUsuariosEsVacia() {
		assertTrue(sem.getUsuarios().isEmpty());
	}

	@Test
	public void test5_UnSEMPuedeRegistrarUnaAppASuListaDeUsuarios() {

		sem.registrarUsuario(app);

		assertFalse(sem.getUsuarios().isEmpty());

	}

	@Test
	public void test6_CuandoUnSEMEsInicialziadoSuListaDeTicketsEsVacia() {
		assertTrue(sem.getTickets().isEmpty());
	}

	@Test
	public void test7_UnSEMPuedeAgregarUnTicketASuListaDeTickets() {

		sem.registrarTicket(ticket);

		assertFalse(sem.getTickets().isEmpty());

	}

	@Test
	public void test8_CuandoUnSEMEsInicializadoSuListaDeZonasEsVacia() {
		assertTrue(sem.getZonasDeEstacionamiento().isEmpty());
	}

	@Test
	public void test9_UnSEMPuedeCrearYAgregarUnaZonaDeEstacionamientoASuListaDEZonas() {

		sem.agregarZonaEstacionamiento("Quilmes");

		assertFalse(sem.getZonasDeEstacionamiento().isEmpty());
	}

	@Test
	public void test10_CuandoUnSEMEsInicializadoSuListaDeInfraccionesEsVacia() {
		assertTrue(sem.getInfracciones().isEmpty());
	}

	@Test
	public void test11_UnSEMPuedeRegistrarUnaInfraccionEnSuListaDeInfracciones() {

		LocalDateTime fechaYHoraActual = LocalDateTime.now();
		AppInspector appInspector = new AppInspector(sem, zona);

		Infraccion infra = new Infraccion(appInspector, fechaYHoraActual, "AAA-111", zona);

		sem.registrarInfraccion(infra);

		assertFalse(sem.getInfracciones().isEmpty());

	}

	@Test
	public void test12_ElSEMConoceSuInicioDeFranjaHoraria() {

		LocalTime inicioFranjaHoraria = LocalTime.of(7, 0);

		assertEquals(sem.getInicioFranjaHoraria(), inicioFranjaHoraria);

	}

	@Test
	public void test12_ElSEMConoceSuFinDeFranjaHoraria() {

		LocalTime finFranjaHoraria = LocalTime.of(20, 0);

		assertEquals(sem.getfinFranjaHoraria(), finFranjaHoraria);
	}

	@Test
	public void test13_ElSEMPuedeInicializarUnEstacionamientoEInteractuaConLaAPP() {

		AppSEM mockApp = Mockito.mock(AppSEM.class);

		sem.estacionamientoIniciado(mockApp, "AAA-111");

		assertFalse(sem.getEstacionados().isEmpty());
		verify(mockApp, times(1)).notificarEstacionamientoExitoso(any(LocalTime.class), any(LocalTime.class));

	}

	@Test
	public void test14_CuandoUnSEMEsInicializadoSuListaDeSuscriptoresEsVacia() {

		assertTrue(sem.getSuscriptores().isEmpty());

	}

	@Test
	public void test15_ElSEMPuedeAgregarUnSuscriptorASuListaDeSuscriptores() {

		SistemaMonitoreo sist = null;

		sem.suscribirA(sist);

		assertFalse(sem.getSuscriptores().isEmpty());

	}

	@Test
	public void test15_ElSEMPuedeQuitarAUnSuscriptorDeSuListaDeSuscriptores() {
		SistemaMonitoreo sist = null;

		sem.suscribirA(sist);

		sem.desuscribirA(sist);

		assertTrue(sem.getSuscriptores().isEmpty());
	}

	@Test
	public void test16_ElSEMPuedeCargarleSaldoAUnaAppAPartirDeSuNroDeTelefono() {

		AppSEM mockApp = Mockito.mock(AppSEM.class);
		when(mockApp.tieneNumero(112233)).thenReturn(true);

		sem.registrarUsuario(mockApp);

		sem.cargarSaldo(112233, 500.00);

		verify(mockApp, times(1)).aumentarSaldo(500.00);

	}

	@Test
	public void test16_ElSEMAvisaQueSiLeDanParaCargarSaldoAUnNumeroTelefonoNoAsignado() {

		AppSEM mockApp = Mockito.mock(AppSEM.class);
		when(mockApp.tieneNumero(112233)).thenReturn(false);

		sem.registrarUsuario(mockApp);

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			sem.cargarSaldo(112233, 500.00);
		});

		assertEquals("El nro de telefono no existe", error.getMessage());

	}

	@Test
	public void test17_ELSEMPuedeFinalizarElEstacionamientoDeUnaAppUsuario() {
		AppSEM mockApp = Mockito.mock(AppSEM.class);
		EstacionadoAPP mockEstacionado = Mockito.mock(EstacionadoAPP.class);

		when(mockApp.getNroTelefono()).thenReturn(112233);
		when(mockEstacionado.tieneNroTelefonico(112233)).thenReturn(true);
		when(mockEstacionado.estaVigente()).thenReturn(true);
		when(mockEstacionado.getHoraInicio()).thenReturn(LocalTime.of(10, 0));
		when(mockEstacionado.getHoraFin()).thenReturn(LocalTime.of(12, 0));

		sem.registrarEstacionamiento(mockEstacionado);

		sem.finalizarEstacionamiento(mockApp);

		// Verificar que el estacionamiento fue finalizado
		verify(mockEstacionado, times(1)).finalizar();

		// Verificar que la notificación fue enviada
		verify(mockApp, times(1)).notificarFinEstacionamiento(eq(LocalTime.of(10, 0)), eq(LocalTime.of(12, 0)), eq(2),
				eq(sem.getPrecioPorHora() * 2));

	}

	@Test
	public void test18_UnSEMSabeConsultarVigenciaPatenteVigente() {

		EstacionadoAPP mockEstacionadoVigente = Mockito.mock(EstacionadoAPP.class);
		when(mockEstacionadoVigente.tienePatente("AAA-111")).thenReturn(true);
		when(mockEstacionadoVigente.estaVigente()).thenReturn(true);

		sem.registrarEstacionamiento(mockEstacionadoVigente);

		assertTrue(sem.consultarVigencia("AAA-111"));
	}

	@Test
	public void test19_UnSEMSabeConsultarVigenciaPatenteNoVigente() {

		EstacionadoAPP mockEstacionadoVigente = Mockito.mock(EstacionadoAPP.class);
		when(mockEstacionadoVigente.estaVigente()).thenReturn(false);

		sem.registrarEstacionamiento(mockEstacionadoVigente);

		assertFalse(sem.consultarVigencia("AAA-111"));
	}

	@Test
	public void test20_UnSEMSabeConsultarVigenciaPatenteNoExiste() {
		EstacionadoAPP mockEstacionadoVigente = Mockito.mock(EstacionadoAPP.class);
		when(mockEstacionadoVigente.estaVigente()).thenReturn(true);

		sem.registrarEstacionamiento(mockEstacionadoVigente);

		assertFalse(sem.consultarVigencia("BBB-222"));
	}
	
    // @Test
    public void unSEMPuedeFinalizarTodosLosEstacionamientosVigentesSiSeEncuentraFueraDeLaFranjaHoraria() {
        // TODO falla 
        AppSEM appMock = mock(AppSEM.class) ;
        
        LocalTime horaActualSimulada = LocalTime.of(3,0);
        
        sem.registrarUsuario(appMock);
        
        MockedStatic<LocalTime> mockedLocalTime = mockStatic(LocalTime.class);
        when(LocalTime.now()).thenReturn(horaActualSimulada);
        
        sem.finalizarTodosLosEstacionamientosVigentes();
        
        verify(appMock).finalizarEstacionamiento();
        
    }

}
