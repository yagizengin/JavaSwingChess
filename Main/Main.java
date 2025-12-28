package Main;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        GamePanel gamePanel = new GamePanel();

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new GridBagLayout());
        jFrame.setResizable(false);

        jFrame.add(gamePanel);
        jFrame.pack();

        jFrame.setVisible(true);
        gamePanel.launch();
    }
}