package views;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.util.Map.*;
import java.util.stream.*;
import java.io.IOException;
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
        Color colour = new Color(0, 0, 0, 0);

        model = new DefaultTableModel(columnNames, 0);
        ranktable = new JTable(model);
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        ranktable.getColumn("ランク").setCellRenderer(dtcr);
        ranktable.getColumn("プレーヤ").setCellRenderer(dtcr);
        ranktable.getColumn("点数").setCellRenderer(dtcr);
        ranktable.setBackground(BLACK);
        ranktable.setForeground(Color.WHITE);
        ranktable.setGridColor(MINT);
        ranktable.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        ranktable.setRowHeight(30);
        ranktable.setOpaque(true);
        JTableHeader header = ranktable.getTableHeader();
        header.setBackground(BLACK);
        header.setForeground(MINT);
        header.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

        JScrollPane sp = new JScrollPane(ranktable);

        int rank = 1;
        for (Entry<String, Integer> entry: sortedEntries) {
            model.addRow(new Object[] { rank, entry.getKey(), entry.getValue() });
            rank++;
        }
        
        this.add(sp,  BorderLayout.CENTER);
        this.setSize(new Dimension(500, 300));
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
