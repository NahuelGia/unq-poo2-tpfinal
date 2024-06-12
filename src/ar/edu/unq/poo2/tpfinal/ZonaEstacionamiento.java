package ar.edu.unq.poo2.tpfinal;


import java.util.ArrayList;


public class ZonaEstacionamiento {
	private ArrayList<PuntoDeVenta> puntosDeVenta;
	

public ZonaEstacionamiento() {
	this.puntosDeVenta = new ArrayList<PuntoDeVenta>();
	
}


public Integer getCantidadDePv() {
	return this.puntosDeVenta.size();
}


public void registrarPuntoDeVenta(PuntoDeVenta punto) {
	this.puntosDeVenta.add(punto);
	
}


public ArrayList<PuntoDeVenta> getPvs() {
	return this.puntosDeVenta;
}


public void removerPuntoDeVenta(PuntoDeVenta punto) {
	this.puntosDeVenta.remove(punto);
	
}
}