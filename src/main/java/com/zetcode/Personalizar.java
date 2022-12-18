package com.zetcode;

import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;

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
import java.awt.GridLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Personalizar extends JFrame {

	private	static Personalizar miPersonalizacion=null;
	
	private JPanel contentPane;
	private JPanel TÃ­tulo;
	private JPanel Contenido;
	private JPanel Volver;
	private JLabel Titulillo;
	private JLabel Colordefondo;
	private JComboBox comboBox;
	private JLabel Colordeladrillo;
	private JLabel Sonidos;
	private JComboBox comboBox_5;
	private JButton confirmar;
	private Color colorFondo;
	private Color colorLadrillo;
	private boolean musica=true;
	private PropertyChangeSupport support;
	private JButton btnSonido;
	
	public static Personalizar getPersonalizar()
	{
		if(miPersonalizacion==null)
		{
			miPersonalizacion=new Personalizar();
		}
		return miPersonalizacion;
	}
	
	public Color getColorLadrillo() {
		return colorLadrillo;
	}
	
	public Color getColorFondo() {
		return colorFondo;
	}
	
	public boolean getMusica()
	{
		return musica;
	}
	
	private Personalizar() {
		setBackground(new Color(221, 160, 221));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 459, 258);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 153));
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getTÃ­tulo(), BorderLayout.NORTH);
		contentPane.add(getContenido(), BorderLayout.CENTER);
		contentPane.add(getVolver(), BorderLayout.SOUTH);
		setTitle("Personalizacion");
	}
	
	public void addObserver(PropertyChangeListener pList) {
		support.addPropertyChangeListener(pList);
	}


	private JPanel getTÃ­tulo() {
		if (TÃ­tulo == null) {
			TÃ­tulo = new JPanel();
			TÃ­tulo.setBackground(new Color(153, 153, 153));
			TÃ­tulo.add(getTitulillo());
		}
		return TÃ­tulo;
	}
	private JPanel getContenido() {
		if (Contenido == null) {
			Contenido = new JPanel();
			Contenido.setBackground(new Color(153, 153, 153));
			GroupLayout gl_Contenido = new GroupLayout(Contenido);
			gl_Contenido.setHorizontalGroup(
				gl_Contenido.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_Contenido.createSequentialGroup()
						.addGroup(gl_Contenido.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_Contenido.createSequentialGroup()
								.addComponent(getColordefondo(), GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(getComboBox_1(), GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_Contenido.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_Contenido.createSequentialGroup()
									.addComponent(getSonidos(), GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(getBtnSonido(), GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_Contenido.createSequentialGroup()
									.addComponent(getColordeladrillo(), GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(getComboBox_5(), GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))))
						.addGap(83))
			);
			gl_Contenido.setVerticalGroup(
				gl_Contenido.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_Contenido.createSequentialGroup()
						.addGroup(gl_Contenido.createParallelGroup(Alignment.BASELINE)
							.addComponent(getColordefondo(), GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addComponent(getComboBox_1(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_Contenido.createParallelGroup(Alignment.BASELINE)
							.addComponent(getSonidos(), GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addComponent(getBtnSonido()))
						.addGroup(gl_Contenido.createParallelGroup(Alignment.BASELINE)
							.addComponent(getColordeladrillo(), GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addComponent(getComboBox_5(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(113))
			);
			Contenido.setLayout(gl_Contenido);
		}
		return Contenido;
	}
	private JPanel getVolver() {
		if (Volver == null) {
			Volver = new JPanel();
			Volver.setBackground(new Color(153, 153, 153));
			
			JButton volver = new JButton("volver");
			volver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Menu volverMenu = new Menu();
					volverMenu.setVisible(true);
					Personalizar.this.dispose();
				
					
				}
			});
			volver.setForeground(Color.WHITE);
			volver.setBackground(Color.BLACK);
			GroupLayout gl_Volver = new GroupLayout(Volver);
			gl_Volver.setHorizontalGroup(
				gl_Volver.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_Volver.createSequentialGroup()
						.addGap(106)
						.addComponent(volver, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addGap(40)
						.addComponent(getConfirmar(), GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(93, Short.MAX_VALUE))
			);
			gl_Volver.setVerticalGroup(
				gl_Volver.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_Volver.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_Volver.createParallelGroup(Alignment.BASELINE)
							.addComponent(getConfirmar())
							.addComponent(volver))
						.addContainerGap())
			);
			Volver.setLayout(gl_Volver);
		}
		return Volver;
	}
	private JLabel getTitulillo() {
		if (Titulillo == null) {
			Titulillo = new JLabel("PERSONALIZACION");
			Titulillo.setFont(new Font("Tahoma", Font.BOLD, 30));
		}
		return Titulillo;
	}
	private JLabel getColordefondo() {
		if (Colordefondo == null) {
			Colordefondo = new JLabel("Color de fondo");
			Colordefondo.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return Colordefondo;
	}
	private JComboBox getComboBox_1() {
		if (comboBox == null) 
		{
			comboBox = new JComboBox();
			comboBox.addItem("White");
			comboBox.addItem("Blue");
			comboBox.addItem("Green");
			comboBox.addItem("Yellow");
			comboBox.addItem("Red");
			comboBox.addItem("Black");
			
			colorFondo = Color.WHITE;
			
			comboBox.addActionListener(new ActionListener() 
			{
	            public void actionPerformed(ActionEvent e) 
	            {
	            	if(comboBox.getSelectedItem().equals("Black")) {
						colorFondo = Color.BLACK;
	            	} else if(comboBox.getSelectedItem().equals("Blue")) {
						colorFondo = Color.BLUE;
					} else if(comboBox.getSelectedItem().equals("Green")) {
						colorFondo = Color.GREEN;
					}  else if(comboBox.getSelectedItem().equals("Yellow")) {
						colorFondo = Color.YELLOW;
					} else if(comboBox.getSelectedItem().equals("White")) {
						colorFondo = Color.WHITE;
					}else {
						colorFondo = Color.RED;
					}
	            } 
            });
		}
		return comboBox;
	}
	private JLabel getColordeladrillo() {
		if (Colordeladrillo == null) {
			Colordeladrillo = new JLabel("Color de ladrillo");
			Colordeladrillo.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return Colordeladrillo;
	}
	private JLabel getSonidos() {
		if (Sonidos == null) {
			Sonidos = new JLabel("Sonidos");
			Sonidos.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return Sonidos;
	}
	private JComboBox getComboBox_5() {
		if (comboBox_5 == null) {
			comboBox_5 = new JComboBox();
			comboBox_5.addItem("Black");
			comboBox_5.addItem("Blue");
			comboBox_5.addItem("Green");
			comboBox_5.addItem("Yellow");
			comboBox_5.addItem("White");
			comboBox_5.addItem("Red");
			
			colorLadrillo=Color.BLACK;
			
			comboBox_5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
					if(comboBox_5.getSelectedItem().equals("Black")) {
						colorLadrillo = Color.BLACK;
					} else if(comboBox_5.getSelectedItem().equals("Blue")) {
						colorLadrillo = Color.BLUE;
					} else if(comboBox_5.getSelectedItem().equals("Green")) {
						colorLadrillo = Color.GREEN;
					}  else if(comboBox_5.getSelectedItem().equals("Yellow")) {
						colorLadrillo = Color.YELLOW;
					} else if(comboBox_5.getSelectedItem().equals("White")) {
						colorLadrillo = Color.WHITE;
					}else {
						colorLadrillo = Color.RED;
					}
                } 
            } );
		}
		return comboBox_5;
	}
	
	
	
	private JButton getConfirmar() {
		if (confirmar == null) {
			confirmar = new JButton("guardar");
			confirmar.setForeground(Color.WHITE);
			confirmar.setBackground(Color.BLACK);
			confirmar.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	Menu volverMenu = new Menu();
		        	volverMenu.setVisible(true);
		        	Personalizar.this.dispose();
                }
            });
        }
        return confirmar;
    }
	
	private JButton getBtnSonido() {
		if (btnSonido == null) 
		{
			btnSonido = new JButton("STOPED			");
			btnSonido.addMouseListener(new MouseAdapter() 
			{				
				public void mouseClicked(MouseEvent e) 
				{
					musica = !musica;
					if(musica)
					{
						btnSonido.setText("PLAYING");
					}
					else
					{
						btnSonido.setText("STOPED");
					}
				}
			});
		}
		return btnSonido;
	}
}
