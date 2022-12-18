package com.zetcode;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Cursor;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IU_Registrarse extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtEmail;
	private JPasswordField pwdF;
	private JPasswordField pwdFRepeat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_Registrarse frame = new IU_Registrarse();
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
	public IU_Registrarse() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 430, 310);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Panel SUPERIOR
		
		JPanel panelSuperior = new JPanel();
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 4));
		
		JLabel lblTitulo = new JLabel("REGISTRARSE");
		panelSuperior.add(lblTitulo);
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTitulo.setForeground(new Color(153, 0, 153));
		
		// Panel CENTRAL
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[] {79, 113, 20, 215, 30, 0};
		gbl_panelCentral.rowHeights = new int[] {11, 35, 35, 35, 35, 35, 5};
		gbl_panelCentral.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentral.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panelCentral);
		
		JLabel lblUsuario = new JLabel("Usuario");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.gridwidth = 2;
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 0;
		gbc_lblUsuario.gridy = 1;
		panelCentral.add(lblUsuario, gbc_lblUsuario);
		
		txtUsuario = new JTextField();
		GridBagConstraints gbc_txtUsuario = new GridBagConstraints();
		gbc_txtUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsuario.gridx = 3;
		gbc_txtUsuario.gridy = 1;
		panelCentral.add(txtUsuario, gbc_txtUsuario);
		txtUsuario.setColumns(5);
		
		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.gridwidth = 2;
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 2;
		panelCentral.add(lblEmail, gbc_lblEmail);
		
		txtEmail = new JTextField();
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 3;
		gbc_txtEmail.gridy = 2;
		panelCentral.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblAviso = new JLabel("<html>Email que EXISTA, para poder recuperar  contrasena en un futuro!!<html>");
		lblAviso.setForeground(Color.RED);
		GridBagConstraints gbc_lblAviso = new GridBagConstraints();
		gbc_lblAviso.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAviso.anchor = GridBagConstraints.NORTH;
		gbc_lblAviso.gridwidth = 3;
		gbc_lblAviso.insets = new Insets(0, 0, 5, 5);
		gbc_lblAviso.gridx = 1;
		gbc_lblAviso.gridy = 3;
		panelCentral.add(lblAviso, gbc_lblAviso);
		
		JLabel lblContrasea = new JLabel("Contrasena");
		GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
		gbc_lblContrasea.gridwidth = 2;
		gbc_lblContrasea.anchor = GridBagConstraints.EAST;
		gbc_lblContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrasea.gridx = 0;
		gbc_lblContrasea.gridy = 4;
		panelCentral.add(lblContrasea, gbc_lblContrasea);
		
		pwdF = new JPasswordField();
		GridBagConstraints gbc_pwdF = new GridBagConstraints();
		gbc_pwdF.insets = new Insets(0, 0, 5, 5);
		gbc_pwdF.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdF.gridx = 3;
		gbc_pwdF.gridy = 4;
		panelCentral.add(pwdF, gbc_pwdF);
		
		JLabel lblRepetirContrasea = new JLabel("Repetir Contrasena");
		GridBagConstraints gbc_lblRepetirContrasea = new GridBagConstraints();
		gbc_lblRepetirContrasea.gridwidth = 2;
		gbc_lblRepetirContrasea.anchor = GridBagConstraints.EAST;
		gbc_lblRepetirContrasea.insets = new Insets(0, 0, 0, 5);
		gbc_lblRepetirContrasea.gridx = 0;
		gbc_lblRepetirContrasea.gridy = 5;
		panelCentral.add(lblRepetirContrasea, gbc_lblRepetirContrasea);
		
		pwdFRepeat = new JPasswordField();
		pwdFRepeat.addKeyListener(new KeyAdapter() { // Funcion del passwordField al pulsar enter
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					registrarse();
				}
			}
		});
		GridBagConstraints gbc_pwdFRepeat = new GridBagConstraints();
		gbc_pwdFRepeat.insets = new Insets(0, 0, 0, 5);
		gbc_pwdFRepeat.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdFRepeat.gridx = 3;
		gbc_pwdFRepeat.gridy = 5;
		panelCentral.add(pwdFRepeat, gbc_pwdFRepeat);
		
		//Panel INFERIOR
		
		JPanel panelInferior = new JPanel();
		contentPane.add(panelInferior, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("Volver");
		// CON EL TECLADO
		btnVolver.addKeyListener(new KeyAdapter() { // Funcion del boton volver al pulsar enter sobre el 
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					volver();
				}			
			}
		});
		// CON EL MOUSE
		btnVolver.addActionListener(new ActionListener() { // Funcion del boton volver al pulsar sobre el 
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		JButton btnRegistrarse = new JButton("Registrarse");
		// CON EL TECLADO
		btnRegistrarse.addKeyListener(new KeyAdapter() { // Funcion del boton registrarse al pulsar enter sobre el
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					registrarse();
				}			}
		});
		// CON EL MOUSE
		btnRegistrarse.addActionListener(new ActionListener() { // Funcion del boton registrarse al pulsar sobre el 
			public void actionPerformed(ActionEvent e) {
				registrarse();
			}
		});
		btnRegistrarse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
		panelInferior.add(btnVolver);
		panelInferior.add(btnRegistrarse);
	}
	
	public void ocultar() { // Metodo para cerrar ventana actual de tipo IU_Registrarse
		this.setVisible(false);
	}
	
	public void volver() { // Metodo para volver a la ventana anterior
		IU_Identificacion iuIdentificacion = new IU_Identificacion();
		ocultar();
		iuIdentificacion.setVisible(true);
	}
	
	public void registrarse() { // Metodo para registrarse
		Gestor GPrincipal = new Gestor();
		String usuario = txtUsuario.getText();
		String email = txtEmail.getText();
		String pwd1 = String.valueOf(pwdF.getPassword());
		String pwd2 = String.valueOf(pwdFRepeat.getPassword());
		int exito = GPrincipal.registrarse(usuario, email, pwd1, pwd2);
		if (exito == 1) {
			IU_Identificacion iuId = new IU_Identificacion();
			ocultar();
			iuId.setVisible(true);
		}
	}
}
