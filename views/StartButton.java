package views;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import models.*;
import static original.Constants.*;

public class StartButton extends JButton {
    private Player player1, player2;

    public StartButton(Player player1, Player player2) {
        setText("スタート");
        addActionListener(new startListener());

        this.player1 = player1;
        this.player2 = player2;
    }

    public class startListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Judge judge = new Judge();

            Result playResult = judge.judgePlayers(player1, player2);
            player1.allRecord(playResult);

            for(Component component : getParent().getParent().getComponents()) {
                if(component.getName() == null) continue;
                switch(component.getName()) {
                    case "ResultArea":
                        ((ResultArea)component).updateHands(playResult);
                        break;
                    case "PlayerArea":
                        for(Component areaComponent : ((PlayerArea)component).getComponents()) {
                            if(areaComponent instanceof Deck)
                                ((Deck)areaComponent).open();
                        }
                        break;
                }
            }
        }
    }
}
