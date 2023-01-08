package src.panel;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.file_handler.WriteFile;
import src.launcher.Setting;
import src.objects.Plan;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class PanelTimeTable extends JPanel {
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

    public static final int STROKE_WIDTH = 3;

    public static final Color COLOR_SUBJECT_ENTERED = Setting.COLOR_VIOLET_03;
    public static final Color COLOR_SUBJECT_EXITED_1 = Setting.COLOR_GRAY_05;
    public static final Color COLOR_LINE_ENTERED = Setting.COLOR_BLUE_02;
    public static final Color COLOR_LINE_EXITED = Setting.COLOR_BLACK;
    public static final Color COLOR_STROKE_PANEL_SUBJECT_EXITED = Setting.COLOR_BLACK;
    public static final Color COLOR_STROKE_PANEL_SUBJECT_ENTERED = COLOR_LINE_ENTERED;
    public static final Color COLOR_STROKE = Setting.COLOR_BLACK;

    // Properties
    private int width, height; // size of this panel
    private int xPos, yPos, rootLocationType; // location of top-left point
    private PanelSubjectList2 panelSubjectList = null;
    private PanelSubject4 panelContent = null;
    private Plan plan;
    private int indexPlan;
    private int indexPressing = -1;

    // Constructor
    public PanelTimeTable(int x, int y, int width, int height, Font font,
            int rootLocationType, Plan plan, int indexPlan, JFrame applicationFrame) {
        this.plan = plan;
        this.indexPlan = indexPlan;
        // Properties, Objects
        this.width = width;
        this.height = height;
        this.rootLocationType = rootLocationType;
        setLayout(null);
        setSize(width, height);
        setLocation(x, y, rootLocationType);
        setBackground(COLOR_STROKE);

        // panels
        panelSubjectList = new PanelSubjectList2(0, 0, width / 4, height, this.plan, PanelSubjectList2.TOP_LEFT,
                this.indexPlan, this);
        panelContent = new PanelSubject4(panelSubjectList.getWidth(), panelSubjectList.getY(),
                width - panelSubjectList.getWidth(), panelSubjectList.getHeight(), plan, indexPlan, this.indexPressing,
                PanelSubject4.TOP_LEFT, this);

        // add panel
        add(panelSubjectList);
        add(panelContent);

    }

    // Get panel subject list
    public PanelSubjectList2 getPanelSubjectList() {
        return this.panelSubjectList;
    }

    // Update content data
    public void updateContent() {
        this.indexPressing = panelSubjectList.getIndexPressing();
        remove(panelContent);
        panelContent = new PanelSubject4(panelSubjectList.getWidth(), panelSubjectList.getY(),
                width - panelSubjectList.getWidth(), panelSubjectList.getHeight(), plan, indexPlan, this.indexPressing,
                PanelSubject4.TOP_LEFT, this);
        add(panelContent);
        repaint();
    }

    // update panel subject list
    public void updateDataSubjectList() {
        panelSubjectList.updateDataContent();
        repaint();
    }

    // Remove subject
    public void removeSubject(int indexSubject) {
        WriteFile.removeSubjectTimeTable(indexPlan, indexSubject);
        panelSubjectList.setIndexPressing(-1);
        plan.getTimeTable().getSubjects().remove(indexSubject);
        panelSubjectList.updateDataContent();
        updateContent();
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
