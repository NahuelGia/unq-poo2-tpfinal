package ar.edu.unq.poo2.tpfinal;

import java.time.LocalDate;
import java.time.LocalTime;

public class TicketEstacionamiento extends Ticket {

	private int cantHoras;

	public TicketEstacionamiento(int numeroControl, PuntoDeVenta puntoDeVenta, LocalDate fechaActual, LocalTime horaActual,int cantHoras) {
		super(numeroControl, puntoDeVenta, fechaActual, horaActual);
		this.setCantHoras(cantHoras);

	}

	public void setCantHoras(int cantHoras) {
		this.cantHoras = cantHoras;
	}

	public int getCantHoras() {
		return cantHoras;
	}

}
