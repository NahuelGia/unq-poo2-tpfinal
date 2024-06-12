package ar.edu.unq.poo2.tpfinal;

import java.util.ArrayList;

public class ZonaEstacionamiento {
	
	private String nombre;
	
	private ArrayList<PuntoDeVenta> puntosDeVenta;
	
	public ZonaEstacionamiento(String nombre) {
		this.puntosDeVenta = new ArrayList<PuntoDeVenta>();
		this.setNombre(nombre);
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


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
