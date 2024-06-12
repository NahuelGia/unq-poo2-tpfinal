package ar.edu.unq.poo2.tpfinal;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SEM {

	List<Estacionado> estacionados = new ArrayList<Estacionado>();
	List<AppSEM> usuarios = new ArrayList<AppSEM>();
	List<Ticket> tickets = new ArrayList<Ticket>();
	List<ZonaEstacionamiento> zonasDeEstacionamientos = new ArrayList<ZonaEstacionamiento>();
	List<Infraccion> infracciones = new ArrayList<Infraccion>();
	private LocalTime inicioFranjaHoraria;
	private LocalTime finFranjaHoraria;

	public SEM() {
		this.setInicioFranjaHoraria(LocalTime.of(7, 0));
		this.setFinFranjaHoraria(LocalTime.of(20, 0));
		
	}

	private void setFinFranjaHoraria(LocalTime of) {
		this.finFranjaHoraria = LocalTime.of(20, 0);

	}

	private void setInicioFranjaHoraria(LocalTime of) {
		this.inicioFranjaHoraria = LocalTime.of(7, 0);

	}

	public LocalTime getInicioFranjaHoraria() {
		return inicioFranjaHoraria;

	}

	public LocalTime getfinFranjaHoraria() {
		return finFranjaHoraria;
	}


	public List<Estacionado> getEstacionados() {
		return estacionados;
	}

	public List<AppSEM> getUsuarios() {
		return usuarios;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public Double getPrecioPorHora() {
		return 40.00;
	}

	public List<ZonaEstacionamiento> getZonasDeEstacionamiento() {
		return zonasDeEstacionamientos;
	}

	public List<Infraccion> getInfracciones() {
		return infracciones;
	}

	public void registrarEstacionamiento(Estacionado estacionado) {
		this.getEstacionados().add(estacionado);
	}

	public void cargarSaldo(int telefono, double monto) {
		// TODO Auto-generated method stub

	}

	public void registrarTicket(Ticket t) {
		this.tickets.add(t);

	}

	public void finalizarEstacionamiento(AppSEM appSEM) {
		// TODO Auto-generated method stub

	}

	public void estacionamientoIniciado(AppSEM appSEM, String patente) {
		
		double horasPagables = (appSEM.getSaldo() / this.getPrecioPorHora());
		LocalTime horaActual = LocalTime.now();
		LocalTime horaMaxima = horaActual.plusHours((long) horasPagables);
		
		
		int intHoraFin = Math.min(this.getfinFranjaHoraria().getHour(), horaMaxima.getHour());
		
		LocalTime horaFin = LocalTime.of(intHoraFin, 0);
		
		
		EstacionadoAPP nuevoEstacionado = new EstacionadoAPP(patente, horaFin, appSEM.getNroTelefono());
		
		this.registrarEstacionamiento(nuevoEstacionado);
		appSEM.notificarEstacionamientoExitoso(horaActual, horaFin);

	}

	public void liberarEstacionados() {
		this.getEstacionados().removeAll(estacionados);
	}

	public void registrarUsuario(AppSEM appSEM) {
		this.getUsuarios().add(appSEM);

	}

	public void agregarZonaEstacionamiento(String string) {

		// creo nueva zona de estacionamiento.
		ZonaEstacionamiento nuevaZona = new ZonaEstacionamiento(string);

		// la agrego a la lista de zonas
		this.getZonasDeEstacionamiento().add(nuevaZona);

	}

	public void registrarInfraccion(Infraccion infraccion) {
		this.getInfracciones().add(infraccion);

	}

	public boolean consultarVigencia(String patente) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
