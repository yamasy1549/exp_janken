package views;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import models.*;
import static original.Constants.*;

public class Deck extends JPanel {

    private Player player;
    private Card cards[] = new Card[HANDNUM];

    Deck(Player player) {
        this.player = player;

        setName("Deck");
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(false);

        // closeしないCardをランダムに決める
        int openCardIndex = new Random().nextInt(HANDNUM);
        Hand[] playerHands = player.getHands();

        for(int i=0; i<playerHands.length; i++) {
            this.cards[i] = new Card(playerHands[i]);
            add(this.cards[i]);

            // ComputerのCardのうちランダム1つを除いてcloseする
            if((player instanceof Computer) && i != openCardIndex) {
                this.cards[i].close();
            }
        }
    }

    /**
     * Deckの持つカードの手を更新する
     */
    public void updateHands() {
        Hand[] hands = new Hand[HANDNUM];
        Component[] components = getComponents();

        for(int i=0; i<components.length; i++) {
            hands[i] = ((Card)components[i]).getHand();
        }
        this.player.setHands(hands);
    }

    /**
     * Deckの持つカード全てをopenする
     */
    public void open() {
        for(Card card : this.cards) {
            card.open();
        }
    }
}
