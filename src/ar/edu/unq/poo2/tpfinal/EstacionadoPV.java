package ar.edu.unq.poo2.tpfinal;

import java.time.LocalTime;

public class EstacionadoPV extends Estacionado {

	private int cantHoras;
	
	public EstacionadoPV(String patente, int cantHoras) {
	    super(patente, LocalTime.now().plusHours(cantHoras)); // Hace el calculo de la hora fin
		this.setCantHoras(cantHoras);
	}



	public int getCantHoras() {
		return cantHoras;
	}

	public void setCantHoras(Integer cantHoras) {
		this.cantHoras = cantHoras;
	}


	@Override
	public boolean tieneNroTelefonico(int nro) {
		return false;
	}
	
	@Override
	public void finalizar() { 
		setHoraFin(LocalTime.now());
		int cantHoras = getHoraFin().getHour() - getHoraFin().getHour();
		setCantHoras(cantHoras);
		// TODO testear
	}

}
