package com.zetcode;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONObject;

public class GestorPremios {
    public static ArrayList<Premio> obtenerPremios(String nombreUsuario) {
        ArrayList<Premio> listaPremios = new ArrayList<Premio>();

        ResultSet resultado;

        resultado = SGBD.execResultSQL(String.format(""
        + "SELECT nombrepremio, progreso, progresoMax "
        + "FROM Premio JOIN PremioObtenido "
        + "ON PremioObtenido.nombrepremio=Premio.Nombre "
        + "WHERE PremioObtenido.nombreusuario='%s'",
            nombreUsuario
        ));
        try {
            while (resultado.next()) {
                String nombre = resultado.getString("nombre");
                Integer progreso = resultado.getInt("progreso");
                Integer progresoMax = resultado.getInt("progresoMax");
                Premio premio = new Premio(nombre, progreso, progresoMax);
    
                listaPremios.add(premio);
            };
        } catch(SQLException e) { 
            e.printStackTrace();
        }

        resultado = SGBD.execResultSQL(""
            + "SELECT nombre, progresoMax "
            + "FROM Premio");
        try {
            while (resultado.next()) {
                String nombre = resultado.getString("nombre");
                Integer progresoMax = resultado.getInt("progresoMax");
                Premio premio = new Premio(nombre, 0, progresoMax);
    
                listaPremios.add(premio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return listaPremios;
    }

    public static JSONObject obtenerDescripcionPremio(String nombrePremio) {
        GestorUsuario gu = GestorUsuario.getGestor();
        Usuario usuario = gu.obtenerUsuarioActual();
        String nombreUsuario = gu.getNombreUsuario(usuario);

        ResultSet resultado;
        JSONObject json = null;

        resultado = SGBD.execResultSQL(String.format(""
            + "SELECT descripcion, progreso, progresoMax "
            + "FROM Premio JOIN PremioObtenido "
                + "ON PremioObtenido.nombrePremio=Premio.nombre "
            + "WHERE PremioObtenido.nombrePremio='%s' "
            + "AND nombreUsuario='%s'",
                nombrePremio,
                nombreUsuario
            ));

        String descripcion = null;
        Integer progreso = null;
        Integer progresoMax = null;
        try {
            if (resultado.next()) {
                descripcion = resultado.getString("descripcion");
                progreso = resultado.getInt("progreso");
                progresoMax = resultado.getInt("progresoMax");
            } else {
                resultado = SGBD.execResultSQL(String.format(""
                    + "SELECT descripcion, progresoMax "
                    + "FROM Premio "
                    + "WHERE Premio.nombre='%s'", nombrePremio));
                resultado.next();

                descripcion = resultado.getString("descripcion");
                progreso = 0;
                progresoMax = resultado.getInt("progresoMax");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        json = new JSONObject();
    
        json.put("nombre", nombrePremio);
        json.put("descripcion", descripcion);
        json.put("progreso", progreso);
        json.put("progresoMax", progresoMax);

        return json;
    }

    public static void comprobarProgresoPremios() {
        ArrayList<Premio> premios = obtenerPremiosProgresados();
        guardarProgresoPremios(premios);
    }

    private static ArrayList<Premio> obtenerPremiosProgresados() {
        Premio pcf = obtenerPremioColocarFichas();
        Premio pef = obtenerPremioEliminarFilas();
        Premio pht = obtenerPremioHacerTetris();

        ArrayList<Premio> listaPremios = new ArrayList<Premio>();
        if (pcf != null) {
            listaPremios.add(pcf);
        }
        if (pef != null) {
            listaPremios.add(pef);
        }
        if (pht != null) {
            listaPremios.add(pht);
        }

        return listaPremios;
    }

    private static Premio obtenerPremioColocarFichas() {
        GestorUsuario gu = GestorUsuario.getGestor();

        Usuario usuario = gu.obtenerUsuarioActual();
        Partida partida = gu.obtenerPartidaUsuario(usuario);
        Integer fichas = GestorPartida.fichasColocadas(partida);

        Premio p = null;
        if (fichas > 0) {
            p = new Premio("fichas colocadas 1", fichas, null);
            p = new Premio("fichas colocadas 2", fichas, null);
            p = new Premio("fichas colocadas 3", fichas, null);
        }

        return p;
    }

    private static Premio obtenerPremioEliminarFilas() {
        GestorUsuario gu = GestorUsuario.getGestor();

        Usuario usuario = gu.obtenerUsuarioActual();
        Partida partida = gu.obtenerPartidaUsuario(usuario);
        Integer filas = GestorPartida.filasEliminadas(partida);

        Premio p = null;
        if (filas > 0) {
            p = new Premio("filas eliminadas 1", filas, null);
            p = new Premio("filas eliminadas 2", filas, null);
            p = new Premio("filas eliminadas 3", filas, null);
        }

        return p;
    }

    private static Premio obtenerPremioHacerTetris() {
        GestorUsuario gu = GestorUsuario.getGestor();

        Usuario usuario = gu.obtenerUsuarioActual();
        Partida partida = gu.obtenerPartidaUsuario(usuario);
        Integer tetrises = GestorPartida.tetrisHechos(partida);

        Premio p = null;
        if (tetrises > 0) {
            p = new Premio("TETRISero", tetrises, null);
            p = new Premio("Conocedor del TETRIS", tetrises, null);
            p = new Premio("Maestro del TETRIS", tetrises, null);
        }

        return p;
    }

    private static void guardarProgresoPremios(ArrayList<Premio> premios) {
        GestorUsuario gu = GestorUsuario.getGestor();

        Usuario usuario = gu.obtenerUsuarioActual();
        Partida partida = gu.obtenerPartidaUsuario(usuario);
        GestorPartida.anadirPremios(partida, premios);
    }

    public static void comprobarProgresoPremiosFinalPartida() {
        ArrayList<Premio> premios = obtenerPremiosProgresadosFinalPartida();
        premios.forEach(premio -> progresarPremio(premio));
    }

    private static ArrayList<String> obtenerPremiosCompletados(String usu, ArrayList<Premio> listaPremios) {
        // TODO
        return null;
    }

    private static ArrayList<Premio> obtenerPremiosProgresadosFinalPartida() {
        GestorUsuario gu = GestorUsuario.getGestor();

        Usuario usuario = gu.obtenerUsuarioActual();
        Partida partida = gu.obtenerPartidaUsuario(usuario);
        obtenerPremioDificultad(partida, usuario);
        obtenerPremioPuntos(partida);
        comprobarProgresoPremios();
        return GestorPartida.obtenerPremios(partida);
    }

    private static Premio obtenerPremioDificultad(Partida partida, Usuario usuario) {
        Integer puntos = GestorPartida.obtenerPuntos(partida);
        int dificultad = GestorDificultad.buscarDificultad(usuario);

        Premio p = null;
        if (dificultad == 0 && puntos >= 5000) {
            p = new Premio("Aprendiz", puntos, null);
        } else if (dificultad == 1 && puntos >= 10000) {
            p = new Premio("Veterano", puntos, null);
        } else if (dificultad == 2 && puntos >= 30000) {
            p = new Premio("Maestro", puntos, null);
        }

        return p;
    }

    private static Premio obtenerPremioPuntos(Partida partida) {
        Integer puntos = GestorPartida.obtenerPuntos(partida);

        Premio p = new Premio("Puntuador extremo", puntos, null);
        return p;
    }

    private static void progresarPremio(Premio premio) {
        GestorUsuario gu = GestorUsuario.getGestor();

        Usuario usuario = gu.obtenerUsuarioActual();
        String nusuario = gu.getNombreUsuario(usuario);
        String npremio = premio.getNombre();
        Integer progreso = premio.getProgreso();
        SGBD.execVoidSQL(String.format(""
            + "INSERT INTO PremioObtenido(nombrepremio, nombreusuario, progreso "
            + "VALUES ('%s', '%s', 0) "
            + "WHERE '%s' NOT IN "
                + "(SELECT nombrepremio "
                + "FROM PremioObtenido "
                + "WHERE nombreusuario='%s')",
            npremio, nusuario,
            npremio,


            nusuario
        ));

        SGBD.execVoidSQL(String.format(""
            + "UPDATE PremioObtenido "
            + "SET progreso=progreso+%s "
            + "WHERE nombrepremio='%s' "
                + "AND nombreusuario='%s'",
            progreso,
            npremio,
            nusuario
        ));
    }
}
