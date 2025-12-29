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
            }
        }
    }

}