package com.zetcode;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Color;

public class MenuDificultad extends JFrame {

	private JPanel contentPane;
	private JPanel Título;
	private JPanel Contenido;
	private JPanel Volver;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JComboBox comboBox;
	private JButton btnNewButton;
	private JTextPane txtpnModificaLaDificultad;
	public int dificultad;
	private PropertyChangeSupport support;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public MenuDificultad() {
		initialize();
		support = new PropertyChangeSupport(this);
	}
		
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 210, 459, 364);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 130, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getTítulo(), BorderLayout.NORTH);
		contentPane.add(getContenido(), BorderLayout.CENTER);
		contentPane.add(getVolver(), BorderLayout.SOUTH);
		setTitle("Dificultad");
	}

	public void addObserver(PropertyChangeListener pList) {
		support.addPropertyChangeListener(pList);
	}

	private JPanel getTítulo() {
		if (Título == null) {
			Título = new JPanel();
			Título.setBackground(new Color(255, 218, 185));
			Título.add(getLblNewLabel());
		}
		return Título;
	}
	private JPanel getContenido() {
		if (Contenido == null) {
			Contenido = new JPanel();
			Contenido.setBackground(new Color(255, 218, 185));
			GroupLayout gl_Contenido = new GroupLayout(Contenido);
			gl_Contenido.setHorizontalGroup(
				gl_Contenido.createParallelGroup(Alignment.TRAILING)
					.addGroup(Alignment.LEADING, gl_Contenido.createSequentialGroup()
						.addGap(70)
						.addComponent(getLblNewLabel_1(), GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
						.addGap(67)
						.addComponent(getComboBox(), 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(84))
					.addGroup(Alignment.LEADING, gl_Contenido.createSequentialGroup()
						.addGap(92)
						.addComponent(getTxtpnModificaLaDificultad(), GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(93, Short.MAX_VALUE))
			);
			gl_Contenido.setVerticalGroup(
				gl_Contenido.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_Contenido.createSequentialGroup()
						.addGap(22)
						.addComponent(getTxtpnModificaLaDificultad(), GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(gl_Contenido.createParallelGroup(Alignment.BASELINE)
							.addComponent(getComboBox(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(getLblNewLabel_1(), GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
						.addContainerGap())
			);
			Contenido.setLayout(gl_Contenido);
		}
		return Contenido;
	}
	private JPanel getVolver() {
		if (Volver == null) {
			Volver = new JPanel();
			Volver.setBackground(new Color(255, 218, 185));
			GroupLayout gl_Volver = new GroupLayout(Volver);
			gl_Volver.setHorizontalGroup(
				gl_Volver.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_Volver.createSequentialGroup()
						.addGap(170)
						.addComponent(getBtnNewButton(), GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(184))
			);
			gl_Volver.setVerticalGroup(
				gl_Volver.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_Volver.createSequentialGroup()
						.addComponent(getBtnNewButton(), GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap())
			);
			Volver.setLayout(gl_Volver);
		}
		return Volver;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Seleccionar Dificultad");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Dificultad");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		return lblNewLabel_1;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.addItem("0. Facil");
            comboBox.addItem("1. Normal");
            comboBox.addItem("2. Dificil");
			comboBox.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
							if(comboBox.getSelectedItem().equals("0. Facil")) {
								dificultad = 0;
							} else if(comboBox.getSelectedItem().equals("1. Normal")) {
								dificultad = 1;
							} else {
								dificultad = 2;
							}System.out.println(dificultad);
                    } 
            } );
		}
		return comboBox;
	}
	private JButton getBtnNewButton() {
        if (btnNewButton == null) {
            btnNewButton = new JButton("Jugar");
            btnNewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    volver();
                }
            });
        }
        return btnNewButton;
    }

	public void volver() {
		support.firePropertyChange("Dificultad", false, dificultad);
		this.dispose();
	}

	private JTextPane getTxtpnModificaLaDificultad() {
		if (txtpnModificaLaDificultad == null) {
			txtpnModificaLaDificultad = new JTextPane();
			txtpnModificaLaDificultad.setBackground(new Color(255, 255, 240));
			txtpnModificaLaDificultad.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtpnModificaLaDificultad.setText("Modifica la dificultad aumentando o disminuyendo el tamaño del tablero y la veolcidad de la del bloque");
		}
		return txtpnModificaLaDificultad;
	}
}