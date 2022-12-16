package com.zetcode;
import java.sql.ResultSet;

public class EjemploBDD {
    public static void main(String[] args) {
        String sql = null;
        boolean sin_inicializar = true;
        try {
            System.out.println("Creating table in given database...");
            sql = "CREATE TABLE REGISTRATION " +
            "(id INTEGER not NULL, " +
            " first VARCHAR(255), " +
            " last VARCHAR(255), " +
            " age INTEGER, " +
            " PRIMARY KEY ( id ))";
            SGBD.execVoidSQL(sql);
            System.out.println("Created table in given database...");
        } catch (Exception e) {
            sin_inicializar = false;
        }

        if (sin_inicializar) {
            System.out.println("INSERTING..."); 
            SGBD.execVoidSQL("INSERT INTO REGISTRATION(id, age) VALUES (5, 19)");
            System.out.println("INSERTED...");
        }

        System.out.println("SELECTING..."); 
        ResultSet result = SGBD.execResultSQL("SELECT id, age FROM REGISTRATION WHERE id=5");
        System.out.println("SELECTED...");
        try {
            result.next();
            System.out.println(result.getInt("id"));
            System.out.println(result.getInt("age"));
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("Ha habido un problema");
        }

        System.out.println("Goodbye!");
    }
}