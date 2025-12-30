package Main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Board {

    private final int row = 8;
    private final int col = 8;

    public static final int tilesize = 64;
    BufferedImage image;

    public Board() {
        try {
            image = ImageIO.read(new File("Assets/wood.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                g2d.setColor((c + r) % 2 == 0 ? new Color(235, 230, 200, 100) : new Color(63, 31, 0, 100));
                g2d.fillRect(c * tilesize, r * tilesize, tilesize, tilesize);
                if (c == 0) {
                    g2d.setColor((c+r) % 2 == 0 ?  new Color(63, 31, 0) : new Color(235, 230, 200));
                    g2d.setFont(g2d.getFont().deriveFont(16f));
                    g2d.drawString(String.valueOf(r+1), 2, GamePanel.HEIGHT - ((r+1) * tilesize) + 16);
                }
                if(r == 0) {
                    g2d.setColor((c+r) % 2 == 0 ?  new Color(63, 31, 0) : new Color(235, 230, 200));
                    g2d.setFont(g2d.getFont().deriveFont(16f));
                    g2d.drawString(String.valueOf((char)('a' + c)), c * tilesize + tilesize - 16, GamePanel.HEIGHT - 2);
                }
            }
        }
    }

}