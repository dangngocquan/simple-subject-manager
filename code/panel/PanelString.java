package code.panel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import code.Setting;
import code.objects.Button;
import java.awt.Canvas;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.List;

public class PanelString extends JPanel {
    // Constants PanelString's root location
    public static final int TOP_LEFT = 0;
    public static final int TOP_CENTER = 1;
    public static final int TOP_RIGHT = 2;
    public static final int CENTER_LEFT = 3;
    public static final int CENTER_CENTER = 4;
    public static final int CENTER_RIGHT = 5;
    public static final int BOTTOM_LEFT = 6;
    public static final int BOTTOM_CENTER = 7;
    public static final int BOTTOM_RIGHT = 8;

    // Contructor
    public PanelString(int x, int y, String text, int width, Font font, int rootLocationType) {
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

        // Create label
        JLabel label = new JLabel(text);
        label.setLayout(null);
        label.setFont(font);
        label.setSize(width, heightRow);
        Canvas c = new Canvas();
        FontMetrics f = c.getFontMetrics(font);
        label.setBounds((width - f.stringWidth(text)) / 2, 0, width, height);

        // Add label to this panel
        add(label);

        // Set up panel
        setLayout(null);
        setSize(width, height);
        int xPos = 0, yPos = 0;
        switch (rootLocationType) {
            case 0:
                xPos = x;
                yPos = y;
                break;
            case 1:
                xPos = x - width / 2;
                yPos = y;
                break;
            case 2:
                xPos = x - width;
                yPos = y;
                break;
            case 3:
                xPos = x;
                yPos = y - height / 2;
                break;
            case 4:
                xPos = x - width / 2;
                yPos = y - height / 2;
                break;
            case 5:
                xPos = x - width;
                yPos = y - height / 2;
                break;
            case 6:
                xPos = x;
                yPos = y - height;
                break;
            case 7:
                xPos = x - width / 2;
                yPos = y - height;
                break;
            case 8:
                xPos = x - width;
                yPos = y - height;
                break;
        }
        setBounds(xPos, yPos, width, height);
    }

    public PanelString(int x, int y, List<String[]> textsList, int width, Font font, int rootLocationType) {
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

        // Set up panel
        setLayout(null);
        setSize(width, height);
        int xPos = 0, yPos = 0;
        switch (rootLocationType) {
            case 0:
                xPos = x;
                yPos = y;
                break;
            case 1:
                xPos = x - width / 2;
                yPos = y;
                break;
            case 2:
                xPos = x - width;
                yPos = y;
                break;
            case 3:
                xPos = x;
                yPos = y - height / 2;
                break;
            case 4:
                xPos = x - width / 2;
                yPos = y - height / 2;
                break;
            case 5:
                xPos = x - width;
                yPos = y - height / 2;
                break;
            case 6:
                xPos = x;
                yPos = y - height;
                break;
            case 7:
                xPos = x - width / 2;
                yPos = y - height;
                break;
            case 8:
                xPos = x - width;
                yPos = y - height;
                break;
        }
        setBounds(xPos, yPos, width, height);
    }

    public PanelString(int x, int y, String[] texts, int width, Font font, int rootLocationType) {
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
        for (int count1 = 0; count1 < texts.length; count1++) {
            String rowContent = texts[count1];

            JLabel label = new JLabel(rowContent);
            label.setLayout(null);
            label.setFont(font);
            label.setSize(width, heightRow);
            label.setBounds(15, heightRow * count1, width, heightRow);
            add(label);
            height += heightRow;
        }

        if (texts.length == 0) {
            height += heightRow;
        }

        // Set up panel
        setLayout(null);
        setSize(width, height);
        int xPos = 0, yPos = 0;
        switch (rootLocationType) {
            case 0:
                xPos = x;
                yPos = y;
                break;
            case 1:
                xPos = x - width / 2;
                yPos = y;
                break;
            case 2:
                xPos = x - width;
                yPos = y;
                break;
            case 3:
                xPos = x;
                yPos = y - height / 2;
                break;
            case 4:
                xPos = x - width / 2;
                yPos = y - height / 2;
                break;
            case 5:
                xPos = x - width;
                yPos = y - height / 2;
                break;
            case 6:
                xPos = x;
                yPos = y - height;
                break;
            case 7:
                xPos = x - width / 2;
                yPos = y - height;
                break;
            case 8:
                xPos = x - width;
                yPos = y - height;
                break;
        }
        setBounds(xPos, yPos, width, height);

    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
