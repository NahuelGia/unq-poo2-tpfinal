package ar.edu.unq.poo2.tpfinal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.time.LocalTime;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class EstacionadoAPPTest {

	@BeforeEach
	public void setUp() {

	}

	@Test
	public void testConstructorEstacionadoAPP() {

		String patente = "ABC";
		LocalTime horaInicio = LocalTime.of(17, 20);
		LocalTime horaFin = LocalTime.of(18, 10);
		int nroTelefono = 14151;

		EstacionadoAPP estacionado = new EstacionadoAPP(patente, horaInicio, horaFin, nroTelefono);

		Assertions.assertEquals(patente, estacionado.getPatente());
		Assertions.assertEquals(horaInicio, estacionado.getHoraInicio());
		Assertions.assertEquals(horaFin, estacionado.getHoraFin());
		Assertions.assertEquals(nroTelefono, estacionado.getNroTelefono());

	}

	@Test
	public void unEstacionadoAPPSabeQueEstaVigente() {

		LocalTime horaActualSimulada = LocalTime.of(18, 10);
		LocalTime horaInicio = LocalTime.of(17, 20);
		LocalTime horaFin = LocalTime.of(20, 10);
		String patente = "ABC";
		int nroTelefono = 14151;

		EstacionadoAPP estacionado = new EstacionadoAPP(patente, horaInicio, horaFin, nroTelefono);

		mockStatic(LocalTime.class);
		when(LocalTime.now()).thenReturn(horaActualSimulada);

		boolean resultado = estacionado.estaVigente();

		assertTrue(resultado);

	}

	/*
	 * @Test public void unEstacionadoAPPSabeQueNoEstaVigente() {
	 * 
	 * mockStatic(LocalTime.class);
	 * when(LocalTime.now()).thenReturn(LocalTime.of(18, 40));
	 * 
	 * String patente = "ABC" ; LocalTime horaInicio = LocalTime.of(17, 20) ;
	 * LocalTime horaFin = LocalTime.of(18, 10) ; int nroTelefono = 14151;
	 * 
	 * EstacionadoAPP estacionado = new EstacionadoAPP(patente, horaInicio, horaFin,
	 * nroTelefono);
	 * 
	 * Assertions.assertFalse(estacionado.estaVigente());
	 * 
	 * }
	 */

}
