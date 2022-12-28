package src.panel;

import javax.swing.JLabel;
import javax.swing.JPanel;

import src.Setting;
import src.objects.Button;

import java.awt.Font;
import java.awt.Canvas;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.Color;
import java.awt.GradientPaint;

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

    // properties
    private int width, height;
    private Color[][] gradientBackgroundColor = null;
    private double[][] gradientPoint1 = null;
    private double[][] gradientPoint2 = null;

    // Contructor
    public PanelString(int x, int y, String text, int width, Font font, int rootLocationType, int locationText) {
        // Set default font
        if (font == null) {
            font = new Font(Setting.FONT_NAME_01,
                    Setting.FONT_STYLE_01,
                    Setting.FONT_SIZE_SMALL);
        }

        // Get height of a row
        // Create pattern button to get height
        Button button = new Button("A");
        button.setFontText(font);
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
        if (locationText > 0) {
            label.setBounds(locationText, 0, width, height);
        } else if (locationText == 0) {
            label.setBounds((width - f.stringWidth(text)) / 2, 0, width, height);
        } else {
            label.setBounds(width - f.stringWidth(text) + locationText, 0, width, height);
        }

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
        this.width = width;
        this.height = height;
    }

    // Contructor
    public PanelString(int x, int y, String text, int width, Font font, int rootLocationType, int locationText,
            Color textColor) {
        // Set default font
        if (font == null) {
            font = new Font(Setting.FONT_NAME_01,
                    Setting.FONT_STYLE_01,
                    Setting.FONT_SIZE_SMALL);
        }

        // Get height of a row
        // Create pattern button to get height
        Button button = new Button("A");
        button.setFont(font);
        int heightRow = button.getHeight();

        // Height of this panel
        int height = heightRow;

        // Create label
        JLabel label = new JLabel(text);
        label.setLayout(null);
        label.setFont(font);
        label.setSize(width, heightRow);
        label.setForeground(textColor);
        Canvas c = new Canvas();
        FontMetrics f = c.getFontMetrics(font);
        if (locationText > 0) {
            label.setBounds(locationText, 0, width, height);
        } else if (locationText == 0) {
            label.setBounds((width - f.stringWidth(text)) / 2, 0, width, height);
        } else {
            label.setBounds(width - f.stringWidth(text) + locationText, 0, width, height);
        }

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
        this.width = width;
        this.height = height;
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
        button.setFont(font);
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
        this.width = width;
        this.height = height;
    }

    public PanelString(int x, int y, List<String[]> textsList, int width, Font font, int rootLocationType,
            int locationText) {
        // Set default font
        if (font == null) {
            font = new Font(Setting.FONT_NAME_01,
                    Setting.FONT_STYLE_01,
                    Setting.FONT_SIZE_SMALL);
        }

        // Get height of a row
        // Create pattern button to get height
        Button button = new Button("A");
        button.setFont(font);
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
            Canvas c = new Canvas();
            FontMetrics f = c.getFontMetrics(font);
            if (locationText > 0) {
                label.setBounds(locationText, heightRow * count1, width, heightRow);
            } else if (locationText == 0) {
                label.setBounds((width - f.stringWidth(rowContent)) / 2, heightRow * count1, width, heightRow);
            } else {
                label.setBounds(width - f.stringWidth(rowContent) + locationText, heightRow * count1, width, heightRow);
            }
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
        this.width = width;
        this.height = height;
    }

    public PanelString(int x, int y, String[] texts, int width, Font font, int rootLocationType, int locationText) {
        // Set default font
        if (font == null) {
            font = new Font(Setting.FONT_NAME_01,
                    Setting.FONT_STYLE_01,
                    Setting.FONT_SIZE_SMALL);
        }

        // Get height of a row
        // Create pattern button to get height
        Button button = new Button("A");
        button.setFont(font);
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
            Canvas c = new Canvas();
            FontMetrics f = c.getFontMetrics(font);
            if (locationText > 0) {
                label.setBounds(locationText, heightRow * count1, width, heightRow);
            } else if (locationText == 0) {
                label.setBounds((width - f.stringWidth(rowContent)) / 2, heightRow * count1, width, heightRow);
            } else {
                label.setBounds(width - f.stringWidth(rowContent) + locationText, heightRow * count1, width, heightRow);
            }
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
        this.width = width;
        this.height = height;
    }

    // set gradient background color
    public void setGradientBackgroundColor(double[][] points1, double[][] points2, Color[][] colors) {
        this.gradientPoint1 = points1;
        this.gradientPoint2 = points2;
        this.gradientBackgroundColor = colors;
        repaint();
    }

    // Remove gradient background color
    public void removeGradientBackgroundColor() {
        this.gradientBackgroundColor = null;
        this.gradientPoint1 = null;
        this.gradientPoint2 = null;
        repaint();
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (this.gradientBackgroundColor != null) {
            // Draw background of button
            int heightPerRow = this.height / this.gradientBackgroundColor.length;
            for (int i = 0; i < this.gradientBackgroundColor.length; i++) {
                GradientPaint gradientPaint = new GradientPaint(
                        (int) this.gradientPoint1[i][0] * width, (int) this.gradientPoint1[i][1] * heightPerRow,
                        this.gradientBackgroundColor[i][0],
                        (int) this.gradientPoint2[i][0] * width, (int) this.gradientPoint2[i][1] * heightPerRow,
                        this.gradientBackgroundColor[i][1]);
                g2.setPaint(gradientPaint);

                g2.fillRect(0, 0, this.width, heightPerRow);
                g2.translate(0, heightPerRow);
            }
            g2.translate(0, -heightPerRow * this.gradientBackgroundColor.length);
        }
    }
}
