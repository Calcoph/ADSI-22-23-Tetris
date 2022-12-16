package com.zetcode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

public class Gestor 
{
    private static Gestor miGestor;

    private Gestor(){}

    public static Gestor getGestor()
    {
        if (miGestor == null)
        {
            miGestor = new Gestor();
        }
        return miGestor;
    }

    private static String encodeValue(String value) 
    {
        try 
        {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } 
        catch (UnsupportedEncodingException ex) 
        {
            throw new RuntimeException(ex.getCause());
        }
    }

    public void publicarResultados(String pBoton)
    {
        GestorUsuarios gestorUsu = GestorUsuarios.getGestorUsuarios();
        GestorPremios gestorPrem = GestorPremios.getGestorPremios();
        Usuario usuActivo = gestorUsu.obtenerUsuActual();
        String nombreUsu = gestorUsu.getNombreUsuario(usuActivo);
        Integer puntosUsu = gestorUsu.obtenerPuntosUsu(usuActivo);
        ArrayList<Premio> listaPremiosUsu = gestorUsu.obtListaPremios(usuActivo);
        ArrayList<String> listaNombresPrem = gestorPrem.obtenerPremiosCompletados(nombreUsu, listaPremiosUsu);
        String msgCompartir = this.configurarMensaje(nombreUsu, puntosUsu, listaNombresPrem);
        if (pBoton.equals("Twitter"))
        {
            msgCompartir = "https://twitter.com/intent/tweet?text="+msgCompartir;

        }
        else if (pBoton.equals("Facebook"))
        {
            String copiaMsg = msgCompartir;
            StringSelection stringSelection = new StringSelection(copiaMsg);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);
            msgCompartir = "https://www.facebook.com/";
        }
        else if (pBoton.equals("Instagram"))
        {
            String copiaMsg = msgCompartir;
            StringSelection stringSelection = new StringSelection(copiaMsg);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);
            msgCompartir = "https://www.instagram.com/";
        }
        try 
        {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(msgCompartir));
        } 
        catch (java.io.IOException e) 
        {
            System.out.println(e.getMessage());
        }
    }    

    private String configurarMensaje(String nomUsu, Integer puntos, ArrayList<String> listaPremios)
    {
        nomUsu = encodeValue(nomUsu);
        String msgFinal = "Enhorabuena "+nomUsu+"! Este jugador ha completado una partida del Tetris con "+puntos+" puntazos. Ademas ha conseguido los siguientes premios: ";
        int t = 0;
        while (t<listaPremios.size() && msgFinal.length()<=280)
        {
            msgFinal = msgFinal+listaPremios.get(t)+", ";
            t++;
        }
        if (msgFinal.length()>280)
        {
            msgFinal = "Enhorabuena "+nomUsu+"! Este jugador ha completado una partida del Tetris con "+puntos+" puntazos. Ademas ha conseguido "+listaPremios.size()+" premios!";
        }
        return msgFinal;
    }

    public void nuevaPartida()
    {
        Partida partidaNv = new Partida();
        Usuario usuAct = GestorUsuarios.getGestorUsuarios().obtenerUsuActual();
        GestorUsuarios.getGestorUsuarios().setPartidaUsuario(usuAct.getNombre(),partidaNv);
    }
}
