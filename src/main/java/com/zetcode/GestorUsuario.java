package com.zetcode;

public class GestorUsuario {
	private static GestorUsuario miGestorUsuario = null;
	private Usuario usuarioActual = null;
	
	private GestorUsuario() {
		
	}
	
	public static GestorUsuario getGestorUsuario() {
		if (miGestorUsuario == null) {
			miGestorUsuario = new GestorUsuario();
		}
		return miGestorUsuario;
	}
	
	public Usuario obtenerUsuarioActual() {
		return this.usuarioActual;
	}
	
	public String getNombreUsuario(Usuario pUsuario) {
		return pUsuario.getNombre();
	}
	
	public Partida obtenerPartidaUsuario(Usuario pUsuario) {
		return pUsuario.obtenerPartida();
	}

}
