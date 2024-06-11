package ar.edu.unq.poo2.tpfinal;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Ticket {

	private int nroDeControl;
	private PuntoDeVenta puntoDeVenta;
	private LocalDate fecha;
	private LocalTime hora;
	
	public Ticket(int nroControl,PuntoDeVenta puntoDeVenta, LocalDate fecha, LocalTime hora) {
		
		this.nroDeControl = nroControl;
		this.puntoDeVenta =	puntoDeVenta;
		this.fecha = 		fecha;
		this.hora  = 		hora;
	
	}

	public int getNroDeControl() {
		return nroDeControl;
	}

	public void setNroDeControl(int nroDeControl) {
		this.nroDeControl = nroDeControl;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public PuntoDeVenta getPuntoDeVenta() {
		return puntoDeVenta;
	}

	public void setPuntoDeVenta(PuntoDeVenta pv) {
		this.puntoDeVenta = pv;
	}

}
