package views;

import javax.swing.*;
import java.awt.event.*;
import models.*;
import views.*;
import static original.Constants.*;

public class Main extends JFrame {
    Person person;
    JTextArea namearea = new JTextArea("", 1, 10);

    public Main() {
        // 名前入力
        person = new Person(new InputName().getName());

        // 名前表示
        JLabel nameArea = new JLabel("Hello, " + person.getName() + "!");

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

        this.add(panel);
        setBounds(100, 100, 500, 500);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public class handListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String clickedHand = e.getActionCommand();
            Hand hand = Hand.valueOf(clickedHand);
        }
    }
}
