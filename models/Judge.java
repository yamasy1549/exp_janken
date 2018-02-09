package models;

import java.io.*;
import java.util.*;
import views.*;
import static original.Constants.*;

public class Judge implements Judgeable {

    /**
     * Judgeableの実装
     */
    @Override
    public Result judgeHand(Hand hand1, Hand hand2) {
        if(hand1 == hand2) {
            return Result.DRAW;
        } else if(hand1 == Hand.ROCK) {
            return (hand2 == Hand.SCISSORS) ? Result.WIN : Result.LOSE;
        } else if(hand1 == Hand.SCISSORS) {
            return (hand2 == Hand.PAPER) ? Result.WIN : Result.LOSE;
        } else {
            return (hand2 == Hand.ROCK) ? Result.WIN : Result.LOSE;
        }
    }

    /**
     * Judgeableの実装
     */
    @Override
    public Result judgePlayers(Player player1, Player player2) {
        recordJudge(player1, player2);

        int winRecord1 = player1.getRecord(Result.WIN);
        int winRecord2 = player2.getRecord(Result.WIN);

        if(winRecord1 == winRecord2) {
            return Result.DRAW;
        } else if(winRecord1 > winRecord2) {
            return Result.WIN;
        } else {
            return Result.LOSE;
        }
    }

    /**
     * Judgeableの実装
     */
    @Override
    public void recordJudge(Player player1, Player player2) {
        for(int i=0; i<HANDNUM; i++) {
            // TODO: もっときれいに書けそう
            Result result1 = judgeHand(player1.getHand(i), player2.getHand(i));
            Result result2 = judgeHand(player2.getHand(i), player1.getHand(i));
            player1.record(result1);
            player2.record(result2);
        }
    }
}
