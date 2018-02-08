package views;

import javax.swing.*;
import java.awt.event.*;

public class HelpButton extends JButton {
    public HelpButton() {
        setText("遊び方");
        addActionListener(new helpListener());
    }

    public class helpListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // TODO: 遊び方を説明したウィンドウ表示
        }
    }
}
