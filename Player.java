import java.io.*;
import static original.Constants.*;

public class Player {
    private String name;
    private int[] counts = new int[3];
    private int points;

    Player(String name) {
        try {
            System.out.println(this.points);
            this.name = name;
            File file = new File("./db/" + name);

            if(file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String[] playerData = reader.readLine().split("\t");
                setWinCounts(Integer.parseInt(playerData[0]));
                setLoseCounts(Integer.parseInt(playerData[1]));
                setDrawCounts(Integer.parseInt(playerData[2]));
                setPoints(Integer.parseInt(playerData[3]));
                reader.close();
            } else {
                file.createNewFile();
                save();
            }
        } catch(IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void setResult(Result result) {
        if(result == Result.WIN)
            this.counts[0]++;
        if(result == Result.LOSE)
            this.counts[1]++;
        if(result == Result.DRAW)
            this.counts[2]++;
        save();
    }

    public void setWinCounts(int counts) {
        this.counts[0] += counts;
    }

    public void setLoseCounts(int counts) {
        this.counts[1] += counts;
    }

    public void setDrawCounts(int counts) {
        this.counts[2] += counts;
    }

    public void setPoints(int points) {
        this.points += points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void save() {
        try {
            File file = new File("./db/" + name);
            FileWriter writer = new FileWriter(file);
            String[] playerData = {
                Integer.toString(counts[0]),
                Integer.toString(counts[1]),
                Integer.toString(counts[2]),
                Integer.toString(points)
            };
            writer.write(String.join("\t", playerData));
            writer.close();
        } catch(IOException ioe) {
            System.out.println(ioe);
        }
    }
}
