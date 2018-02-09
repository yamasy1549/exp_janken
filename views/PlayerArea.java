package views;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import models.*;
import static original.Constants.*;

public class PlayerArea extends JPanel {

    PlayerArea(Player player) {
        setName("PlayerArea");
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(new PlayerInfo(player));
        add(new Deck(player));
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
