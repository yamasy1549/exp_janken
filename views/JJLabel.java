package views;

import java.awt.*;
import javax.swing.*;
import static original.Constants.*;

public class JJLabel extends JLabel {

    JJLabel(String text) {
        super(text);
        setOpaque(false);
        setForeground(WHITE);
        setHorizontalAlignment(JLabel.CENTER);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        setPreferredSize(new Dimension(200, 100));
    }
}
