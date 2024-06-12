package ar.edu.unq.poo2.tpfinal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

}
