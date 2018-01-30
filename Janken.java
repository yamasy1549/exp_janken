import java.util.*;
import java.io.*;
import java.net.*;

class Janken {
    static void janken(BufferedReader in, PrintWriter out) throws IOException {
        Random random = new Random();
        String s = in.readLine();
        System.out.println("対戦相手は" + s);
        // 自分の手と相手の手を記録するための配列
        int myHands[] = new int[50];
        int opHands[] = new int[50];
        // 50回の繰り返し
        for(int i=0; i<50; i++) {
            // 自分の手は乱数で
            int myHand = random.nextInt(3);
            // 自分の手を送る
            out.println(myHand);
            // サーバからの入力をもらう
            if((s = in.readLine()) == null) {
                System.exit(1);
            }
            // 相手の手を受け取る
            int opHand = Integer.parseInt(s);
            // 記録する
            myHands[i] = myHand;
            opHands[i] = opHand;
        }
        while((s = in.readLine()) != null) {
            System.out.println(s);
        }
    }

    public static void main(String args[]) throws IOException {
        if(args.length < 3) usage();

        Socket sock = new Socket(args[0], Integer.parseInt(args[1]));
        // サーバから送られてくるメッセージの文字コードはUTF-8なので，
        // その変換をおこなうInputStreamReaderを作る
        BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream(), "UTF-8"));
        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
        out.println(args[2]);
        janken(in, out);
    }

    static void usage() {
        System.err.println("使い方: java Janken ホスト名 ポート番号 ユーザー名");
        System.exit(1);
    }
}
