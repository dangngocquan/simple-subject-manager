package code;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Canvas;
import java.awt.FontMetrics;
import java.awt.Color;

public class Button extends JButton {
    // Constants Font style of button's text
    public static final int BOLD = Font.BOLD;
    public static final int ITALIC = Font.ITALIC;
    public static final int BOLD_ITALIC = Font.BOLD + Font.ITALIC;
    public static final int PLAIN = Font.PLAIN;

    // Constants button's root location
    public static final int TOP_LEFT = 0;
    public static final int TOP_CENTER = 1;
    public static final int TOP_RIGHT = 2;
    public static final int CENTER_LEFT = 3;
    public static final int CENTER_CENTER = 4;
    public static final int CENTER_RIGHT = 5;
    public static final int BOTTOM_LEFT = 6;
    public static final int BOTTOM_CENTER = 7;
    public static final int BOTTOM_RIGHT = 8;

    // Properties of Button
    private int width, height;
    private String fontName;
    private int fontStyle, fontSize;
    private int rootLocationType = 0;
    private int xPos = 0, yPos = 0;
    private boolean isDependOnText = true;

    // Constructor
    public Button(String text) {
        setText(text);
        setFocusPainted(false);
    }

    // Getter
    public boolean isButtonDependOnText() {
        return this.isDependOnText;
    }

    public int getButtonWidth() {
        return this.width;
    }

    public int getButtonHeight() {
        return this.height;
    }

    public String getFontName() {
        return this.fontName;
    }

    public int getFontStyle() {
        return this.fontStyle;
    }

    public int getFontSize() {
        return this.fontSize;
    }

    public int getXPosition() {
        return this.xPos;
    }

    public int getYPosition() {
        return this.yPos;
    }

    public int getRootLocationType() {
        return this.rootLocationType;
    }

    // Setter
    // Set font for button's text
    public void setFont(String fontName, int fontStyle, int fontSize) {
        this.fontName = fontName;
        this.fontStyle = fontStyle;
        this.fontSize = fontSize;
        Font font = new Font(fontName, fontStyle, fontSize);
        setFont(font);
        Canvas c = new Canvas();
        FontMetrics fontMetrics = c.getFontMetrics(font);
        this.width = fontMetrics.stringWidth(getText()) + fontMetrics.stringWidth("ABCDEF");
        this.height = fontMetrics.getHeight() + fontMetrics.stringWidth("ABC") / 2;
        setSize(this.width, this.height);
        setFont(new Font(fontName, fontStyle, fontSize));
    }

    // Set text
    public void setText(String text, boolean sizeDependOnText) {
        if (sizeDependOnText) {
            setText(text);
            Font font = new Font(fontName, fontStyle, fontSize);
            Canvas c = new Canvas();
            FontMetrics fontMetrics = c.getFontMetrics(font);
            this.width = fontMetrics.stringWidth(getText()) + fontMetrics.stringWidth("ABCDEF");
            this.height = fontMetrics.getHeight() + fontMetrics.stringWidth("ABC") / 2;
            setSize(this.width, this.height);
        } else {
            setText(text);
        }
    }

    // Set text and size
    public void setText(String text, int fontSize1, boolean sizeDependOnText) {
        if (sizeDependOnText) {
            setText(text);
            Font font = new Font(this.fontName, this.fontStyle, fontSize1);
            Canvas c = new Canvas();
            FontMetrics fontMetrics = c.getFontMetrics(font);
            this.width = fontMetrics.stringWidth(getText()) + fontMetrics.stringWidth("ABCDEF");
            this.height = fontMetrics.getHeight() + fontMetrics.stringWidth("ABC") / 2;
            setSize(this.width, this.height);
        } else {
            setFont(new Font(fontName, fontStyle, fontSize1));
            setText(text);
        }
    }

    // Button size depend on text
    public void setSizeDependOnText(boolean flag) {
        this.isDependOnText = flag;
    }

    // Set size of button when button not depend on text
    public void setSizeButton(int width, int height, boolean sizeDependOnText) {
        this.width = width;
        this.height = height;
        setSize(this.width, this.height);
        if (sizeDependOnText) {
            setText(getText());
        }

    }

    // Set color for button's text
    public void setTextColor(int r, int g, int b) {
        setForeground(new Color(r, g, b));
    }

    // Set location and root location type
    public void setLocation(int x, int y, int rootLocationType) {
        this.rootLocationType = rootLocationType;
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
}
