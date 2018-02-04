import java.util.*;
import java.io.*;
import java.net.*;

// 呼び出し方
// java Janken ホスト名 ポート番号 ユーザー名

class Janken {
    static void janken(BufferedReader in, PrintWriter out) throws IOException {
        Random random = new Random();
        String s = in.readLine();
        System.out.println("対戦相手：" + s);
        int myHands[] = new int[20];
        int opHands[] = new int[20];

        for(int i=0; i<20; i++) {
            int myHand = random.nextInt(3);
            out.println(myHand);
            if((s = in.readLine()) == null) {
                System.exit(1);
            }
            int opHand = Integer.parseInt(s);
            myHands[i] = myHand;
            opHands[i] = opHand;
        }
        while((s = in.readLine()) != null) {
            System.out.println(s);
        }
    }

    public static void main(String args[]) throws IOException {
        if(args.length < 3) System.exit(1);

        String hostname = args[0];
        String port = args[1];
        Player player = new Player(args[2]);

        Socket sock = new Socket(hostname, Integer.parseInt(port));
        BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream(), "UTF-8"));
        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
        out.println(player.name);
        janken(in, out);
    }
}
