package ar.edu.unq.poo2.tpfinal;

public class PuntoDeVenta {

	private SEM sem;

	public PuntoDeVenta(SEM sem) {
		//this.setSet(sem);
		this.sem = sem;
	}

	public void setSet(SEM sem) {
		this.sem = sem;		
	}

	public SEM getSEM() {
		return sem;
	}

	public void registrarEstacionamiento(String patente, int horas) {
		
		// Creo un EstacionadoPV
		EstacionadoPV estacionado = new EstacionadoPV(patente,horas);
		
		// Se lo paso al SEM para que lo registre
		this.getSEM().registrarEstacionamiento(estacionado);
		
	}

}
