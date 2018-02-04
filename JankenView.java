import javax.swing.*;
import java.awt.event.*;
import static original.Constants.*;

class JankenView extends JFrame {
    String name;
    JTextArea namearea = new JTextArea("", 1, 10);

    JankenView() {
        setBounds(100, 100, 300, 200);

        namearea.requestFocus();

        // 手ボタン
        JButton[] handButtons = new JButton[3];
        handButtons[0] = new JButton("ROCK");
        handButtons[1] = new JButton("SCISSORS");
        handButtons[2] = new JButton("PAPER");
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
            Hand hand = Hand.valueOf(clickedHand);
            new Janken("192.168.1.3", "9999", name, hand);
        }
    }

    public static void main(String args[]) {
        JankenView view = new JankenView();

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setVisible(true);
    }
}
