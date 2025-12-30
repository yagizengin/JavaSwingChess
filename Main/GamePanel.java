package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Logger;

import javax.swing.JPanel;

import Game.GameController;
import Game.MoveValidator;
import Game.Position;

import Pieces.Piece.PieceColor;
import Pieces.Piece;

public class GamePanel extends JPanel implements Runnable {
    Logger logger = Logger.getLogger(GamePanel.class.getName());

    public static final int WIDTH = 512;
    public static final int HEIGHT = 512;
    private final int FPS = 60;

    private Board board;
    private Thread gameThread;
    private GameController gameManager;
    private Mouse mouse;

    private PieceColor currentColor = PieceColor.WHITE;

    public GamePanel() {
        this.gameManager = new GameController();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);

        this.board = new Board();

        gameManager.initPieces();

        mouse = new Mouse();
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
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
            Position mousePos = mouse.getPosition();

            if (gameManager.isSelectedPiece()) {
                if (!gameManager.movePiece(mousePos)) gameManager.selectPiece(mousePos);
            } else {
                gameManager.selectPiece(mousePos);
            }

        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        board.draw(g2d);

        if (gameManager.isSelectedPiece()) {
            g2d.setColor(new Color(255, 200, 127, 127));
            g2d.fillRect(gameManager.getSelectedPiece().getCol() * Board.tilesize,
                    (7 - gameManager.getSelectedPiece().getRow()) * Board.tilesize,
                    Board.tilesize, Board.tilesize);

            g2d.setColor(new Color(127, 127, 127, 127));
            g2d.fillRect(mouse.getCol() * Board.tilesize,
                    (7 - mouse.getRow()) * Board.tilesize, Board.tilesize, Board.tilesize);
            for (Piece piece : gameManager.getPieces()) {
                if (piece.getCol() == mouse.getCol() &&
                        piece.getRow() == mouse.getRow() && piece.getColor() == currentColor) {
                    g2d.setColor(new Color(127, 127, 127, 127));
                    g2d.fillRect(mouse.getCol() * Board.tilesize,
                            (7 - mouse.getRow()) * Board.tilesize, Board.tilesize,
                            Board.tilesize);
                }
            }

            MoveValidator moveValidator = gameManager.getMoveValidator();
            Piece selectedPiece = gameManager.getSelectedPiece();

            for (Position pos : moveValidator.getLegalMoves(selectedPiece)) {
                g2d.setColor(new Color(0, 0, 0, 127));
                g2d.setStroke(new BasicStroke(6));
                if (moveValidator.isCapturingPiece(selectedPiece, pos)) {
                    g2d.drawOval(pos.getCol() * Board.tilesize + 3,
                            (7 - pos.getRow()) * Board.tilesize + 3,
                            Board.tilesize - 6, Board.tilesize - 6);
                } else {
                    g2d.fillOval(pos.getCol() * Board.tilesize + Board.tilesize / 3,
                            (7 - pos.getRow()) * Board.tilesize + Board.tilesize / 3,
                            Board.tilesize / 3, Board.tilesize / 3);
                }
            }
        }
        
        for (Piece piece : gameManager.getPieces()) {
            piece.draw(g2d);
        }
    }
}