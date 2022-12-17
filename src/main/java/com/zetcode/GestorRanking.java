package com.zetcode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;

public class GestorRanking {

	public JSONArray getRankingGlobal() throws JSONException, SQLException {
        // TODO
    	JSONArray jsr=new JSONArray();
    	ResultSet res=SGBD.execResultSQL(String.format(""+"SELECT NombreUsuario, NivelDificultad, Puntos"
    			+" FROM"
    			+"(SELECT * "
    			+ "FROM Ranking ORDER BY Puntos Desc, NivelDificultad Desc, FechaHora)"));
    	while(res.next()) {
	    	JSONObject jsu=new JSONObject();
	    	jsu.put("nombreUsuario", res.getString("nombreUsuario"));
	    	jsu.put("dif",res.getInt("NivelDificultad"));
	    	jsu.put("punt", res.getInt("Puntos"));
	    	jsr.put(jsu);
    	}
    	
    	
        return jsr;	
    }

    public JSONArray getRankingGlobalFiltrado(int difi) throws SQLException, JSONException {
        // TODO
    	JSONArray jsr=new JSONArray();
    	ResultSet res=SGBD.execResultSQL(String.format(""+"SELECT NombreUsuario, NivelDificultad, Puntos"
    			+" FROM "
    			+"(SELECT * "
    			+ "FROM Ranking "
    			+ "ORDER BY Puntos Desc, NivelDificultad, FechaHora) "+
    			"WHERE NivelDificultad = '%s'",difi));
    	while(res.next()) {
	    	JSONObject jsu=new JSONObject();
	    	jsu.put("nombreUsuario", res.getString("nombreUsuario"));
	    	jsu.put("dif",res.getInt("NivelDificultad"));
	    	jsu.put("punt", res.getInt("Puntos"));
	    	jsr.put(jsu);
    	}
    	
    	
        return jsr;	
    }

    public JSONArray  getRankingPersonal(String nombre) throws SQLException, JSONException {
    	JSONArray jsr=new JSONArray();
    	ResultSet res=SGBD.execResultSQL(String.format(""+"SELECT Puntos, NivelDificultad "
    			+ "FROM "
    			+ "(SELECT * "
    			+ "FROM Ranking "
    			+ "ORDER BY Puntos Desc, NivelDificultad, FechaHora) "
    			+ "WHERE NombreUsuario = '%s'",nombre));
    	while(res.next()) {
	    	JSONObject jsu=new JSONObject();
	    	jsu.put("dif",res.getInt("NivelDificultad"));
	    	jsu.put("punt", res.getInt("Puntos"));
	    	jsr.put(jsu);
    	}
        return jsr;
    }

    public JSONArray getRankingPersonalFiltrado(int difi, String nombre) throws SQLException, JSONException {
        // TODO
    	JSONArray jsr=new JSONArray();
    	ResultSet res=SGBD.execResultSQL(String.format(""+"SELECT Puntos, NivelDificultad "
    			+ "FROM "
    			+ "(SELECT * "
    			+ "FROM Ranking "
    			+ "ORDER BY Puntos Desc, NivelDificultad, FechaHora) "
    			+ "WHERE NombreUsuario = '%s'"
    			+ " AND NivelDificultad = '%s'", nombre,difi));
    	while(res.next()) {
	    	JSONObject jsu=new JSONObject();
	    	jsu.put("dif",res.getInt("NivelDificultad"));
	    	jsu.put("punt", res.getInt("Puntos"));
	    	jsr.put(jsu);
    	}
        return jsr;
    }
    
    public void setNuevaPuntuacion(int punt, String nom, String time, int difi) {
    	SGBD.execVoidSQL(String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) "
    			+ "VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = '%s'),"
        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '%s'),'%s','%s')",nom,difi,time,punt));
    }
}
