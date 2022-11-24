package code.text_field;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.LinkedList;
import java.util.List;

import code.Setting;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TextField extends JPanel {
    private JTextField field;

    // Constructor
    public TextField(int x, int y, int width, int height, String defaultText) {
        JTextField field = new JTextField(defaultText);
        field.setLayout(null);
        field.setFont(new Font(
                Setting.FONT_NAME_01,
                Setting.FONT_STYLE_01,
                Setting.FONT_SIZE_SMALL));
        field.setSize(width, height);
        field.setBounds(0, 0, width, height);
        field.setForeground(Setting.COLOR_GRAY_03);
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent event) {
                if (field.getText().equals(defaultText) && field.getForeground() == Setting.COLOR_GRAY_03) {
                    field.setForeground(Setting.COLOR_BLACK);
                    field.setText("");
                }
                super.focusGained(event);
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Setting.COLOR_GRAY_03);
                    field.setText(defaultText);
                }
                super.focusLost(event);
            }
        });

        setLayout(null);
        setSize(width, height);
        setBounds(x, y, width, height);
        setVisible(true);
        add(field);

    }

    // Get input data
    public String getInputString() {
        String input = "";
        if (field.getForeground() == Setting.COLOR_BLACK) {
            input = field.getText();
        }
        return input;
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
