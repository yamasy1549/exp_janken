package views;

import javax.swing.*;
import java.awt.event.*;
import models.*;
import static original.Constants.*;

public class ResultArea extends JPanel {
    private Player player1, player2;
    private Judge judge;
    private ResultLabel label = new ResultLabel();
    private ResultLabel[] labels = new ResultLabel[HANDNUM];

    public ResultArea(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.judge = new Judge();

        setName("ResultArea");
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(false);

        add(this.label);

        for(int i=0; i<HANDNUM; i++) {
            this.labels[i] = new ResultLabel();
            add(this.labels[i]);
        }
    }

    public void updateHands(Result playResult) {
        for(int i=0; i<HANDNUM; i++) {
            Hand hand1 = player1.getHand(i);
            Hand hand2 = player2.getHand(i);
            Result result = this.judge.judgeHand(hand1, hand2);
            this.labels[i].updateResult(result);
        }
        this.label.updateResult(playResult);
    }
}
