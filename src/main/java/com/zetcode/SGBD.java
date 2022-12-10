package com.zetcode;
import java.sql.*;

public class SGBD {
    Connection conexion = null;

    private static SGBD miSGBD;

    private SGBD()
    {

    }

    public static SGBD getGestorBD()
    {
        if(SGBD.miSGBD == null)
        {
            SGBD.miSGBD = new SGBD();
        }
        return SGBD.miSGBD;
    }

    public ResultSet execSQL(String pSentencia) /** SELECT **/
    {
        try
        {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tetris", "root", "");
            PreparedStatement ps = conexion.prepareStatement(pSentencia);
            ResultSet resultado = ps.executeQuery(); /** Se utiliza para recoger datos (SELECT) **/
            //conexion.close();
            return resultado;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void execSQLVoid(String pSentencia)  /** Existe este método void porque en operaciones como UPDATE no pueden devolver un valor, y saltaría un error **/
    {
        try
        {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tetris", "root", "");
            PreparedStatement ps = conexion.prepareStatement(pSentencia);
            ps.executeUpdate(); /** Se utiliza para instrucciones que modifican la BD (INSERT, UPDATE, DELETE, CREATE, ALTER) **/
            conexion.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}