package com.zetcode;

import java.sql.ResultSet;

public class GestorUsuario {
    private Usuario usuarioActual;
    private static GestorUsuario miGestor;
    
    public static GestorUsuario getGestor() {
        if (miGestor == null) {
            miGestor = new GestorUsuario();
        }

        return miGestor;
    }

    public Usuario obtenerUsuarioActual() {
        // TODO
        return null;
    }

    public String getNombreUsuario() {
        //TODO
        return usuarioActual.getNombre();
    }

    public Partida obtenerPartidaUsuario(Usuario a1) {
        // TODO
        return null;
    }
    
    public void registrarse(String usu, String email, String pwd1) {
	    String consulta =  String.format("INSERT INTO USUARIO VALUES ('%s', '%s', '%s')", usu,pwd1,email);
		SGBD.execVoidSQL(consulta);
    }
    
    public boolean existeUsuario(String usu, String pwd) {
    	String existe = String.format("SELECT nombreUsuario, contrasena, email FROM USUARIO WHERE nombreUsuario = '%s' AND contrasena = '%s'", usu,pwd);
	    ResultSet result = SGBD.execResultSQL(existe);
	    try {
	    	if (result.next()) {
	    		return true;
	    	} else {
				return false;
			}
    	} catch (Exception e) {
			System.exit(1);
			return false;
		}
    }
    
    public boolean existeUsuario(String usu) {
    	String existe = String.format("SELECT nombreUsuario FROM USUARIO WHERE nombreUsuario = '%s'", usu);
	    ResultSet result = SGBD.execResultSQL(existe);
	    try {
	    	if (result.next()) {
	    		return true;
	    	} else {
				return false;
			}
    	} catch (Exception e) {
			System.exit(1);
			return false;
		}
    }
    
    public boolean existeEmail(String email) {
    	String existe = String.format("SELECT email FROM USUARIO WHERE email = '%s'", email);
	    ResultSet result = SGBD.execResultSQL(existe);
	    try {
	    	if (result.next()) {
	    		return true;
	    	} else {
				return false;
			}
    	} catch (Exception e) {
			System.exit(1);
			return false;
		}
    }
    
    public void cambiarContraseña(String usu, String pwd1) {
    	String consulta =  String.format("UPDATE USUARIO SET contrasena = '%s' WHERE nombreUsuario = '%s'", pwd1,usu);
		SGBD.execVoidSQL(consulta);
    }
}