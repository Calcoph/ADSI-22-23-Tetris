package com.zetcode;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JSeparator;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);
		JLabel titulo = new JLabel("Tetrix");
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(titulo);
		contentPane.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton nuevaPartida = new JButton("Nueva Partida");
		nuevaPartida.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(nuevaPartida);
		contentPane.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton cargarPartida = new JButton("Cargar Partida");
		cargarPartida.setAlignmentX(CENTER_ALIGNMENT);
		cargarPartida.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Menu.getMenu().close();
				Gestor.getGestor().cargarPartida();
				Tetris.getTetris().start();
				
			}
		});
		contentPane.add(cargarPartida);
		contentPane.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton personalizarMapa = new JButton("Personalizar Mapa");
		personalizarMapa.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(personalizarMapa);
		contentPane.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton verRanking = new JButton("Ver Ranking");
		verRanking.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(verRanking);
		contentPane.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton verPremios = new JButton("Ver Premios");
		verPremios.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(verPremios);
		contentPane.add(Box.createRigidArea(new Dimension(5, 0)));
		
	}
	
	public static void main(String[] args) {
    	Menu.getMenu().start();
    }
}
