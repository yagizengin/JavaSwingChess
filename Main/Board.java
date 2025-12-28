package Main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Board {

    private final int row = 8;
    private final int col = 8;

    public static final int tilesize = 64;

    public void draw(Graphics2D g) {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                g.setColor((c + r) % 2 == 0 ? new Color(235, 230, 200) : new Color(150, 100, 20));
                g.fillRect(c * tilesize, r * tilesize, tilesize, tilesize);
            }
        }
    }

}
