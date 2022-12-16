package com.zetcode;

import java.util.ArrayList;

public class GestorPremios 
{
    private static GestorPremios miGestorPrem;

    private GestorPremios(){}

    public static GestorPremios getGestorPremios()
    {
        if (miGestorPrem == null)
        {
            miGestorPrem = new GestorPremios();
        }
        return miGestorPrem;  
    }

    public ArrayList<Premio> obtenerPremios(String nombre) //Este metodo implementado en Premios
    {
        ArrayList<Premio> listaPremActual = new ArrayList<Premio>();
        return listaPremActual;
    }
    
    public ArrayList<String> obtenerPremiosCompletados(String nomUsu, ArrayList<Premio> premiosPartida)
    {
        ArrayList<String> listaNombres = new ArrayList<String>();
        ArrayList<Premio> premiosTotales = this.obtenerPremios(nomUsu);
        int i = 0;
        while (i<premiosPartida.size())
        {
            int s = 0;
            boolean found = false;
            while (s<premiosTotales.size() && !found)
            {
                if (premiosTotales.get(s).getNombre().equals(premiosPartida.get(i).getNombre()))
                {
                    int progPartida = premiosPartida.get(i).progresoAct;
                    int progTotal = premiosTotales.get(s).progresoAct;
                    int progMax = premiosTotales.get(s).progresoMax;
                    if ((progPartida + progTotal) == progMax)
                    {
                        listaNombres.add(premiosTotales.get(s).getNombre());
                    }
                    found = true;
                }
                s++;
            }
        }
        return listaNombres;
    }
}
