package views;

import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import models.*;
import static original.Constants.*;

public class PlayerArea extends JPanel {
    public PlayerArea(Player player) {
        setName("PlayerArea");
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(new PlayerInfo(player));
        add(new Deck(player));
    }

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
