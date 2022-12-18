package eus.ehu.lsi.adsi;

import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.zetcode.*;

public class PersonalizacionTest {
	
	@Test
	public void testMenuPersonalizar() {
		Personalizar abrirPersonalizacion = new Personalizar();
		//abrirPersonalizacion.setVisible(true);
		assertTrue(abrirPersonalizacion.isVisible() == true);
	}
	
	@Test
	public void testDespegableColorFondo() {
		Personalizar personalizarCF = new Personalizar();
		JComboBox comboBoxCF = new JComboBox();
		comboBoxCF.setModel(new DefaultComboBoxModel(new String[] {"White", "Blue", "Green", "Yellow", "Red", "Black"}));
		assertTrue(comboBoxCF.getItemCount() == 6);
		}
	@Test
	public void testEleccionColorFBlack() {
		Personalizar personalizarB = new Personalizar();
		JComboBox comboBoxB = new JComboBox();
		comboBoxB.setModel(new DefaultComboBoxModel(new String[] {"White", "Blue", "Green", "Yellow", "Red", "Black"}));
		comboBoxB.setSelectedItem("Black");
		assertTrue(comboBoxB.getSelectedIndex() == 5);
		}
	
	@Test
	public void testEleccionColorFBlue() {
		Personalizar personalizarB = new Personalizar();
		JComboBox comboBoxB = new JComboBox();
		comboBoxB.setModel(new DefaultComboBoxModel(new String[] {"White", "Blue", "Green", "Yellow", "Red", "Black"}));
		comboBoxB.setSelectedItem("Blue");
		assertTrue(comboBoxB.getSelectedIndex() == 1);
		}
	
	@Test
	public void testEleccionColorFGreen() {
		Personalizar personalizarG = new Personalizar();
		JComboBox comboBoxG = new JComboBox();
		comboBoxG.setModel(new DefaultComboBoxModel(new String[] {"White", "Blue", "Green", "Yellow", "Red", "Black"}));
		comboBoxG.setSelectedItem("Green");
		assertTrue(comboBoxG.getSelectedIndex() == 2);
		}
	@Test
	public void testEleccionColorFYellow() {
		Personalizar personalizarY = new Personalizar();
		JComboBox comboBoxY = new JComboBox();
		comboBoxY.setModel(new DefaultComboBoxModel(new String[] {"White", "Blue", "Green", "Yellow", "Red", "Black"}));
		comboBoxY.setSelectedItem("Yellow");
		assertTrue(comboBoxY.getSelectedIndex() == 3);
		}
	@Test
	public void testEleccionColorFRed() {
		Personalizar personalizarB = new Personalizar();
		JComboBox comboBoxB = new JComboBox();
		comboBoxB.setModel(new DefaultComboBoxModel(new String[] {"White", "Blue", "Green", "Yellow", "Red", "Black"}));
		comboBoxB.setSelectedItem("Red");
		assertTrue(comboBoxB.getSelectedIndex() == 4);
		}
	@Test
	public void testEleccionColorFWhite() {
		Personalizar personalizarB = new Personalizar();
		JComboBox comboBoxB = new JComboBox();
		comboBoxB.setModel(new DefaultComboBoxModel(new String[] {"White", "Blue", "Green", "Yellow", "Red", "Black"}));
		comboBoxB.setSelectedItem("White");
		assertTrue(comboBoxB.getSelectedIndex() == 0);
		}
	
	@Test
	public void testDespegableColorLadrillo() {
		Personalizar personalizarCL = new Personalizar();
		JComboBox comboBoxCL = new JComboBox();
		comboBoxCL.setModel(new DefaultComboBoxModel(new String[] {"Black", "Blue", "Green", "Yellow", "Red", "White"}));
		assertTrue(comboBoxCL.getItemCount() == 6);
		}
	
	@Test
	public void testEleccionColorLBlack() {
		Personalizar personalizarB = new Personalizar();
		JComboBox comboBoxB = new JComboBox();
		comboBoxB.setModel(new DefaultComboBoxModel(new String[] {"Black", "Blue", "Green", "Yellow", "Red", "White"}));
		comboBoxB.setSelectedItem("Black");
		assertTrue(comboBoxB.getSelectedIndex() == 0);
		}
	
	@Test
	public void testEleccionColorLBlue() {
		Personalizar personalizarB = new Personalizar();
		JComboBox comboBoxB = new JComboBox();
		comboBoxB.setModel(new DefaultComboBoxModel(new String[] {"Black", "Blue", "Green", "Yellow", "Red", "White"}));
		comboBoxB.setSelectedItem("Blue");
		assertTrue(comboBoxB.getSelectedIndex() == 1);
		}
	
	@Test
	public void testEleccionColorLGreen() {
		Personalizar personalizarG = new Personalizar();
		JComboBox comboBoxG = new JComboBox();
		comboBoxG.setModel(new DefaultComboBoxModel(new String[] {"Black", "Blue", "Green", "Yellow", "Red", "White"}));
		comboBoxG.setSelectedItem("Green");
		assertTrue(comboBoxG.getSelectedIndex() == 2);
		}
	@Test
	public void testEleccionColorLYellow() {
		Personalizar personalizarY = new Personalizar();
		JComboBox comboBoxY = new JComboBox();
		comboBoxY.setModel(new DefaultComboBoxModel(new String[] {"Black", "Blue", "Green", "Yellow", "Red", "White"}));
		comboBoxY.setSelectedItem("Yellow");
		assertTrue(comboBoxY.getSelectedIndex() == 3);
		}
	@Test
	public void testEleccionColorLRed() {
		Personalizar personalizarB = new Personalizar();
		JComboBox comboBoxB = new JComboBox();
		comboBoxB.setModel(new DefaultComboBoxModel(new String[] {"Black", "Blue", "Green", "Yellow", "Red", "White"}));
		comboBoxB.setSelectedItem("Red");
		assertTrue(comboBoxB.getSelectedIndex() == 4);
		}
	@Test
	public void testEleccionColorLWhite() {
		Personalizar personalizarB = new Personalizar();
		JComboBox comboBoxB = new JComboBox();
		comboBoxB.setModel(new DefaultComboBoxModel(new String[] {"Black", "Blue", "Green", "Yellow", "Red", "White"}));
		comboBoxB.setSelectedItem("White");
		assertTrue(comboBoxB.getSelectedIndex() == 5);
		}
	
	public void testBotonGuardar() {
		Personalizar guardarPersonalizacion = new Personalizar();
		guardarPersonalizacion.dispose();
		Menu menu = new Menu();
		assertTrue(guardarPersonalizacion.isVisible() == false);
		assertTrue(menu.isVisible() == true);
	}
	


}
