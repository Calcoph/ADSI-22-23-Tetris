package com.zetcode;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuDificultad extends JFrame {
	
	private JPanel contentPane;
	private PropertyChangeSupport support;
	public static int dificultad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuDificultad frame = new MenuDificultad();
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
	public MenuDificultad() {
		this.setVisible(true);
		setBackground(new Color(221, 160, 221));
		initialize();
		support = new PropertyChangeSupport(this);
	}
	
	public void initialize() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 271);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 153));
		contentPane.setForeground(new Color(153, 153, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel titulo = new JPanel();
		contentPane.add(titulo, BorderLayout.NORTH);
		titulo.setBackground(new Color(153, 153, 153));
		
		JLabel lblDificultad = new JLabel("Elige el nivel");
		lblDificultad.setForeground(new Color(153, 0, 153));
		lblDificultad.setFont(new Font("Dialog", Font.BOLD, 30));
		titulo.add(lblDificultad);
		
		JPanel Contenido = new JPanel();
		contentPane.add(Contenido, BorderLayout.CENTER);
		Contenido.setBackground(new Color(153, 153, 153));
		
		JLabel lblSelectorDificultad = new JLabel("Dificutad");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0. Fácil", "1. Normal", "2. Difícil"}));
		comboBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
						if(comboBox.getSelectedItem().equals("0. Facil")) {
							dificultad = 0;
						} else if(comboBox.getSelectedItem().equals("1. Normal")) {
							dificultad = 1;
						} else if(comboBox.getSelectedItem().equals("2. Difícil")) {
							dificultad = 2;
						}
                }
			});
		
		JLabel lblinfo = new JLabel("<html>0. Fácil - Tablero alto y ancho y velocidad de caida de los bloques lenta.<p>1. Normal - Tablero con altura reducida y velocidad de caida de los bloques aumentada.<p>2. Difícil - Altura y anchura del tablero reducida y velocidad de caida de los bloques aún más rápida<html>");
		lblinfo.setToolTipText("");
		GroupLayout gl_Contenido = new GroupLayout(Contenido);
		gl_Contenido.setHorizontalGroup(
			gl_Contenido.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Contenido.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_Contenido.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Contenido.createSequentialGroup()
							.addComponent(lblinfo, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_Contenido.createSequentialGroup()
							.addComponent(lblSelectorDificultad)
							.addGap(59)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(123))))
		);
		gl_Contenido.setVerticalGroup(
			gl_Contenido.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Contenido.createSequentialGroup()
					.addComponent(lblinfo, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
					.addGap(29)
					.addGroup(gl_Contenido.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectorDificultad)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		Contenido.setLayout(gl_Contenido);
		
		JPanel Jugar = new JPanel();
		contentPane.add(Jugar, BorderLayout.SOUTH);
		Jugar.setBackground(new Color(153, 153, 153));
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tetris jugar = new Tetris();
				jugar.setVisible(true);
				MenuDificultad.this.dispose();
			}
		});
		btnJugar.setHorizontalAlignment(SwingConstants.RIGHT);
		GroupLayout gl_Jugar = new GroupLayout(Jugar);
		gl_Jugar.setHorizontalGroup(
			gl_Jugar.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_Jugar.createSequentialGroup()
					.addGap(177)
					.addComponent(btnJugar)
					.addContainerGap(188, Short.MAX_VALUE))
		);
		gl_Jugar.setVerticalGroup(
			gl_Jugar.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Jugar.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnJugar))
		);
		Jugar.setLayout(gl_Jugar);
	}
	
	public void addObserver(PropertyChangeListener pList) {
		support.addPropertyChangeListener(pList);
	}
}
