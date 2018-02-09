package views;

import javax.swing.*;
import models.*;
import static original.Constants.*;

public class ControlArea extends JJPanel {

    ControlArea(Player player1, Player player2) {
        super("ControlArea");

        HelpButton helpButton = new HelpButton();
        addAndSetBounds(helpButton, 25, 470, 150, 80);

        StartButton startButton = new StartButton(player1, player2);
        addAndSetBounds(startButton, 25, 570, 150, 80);
    }
}
