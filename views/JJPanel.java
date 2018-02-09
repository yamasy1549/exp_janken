package views;

import java.awt.*;
import javax.swing.*;
import models.*;
import static original.Constants.*;

class JJPanel extends JPanel {

    JJPanel(String name) {
        super();
        setName(name);
        setLayout(null);
        setOpaque(false);
    }

    /**
     * Componentを絶対位置指定して追加する
     * @param component 追加するComponent
     * @param x Componentのx座標
     * @param y Componentのy座標
     * @param width Componentの幅
     * @param height Componentの高さ
     */
    public void addAndSetBounds(Component component, int x, int y, int width, int height) {
        component.setBounds(x, y, width, height);
        add(component);
    }
}

