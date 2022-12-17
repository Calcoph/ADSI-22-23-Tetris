package com.zetcode;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Partida {
	private int id;
	private int puntos;
	private ArrayList<Ficha> listaFichas;
	
	
	public Partida() {
		
	}
	
	public int obtenerId() {
		return this.id;
	}
	
	public int obtenerPuntos() {
		return this.puntos;
	}
	
	public JSONObject obtenerEstadoTablero() {
		JSONObject[] arrayDatosFichas = {};
		for (Ficha pFicha : listaFichas) {
			JSONObject datosFicha = pFicha.obtenerDatosFicha();
			arrayDatosFichas[arrayDatosFichas.length] = datosFicha;
		}
		JSONObject datosFichas = new JSONObject();
		datosFichas.put("estadoTablero", arrayDatosFichas);
		return datosFichas;
		
	}
}
