package views;

import java.awt.*;
import java.io.*;
import java.io.IOException;
import javax.swing.*;
import java.util.*;
import models.*;
import static original.Constants.*;

public class Ranking extends JFrame {

    Ranking() {
        setName("ランキング");

        // PlayerRate[]をrateで降順ソート
        PlayerRate[] sortedEntries = sort(loadEntries());
        String[][] rowData = new String[sortedEntries.length][3];
        int i = 0;
        for(PlayerRate playerRate: sortedEntries) {
            String rank = Integer.toString(i+1);
            String name = playerRate.getName();
            String rate = Float.toString(playerRate.getRate());
            String[] entry = { rank, name, rate };
            rowData[i] = entry;
            i++;
        }

        String[] columnNames = { "順位", "プレーヤ", "勝率" };

        RankingTable rankingTable = new RankingTable(rowData, columnNames);
        JScrollPane sp = new JScrollPane(rankingTable);

        add(sp, BorderLayout.CENTER);
        setSize(new Dimension(500, 300));
        setVisible(true);
    }

    /**
     * DBDIRに保存されているPlayer情報から勝率を集計する
     * @return 保存されているすべてのPlayerのPlayerRate
     */
    private PlayerRate[] loadEntries() {
        PlayerRate[] playerRates = new PlayerRate[1];

        try {
            // DBDIR以下すべてのファイル名一覧を取得する
            File database = new File(DBDIR);
            String[] filenames = database.list();

            // Player名とwinCountをペアで持っておく
            playerRates = new PlayerRate[filenames.length];

            for(int i=0; i<filenames.length; i++) {
                String filename = filenames[i];
                File file = new File(DBDIR + filename);

                BufferedReader reader = new BufferedReader(new FileReader(file));
                String[] playerData = reader.readLine().split("\t");
                // filenameがPlayer名
                playerRates[i] = new PlayerRate(filename, playerData);

                reader.close();
            }
        } catch(IOException ioe) {
            System.out.println(ioe);
        }

        return playerRates;
    }

    /**
     * PlayerRateを非破壊ソートする
     * @param array ソートしたいPlayerRateの配列
     * @return ソート結果
     */
    private PlayerRate[] sort(PlayerRate[] array) {
        PlayerRate[] sorted = array;
        Arrays.sort(sorted);
        return sorted;
    }
}
