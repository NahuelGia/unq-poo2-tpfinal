package ar.edu.unq.poo2.tpfinal;

public class Automatico implements ModoEstacionamiento {

	private String patente;
	
	public Automatico(String patente) {
		setPatente(patente);
	}
	
	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public void inicioEstacionamiento(AppSEM app) {
		app.iniciarEstacionamiento(getPatente());
	}

	public void finEstacionamiento(AppSEM app) {
		app.finalizarEstacionamiento();
	}

	public void setNotifActiva(boolean b) {
		
	}
	
}
