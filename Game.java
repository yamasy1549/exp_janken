import java.io.*;
import java.net.*;
import static original.Constants.*;

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

  public Result judge(Hand hand1, Hand hand2) {
      // 主体はhand1のPlayer

      if(hand1 == hand2) {
          return Result.DRAW;
      } else if(hand1 == Hand.ROCK) {
          return (hand2 == Hand.SCISSORS) ? Result.WIN : Result.LOSE;
      } else if(hand1 == Hand.SCISSORS) {
          return (hand2 == Hand.PAPER) ? Result.WIN : Result.LOSE;
      } else {
          return (hand2 == Hand.ROCK) ? Result.WIN : Result.LOSE;
      }
  }

  static String recToStr(String name, Hand hand) {
      return (name + "        ").substring(0,8) + hand.toString() + "\n";
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
        Hand[] gameRec = new Hand[2];

        for(i=0; i<2; i++) {
            if((s[i] = ins[i].readLine()) == null) {
                break;
            }
            gameRec[i] = Hand.valueOf(s[i]);
        }
        for(i=0; i<2; i++){
            outs[i].println(gameRec[1-i]);
        }

        String str = recToStr(names[0], gameRec[0]) + recToStr(names[1], gameRec[1]);
        for(i=0; i<2; i++) {
            outs[i].println(str);

            Result result = (i == 0) ? judge(gameRec[0], gameRec[1]) : judge(gameRec[1], gameRec[0]);
            outs[i].println(result);
        }
        js.println(str);

        sock[0].close();
        sock[1].close();
    } catch(IOException e) {
        System.err.println(e);
    }
  }
}
