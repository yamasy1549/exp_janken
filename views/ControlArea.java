package views;

import javax.swing.*;
import models.*;
import static original.Constants.*;

public class ControlArea extends JPanel {
    public ControlArea(Player player) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        HelpButton helpButton = new HelpButton();
        StartButton startButton = new StartButton();
        add(helpButton);
        add(startButton);
    }
}
