package models;

import static original.Constants.*;

public interface Playable {
    /**
     * ランダムに手を用意する
     * @param count 用意する手の数
     */
    void setHands(int count);

    /**
     * ポイントを賭ける
     * @param points 賭けるポイント
     */
    void betPoints(int points);

    /**
     * ポイントを得る
     * @param points 得るポイント
     */
    void getPoints(int points);

    /**
     * ゲーム結果を記録する
     * @param points ゲーム結果
     */
    void record(Result result);

    /**
     * 過去すべてのゲーム結果を記録する
     * @param result 今回のゲーム結果
     */
    void allRecord(Result result);
}
