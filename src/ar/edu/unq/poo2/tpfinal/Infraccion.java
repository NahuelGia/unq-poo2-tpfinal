package ar.edu.unq.poo2.tpfinal;


import java.time.LocalDateTime;

public class Infraccion {
	private String patente;
	private LocalDateTime fechayHora;
	private AppInspector appInspector;
	private ZonaEstacionamiento zona;
	
	public Infraccion(AppInspector inspector, LocalDateTime localDate, String patente,ZonaEstacionamiento zona) {
		this.appInspector=inspector;
		this.fechayHora= localDate;
		this.zona=zona;
		this.patente=patente;
	}

	public String getPatente() {
		return patente;
	}

	public LocalDateTime getFechayHora() {
		return fechayHora;
	}

	public AppInspector getAppInspector() {
		return appInspector;
	}

	public ZonaEstacionamiento getZona() {
		return zona;
	}
	
}
