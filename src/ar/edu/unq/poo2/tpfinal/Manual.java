package ar.edu.unq.poo2.tpfinal;

public class Manual implements ModoEstacionamiento {

	private boolean notifActiva;
	
	public Manual() {
		setNotifActiva(false);
	}
	
	public boolean isNotifActiva() {
		return notifActiva;
	}
	
	public void setNotifActiva(boolean notifActiva) {
		this.notifActiva = notifActiva;
	}

	public void inicioEstacionamiento(AppSEM app) {
		if (isNotifActiva()) {
			app.notificarPosibleInicio();			
		}
	}

	public void finEstacionamiento(AppSEM app) {
		if (isNotifActiva()) {
			app.notificarPosibleFin();			
		}
	}
	
	
}
