package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;

public class GamePanel extends JPanel implements Runnable {
    public static final int WIDTH = 512;
    public static final int HEIGHT = 512;
    private final int FPS = 60;

    private Board board;
    private Thread gameThread;
    private static ArrayList<Piece> pieces;

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        this.board = new Board();
        pieces = new ArrayList<>();
        initPieces();
    }

    public void initPieces() {
        pieces.add(new Pawn(0, 1, true));
        pieces.add(new Pawn(1, 1, true));
        pieces.add(new Pawn(2, 1, true));
        pieces.add(new Pawn(3, 1, true));
        pieces.add(new Pawn(4, 1, true));
        pieces.add(new Pawn(5, 1, true));
        pieces.add(new Pawn(6, 1, true));
        pieces.add(new Pawn(7, 1, true));

        pieces.add(new Pawn(0, 6, false));
        pieces.add(new Pawn(1, 6, false));
        pieces.add(new Pawn(2, 6, false));
        pieces.add(new Pawn(3, 6, false));
        pieces.add(new Pawn(4, 6, false));
        pieces.add(new Pawn(5, 6, false));
        pieces.add(new Pawn(6, 6, false));
        pieces.add(new Pawn(7, 6, false));

        pieces.add(new Rook(0, 0, true));
        pieces.add(new Rook(7, 0, true));
        pieces.add(new Rook(0, 7, false));
        pieces.add(new Rook(7, 7, false));

        pieces.add(new Knight(1, 0, true));
        pieces.add(new Knight(6, 0, true));
        pieces.add(new Knight(1, 7, false));
        pieces.add(new Knight(6, 7, false));

        pieces.add(new Bishop(2, 0, true));
        pieces.add(new Bishop(5, 0, true));
        pieces.add(new Bishop(2, 7, false));
        pieces.add(new Bishop(5, 7, false));

        pieces.add(new Queen(3, 0, true));
        pieces.add(new Queen(3, 7, false));

        pieces.add(new King(4, 0, true));
        pieces.add(new King(4, 7, false));

    }

    public void launch() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000 / FPS;
        long previousTime = System.nanoTime();

        while (true) {
            long currentTime = System.nanoTime();
            long passedTime = currentTime - previousTime;
            previousTime = currentTime;

            if (passedTime >= timePerFrame) {
                update();
                repaint();
            }

        }
    }

    public void update() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        board.draw(g2d);
        for (Piece piece : pieces) {
            piece.Draw(g2d);
        }
    }
}
