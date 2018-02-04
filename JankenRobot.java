import java.io.*;
import java.net.*;
import java.util.*;

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
        for(i=0; i<20; i++) {
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
            int[][] gameRec = new int[2][20];

            for(i=0; i<20; i++){
                int a=robot(i, gameRec);
                if((s = in.readLine()) == null) break;
                int b = Integer.parseInt(s);
                gameRec[0][i] = a;
                gameRec[1][i] = b;
                out.println(a);
            }
            if(i == 20) {
                String str = recToStr(robotNames[type], gameRec[0]) + recToStr(name, gameRec[1]);
                int w;
                for(w=0, i=0; i<20; i++) {
                    w += win(gameRec[0][i], gameRec[1][i]);
                }
                if(w > 0) {
                    str = str + robotNames[type] + " が " + name + "に" + (w + 20) + " 点で勝ちました";
                }
                else if(w < -10) {
                    str = str + name + " が " + robotNames[type] + "に" + (20 - w) + " 点で勝ちました";
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
