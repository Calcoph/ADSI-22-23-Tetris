package com.zetcode;

import java.util.ArrayList;

public class Partida 
{
    private Integer puntos;
    private ArrayList<Premio> listaPremios;
    
    public Integer obtenerPuntos()
    {
        return this.puntos;
    }

    public ArrayList<Premio> obtenerPremios()
    {
        return this.listaPremios;
    }
}
