package views;

import javax.swing.*;
import java.awt.event.*;
import models.*;
import static original.Constants.*;

public class PlayerArea extends JPanel {
    public PlayerArea(Player player) {
        super();

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(new PlayerInfo(player));
        add(new Deck(player));
    }
}
