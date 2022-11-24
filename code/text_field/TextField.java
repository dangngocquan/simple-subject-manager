package code.text_field;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import code.Setting;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TextField extends JPanel {

    // Constructor
    public TextField(int x, int y, int width, int height, String defaultText) {
        JTextField fieldName = new JTextField(defaultText);
        fieldName.setLayout(null);
        fieldName.setFont(new Font(
                Setting.FONT_NAME_01,
                Setting.FONT_STYLE_01,
                Setting.FONT_SIZE_SMALL));
        fieldName.setSize(width, height);
        fieldName.setBounds(0, 0, width, height);
        fieldName.setForeground(Setting.COLOR_GRAY_03);
        fieldName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent event) {
                if (fieldName.getText().equals(defaultText) && fieldName.getForeground() == Setting.COLOR_GRAY_03) {
                    fieldName.setForeground(Setting.COLOR_BLACK);
                    fieldName.setText("");
                }
                super.focusGained(event);
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (fieldName.getText().isEmpty()) {
                    fieldName.setForeground(Setting.COLOR_GRAY_03);
                    fieldName.setText(defaultText);
                }
                super.focusLost(event);
            }
        });

        setLayout(null);
        setSize(width, height);
        setBounds(x, y, width, height);
        setVisible(true);
        add(fieldName);

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
