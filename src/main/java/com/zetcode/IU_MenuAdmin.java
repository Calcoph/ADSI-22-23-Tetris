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
		btnEliminarUsuario.addActionListener(new ActionListener() { // Funcion del boton eliminar usuario al pulsar sobre el 
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
	
	public void elimiarUsuario() { // Metodo para abrir ventana IU_EliminarUsuario
		IU_EliminarUsuario iuEliminarUsuario = new IU_EliminarUsuario();
		ocultar();
		System.out.println("eliminar");
		iuEliminarUsuario.setVisible(true);
	}
}