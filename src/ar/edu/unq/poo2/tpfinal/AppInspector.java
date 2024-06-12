package ar.edu.unq.poo2.tpfinal;

import java.time.LocalDateTime;

public class AppInspector {
	
	private SEM sem;
	public ZonaEstacionamiento estacionamiento;

	public AppInspector(SEM sem, ZonaEstacionamiento estacionamiento) {
		this.sem = sem;
		this.estacionamiento = estacionamiento;
	}

	public SEM getSem() {
		return this.sem;
	}
	
	public void setSem(SEM sem) {
		this.sem = sem;

	}

	public ZonaEstacionamiento getZona() {
		return this.estacionamiento;
	}

	public boolean consultarVigencia(String patente) {
		boolean esVigente = this.sem.consultarVigencia(patente);
		if (!esVigente) {
			this.labrarInfraccion(new Infraccion(this, LocalDateTime.now(), patente, this.estacionamiento));
		}
		return esVigente;

	}

	public void labrarInfraccion(Infraccion infraccion) {
		this.sem.registrarInfraccion(infraccion);

	}

	
}
