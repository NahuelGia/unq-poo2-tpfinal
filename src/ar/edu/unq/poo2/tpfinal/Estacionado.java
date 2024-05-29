package ar.edu.unq.poo2.tpfinal;

import java.time.LocalTime;

public abstract class Estacionado {
	
	private String patente;
	
	private LocalTime horaInicio;
	
	private LocalTime horaFin;
	
	public Estacionado(String patente, LocalTime horaInicio, LocalTime horaFin) {
		this.setPatente(patente);
		this.setHoraInicio(horaInicio);
		this.setHoraFin(horaFin);
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}
	
	
	
}
