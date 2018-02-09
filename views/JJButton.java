package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import static original.Constants.*;

public class JJButton extends JButton implements MouseListener {

    JJButton(String text) {
        super(text);
        setOpaque(true);
        setBorderPainted(false);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        setForeground(BLACK);
        setBackground(MINT);

        addMouseListener(this);
    }

    /**
     * マウスホバー時の強調をつける
     */
    private void addHoverEffect() {
        setBorder(new EmptyBorder(0, 0, 5, 0));
    }

    /**
     * マウスホバー時の強調を外す
     */
    private void removeHoverEffect() {
        setBorder(null);
    }

    /**
     * MouseListenerの実装
     */
    @Override
    public void mouseEntered(MouseEvent me) {
        JJButton button = (JJButton)me.getSource();
        button.addHoverEffect();
    }

    /**
     * MouseListenerの実装
     */
    @Override
    public void mouseExited(MouseEvent me) {
        JJButton button = (JJButton)me.getSource();
        button.removeHoverEffect();
    }

    /**
     * MouseListenerの実装
     */
    @Override
    public void mouseClicked(MouseEvent me) { }

    /**
     * MouseListenerの実装
     */
    @Override
    public void mousePressed(MouseEvent me) { }

    /**
     * MouseListenerの実装
     */
    @Override
    public void mouseReleased(MouseEvent me) { }
}
