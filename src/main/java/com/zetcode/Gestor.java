package com.zetcode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
import org.json.JSONException;

import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.StackWalker.Option;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.core.pattern.RelativeTimePatternConverter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//import javax.mail.*;

public class Gestor {
    /**
     * Obtiene todos los premios del usuario.
     * Tanto si se han completado como si no.
    */
    public static JSONArray obtenerPremios() {
        GestorUsuario gu = GestorUsuario.getGestorUsuario();
        Usuario usuario = gu.obtenerUsuarioActual();
        String nombreUsuario = gu.getNombreUsuario(usuario);
        ArrayList<Premio> premios = GestorPremios.obtenerPremios(nombreUsuario);
        JSONArray premiosjson = new JSONArray(premios.stream().map(
            premio -> {
                JSONObject json = new JSONObject();
                json.put("nombrePremio", premio.getNombre());
                json.put("progreso", premio.getProgreso());
                json.put("progresoMax", premio.getProgresoMax());
                return json;
            }).collect(Collectors.toList()
        ));
        return premiosjson;
    }
	
    public void setUsuarioActual(Usuario nom) {
    	GestorUsuario.getGestorUsuario().setUsuario(nom);
    }

    /**
     * Obtiene todo lo necesario para mostrar la descripción de un premio.
     * 
     * Esto es la descripción, su progreso, su nombre y su progreso máximo.
     */
    public static JSONObject obtenerDescripcionPremio(String nombrePremio) {
        return GestorPremios.obtenerDescripcionPremio(nombrePremio);
    }

    /**
     * Si el usuario ha progresado en un premio, se registra en su partida
     */
    public static void comprobarProgresoPremios() {
        GestorPremios.comprobarProgresoPremios();
    }

    /**
     * Progresa en todos los premios, tanto de final de partida
     * como los normales.
     * 
     * Después, actualiza la base de datos
     */
    public static void comprobarProgresoPremiosFinalPartida() {
        GestorPremios.comprobarProgresoPremiosFinalPartida();
    }

	public static void addPuntos(int puntos) {
		GestorUsuario gu = GestorUsuario.getGestorUsuario();
        Usuario usuario = gu.obtenerUsuarioActual();
		Partida par = gu.obtenerPartidaUsuario(usuario);
		GestorPartida.addPuntos(par, puntos);
	}

     public JSONArray getRankingGlobal() throws JSONException, SQLException {
    	return new GestorRanking().getRankingGlobal();
    	
    }

    public JSONArray getRankingGlobalFiltrado(int difi) throws SQLException, JSONException {
        return new GestorRanking().getRankingGlobalFiltrado(difi);
    }

    public JSONArray getRankingPersonal(String nombreUsuario) throws SQLException, JSONException {
        return new GestorRanking().getRankingPersonal(nombreUsuario);
    }

    public JSONArray getRankingPersonalFiltrado(int difi, String nombreUsuario) throws SQLException, JSONException {
        return new GestorRanking().getRankingPersonalFiltrado(difi, nombreUsuario);
    }
    
    public void setNuevaPuntuacion(int punt, String nom, String time, int difi) {
    	GestorRanking g=new GestorRanking();
    	g.setNuevaPuntuacion(punt, nom, time, difi);
    }
	
    public String getNombreUsuario() {
    	
    	return GestorUsuario.getGestorUsuario().getNombreUsuario();
    }
    
    public int getDificultad() {
    	return GestorDificultad.getDificultad();
    }

