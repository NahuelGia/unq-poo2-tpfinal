package ar.edu.unq.poo2.tpfinal;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SEM {

	List<Estacionado> estacionados = new ArrayList<Estacionado>();
	List<AppSEM> usuarios = new ArrayList<AppSEM>();
	List<Ticket> tickets = new ArrayList<Ticket>();
	List<ZonaEstacionamiento> zonasDeEstacionamientos = new ArrayList<ZonaEstacionamiento>();
	List<Infraccion> infracciones = new ArrayList<Infraccion>();
	private LocalTime inicioFranjaHoraria;
	private LocalTime finFranjaHoraria;
	private List<SistemaMonitoreo> suscriptores;

	public SEM() {
		this.setInicioFranjaHoraria(LocalTime.of(7, 0));
		this.setFinFranjaHoraria(LocalTime.of(20, 0));
		this.setSuscriptores(new ArrayList<SistemaMonitoreo>());
		
	}

	private void setFinFranjaHoraria(LocalTime horaFin) {
		this.finFranjaHoraria = horaFin;

	}

	private void setInicioFranjaHoraria(LocalTime horaInicio) {
		this.inicioFranjaHoraria = horaInicio;

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

	public List<SistemaMonitoreo> getSuscriptores() {
		return suscriptores;
	}

	private void setSuscriptores(List<SistemaMonitoreo> suscriptores) {
		this.suscriptores = suscriptores;
	}
	
	public void registrarSuscriptor(SistemaMonitoreo suscriptor) {
		this.getSuscriptores().add(suscriptor);
	}

	public void registrarEstacionamiento(Estacionado estacionado) {
		this.getEstacionados().add(estacionado);
	}

	public void cargarSaldo(int telefono, double monto) {
		// TODO testear
		Optional<AppSEM> usuario = getUsuarios().stream()
					                            .filter(a -> a.tieneNumero(telefono))
					                            .findFirst() ;
		
		if(usuario.isPresent()) {
			usuario.get().aumentarSaldo(monto);
		} else {
		  throw new IllegalArgumentException("El nro de telefono no existe");
		}
	}

	public void registrarTicket(Ticket t) {
		this.tickets.add(t);

	}

	public void finalizarEstacionamiento(AppSEM app) {
		// TODO Hacer tests
		int nro = app.getNroTelefono() ;
		
		Estacionado estacionado = getEstacionados().stream()
												   .filter(e -> e.tieneNroTelefonico(nro))
												   .findFirst().get();
		
		// Se que si o si hay un estacionado gracias a los estado de la app
		if (estacionado.estaVigente()) {
			estacionado.finalizar();
		} 
		
		LocalTime horaInicio = estacionado.getHoraInicio();
	    
		LocalTime horaFin = estacionado.getHoraFin();
		
	    int duracion = horaFin.getHour() - horaInicio.getHour();
	    
	    Double costo = duracion * getPrecioPorHora();
	    
	    app.NotificaFinEstacionamiento(horaInicio, horaFin, duracion, costo  );
		
		notificarFinASuscriptores((EstacionadoAPP)estacionado);// Se que voy a conseguir un EstacionadoAPP
	}

	private void notificarFinASuscriptores(EstacionadoAPP estacionado) {
		// TODO Escribir los tests
		getSuscriptores().stream()
		 .forEach(s -> s.updateFinEstacionamiento(this, estacionado));
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
		
		notificarInicioASuscriptores(nuevoEstacionado);

	}

	private void notificarInicioASuscriptores(EstacionadoAPP nuevoEstacionado) {
		// TODO Escribir test
		getSuscriptores().stream()
						 .forEach(s -> s.updateInicioEstacionamiento(this, nuevoEstacionado));
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
		// TODO Hacer tests
		Optional<Estacionado> estacionado = getEstacionados().stream()
						                                     .filter(e -> e.tienePatente(patente) && e.estaVigente())
						                                     .findFirst();
		
		return estacionado.isPresent();
	}
	
}
