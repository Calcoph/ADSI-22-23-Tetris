package com.zetcode;

public class Usuario {
	private String nombre = null;
	private Partida partida = null;
	
	public Usuario(String pNombre) {
		this.nombre = pNombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Partida obtenerPartida() {
		return this.partida;
	}
}
