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

/*
 *   public void judge(Player player1, Player player2) {
 *       int hand1 = player1.getPlayerHand();
 *       int hand2 = player2.getPlayerHand();
 *       String winner = new String[]
 * 
 *       //ゴキブリ=1, 人=2, 殺虫剤=3
 * 
 *       if(hand1 == hand2) {
 *           js.println("あいこです");
 *       } else if(hand1 == 1) {                //プレーヤ1の手が ゴキブリ であるとき
 *           if(hand2 == 2) {
 *               winner = player1.getName();   //プレーヤ2が　人　だったらプレーヤ1勝つ
 *           } else if(hand2 == 3) {
 *               winner = player2.getName();   //プレーヤ2が　殺虫剤　だったらプレーヤ2勝つ
 *           }
 *       } else if(hand1 == 2) {                //プレーヤ1の手が 人 であるとき
 *           if(hand2 == 1) {
 *               winner = player2.getName();   //プレーヤ2が　ゴキブリ　だったらプレーヤ1勝つ
 *           } else if(hand2 == 3) {
 *               winner = player1.getName();   //プレーヤ2が　殺虫剤　だったらプレーヤ1勝つ
 *           }
 *       } else if(hand1 == 3) {                //プレーヤ1の手が 殺虫剤 であるとき
 *           if(hand2 == 1) {
 *               winner = player2.getName();   //プレーヤ2が　ゴキブリ　だったらプレーヤ1勝つ
 *           }else if(hand2 == 2){
 *               winner = player1.getName();   //プレーヤ2が　人　だったらプレーヤ1勝つ
 *           }
 *       }
 *       js.println(winner + "が勝ちました");
 *   }
 */

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
            String str = JankenRobot.recToStr(names[0], gameRec[0]) + JankenRobot.recToStr(names[1], gameRec[1]);
            int w;
            for(w=0, i=0; i<20; i++) {
                w += JankenRobot.win(gameRec[0][i], gameRec[1][i]);
            }
            if(w > 0) {
                str = str + names[0] + " が " + names[1] + "に" + (w + 20) + " 点で勝ちました";
            }
            else if(w < 0) {
                str = str + names[1] + " が " + names[0] + "に" + (20 - w) + " 点で勝ちました";
            }
            else
                str = str+names[0] + " と " + names[1] + "は引き分けでした";
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
