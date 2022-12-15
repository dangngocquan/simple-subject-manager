package code.objects;

import javax.swing.JPanel;

import code.Setting;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Canvas;
import java.awt.FontMetrics;
import java.awt.RenderingHints;

public class Button2 extends JPanel {
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

    // Constant font
    public static final Font SERIF_BOLD_18 = new Font("Serif", Font.BOLD, 18);
    public static final Font SERIF_BOLD_21 = new Font("Serif", Font.BOLD, 21);
    public static final Font SERIF_BOLD_24 = new Font("Serif", Font.BOLD, 24);
    public static final Font SERIF_BOLD_36 = new Font("Serif", Font.BOLD, 36);

    // Constant color
    public static final Color STROKE_COLOR_BLACK = Setting.COLOR_BLACK;
    public static final Color TEXT_COLOR_BLACK = Setting.COLOR_BLACK;
    public static final Color BACKGROUND_COLOR_BLUE = Setting.COLOR_BLUE_01;
    public static final Color BACKGROUND_COLOR_RED = Setting.COLOR_RED_05;

    // Constant stroke width
    public static final int STROKE_WIDTH_0 = 0;
    public static final int STROKE_WIDTH_1 = 1;
    public static final int STROKE_WIDTH_2 = 2;
    public static final int STROKE_WIDTH_3 = 3;

    // Properties
    // properties of button (size, location, button color, stroke width, stroke
    // color)
    private int width, height;
    private int rootLocationType = 0;
    private int xPos = 0, yPos = 0;
    private int strokeWidth = 0;
    private Color strokeColor = STROKE_COLOR_BLACK;
    private Color[] backgroundColor = { BACKGROUND_COLOR_RED, BACKGROUND_COLOR_BLUE };
    private int[] gradientPoint1 = { 0, 0 }, gradientPoint2 = { 0, 0 };
    // properties of text
    private String text = "";
    private int[] sizeText = null;
    private Font font = SERIF_BOLD_24;
    private int xText = 0;
    private int yText = 0;
    private Color textColor;

    public Button2(int x, int y, String text) {
        setLayout(null);
        // create text
        this.text = text;
        // Create default font of text
        this.font = SERIF_BOLD_24;
        // set size of text
        setDefaultSizeText();
        // Create default size of button
        setSizeButton(sizeText[0] / 5 * 7, sizeText[1] / 5 * 7);
        // Set default location of button
        setLocationButton(x, y, TOP_LEFT);
        // Set default location of text in button
        setLocationText(0, 0); // CENTER_CENTER
        // Set default Stroke width
        setStrokeWidth(STROKE_WIDTH_2);
        // Set default stroke color
        this.strokeColor = STROKE_COLOR_BLACK;
        // Set default text color
        this.textColor = TEXT_COLOR_BLACK;
        // Set default gradient color background
        setGradientBackground(0, 0, BACKGROUND_COLOR_BLUE, 0, this.height / 2 * 3, BACKGROUND_COLOR_RED);

    }

    // Get basic width and height of text with a input font
    public int[] getSizeText(String text, Font font) {
        Canvas c = new Canvas();
        FontMetrics fontMetrics = c.getFontMetrics(font);
        int w = fontMetrics.stringWidth(text);
        int h = fontMetrics.getHeight();
        int descent = fontMetrics.getDescent();
        return (new int[] { w, h, descent });
    }

    // Get basic width and height of text with a input font
    public void setDefaultSizeText() {
        this.sizeText = getSizeText(this.text, this.font);
    }

    // Set size of button
    public void setSizeButton(int width, int height) {
        this.width = width;
        this.height = height;
        setSize(this.width, this.height);
        repaint();
    }

    // Set size button with current text
    public void setCorrectSizeButton() {
        this.width = this.sizeText[0] / 5 * 7;
        this.height = this.sizeText[1] / 5 * 7;
        setSize(this.width, this.height);
        setLocationText(xText, yText);
        repaint();
    }

    // Set font of text
    public void setFontText(Font font) {
        this.font = font;
        setDefaultSizeText();
        setLocationText(xText, yText);
        repaint();
    }

    // Set stroke width
    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        repaint();
    }

    // Set stroke color
    public void setStrokeColor(Color color) {
        this.strokeColor = color;
        repaint();
    }

    // Set gradient color of background
    public void setGradientBackground(int x1, int y1, Color color1, int x2, int y2, Color color2) {
        this.backgroundColor[0] = color1;
        this.backgroundColor[1] = color2;
        this.gradientPoint1[0] = x1;
        this.gradientPoint1[1] = y1;
        this.gradientPoint2[0] = x2;
        this.gradientPoint2[1] = y2;
        repaint();
    }

    // Set location and root location type
    public void setLocationButton(int x, int y, int rootLocationType) {
        this.rootLocationType = rootLocationType;
        switch (this.rootLocationType) {
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
        repaint();
    }

    // Set location of text in button
    public void setLocationText(int x, int y) {
        this.xText = x;
        this.yText = y;
        repaint();
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gradientPaint = new GradientPaint(
                this.gradientPoint1[0], this.gradientPoint1[1], this.backgroundColor[0],
                this.gradientPoint2[0], this.gradientPoint2[1], this.backgroundColor[1]);

        // Draw stroke
        g2.setColor(this.strokeColor);
        g2.fillRect(0, 0, this.width, this.height);

        // Draw background of button
        g2.setPaint(gradientPaint);
        g2.fillRect(this.strokeWidth, this.strokeWidth,
                this.width - 2 * this.strokeWidth, this.height - 2 * this.strokeWidth);

        // Draw text
        g2.setColor(this.textColor);
        g2.setFont(this.font);
        int tempX = 0, tempY = 0;
        if (this.xText == 0) {
            tempX = (this.width - sizeText[0]) / 2;
        } else if (this.xText > 0) {
            tempX = this.xText;
        } else {
            tempX = this.width - (sizeText[0] + (-this.xText));
        }

        if (this.yText == 0) {
            tempY = (this.height - sizeText[1]) / 2 + (sizeText[1] - sizeText[2]);
        } else if (this.yText > 0) {
            tempY = this.yText + (sizeText[1] - sizeText[2]);
        } else {
            tempY = this.height - (sizeText[1] + (-this.yText)) + (sizeText[1] - sizeText[2]);
        }
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString(this.text, tempX, tempY);
    }
}
