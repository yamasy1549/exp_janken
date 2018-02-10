package views;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.util.Map.*;
import java.util.stream.*;
import static original.Constants.*;

public class Ranking extends JFrame {
    private HashMap<String, Integer> entries = new HashMap<String, Integer>();

    Ranking() {
        setName("ランキング");

        JTable ranktable;
        DefaultTableModel model;

        readFile();

        java.util.List<Entry<String, Integer>> sortedEntries = entries.entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Entry.comparingByValue()))
            .collect(Collectors.toList());


        String[] columnNames = { "ランク", "プレーヤ", "点数" };

        model = new DefaultTableModel(columnNames, 0);
        ranktable = new JTable(model);
        ranktable.setOpaque(false);
        
        int rank = 1;
        for (Entry<String, Integer> entry: sortedEntries) {
            model.addRow(new Object[] { rank, entry.getKey(), entry.getValue() });
            rank++;
        }

        JPanel rankpanel = new JPanel();
        ImageIcon icon = new ImageIcon("../images/BACKGROUND.png");
        JLabel background = new JLabel();
        background.setIcon(icon);
        rankpanel.add(ranktable);
        rankpanel.setOpaque(false);
        
        this.setContentPane(background);
        this.add(rankpanel, BorderLayout.CENTER);
        this.setSize(new Dimension(300, 200));
        this.setVisible(true);
    }

        this.setSize(new Dimension(300, 200));
        this.add(ranktable, BorderLayout.CENTER);
        this.setVisible(true);
    }

    // DBDIRから以下すべてのファイル名一覧を取得する
    private void readFile() {
        try {
            File database = new File(DBDIR);
            String[] filenames = database.list();

            // filenameがPlayer名
            for(String filename: filenames) {
                File file = new File(DBDIR + filename);

                BufferedReader reader = new BufferedReader(new FileReader(file));
                String[] playerData = reader.readLine().split("\t");
                int winCount = Integer.parseInt(playerData[0]);
                entries.put(filename, winCount);

                reader.close();
            }
        } catch(IOException ioe) {
            System.out.println(ioe);
        }
    }
}
