package views;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import models.*;
import static original.Constants.*;

public class Deck extends JPanel {
    private Player player;
    private Card cards[] = new Card[HANDNUM];

    public Deck(Player player) {
        this.player = player;

        setName("Deck");
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(false);

        Hand[] playerHands = player.getHands();
        int openCardIndex = new Random().nextInt(HANDNUM);
        for(int i=0; i<playerHands.length; i++) {
            this.cards[i] = new Card(playerHands[i]);
            add(this.cards[i]);
            if((player instanceof Computer) && i != openCardIndex) {
                this.cards[i].close();
            }
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

    public void open() {
        for(Card card : this.cards) {
            card.open();
        }
    }
}
