package views;

import javax.swing.*;
import java.awt.event.*;
import static original.Constants.*;

public class Card extends JButton {
    public Card(Hand hand) {
        super();

        String _hand = hand.toString();
        ImageIcon icon = new ImageIcon("./images/" + _hand + ".png");

        setActionCommand(_hand);
        setIcon(icon);
        addActionListener(new cardListener());
    }

    public class cardListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String clickedCard = e.getActionCommand();
            Hand hand = Hand.valueOf(clickedCard);
            // TODO
        }
    }
}
