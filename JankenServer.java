import java.io.*;
import java.net.*;
import java.util.*;

class JankenGame implements Runnable {
  Socket sock[] = new Socket[2];
  BufferedReader[] ins = new BufferedReader[2];
  PrintWriter[] outs = new PrintWriter[2];
  String names[] = new String[2];
  JankenServer js;

  JankenGame(JankenServer js, Socket sock0, Socket sock1) {
    this.js = js;
    this.sock[0] = sock0;
    this.sock[1] = sock1;
  }

  public void run() {
    int i, j;

    try {
        for(i=0; i<2; i++) {
            outs[i] = new PrintWriter(sock[i].getOutputStream(), true);
            ins[i] = new BufferedReader(new InputStreamReader(sock[i].getInputStream()));
            names[i] = ins[i].readLine();
        }
        for(i=0; i<2; i++) {
            outs[i].println(names[1-i]);
        }
        String[] s = new String[2];
        int[][] gameRec = new int[2][50];
        gameLoop: for(j=0; j<50; j++) {
            for(i=0; i<2; i++) {
                if((s[i] = ins[i].readLine()) == null) {
                    break gameLoop;
                }
                gameRec[i][j] = Integer.parseInt(s[i]);
            }
            for(i=0; i<2; i++){
                outs[i].println(gameRec[1-i][j]);
            }
        }
        if(j == 50) {
            String str = JankenRobot.recToStr(names[0], gameRec[0]) + JankenRobot.recToStr(names[1], gameRec[1]);
            int w;
            for(w=0, i=0; i<50; i++) {
                w += JankenRobot.win(gameRec[0][i], gameRec[1][i]);
            }
            if(w > 0) {
                str = str + names[0] + " が " + names[1] + "に" + (w + 50) + " 点で勝ちました";
            }
            else if(w < 0) {
                str = str + names[1] + " が " + names[0] + "に" + (50 - w) + " 点で勝ちました";
            }
            else
                str = str+names[0] + " と " + names[1] + "は引き分けでした";
            for(i=0; i<2; i++) {
                outs[i].println(str);
            }
            js.println(str);
        }
        else {
            for(i=0; i<2; i++) {
                outs[i].println("プログラムが途中で中断しました");
            }
            js.println("プログラムが途中で中断しました");
        }
        sock[0].close();
        sock[1].close();
    } catch(IOException e) {
        System.err.println(e);
    }
  }
}

class JankenRobot implements Runnable {
    Socket sock;
    JankenServer js;
    int type;
    PrintWriter out;
    BufferedReader in;
    String[] robotNames = {"", "Kodomo", "Otona"};

    JankenRobot(JankenServer js, Socket sock, int type){
        this.js = js;
        this.sock = sock;
        this.type = type;
    }

    // m0がm1に勝つ時は 1, 引き分けが0,敗けは -1
    static int win(int m0, int m1) {
        if(m0 - m1 == -1 || m0 - m1 == 2) return 1;
        else if(m0 == m1) return 0;
        else return -1;
    }

    static String recToStr(String name, int[] game){
        String s = (name + "        ").substring(0,8);
        int i;
        for(i=0; i<50; i++) {
            s = s + game[i];
        }
        return s + "\n";
    }

    Random r = new Random();
    int ir;
    int robot(int i, int[][] rec) {
        if(i == 0) {
            ir = r.nextInt(3);
            return ir;
        }
        switch(type) {
            case 1: /* Kodomo: いつも同じものを出す */
                return ir;
            case 2: { /* Otona: 直前の自分の手,相手の手を元に次の手を推察する */
                int j;
                int hist[] = new int[3];
                if(i == 1) return (rec[1][0] + 2) % 3;
                for(j=1; j< i; j++) {
                    if(rec[0][j-1] == rec[0][i-1] && rec[1][j-1] == rec[1][i-1]) {
                        hist[rec[1][j]]++;
                    }
                }
                if(hist[0] >= hist[1] && hist[0] >= hist[2]) return 2;
                else if(hist[1] >= hist[2]) return 0;
                else return 1;
            }
        }
        return 0;
    }

    public void run() {
        try {
            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out.println(robotNames[type]);
            int i;
            String name = in.readLine();
            String s = null;
            int[][] gameRec = new int[2][50];

            for(i=0; i<50; i++){
                int a=robot(i, gameRec);
                if((s = in.readLine()) == null) break;
                int b = Integer.parseInt(s);
                gameRec[0][i] = a;
                gameRec[1][i] = b;
                out.println(a);
            }
            if(i == 50) {
                String str = recToStr(robotNames[type], gameRec[0]) + recToStr(name, gameRec[1]);
                int w;
                for(w=0, i=0; i<50; i++) {
                    w += win(gameRec[0][i], gameRec[1][i]);
                }
                if(w > 0) {
                    str = str + robotNames[type] + " が " + name + "に" + (w + 50) + " 点で勝ちました";
                }
                else if(w < -10) {
                    str = str + name + " が " + robotNames[type] + "に" + (50 - w) + " 点で勝ちました";
                } else {
                    str = str + name + " と " + robotNames[type] + "は引き分けでした";
                }
                out.println(str);
                js.println(str);
            } else {
                out.println("プログラムが途中で中断しました");
                js.println("プログラムが途中で中断しました");
            }
            sock.close();
        }
        catch(IOException e) {
            System.err.println(e);
        }
    }
}

// 呼び出し方
// java JankenServer 対戦モード ポート番号
// 対戦モードは
// 0 自由対戦
// 1-2 対応するプログラム

class JankenServer {
    public JankenServer(int type, int port){
        Socket sock, waitingSock = null;
        try {
            // ServerSocketを作成
            ServerSocket servsock = new ServerSocket(port);
            // 無限ループ，breakが来るまで
            while(true) {
                // クライアントからのアクセスをうけつけた．
                sock = servsock.accept();
                if(type == 0){
                    if(waitingSock == null) {
                        waitingSock = sock;
                    } else {
                        new Thread(new JankenGame(this, waitingSock, sock)).start();
                        waitingSock = null;
                    }
                }
                else if(type <= 5){
                    new Thread(new JankenRobot(this, sock, type)).start();
                    waitingSock = null;
                }
            }
        } catch(IOException ioe) {
            System.out.println(ioe);
        }
    }

    synchronized void println(String s) {
        System.err.println(s);
    }

    public static void main(String args[]) {
        // インスタンスを1つだけ作る．
        new JankenServer(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    }
}
