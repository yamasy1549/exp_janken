package views;

import java.awt.event.*;
import javax.swing.*;
import models.*;
import static original.Constants.*;

public class ResultArea extends JJPanel {

    private Player player1, player2;
    private Judge judge;
    // 全体のResult
    private ResultLabel label = new ResultLabel();
    // 手ごとのResult
    private ResultLabel[] labels = new ResultLabel[HANDNUM];

    ResultArea(Player player1, Player player2) {
        super("ResultArea");

        this.player1 = player1;
        this.player2 = player2;
        this.judge = new Judge();

        for(int i=0; i<HANDNUM; i++) {
            this.labels[i] = new ResultLabel();
            addAndSetBounds(this.labels[i], i*(CARD_WIDTH+10)+30, 0, CARD_WIDTH, 100);
        }

        addAndSetBounds(this.label, 870, 0, 200, 100);
    }

    /**
     * 結果表示を更新する
     * @param playResult 全体のResult
     */
    public void updateResults(Result playResult) {
        for(int i=0; i<HANDNUM; i++) {
            Hand hand1 = player1.getHand(i);
            Hand hand2 = player2.getHand(i);
            Result result = this.judge.judgeHand(hand1, hand2);
            this.labels[i].updateResult(result);
        }
        this.label.updateResult(playResult);
    }
}
