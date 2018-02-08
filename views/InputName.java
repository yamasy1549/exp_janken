package views;

import javax.swing.*;

public class InputName {
    String name;

    InputName() {
        this.name = JOptionPane.showInputDialog("Input your name");
    }

    public String getName() {
        return this.name;
    }
}
