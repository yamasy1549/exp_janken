package original;

import java.util.*;

public class Constants {
    public static final int JankenServerPort = 9999;

    public static final String DBDIR = "./db/";

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
}
