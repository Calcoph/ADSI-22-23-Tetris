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

public class premiosobtenidos extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					premiosobtenidos frame = new premiosobtenidos();
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
	public premiosobtenidos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("documentacion/funcionalidad 6/premio.png"));
		btnNewButton.setBounds(12, 42, 128, 128);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Premios obtenidos");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(200, 38, 290, 77);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("· Premio #1");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel_1.setBounds(20, 165, 260, 37);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("· Premio #3");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel_2.setBounds(20, 204, 312, 33);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Ok");
		btnNewButton_1.setBounds(465, 276, 97, 25);
		contentPane.add(btnNewButton_1);
	}
}
