package views;

import javax.swing.*;
import java.awt.event.*;
import models.*;
import static original.Constants.*;

public class PlayerInfo extends JPanel {
    public PlayerInfo(Player player) {
        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel name = new JLabel(player.getName());
        JLabel points = new JLabel(player.getPoints() + "pt");
        add(name);
        add(points);
    }
}
