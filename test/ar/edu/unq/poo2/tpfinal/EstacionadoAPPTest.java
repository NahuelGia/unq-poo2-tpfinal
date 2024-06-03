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

public class EstacionadoAPPTest {

	private MockedStatic<LocalTime> mockedLocalTime;

	private EstacionadoAPP estacionadoTest;
/*
 * TODO: Mockear las horas de inicio 
 * */
	
	@BeforeEach
	public void setUp() {
		LocalTime horaFin = LocalTime.of(19, 10);
		String patente = "ABC";
		Integer nroTelefono = 111;
		estacionadoTest = new EstacionadoAPP(patente, horaFin, nroTelefono);
	}

	@AfterEach
	public void tearDown() {
		if (mockedLocalTime != null) {
			mockedLocalTime.close();
		}
	}

	@Test
	public void testConstructorEstacionadoAPP() {
		
		
		String patente = "ABC";
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFin = LocalTime.of(18, 10);
		int nroTelefono = 14151;
		
		EstacionadoAPP estacionado = new EstacionadoAPP(patente, horaFin, nroTelefono);

		assertEquals(patente, estacionado.getPatente());
		assertEquals(horaInicio, estacionado.getHoraInicio());
		assertEquals(horaFin, estacionado.getHoraFin());
		assertEquals(nroTelefono, estacionado.getNroTelefono());

	}

	@Test
	public void unEstacionadoAPPSabeQueEstaVigente() {

		LocalTime horaActualSimulada = LocalTime.of(18, 10);
		LocalTime horaFin = LocalTime.of(19, 10);
		String patente = "ABC";
		int nroTelefono = 14151;

		EstacionadoAPP estacionado = new EstacionadoAPP(patente, horaFin, nroTelefono);

		mockedLocalTime = mockStatic(LocalTime.class);
		when(LocalTime.now()).thenReturn(horaActualSimulada);

		boolean resultado = estacionado.estaVigente();

		assertTrue(resultado);
	}

	@Test
	public void unEstacionadoAPPSabeQueNoEstaVigente() {

		LocalTime horaActualSimulada = LocalTime.of(19, 30);
		LocalTime horaFin = LocalTime.of(19, 10);
		String patente = "ABC";
		int nroTelefono = 14151;

		EstacionadoAPP estacionado = new EstacionadoAPP(patente, horaFin, nroTelefono);

		mockedLocalTime = mockStatic(LocalTime.class);
		when(LocalTime.now()).thenReturn(horaActualSimulada);

		boolean resultado = estacionado.estaVigente();

		assertFalse(resultado);
	}

	@Test
	public void unEstacionadoAPPSabeQueNoEstaVigenteSiEstaSobreLaHoraDeFin() {

		LocalTime horaActualSimulada = LocalTime.of(19, 30);
		LocalTime horaFin = LocalTime.of(19, 10);
		String patente = "ABC";
		int nroTelefono = 14151;

		EstacionadoAPP estacionado = new EstacionadoAPP(patente, horaFin, nroTelefono);

		mockedLocalTime = mockStatic(LocalTime.class);
		when(LocalTime.now()).thenReturn(horaActualSimulada);

		boolean resultado = estacionado.estaVigente();

		assertFalse(resultado);
	}

	@Test
	public void unEstacionadoAPPSabeQueNoTieneLaPatenteDada() {

		boolean resultado = estacionadoTest.tienePatente("A");

		assertFalse(resultado);

	}

	@Test
	public void unEstacionadoAPPSabeQueTieneLaPatenteDada() {

		boolean resultado = estacionadoTest.tienePatente("ABC");

		assertTrue(resultado);
	}
	
	@Test
	public void unEstacionadoAPPSabeQueNoTieneElNroTelefonicoDado() {
		
		boolean resultado = estacionadoTest.tieneNroTelefonico(0202);
		
		assertFalse(resultado);
	}
	
	@Test
	public void unEstacionadoAPPSabeQueTieneElNroTelefonicoDado() {
		
		boolean resultado = estacionadoTest.tieneNroTelefonico(111);
		
		assertTrue(resultado);
		
	} 
	

}
