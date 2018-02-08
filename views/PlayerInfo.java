package views;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import models.*;
import static original.Constants.*;

public class PlayerInfo extends JPanel {
    public PlayerInfo(Player player) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        JLabel name = new JLabel(player.getName());
        JLabel points = new JLabel(player.getPoints() + "pt");
        name.setForeground(Color.WHITE);
        points.setForeground(Color.WHITE);
        add(name);
        add(points);
    }
}
