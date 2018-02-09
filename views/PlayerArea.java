package views;

import java.awt.*;
import javax.swing.*;
import models.*;
import static original.Constants.*;

public class PlayerArea extends JPanel {

    PlayerArea(Player player) {
        setName("PlayerArea");
        setSize(1200, 300);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(false);

        add(new PlayerInfo(player));
        add(new Deck(player));
    }
}
