package com.zetcode;

import java.lang.StackWalker.Option;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.core.pattern.RelativeTimePatternConverter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//import javax.mail.*;

public class Gestor {
    private String obtenerPremios() {
        // TODO
        return "";
    }
    
    
    public void setUsuarioActual(String nom) {
    	GestorUsuario.getGestor().setUsuarioActual(nom);
    }
    
    private String obtenerDescripcionPremio(String st1) {
        // TODO
        return "";
    }

    public JSONArray getRankingGlobal() throws JSONException, SQLException {
        // TODO
    	return new GestorRanking().getRankingGlobal();
    	
    }

    public JSONArray getRankingGlobalFiltrado(int difi) throws SQLException, JSONException {
        // TODO
        return new GestorRanking().getRankingGlobalFiltrado(difi);
    }

    public JSONArray getRankingPersonal(String nombreUsuario) throws SQLException, JSONException {
        // TODO
        return new GestorRanking().getRankingPersonal(nombreUsuario);
    }

    public JSONArray getRankingPersonalFiltrado(int difi, String nombreUsuario) throws SQLException, JSONException {
        // TODO
        return new GestorRanking().getRankingPersonalFiltrado(difi, nombreUsuario);
    }
    
    public void setNuevaPuntuacion(int punt, String nom, String time, int difi) {
    	GestorRanking g=new GestorRanking();
    	g.setNuevaPuntuacion(punt, nom, time, difi);
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
        					GestorUsuario GU = new GestorUsuario();
        					if (GU.existeUsuario(usuario) == false && GU.existeEmail(pEmail) == false) {
        						option.showMessageDialog(null, "Te has registrado correctamente", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
        						return true;
        					} else {
        						option.showMessageDialog(null, "El usuario introducido ya existe", "ERROR",JOptionPane.ERROR_MESSAGE);
        						return false;
        					}
        				} else {
        					option.showMessageDialog(null, "El email introducido no es válido", "ERROR",JOptionPane.ERROR_MESSAGE);
        					return false;
        				}
        			} else {
        				option.showMessageDialog(null, "Las contraseñas no son iguales", "ERROR",JOptionPane.ERROR_MESSAGE);
        				return false;
        			} 
    			} else {
    				option.showMessageDialog(null, "La contraseña introducida debe contener al menos 4 caracteres", "ERROR",JOptionPane.ERROR_MESSAGE);
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
    
    public boolean comprobarDatosCambiarContraseña(String usuario, String pwdOld, String pwd1, String pwd2) {
    	JOptionPane option = new JOptionPane();
    	GestorUsuario GU = new GestorUsuario();
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

    public int registrarse(String usu, String email, String pwd1, String pwd2) {
    	GestorUsuario GU = new GestorUsuario();
    	if (comprobarDatosRegistro(usu, email, pwd1, pwd2)) {
    		GU.registrarse(usu, email, pwd1);
    		return 1;
		} else {
			return 0;
		}
    }

    public boolean identificarse(String usu, String pwd) {
    	JOptionPane option = new JOptionPane();
    	GestorUsuario GU = new GestorUsuario();
        if (usu.length()!=0 || pwd.length()!=0) {
        	if (GU.existeUsuario(usu, pwd)) {
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

    private void recuperar(String nombreUsuario, String pwd, String destinatario) {
    	String asunto = "Contraseña TETRIX";
		String cuerpo = "La contraseña correspondiente al usuario " + nombreUsuario + "es: " + pwd;

		String remitente = "adsitetrix@gmail.com";
		String clave = "tetrixsa22";

		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com"); // Servidor SMTP de Google
		//props.setProperty("mail.smtp.user", remitente); // Correo electronico desde donde se mandara el mensaje
		//props.setProperty("mail.smtp.clave", clave); // La clave de la cuenta
		props.setProperty("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
		props.setProperty("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.setProperty("mail.smtp.port", "587"); // El puerto SMTP seguro de Google
		
		javax.mail.Session session = javax.mail.Session.getInstance(props,new javax.mail.Authenticator() {
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

	public void enviarEmail (String texto) {
		GestorUsuario GU = new GestorUsuario();
		String[] credenciales = GU.obtenerDatos(texto);
		JOptionPane option = new JOptionPane();
		if (credenciales[0]!=null) {
			recuperar(credenciales[0], credenciales[1], credenciales[2]);
			option.showMessageDialog(null, "Se han enviado los datos a su email", "DATOS ENVIADOS", JOptionPane.INFORMATION_MESSAGE);
		} else {
			option.showMessageDialog(null, "No se han podido enviar los datos a su email", "DATOS NO ENVIADOS", JOptionPane.ERROR_MESSAGE);
		}
	}

    public void cambiar(String usu, String pwdOld, String pwd1, String pwd2) {
        GestorUsuario GU = new GestorUsuario();
        if (comprobarDatosCambiarContraseña(usu, pwdOld, pwd1, pwd2)) {
			GU.cambiarContraseña(usu, pwd1);
		}
    }

    public void eliminarUsuario(String usu) {
    	JOptionPane option = new JOptionPane();
        GestorUsuario GU = new GestorUsuario();
        if (GU.existeUsuario(usu)) {
        	int resp = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres eliminar al usuario: " + usu, "ATENCIÓN", JOptionPane.YES_NO_OPTION);
        	if (resp == 0) {
				System.out.println("Usuario eliminado");
				//GU.eliminarUsuario(usu);
			}
		} else {
			option.showMessageDialog(null, "El usuario introducido no existe", "ERROR",JOptionPane.ERROR_MESSAGE);
		}
    }

    private int mostarDificultad(String nombreUsuario, int dificultad) {
        // TODO
        return 0;
    }

    private void actualizarDificultad(String nombreUsuario, int dificultad) {
        // TODO
    }

    private String mostrarPersonalizacion(String nombreUsuario) {
        // TODO
        return "";
    }

    private String actualizarPersonalizacion(String nombreUsuario) {
        // TODO
        return "";
    }

  public static void guardarPartida() {
		
	}
	
	public void cargarPartida() {
		//execSQL para conseguir todos los datos necesarios...
		//GestorDificultad.getGestorDificultad().cargarPartida(nombreUsuario);
		// TO DO
	}
    private void gestionarEvento(String evento) {
        // TODO
    }

    private void publicarResultados(String st1) {
        // TODO
    }

    private String configurarMensaje(String nombreUsuario, int puntos, ArrayList<String> listNombresPrem) {
        // TODO
        return "";
    }
    
    public String getNombreUsuario() {
    	
    	return GestorUsuario.getGestor().getNombreUsuario();
    }
    
    public int getDificultad() {
    	return GestorDificultad.getDificultad();
    }
    
    
}
