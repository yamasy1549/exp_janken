package views;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import models.*;
import static original.Constants.*;

public class Deck extends JPanel {
    private Player player;

    public Deck(Player player) {
        this.player = player;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(false);

        for(Hand hand : player.getHands()) {
            add(new Card(hand));
        }
    }

    public void updateHands() {
        Hand[] hands = new Hand[HANDNUM];
        Component[] components = getComponents();
        for(int i=0; i<components.length; i++) {
            String _hand = ((Card)components[i]).getText();
            hands[i] = Hand.valueOf(_hand);
        }
        this.player.setHands(hands);
    }
}
