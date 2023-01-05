package src.panel;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import src.Setting;
import src.algorithm.CalculateCPA;
import src.objects.Button;
import src.objects.Plan;
import src.objects.Subject;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class PanelCPA extends JPanel {
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
    private int width, height; // size of this panel
    private int xPos, yPos, rootLocationType; // location of top-left point
    private Plan plan; // data plan
    private Button panelCurrentCPA;
    private Button panelMinCPA;
    private Button panelMaxCPA;
    private JCheckBox checkbox;
    private JPanel scrollPanel; // Contains all information of plan
    private int cursorScroll = 0; // to define where the contentPanel in scrolllPanel

    // Constructor
    public PanelCPA(int x, int y, int width, int height, Plan plan, int rootLocationType) {
        // Properties, Objects
        this.width = width;
        this.height = height;
        this.plan = plan;
        this.rootLocationType = rootLocationType;
        setLayout(null);
        setSize(width, height);
        setLocation(x, y, rootLocationType);

        int tempHeight = height / 4;
        // panel current CPA
        panelCurrentCPA = new Button("CPA hiện tại: " + CalculateCPA.getCurrentCPA(this.plan));
        panelCurrentCPA.setFontText(Button.ARIAL_BOLD_21);
        panelCurrentCPA.setCorrectSizeButton();
        panelCurrentCPA.setLocationText(15, 0);
        panelCurrentCPA.setLocationButton(0, tempHeight, Button.TOP_LEFT);
        panelCurrentCPA.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        panelCurrentCPA.setEnable(false);
        panelCurrentCPA.setStrokeWidth(0);
        tempHeight += panelCurrentCPA.getHeight() + 30;

        // panel min and max CPA
        double[] minAndMaxCPA = CalculateCPA.getMinAndMaxCPA(plan, false);

        panelMinCPA = new Button("CPA tệ nhất có thể: " + minAndMaxCPA[0]);
        panelMinCPA.setFontText(Button.ARIAL_BOLD_21);
        panelMinCPA.setCorrectSizeButton();
        panelMinCPA.setLocationText(15, 0);
        panelMinCPA.setLocationButton(0, tempHeight, Button.TOP_LEFT);
        panelMinCPA.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        panelMinCPA.setEnable(false);
        panelMinCPA.setStrokeWidth(0);
        tempHeight += panelMinCPA.getHeight() + 30;

        panelMaxCPA = new Button("CPA tốt nhất có thể: " + minAndMaxCPA[1]);
        panelMaxCPA.setFontText(Button.ARIAL_BOLD_21);
        panelMaxCPA.setCorrectSizeButton();
        panelMaxCPA.setLocationText(15, 0);
        panelMaxCPA.setLocationButton(0, tempHeight, Button.TOP_LEFT);
        panelMaxCPA.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        panelMaxCPA.setEnable(false);
        panelMaxCPA.setStrokeWidth(0);
        tempHeight += panelMaxCPA.getHeight() + 30;

        // Checkbox
        checkbox = new JCheckBox("Học cải thiện những môn D/D+");
        checkbox.setFont(Button.ARIAL_BOLD_18);
        checkbox.setFocusPainted(false);
        checkbox.addItemListener(new ItemHandler());
        checkbox.setSize(panelCurrentCPA.getWidth() * 5 / 4, panelCurrentCPA.getHeight());
        checkbox.setLocation(0, tempHeight);

        // set size again
        int maxWidth = Math.max(Math.max(panelCurrentCPA.getWidth(), panelMinCPA.getWidth()), panelMaxCPA.getWidth());
        panelCurrentCPA.setSizeButton(maxWidth, panelCurrentCPA.getHeight());
        panelMinCPA.setSizeButton(maxWidth, panelMinCPA.getHeight());
        panelMaxCPA.setSizeButton(maxWidth, panelMaxCPA.getHeight());
        checkbox.setSize(maxWidth, checkbox.getHeight());

        // Add panel
        add(panelCurrentCPA);
        add(panelMinCPA);
        add(panelMaxCPA);
        add(checkbox);

        // Add MouseWheelListener to this panel
        addMouseWheelListener(new MouseWheelHandler());

    }

    // calculate again
    public void updateCalculate() {
        panelCurrentCPA.setTextButton("CPA hiện tại: " + CalculateCPA.getCurrentCPA(this.plan));

        double[] minAndMaxCPA = CalculateCPA.getMinAndMaxCPA(plan, checkbox.isSelected());
        panelMinCPA.setTextButton("CPA tệ nhất có thể: " + minAndMaxCPA[0]);
        panelMaxCPA.setTextButton("CPA tốt nhất có thể: " + minAndMaxCPA[1]);
    }

    // Update contentPanel
    public void updateContentShowing() {
        scrollPanel.setBounds(0, -cursorScroll, scrollPanel.getWidth(), scrollPanel.getHeight());
    }

    // Get rootLocationType
    public int getRootLocationType() {
        return this.rootLocationType;
    }

    // Get plan
    public Plan getplan() {
        return this.plan;
    }

    // get CursorScroll
    public int getCursorScroll() {
        return this.cursorScroll;
    }

    // Get max cursorScroll
    public int getMaxCursorScroll() {
        return Math.max(0, this.scrollPanel.getHeight() - getHeight());
    }

    // set cursorScroll
    public void setCurscorScroll(int value) {
        if (value >= 0 && value <= getMaxCursorScroll()) {
            this.cursorScroll = value;
        }
        updateContentShowing();
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

    private class MouseWheelHandler implements MouseWheelListener {
        public void mouseWheelMoved(MouseWheelEvent event) {
            for (int count = 0; count < 25; count++) {
                setCurscorScroll(getCursorScroll() + event.getWheelRotation());
            }
            updateContentShowing();
        }
    }

    private class ItemHandler implements ItemListener {
        public void itemStateChanged(ItemEvent event) {
            // Press checkbox "Auto selection"
            if (event.getSource() == checkbox) {
                updateCalculate();
            }
        }
    }
}
