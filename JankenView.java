import javax.swing.*;
import java.awt.event.*;

class JankenView extends JFrame implements ActionListener {
    public static void main(String args[]) {
        JankenView test = new JankenView();

        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setVisible(true);
    }

    JankenView() {
        setBounds(10, 10, 300, 200);

        JButton btn = new JButton("Start");
        btn.addActionListener(this);

        JPanel p = new JPanel();
        p.add(btn);

        getContentPane().add(p);
    }

    public void actionPerformed(ActionEvent e){
        new Janken("192.168.1.3", "9999", "user1");
    }
}
