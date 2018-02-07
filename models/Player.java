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

    // カード
    private Card[] cards;

    public Card[] getCards() {
        return this.cards;
    }

    // ポイント
    private int points;

    public int getPoints() {
        return this.points;
    }

    // ゲーム結果
    private Map<Result, Integer> records = new HashMap<Result, Integer>() {
        {
            put(Result.WIN, 0);
            put(Result.LOSE, 0);
            put(Result.DRAW, 0);
        }
    };

    public Player(String name) {
        this.name = name;
        setCards(CARDNUM);
        load();
    }

    public void setCards(int count) {
        this.cards = new Card[count];

        for(int i=0; i<count; i++) {
            Hand hand = Hand.random();
            this.cards[i] = new Card(hand);
        }

    }

    public void betPoints(int points) {
        this.points -= points;
    }

    public void getPoints(int points) {
        this.points += points;
    }

    public void record(Result result) {
        this.records.merge(result, 1, Integer::sum);
        save();
    }

    private void save() {
        try {
            File file = new File(DBDIR + name);
            FileWriter writer = new FileWriter(file);
            String[] _outputs = {
                this.records.get(Result.WIN).toString(),
                this.records.get(Result.LOSE).toString(),
                this.records.get(Result.DRAW).toString(),
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
                this.records.put(Result.WIN, Integer.parseInt(playerData[0]));
                this.records.put(Result.LOSE, Integer.parseInt(playerData[1]));
                this.records.put(Result.DRAW, Integer.parseInt(playerData[2]));
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
