package ar.edu.unq.poo2.tpfinal;


import java.time.LocalDateTime;

public class AppInspector {
	private SEM sem;
	public ZonaEstacionamiento estacionamiento;
	
	public AppInspector(SEM sem,ZonaEstacionamiento estacionamiento) {
		this.sem=sem;;
		this.estacionamiento =estacionamiento;
	}
	public SEM getSem() {
		return this.sem;
	}
	public ZonaEstacionamiento getZona() {
		return this.estacionamiento;
	}
/*
	public void setSem(SEM sem2) {
		this.sem=sem2;
		
	}
	public void setZonaEst(ZonaEstacionamiento estacionamiento2) {
		this.estacionamiento =estacionamiento2;
	}*/
	public boolean consultarVigencia(String patente) {
		boolean esVigente = this.sem.consultarVigencia(patente);
		if (! esVigente) {
			this.labrarInfraccion(new Infraccion(this, LocalDateTime.now(),patente,this.estacionamiento));
		}
		return esVigente;
		
	}
 public void labrarInfraccion(Infraccion infraccion) {
	this.sem.registrarInfraccion(infraccion);
	
}
public void setSem(SEM sem) {
	this.sem=sem;
	
}
}
