package views;

import javax.swing.*;
import models.*;
import static original.Constants.*;

public class ResultLabel extends JLabel {

    public ResultLabel(Result result) {
        updateResult(result);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(false);
    }

    public ResultLabel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(false);
    }

    /**
     * 手ごとのResultを更新する
     * @param result 手ごとのResult
     */
    public void updateResult(Result result) {
        // デバッグ時見やすいように空白入れてるだけ
        setText(result.toString() + " ");
    }
}
