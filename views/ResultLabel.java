package views;

import java.awt.*;
import javax.swing.*;
import models.*;
import static original.Constants.*;

public class ResultLabel extends JJLabel {

    ResultLabel(Result result) {
        super(result.toString());

        updateResult(result);
    }

    public ResultLabel() {
        super("");
    }

    /**
     * 手ごとのResultを更新する
     * @param result 手ごとのResult
     */
    public void updateResult(Result result) {
        setText(result.toString());
    }
}
