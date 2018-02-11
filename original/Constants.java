package original;

import java.awt.*;
import java.util.*;

public class Constants {
    public static final int JankenServerPort = 9999;

    public static final String DBDIR = "./db/";

    public static final int CARD_WIDTH = 158;
    public static final int CARD_HEIGHT = 192;

    public static final int HANDNUM = 5;

    public static enum Hand {
        ROCK,
        SCISSORS,
        PAPER;

        private static final Hand[] VALUES = values();

        private static final int SIZE = VALUES.length;

        public static Hand random()  {
            int random = new Random().nextInt(SIZE);
            return VALUES[random];
        }
    }

    public static enum Result {
        WIN,
        LOSE,
        DRAW
    }

    public static final Color BLACK = new Color(23, 31, 38);
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color GRAY = new Color(51, 51, 51);
    public static final Color MINT = new Color(80, 171, 164);
}
