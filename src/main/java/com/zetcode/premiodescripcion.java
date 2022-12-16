package com.zetcode;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JProgressBar;

public class premiodescripcion extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					premiodescripcion frame = new premiodescripcion();
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
	public premiodescripcion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("documentacion/funcionalidad 6/premio.png"));
		btnNewButton.setBounds(12, 42, 195, 176);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Premio #4");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel.setBounds(289, 60, 221, 77);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Aguanta 3 minutos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel_1.setBounds(250, 165, 260, 37);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("en 10 partidas seguidas");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel_2.setBounds(250, 204, 312, 33);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.setBounds(465, 276, 97, 25);
		contentPane.add(btnNewButton_1);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(75);
		progressBar.setBounds(22, 231, 146, 14);
		contentPane.add(progressBar);
	}
}
