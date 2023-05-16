package src.panel;

import javax.swing.JPanel;

import src.launcher.Setting;
import src.objects.Button;
import src.objects.Plan;
import src.objects.Subject;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.Graphics;
import java.awt.Color;

public class PanelSubjectList extends JPanel {
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

    public static final Color COLOR_BACKGROUND_KNOWLEDGE_NAME = Setting.COLOR_BLUE_04;
    public static final Color COLOR_BACKGROUND_DESCRIPTION = Setting.COLOR_BLUE_04;
    public static final Color COLOR_BACKGROUND_DESCRIPTION_2 = Setting.COLOR_BLUE_04;
    public static final Color COLOR_SUBJECT_1 = Setting.COLOR_GRAY_05;
    public static final Color COLOR_SUBJECT_2 = Setting.COLOR_GRAY_04;
    public static final Color COLOR_STROKE = Setting.COLOR_BLACK;

    // Properties
    private int width, height; // size of this panel
    private int xPos, yPos, rootLocationType; // location of top-left point
    private Plan plan; // data plan
    private JPanel headerPanel; // contains title
    private JPanel contentPanel; // a part of scrollPanel wil be shown here
    private JPanel scrollPanel; // Contains all information of plan
    private int cursorScroll = 0; // to define where the contentPanel in scrolllPanel

    // Constructor
    public PanelSubjectList(int x, int y, int width, int height, Plan plan, int rootLocationType) {
        // Properties, Objects
        this.width = width;
        this.height = height;
        this.plan = plan;
        this.rootLocationType = rootLocationType;
        setLayout(null);
        setSize(width, height);
        setLocation(x, y, rootLocationType);

        // Create panels
        headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setSize(width - STROKE_WIDTH * 2, height / 10 - STROKE_WIDTH * 2);
        headerPanel.setBounds(STROKE_WIDTH, STROKE_WIDTH, headerPanel.getWidth(), headerPanel.getHeight());

        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setSize(headerPanel.getWidth(), height - headerPanel.getHeight() - 2 * STROKE_WIDTH);
        contentPanel.setBounds(STROKE_WIDTH, headerPanel.getHeight() + 2 * STROKE_WIDTH, contentPanel.getWidth(),
                contentPanel.getHeight());

        // Create titles for headerPanel
        Button titleOrder = new Button("STT");
        titleOrder.setFontText(Button.ARIAL_BOLD_21);
        titleOrder.setEnable(false);
        titleOrder.setLocationText(0, 0);
        titleOrder.setStrokeWidth(0);
        titleOrder.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_4, Setting.GRADIENT_POINTS2_4,
                Setting.GRADIENT_COLORS_4);
        titleOrder.setSizeButton(headerPanel.getWidth() / 12, headerPanel.getHeight());
        titleOrder.setBounds(0, 0, titleOrder.getWidth(), titleOrder.getHeight());

        Button titleCode = new Button("Mã");
        titleCode.setFontText(Button.ARIAL_BOLD_21);
        titleCode.setEnable(false);
        titleCode.setLocationText(15, 0);
        titleCode.setStrokeWidth(0);
        titleCode.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_4, Setting.GRADIENT_POINTS2_4,
                Setting.GRADIENT_COLORS_4);
        titleCode.setSizeButton(headerPanel.getWidth() / 12, headerPanel.getHeight());
        titleCode.setBounds(titleOrder.getX() + titleOrder.getWidth(), 0, titleCode.getWidth(), titleCode.getHeight());

        Button titleName = new Button("Tên môn học");
        titleName.setFontText(Button.ARIAL_BOLD_21);
        titleName.setEnable(false);
        titleName.setLocationText(15, 0);
        titleName.setStrokeWidth(0);
        titleName.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_4, Setting.GRADIENT_POINTS2_4,
                Setting.GRADIENT_COLORS_4);
        titleName.setSizeButton(headerPanel.getWidth() / 12 * 5, headerPanel.getHeight());
        titleName.setBounds(titleCode.getX() + titleCode.getWidth(), 0, titleName.getWidth(),
                titleName.getHeight());

        Button titleCredits = new Button("Số tín");
        titleCredits.setFontText(Button.ARIAL_BOLD_21);
        titleCredits.setEnable(false);
        titleCredits.setLocationText(0, 0);
        titleCredits.setStrokeWidth(0);
        titleCredits.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_4, Setting.GRADIENT_POINTS2_4,
                Setting.GRADIENT_COLORS_4);
        titleCredits.setSizeButton(headerPanel.getWidth() / 12, headerPanel.getHeight());
        titleCredits.setBounds(titleName.getX() + titleName.getWidth(), 0,
                titleCredits.getWidth(), titleCredits.getHeight());

        Button titleParentCodes = new Button("Mã học phần tiên quyết");
        titleParentCodes.setFontText(Button.ARIAL_BOLD_21);
        titleParentCodes.setEnable(false);
        titleParentCodes.setLocationText(15, 0);
        titleParentCodes.setStrokeWidth(0);
        titleParentCodes.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_4, Setting.GRADIENT_POINTS2_4,
                Setting.GRADIENT_COLORS_4);
        titleParentCodes.setSizeButton(
                headerPanel.getWidth() - titleOrder.getWidth() - titleCode.getWidth() - titleName.getWidth()
                        - titleCredits.getWidth(),
                headerPanel.getHeight());
        titleParentCodes.setBounds(titleCredits.getX() + titleCredits.getWidth(), 0,
                titleParentCodes.getWidth(), titleParentCodes.getHeight());

        // Create scrollPanel
        scrollPanel = new JPanel();
        int heightScroll = 0;
        int countSubjects = 0;

        // START Create panel for compulsory subjects (if have)
        for (Subject subject : plan.getSubjects()) {
            PanelSubject panelSubject = new PanelSubject(0, heightScroll, subject, headerPanel.getWidth(),
                    Button.ARIAL_BOLD_18, countSubjects + 1);
            if (countSubjects % 2 == 0) {
                panelSubject.setBackgroundColorPanelSubject(COLOR_SUBJECT_1);
            } else {
                panelSubject.setBackgroundColorPanelSubject(COLOR_SUBJECT_2);
            }
            countSubjects++;
            scrollPanel.add(panelSubject);
            heightScroll += panelSubject.getHeight();
        }

        scrollPanel.setLayout(null);
        scrollPanel.setSize(contentPanel.getWidth(), heightScroll);
        updateContentShowing();

        // Add sub panels to this panel
        setBackground(COLOR_STROKE);
        headerPanel.setBackground(COLOR_STROKE);
        add(headerPanel);
        add(contentPanel);
        headerPanel.add(titleOrder);
        headerPanel.add(titleCode);
        headerPanel.add(titleName);
        headerPanel.add(titleCredits);
        headerPanel.add(titleParentCodes);
        contentPanel.add(scrollPanel);

        // Add MouseWheelListener to this panel
        addMouseWheelListener(new MouseWheelHandler());

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
        return Math.max(0, this.scrollPanel.getHeight() - this.contentPanel.getHeight());
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
}
