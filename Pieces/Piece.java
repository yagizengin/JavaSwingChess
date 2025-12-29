package Pieces;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.Position;
import Main.Board;
import Interfaces.*;
import java.util.List;

public abstract class Piece implements Drawable, Movable {

    protected boolean moved;
    protected BufferedImage image;

    public enum PieceColor {
        WHITE, BLACK
    };

    protected PieceColor color;
    protected String name;

    protected Position position;
    public Piece(Position position, PieceColor color, String name) {
        this.position = position;
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
        return this.position.getCol();
    }

    public int getRow() {
        return this.position.getRow();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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

    public abstract boolean isLegalMove(Position position);

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, this.position.getCol() * Board.tilesize, (7 - this.position.getRow()) * Board.tilesize, Board.tilesize, Board.tilesize, null);
    }


    public abstract List<int[]> getLegalMoves(); // TODO

}