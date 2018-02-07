package views;

import javax.swing.*;
import java.awt.event.*;

public class StartButton extends JButton {
    public StartButton() {
        setText("スタート");
        addActionListener(new startListener());
    }

    public class startListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // TODO
        }
    }
}
