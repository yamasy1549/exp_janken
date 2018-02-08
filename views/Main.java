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

        // 配置
        // TODO: レイアウト
        JPanel panel = new JPanel();
        panel.add(new PlayerArea(computer));
        panel.add(new ResultArea(person, computer));
        panel.add(new PlayerArea(person));
        panel.add(new ControlArea(person, computer));

        this.add(panel);
        setSize(1000, 700);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
