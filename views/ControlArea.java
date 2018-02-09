package views;

import javax.swing.*;
import models.*;
import static original.Constants.*;

public class ControlArea extends JPanel {

    ControlArea(Player player1, Player player2) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        HelpButton helpButton = new HelpButton();
        StartButton startButton = new StartButton(player1, player2);

        add(helpButton);
        add(startButton);
    }
}
