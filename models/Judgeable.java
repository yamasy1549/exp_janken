package models;

import static original.Constants.*;

public interface Judgeable {
    // 特定の手から勝敗を判断する
    Result judgeHand(Hand hand1, Hand hand2);

    // すべての手について勝敗を記録する
    void recordJudge(Player player1, Player player2);

    // 手の勝敗数からプレーヤーの勝敗を判断する
    Result judgePlayers(Player player1, Player player2);
}
