package models;

import static original.Constants.*;

public class PlayerRate implements Comparable {

    private String name;
    private float rate;

    public PlayerRate(String name, String[] playerData) {
        float winCount = Float.parseFloat(playerData[0]);
        int loseCount = Integer.parseInt(playerData[1]);
        int drawCount = Integer.parseInt(playerData[2]);

        this.name = name;
        if(winCount == 0) {
            this.rate = 0;
        } else {
            this.rate = winCount / (winCount + loseCount + drawCount);
        }
    }

    public String getName() {
        return this.name;
    }

    public float getRate() {
        return this.rate;
    }

    @Override
    public int compareTo(Object another) {
        float thisRate = this.rate;
        float anotherRate = ((PlayerRate)another).getRate();
        if (thisRate > anotherRate) return -1;
        if (thisRate < anotherRate) return 1;
        return Float.compare(thisRate, anotherRate);
    }
}
