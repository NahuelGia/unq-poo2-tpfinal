package ar.edu.unq.poo2.tpfinal;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PuntoDeVentaTest {

	PuntoDeVenta puntoDeVenta;
	PuntoDeVenta puntoSinSEM;
	SEM mockSEM;

	@BeforeEach
	void setUp() throws Exception {

		// Creo un mock de la clase SEM
		mockSEM = Mockito.mock(SEM.class);

		// Creo una instancia de PuntoDeVenta pasando el mock de SEM.
		puntoDeVenta = new PuntoDeVenta(mockSEM);

	}

	@Test
	void test1_UnPuntoDeVentaSeInicializaConUnSEM() {
		assertEquals(mockSEM, puntoDeVenta.getSEM());

	}

	@Test
	void test2_UnPuntoDeVentaPuedeRegistrarUnEstacionamiento() {

		puntoDeVenta.registrarEstacionamiento("AAA-111", 2);

		verify(mockSEM).registrarEstacionamiento(argThat(estacionado -> estacionado.getCantHoras() == 2));
		
		verify(mockSEM).registrarEstacionamiento(argThat(estacionado -> estacionado.getPatente().equals("AAA-111")));

	}
	
	@Test
	void test3_UnPuntoDeVentaNoPuedeRegistrarUnEstacionamientoConHorasNegativas() {
		
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {puntoDeVenta.registrarEstacionamiento("AAA-111", -1);});
		
		 assertEquals("La cantidad de horas no puede ser negativa o 0.", exception.getMessage());
	}

	@Test
	void test4_UnPuntoDeVentaPuedeCargarleSaldoAUnCliente() {

		puntoDeVenta.cargarSaldo(112233, 500.00);

		verify(mockSEM, times(1)).cargarSaldo(112233, 500.00);

	}
	
	@Test
	void test5_UnPuntoDeVentaNoPuedeCargaleSaldoNegativoAUnCliente() {
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {puntoDeVenta.cargarSaldo(123456789, -50.0);});
	
		 assertEquals("El monto de la recarga no puede ser negativo o 0.", exception.getMessage());
	}
	
	

	@Test
	void test6_UnPuntoDeVentaPuedeGenerarUnTicketDeRecarga() {

		puntoDeVenta.registrarTicketRecarga(112233, 500.00);
		
		verify(mockSEM, times(1)).registrarTicket(argThat(ticket -> ticket.getCelular() == 112233 && ticket.getMonto() == 500.00));
	}
	
	@Test
	void test7_unPuntoDeVentaAsignaUnNumeroDeControlMenorA9999(){
		puntoDeVenta.setNumeroControl(100);
		
		assertEquals(100,puntoDeVenta.asignarNumeroControl()); // Se asigna el numero de control actual.
		assertEquals(101,puntoDeVenta.asignarNumeroControl()); // Se asigna el nunero de control que sigue.
		
	}

	@Test
	void test8_UnPuntoDeVentaReseteaSuNumeroDeControlAlLlegarA10000(){
		puntoDeVenta.setNumeroControl(10000);
		
		assertEquals(0,puntoDeVenta.asignarNumeroControl());
	}
	
	
}
