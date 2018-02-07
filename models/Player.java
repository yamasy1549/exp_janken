package models;

import java.io.*;
import java.util.*;
import views.*;
import static original.Constants.*;

public class Player implements Playable {
    // 名前
    private String name;

    public String getName() {
        return this.name;
    }

    // 手
    private Hand[] hands;

    public Hand[] getHands() {
        return this.hands;
    }

    public Hand getHand(int index) {
        return this.hands[index];
    }

    // ポイント
    private int points;

    public int getPoints() {
        return this.points;
    }

    // ゲーム結果
    private Record records = new Record();

    public int getRecord(Result result) {
        return this.records.get(result);
    }

    // 過去すべてのゲーム結果
    private Record allRecords = new Record();

    public int getAllRecord(Result result) {
        return this.allRecords.get(result);
    }

    public Player(String name) {
        this.name = name;
        setHands(HANDNUM);
        load();
    }

    public void setHands(int count) {
        this.hands = new Hand[count];

        for(int i=0; i<count; i++) {
            this.hands[i] = Hand.random();
        }
    }

    public void betPoints(int points) {
        this.points -= points;
    }

    public void getPoints(int points) {
        this.points += points;
    }

    public void record(Result result) {
        this.records.merge(result, 1);
        save();
    }

    public void allRecord(Result result) {
        this.allRecords.merge(result, 1);
        save();
    }

    private void save() {
        try {
            File file = new File(DBDIR + name);
            FileWriter writer = new FileWriter(file);
            String[] _outputs = {
                Integer.toString(this.allRecords.get(Result.WIN)),
                Integer.toString(this.allRecords.get(Result.LOSE)),
                Integer.toString(this.allRecords.get(Result.DRAW)),
                Integer.toString(this.points)
            };
            writer.write(String.join("\t", _outputs));
            writer.close();
        } catch(IOException ioe) {
            System.out.println(ioe);
        }
    }

    private void load() {
        try {
            File file = new File(DBDIR + name);

            if(file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String[] playerData = reader.readLine().split("\t");
                this.allRecords.set(Result.WIN, Integer.parseInt(playerData[0]));
                this.allRecords.set(Result.LOSE, Integer.parseInt(playerData[1]));
                this.allRecords.set(Result.DRAW, Integer.parseInt(playerData[2]));
                this.points = Integer.parseInt(playerData[3]);
                reader.close();
            } else {
                file.createNewFile();
                save();
            }
        } catch(IOException ioe) {
            System.out.println(ioe);
        }
    }
}
