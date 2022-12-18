package eus.ehu.lsi.adsi;

import org.junit.Test;
import com.zetcode.*;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;

public class TestPublicar
{
    @Before  
    public void setUp() 
    {
        SGBD.inicializarTest();
        SGBD.execVoidSQL("INSERT INTO USUARIO(nombreUsuario,contrasena,email) VALUES ('usuariotest','contr','test@gmail.com')");
        GestorUsuario gu = GestorUsuario.getGestorUsuario();
        Usuario usu = new Usuario("usuariotest");
        Partida par = new Partida();
        usu.setPartida(par);
        gu.setUsuario(usu);
    }

    @After  
    public void tearDown() 
    {
        SGBD.execVoidSQL("DELETE FROM PREMIOOBTENIDO WHERE nombreUsuario='usuariotest'");
        SGBD.execVoidSQL("DELETE FROM USUARIO WHERE nombreUsuario='usuariotest'");
    }
    
    /**
     * <h2>Test 7.0</h2>
     * 
     * <h4>Descripción</h4> <p>Se pulsa el botón de Twitter. (se ha obtenido algún premio)</p>
     * 
     * <h4>Reslutado esperado</h4> <p>Se abre el navegador en la página de twitter. En la página estará listo para publicarse un tuit en el que aparezca la puntuación obtenida y los premios obtenidos</p>
     */
    @Test
	public void PublicarTwitNormal() {
        GestorUsuario gu = GestorUsuario.getGestorUsuario();
        Partida par = gu.obtenerPartidaUsuario(gu.obtenerUsuarioActual());
        par.addPuntos(69420);
        ArrayList<Premio> lPremios = new ArrayList<Premio>();
        Premio p1 = new Premio("muyApuesto", 100, 100);
        Premio p2 = new Premio("muyListo", 80, 80);
        Premio p3 = new Premio("buenoAlPokemon", 2000, 2000);
        lPremios.add(p1);
        lPremios.add(p2);
        lPremios.add(p3);
        par.anadirPremios(lPremios);
        Gestor.publicarResultados("Twitter");
        int check=0;
        assertTrue(check==0);
	}

    /**
     * <h2>Test 7.1</h2>
     * 
     * <h4>Descripción</h4> <p>Se pulsa el botón de Twitter. Pero el mensaje predefinido sería más largo de lo que permite Twitter. (se han obtenido muchos premios)</p>
     * 
     * <h4>Reslutado esperado</h4> <p>Se abre el navegador en la página de twitter. En la página estará listo para publicarse un tuit en el que aparezca la puntuación obtenida y un resumen de los premios obtenidos.</p>
     */
    @Test
	public void PublicarTwitResumen() {
        GestorUsuario gu = GestorUsuario.getGestorUsuario();
        Partida par = gu.obtenerPartidaUsuario(gu.obtenerUsuarioActual());
        par.addPuntos(69420);
        ArrayList<Premio> lPremios = new ArrayList<Premio>();
        Premio p1 = new Premio("muyApuesto", 100, 100);
        Premio p2 = new Premio("muyListo", 80, 80);
        Premio p3 = new Premio("buenoAlPokemon", 2000, 2000);
        Premio p4 = new Premio("largodenaricespqhayquellenarcaracteresyestoyaquiescribiendoquehabiaunavezunaabejaquesegunlasleyesdelaaerodinamicanodeberiasercapazdevolarperoellaloseguiahaciendoporquenoleimportabaloquepensaranlosdemasasiqueellaerafeliz", 1, 1);
        lPremios.add(p1);
        lPremios.add(p2);
        lPremios.add(p3);
        lPremios.add(p4);
        par.anadirPremios(lPremios);
        Gestor.publicarResultados("Twitter");
        int check=0;
        assertTrue(check==0);
	}

    /**
     * <h2>Test 7.2</h2>
     * 
     * <h4>Descripción</h4> <p>Se pulsa el botón de Instagram. (se ha obtenido algún premio)</p>
     * 
     * <h4>Reslutado esperado</h4> <p>Se abre el navegador en la página de instagram. En la página estará listo para publicarse un post, la imagen será el logo de tetris con un comentario en el que aparezca la puntuación obtenida y los premios obtenidos.</p>
     */
    @Test
	public void PublicarInstaNormal() {
        GestorUsuario gu = GestorUsuario.getGestorUsuario();
        Partida par = gu.obtenerPartidaUsuario(gu.obtenerUsuarioActual());
        par.addPuntos(69420);
        ArrayList<Premio> lPremios = new ArrayList<Premio>();
        Premio p1 = new Premio("muyApuesto", 100, 100);
        Premio p2 = new Premio("muyListo", 80, 80);
        Premio p3 = new Premio("buenoAlPokemon", 2000, 2000);
        lPremios.add(p1);
        lPremios.add(p2);
        lPremios.add(p3);
        par.anadirPremios(lPremios);
        Gestor.publicarResultados("Instagram");
        int check=0;
        assertTrue(check==0);
	}

