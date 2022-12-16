package com.zetcode;

import java.sql.SQLException;
import java.util.ArrayList;
import org.json.*;

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
    private String obtenerPremios() {
        // TODO
        return "";
    }

    private String obtenerDescripcionPremio(String st1) {
        // TODO
        return "";
    }

    public JSONArray getRankingGlobal() throws JSONException, SQLException {
        // TODO
    	return GestorRanking.getGestorRanking().getRankingGlobal();
    	
    }

    public JSONArray getRankingGlobalFiltrado(int difi) throws SQLException, JSONException {
        // TODO
        return GestorRanking.getGestorRanking().getRankingGlobalFiltrado(difi);
    }

    public JSONArray getRankingPersonal(String nombreUsuario) throws SQLException, JSONException {
        // TODO
        return GestorRanking.getGestorRanking().getRankingPersonal(nombreUsuario);
    }

    public JSONArray getRankingPersonalFiltrado(int difi, String nombreUsuario) throws SQLException, JSONException {
        // TODO
        return GestorRanking.getGestorRanking().getRankingPersonalFiltrado(difi, nombreUsuario);
    }
    
    public String getNombreUsuario() {
    	return GestorUsuario.getGestor().getNombreUsuario();
    }

    private boolean comprobarContraseña(String pwd1, String pwd2) {
        // TODO
        return false;
    }

    private void registrarse(String usu, String email, String pwd1) {
        // TODO
    }

    private String identificarse(String usu, String pwd) {
        // TODO
        return "";
    }

    private boolean recuperar(String textoIntroducido) {
        // TODO
        return false;
    }

    private boolean comprobar(String usu, String pwdVieja, String pwd1, String pwd2) {
        // TODO
        return false;
    }

    private void cambiar(String usu, String pwdVieja, String pwd) {
        // TODO
    }

    private void eliminarUsuario(String usu) {
        // TODO
    }

    private int mostarDificultad(String nombreUsuario, int dificultad) {
        // TODO
        return 0;
    }

    private void actualizarDificultad(String nombreUsuario, int dificultad) {
        // TODO
    }

    private String mostrarPersonalizacion(String nombreUsuario) {
        // TODO
        return "";
    }

    private String actualizarPersonalizacion(String nombreUsuario) {
        // TODO
        return "";
    }

    private void guardarPartida() {
        // TODO
    }

    private void cargarPartida(String st1) {
        // TODO
    }

    private void gestionarEvento(String evento) {
        // TODO
    }

    private void publicarResultados(String st1) {
        // TODO
    }

    private String configurarMensaje(String nombreUsuario, int puntos, ArrayList<String> listNombresPrem) {
        // TODO
        return "";
    }
}
