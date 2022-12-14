package com.zetcode;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;

public class menupremios extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menupremios frame = new menupremios();
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
	public menupremios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("documentacion/funcionalidad 6/premio.png"));
		btnNewButton.setBounds(34, 56, 195, 184);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon("documentacion/funcionalidad 6/premio.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(241, 56, 195, 184);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon("documentacion/funcionalidad 6/premio.png"));
		btnNewButton_2.setBounds(454, 56, 195, 184);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setIcon(new ImageIcon("documentacion/funcionalidad 6/premio.png"));
		btnNewButton_3.setBounds(34, 283, 195, 184);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setIcon(new ImageIcon("documentacion/funcionalidad 6/premio.png"));
		btnNewButton_4.setBounds(241, 283, 195, 184);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setIcon(new ImageIcon("documentacion/funcionalidad 6/premio.png"));
		btnNewButton_5.setBounds(454, 283, 195, 184);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Volver");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_6.setBounds(588, 508, 97, 25);
		contentPane.add(btnNewButton_6);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(100);
		progressBar.setBounds(44, 253, 146, 14);
		contentPane.add(progressBar);
		
		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setValue(100);
		progressBar_1.setBounds(251, 253, 146, 14);
		contentPane.add(progressBar_1);
		
		JProgressBar progressBar_2 = new JProgressBar();
		progressBar_2.setValue(90);
		progressBar_2.setBounds(464, 253, 146, 14);
		contentPane.add(progressBar_2);
		
		JProgressBar progressBar_3 = new JProgressBar();
		progressBar_3.setBounds(44, 483, 146, 14);
		contentPane.add(progressBar_3);
		
		JProgressBar progressBar_4 = new JProgressBar();
		progressBar_4.setValue(35);
		progressBar_4.setBounds(251, 483, 146, 14);
		contentPane.add(progressBar_4);
		
		JProgressBar progressBar_5 = new JProgressBar();
		progressBar_5.setValue(10);
		progressBar_5.setBounds(464, 481, 146, 14);
		contentPane.add(progressBar_5);
	}

}
