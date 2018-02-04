public class Player {
    String name;
    private int playerHand = 0;
    private int points = 0;

    public Player(String name) {
        this.name = name;
    }

    public void setPlayerHand(int hand) {
        this.playerHand = hand;
    }

    public void setPoints() {
        this.points = this.points++;
    }

    public String getName() {
        return name;
    }

    public int getPlayerHand() {
        return playerHand;
    }

    public int getPoints() {
        return points;
    }
}
