package ar.edu.unq.poo2.tpfinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PuntoDeVentaTest {

	PuntoDeVenta puntoDeVenta;
	SEM mockSEM;
	EstacionadoPV mockEstacionado;

	@BeforeEach
	void setUp() throws Exception {

		// Creo un mock de la clase EstaciondoPV
		mockEstacionado = Mockito.mock(EstacionadoPV.class);

		// Creo un mock de la clase SEM
		mockSEM = Mockito.mock(SEM.class);

		// Creo una instancia de PuntoDeVenta pasando el mock de SEM.
		puntoDeVenta = new PuntoDeVenta(mockSEM);

	}

	@Test
	void test1_UnPuntoDeVentaInicialmenteTieneUnSEMPorDefault() {
		assertEquals(mockSEM, puntoDeVenta.getSEM());

	}

	@Test
	void test2_UnPuntoDeVentaPuedeRegistrarUnEstacionamiento() {

		puntoDeVenta.registrarEstacionamiento("AAA-111", 2);

		when(mockEstacionado.getCantHoras()).thenReturn(2);
		when(mockEstacionado.getPatente()).thenReturn("AAA");

		verify(mockSEM).registrarEstacionamiento(
		argThat(estacionado -> estacionado.getPatente().equals("AAA") && estacionado.getCantHoras() == 2));

	}

}
