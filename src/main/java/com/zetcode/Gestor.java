package com.zetcode;

import org.json.JSONObject;

public class Gestor {
	private static Gestor miGestor = null;
	
	private Gestor() {
		
	}
	
	public static Gestor getGestor() {
		if (miGestor == null) {
			miGestor = new Gestor();
		}
		return miGestor;
	}
	
	public void guardarPartida() {
		Usuario usuario = GestorUsuario.getGestorUsuario().obtenerUsuarioActual();
		String nombreUsuario = GestorUsuario.getGestorUsuario().getNombreUsuario(usuario);
		Partida partidaUsuario = GestorUsuario.getGestorUsuario().obtenerPartidaUsuario(usuario);
		GestorPremios.comprobarProgresoPremios();
		int idPartida = GestorPartida.getGestorPartida().obtenerIdPartida(partidaUsuario);
		int puntos = GestorPartida.getGestorPartida().obtenerPuntos(partidaUsuario);
		JSONObject estadoTablero = GestorPartida.getGestorPartida().obtenerEstadoTablero(partidaUsuario); //Guardar el JSON como un string en la base de datos y luego parsear al cargar
		ArrayList<Premio> listaPremios = GestorPremios.obtenerPremios(nombreUsuario);
		Dificultad dificultad = GestorDificultad.getGestorDificultad().buscarDificultad(usuario);
		// TO DO: execSql con los datos de Partida
		for (Premio premio : listaPremios) {
			String nombre = premio.getNombre();
			int progreso = premio.getProgreso();
			//execSQL de PremiosEnPartida con idPartida,nombre,progreso
		}
	}
	
	public void cargarPartida() {
		//execSQL para conseguir todos los datos necesarios...
		//GestorDificultad.getGestorDificultad().cargarPartida(nombreUsuario);
		// TO DO
	}

}
