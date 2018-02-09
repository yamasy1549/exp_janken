package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import models.*;
import static original.Constants.*;

public class PlayerInfo extends JJPanel {

    PlayerInfo(Player player) {
        super("PlayerInfo");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel name = new JLabel(player.getName());
        JLabel points = new JLabel(player.getPoints() + "pt");

        name.setForeground(Color.WHITE);
        points.setForeground(Color.WHITE);

        add(name);
        add(points);
    }
}
