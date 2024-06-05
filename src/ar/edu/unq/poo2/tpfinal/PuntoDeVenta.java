package ar.edu.unq.poo2.tpfinal;

import java.time.LocalDate;
import java.time.LocalTime;


public class PuntoDeVenta {

	private SEM sem;

	public PuntoDeVenta(SEM sem) {
		// this.setSet(sem);
		this.setSet(sem);
	}

	public void setSet(SEM sem) {
		this.sem = sem;
	}

	public SEM getSEM() {
		return sem;
	}

	public void registrarEstacionamiento(String patente, int horas) {

		// Creo un EstacionadoPV
		EstacionadoPV estacionado = new EstacionadoPV(patente, horas);

		// Se lo paso al SEM para que lo registre
		this.getSEM().registrarEstacionamiento(estacionado);

	}

	public void cargarSaldo(int telefono, double monto) {
		
		if(monto <= 0) {
			  throw new IllegalArgumentException("El monto de la recarga no puede ser negativo.");
		}

		getSEM().cargarSaldo(telefono, monto);
		this.registrarTicketRecarga(telefono, monto);

	}

	public void registrarTicketRecarga(int telefono, double monto) {

		LocalDate fechaActual = LocalDate.now();

		LocalTime horaActual = LocalTime.now();

		TicketRecarga ticket = new TicketRecarga(this, fechaActual, horaActual, monto, telefono);

		getSEM().registrarTicket(ticket);

	}

}
