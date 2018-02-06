package views;

import javax.swing.*;
import java.awt.event.*;
import static original.Constants.*;

public class Deck extends JPanel {
    private int CARDNUM = 5;
    private Card cards[] = new Card[CARDNUM];

    public Deck() {
        super();

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        for(int i=0; i<5; i++) {
            Hand hand = Hand.random();
            cards[i] = new Card(hand);
            add(cards[i]);
        }
    }
}