    /**
     * <h2>Test 7.3</h2>
     * 
     * <h4>Descripción</h4> <p>Se pulsa el botón de Instagram. Pero el comentario predefinido sería más largo de lo que permite Instagram. (se han obtenido muchos premios)</p>
     * 
     * <h4>Reslutado esperado</h4> <p>Se abre el navegador en la página de instagram. En la página estará listo para publicarse un post en el que aparezca la puntuación obtenida y un resumen de los premios obtenidos.</p>
     */
    @Test
	public void PublicarInstaResumen() {
        GestorUsuario gu = GestorUsuario.getGestorUsuario();
        Partida par = gu.obtenerPartidaUsuario(gu.obtenerUsuarioActual());
        par.addPuntos(69420);
        ArrayList<Premio> lPremios = new ArrayList<Premio>();
        Premio p1 = new Premio("muyApuesto", 100, 100);
        Premio p2 = new Premio("muyListo", 80, 80);
        Premio p3 = new Premio("buenoAlPokemon", 2000, 2000);
        Premio p4 = new Premio("largodenaricespqhayquellenarcaracteresyestoyaquiescribiendoquehabiaunavezunaabejaquesegunlasleyesdelaaerodinamicanodeberiasercapazdevolarperoellaloseguiahaciendoporquenoleimportabaloquepensaranlosdemasasiqueellaerafeliz", 1, 1);
        lPremios.add(p1);
        lPremios.add(p2);
        lPremios.add(p3);
        lPremios.add(p4);
        par.anadirPremios(lPremios);
        Gestor.publicarResultados("Instagram");
        int check=0;
        assertTrue(check==0);
	}

    /**
     * <h2>Test 7.4</h2>
     * 
     * <h4>Descripción</h4> <p>Se pulsa el botón de Instagram. (se ha obtenido algún premio)</p>
     * 
     * <h4>Reslutado esperado</h4> <p>Se abre el navegador en la página de facebook. En la página estará listo para publicarse un post en el que aparezca la puntuación obtenida y los premios obtenidos.</p>
     */
    @Test
	public void PublicarFacebook() {
        GestorUsuario gu = GestorUsuario.getGestorUsuario();
        Partida par = gu.obtenerPartidaUsuario(gu.obtenerUsuarioActual());
        par.addPuntos(69420);
        ArrayList<Premio> lPremios = new ArrayList<Premio>();
        Premio p1 = new Premio("muyApuesto", 100, 100);
        Premio p2 = new Premio("muyListo", 80, 80);
        Premio p3 = new Premio("buenoAlPokemon", 2000, 2000);
        lPremios.add(p1);
        lPremios.add(p2);
        lPremios.add(p3);
        par.anadirPremios(lPremios);
        Gestor.publicarResultados("Facebook");
        int check=0;
        assertTrue(check==0);
	}

    /**
     * <h2>Test 7.7</h2>
     * 
     * <h4>Descripción</h4> <p>Se pulsa un botón de compartir en una partida en la que no se han obtenido premios</p>
     * 
     * <h4>Reslutado esperado</h4> <p>El mensaje predeterminado no mencionará los premios, solo la puntuación</p>
     */
    @Test
	public void PublicarSinPremios() {
        GestorUsuario gu = GestorUsuario.getGestorUsuario();
        Partida par = gu.obtenerPartidaUsuario(gu.obtenerUsuarioActual());
        par.addPuntos(69420);
        Gestor.publicarResultados("Twitter");
        int check=0;
        assertTrue(check==0);
	}

    /**
     * <h2>Test 7.8</h2>
     * 
     * <h4>Descripción</h4> <p>Se pulsa un botón de compartir en una partida en la que no se han obtenido premios pero el mensaje es demasiado largo como para que quepa en un tuit/post de instagram</p>
     * 
     * <h4>Reslutado esperado</h4> <p>El mensaje predeterminado no mencionará los premios, y en vez de decir específicamente la cantidad de puntos que ha obtenido el usuario se dirá que son muchos</p>
     */
    @Test
	public void PublicarDemasiadosPuntos() {
        GestorUsuario gu = GestorUsuario.getGestorUsuario();
        Partida par = gu.obtenerPartidaUsuario(gu.obtenerUsuarioActual());
        par.addPuntos(999999999);
        Gestor.publicarResultados("TWitter");
        int check=0;
        assertTrue(check==0);
	}
}