    public boolean comprobarDatosCambiarContraseña(String usuario, String pwdOld, String pwd1, String pwd2) {
    	JOptionPane option = new JOptionPane();
    	GestorUsuario GU = GestorUsuario.getGestorUsuario();
    	if (usuario !="admin") {
    		if (usuario.length()!=0 || pwdOld.length()!=0 || pwd1.length()!=0 || pwd2.length()!=0) {
        		if (GU.existeUsuario(usuario)) {
            		if (!pwdOld.equals(pwd1) && !pwdOld.equals(pwd2)) {
                		if (pwd1.length()>3 && pwd2.length()>3) {
                    		if (pwd1.equals(pwd2)== true) {
                        		option.showMessageDialog(null, "La contraseña se ha cambiado correctamente", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
                    			return true;
                    		} else {
                    			option.showMessageDialog(null, "Las contraseñas no son iguales", "ERROR",JOptionPane.ERROR_MESSAGE);
                    			return false;
                    		} 
                		} else {
                			option.showMessageDialog(null, "La contraseña introducida debe contener al menos 4 caracteres", "ERROR",JOptionPane.ERROR_MESSAGE);
                			return false;
                		}
            		} else {
            			option.showMessageDialog(null, "La nueva contraseña debe ser diferente a la anterior", "ERROR",JOptionPane.ERROR_MESSAGE);
            			return false;
            		}
        		} else {
        			option.showMessageDialog(null, "El usuario introducido no existe", "ERROR",JOptionPane.ERROR_MESSAGE);
        			return false;
        		}
    		} else {
    			option.showMessageDialog(null, "Por favor, rellene todos los campos", "ERROR",JOptionPane.ERROR_MESSAGE);
    			return false;
    		}
		} else {
			option.showMessageDialog(null, "No seas pillo, la del admin no se puede cambiar bobi", "ERROR",JOptionPane.ERROR_MESSAGE);
			return false;
		}
    }

    public boolean comprobarDatosRegistro(String usuario, String pEmail, String pwd1, String pwd2) {
    	JOptionPane option = new JOptionPane();
    	if (usuario.length()!=0 || pEmail.length()!=0 || pwd1.length()!=0 || pwd2.length()!=0) {
    		if (usuario.length()>2) {
        		if (pwd1.length()>3 && pwd2.length()>3) {
        			if (pwd1.equals(pwd2)== true) {
        				Pattern emailPat = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        				Matcher matcher = emailPat.matcher(pEmail);
        				if (matcher.find()==true) {
        					GestorUsuario GU = GestorUsuario.getGestorUsuario();
        					if (GU.existeUsuario(usuario) == false && GU.existeEmail(pEmail) == false) {
        						option.showMessageDialog(null, "Te has registrado correctamente", "EXITO", JOptionPane.INFORMATION_MESSAGE);
        						return true;
        					} else {
        						option.showMessageDialog(null, "El usuario introducido ya existe", "ERROR",JOptionPane.ERROR_MESSAGE);
        						return false;
        					}
        				} else {
        					option.showMessageDialog(null, "El email introducido no es valido", "ERROR",JOptionPane.ERROR_MESSAGE);
        					return false;
        				}
        			} else {
        				option.showMessageDialog(null, "Las contrasenas no coinciden", "ERROR",JOptionPane.ERROR_MESSAGE);
        				return false;
        			} 
    			} else {
    				option.showMessageDialog(null, "La contrasena introducida debe contener al menos 4 caracteres", "ERROR",JOptionPane.ERROR_MESSAGE);
    				return false;
    			}
    		} else {
    			option.showMessageDialog(null, "El nombre de usuario debe contener al menos 3 caracteres", "ERROR",JOptionPane.ERROR_MESSAGE);
    			return false;
    		}
		} else {
			option.showMessageDialog(null, "Por favor, rellene todos los campos", "ERROR",JOptionPane.ERROR_MESSAGE);
			return false;
		}
    }
    
    public boolean comprobarDatosCambiarContrasena(String usuario, String pwdOld, String pwd1, String pwd2) {
    	JOptionPane option = new JOptionPane();
    	GestorUsuario GU = GestorUsuario.getGestorUsuario();
    	String[] credenciales = GU.obtenerDatos(usuario);
    	if(GU.getContrasena(usuario).equals(pwdOld)) {
    		if (usuario !="tetrixadmin") {
        		if (usuario.length()!=0 || pwdOld.length()!=0 || pwd1.length()!=0 || pwd2.length()!=0) {
            		if (GU.existeUsuario(usuario)) {
                		if (!pwdOld.equals(pwd1) && !pwdOld.equals(pwd2)) {
                    		if (pwd1.length()>3 && pwd2.length()>3) {
                        		if (pwd1.equals(pwd2)== true) {
                            		option.showMessageDialog(null, "La contrasena se ha cambiado correctamente", "EXITO", JOptionPane.INFORMATION_MESSAGE);
                        			return true;
                        		} else {
                        			option.showMessageDialog(null, "Las contrasenas no coinciden", "ERROR",JOptionPane.ERROR_MESSAGE);
                        			return false;
                        		} 
                    		} else {
                    			option.showMessageDialog(null, "La contrasena introducida debe contener al menos 4 caracteres", "ERROR",JOptionPane.ERROR_MESSAGE);
                    			return false;
                    		}
                		} else {
                			option.showMessageDialog(null, "La nueva contrasena debe ser diferente a la anterior", "ERROR",JOptionPane.ERROR_MESSAGE);
                			return false;
                		}
            		} else {
            			option.showMessageDialog(null, "El usuario introducido no existe", "ERROR",JOptionPane.ERROR_MESSAGE);
            			return false;
            		}
        		} else {
        			option.showMessageDialog(null, "Por favor, rellene todos los campos", "ERROR",JOptionPane.ERROR_MESSAGE);
        			return false;
        		}
    		} else {
    			option.showMessageDialog(null, "No seas pillo, la del admin no se puede cambiar bobi", "ERROR",JOptionPane.ERROR_MESSAGE);
    			return false;
    		}
		} else {
			option.showMessageDialog(null, "La contrasena actual introducida no es correcta", "ERROR",JOptionPane.ERROR_MESSAGE);
			return false;
		}
    }

    public int registrarse(String usu, String email, String pwd1, String pwd2) {
    	GestorUsuario GU = GestorUsuario.getGestorUsuario();
    	if (comprobarDatosRegistro(usu, email, pwd1, pwd2)) {
    		GU.registrarse(usu, email, pwd1);
    		return 1;
		} else {
			return 0;
		}
    }

    public boolean identificarse(String usu, String pwd) {
    	JOptionPane option = new JOptionPane();
    	GestorUsuario GU = GestorUsuario.getGestorUsuario();
        if (usu.length()!=0 || pwd.length()!=0) {
        	if (GU.existeUsuario(usu, pwd)) {
				setUsuario(usu);
            	return true;
            } else {
    			option.showMessageDialog(null, "Las credenciales introducidas no son correctas \nVuelve a intentarlo", "ERROR",JOptionPane.ERROR_MESSAGE);
    			return false;
    		}
		} else {
			option.showMessageDialog(null, "Por favor, rellene todos los campos", "ERROR",JOptionPane.ERROR_MESSAGE);
			return false;
		}
    }

	public void setUsuario(String usu) {
        Usuario usuarioActual = new Usuario(usu);
		GestorUsuario.getGestorUsuario().setUsuario(usuarioActual);
    }

    private void recuperar(String nombreUsuario, String pwd, String destinatario) {
    	String asunto = "Contrasena TETRIX";
		String cuerpo = "La contrasena correspondiente al usuario " + nombreUsuario + " es: " + pwd;

		String remitente = "adsitetrix@gmail.com";
		String clave = "ekqvmcmitqihyddj"; // tetrixsa22
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com"); // Servidor SMTP de Google
		//props.setProperty("mail.smtp.user", remitente); // Correo electronico desde donde se mandara el mensaje
		//props.setProperty("mail.smtp.clave", clave); // La clave de la cuenta
		props.setProperty("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
		props.setProperty("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.setProperty("mail.smtp.port", "587"); // El puerto SMTP seguro de Google 587
		
		
		Session session = Session.getInstance(props,new javax.mail.Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(remitente, clave);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(remitente));
			message.addRecipients(Message.RecipientType.TO, destinatario);
			message.setSubject(asunto);
			message.setText(cuerpo);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente, clave);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}

	public int enviarEmail (String email) {
		GestorUsuario GU = GestorUsuario.getGestorUsuario();
		String[] credenciales = GU.obtenerDatos(email);
		JOptionPane option = new JOptionPane();
		Pattern emailPat = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher = emailPat.matcher(email);
		if (matcher.find()==true) {
			if (credenciales[0]!=null) {
				recuperar(credenciales[0], credenciales[1], credenciales[2]);
				option.showMessageDialog(null, "Se han enviado los datos a su email", "CONTRASENA ENVIADA", JOptionPane.INFORMATION_MESSAGE);
				return 1;
			} else {
				option.showMessageDialog(null, "Email introducido incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
				return 0;
			}
		} else {
			option.showMessageDialog(null, "El email introducido no es valido", "ERROR", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
	}

    public int cambiar(String usu, String pwdOld, String pwd1, String pwd2) {
        GestorUsuario GU = GestorUsuario.getGestorUsuario();
        if (comprobarDatosCambiarContrasena(usu, pwdOld, pwd1, pwd2)) {
			GU.cambiarContrasena(usu, pwd1);
			return 1;
		} else {
			return 0;
		}
    }

    public int eliminarUsuario(String usu) {
    	JOptionPane option = new JOptionPane();
        GestorUsuario GU = GestorUsuario.getGestorUsuario();
        if (GU.existeUsuario(usu)) {
        	int resp = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres eliminar al usuario: " + usu, "ATENCION", JOptionPane.YES_NO_OPTION);
        	if (resp == 0) {
				GU.eliminarUsuario(usu);
        		option.showMessageDialog(null, "Usuario eliminado correctamente", "EXITO", JOptionPane.INFORMATION_MESSAGE);
				return 1;
			} else {
				return 0;
			}
		} else {
			option.showMessageDialog(null, "El usuario introducido no existe", "ERROR",JOptionPane.ERROR_MESSAGE);
			return 0;
		}
    }

    public static void guardarPartida(String pEstadoTablero) {
		ResultSet resultado;
		//Obtener los datos necesarios
		Usuario usuario = GestorUsuario.getGestorUsuario().obtenerUsuarioActual();
		String nombreUsuario = GestorUsuario.getGestorUsuario().getNombreUsuario(usuario);
		Partida partidaUsuario = GestorUsuario.getGestorUsuario().obtenerPartidaUsuario(usuario);
		int idPartida = GestorPartida.obtenerIdPartida(partidaUsuario);
		int puntos = GestorPartida.obtenerPuntos(partidaUsuario);
		String estadoTablero = pEstadoTablero;
		int dificultad = GestorDificultad.getDificultad();
		GestorPremios.comprobarProgresoPremios(); //Comprobar el progreso de los premios
		ArrayList<Premio> listaPremios = GestorPremios.obtenerPremios(nombreUsuario);
		try {
			resultado = SGBD.execResultSQL("SELECT * FROM PARTIDA WHERE id = "+idPartida+"");
			if (resultado == null) { //INSERT
				SGBD.execVoidSQL(String.format("INSERT INTO PARTIDA VALUES(%d,%d,'%s','%s',%d)",idPartida,puntos,estadoTablero,nombreUsuario,dificultad));
			} else { //UPDATE
				SGBD.execVoidSQL(String.format("UPDATE PARTIDA SET puntos = %d, estadoTablero = '%s' WHERE id = %d",puntos,estadoTablero,idPartida));
			}
			for (Premio premio : listaPremios) {
				String nombrePremio = premio.getNombre();
				int progreso = premio.getProgreso();
				resultado = SGBD.execResultSQL("SELECT * FROM PREMIOSENPARTIDA WHERE idPartida = "+idPartida+" AND nombrePremio = '"+nombrePremio+"'");
				if (resultado == null) { //INSERT
					SGBD.execVoidSQL(String.format("INSERT INTO PREMIOSENPARTIDA VALUES(%d,'%s',%d)",idPartida,nombrePremio,progreso));
				} else { //UPDATE
					SGBD.execVoidSQL(String.format("UPDATE PREMIOSENPARTIDA SET progreso = %d WHERE idPartida = %d",progreso,idPartida));
				}
			}
			resultado.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean cargarPartida(int pIdPartida) {
		ResultSet resultado;
		Usuario usuario = GestorUsuario.getGestorUsuario().obtenerUsuarioActual();
		Partida partidaUsuario = new Partida();
		String estadoTablero = null;
		try {
			//Cargar los datos de PARTIDA
			resultado = SGBD.execResultSQL("SELECT * FROM PARTIDA WHERE id = '"+pIdPartida+"'");
			if (resultado.next()) {
				int id = pIdPartida;
				int puntos = resultado.getInt("puntos");
				estadoTablero = resultado.getString("estadoTablero");
				String nombreUsuario = GestorUsuario.getGestorUsuario().getNombreUsuario(usuario);
				int dificultad = resultado.getInt("dificultad");
				GestorPartida.setIdPartida(partidaUsuario,pIdPartida);
				GestorPartida.setPuntosPartida(partidaUsuario,puntos);
				GestorPartida.setEstadoTablero(partidaUsuario,estadoTablero);
				GestorUsuario.getGestorUsuario().setPartidaUsuario(usuario,partidaUsuario);
				GestorDificultad.actualizarDificultad();
			}
			resultado.close();
			
			//Cargar datos de PREMIOS EN PARTIDA
			resultado = SGBD.execResultSQL("SELECT * FROM PREMIOSENPARTIDA WHERE idPartida = '"+pIdPartida+"'");
			while (resultado.next()) {
				String nombrePremio = resultado.getString("nombrePremio");
				int progreso = resultado.getInt("progreso");
				Premio premio = new Premio(nombrePremio,progreso,null);
				GestorPartida.anadirPremioPartida(partidaUsuario, premio);
			}
			resultado.close();
			Tetris.getTetris().start(estadoTablero);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static JSONArray obtenerPartidasUsuarioActual() {
		Usuario usuario = GestorUsuario.getGestorUsuario().obtenerUsuarioActual();
		String nombreUsuario = GestorUsuario.getGestorUsuario().getNombreUsuario(usuario);
		JSONArray arrayPartidas = new JSONArray();
		JSONObject partida;
		try {
			ResultSet resultado = SGBD.execResultSQL("SELECT * FROM PARTIDA WHERE nombreUsuario = '"+nombreUsuario+"'");
			while (resultado.next()) {
				int id = resultado.getInt("id");
				int puntos = resultado.getInt("puntos");
				partida = new JSONObject();
				partida.put("id", id);
				partida.put("puntos", puntos);
				arrayPartidas.put(partida);
			}
			resultado.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		return arrayPartidas;
	}
	
	public static void nuevaPartida() {
		try{
			ResultSet resultado = SGBD.execResultSQL("SELECT MAX(id) AS idPartida FROM PARTIDA");
			resultado.next();
			int idPartida = resultado.getInt("idPartida");
			Partida partidaNv = new Partida();
			partidaNv.setIdPartida(idPartida+1);
        	Usuario usuAct = GestorUsuario.getGestorUsuario().obtenerUsuarioActual();
        	GestorUsuario.getGestorUsuario().setPartidaUsuario(usuAct,partidaNv);
			Tetris.getTetris().start(null);
			resultado.close();
		} catch (Exception e) {
			System.err.println(e);
		}
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

    public static void publicarResultados(String pBoton)
    {
        GestorUsuario gestorUsu = GestorUsuario.getGestorUsuario();
        Usuario usuActivo = gestorUsu.obtenerUsuarioActual();
        String nombreUsu = gestorUsu.getNombreUsuario(usuActivo);
        Partida partidaAct = gestorUsu.obtenerPartidaUsuario(usuActivo);
        Integer puntosUsu = GestorPartida.obtenerPuntos(partidaAct);
        ArrayList<Premio> listaPremiosUsu = GestorPartida.obtenerPremios(partidaAct);
        ArrayList<String> listaNombresPrem = GestorPremios.obtenerPremiosCompletados(nombreUsu, listaPremiosUsu);
        String msgCompartir = configurarMensaje(nombreUsu, puntosUsu, listaNombresPrem, pBoton);
        if (pBoton.equals("Twitter"))
        {
            msgCompartir = "https://twitter.com/intent/tweet?text="+msgCompartir;

        }
        else if (pBoton.equals("Facebook")) //Copiamos el mensaje en el protapapeles para que el usuario lo pegue en su post
        {
            String copiaMsg = msgCompartir;
            StringSelection stringSelection = new StringSelection(copiaMsg);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);
            msgCompartir = "https://www.facebook.com/";
        }
        else if (pBoton.equals("Instagram")) //Copiamos el mensaje en el protapapeles para que el usuario lo pegue en su post
        {
            String copiaMsg = msgCompartir;
            StringSelection stringSelection = new StringSelection(copiaMsg);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);
            msgCompartir = "https://www.instagram.com/";
        }
        try 
        {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(msgCompartir)); //Abrimos la direccion que corresponda
        } 
        catch (java.io.IOException e) 
        {
            System.out.println(e.getMessage());
        }
    }    

    private static String configurarMensaje(String nomUsu, Integer puntos, ArrayList<String> listaPremios, String pDirec)
    {
        nomUsu = encodeValue(nomUsu);
        String msgFinal = "Enhorabuena "+nomUsu+"! Este jugador ha completado una partida del Tetris con "+puntos+" puntazos. Ademas ha conseguido los siguientes premios: ";
        int t = 0;
        if (pDirec.equals("Twitter")) //como en Twitter el mensaje se genera en url no podemos usar espacios
        {
            msgFinal = "Enhorabuena%20"+nomUsu+"!%20Este%20jugador%20ha%20completado%20una%20partida%20del%20Tetris%20con%20"+puntos+"%20puntazos.%20Ademas%20ha%20conseguido%20los%20siguientes%20premios:%20";
            int n = 0;
            while (n<listaPremios.size() && msgFinal.length()<=300)
            {
                msgFinal = msgFinal+listaPremios.get(n)+",%20";
                n++;
            }
            if (msgFinal.length()>280) //si nos pasamos del limite de caracteres el mensaje aparecera a medias, asi que lo recortamos
            {
                msgFinal = "Enhorabuena%20"+nomUsu+"!%20Este%20jugador%20ha%20completado%20una%20partida%20del%20Tetris%20con%20"+puntos+"%20puntazos.%20Ademas%20ha%20conseguido%20"+listaPremios.size()+"%20premios!";
            }   
        }
        else
        {
            while (t<listaPremios.size() && msgFinal.length()<=280)
            {
                msgFinal = msgFinal+listaPremios.get(t)+", ";
                t++;
            }
            if (msgFinal.length()>280)
            {
                msgFinal = "Enhorabuena "+nomUsu+"! Este jugador ha completado una partida del Tetris con "+puntos+" puntazos. Ademas ha conseguido "+listaPremios.size()+" premios!";
            }
        }
        
        return msgFinal;
    }

    public static void addFilas(int filas) {
        GestorUsuario gu = GestorUsuario.getGestorUsuario();
        Usuario usuario = gu.obtenerUsuarioActual();
        Partida partida = gu.obtenerPartidaUsuario(usuario);

        GestorPartida.addFilas(partida, filas);
    }

    public static void addTetrises(int tetrises) {
        GestorUsuario gu = GestorUsuario.getGestorUsuario();
        Usuario usuario = gu.obtenerUsuarioActual();
        Partida partida = gu.obtenerPartidaUsuario(usuario);

        GestorPartida.addTetrises(partida, tetrises);
    }

    public static void contarFicha() {
        GestorUsuario gu = GestorUsuario.getGestorUsuario();
        Usuario usuario = gu.obtenerUsuarioActual();
        Partida partida = gu.obtenerPartidaUsuario(usuario);

        GestorPartida.contarFicha(partida);
    }
}
