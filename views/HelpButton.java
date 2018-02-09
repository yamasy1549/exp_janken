package views;

import java.awt.event.*;
import javax.swing.*;

public class HelpButton extends JButton {

    HelpButton() {
        setText("遊び方");
        addActionListener(new helpListener());
    }

    public class helpListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // TODO: 遊び方を説明したウィンドウ表示
            // view/Asobikata.javaをつくる
            // class Asobikata extends JFrame
            new Asobikata();
        }
    }
}
