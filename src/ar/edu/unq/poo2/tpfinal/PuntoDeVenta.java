package ar.edu.unq.poo2.tpfinal;

import java.time.LocalDate;
import java.time.LocalTime;

public class PuntoDeVenta {

	private SEM sem;
	private int numeroControl;

	public PuntoDeVenta(SEM sem) {

		this.setSEM(sem);
		this.numeroControl = 0;
	}

	public void setSEM(SEM sem) {
		this.sem = sem;
	}

	public SEM getSEM() {
		return sem;
	}

	public int getNumeroControl() {
		return numeroControl;
	}

	public void setNumeroControl(int numeroControl) {
		this.numeroControl = numeroControl;
	}

	public void registrarEstacionamiento(String patente, int horas) {

		if (horas <= 0) {
			throw new IllegalArgumentException("La cantidad de horas no puede ser negativa o 0.");
		}

		// Creo un EstacionadoPV
		EstacionadoPV estacionado = new EstacionadoPV(patente, horas);

		// Se lo paso al SEM para que lo registre
		this.getSEM().registrarEstacionamiento(estacionado);
		this.registrarTicketEstacionamiento(horas);

	}

	public void registrarTicketEstacionamiento(int horas) {

		LocalDate fechaActual = LocalDate.now();

		LocalTime horaActual = LocalTime.now();

		TicketEstacionamiento ticket = new TicketEstacionamiento(this.asignarNumeroControl(), this, fechaActual,
				horaActual, horas);

		this.getSEM().registrarTicket(ticket);

	}

	public void cargarSaldo(int telefono, double monto) {

		if (monto <= 0) {
			throw new IllegalArgumentException("El monto de la recarga no puede ser negativo o 0.");
		}

		this.getSEM().cargarSaldo(telefono, monto);
		this.registrarTicketRecarga(telefono, monto);

	}

	
	public void registrarTicketRecarga(int telefono, double monto) {

		LocalDate fechaActual = LocalDate.now();

		LocalTime horaActual = LocalTime.now();

		TicketRecarga ticket = new TicketRecarga(this.asignarNumeroControl(), this, fechaActual, horaActual, monto,
				telefono);

		this.getSEM().registrarTicket(ticket);

	}

	public int asignarNumeroControl() {

		if (this.getNumeroControl() < 9999) {

			int numeroActual = this.getNumeroControl();
			this.setNumeroControl(numeroActual + 1);
			return numeroActual;

		}
		this.setNumeroControl(0);
		return this.getNumeroControl();

	}

}
