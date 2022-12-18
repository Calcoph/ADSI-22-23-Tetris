package com.zetcode;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
Java Tetris game clone

Author: Jan Bodnar
Website: https://zetcode.com
 */
public class Tetris extends JFrame {
    private static final Logger logger = LogManager.getLogger(Tetris.class);
    private JLabel statusbar;
    private static Tetris miTetris = null;
    Board miBoard = null;
    
    public static Tetris getTetris() {
    	if (miTetris == null) {
    		miTetris = new Tetris(false);
    	}
    	return miTetris;
    }
    
    public void start(String pEstadoTablero) {
        EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    miTetris.initUI(pEstadoTablero);
		    miTetris.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }
    
    public void close() {
    	miTetris.dispose();
    }

    private Tetris(boolean p) {
        
    }

    public Tetris() {
        initUI(null);
    }

    private void initUI(String pEstadoTablero) {
        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);

        if (miBoard != null) {
            remove(miBoard);
        }
        miBoard = new Board(this);
        add(miBoard);
        miBoard.start(pEstadoTablero);
        
        setTitle("Tetris");
        setSize(200, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    JLabel getStatusBar() {
        return statusbar;
    }

    public static void main(String[] args) {
        logger.info("Playing");
        EventQueue.invokeLater(() -> {

            var game = new Tetris();
            game.setVisible(true);
        });
    }
}
