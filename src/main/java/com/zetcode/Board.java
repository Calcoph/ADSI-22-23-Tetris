package com.zetcode;

import com.zetcode.Ficha.Tetrominoe;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel {

    private Timer timer;
    private boolean isFallingFinished = false;
    private boolean isPaused = false;
    private int numLinesRemoved = 0;
    private int curX = 0;
    private int curY = 0;
    private JLabel statusbar;
    private Ficha curPiece;
    private Tetrominoe[] board;
    JButton pausarPartida = null;
    JButton guardarPartida = null;
    JButton cancelar = null;

    public Board(Tetris parent) {
	GestorDificultad.actualizarDificultad();
        initBoard(parent);
    }

    private void initBoard(Tetris parent) {
    	setBackground(Personalizar.getPersonalizar().getColorFondo());
        setFocusable(true);
        statusbar = parent.getStatusBar();
        Audio.getAudio().Musica();
        addKeyListener(new TAdapter());
    }

    private int squareWidth() {

        return (int) getSize().getWidth() / Dificultad.getBOARD_WIDTH();
    }

    private int squareHeight() {

        return (int) getSize().getHeight() / Dificultad.getBOARD_HEIGHT();
    }

    private Tetrominoe shapeAt(int x, int y) {

        return board[(y * Dificultad.getBOARD_WIDTH()) + x];
    }

    public void start(String pEstadoPartida) {
	    
	this.pausarPartida = new JButton("Pausar partida");
        pausarPartida.addActionListener(new ActionListener() {
    				
            @Override
            public void actionPerformed(ActionEvent e) {
                pause();
            }
        });
	this.pausarPartida.setFocusable(false);
        this.add(pausarPartida, BorderLayout.NORTH);

    	curPiece = new Ficha();
    	
        if(pEstadoPartida != null) {
            board = this.convertirStringABoard(pEstadoPartida);
        }
        else {
            board = new Tetrominoe[Dificultad.getBOARD_WIDTH() * Dificultad.getBOARD_HEIGHT()];
            clearBoard();
        }
        
        newPiece();

        timer = new Timer(Dificultad.getPERIOD_INTERVAL(), new GameCycle());
        timer.start();
    }
    
    private String convertirBoardAString()
    /** Convierte el Board Tetrominoe[] al String estadoPartida **/
    {
        String s = "";
        for(int i=0; i<board.length; i++)
        {
            Tetrominoe te = board[i];
            if(i != board.length-1)
            {
                s = s + te + " ";
            }
            else
            {
                s = s + te;
            }
        }

        //System.out.println(s);
        return s;
    }
    
    private Tetrominoe[] convertirStringABoard(String pEstadoPartida)
    /** Convierte el String estadoPartida al Board Tetrominoe[] **/
    {
        String[] textoSeparado = pEstadoPartida.split(" ");     //Devuelve un array de String por cada palabra separada por un espacio

        Tetrominoe[] resultado = new Tetrominoe[Dificultad.getBOARD_WIDTH() * Dificultad.getBOARD_HEIGHT()];
        for(int i=0; i< textoSeparado.length; i++)
        {
            String s = textoSeparado[i];
            if(s.equals("NoShape"))
            {
                resultado[i] = Tetrominoe.NoShape;
            }
            else if(s.equals("ZShape"))
            {
                resultado[i] = Tetrominoe.ZShape;
            }
            else if(s.equals("SShape"))
            {
                resultado[i] = Tetrominoe.SShape;
            }
            else if(s.equals("LineShape"))
            {
                resultado[i] = Tetrominoe.LineShape;
            }
            else if(s.equals("TShape"))
            {
                resultado[i] = Tetrominoe.TShape;
            }
            else if(s.equals("SquareShape"))
            {
                resultado[i] = Tetrominoe.SquareShape;
            }
            else if(s.equals("LShape"))
            {
                resultado[i] = Tetrominoe.LShape;
            }
            else if(s.equals("MirroredLShape"))
            {
                resultado[i] = Tetrominoe.MirroredLShape;
            }
        }

        return resultado;
    }

    private void pause() {

        isPaused = !isPaused;
        if (isPaused) {
	    remove(this.pausarPartida);
            statusbar.setText("paused");
            if (this.guardarPartida == null && this.cancelar == null) {
            	this.guardarPartida = new JButton("Guardar Partida");
            	guardarPartida.addActionListener(new ActionListener() {
    				
    				@Override
    				public void actionPerformed(ActionEvent e) {
    					Gestor.guardarPartida(convertirBoardAString());
    					Tetris.getTetris().close();
                        Menu menu = new Menu();
                        menu.setVisible(true);
    				}
    			});
            	this.add(guardarPartida, BorderLayout.NORTH);
            	this.cancelar = new JButton("Cancelar");
            	cancelar.addActionListener(new ActionListener() {
    				
    				@Override
    				public void actionPerformed(ActionEvent e) {
    					pause();
    					
    				}
    			});
                this.add(cancelar, BorderLayout.NORTH);
            }
        } else {
	    this.pausarPartida.setFocusable(false);
	    this.add(pausarPartida, BorderLayout.NORTH);
            remove(this.guardarPartida);
	    remove(this.cancelar);
            this.guardarPartida = null;
            this.cancelar = null;
            statusbar.setText(String.valueOf(numLinesRemoved));
        }
        

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        var size = getSize();
        int boardTop = (int) size.getHeight() - Dificultad.getBOARD_HEIGHT() * squareHeight();

        for (int i = 0; i < Dificultad.getBOARD_HEIGHT(); i++) {

            for (int j = 0; j < Dificultad.getBOARD_WIDTH(); j++) {

                Tetrominoe shape = shapeAt(j, Dificultad.getBOARD_HEIGHT() - i - 1);

                if (shape != Tetrominoe.NoShape) {

                    drawSquare(g, j * squareWidth(),
                            boardTop + i * squareHeight(), shape, Personalizar.getPersonalizar().getColorLadrillo() );
                }
            }
        }

        if (curPiece.getShape() != Tetrominoe.NoShape) {

            for (int i = 0; i < 4; i++) {

                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);

                drawSquare(g, x * squareWidth(),
                        boardTop + (Dificultad.getBOARD_HEIGHT() - y - 1) * squareHeight(),
                        curPiece.getShape(), Personalizar.getPersonalizar().getColorLadrillo());
            }
        }
    }

    private void dropDown() {

        int newY = curY;

        while (newY > 0) {

            if (!tryMove(curPiece, curX, newY - 1)) {

                break;
            }

            newY--;
        }

        pieceDropped();
    }

    private void oneLineDown() {

        if (!tryMove(curPiece, curX, curY - 1)) {

            pieceDropped();
        }
    }

    private void clearBoard() {

        for (int i = 0; i < Dificultad.getBOARD_HEIGHT() * Dificultad.getBOARD_WIDTH(); i++) {

            board[i] = Tetrominoe.NoShape;
        }
    }

    private void pieceDropped() {

        for (int i = 0; i < 4; i++) {

            int x = curX + curPiece.x(i);
            int y = curY - curPiece.y(i);
            board[(y * Dificultad.getBOARD_WIDTH()) + x] = curPiece.getShape();
        }

        removeFullLines();

        if (!isFallingFinished) {

            newPiece();
        }

        Gestor.contarFicha();

        Gestor.comprobarProgresoPremios();
    }

    private void newPiece() {

        curPiece.setRandomShape();
        curX = Dificultad.getBOARD_WIDTH() / 2 + 1;
        curY = Dificultad.getBOARD_HEIGHT() - 1 + curPiece.minY();

        if (!tryMove(curPiece, curX, curY)) {

            curPiece.setShape(Tetrominoe.NoShape);
            timer.stop();
	    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()); //Para saber la fecha
            Gestor g=new Gestor();
            int difi=g.getDificultad();
            int punt=GestorUsuario.getGestorUsuario().obtenerPartidaUsuario(GestorUsuario.getGestorUsuario().obtenerUsuarioActual()).obtenerPuntos();
	    g.setNuevaPuntuacion(/*Puntuacion*/punt,/*Nombre*/ g.getNombreUsuario(), timeStamp, /*Dificultad*/ difi);
            var msg = String.format("Game over. Score: %d", numLinesRemoved);
            statusbar.setText(msg);
            Gestor.comprobarProgresoPremiosFinalPartida();
            CompartirResultados resultados = new CompartirResultados();
            resultados.setVisible(true);
            Tetris.getTetris().dispose();
        }
    }

    private boolean tryMove(Ficha newPiece, int newX, int newY) {

        for (int i = 0; i < 4; i++) {

            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);

            if (x < 0 || x >= Dificultad.getBOARD_WIDTH() || y < 0 || y >= Dificultad.getBOARD_HEIGHT()) {

                return false;
            }

            if (shapeAt(x, y) != Tetrominoe.NoShape) {

                return false;
            }
        }

        curPiece = newPiece;
        curX = newX;
        curY = newY;

        repaint();

        return true;
    }

    private void removeFullLines() {

        int numFullLines = 0;

        for (int i = Dificultad.getBOARD_HEIGHT() - 1; i >= 0; i--) {

            boolean lineIsFull = true;

            for (int j = 0; j < Dificultad.getBOARD_WIDTH(); j++) {

                if (shapeAt(j, i) == Tetrominoe.NoShape) {

                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {

                numFullLines++;

                for (int k = i; k < Dificultad.getBOARD_HEIGHT() - 1; k++) {
                    for (int j = 0; j < Dificultad.getBOARD_WIDTH(); j++) {
                        board[(k * Dificultad.getBOARD_WIDTH()) + j] = shapeAt(j, k + 1);
                    }
                }
            }
        }

        if (numFullLines > 0) {

            numLinesRemoved += numFullLines;

            Gestor.addFilas(numFullLines);
            
            if (numFullLines == 1) {
                Gestor.addPuntos(10);
            } else if (numFullLines == 2) {
                Gestor.addPuntos(50);
            } else if (numFullLines == 3) {
                Gestor.addPuntos(150);
            } else {
                Gestor.addPuntos(300);
            }

            if (numFullLines >= 4) {
                Gestor.addTetrises(1);
            }

            statusbar.setText(String.valueOf(numLinesRemoved));
            isFallingFinished = true;
            curPiece.setShape(Tetrominoe.NoShape);
        }
    }

    private void drawSquare(Graphics g, int x, int y, Tetrominoe shape, Color pColor) {

        

        g.setColor(pColor);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(pColor.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(pColor.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + 1);
    }

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }

    private void doGameCycle() {

        update();
        repaint();
    }

    private void update() {

        if (isPaused) {

            return;
        }

        if (isFallingFinished) {

            isFallingFinished = false;
            newPiece();
        } else {

            oneLineDown();
        }
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (curPiece.getShape() == Tetrominoe.NoShape) {

                return;
            }

            int keycode = e.getKeyCode();

            // Java 12 switch expressions
            switch (keycode) {

                case KeyEvent.VK_P -> pause();
                case KeyEvent.VK_LEFT -> tryMove(curPiece, curX - 1, curY);
                case KeyEvent.VK_RIGHT -> tryMove(curPiece, curX + 1, curY);
                case KeyEvent.VK_DOWN -> tryMove(curPiece.rotateRight(), curX, curY);
                case KeyEvent.VK_UP -> tryMove(curPiece.rotateLeft(), curX, curY);
                case KeyEvent.VK_SPACE -> dropDown();
                case KeyEvent.VK_D -> oneLineDown();
            }
        }
    }
}
