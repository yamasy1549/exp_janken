package views;

import java.awt.event.*;
import javax.swing.*;

public class HelpButton extends JJButton {

    HelpButton() {
        super("遊び方");

        addActionListener(new helpListener());
    }

    public class helpListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Help help = new Help();
            help.setVisible(true);
        }
    }
}
