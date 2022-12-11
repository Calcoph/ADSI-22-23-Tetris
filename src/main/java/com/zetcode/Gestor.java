package com.zetcode;

import org.json.JSONObject;
import java.sql.ResultSet;

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
		ResultSet resultado;
		//Obtener los datos necesarios
		Usuario usuario = GestorUsuario.getGestorUsuario().obtenerUsuarioActual();
		String nombreUsuario = GestorUsuario.getGestorUsuario().getNombreUsuario(usuario);
		Partida partidaUsuario = GestorUsuario.getGestorUsuario().obtenerPartidaUsuario(usuario);
		int idPartida = GestorPartida.getGestorPartida().obtenerIdPartida(partidaUsuario);
		int puntos = GestorPartida.getGestorPartida().obtenerPuntos(partidaUsuario);
		JSONObject estadoTableroJSON = GestorPartida.getGestorPartida().obtenerEstadoTablero(partidaUsuario); //Guardar el JSON como un string en la base de datos y luego parsear al cargar
		String estadoTablero = estadoTableroJSON.toString();
		String dificultad = GestorDificultad.getGestorDificultad().buscarDificultad(usuario).getNombre();
		GestorPremios.comprobarProgresoPremios(); //Comprobar el progreso de los premios
		ArrayList<Premio> listaPremios = GestorPremios.obtenerPremios(nombreUsuario);
		resultado = SGBD.execResultSQL("SELECT * FROM PARTIDA WHERE id =="+idPartida);
		if (resultado == null) { //INSERT
			SGBD.execVoidSQL(String.format("INSERT INTO PARTIDA VALUES(%d,%d,%s,%s,%s)",idPartida,puntos,estadoTablero,nombreUsuario,dificultad));
		} else { //UPDATE
			SGBD.execVoidSQL(String.format("UPDATE PARTIDA SET puntos=%d, estadoTablero='%s' WHERE id=%d)",puntos,estadoTablero,idPartida));
		}
		for (Premio premio : listaPremios) {
			String nombrePremio = premio.getNombre();
			int progreso = premio.getProgreso();
			resultado = SGBD.execResultSQL("SELECT * FROM PREMIOSENPARTIDA WHERE id =="+idPartida+"nombrePremio="+nombrePremio);
			if (resultado == null) { //INSERT
				SGBD.execVoidSQL(String.format("INSERT INTO PREMIOSENPARTIDA VALUES(%d,'%s',%d)",idPartida,nombrePremio,progreso));
			} else { //UPDATE
				SGBD.execVoidSQL(String.format("UPDATE PREMIOSENPARTIDA SET progreso=%d)",progreso));
			}
		}
	}
	
	public void cargarPartida() {
		//execSQL para conseguir todos los datos necesarios...
		//GestorDificultad.getGestorDificultad().cargarPartida(nombreUsuario);
		// TO DO
	}

}
