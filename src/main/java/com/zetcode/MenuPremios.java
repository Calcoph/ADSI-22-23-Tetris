package com.zetcode;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;
import org.json.JSONArray;

import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;

public class MenuPremios extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPremios frame = new MenuPremios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPremios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 593);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 3, 0, 0));

		JSONArray premios = Gestor.obtenerPremios();
		for (Object premio_ : premios) {
			JSONObject premio = (JSONObject) premio_;
			String nombrePremio = premio.getString("nombrePremio");
			Integer progreso = premio.getInt("progreso");
			Integer progresoMax = premio.getInt("progresoMax");

			int progresoFinal = progreso * 100 / progresoMax;
			if (progresoFinal > 100) {
				progresoFinal = 100;
			}

			JPanel panel_1 = new JPanel();
			panel.add(panel_1);
			panel_1.setLayout(null);

			JButton btnNewButton = new JButton("");
			if (progresoFinal == 100) {
				btnNewButton.setIcon(new ImageIcon("resources/premio.png"));
			} else {
				btnNewButton.setIcon(new ImageIcon("resources/premio sin completar.png"));
			}
			btnNewButton.setBounds(34, 10, 96, 96);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					PremioDescripcion premioDescripcion = new PremioDescripcion(nombrePremio);
					premioDescripcion.setLocationRelativeTo(null);
					premioDescripcion.setVisible(true);
					dispose();
				}
			});
			panel_1.add(btnNewButton);

			JProgressBar progressBar = new JProgressBar();
			progressBar.setBounds(20, 110, 124, 14);

			progressBar.setValue(progresoFinal);
			panel_1.add(progressBar);
		}

		JPanel panel_inferior = new JPanel();
		contentPane.add(panel_inferior, BorderLayout.SOUTH);
		panel_inferior.setLayout(new BorderLayout(0, 0));

		JPanel panel_volver = new JPanel();
		panel_inferior.add(panel_volver, BorderLayout.EAST);

		JButton botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_volver.add(botonVolver);

		Component HorizontalStrut = Box.createHorizontalStrut(20);
		panel_volver.add(HorizontalStrut);
	}
}
