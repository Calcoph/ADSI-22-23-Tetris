package com.zetcode;

public class Dificultad {
	
	// Ancho del tablero
	private static int BOARD_WIDTH;
	// Alto del tablero
	private static int BOARD_HEIGHT;
	// Velocidad de las fichas
	private static int PERIOD_INTERVAL;
	
	// Metodos para devolver y cambiar el ancho
	public static int getBOARD_WIDTH() {
		return BOARD_WIDTH;
	}
	public static void setBOARD_WIDTH(int bOARD_WIDTH) {
		BOARD_WIDTH = bOARD_WIDTH;
	}
	
	// Metodos para devolver y cambiar el alto
	public static int getBOARD_HEIGHT() {
		return BOARD_HEIGHT;
	}
	public static void setBOARD_HEIGHT(int bOARD_HEIGHT) {
		BOARD_HEIGHT = bOARD_HEIGHT;
	}
	
	// Metodos para devolver y cambiar la velocidad
	public static int getPERIOD_INTERVAL() {
		return PERIOD_INTERVAL;
	}
	public static void setPERIOD_INTERVAL(int pERIOD_INTERVAL) {
		PERIOD_INTERVAL = pERIOD_INTERVAL;
	}
	
}
