package ar.edu.unq.poo2.tpfinal;

public class Vigente extends EstadoEstacionamiento {

	public void finalizar(AppSEM appSEM) {
		appSEM.getSistema().finalizarEstacionamiento(appSEM);
		appSEM.setEstado(new NoVigente());
	}

	public void finNotificado(AppSEM appSEM) {
		
		if (appSEM.estaEnZonaEstacionamiento()) {
			appSEM.getModo().finEstacionamiento(appSEM);
		}
	
	}


}