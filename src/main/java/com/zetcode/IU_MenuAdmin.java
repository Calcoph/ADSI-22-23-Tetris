package com.zetcode;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IU_MenuAdmin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_MenuAdmin frame = new IU_MenuAdmin();
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
	public IU_MenuAdmin() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 410, 380);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(153, 153, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSuperior = new JPanel();
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblTitulo = new JLabel("TETRIX ADMIN");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 20));
		panelSuperior.add(lblTitulo);
		lblTitulo.setForeground(new Color(153, 0, 153));
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[] {95, 0, 95, 0};
		gbl_panelCentral.rowHeights = new int[]{-19, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCentral.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentral.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panelCentral);
		
		JButton btnNuevaPartida = new JButton("Nueva Partida");
		btnNuevaPartida.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				nuevaPartida();
			}
		});
		btnNuevaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevaPartida();
			}
		});
		btnNuevaPartida.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_btnNuevaPartida = new GridBagConstraints();
		gbc_btnNuevaPartida.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNuevaPartida.insets = new Insets(0, 0, 5, 5);
		gbc_btnNuevaPartida.gridx = 1;
		gbc_btnNuevaPartida.gridy = 1;
		panelCentral.add(btnNuevaPartida, gbc_btnNuevaPartida);
		
		JButton btnCargarPartida = new JButton("Cargar Partida");
		btnCargarPartida.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				cargarPartida();
			}
		});
		btnCargarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarPartida();
			}
		});
		btnCargarPartida.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_btnCargarPartida = new GridBagConstraints();
		gbc_btnCargarPartida.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCargarPartida.insets = new Insets(0, 0, 5, 5);
		gbc_btnCargarPartida.gridx = 1;
		gbc_btnCargarPartida.gridy = 2;
		panelCentral.add(btnCargarPartida, gbc_btnCargarPartida);
		
		JButton btnPersonalizarPantalla = new JButton("Personalizar Pantalla");
		btnPersonalizarPantalla.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				personalizarPantalla();
			}
		});
		btnPersonalizarPantalla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personalizarPantalla();
			}
		});
		btnPersonalizarPantalla.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_btnPersonalizarPantalla = new GridBagConstraints();
		gbc_btnPersonalizarPantalla.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPersonalizarPantalla.insets = new Insets(0, 0, 5, 5);
		gbc_btnPersonalizarPantalla.gridx = 1;
		gbc_btnPersonalizarPantalla.gridy = 3;
		panelCentral.add(btnPersonalizarPantalla, gbc_btnPersonalizarPantalla);
		
		JButton btnVerRanking = new JButton("Ver Ranking");
		btnVerRanking.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				verRanking();
			}
		});
		btnVerRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verRanking();
			}
		});
		btnVerRanking.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_btnVerRanking = new GridBagConstraints();
		gbc_btnVerRanking.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnVerRanking.insets = new Insets(0, 0, 5, 5);
		gbc_btnVerRanking.gridx = 1;
		gbc_btnVerRanking.gridy = 4;
		panelCentral.add(btnVerRanking, gbc_btnVerRanking);
		
		JButton btnVerPremios = new JButton("Ver Premios");
		btnVerPremios.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				verPremios();
			}
		});
		btnVerPremios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verPremios();
			}
		});
		btnVerPremios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_btnVerPremios = new GridBagConstraints();
		gbc_btnVerPremios.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnVerPremios.insets = new Insets(0, 0, 5, 5);
		gbc_btnVerPremios.gridx = 1;
		gbc_btnVerPremios.gridy = 5;
		panelCentral.add(btnVerPremios, gbc_btnVerPremios);
		
		JButton btnEliminarusuario = new JButton("EliminarUsuario");
		btnEliminarusuario.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				elimiarUsuario();
			}
		});
		btnEliminarusuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elimiarUsuario();
			}
		});
		btnEliminarusuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_btnEliminarusuario = new GridBagConstraints();
		gbc_btnEliminarusuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEliminarusuario.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminarusuario.gridx = 1;
		gbc_btnEliminarusuario.gridy = 6;
		panelCentral.add(btnEliminarusuario, gbc_btnEliminarusuario);
	}
	
	public void ocultar() {
		this.setVisible(false);
	}
	
	public void nuevaPartida() {
		
	}
	
	public void cargarPartida() {
		
	}
	
	public void personalizarPantalla() {
		
	}
	
	public void verRanking() {
		
	}
	
	public void verPremios() {
		
	}
	
	public void elimiarUsuario() {
		IU_EliminarUsuario iuEliminarUsuario = new IU_EliminarUsuario();
		iuEliminarUsuario.setVisible(true);
		ocultar();
	}
}
