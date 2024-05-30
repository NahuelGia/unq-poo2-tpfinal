package ar.edu.unq.poo2.tpfinal;

import java.time.LocalTime;

public class EstacionadoAPP extends Estacionado {

	private Integer nroTelefono;
	
	public EstacionadoAPP(String patente, LocalTime horaInicio, LocalTime horaFin, int nroTelefono) {
		super(patente, horaInicio, horaFin);
		this.setNroTelefono(nroTelefono);
	}

	public Integer getNroTelefono() {
		return nroTelefono;
	}

	public void setNroTelefono(Integer nroTelefono) {
		this.nroTelefono = nroTelefono;
	}

	@Override
	public boolean tieneNroTelefonico(int nro) {
		// TODO Auto-generated method stub
		return false;
	}

}
