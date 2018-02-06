package models;

import static original.Constants.*;

public interface Playable {
    // ランダムにカードを配置する
    void setCards(int count);

    // ポイントを賭ける
    void betPoints(int points);

    // ポイントを得る
    void getPoints(int points);

    // ゲーム結果を記録する
    void record(Result result);
}
