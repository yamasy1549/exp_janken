package views;

import javax.swing.*;
import java.awt.event.*;
import models.*;
import static original.Constants.*;

public class Main extends JFrame {
    public Main() {
        // 名前入力
        String personName = new InputName().getName();

        // プレーヤー準備
        Person person = new Person(personName);
        Computer computer = new Computer();

        // 名前表示
        JLabel nameArea = new JLabel("Hello, " + personName + "!\nThe opponent is " + computer.getName() + ".");

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
