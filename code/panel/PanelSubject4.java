package code.panel;

import javax.swing.JPanel;
import java.awt.Color;
import code.Setting;
import code.objects.Subject;
import java.awt.Graphics;
import java.awt.Font;

public class PanelSubject4 extends JPanel {
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

    // Properties
    private int width, height;
    private int xPos, yPos, rootLocationType; // location of top-left point
    private JPanel mainPanel;
    private PanelString panelCodeSubject;

    // Constructor
    public PanelSubject4(int x, int y, Subject subject, int width, int height, Font font, int rootLocationType) {
            // Create defaulr font
            this.width = width;
            this.height = height;
            this.rootLocationType = rootLocationType;
            setLocation(x, y, rootLocationType);
            if (font == null) {
                    font = new Font(Setting.FONT_NAME_01,
                                    Setting.FONT_STYLE_01,
                                    Setting.FONT_SIZE_VERY_SMALL);
            }

            // mainPanel
            mainPanel = new JPanel();
            mainPanel.setLayout(null);
            mainPanel.setSize(width, height);
            mainPanel.setBounds(0, 0, mainPanel.getWidth(), mainPanel.getHeight());

            // sub-panel
            panelCodeSubject = new PanelString(mainPanel.getWidth() / 2, mainPanel.getHeight()/2,
                        subject.getCode(), mainPanel.getWidth()-4, font, PanelString.CENTER_CENTER, 0);
            panelCodeSubject.setBackground(subject.getColor());


            // Properties of this panel
            setLayout(null);
            setSize(width, height);
            setBounds(x, y, getWidth(), getHeight());

            // Add mainPanel to this panel
            add(mainPanel);
            mainPanel.add(panelCodeSubject);
    }

    // Get rootLocationType
    public int getRootLocationType() {
        return this.rootLocationType;
    }

    public int getCenterX() {
        return xPos + width/2;
    }

    public int getBottomY() {
        return yPos + height;
    }

    // Set background Color
    public void setBackgroundColorPanelSubject(Color colorStroke, Color backgroundColor) {
        mainPanel.setBackground(colorStroke);
        panelCodeSubject.setBackground(backgroundColor);
    }

    // Set root location tyoe
    public void setLocation(int x, int y, int type) {
        this.rootLocationType = type;
        switch (type) {
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
