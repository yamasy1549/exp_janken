package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import models.*;
import static original.Constants.*;

public class PlayPanel extends JJPanel {

    public PlayPanel(Player player1, Player player2) {
        super("PlayPanel");

        PlayerArea playerArea2 = new PlayerArea(player2);
        addAndSetBounds(playerArea2, 0, 0, 1100, 300);

        PlayerArea playerArea1 = new PlayerArea(player1);
        addAndSetBounds(playerArea1, 0, 400, 1100, 300);

        ResultArea resultArea = new ResultArea(player1, player2);
        addAndSetBounds(resultArea, 0, 300, 1100, 100);

        ControlArea controlArea = new ControlArea(player1, player2);
        addAndSetBounds(controlArea, 1100, 0, 200, 700);
    }

    /**
     * 背景画像を設定する
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        try {
            BufferedImage background;
            background = ImageIO.read(getClass().getResource("../images/BACKGROUND.png"));
            g.drawImage(background, 0, 0, this);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}
