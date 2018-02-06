package original;

public class Constants {
    public static final int JankenServerPort = 9999;

    public static final String DBDIR = "./db/";

    public static enum Hand {
        ROCK,
        SCISSORS,
        PAPER
    }

    public static enum Result {
        WIN,
        LOSE,
        DRAW
    }
}
