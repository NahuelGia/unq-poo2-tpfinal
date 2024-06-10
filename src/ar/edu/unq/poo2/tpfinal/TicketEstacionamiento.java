package ar.edu.unq.poo2.tpfinal;

import java.time.LocalDate;
import java.time.LocalTime;

public class TicketEstacionamiento extends Ticket {

	private int cantHoras;

	public TicketEstacionamiento(int numeroControl, PuntoDeVenta pv, LocalDate fechaActual, LocalTime horaActual,
			int cantHoras) {

		this.setNroDeControl(numeroControl);
		this.setPuntoDeVenta(pv);
		this.setFecha(fechaActual);
		this.setHora(horaActual);
		this.setCantHoras(cantHoras);

	}

	public void setCantHoras(int cantHoras) {
		this.cantHoras = cantHoras;
	}

	public int getCantHoras() {
		return cantHoras;
	}

}
