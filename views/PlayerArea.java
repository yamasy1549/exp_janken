package views;

import javax.swing.*;
import models.*;
import static original.Constants.*;

public class PlayerArea extends JJPanel {

    PlayerArea(Player player) {
        super("PlayerArea");

        PlayerInfo playerInfo = new PlayerInfo(player);
        Deck deck = new Deck(player);
        addAndSetBounds(playerInfo, 0, 0, 200, 300);
        addAndSetBounds(deck, 200, 0, 900, 300);
    }
}
