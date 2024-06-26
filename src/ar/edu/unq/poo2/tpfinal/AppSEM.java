package ar.edu.unq.poo2.tpfinal;

import java.time.LocalTime;

public class AppSEM implements MovementSensor {
	/*
	 * La app genera el estacionado Tiene un state Tiene un strategy
	 */

	private int nroTelefono;
	private Double saldo;
	private EstadoEstacionamiento estado;
	private ModoEstacionamiento modo;
	private SEM sistema;
	private boolean walking;

	public AppSEM(int nroTel, SEM sist) {
		setNroTelefono(nroTel);
		setSaldo(0.0);
		setEstado(new NoVigente());
		setModo(new Manual());
		setSistema(sist);
		setWalking(false);
		getSistema().registrarUsuario(this);

	}

	public int getNroTelefono() {
		return nroTelefono;
	}

	public void setNroTelefono(int nroTelefono) {
		this.nroTelefono = nroTelefono;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public EstadoEstacionamiento getEstado() {
		return estado;
	}

	public void setEstado(EstadoEstacionamiento estado) {
		this.estado = estado;
	}

	public ModoEstacionamiento getModo() {
		return modo;
	}

	public void setModo(ModoEstacionamiento modo) {
		this.modo = modo;
	}

	public SEM getSistema() {
		return sistema;
	}

	public void setSistema(SEM sistema) {
		this.sistema = sistema;
	}

	public boolean isWalking() {
		return walking;
	}

	public void setWalking(boolean walking) {
		this.walking = walking;
	}

	public void aumentarSaldo(Double monto) {
		// Como el punto de venta chequea que el monto no sea negativo, acá ya no es
		// necesario.
		setSaldo(getSaldo() + monto);
	}

	public void iniciarEstacionamiento(String patente) {
		estado.iniciar(this, patente);
	}

	public void finalizarEstacionamiento() {
		estado.finalizar(this);
	}

	public void posibleInicioEstacionamiento() {
		estado.inicioNotificado(this);
	}

	public void posibleFinEstacionamiento() {
		estado.finNotificado(this);
	}

	public void activarNotificaciones() {
		getModo().setNotifActiva(true);
	}

	public void desactivarNotificaciones() {
		getModo().setNotifActiva(false);
	}

	public boolean tieneSaldoMinimo() {
		return getSaldo() >= getSistema().getPrecioPorHora();
	}

	public boolean estaEnZonaEstacionamiento() {
		// Asumimos que mediante el gps puede responder la pregunta
		return true;
	}

	public void notificarSaldoInsuficiente() {
		System.out.print("El saldo es insuficiente");

	}

	public void notificarEstacionamientoExitoso(LocalTime horaInicio, LocalTime horaFin) {
		System.out.print("El estacionamiento se ha iniciado con éxito. " + "La hora de inicio es:"
				+ horaInicio.toString() + "La hora máxima de fin posible:" + horaFin.toString());

	}

	public void notificarPosibleInicio() {
		System.out.print("Recuerde iniciar su estacionamiento");
	}

	public void notificarPosibleFin() {
		System.out.print("Recuerde finalizar su estacionamiento");
	}

	public void driving() {
		if (isWalking()) {
			posibleFinEstacionamiento();
			setWalking(false);
		}
	}

	public void walking() {
		if (!isWalking()) {
			posibleInicioEstacionamiento();
			setWalking(true);
		}
	}

	public void notificarFinEstacionamiento(LocalTime horaInicio, LocalTime horaFin, Integer duracion, Double costo) {

		System.out.print(
				"El estacionamiento se ha finalizado con exito." + "La hora de inicio fue: " + horaInicio.toString()
						+ "La hora de fin fue: " + horaFin.toString() + "La duracion del estacionamiento fue: "
						+ duracion.toString() + "El costo fue de: " + costo.toString());

	}

	public boolean tieneNumero(int nro) {
		return getNroTelefono() == nro;
	}

}
