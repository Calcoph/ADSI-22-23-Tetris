package com.zetcode;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GestorUsuario {
    private Usuario usuarioActual;
    private static GestorUsuario miGestorUsuario;
    
    // VARIABLES PARA EVITAR SQL INJECTION
    private String SELECT = " SELECT ";
    private String FROM = " FROM ";
    private String WHERE = " WHERE ";
    private String INSERT = " INSERT ";
    private String INTO = " INTO ";
    private String VALUES = " VALUES ";
    private String AND = " AND ";
    private String UPDATE = " UPDATE ";
    private String SET = " SET ";
    private String DELETE = " DELETE ";

    private GestorUsuario() {

    }
        
    public static GestorUsuario getGestorUsuario() {
        if (miGestorUsuario == null) {
            miGestorUsuario = new GestorUsuario();
        }

        return miGestorUsuario;
    }
    
    public String getNombreUsuario(Usuario pUsuario) {
        return pUsuario.getNombre();
    }
    
    public Partida obtenerPartidaUsuario(Usuario pUsuario) {
        return pUsuario.obtenerPartida();
    }
    
    public void setPartidaUsuario(Usuario pUsuario, Partida pPartida) {
        pUsuario.setPartida(pPartida);
    }

    public Usuario obtenerUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuario(Usuario usu) {
        usuarioActual = usu;
    }

    public String getNombreUsuario() {
        
        return usuarioActual.getNombre();
    }
    public void registrarse(String usu, String email, String pwd1) {
		String consulta =  String.format(INSERT + INTO + "USUARIO " + VALUES + "('%s', '%s', '%s')", usu,pwd1,email);
		SGBD.execVoidSQL(consulta);
    }
    
    public boolean existeUsuario(String usu, String pwd) {
    	String existe = String.format(SELECT + "*" + FROM + "USUARIO" + WHERE + "nombreUsuario = '%s'" + AND + "contrasena = '%s'", usu,pwd);
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
    	String existe = String.format(SELECT + "*" + FROM + "USUARIO" + WHERE + "nombreUsuario = '%s'", usu);
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
    	String existe = String.format(SELECT + "email" + FROM + "USUARIO" + WHERE + "email = '%s'", email);
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
    
    public String getContrasena(String usuario) {
    	String existe = String.format(SELECT + "contrasena" + FROM + "USUARIO" + WHERE + "nombreUsuario = '%s'", usuario);
	    ResultSet result = SGBD.execResultSQL(existe);
	    try {
	    	if (result.next()) {
	    		System.out.println(result.getString("contrasena"));
	    		return result.getString("contrasena");
	    	} else {
				return null;
			}
    	} catch (Exception e) {
			System.exit(1);
			return null;
		}
    }
    
    public void cambiarContrasena(String usu, String pwd1) {
    	String consulta =  String.format(UPDATE + "USUARIO" + SET + "contrasena = '%s'" + WHERE + "nombreUsuario = '%s'", pwd1,usu);
		SGBD.execVoidSQL(consulta);
    }
    
    public void eliminarUsuario(String usu) {
      List<String> consultas = new ArrayList<>();
      consultas.add(String.format(DELETE + FROM + "PREMIOOBTENIDO" + WHERE + "nombreUsuario = '%s'", usu));
      consultas.add(String.format(DELETE + FROM + "RANKING" + WHERE + "nombreUsuario = '%s'", usu));
      consultas.add(String.format(DELETE + FROM + "PARTIDA" + WHERE + "nombreUsuario = '%s'", usu));
      consultas.add(String.format(DELETE + FROM + "USUARIO" + WHERE + "nombreUsuario = '%s'", usu));
      for (String consulta : consultas) {
          SGBD.execVoidSQL(consulta);
      }
    }

    public String[] obtenerDatos(String correo) {
      String consulta = String.format(SELECT + "nombreUsuario, contrasena, email" + FROM + "USUARIO" + WHERE + "email = '%s'", correo);
      ResultSet resultado = SGBD.execResultSQL(consulta);
      String[] usu = null;

      if (resultado!=null) {
        usu = new String[3];
        try {
          while (resultado.next()) {
            usu[0] = resultado.getString("nombreUsuario");
            usu[1] = resultado.getString("contrasena");
            usu[2] = resultado.getString("email");
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      return usu;
    }
}
