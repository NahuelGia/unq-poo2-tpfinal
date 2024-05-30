package ar.edu.unq.poo2.tpfinal;

import java.time.LocalTime;

public class EstacionadoPV extends Estacionado {

	
	
	public EstacionadoPV(String patente, LocalTime horaInicio, LocalTime horaFin,int cantHoras) {
		super(patente, horaInicio, horaFin);
		// TODO Auto-generated constructor stub
	}


	public String getPatente() {
		return null;
		// TODO Auto-generated constructor stub
	}

	
	public int getHoras() {
		return 0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean tieneNroTelefonico(int nro) {
		// TODO Auto-generated method stub
		return false;
	}

}
