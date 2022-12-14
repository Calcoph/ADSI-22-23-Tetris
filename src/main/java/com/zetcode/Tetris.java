package com.zetcode;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
Java Tetris game clone

Author: Jan Bodnar
Website: https://zetcode.com
 */
public class Tetris extends JFrame {
    private static final Logger logger = LogManager.getLogger(Tetris.class);
    private JLabel statusbar;
    private static Tetris miTetris = null;
    
    public static Tetris getTetris() {
    	if (miTetris == null) {
    		miTetris = new Tetris();
    	}
    	return miTetris;
    }
    
    public void start() {
        EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
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

    private Tetris() {
        initUI();
    }

    private void initUI() {
        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);

        var board = new Board(this);
        add(board);
        board.start();

        setTitle("Tetris");
        setSize(200, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    JLabel getStatusBar() {
        return statusbar;
    }
}
