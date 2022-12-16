package com.zetcode;

public class Premio 
{
    String nomPrem;
    int progresoMax;
    int progresoAct;

    public String getNombre()
    {  
        return (this.nomPrem);
    }

    public int getProgresoMax()
    {
        return this.progresoMax;
    }

    public int getProgreso()
    {
        return this.progresoAct;
    }
}
