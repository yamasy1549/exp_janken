import java.util.*;
import java.io.*;
import java.net.*;
import static original.Constants.*;

// 呼び出し方
// java Janken ホスト名 ポート番号 ユーザー名 手

class Janken {
    Player player;
    static Hand myHand;

    Janken(String hostname, String port, String name, Hand myHand) {
        this.player = new Player(name);
        this.myHand = myHand;

        try {
            Socket sock = new Socket(hostname, Integer.parseInt(port));
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream(), "UTF-8"));
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            out.println(this.player.name);
            janken(in, out);
        } catch(IOException ioe) {
            System.out.println(ioe);
        }
    }

    static void janken(BufferedReader in, PrintWriter out) throws IOException {
        String s = in.readLine();
        System.out.println("対戦相手：" + s);

        out.println(myHand);
        if((s = in.readLine()) == null) {
            System.exit(1);
        }

        while((s = in.readLine()) != null) {
            System.out.println(s);
        }
    }
}
