package com.zetcode;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Cursor;

public class IU_Identificacion extends JFrame {

	private JPanel contentPane;
	private JPasswordField pwdF;
	private JTextField txtUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_Identificacion frame = new IU_Identificacion();
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
	public IU_Identificacion() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//PANEL SUPERIOR
		JPanel panelSuperior = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelSuperior.getLayout();
		flowLayout.setVgap(15);
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		
		JLabel lblInicioDeSesion = new JLabel("INICIO DE SESION");
		lblInicioDeSesion.setFont(new Font("Dialog", Font.BOLD, 20));
		lblInicioDeSesion.setForeground(new Color(153,0,153));
		panelSuperior.add(lblInicioDeSesion);
		
		//PANEL CENTRAL
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[]{30, 450, 450, 44, 0};
		gbl_panelCentral.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelCentral.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentral.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panelCentral);
		
		JLabel lblUsuario = new JLabel("Usuario");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 0;
		panelCentral.add(lblUsuario, gbc_lblUsuario);
		
		txtUsuario = new JTextField();
		GridBagConstraints gbc_txtUsuario = new GridBagConstraints();
		gbc_txtUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsuario.gridx = 2;
		gbc_txtUsuario.gridy = 0;
		panelCentral.add(txtUsuario, gbc_txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrasena");
		GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
		gbc_lblContrasea.anchor = GridBagConstraints.EAST;
		gbc_lblContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrasea.gridx = 1;
		gbc_lblContrasea.gridy = 1;
		panelCentral.add(lblContrasea, gbc_lblContrasea);
		
		pwdF = new JPasswordField();
		pwdF.addKeyListener(new KeyAdapter() { // Funcion del passwordField al pulsar enter
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					iniciarSesion();
				}
			}
		});
		GridBagConstraints gbc_pwdF = new GridBagConstraints();
		gbc_pwdF.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdF.insets = new Insets(0, 0, 5, 5);
		gbc_pwdF.gridx = 2;
		gbc_pwdF.gridy = 1;
		panelCentral.add(pwdF, gbc_pwdF);
			
		JButton btnNewButton = new JButton("Registrarse");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addKeyListener(new KeyAdapter() { // Funcion del boton registrarse al pulsar enter sobre el
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					registrarse();
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() { // Funcion del boton registrarse al pulsar sobre el
			public void actionPerformed(ActionEvent e) {
				registrarse();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		panelCentral.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Iniciar Sesión");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.addKeyListener(new KeyAdapter() { // Funcion del boton iniciar sesion al pulsar enter sobre el 
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					iniciarSesion();
				}
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() { // Funcion del boton iniciar sesion al pulsar sobre el 
			public void actionPerformed(ActionEvent e) {
				iniciarSesion();
			}
		});

		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 12));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 2;
		panelCentral.add(btnNewButton_1, gbc_btnNewButton_1);
		
		
		//PANEL INFERIOR
		JPanel panelInferior = new JPanel();
		contentPane.add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
		
		JButton btnCambiarContrasea = new JButton("Cambiar contrasena");
		btnCambiarContrasea.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCambiarContrasea.addKeyListener(new KeyAdapter() { // Funcion del boton cambiar contrasena al pulsar enter sobre el
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					cambiarContrasena();
				}
			}
		});
		btnCambiarContrasea.addActionListener(new ActionListener() { // Funcion del boton cambiar contrasena al pulsar sobre el
			public void actionPerformed(ActionEvent e) {
				cambiarContrasena();
			}
		});
		panelInferior.add(btnCambiarContrasea);
		
		JButton btnRecuperarContrasea = new JButton("Recuperar contrasena");
		btnRecuperarContrasea.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRecuperarContrasea.addKeyListener(new KeyAdapter() { // Funcion del boton recuperar contrasena al pulsar enter sobre el
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					recuperarContrasena();
				}
			}
		});
		btnRecuperarContrasea.addActionListener(new ActionListener() { // Funcion del boton recuperar contrasena al pulsar sobre el
			public void actionPerformed(ActionEvent e) {
				recuperarContrasena();
			}
		});
		panelInferior.add(btnRecuperarContrasea);
	}
	
	public void ocultar() { // Metodo para cerrar ventana actual de tipo IU_Identificacion
		this.setVisible(false);
	}
	
	public void registrarse() { // Metodo para abrir ventana de registro
		IU_Registrarse iuRegistrarse = new IU_Registrarse();
		ocultar();
		iuRegistrarse.setVisible(true);
	}
	
	public void iniciarSesion() { // Metodo para iniciar sesion, en el que se diferencia entre si es admin o si no lo es o no existe para abrir un menu u otro
		Gestor GestorPrincipal= new Gestor();
		String nombreUsuario = txtUsuario.getText();
		String pwd = String.valueOf(pwdF.getPassword());
		boolean existe = GestorPrincipal.identificarse(nombreUsuario, pwd);
		if (existe && (nombreUsuario.equals("admin"))) {
			IU_MenuAdmin menuAdmin = new IU_MenuAdmin();
			ocultar();
			menuAdmin.setVisible(true);
		} else if (!existe) {
			
		} else {
			Menu menu = new Menu();
			ocultar();	
			menu.setVisible(true);
		}
	}
	
	public void cambiarContrasena() { // Metodo para abrir ventana cambiar contrasena
		IU_CambiarContrasena iuCambiarContrasena = new IU_CambiarContrasena();
		ocultar();
		iuCambiarContrasena.setVisible(true);
	}
	
	public void recuperarContrasena() { // Metodo para abrir ventana recuperar contrasena
		IU_RecuperarContrasena iuRecuperarContrasena = new IU_RecuperarContrasena();
		ocultar();
		iuRecuperarContrasena.setVisible(true);
	}
}
