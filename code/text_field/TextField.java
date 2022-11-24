package code.text_field;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import code.Setting;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TextField extends JPanel {

    // Constructor
    public TextField(int x, int y, int width, int height) {

    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    // private class Handler implements ActionListener {
    // public void actionPerformed(ActionEvent event) {
    // if (event.getSource() == textField) {
    // if (textField.getText().isEmpty()) {
    // deepText.setVisible(true);
    // } else {
    // deepText.setVisible(false);
    // }
    // }
    // repaint();
    // }
    // }
}
