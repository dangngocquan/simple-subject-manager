package code.panel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import code.Setting;
import code.button.Button;

import java.awt.Graphics;
import java.util.List;

public class PanelString extends JPanel {
    // Contructor
    public PanelString(int x, int y, String text, int width, Font font) {
        // Set default font
        if (font == null) {
            font = new Font(Setting.FONT_NAME_01,
                    Setting.FONT_STYLE_01,
                    Setting.FONT_SIZE_SMALL);
        }

        // Get height of a row
        // Create pattern button to get height
        Button button = new Button("A");
        button.setFont(font.getFontName(),
                font.getStyle(),
                font.getSize());
        int heightRow = button.getHeight();

        // Height of this panel
        int height = heightRow;

        // Set up panel
        setLayout(null);
        setSize(width, height);
        setBounds(x, y, width, height);

        // Create label
        JLabel label = new JLabel(text);
        label.setLayout(null);
        label.setFont(font);
        label.setSize(width, heightRow);
        label.setBounds(15, 0, width, height);

        // Add label to this panel
        add(label);

    }

    public PanelString(int x, int y, List<String[]> textsList, int width, Font font) {
        // Set default font
        if (font == null) {
            font = new Font(Setting.FONT_NAME_01,
                    Setting.FONT_STYLE_01,
                    Setting.FONT_SIZE_SMALL);
        }

        // Get height of a row
        // Create pattern button to get height
        Button button = new Button("A");
        button.setFont(font.getFontName(),
                font.getStyle(),
                font.getSize());
        int heightRow = button.getHeight();

        // Height of this panel
        int height = 0;

        // Create labels and add to this panel
        for (int count1 = 0; count1 < textsList.size(); count1++) {
            String rowContent = "";
            String[] texts = textsList.get(count1);
            for (int count2 = 0; count2 < texts.length; count2++) {
                if (count2 > 0) {
                    rowContent += " / " + texts[count2];
                } else {
                    rowContent += texts[count2];
                }
            }

            JLabel label = new JLabel(rowContent);
            label.setLayout(null);
            label.setFont(font);
            label.setSize(width, heightRow);
            label.setBounds(15, heightRow * count1, width, heightRow);
            add(label);
            height += heightRow;
        }

        if (textsList.size() == 0) {
            height += heightRow;
        }

        // Set up this panel
        setLayout(null);
        setSize(width, height);
        setBounds(x, y, width, height);

    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
