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
        if(_name.length() > 8) _name = _name.substring(0, 8) + "...";
        JLabel name = new JJLabel(_name);
        addAndSetBounds(name, 0, 120, 200, 30);

        JLabel points = new JJLabel(player.getPoints() + "pt");
        points.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        addAndSetBounds(points, 0, 150, 200, 30);
    }
}
