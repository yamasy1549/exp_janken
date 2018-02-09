package views;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import static original.Constants.*;

public class Asobikata extends JFrame {
    HashMap<String, Integer> map = new HashMap<String, Integer>();

    Asobikata() {
        setName("ランキング");

        readFile();
        // TODO mapをIntegerでsort(DESC)
        // TableとかLabelとかに入れる
        // e.g. 1. NAME xxpt
        //      2. NAME xxpt
        //      ...

        this.setVisible(true);
    }

    private void readFile() {

        try {
            // ../dbから以下すべてのファイル名一覧を取得する
            // String[] filenames;
            for(String filename: filenames) {
                File file = new File(DBDIR + name);

                BufferedReader reader = new BufferedReader(new FileReader(file));
                String[] playerData = reader.readLine().split("\t");
                Integer.parseInt(playerData[0]); //WINの回数
                map.put(filename, //ここにWINの回数)

                reader.close();
            }
        } catch(IOException ioe) {
            System.out.println(ioe);
        }
    }

}
