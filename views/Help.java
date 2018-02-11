package views;

import java.awt.*;
import java.io.*;
import java.io.IOException;
import javax.swing.*;
import java.util.*;
import models.*;
import static original.Constants.*;

public class Help extends JFrame {

    Help() {
        setName("遊び方");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel(new ImageIcon("./images/HELP1.png")));
        panel.add(new JLabel(new ImageIcon("./images/HELP2.png")));

        add(panel);
        pack();
    }
}
