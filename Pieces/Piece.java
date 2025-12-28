package Pieces;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Board;

public abstract class Piece {

    protected BufferedImage image;
    protected int col, row;
    protected boolean isWhite;
    protected String name;

    public Piece(int col, int row, boolean isWhite, String name) {
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;
        this.name = name;
        this.image = getImage("Assets/" + (isWhite ? "White" : "Black") + "/" + name + ".png");
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

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void Draw(Graphics2D g) {
        g.drawImage(image, col * Board.tilesize, (7 - row) * Board.tilesize, Board.tilesize, Board.tilesize, null);
    }

}
