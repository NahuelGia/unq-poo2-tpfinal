package ar.edu.unq.poo2.tpfinal;

import java.time.LocalDate;
import java.time.LocalTime;

public class TicketRecarga extends Ticket {

	private double monto;
	private int celular;

	public TicketRecarga(PuntoDeVenta puntoDeVenta, LocalDate fechaActual, LocalTime horaActual, double monto,int celular) {
		this.setCelular(celular);
		this.setMonto(monto);
		this.setPuntoDeVenta(puntoDeVenta);
		this.setFecha(fechaActual);
		this.setHora(horaActual);
	}

	private void setMonto(double monto) {
		this.monto = monto;

	}

	private void setCelular(int celular) {
		this.celular = celular;

	}

	public int getCelular() {
		return celular;
	}

	public double getMonto() {
		return monto;
	}

}
