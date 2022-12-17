package com.zetcode;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Cursor;

public class IU_EliminarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_EliminarUsuario frame = new IU_EliminarUsuario();
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
	public IU_EliminarUsuario() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 420, 260);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(153, 153, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// PANEL SUPERIOR
		JPanel panelSuperior = new JPanel();
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
		
		JLabel lblPersonalizacin = new JLabel("ELIMINAR USUARIO");
		panelSuperior.add(lblPersonalizacin);
		lblPersonalizacin.setFont(new Font("Dialog", Font.BOLD, 20));
		lblPersonalizacin.setForeground(new Color(153, 0, 153));
		
		// PANEL CENTRAL
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 15));
		
		JLabel lblIndicaElUsuario = new JLabel("Indica el usuario que desea eliminar:\n");
		panelCentral.add(lblIndicaElUsuario);
		
		txtUsuario = new JTextField();
		panelCentral.add(txtUsuario);
		txtUsuario.setName("Usuario");
		txtUsuario.setToolTipText("");
		txtUsuario.setColumns(10);
		
		// PANEL INFERIOR
		JPanel panelInferior = new JPanel();
		contentPane.add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 115, 5));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVolver.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				volver();
			}
		});
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		panelInferior.add(btnVolver);
		
		JButton btnGuardarCambios = new JButton("Eliminar");
		btnGuardarCambios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardarCambios.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				eliminar();
			}
		});
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		panelInferior.add(btnGuardarCambios);
	}
	
	public void ocultar() {
		this.setVisible(false);
	}
	
	public void volver() {
		IU_MenuAdmin iuMenuAdmin = new IU_MenuAdmin();
		iuMenuAdmin.setVisible(true);
		ocultar();
	}
	
	public void eliminar() {
		Gestor GPrincipal = new Gestor();
		String txtNombreUsuario = txtUsuario.getText();
		GPrincipal.eliminarUsuario(txtNombreUsuario);
	}
}
