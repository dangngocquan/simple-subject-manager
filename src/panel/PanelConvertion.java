package src.panel;

import javax.swing.JPanel;
import src.launcher.Setting;
import src.objects.Button;
import src.objects.ConversionTable;
import src.objects.KnowledgePart;
import src.objects.Major;
import src.objects.Subject;
import java.awt.event.MouseWheelListener;
import java.util.List;
import java.awt.event.MouseWheelEvent;
import java.awt.Graphics;
import java.awt.Color;

public class PanelConvertion extends JPanel {
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

    public static final Color COLOR_HEADER = Setting.COLOR_BLUE_02;
    public static final Color COLOR_STROKE = Setting.COLOR_BLACK;
    public static final Color COLOR_ROW_1 = Setting.COLOR_GRAY_05;
    public static final Color COLOR_ROW_2 = Setting.COLOR_GRAY_04;

    // Properties
    private int width, height; // size of this panel
    private int xPos, yPos, rootLocationType; // location of top-left point
    private JPanel panelHeader; // contains title
    private JPanel panelContent; // a part of scrollPanel wil be shown here
    private PanelString title1, title2, title3;

    // Constructor
    public PanelConvertion(int x, int y, int width, int height, ConversionTable convertion, int rootLocationType) {
        // Properties, Objects
        this.width = width;
        this.height = height;
        this.rootLocationType = rootLocationType;
        setLayout(null);
        setSize(width, height);
        setLocation(x, y, rootLocationType);

        // Create panels
        panelHeader = new JPanel();
        panelHeader.setLayout(null);
        panelHeader.setSize(width, height / 10);
        panelHeader.setBounds(0, 0, panelHeader.getWidth(), panelHeader.getHeight());

        int tempHeight = 0;

        // Create titles
        int strokeWidth = 3;
        title1 = new PanelString(strokeWidth, 0, "Điểm hệ 10", panelHeader.getWidth() / 2, null, PanelString.TOP_LEFT,
                0);
        title2 = new PanelString(title1.getX() + title1.getWidth() + strokeWidth, title1.getY(), "Điểm chữ",
                panelHeader.getWidth() / 4, null,
                PanelString.TOP_LEFT, 0);
        title3 = new PanelString(title2.getX() + title2.getWidth() + strokeWidth, title1.getY(), "Điểm hệ 4",
                panelHeader.getWidth() - title1.getWidth() - title2.getWidth() - strokeWidth * 4, null,
                PanelString.TOP_LEFT, 0);

        panelHeader.setSize(panelHeader.getWidth(), title1.getHeight() + strokeWidth);
        panelHeader.setBounds(width / 10, tempHeight, panelHeader.getWidth(), panelHeader.getHeight());
        panelHeader.setBounds(0, 0, panelHeader.getWidth(), panelHeader.getHeight());
        tempHeight += panelHeader.getHeight();

        // Set background color of header panel
        title1.setBackground(COLOR_HEADER);
        title2.setBackground(COLOR_HEADER);
        title3.setBackground(COLOR_HEADER);
        panelHeader.setBackground(COLOR_STROKE);

        // create content
        panelContent = new JPanel();
        panelContent.setLayout(null);
        int contentHeight = 0;

        for (int i = convertion.getCharacterScore().size() - 1; i >= 0; i--) {
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setSize(panelHeader.getWidth(), 0);
            String strTemp1 = String.format("Từ %s đến %s", convertion.getScore10().get(i),
                    convertion.getScore10().get(i + 1));
            PanelString temp1 = new PanelString(strokeWidth, 0, strTemp1, panelHeader.getWidth() / 2, null,
                    PanelString.TOP_LEFT,
                    0);
            PanelString temp2 = new PanelString(title1.getX() + title1.getWidth() + strokeWidth, title1.getY(),
                    convertion.getCharacterScore().get(i),
                    panelHeader.getWidth() / 4, null,
                    PanelString.TOP_LEFT, 0);
            PanelString temp3 = new PanelString(title2.getX() + title2.getWidth() + strokeWidth, title1.getY(),
                    convertion.getScore4().get(i) + "",
                    panelHeader.getWidth() - title1.getWidth() - title2.getWidth() - strokeWidth * 4, null,
                    PanelString.TOP_LEFT, 0);

            panel.add(temp1);
            panel.add(temp2);
            panel.add(temp3);
            panel.setSize(panel.getWidth(), temp1.getHeight());
            panel.setBounds(0, contentHeight, panel.getWidth(), panel.getHeight());
            panel.setBackground(COLOR_STROKE);

            if (i % 2 == 0) {
                temp1.setBackground(COLOR_ROW_1);
                temp2.setBackground(COLOR_ROW_1);
                temp3.setBackground(COLOR_ROW_1);
            } else {
                temp1.setBackground(COLOR_ROW_2);
                temp2.setBackground(COLOR_ROW_2);
                temp3.setBackground(COLOR_ROW_2);
            }
            panelContent.add(panel);
            contentHeight += panel.getHeight();
        }

        panelContent.setSize(panelHeader.getWidth(), contentHeight);
        panelContent.setBounds(panelHeader.getX(), panelHeader.getY() + panelHeader.getHeight(),
                panelContent.getWidth(), panelContent.getHeight());

        // Add sub panels to this panel
        add(panelHeader);
        panelHeader.add(title1);
        panelHeader.add(title2);
        panelHeader.add(title3);
        add(panelContent);

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
