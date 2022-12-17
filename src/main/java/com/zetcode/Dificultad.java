package com.zetcode;

public class Dificultad {
	
	private static int BOARD_WIDTH;
	private static int BOARD_HEIGHT;
	private static int PERIOD_INTERVAL;
	
	public static int getBOARD_WIDTH() {
		return BOARD_WIDTH;
	}
	public static void setBOARD_WIDTH(int bOARD_WIDTH) {
		BOARD_WIDTH = bOARD_WIDTH;
	}
	
	public static int getBOARD_HEIGHT() {
		return BOARD_HEIGHT;
	}
	public static void setBOARD_HEIGHT(int bOARD_HEIGHT) {
		BOARD_HEIGHT = bOARD_HEIGHT;
	}
	
	public static int getPERIOD_INTERVAL() {
		return PERIOD_INTERVAL;
	}
	public static void setPERIOD_INTERVAL(int pERIOD_INTERVAL) {
		PERIOD_INTERVAL = pERIOD_INTERVAL;
	}
	
}