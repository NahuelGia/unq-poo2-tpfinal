package ar.edu.unq.poo2.tpfinal;

public class NoVigente extends EstadoEstacionamiento {

	@Override
	public void iniciar(AppSEM appSEM, String patente) {
		if (appSEM.tieneSaldoMinimo()) {
			appSEM.getSistema().estacionamientoIniciado(appSEM, patente);
			appSEM.setEstado(new Vigente());
		}
		else {
			appSEM.notificarSaldoInsuficiente();
		}
	}
	
	@Override
	public void inicioNotificado(AppSEM appSEM) {
		if (appSEM.estaEnZonaEstacionamiento()) {
			appSEM.getModo().inicioEstacionamiento(appSEM);
		}
			
	}

}
