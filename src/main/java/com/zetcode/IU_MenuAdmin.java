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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

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
		setBounds(0, 0, 415, 145);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(153, 153, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSuperior = new JPanel();
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblTitulo = new JLabel("ADMIN TETRIX");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 20));
		panelSuperior.add(lblTitulo);
		lblTitulo.setForeground(new Color(153, 0, 153));
		panelSuperior.add(lblTitulo);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JButton btnEliminarUsuario = new JButton("Eliminar usuario");
		btnEliminarUsuario.addKeyListener(new KeyAdapter() { // Funcion del boton eliminar usuario al pulsar enter sobre el 
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					elimiarUsuario();
				}
			}
		});
<<<<<<< HEAD
		btnEliminarUsuario.addActionListener(new ActionListener() { // Funcion del boton eliminar usuario al pulsar sobre el 
=======
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
>>>>>>> 963058a348987f620d25c21de12561f054a2f723
			public void actionPerformed(ActionEvent e) {
				elimiarUsuario();
			}
		});
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelCentral.createSequentialGroup()
					.addContainerGap(133, Short.MAX_VALUE)
					.addComponent(btnEliminarUsuario)
					.addGap(124))
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(23)
					.addComponent(btnEliminarUsuario)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		panelCentral.setLayout(gl_panelCentral);

	}
	public void ocultar() { // Metodo para cerrar ventana actual de tipo IU_MenuAdmin
		this.setVisible(false);
	}
	
<<<<<<< HEAD
	public void elimiarUsuario() { // Metodo para abrir ventana IU_EliminarUsuario
=======
	public void nuevaPartida() {
		
	}
	
	public void cargarPartida() {
		
	}
	
	public void personalizarPantalla() {
		
	}
	
	public void verRanking() {
		
	}
	
	public void verPremios() {
		MenuPremios premios = new MenuPremios();
		premios.setLocationRelativeTo(null);
		premios.setVisible(true);
		dispose();
	}
	
	public void elimiarUsuario() {
>>>>>>> 963058a348987f620d25c21de12561f054a2f723
		IU_EliminarUsuario iuEliminarUsuario = new IU_EliminarUsuario();
		ocultar();
		System.out.println("eliminar");
		iuEliminarUsuario.setVisible(true);
	}
}