package src2.panel;

import src.launcher.Setting;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;

public class TextField extends JTextField {
    private Shape shape;
    private int thickness;
    private int arcWidth, arcHeight;
    private String defaultText;

    // Constants panel's root location
    public static final int TOP_LEFT = 0;
    public static final int TOP_CENTER = 1;
    public static final int TOP_RIGHT = 2;
    public static final int CENTER_LEFT = 3;
    public static final int CENTER_CENTER = 4;
    public static final int CENTER_RIGHT = 5;
    public static final int BOTTOM_LEFT = 6;
    public static final int BOTTOM_CENTER = 7;
    public static final int BOTTOM_RIGHT = 8;

    // Constructor
    public TextField(int x, int y, int width, int height, int rootLocationType, String defaultText, int thickness,
            int arcWidth,
            int arcHeight) {
        super(defaultText);
        this.defaultText = defaultText;
        this.thickness = thickness;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setOpaque(false);
        setLayout(null);
        setFont(new Font(
                Setting.FONT_NAME_01,
                Setting.FONT_STYLE_01,
                Setting.FONT_SIZE_SMALL));
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
        setForeground(Setting.COLOR_GRAY_03);
        setBorder(new LineBorder(Setting.COLOR_GRAY_03, thickness, true));
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent event) {
                if (getText().equals(defaultText) && getForeground() == Setting.COLOR_GRAY_03) {
                    setForeground(Setting.COLOR_BLACK);
                    setText("");
                }
                setBorder(new LineBorder(Setting.COLOR_BLACK, thickness, true));
                super.focusGained(event);
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (getText().isEmpty()) {
                    setForeground(Setting.COLOR_GRAY_03);
                    setText(defaultText);
                }
                setBorder(new LineBorder(Setting.COLOR_GRAY_03, thickness, true));
                super.focusLost(event);
            }
        });
    }

    // Get default text
    public String getDefaultText() {
        return this.defaultText;
    }

    // Get input data
    public String getInputString() {
        String input = "";
        if (getForeground() == Setting.COLOR_BLACK) {
            input = getText();
        }
        return input;
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(thickness - 1, thickness - 1, getWidth() - 2 * thickness, getHeight() - 2 * thickness, arcWidth,
                15);
        super.paintComponent(g);
    }

    public void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getForeground());
        g2.setStroke(new BasicStroke(thickness));
        g.drawRoundRect(thickness - 1, thickness - 1, getWidth() - 2 * thickness, getHeight() - 2 * thickness, arcWidth,
                arcHeight);
    }

    public boolean contains(int x0, int y0) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth(),
                    getHeight(), arcWidth, arcHeight);
        }
        return shape.contains(x0, y0);
    }
}
