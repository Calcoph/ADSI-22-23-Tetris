package com.zetcode;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import org.apache.ibatis.javassist.expr.NewArray;

public class PruebasClasificación {
	public static void main(String[] args) {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        System.out.println("Creating table in given database..."); 
        String sql = null;
        Gestor g=new Gestor();
//        sql = "INSERT INTO USUARIO(nombreUsuario,contrasena,email) VALUES ('Ander','1234','email.com')"; 
//        SGBD.execVoidSQL(sql);
//        sql = "INSERT INTO DIFICULTAD(nivelDificultad,velocidadLadrillos,tamanoTablero) VALUES ('0', '23.47','05x20')"; 
//        SGBD.execVoidSQL(sql);
//        sql = "INSERT INTO DIFICULTAD(nivelDificultad,velocidadLadrillos,tamanoTablero) VALUES ('1', '23.47','08x20')"; 
//        SGBD.execVoidSQL(sql);
//        sql = "INSERT INTO DIFICULTAD(nivelDificultad,velocidadLadrillos,tamanoTablero) VALUES ('2', '23.47','08x20')"; 
//        SGBD.execVoidSQL(sql);
//        sql = String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'Ander'),"
//        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '1'),'%s','3')",timeStamp); 
//        SGBD.execVoidSQL(sql);
//        timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
//        sql = String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'Ander'),"
//        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '1'),'%s','8')",timeStamp); 
//        SGBD.execVoidSQL(sql);
//        timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
//        sql = String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'Ander'),"
//        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '2'),'%s','1')",timeStamp); 
//        SGBD.execVoidSQL(sql);

        g.setNuevaPuntuacion(89, "Martin", timeStamp, 2);
        System.out.println("Created table in given database...");
        System.out.println("SELECTING..."); 
        ResultSet result = SGBD.execResultSQL("SELECT * FROM RANKING");
        System.out.println("SELECTED...");
	}
}
