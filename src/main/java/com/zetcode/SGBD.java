package com.zetcode;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import org.apache.ibatis.jdbc.ScriptRunner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.FileNotFoundException;

public class SGBD {
    static boolean inicializado = false;
    static String DB_URL = "jdbc:h2:~/test";
    static String USER = "sa";
    static String PASS = "sa";
    static Statement consulta = null;
    static Connection conexion = null;

    private static Connection getConnection() {
        if (!inicializado) {
            SGBD.inicializar();
        }

        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private static void inicializar() {
        inicializado = true;
        String JDBC_DRIVER = "org.h2.Driver";
        try {
            Class.forName(JDBC_DRIVER);
        } catch (Exception e) {
            System.err.println("Error con los drivers de la base de datos. Cerrando el programa.");
            System.exit(1);
        }
        try {
            DB_URL = "jdbc:h2:" + new File("test").getCanonicalPath();
        } catch (Exception e) {
            System.err.println(e);
        }
    	
        Boolean bdd_existe = false;
        try {
            ResultSet resultado = SGBD.execResultSQL("SELECT * FROM PREMIO");
            resultado.next();
            resultado.getString("nombre");
            bdd_existe = true;
        } catch (Exception e) {
            // La base de datos no existe, no hay que hacer nada porque bdd_existe ya es false
        }

        if (!bdd_existe) {
            try {
                ScriptRunner sr = new ScriptRunner(conexion);
                Reader reader = new BufferedReader(new FileReader("database.sql"));
                sr.runScript(reader);
            } catch (FileNotFoundException e) {
                System.err.println(e);
            }
        }
    }
    
    public static void execVoidSQL(String pConsulta) { //INSERT, UPDATE, DELETE
    	try {
            if (conexion != null) {
                conexion.close();
            }
            if (consulta != null) {
                consulta.close();
            }
            conexion = SGBD.getConnection();
    		consulta = conexion.createStatement();
    		consulta.executeUpdate(pConsulta);
    	} catch (Exception e) {
            e.printStackTrace();
    		System.err.println(e);
    	}
    }
    
    public static ResultSet execResultSQL(String pConsulta) { //SELECT
    	ResultSet resultado = null;
    	try {
            if (conexion != null) {
                conexion.close();
            }
            if (consulta != null) {
                consulta.close();
            }
            conexion = SGBD.getConnection();
    		consulta = conexion.createStatement();
    		resultado = consulta.executeQuery(pConsulta);
    	} catch (Exception e) {
            e.printStackTrace();
    		System.err.println(e);
    	}
    	return resultado;
    }
}
