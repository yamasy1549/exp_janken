package models;

import static original.Constants.*;

public interface Judgeable {
    /**
     * 特定の手から勝敗を判断する
     * @param hand1 player1の手
     * @param hand2 player2の手
     * @return player1の勝敗結果
     */
    Result judgeHand(Hand hand1, Hand hand2);

    /**
     * 手の勝敗数からプレーヤーの勝敗を判断する
     * @param player1
     * @param player2
     * @return player1の勝敗結果
     */
    Result judgePlayers(Player player1, Player player2);

    /**
     * すべての手について勝敗を記録する
     * @param player1
     * @param player2
     */
    void recordJudge(Player player1, Player player2);
}
