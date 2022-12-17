package com.zetcode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JSeparator;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import org.json.JSONException;

import javax.swing.BorderFactory;

public class Menu extends JFrame {

	private JPanel contentPane;
	private static Menu miMenu = null;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					miMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void close() {
		miMenu.dispose();
	}
	
	public static Menu getMenu() {
		if (miMenu == null) {
			miMenu = new Menu();
		}
		return miMenu;
	}

	/**
	 * Create the frame.
	 */
	private Menu() {

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(0, 0, 450, 350);
			setLocationRelativeTo(null);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout(0, 0));
			
			JPanel panel_1 = new JPanel();
			contentPane.add(panel_1, BorderLayout.CENTER);
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[] {95, 0, 95, 0};
			gbl_panel_1.rowHeights = new int[] {30, 0, 0, 0, 0, 0, 30};
			gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panel_1.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0};
			panel_1.setLayout(gbl_panel_1);
			
			JButton btnCargarPartida = new JButton("Cargar Partida");
			GridBagConstraints gbc_btnCargarPartida = new GridBagConstraints();
			gbc_btnCargarPartida.anchor = GridBagConstraints.NORTH;
			gbc_btnCargarPartida.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnCargarPartida.insets = new Insets(0, 0, 5, 5);
			gbc_btnCargarPartida.gridx = 1;
			gbc_btnCargarPartida.gridy = 1;
			panel_1.add(btnCargarPartida, gbc_btnCargarPartida);
			
			JButton btnPersonalizarPantalla = new JButton("Personalizar Pantalla");
			GridBagConstraints gbc_btnPersonalizarPantalla = new GridBagConstraints();
			gbc_btnPersonalizarPantalla.anchor = GridBagConstraints.NORTH;
			gbc_btnPersonalizarPantalla.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnPersonalizarPantalla.insets = new Insets(0, 0, 5, 5);
			gbc_btnPersonalizarPantalla.gridx = 1;
			gbc_btnPersonalizarPantalla.gridy = 2;
			panel_1.add(btnPersonalizarPantalla, gbc_btnPersonalizarPantalla);
			
			JButton btnVerRanking = new JButton("Ver Ranking");
			GridBagConstraints gbc_btnVerRanking = new GridBagConstraints();
			gbc_btnVerRanking.anchor = GridBagConstraints.NORTH;
			gbc_btnVerRanking.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnVerRanking.insets = new Insets(0, 0, 5, 5);
			gbc_btnVerRanking.gridx = 1;
			gbc_btnVerRanking.gridy = 3;
			panel_1.add(btnVerRanking, gbc_btnVerRanking);
			btnVerRanking.addActionListener(new verClasificacion());
			
			JButton btnVerPremios = new JButton("Ver Premios");
			GridBagConstraints gbc_btnVerPremios = new GridBagConstraints();
			gbc_btnVerPremios.anchor = GridBagConstraints.NORTH;
			gbc_btnVerPremios.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnVerPremios.insets = new Insets(0, 0, 5, 5);
			gbc_btnVerPremios.gridx = 1;
			gbc_btnVerPremios.gridy = 4;
			panel_1.add(btnVerPremios, gbc_btnVerPremios);
			
			JButton btnNuevaPartida = new JButton("Nueva Partida");
			GridBagConstraints gbc_btnNuevaPartida = new GridBagConstraints();
			gbc_btnNuevaPartida.anchor = GridBagConstraints.NORTH;
			gbc_btnNuevaPartida.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnNuevaPartida.insets = new Insets(0, 0, 5, 5);
			gbc_btnNuevaPartida.gridx = 1;
			gbc_btnNuevaPartida.gridy = 5;
			panel_1.add(btnNuevaPartida, gbc_btnNuevaPartida);
			btnNuevaPartida.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MenuDificultad abrirDificultad = new MenuDificultad();
					abrirDificultad.setVisible(true);
					Menu.this.dispose();
				}
			});
			
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.NORTH);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			JLabel titulo = new JLabel("TETRIX");
			panel.add(titulo);
			titulo.setFont(new Font("Dialog", Font.BOLD, 20));
			titulo.setAlignmentX(CENTER_ALIGNMENT);
			titulo.setForeground(new Color(153, 0, 153));
		}
			
			public void actionPerformed(ActionEvent e) {
				Menu.getMenu().close();
				Gestor g=new Gestor();
				g.cargarPartida();
				Tetris.getTetris().start();
				
			}

	
	
	public static void main(String[] args) {
    	Menu.getMenu().start();
    }
	
	//Listener
	
		class verClasificacion implements ActionListener{
				public void actionPerformed(ActionEvent a) {
					Clasificacion frame;
					try {
						frame = new Clasificacion();
						frame.setVisible(true);
						Menu.getMenu().close();
					} catch (JSONException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
		}
}
