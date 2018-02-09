package views;

import java.awt.*;
import javax.swing.*;
import models.*;
import static original.Constants.*;

public class ResultLabel extends JLabel {

    public ResultLabel(Result result) {
        updateResult(result);
        setOpaque(false);
        setForeground(Color.WHITE);
    }

    public ResultLabel() {
        setOpaque(false);
        setForeground(Color.WHITE);
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
