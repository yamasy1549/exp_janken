import java.util.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import static original.Constants.*;

// 呼び出し方
// java Janken ホスト名 ポート番号 ユーザー名 手 出力

class Janken {
    static Player player;
    static Hand myHand;

    Janken(String hostname, int port, Player player, Hand myHand, JTextArea logArea) {
        this.player = player;
        this.myHand = myHand;

        try {
            Socket sock = new Socket(hostname, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream(), "UTF-8"));
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            PrintWriter viewout = new PrintWriter(new TextAreaWriter(logArea), true);
            out.println(this.player.getName());
            janken(in, out, viewout);
        } catch(IOException ioe) {
            System.out.println(ioe);
        }
    }

    public class TextAreaWriter extends Writer {
        protected final JTextArea text;

        public TextAreaWriter(JTextArea text) {
            this.text = text;
        }

        @Override
        public void write(char[] c, int off, int len) {
            text.append(new String(c, off, len));
        }

        @Override
        public void flush() { }

        @Override
        public void close() { }
    }

    static void janken(BufferedReader in, PrintWriter out, PrintWriter viewout) throws IOException {
        String s = in.readLine();
        viewout.println("対戦相手：" + s);

        out.println(myHand);
        if((s = in.readLine()) == null) {
            System.exit(1);
        }

        while((s = in.readLine()) != null) {
            viewout.println(s);
            try {
                player.setResult(Result.valueOf(s));
            } catch(Exception e) {
            }
        }
    }
}
