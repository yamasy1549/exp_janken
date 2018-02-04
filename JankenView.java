import javax.swing.*;
import java.awt.event.*;

class JankenView extends JFrame implements ActionListener {
    JTextArea textarea = new JTextArea("", 1, 10);

    JankenView() {
        setBounds(100, 100, 300, 200);

        textarea.requestFocus();

        JButton button = new JButton("Start");
        button.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(textarea);
        panel.add(button);

        getContentPane().add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        String name = textarea.getText();
        if(name != "") {
            new Janken("192.168.1.3", "9999", name);
        }
    }

    public static void main(String args[]) {
        JankenView view = new JankenView();

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setVisible(true);
    }
}
