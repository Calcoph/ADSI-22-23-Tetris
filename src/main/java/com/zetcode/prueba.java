package com.zetcode;

import java.sql.ResultSet;

public class prueba {

	public static void main(String[] args) {
		//cambiarContraseña();
		//verUsuarios();
		//eliminarUsuario();
		verUsuarios();
		//verTodo();
	}

	public static void verUsuarios() {
		//SGBD.inicializarTest();
		System.out.println("SELECTING..."); 
        ResultSet result = SGBD.execResultSQL("SELECT * FROM USUARIO");
        System.out.println("SELECTED...");
        try {
        	while (result.next()) {
        		System.out.println(result.getString("nombreUsuario"));
        		System.out.println(result.getString("contrasena"));
        		System.out.println(result.getString("email"));
			} 
        } catch(Exception e) {
        	e.printStackTrace();
            System.err.println("Ha habido un problema");
        }
	}
	
	public static void eliminarUsuario() {
		System.out.println("DELTING...");
	    String eliminar =  String.format("DELETE FROM USUARIO WHERE contrasena = '0413'");
		SGBD.execVoidSQL(eliminar);
		System.out.println("DELETED...");
	}
	
	public static void cambiarContraseña() {
		System.out.println("CHANGING...");
	    String cambiar =  String.format("UPDATE USUARIO SET contrasena = 'admin' WHERE nombreUsuario = 'admin'");
		SGBD.execVoidSQL(cambiar);
		System.out.println("CHANGED...");
	}
	
	private static void verTodo() {
		System.out.println("SELECTING..."); 
        ResultSet result = SGBD.execResultSQL("SELECT * FROM USUARIO");
        System.out.println("SELECTED...");
        try {
        	while (result.next()) {
        		System.out.println(result.getString("nombreUsuario"));
        		System.out.println(result.getString("contrasena"));
        		System.out.println(result.getString("email"));
			} 
        } catch(Exception e) {
        	e.printStackTrace();
            System.err.println("Ha habido un problema");
        }
	}
}