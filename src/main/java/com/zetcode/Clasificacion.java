package com.zetcode;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;

public class Clasificacion extends JFrame{
	private JPanel contentPane;
	private JPanel panelGlobal; //Panel clasificacion global
	private JPanel panelPersonal; //Panel clasificacion personal
	private JPanel panelRankingG; //Panel del ranking global
	private JPanel panelRankingP; //Panel del ranking personal
	private JPanel panelAbajo; //Panel con el botón volver y la dificultad
	private JPanel panelDifi;
	private JTabbedPane pestanas;
	private JComboBox<String> dificultad;
	private JButton btnVolver;
	private JPanel	panelVac;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clasificacion frame = new Clasificacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Vista
	public Clasificacion() throws JSONException, SQLException{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//contentPane.setLayout(new GridLayout(2, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0,0));
		contentPane.add(getPestanas(),BorderLayout.CENTER);
		contentPane.add(getPanelAbajo(),BorderLayout.SOUTH);
		setTitle("Clasificación");
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	
	private JPanel getPanelGlobal() throws JSONException, SQLException {
		if (panelGlobal==null) {
			panelGlobal=new JPanel();
			panelGlobal.setLayout(new BorderLayout(0,0));
			panelGlobal.add(getPanelRankingG(),BorderLayout.CENTER);
			//panelGlobal.add(getPanelAbajo(),BorderLayout.SOUTH);
		}
		return panelGlobal;
	}
	
	private JPanel getPanelPersonal() throws SQLException, JSONException {
		if (panelPersonal==null) {
			panelPersonal=new JPanel();
			panelPersonal.setLayout(new BorderLayout(0,0));
			panelPersonal.add(getPanelRankingP(),BorderLayout.CENTER);		
			}
		return panelPersonal;
	}
	
	private JPanel getPanelRankingG() throws JSONException, SQLException {
		if(panelRankingG==null) {
			panelRankingG=new JPanel();
			panelRankingG.setLayout(new BorderLayout(0, 0));
			JPanel panelAux=new JPanel(); //Para fijar los títulos
			panelAux.setLayout(new GridLayout(1,4));
			panelRankingG.add(panelAux,BorderLayout.NORTH);
			panelRankingG.add(new JLabel());
			panelAux.add(new JLabel(""));
			panelAux.add(new JLabel("Nombre"));
			panelAux.add(new JLabel("Puntuación"));
			panelAux.add(new JLabel("Dificultad"));
			Gestor g=new Gestor();
			JSONArray jl=g.getRankingGlobal();
			if(jl.length()!=0) {
				
				JPanel panelPunt=new JPanel();
				panelPunt.setLayout(new GridLayout(jl.length(),3,50,50));//Panel de las puntuaciones
				
				//panelPunt.se
				JScrollPane barra= new JScrollPane(panelPunt);
				barra.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				panelRankingG.add(barra);
				
			for(Integer i=0;i<jl.length();i++) {
				JSONObject o=jl.getJSONObject(i);
				Integer pos=i+1;
				panelPunt.add(new JLabel(pos.toString() + "º"));
				panelPunt.add(new JLabel(o.getString("nombreUsuario")));
				panelPunt.add(new JLabel(String.valueOf(o.getInt("punt"))));
				panelPunt.add(new JLabel(String.valueOf(o.getInt("dif"))));
				
			}
			} else {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				JPanel panelVac=new JPanel();
				panelVac.setLayout(new BorderLayout(0,0));
				panelVac.add(new JLabel("No existe ninguna puntuación",JLabel.CENTER));
				panelRankingG.add(panelVac,BorderLayout.CENTER);
			}	
		}
		return panelRankingG;
	}
	
	private JPanel getPanelRankingP() throws SQLException, JSONException {
		if(panelRankingP==null) {
			panelRankingP=new JPanel();
			panelRankingP.setLayout(new BorderLayout(0, 0));
			JPanel panelAux=new JPanel(); //Para fijar los títulos
			panelAux.setLayout(new GridLayout(1,4,50,50));
			panelRankingP.add(panelAux,BorderLayout.NORTH);
			panelRankingP.add(new JLabel());
			panelAux.add(new JLabel(""));
			panelAux.add(new JLabel("Nombre"));
			panelAux.add(new JLabel("Puntuación"));
			panelAux.add(new JLabel("Dificultad"));
			Gestor g=new Gestor();
			JSONArray jl=g.getRankingPersonal(g.getNombreUsuario()); //Cambiar por g.getusuario()
			if(jl.length()!=0) {	
				JPanel panelPunt=new JPanel();
				panelPunt.setLayout(new GridLayout(jl.length(),3,50,50));//Panel de las puntuaciones
				JScrollPane barra= new JScrollPane(panelPunt);
				barra.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				panelRankingP.add(barra);
				
			for(Integer i=0;i<jl.length();i++) {
				JSONObject o=jl.getJSONObject(i);
				Integer pos=i+1;
				panelPunt.add(new JLabel(pos.toString() + "º"));
				panelPunt.add(new JLabel(g.getNombreUsuario())); //cambiar por g.getusuario()
				panelPunt.add(new JLabel(String.valueOf(o.getInt("punt"))));
				panelPunt.add(new JLabel(String.valueOf(o.getInt("dif"))));
				
			}
			} else {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				JPanel panelVac=new JPanel();
				panelVac.setLayout(new BorderLayout(0,0));
				panelVac.add(new JLabel("No existe ninguna puntuación tuya",JLabel.CENTER));
				panelRankingP.add(panelVac,BorderLayout.CENTER);
			}	
		}
		return panelRankingP;
	}
	
	private JButton getBtnVolver() {
		if(btnVolver==null) {
			btnVolver=new JButton("Volver");
			btnVolver.setBackground(new Color(146,139,143));
		    btnVolver.setForeground(Color.BLACK);
			btnVolver.addActionListener(new verMenu());
		}
		return btnVolver;
	}
	
	private JPanel getPanelAbajo(){
		if (panelAbajo==null){
			panelAbajo=new JPanel();
			panelAbajo.setLayout(new FlowLayout(FlowLayout.CENTER,60,10));
			panelAbajo.add(getBtnVolver());
			panelAbajo.add(getPanelDifi());
		}
		return panelAbajo;
	}
	
	private JComboBox<String> getDificultad(){
		if(dificultad==null) {
			String[] ejemplo= {"Todo","0","1","2"}; //Ejemplo
			dificultad=new JComboBox<>(ejemplo);
			dificultad.setForeground(Color.BLACK);
			dificultad.setBackground(Color.WHITE);
			dificultad.addActionListener(new verRankingFiltrado());
		}
		return dificultad;
	}
	
	private JPanel getPanelDifi() {
		if(panelDifi==null) {
			panelDifi=new JPanel();
			panelDifi.setLayout(new FlowLayout());
			panelDifi.add(new JLabel("Nivel:"));
			panelDifi.add(getDificultad());
		}
		return panelDifi;
	}
	
	private JTabbedPane getPestanas() throws JSONException, SQLException {
		if(pestanas==null) {
			pestanas= new JTabbedPane();
			pestanas.add(getPanelGlobal(),"Global");
			pestanas.add(getPanelPersonal(),"Tú");
			pestanas.addChangeListener(new volverATodo());
		}
		return pestanas;
	}
	
	private JPanel	getPanelVac() {
		if(panelVac==null) {
			JPanel panelVac=new JPanel();
			panelVac.setLayout(new BorderLayout(0,0));
			panelVac.add(new JLabel("No existe ninguna puntuación cambia",JLabel.CENTER));
		}
		return panelVac;
	}
	
	
	//Listeners
	
	class verMenu implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			Menu frame=Menu.getMenu();
			frame.setVisible(true);
			dispose();	//Oculta la clasificacion
		}
	}
	
	class	verRankingFiltrado implements ActionListener{
		public void actionPerformed(ActionEvent a) {
				int p=pestanas.getSelectedIndex();
				if(p==0) {
					try {
						getPanelRankingG().removeAll();
						JPanel panelAux=new JPanel(); //Para fijar los títulos
						panelAux.setLayout(new GridLayout(1,4));
						panelRankingG.add(panelAux,BorderLayout.NORTH);
						panelRankingG.add(new JLabel());
						panelAux.add(new JLabel(""));
						panelAux.add(new JLabel("Nombre"));
						panelAux.add(new JLabel("Puntuación"));
						panelAux.add(new JLabel("Dificultad"));
						Gestor g=new Gestor();
						String s=dificultad.getSelectedItem().toString();
						Integer d=Integer.parseInt(s);
						JSONArray jl=g.getRankingGlobalFiltrado(d);
						if(jl.length()!=0) {
							JPanel panelPunt=new JPanel();
							panelPunt.setLayout(new GridLayout(jl.length(),3,50,50));//Panel de las puntuaciones
						for(Integer i=0;i<jl.length();i++) {
							JSONObject o=jl.getJSONObject(i);
							Integer pos=i+1;
							panelPunt.add(new JLabel(pos.toString() + "º"));
							panelPunt.add(new JLabel(o.getString("nombreUsuario")));
							panelPunt.add(new JLabel(String.valueOf(o.getInt("punt"))));
							panelPunt.add(new JLabel(String.valueOf(o.getInt("dif"))));
							
						}
						JScrollPane barra= new JScrollPane(panelPunt);
						barra.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
						getPanelRankingG().add(barra,BorderLayout.CENTER);
						getPanelRankingG().revalidate();
						getPanelRankingG().repaint();
						}else {
							JPanel panelVac=new JPanel();
							panelVac.setLayout(new BorderLayout(0,0));
							panelVac.add(new JLabel("No existe ninguna puntuación en este nivel",JLabel.CENTER));
							getPanelRankingG().add(panelVac,BorderLayout.CENTER);
							getPanelRankingG().revalidate();
							getPanelRankingG().repaint();
						}
						
					}catch(NumberFormatException | JSONException | SQLException e) { //Si "Todo"
						try {
							Gestor g=new Gestor();
							JSONArray jl=g.getRankingGlobal();
							if(jl.length()!=0) {
								JPanel panelPunt=new JPanel();
								panelPunt.setLayout(new GridLayout(jl.length(),3,50,50));//Panel de las puntuaciones	
								for(Integer i=0;i<jl.length();i++) {
									JSONObject o=jl.getJSONObject(i);
									Integer pos=i+1;									
									panelPunt.add(new JLabel(pos.toString() + "º"));
									panelPunt.add(new JLabel(o.getString("nombreUsuario")));
									panelPunt.add(new JLabel(String.valueOf(o.getInt("punt"))));
									panelPunt.add(new JLabel(String.valueOf(o.getInt("dif"))));
									
								}
								JScrollPane barra= new JScrollPane(panelPunt);
								barra.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
								getPanelRankingG().add(barra,BorderLayout.CENTER);
								getPanelRankingG().revalidate();
								getPanelRankingG().repaint();
							}else {
								JPanel panelVac=new JPanel();
								panelVac.setLayout(new BorderLayout(0,0));
								panelVac.add(new JLabel("No existe ninguna puntuación",JLabel.CENTER));
								getPanelRankingG().add(panelVac,BorderLayout.CENTER);
								getPanelRankingG().revalidate();
								getPanelRankingG().repaint();
							}
							
								
								
					}catch(JSONException | SQLException daads)	{}}	//Poner que ha habido un error
				}else { //Ranking personal seleccionado
					try {
					getPanelRankingP().removeAll();
					JPanel panelAux=new JPanel(); //Para fijar los títulos
					panelAux.setLayout(new GridLayout(1,4));
					panelRankingP.add(panelAux,BorderLayout.NORTH);
					panelRankingP.add(new JLabel());
					panelAux.add(new JLabel(""));
					panelAux.add(new JLabel("Nombre"));
					panelAux.add(new JLabel("Puntuación"));
					panelAux.add(new JLabel("Dificultad"));
					Gestor g=new Gestor();
					String s=dificultad.getSelectedItem().toString();
					Integer d=Integer.parseInt(s);
					JSONArray jl=g.getRankingPersonalFiltrado(d,g.getNombreUsuario()); //Cambiar por g.getusuario()
					if(jl.length()!=0) {
						JPanel panelPunt=new JPanel();
						panelPunt.setLayout(new GridLayout(jl.length(),3,50,50));//Panel de las puntuaciones
						panelPunt.setAlignmentY(TOP_ALIGNMENT);
						for(Integer i=0;i<jl.length();i++) {
							JSONObject o=jl.getJSONObject(i);
							Integer pos=i+1;
							panelPunt.add(new JLabel(pos.toString() + "º"));
							panelPunt.add(new JLabel(g.getNombreUsuario())); //Cambiar por g.getusuario()
							panelPunt.add(new JLabel(String.valueOf(o.getInt("punt"))));
							panelPunt.add(new JLabel(String.valueOf(o.getInt("dif"))));
					}	
						JScrollPane barra= new JScrollPane(panelPunt);
						barra.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
						getPanelRankingP().add(barra,BorderLayout.CENTER);
					getPanelRankingP().revalidate();
					getPanelRankingP().repaint();
					}else {
						JPanel panelVac=new JPanel();
						panelVac.setLayout(new BorderLayout(0,0));
						panelVac.add(new JLabel("No existe ninguna puntuación tuya en este nivel",JLabel.CENTER));
						getPanelRankingP().add(panelVac,BorderLayout.CENTER);
						getPanelRankingP().revalidate();
						getPanelRankingP().repaint();
					}
					}catch(NumberFormatException | JSONException | SQLException e) {//Si pulsa "todo"
					try {
						Gestor g=new Gestor();
						JSONArray jl=g.getRankingPersonal(g.getNombreUsuario()); //Cambiar por g.getusuario()
						if(jl.length()!=0) {
							JPanel panelPunt=new JPanel();
							panelPunt.setLayout(new GridLayout(jl.length(),3,50,50));//Panel de las puntuaciones
							for(Integer i=0;i<jl.length();i++) {
								JSONObject o=jl.getJSONObject(i);
								Integer pos=i+1;
								panelPunt.add(new JLabel(pos.toString() + "º"));
								panelPunt.add(new JLabel(g.getNombreUsuario())); //Cambiar por g.getusuario
								panelPunt.add(new JLabel(String.valueOf(o.getInt("punt"))));
								panelPunt.add(new JLabel(String.valueOf(o.getInt("dif"))));
								
							}
							JScrollPane barra= new JScrollPane(panelPunt);
							barra.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
							panelRankingP.add(barra);
							getPanelRankingP().revalidate();
							getPanelRankingP().repaint();
						}else {
							JPanel panelVac=new JPanel();
							panelVac.setLayout(new BorderLayout(0,0));
							panelVac.add(new JLabel("No existe ninguna puntuación tuya",JLabel.CENTER));
							getPanelRankingP().add(panelVac,BorderLayout.CENTER);
							getPanelRankingP().revalidate();
							getPanelRankingP().repaint();
						}
					}catch(JSONException | SQLException eev) {} //Poner que ha habido un error
					}
				}
		}
		
	}
	class volverATodo implements ChangeListener{
		public void stateChanged (ChangeEvent a) {
			getDificultad().setSelectedIndex(0);
		}
	}
	
}
