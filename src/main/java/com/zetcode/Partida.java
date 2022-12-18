package com.zetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Partida {
    private int fichasColocadas;
    private int filasEliminadas;
    private int tetrisHechos;
    private ArrayList<Premio> premios;
    private int puntos;
    private int idPartida;
    private ArrayList<Ficha> listaFichas;
    private String estadoTablero;

    public Partida() {
        premios = new ArrayList<Premio>();
    }

    /**
     * Devuelve y resetea el contador de fichas
    */
    public int fichasColocadas() {
        int fichas = fichasColocadas;
        resetearContadorFichas();
        return fichas;
    }

    private void resetearContadorFichas() {
        fichasColocadas = 0;
    }

    /**
     * Devuelve y resetea el contador de filas
    */
    public int filasEliminadas() {
        int filas = filasEliminadas;
        resetearContadorFilas();
        return filas;
    }

    private void resetearContadorFilas() {
        filasEliminadas = 0;
    }

    /**
     * Devuelve y resetea el contador de tetrises
    */
    public int tetrisHechos() {
        int tetrises = tetrisHechos;
        resetearContadorTetris();
        return tetrises;
    }

    private void resetearContadorTetris() {
        tetrisHechos = 0;
    }

    /**
     * Añade los premios a la lista
     * 
     * Si alguno de los premios está repetido, suma sus progresos en un solo premio
    */
    public void anadirPremios(ArrayList<Premio> pPremios) {
        if (pPremios.size() > 0) {
            HashMap<String, Premio> hmpremios = new HashMap<String, Premio>();
            premios.forEach(premio -> hmpremios.put(premio.getNombre(), premio));
            pPremios.forEach(premio -> {
                String clave = premio.getNombre();
                if (hmpremios.containsKey(clave)) {
                    Premio suma = new Premio(clave, hmpremios.get(clave).getProgreso()+premio.getProgreso(), null);
                    hmpremios.put(clave, suma);
                } else {
                    hmpremios.put(clave, premio);
                }
            });
            premios = hmpremios.values().stream().collect(Collectors.toCollection(ArrayList::new));
        }
    }
    
    public int obtenerPuntos() {
        return puntos;
    }

    public void addPuntos(int pPuntos) {
        puntos += pPuntos;
    }

    /**
     * Devuelve todos los premios en los que se ha
     * progresado en esta partida. Se hayan completado o no
    */
    public ArrayList<Premio> obtenerPremios() {
        return premios;
    }

    public int obtenerId() {
        return this.idPartida;
    }

    public String obtenerEstadoTablero() {
		return this.estadoTablero;
	}

    public void setPuntosPartida(int pPuntos) {
		this.puntos = pPuntos;
	}
    
    public void setIdPartida(int pIdPartida) {
		this.idPartida = pIdPartida;
	}

    public void setEstadoTablero(String pEstadoTablero) {
		this.estadoTablero = pEstadoTablero;
	}

    public void addFilas(int filas) {
        filasEliminadas += filas;
    }

    public void addTetrises(int tetrises) {
        tetrisHechos += tetrises;
    }

    public void contarFicha() {
        fichasColocadas += 1;
    }
	
    public void anadirPremio(Premio pPremio) {
        this.premios.add(pPremio);
    }
}
