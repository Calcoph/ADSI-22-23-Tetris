package com.zetcode;

import java.util.ArrayList;

public class Usuario 
{
    private String nombre;
    private Partida partidaActiva;

    public String getNombre()
    {
        return this.nombre;
    }

    public Integer obtPuntosPartida()
    {
        return this.partidaActiva.obtenerPuntos();
    }

    public ArrayList<Premio> obtListaPremios()
    {
        return this.partidaActiva.obtenerPremios();
    }
}
