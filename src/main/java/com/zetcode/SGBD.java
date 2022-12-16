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
    static String DB_URL = "jdbc:h2:~/tetrisSASL";
    static String USER = "sa";
    static String PASS = "sa";
    static Statement consulta = null;
    static Connection conexion = null;

    private static Connection getConnection() {
        if (!inicializado) {
            SGBD.inicializar(false);
        }

        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static void inicializarTest() {
        if (inicializado) {
            DB_URL = "jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1";
        } else {
            inicializar(true);
        }
    }

    private static void inicializar(Boolean esTest) {
        inicializado = true;
        String JDBC_DRIVER = "org.h2.Driver";
        try {
            Class.forName(JDBC_DRIVER);
        } catch (Exception e) {
            System.err.println("Error con los drivers de la base de datos. Cerrando el programa.");
            System.exit(1);
        }

        try {
            if (esTest) {
                DB_URL = "jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1";
            } else {
                DB_URL = "jdbc:h2:" + new File("tetrisSASL").getCanonicalPath();
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        Boolean bdd_existe = false;
        Connection conn = null;
        Statement stat = null;
        try {
            conn = SGBD.getConnection();
            stat = conn.createStatement();
            ResultSet resultado = stat.executeQuery("SELECT * FROM PREMIO");
            bdd_existe = true;
            resultado.next();
            String a = resultado.getString("nombre");
            conn.close();
            stat.close();
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (stat != null) {
                    stat.close();
                }
            } catch (Exception ex) {
            }
            // La base de datos no existe, no hay que hacer nada porque bdd_existe ya es false
        }

        if (!bdd_existe) {
            try {
                conn = SGBD.getConnection();
                ScriptRunner sr = new ScriptRunner(conn);
                Reader reader = new BufferedReader(new FileReader("database.sql"));
                sr.runScript(reader);
                try {
                    conn.close();
                } catch(Exception e) {
                }
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