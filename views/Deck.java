package views;

import javax.swing.*;
import java.awt.event.*;
import models.*;
import static original.Constants.*;

public class Deck extends JPanel {
    public Deck(Player player) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(false);

        for(Card card : player.getCards()) {
            add(card);
        }
    }
}
