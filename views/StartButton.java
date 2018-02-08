package views;

import javax.swing.*;
import java.awt.event.*;
import models.*;
import static original.Constants.*;

public class StartButton extends JButton {
    private Player player1, player2;

    public StartButton(Player player1, Player player2) {
        setText("スタート");
        addActionListener(new startListener());

        this.player1 = player1;
        this.player2 = player2;
    }

    public class startListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Judge judge = new Judge();
            Result result = judge.judgePlayers(player1, player2);
            System.out.println(result);
            player1.allRecord(result);
        }
    }
}
