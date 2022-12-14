package code.panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import code.Setting;
import code.objects.ConversionTable;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class PanelCalculateScoreLastTerm extends JPanel {
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

    public static final Color COLOR_SUBJECT_ENTERED = Setting.COLOR_VIOLET_03;
    public static final Color COLOR_SUBJECT_EXITED_1 = Setting.COLOR_GRAY_05;
    public static final Color COLOR_LINE_ENTERED = Setting.COLOR_BLUE_02;
    public static final Color COLOR_LINE_EXITED = Setting.COLOR_BLACK;
    public static final Color COLOR_STROKE_PANEL_SUBJECT_EXITED = Setting.COLOR_BLACK;
    public static final Color COLOR_STROKE_PANEL_SUBJECT_ENTERED = COLOR_LINE_ENTERED;

    // Properties
    private int width, height; // size of this panel
    private int xPos, yPos, rootLocationType; // location of top-left point
    private JFrame applicationFrame;
    private PanelCalculateLastTerm3 panelCalculateLastTerm3;
    private PanelCalculateLastTerm4 panelCalculateLastTerm4;

    // Constructor
    public PanelCalculateScoreLastTerm(int x, int y, int width, int height, Font font,
            int rootLocationType, ConversionTable conversionTable, JFrame applicationFrame) {
        this.applicationFrame = applicationFrame;
        // Properties, Objects
        this.width = width;
        this.height = height;
        this.rootLocationType = rootLocationType;
        setLayout(null);
        setSize(width, height);
        setLocation(x, y, rootLocationType);
        setBackground(COLOR_LINE_ENTERED);

        // panels
        panelCalculateLastTerm3 = new PanelCalculateLastTerm3(0, 0, width, height / 5 * 2, font,
                conversionTable, this.applicationFrame);
        panelCalculateLastTerm4 = new PanelCalculateLastTerm4(0, panelCalculateLastTerm3.getHeight(), width,
                height - panelCalculateLastTerm3.getHeight(), font,
                conversionTable, this.applicationFrame);

        // add panel
        add(panelCalculateLastTerm3);
        add(panelCalculateLastTerm4);

    }

    // Update content data
    public void updateContent() {

    }

    // Get rootLocationType
    public int getRootLocationType() {
        return this.rootLocationType;
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
