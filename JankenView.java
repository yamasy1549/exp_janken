import javax.swing.*;
import java.awt.event.*;

class JankenView extends JFrame {
    String name;
    JTextArea namearea = new JTextArea("", 1, 10);

    JankenView() {
        setBounds(100, 100, 300, 200);

        namearea.requestFocus();

        // 手ボタン
        JButton[] handButtons = new JButton[3];
        handButtons[0] = new JButton("グー");
        handButtons[1] = new JButton("チョキ");
        handButtons[2] = new JButton("パー");
        for(JButton button: handButtons) {
            button.addActionListener(new handListener());
        }

        JPanel panel = new JPanel();
        panel.add(namearea);
        for(JButton button: handButtons) {
            panel.add(button);
        }

        getContentPane().add(panel);
    }

    public class handListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = namearea.getText();
            if(name == "") System.exit(1);
            name = name;

            String clickedHand = e.getActionCommand();
            int hand = 0;

            switch(clickedHand) {
                case "グー":
                    hand = 0;
                    break;
                case "チョキ":
                    hand = 1;
                    break;
                case "パー":
                    hand = 2;
            }

            new Janken("192.168.1.3", "9999", name, hand);
        }
    }

    public static void main(String args[]) {
        JankenView view = new JankenView();

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setVisible(true);
    }
}
