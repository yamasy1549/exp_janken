import java.io.*;
import java.net.*;

class Game implements Runnable {
  Socket sock[] = new Socket[2];
  BufferedReader[] ins = new BufferedReader[2];
  PrintWriter[] outs = new PrintWriter[2];
  String names[] = new String[2];
  JankenServer js;

  Game(JankenServer js, Socket sock0, Socket sock1) {
    this.js = js;
    this.sock[0] = sock0;
    this.sock[1] = sock1;
  }

  public int judge(int hand1, int hand2) {
      // 主体はhand1のPlayer
      // 0:勝ち 1:負け 2:引き分け
      // 0:グー 1:チョキ 2:パー

      if(hand1 > 2 || hand2 > 2) System.exit(1);

      if(hand1 == hand2) {
          return 2;
      } else if(hand1 == 0) {
          return (hand2 == 1) ?  0 : 1;
      } else if(hand1 == 1) {
          return (hand2 == 2) ?  0 : 1;
      } else {
          return (hand2 == 0) ?  0 : 1;
      }
  }

  static String recToStr(String name, int[] game){
      String s = (name + "        ").substring(0,8);
      int i;
      for(i=0; i<20; i++) {
          s = s + game[i];
      }
      return s + "\n";
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
        int[][] gameRec = new int[2][20];
        gameLoop: for(j=0; j<20; j++) {
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
        if(j == 20) {
            String str = recToStr(names[0], gameRec[0]) + recToStr(names[1], gameRec[1]);
            for(i=0; i<20; i++) {
                System.out.println(judge(gameRec[0][i], gameRec[1][i]));
            }
            for(i=0; i<2; i++) {
                outs[i].println(str);
            }
            js.println(str);
        } else {
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
