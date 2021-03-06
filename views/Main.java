package views;

import javax.swing.*;
import models.*;
import static original.Constants.*;

public class Main extends JFrame {

    public Main() {
        setName("Main");

        // 名前入力
        String personName;
        do {
            personName = new InputName().getName();
            if(personName == null) return;
        } while(personName.equals(""));

        // プレーヤー準備
        Person person = new Person(personName);
        Computer computer = new Computer();

        // 配置
        PlayPanel panel = new PlayPanel(person, computer);
        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setVisible(true);
    }

    public Main(String name) {
        // プレーヤー準備
        Person person = new Person(name);
        Computer computer = new Computer();

        // 配置
        PlayPanel panel = new PlayPanel(person, computer);
        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setVisible(true);
    }
}
