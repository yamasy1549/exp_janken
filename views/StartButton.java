package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import models.*;
import static original.Constants.*;

public class StartButton extends JJButton {

    private Player player1, player2;

    public StartButton(Player player1, Player player2) {
        super("スタート");

        this.player1 = player1;
        this.player2 = player2;

        addActionListener(new startListener());
    }

    public class startListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Judge judge = new Judge();

            Result playResult1 = judge.judgePlayers(player1, player2);
            player1.allRecord(playResult1);
            Result playResult2 = judge.judgePlayers(player2, player1);
            player2.allRecord(playResult2);

            for(Component component : getParent().getParent().getComponents()) {
                if(component.getName() == null) continue;

                switch(component.getName()) {
                    case "ResultArea":
                        // Result表示を更新する
                        ((ResultArea)component).updateResults(playResult1);
                        break;
                    case "PlayerArea":
                        // Cardを全てopenにする
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
