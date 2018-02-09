package views;

import java.awt.*;
import javax.swing.*;
import static original.Constants.*;

public class JJButton extends JButton {

    JJButton(String text) {
        super(text);
        setOpaque(true);
        setBorderPainted(false);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        setForeground(BLACK);
        setBackground(MINT);
    }
}
