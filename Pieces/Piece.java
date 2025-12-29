package Pieces;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Board;
import Interfaces.*;
import java.util.List;

public abstract class Piece implements Drawable, Movable {

    protected boolean moved;
    protected BufferedImage image;
    protected int col, row;

    public enum PieceColor {
        WHITE, BLACK
    };

    protected PieceColor color;
    protected String name;

    // TODO use position instead of col and row
    public Piece(int col, int row, PieceColor color, String name) {
        this.col = col;
        this.row = row;
        this.color = color;
        this.name = name;
        this.moved = false;
        this.image = getImage("Assets/" + (color == PieceColor.WHITE ? "White" : "Black") + "/" + name + ".png");
    }

    public BufferedImage getImage(String imagePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract boolean isLegalMove(int col, int row);

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, col * Board.tilesize, (7 - row) * Board.tilesize, Board.tilesize, Board.tilesize, null);
    }


    public abstract List<int[]> getLegalMoves(); // TODO

}