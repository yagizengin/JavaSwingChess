package Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Game.Position;

public class Mouse extends MouseAdapter {

    private int x, y;
    private boolean isPressed;
    private volatile boolean clicked;

    @Override
    public void mousePressed(MouseEvent e) {
        isPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isPressed = false;
        clicked = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return GamePanel.HEIGHT - y;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public boolean isClicked() {
        if (clicked) {
            clicked = false;
            return true;
        }
        return false;
    }

    public int getCol() {
        return getX() / Board.tilesize;
    }

    public int getRow() {
        return getY() / Board.tilesize;
    }

    public Position getPosition() {
        return new Position(getCol(), getRow());
    }

}