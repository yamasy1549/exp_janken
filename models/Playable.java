package models;

import static original.Constants.*;

public interface Playable {
    // ランダムに手を用意する
    void setHands(int count);

    // ポイントを賭ける
    void betPoints(int points);

    // ポイントを得る
    void getPoints(int points);

    // ゲーム結果を記録する
    void record(Result result);
}
