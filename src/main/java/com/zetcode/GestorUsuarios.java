package com.zetcode;

import java.util.ArrayList;

public class GestorUsuarios
{
    private static GestorUsuarios miGestorUsu;
    private Usuario usuActual;

    private GestorUsuarios(){}

    public static GestorUsuarios getGestorUsuarios()
    {
        if (miGestorUsu == null)
        {
            miGestorUsu = new GestorUsuarios();
        }
        return miGestorUsu;
    }

    public Usuario obtenerUsuActual()
    {
        return this.usuActual;
    }

    public String getNombreUsuario(Usuario usuElegido)
    {
        return usuElegido.getNombre();
    }

    public Integer obtenerPuntosUsu(Usuario usuElegido)
    {
        return usuElegido.obtPuntosPartida();
    }

    public ArrayList<Premio> obtListaPremios(Usuario usuElegido)
    {
        return usuElegido.obtListaPremios();
    }

    public void setPartidaUsuario(String string, Partida partidaNv) {
    }
}
