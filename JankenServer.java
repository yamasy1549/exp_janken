import java.io.*;
import java.net.*;
import static original.Constants.*;

// 呼び出し方
// java JankenServer

class JankenServer {
    public JankenServer(int port){
        Socket sock, waitingSock = null;
        try {
            ServerSocket servsock = new ServerSocket(port);
            while(true) {
                sock = servsock.accept();
                if(waitingSock == null) {
                    waitingSock = sock;
                } else {
                    new Thread(new Game(this, waitingSock, sock)).start();
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
        new JankenServer(JankenServerPort);
    }
}
