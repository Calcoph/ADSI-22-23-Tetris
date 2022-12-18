package com.zetcode;

import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private Partida partida;

    public Usuario(String pNombre) {
		this.nombre = pNombre;
	}

    public void setPartida(Partida pPartida) {
        this.partida = pPartida;
    }

    public String getNombre() {
        return nombre;
    }

    public Partida obtenerPartida() {
        return partida;
    }
}
