package views;

import javax.swing.*;
import models.*;
import static original.Constants.*;

public class PlayerArea extends JJPanel {

    PlayerArea(Player player) {
        super("PlayerArea");

        Deck deck = new Deck(player);
        PlayerInfo playerInfo = new PlayerInfo(player);
        addAndSetBounds(deck, 0, 0, 870, 300);
        addAndSetBounds(playerInfo, 870, 0, 200, 300);
    }
}
