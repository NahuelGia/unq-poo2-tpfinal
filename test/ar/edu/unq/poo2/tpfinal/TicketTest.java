package ar.edu.unq.poo2.tpfinal;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicketTest {

	PuntoDeVenta pv;
	LocalTime horaActual;
	LocalDate fechaActual;
	
	@BeforeEach
	void setUp() throws Exception {
		
		pv = new PuntoDeVenta(new SEM());
		horaActual = LocalTime.now();
		fechaActual = LocalDate.now();
		
	}

	@Test
	void test1_ConstructorTicketRecarga() {
		
					
		TicketRecarga ticketP = new TicketRecarga(1, pv, fechaActual, horaActual, 500.00, 112233);
		
		assertEquals(ticketP.getCelular(),112233);
		assertEquals(ticketP.getPuntoDeVenta(),pv);
		assertEquals(ticketP.getFecha(),fechaActual);
		assertEquals(ticketP.getHora(),horaActual);
		assertEquals(ticketP.getMonto(), 500.00);
		assertEquals(ticketP.getNroDeControl(), 1);
		
	}
	
	
	@Test
	void test2_ConstructorTicketEstacionamiento() {
		
		TicketEstacionamiento ticketE = new TicketEstacionamiento(1,pv,fechaActual,horaActual,5);
		
		assertEquals(ticketE.getNroDeControl(), 1);
		assertEquals(ticketE.getPuntoDeVenta(),pv); 
		assertEquals(ticketE.getFecha(),fechaActual);
		assertEquals(ticketE.getHora(),horaActual);
		assertEquals(ticketE.getCantHoras(),5);
		
	}
	
	//El caso  que un TicketRecarga es creado con monto negativo o 0, es cubierto en el PuntoDeVenta.
	//El caso  que un TicketEstacionamiento es creado con horas negativas o 0, es cubierto en el PuntoDeVenta.
	
	

}
