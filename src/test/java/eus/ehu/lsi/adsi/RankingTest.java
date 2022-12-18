package eus.ehu.lsi.adsi;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zetcode.Clasificacion;
import com.zetcode.Gestor;
import com.zetcode.GestorRanking;
import com.zetcode.IU_Identificacion;
import com.zetcode.SGBD;
import com.zetcode.Usuario;

public class RankingTest {
	@Before
	public void setUp() throws AWTException {
		SGBD.inicializarTest();
		SGBD.execVoidSQL("INSERT INTO USUARIO(nombreUsuario,contrasena,email) VALUES ('yo','1234','test@mail.com')");
		SGBD.execVoidSQL("INSERT INTO USUARIO(nombreUsuario,contrasena,email) VALUES ('otro','1234','test2@mail.com')");
		SGBD.execVoidSQL("INSERT INTO DIFICULTAD(nivelDificultad,velocidadLadrillos,tamanoTablero) VALUES ('0', '23.47','05x20')");
		SGBD.execVoidSQL("INSERT INTO DIFICULTAD(nivelDificultad,velocidadLadrillos,tamanoTablero) VALUES ('1', '23.48','06x20')");
		SGBD.execVoidSQL("INSERT INTO DIFICULTAD(nivelDificultad,velocidadLadrillos,tamanoTablero) VALUES ('2', '23.49','07x20')");

		
	}
	@After
	public void finalizar() {
		SGBD.execVoidSQL("DELETE FROM RANKING");
		SGBD.execVoidSQL("DELETE FROM USUARIO");
		SGBD.execVoidSQL("DELETE FROM DIFICULTAD");
	}
	@Test
	public void ningunaPuntuacion() throws JSONException, SQLException {
		Usuario u=new Usuario("yo");
		Gestor g=new Gestor();
		g.setUsuarioActual(u);
		assertEquals(0, g.getRankingGlobal().length());
		System.out.println("Test ningunaPuntuacion:");
		System.out.println("No hay ninguna puntuacion");
	}
			
			
	@Test
	public void conPuntuacionesGlobal() throws JSONException, SQLException {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        SGBD.execVoidSQL(String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'yo'),"
        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '1'),'%s','3')",timeStamp));
		timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        SGBD.execVoidSQL(String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'yo'),"
        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '0'),'%s','10')",timeStamp));
		timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        SGBD.execVoidSQL(String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'otro'),"
        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '0'),'%s','8')",timeStamp));
        //System.out.println("Salen puntuaciones");
        
        Usuario u=new Usuario("yo");
		Gestor g=new Gestor();
		g.setUsuarioActual(u);
		assertEquals(3, g.getRankingGlobal().length());
		System.out.println("Test conPuntuacionesGlobal:");
		System.out.println("	Nombre Puntuación Dificultad");
		JSONArray jl=g.getRankingGlobal();
		for(Integer i=0;i<jl.length();i++) {
			JSONObject o=jl.getJSONObject(i);
			Integer pos=i+1;
			System.out.println(pos.toString() + "º	"+o.getString("nombreUsuario")+" 		"
			+String.valueOf(o.getInt("punt"))+ " 	"+String.valueOf(o.getInt("dif")));
			
		}
	}
	
	@Test
	public void conPuntuacionPersonal() throws JSONException, SQLException {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        SGBD.execVoidSQL(String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'yo'),"
        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '1'),'%s','3')",timeStamp));
		timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        SGBD.execVoidSQL(String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'yo'),"
        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '0'),'%s','10')",timeStamp));
		timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        SGBD.execVoidSQL(String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'otro'),"
        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '0'),'%s','8')",timeStamp));
		Usuario u=new Usuario("yo");
		Gestor g=new Gestor();
		g.setUsuarioActual(u);
		assertEquals(2, g.getRankingPersonal(u.getNombre()).length());
		System.out.println("Test conPuntuacionesPersonal:");
		System.out.println("	Nombre Puntuación Dificultad");
		JSONArray jl=g.getRankingPersonal(u.getNombre());
		for(Integer i=0;i<jl.length();i++) {
			JSONObject o=jl.getJSONObject(i);
			Integer pos=i+1;
			System.out.println(pos.toString() + "º	"+u.getNombre()+" 		"
			+String.valueOf(o.getInt("punt"))+ "	 	"+String.valueOf(o.getInt("dif")));
			
		}
	}
	
	@Test
	public void conPuntuacionFiltrado() throws JSONException, SQLException {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        SGBD.execVoidSQL(String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'yo'),"
        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '1'),'%s','3')",timeStamp));
		timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        SGBD.execVoidSQL(String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'yo'),"
        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '0'),'%s','10')",timeStamp));
		timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        SGBD.execVoidSQL(String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'otro'),"
        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '0'),'%s','8')",timeStamp));
		Usuario u=new Usuario("yo");
		Gestor g=new Gestor();
		g.setUsuarioActual(u);
		assertEquals(2, g.getRankingGlobalFiltrado(0).length());
		System.out.println("Test conPuntuacionFiltrado:");
		System.out.println("	Nombre Puntuación Dificultad");
		JSONArray jl=g.getRankingGlobalFiltrado(0);
		for(Integer i=0;i<jl.length();i++) {
			JSONObject o=jl.getJSONObject(i);
			Integer pos=i+1;
			System.out.println(pos.toString() + "º	"+o.getString("nombreUsuario")+" 	"
			+String.valueOf(o.getInt("punt"))+ "	 "+String.valueOf(o.getInt("dif")));
			
		}
	}
	
	@Test
	public void conMismaPuntuacionMayorNivel() throws JSONException, SQLException {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        SGBD.execVoidSQL(String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'yo'),"
        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '1'),'%s','3')",timeStamp));
		timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        SGBD.execVoidSQL(String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'yo'),"
        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '0'),'%s','10')",timeStamp));
		timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        SGBD.execVoidSQL(String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'otro'),"
        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '2'),'%s','3')",timeStamp));
		Usuario u=new Usuario("yo");
		Gestor g=new Gestor();
		g.setUsuarioActual(u);
		JSONObject ob=(JSONObject) g.getRankingGlobal().get(1);
		assertEquals("otro", ob.get("nombreUsuario"));
		System.out.println("Test conMismaPuntuacionMayorNivel:");
		System.out.println("	Nombre Puntuación Dificultad");
		JSONArray jl=g.getRankingGlobal();
		for(Integer i=0;i<jl.length();i++) {
			JSONObject o=jl.getJSONObject(i);
			Integer pos=i+1;
			System.out.println(pos.toString() + "º	"+o.getString("nombreUsuario")+" 	"
			+String.valueOf(o.getInt("punt"))+ "	 "+String.valueOf(o.getInt("dif")));
			
		}
	}
	
	@Test
	public void conMismoNivelMayorPunt() throws JSONException, SQLException {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        SGBD.execVoidSQL(String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'yo'),"
        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '1'),'%s','3')",timeStamp));
		timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        SGBD.execVoidSQL(String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'yo'),"
        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '0'),'%s','10')",timeStamp));
		timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        SGBD.execVoidSQL(String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'otro'),"
        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '2'),'%s','5')",timeStamp));
		Usuario u=new Usuario("yo");
		Gestor g=new Gestor();
		g.setUsuarioActual(u);
		JSONObject ob=(JSONObject) g.getRankingGlobal().get(0);
		assertEquals("yo", ob.get("nombreUsuario"));
		System.out.println("Test conMismoNivelMayorPunt:");
		System.out.println("	Nombre Puntuación Dificultad");
		JSONArray jl=g.getRankingGlobal();
		for(Integer i=0;i<jl.length();i++) {
			JSONObject o=jl.getJSONObject(i);
			Integer pos=i+1;
			System.out.println(pos.toString() + "º	"+o.getString("nombreUsuario")+" 	"
			+String.valueOf(o.getInt("punt"))+ "	 "+String.valueOf(o.getInt("dif")));
			
		}
	}
	
	@Test
	public void conMismoNivelPunt() throws JSONException, SQLException {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        SGBD.execVoidSQL(String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'yo'),"
        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '1'),'%s','3')",timeStamp));
		timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        SGBD.execVoidSQL(String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'yo'),"
        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '0'),'%s','10')",timeStamp));
		timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        SGBD.execVoidSQL(String.format("INSERT INTO RANKING(nombreUsuario,niveldificultad, fechahora,puntos) VALUES((SELECT nombreusuario FROM USUARIO WHERE nombreUsuario = 'otro'),"
        		+ "(SELECT niveldificultad FROM DIFICULTAD WHERE niveldificultad = '1'),'%s','3')",timeStamp));
		Usuario u=new Usuario("yo");
		Gestor g=new Gestor();
		g.setUsuarioActual(u);
		JSONObject ob=(JSONObject) g.getRankingGlobal().get(1);
		assertEquals("yo", ob.get("nombreUsuario"));
		System.out.println("Test conMismoNivelPunt:");
		System.out.println("	Nombre Puntuación Dificultad");
		JSONArray jl=g.getRankingGlobal();
		for(Integer i=0;i<jl.length();i++) {
			JSONObject o=jl.getJSONObject(i);
			Integer pos=i+1;
			System.out.println(pos.toString() + "º	"+o.getString("nombreUsuario")+" 	"
			+String.valueOf(o.getInt("punt"))+ "	 "+String.valueOf(o.getInt("dif")));
			
		}
	}
	
//	@Test
//	public void btnVolver() throws JSONException, SQLException {
//		Usuario u=new Usuario("yo");
//		Gestor g=new Gestor();
//		g.setUsuarioActual(u);
//		Clasificacion c=new Clasificacion();
//		c.setVisible(true);
//		Clasificacion.getFrames();
//	}
	
	

}
