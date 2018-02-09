package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import models.*;
import static original.Constants.*;

public class PlayerInfo extends JJPanel {

    PlayerInfo(Player player) {
        super("PlayerInfo");

        String _name = player.getName();
        if(_name.length() > 7) _name = _name.substring(0, 7) + "...";
        JLabel name = new JLabel(_name);
        name.setForeground(Color.WHITE);
        name.setHorizontalAlignment(JLabel.CENTER);
        name.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        name.setPreferredSize(new Dimension(200, 100));
        addAndSetBounds(name, 0, 120, 200, 30);

        JLabel points = new JLabel(player.getPoints() + "pt");
        points.setForeground(Color.WHITE);
        points.setHorizontalAlignment(JLabel.CENTER);
        points.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        points.setPreferredSize(new Dimension(200, 100));
        addAndSetBounds(points, 0, 150, 200, 30);
    }
}
