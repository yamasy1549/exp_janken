import javax.swing.*;
import java.awt.event.*;
import models.*;
import static original.Constants.*;

class JankenGame extends JFrame {
    Person person;
    JTextArea namearea = new JTextArea("", 1, 10);

    JankenGame() {
        // 名前入力
        String name = JOptionPane.showInputDialog(this, "Input your name");
        person = new Person(name);

        // 名前表示
        JLabel nameArea = new JLabel("Hello, " + name + "!");

        // 手ボタン
        JButton[] handButtons = new JButton[3];
        handButtons[0] = new JButton(Hand.ROCK.toString());
        handButtons[1] = new JButton(Hand.SCISSORS.toString());
        handButtons[2] = new JButton(Hand.PAPER.toString());
        for(JButton button: handButtons) {
            button.addActionListener(new handListener());
        }

        // 配置
        // TODO: レイアウト
        JPanel panel = new JPanel();
        panel.add(nameArea);
        for(JButton button: handButtons) {
            panel.add(button);
        }

        getContentPane().add(panel);
        setBounds(100, 100, 500, 500);
    }

    public class handListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String clickedHand = e.getActionCommand();
            Hand hand = Hand.valueOf(clickedHand);
        }
    }

    public static void main(String args[]) {
        JankenGame game = new JankenGame();

        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
    }
}
