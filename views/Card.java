package views;

import javax.swing.*;
import java.awt.event.*;
import static original.Constants.*;

public class Card extends JButton {
    private String _hand;

    public Card(Hand hand) {
        this._hand = hand.toString();
        setActionCommand(this._hand);
        addActionListener(new cardListener());
        close();
    }

    public class cardListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Card clickedCard = (Card)e.getSource();
            clickedCard.open();
        }
    }

    public void close() {
        ImageIcon icon = new ImageIcon("./images/CLOSE.png");
        setIcon(icon);
    }

    public void open() {
        ImageIcon icon = new ImageIcon("./images/" + this._hand + ".png");
        setIcon(icon);
    }
}
