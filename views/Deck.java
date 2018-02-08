package views;

import javax.swing.*;
import java.awt.event.*;
import models.*;
import static original.Constants.*;

public class Deck extends JPanel {
    public Deck(Player player) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(false);

        for(Hand hand : player.getHands()) {
            add(new Card(hand));
        }
    }
}
