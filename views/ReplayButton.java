package views;

import java.awt.event.*;
import javax.swing.*;

public class ReplayButton extends JJButton {

    private String name;

    ReplayButton(String name) {
        super("リプレイ");

        this.name = name;

        addActionListener(new replayListener());
    }

    public class replayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new Main(name);
        }
    }
}
