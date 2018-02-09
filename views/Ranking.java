package views;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import static original.Constants.*;

public class Ranking extends JFrame {
    HashMap<String, Integer> map = new HashMap<String, Integer>();

    Ranking() {
        setName("ランキング");
        JTable ranktable;
        DefaultTableModel model;    

        readFile(); 
        List<Entry<String, Integer>> list_entries = new ArrayList<Entry<String, Integer>>(map.entrySet()); // map.Entryのリストを作成する
        Collections.sort(list_entries, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2)
            {
                return obj2.getValue().compareTo(obj1.getValue());
            }
        });
        
        private String[] columnNames = {"ランク", "プレーヤ", "点数"}; 

        
        model = new DefaultTableModel(columnNames,0);
        ranktable = new JTable(model);

        int k = 1;
        for(Entry<String, Integer> entry : list_entries){
            model.addRow(new Object[]{k, entry.getKey(), entry.getValue()});
            k++;
        }

        this.setSize(new Dimension(300,200));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(ranktable,BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void readFile() {
        try {
            // ../dbから以下すべてのファイル名一覧を取得する
            File database = new File("../db");
            String[] filenames = database.list();
            for(String filename: filenames){
                File file = new File(DBDIR + name);

                BufferedReader reader = new BufferedReader(new FileReader(file));
                String[] playerData = reader.readLine().split("\t");
                int win = Integer.parseInt(playerData[0]); //WINの回数
                map.put(filename, win); //ここにWINの回数

                reader.close();
            }
        } catch(IOException ioe) {
            System.out.println(ioe);
        }
    }

}