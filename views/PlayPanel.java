package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import models.*;
import static original.Constants.*;

public class PlayPanel extends JPanel {

    public PlayPanel(Player player1, Player player2) {
        setLayout(null);

        PlayerArea playerArea2 = new PlayerArea(player2);
        addAndSetBounds(playerArea2, 0, 0, 1000, 300);

        PlayerArea playerArea1 = new PlayerArea(player1);
        addAndSetBounds(playerArea1, 0, 400, 1000, 300);

        ResultArea resultArea = new ResultArea(player2, player1);
        addAndSetBounds(resultArea, 0, 300, 1000, 100);

        ControlArea controlArea = new ControlArea(player2, player1);
        addAndSetBounds(controlArea, 1000, 0, 200, 700);
    }

    /**
     * JPanelにComponentを絶対位置指定して追加する
     * @param component 追加するComponent
     * @param x Componentのx座標
     * @param y Componentのy座標
     * @param width Componentの幅
     * @param height Componentの高さ
     */
    private void addAndSetBounds(Component component, int x, int y, int width, int height) {
        component.setBounds(x, y, width, height);
        add(component);
    }

    /**
     * 背景画像を設定する
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        try {
            BufferedImage background;
            background = ImageIO.read(getClass().getResource("../images/BACKGROUND.png"));
            g.drawImage(background, 0, 0, this);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}
