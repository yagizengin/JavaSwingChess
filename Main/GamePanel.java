package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JPanel;

import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;

public class GamePanel extends JPanel implements Runnable {
    Logger logger = Logger.getLogger(GamePanel.class.getName());

    public static final int WIDTH = 512;
    public static final int HEIGHT = 512;
    private final int FPS = 60;

    private Board board;
    private Thread gameThread;
    private Mouse mouse;

    private static ArrayList<Piece> pieces;
    private Piece selectedPiece;
    private boolean currentColor = true;

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);

        this.board = new Board();
        pieces = new ArrayList<>();

        initPieces();

        mouse = new Mouse();
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
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
        double timePerFrame = 1000000000.0 / FPS;
        long previousTime = System.nanoTime();

        while (true) {
            long currentTime = System.nanoTime();
            long passedTime = currentTime - previousTime;

            if (passedTime >= timePerFrame) {
                previousTime = currentTime;
                update();
                repaint();
            }
        }
    }

    public void update() {
        if (mouse.isClicked()) {
            if (selectedPiece == null) {
                selectedPiece = pieces.stream().filter(
                        p -> p.getCol() == mouse.getX() / Board.tilesize &&
                                p.getRow() == mouse.getY() / Board.tilesize && p.isWhite() == currentColor)
                        .findFirst().orElse(null);
                if (selectedPiece != null)
                    logger.info("selected piece: " + selectedPiece.getName());
                else
                    logger.info("no piece selected");

            } else {
                simulateMove();
                logger.info("piece moved");
            }
        }

    }

    public void simulateMove() {
        selectedPiece.setCol(mouse.getX() / Board.tilesize);
        selectedPiece.setRow(mouse.getY() / Board.tilesize);
        selectedPiece = null;
        currentColor = !currentColor;
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