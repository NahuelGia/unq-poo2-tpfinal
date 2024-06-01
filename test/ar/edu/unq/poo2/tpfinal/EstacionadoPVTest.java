package ar.edu.unq.poo2.tpfinal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class EstacionadoPVTest {

	private MockedStatic<LocalTime> mockedLocalTime;

	private EstacionadoPV estacionadoTest;

	@BeforeEach
	public void setUp() {
		String patente = "ABC";
		Integer cantHoras = 2;
		estacionadoTest = new EstacionadoPV(patente, cantHoras);
	}

	@AfterEach
	public void tearDown() {
		if (mockedLocalTime != null) {
			mockedLocalTime.close();
		}
	}

	@Test
	public void testConstructorEstacionadoPV() {

		String patente = "ABC";
		LocalTime horaInicio = LocalTime.now();
		Integer cantHoras = 3;
		LocalTime horaFin = LocalTime.now().plusHours(cantHoras);

		EstacionadoPV estacionado = new EstacionadoPV(patente, cantHoras);

		assertEquals(patente, estacionado.getPatente());
		assertEquals(horaInicio, estacionado.getHoraInicio());
		assertEquals(horaFin, estacionado.getHoraFin());
		assertEquals(cantHoras, estacionado.getCantHoras());

	}

	@Test
	public void unEstacionadoPVSabeQueEstaVigente() {

		String patente = "ABC";
		int cantHoras = 3;

		EstacionadoPV estacionado = new EstacionadoPV(patente, cantHoras);

		boolean resultado = estacionado.estaVigente();

		assertTrue(resultado);
	}

	@Test
	public void unEstacionadoPVSabeQueNoEstaVigente() {

		LocalTime horaActualSimulada = LocalTime.now().plusHours(3);
		String patente = "ABC";
		int cantHoras = 1;

		EstacionadoPV estacionado = new EstacionadoPV(patente, cantHoras);

		mockedLocalTime = mockStatic(LocalTime.class);
		when(LocalTime.now()).thenReturn(horaActualSimulada);

		boolean resultado = estacionado.estaVigente();

		assertFalse(resultado);
	}

	@Test
	public void unEstacionadoPVSabeQueNoEstaVigenteSiEstaSobreLaHoraDeFin() {

		/*
		 * Para esto voy a mockear la hora al momento de verificar, asegurandome
		 * que sea exactamente sobre la hora en la que se finaliza el estacionamiento,
		 * que en este caso es una hora despues de la creación.
		 */

		LocalTime horaActualSimulada = LocalTime.now().plusHours(1);
		String patente = "ABC";
		int cantHoras = 1;

		EstacionadoPV estacionado = new EstacionadoPV(patente, cantHoras);

		mockedLocalTime = mockStatic(LocalTime.class);
		when(LocalTime.now()).thenReturn(horaActualSimulada);

		boolean resultado = estacionado.estaVigente();

		assertFalse(resultado);
	}

	@Test
	public void unEstacionadoPVSabeQueNoTieneLaPatenteDada() {

		boolean resultado = estacionadoTest.tienePatente("A");

		assertFalse(resultado);

	}

	@Test
	public void unEstacionadoPVSabeQueTieneLaPatenteDada() {

		boolean resultado = estacionadoTest.tienePatente("ABC");

		assertTrue(resultado);
	}
	
	@Test
	public void unEstacionadoPVSabeQueNoTieneNroTelefonico() {
		boolean resultado = estacionadoTest.tieneNroTelefonico(123);
		
		assertFalse(resultado);
	}

}
