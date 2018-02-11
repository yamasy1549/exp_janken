package views;

import javax.swing.*;
import models.*;
import static original.Constants.*;

public class ControlArea extends JJPanel {

    ControlArea(Player player1, Player player2) {
        super("ControlArea");

        HelpButton helpButton = new HelpButton();
        addAndSetBounds(helpButton, 10, 270, 150, 80);

        RankingButton rankingButton = new RankingButton();
        addAndSetBounds(rankingButton, 10, 370, 150, 80);

        ReplayButton replayButton = new ReplayButton(player1.getName());
        addAndSetBounds(replayButton, 10, 470, 150, 80);

        StartButton startButton = new StartButton(player1, player2);
        addAndSetBounds(startButton, 10, 570, 150, 80);
    }
}
