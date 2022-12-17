package eus.ehu.lsi.adsi;

import static org.junit.Assert.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.junit.Test;

import com.zetcode.Board;
import com.zetcode.Dificultad;
import com.zetcode.MenuDificultad;
import com.zetcode.Tetris;

public class DificultadTests {

	@Test
	public void testMenuDificultad() {
		MenuDificultad abrirDificultad = new MenuDificultad();
		assertTrue(abrirDificultad.isVisible() == true);
		
	}
	
	@Test
	public void testDeplegable() {
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0. Fácil", "1. Normal", "2. Difícil"}));
		assertTrue(comboBox.getItemCount() == 3);
	}
	
	@Test
	public void testFacil() {
		JComboBox comboBox = new JComboBox();
		Dificultad d = new Dificultad();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0. Fácil", "1. Normal", "2. Difícil"}));
		comboBox.setSelectedItem("0. Fácil");
		if(comboBox.getSelectedIndex() == 0) {
			d.setBOARD_HEIGHT(27);
			d.setBOARD_WIDTH(15);
			d.setPERIOD_INTERVAL(400);
		}
		assertTrue(d.getBOARD_HEIGHT() == 27);
		assertTrue(d.getBOARD_WIDTH() == 15);
		assertTrue(d.getPERIOD_INTERVAL() == 400);
		
	}
	
	@Test
	public void testNormal() {
		JComboBox comboBox = new JComboBox();
		Dificultad d = new Dificultad();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0. Fácil", "1. Normal", "2. Difícil"}));
		comboBox.setSelectedItem("1. Normal");
		if(comboBox.getSelectedIndex() == 1) {
			d.setBOARD_HEIGHT(22);
			d.setBOARD_WIDTH(15);
			d.setPERIOD_INTERVAL(300);
		}
		assertTrue(d.getBOARD_HEIGHT() == 22);
		assertTrue(d.getBOARD_WIDTH() == 15);
		assertTrue(d.getPERIOD_INTERVAL() == 300);
	}
	
	@Test
	public void testDificil() {
		JComboBox comboBox = new JComboBox();
		Dificultad d = new Dificultad();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0. Fácil", "1. Normal", "2. Difícil"}));
		comboBox.setSelectedItem("2. Difícil");
		if(comboBox.getSelectedIndex() == 2) {
			d.setBOARD_HEIGHT(22);
			d.setBOARD_WIDTH(10);
			d.setPERIOD_INTERVAL(150);
		}
		assertTrue(d.getBOARD_HEIGHT() == 22);
		assertTrue(d.getBOARD_WIDTH() == 10);
		assertTrue(d.getPERIOD_INTERVAL() == 150);
	}
	
	@Test
	public void testJugar() {
		MenuDificultad menuDificultad = new MenuDificultad();
		menuDificultad.dispose();
		Tetris tetris = new Tetris();
		Board board = new Board(tetris);
		assertTrue(menuDificultad.isVisible() == false);
		assertTrue(board.isVisible() == true);
	}
}
