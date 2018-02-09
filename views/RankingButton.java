package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import models.*;
import static original.Constants.*;

public class RankingButton extends JJButton {

    public RankingButton() {
        super("ランキング");

        addActionListener(new rankingListener());
    }

    public class rankingListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Ranking ranking = new Ranking();
            ranking.setVisible(true);
        }
    }
}

