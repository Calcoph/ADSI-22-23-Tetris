package com.zetcode;

import org.json.JSONObject;

public class Ficha {
    private int posX;
    private int posY;
    private String color;

    public void setDatosFicha(int pPosX, int pPosY, String pColor) {
        this.posX = pPosX;
        this.posY = pPosY;
        this.color = pColor;
    }

    public JSONObject obtenerDatosFicha() {
    	JSONObject datosFicha = new JSONObject();
    	datosFicha.put("posX", this.posX);
    	datosFicha.put("posY", this.posY);
    	datosFicha.put("color", this.color);
        return datosFicha;
    }
}