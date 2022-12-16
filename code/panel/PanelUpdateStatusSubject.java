package code.panel;

import javax.swing.JPanel;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import code.Setting;
import code.dialog.DialogUpdateStatusSubject;
import code.file_handler.WriteFile;
import code.objects.Button;
import code.objects.Plan;
import code.objects.Subject;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class PanelUpdateStatusSubject extends JPanel {
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

    public static final Color COLOR_SUBJECT_1 = Setting.COLOR_GRAY_05;
    public static final Color COLOR_SUBJECT_2 = Setting.COLOR_GRAY_04;

    public static final Color COLOR_SUBJECT_ENTERED = Setting.COLOR_VIOLET_03;
    public static final Color COLOR_SUBJECT_EXITED_1 = Setting.COLOR_GRAY_05;
    public static final Color COLOR_SUBJECT_EXITED_2 = Setting.COLOR_GRAY_04;
    public static final Color COLOR_SUBJECT_PRESSED = Setting.COLOR_GREEN_03;
    public static final Color COLOR_STROKE = Setting.COLOR_BLACK;

    // Properties
    private int width, height; // size of this panel
    private int xPos, yPos, rootLocationType; // location of top-left point
    private Plan plan; // data plan
    private JPanel headerPanel; // contains title
    private JPanel contentPanel; // a part of scrollPanel wil be shown here
    private JPanel summaryPanel; //
    private JPanel scrollPanel; // Contains all information of plan
    private int cursorScroll = 0; // to define where the contentPanel in scrolllPanel
    private PanelSubject3[] panelSubjects = null;
    private PanelString[] panelInfors = null;
    private int indexPlan;

    // Constructor
    public PanelUpdateStatusSubject(int x, int y, int width, int height, Plan plan, int indexPlan,
            int rootLocationType) {
        // Properties, Objects
        this.width = width;
        this.height = height;
        this.plan = plan;
        this.indexPlan = indexPlan;
        this.rootLocationType = rootLocationType;
        setLayout(null);
        setSize(width, height);
        setLocation(x, y, rootLocationType);

        // Create panels
        headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setSize(width / 16 * 11 - STROKE_WIDTH * 2, height / 10 - 2 * STROKE_WIDTH);
        headerPanel.setBounds(STROKE_WIDTH, STROKE_WIDTH, headerPanel.getWidth(), headerPanel.getHeight());

        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setSize(headerPanel.getWidth(), height - headerPanel.getHeight() - 2 * STROKE_WIDTH);
        contentPanel.setBounds(STROKE_WIDTH, headerPanel.getHeight() + 2 * STROKE_WIDTH, contentPanel.getWidth(),
                contentPanel.getHeight());

        summaryPanel = new JPanel();
        summaryPanel.setLayout(null);
        summaryPanel.setSize(width - headerPanel.getWidth() - 2 * STROKE_WIDTH, height);
        summaryPanel.setBounds(headerPanel.getWidth() + 2 * STROKE_WIDTH, 0, summaryPanel.getWidth(),
                summaryPanel.getHeight());

        // Create titles for headerPanel
        Button titleOrder = new Button("STT");
        titleOrder.setFontText(new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_SMALL_2));
        titleOrder.setEnable(false);
        titleOrder.setLocationText(0, 0);
        titleOrder.setStrokeWidth(0);
        titleOrder.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_4, Setting.GRADIENT_POINTS2_4,
                Setting.GRADIENT_COLORS_4);
        titleOrder.setSizeButton(headerPanel.getWidth() / 24 * 2, headerPanel.getHeight());
        titleOrder.setBounds(0, 0, titleOrder.getWidth(), titleOrder.getHeight());

        Button titleCode = new Button("Mã");
        titleCode.setFontText(new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_SMALL_2));
        titleCode.setEnable(false);
        titleCode.setLocationText(15, 0);
        titleCode.setStrokeWidth(0);
        titleCode.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_4, Setting.GRADIENT_POINTS2_4,
                Setting.GRADIENT_COLORS_4);
        titleCode.setSizeButton(headerPanel.getWidth() / 24 * 3, headerPanel.getHeight());
        titleCode.setBounds(titleOrder.getWidth(), 0, titleCode.getWidth(), titleCode.getHeight());

        Button titleName = new Button("Tên môn học");
        titleName.setFontText(new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_SMALL_2));
        titleName.setEnable(false);
        titleName.setLocationText(15, 0);
        titleName.setStrokeWidth(0);
        titleName.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_4, Setting.GRADIENT_POINTS2_4,
                Setting.GRADIENT_COLORS_4);
        titleName.setSizeButton(headerPanel.getWidth() / 24 * 12, headerPanel.getHeight());
        titleName.setBounds(titleCode.getX() + titleCode.getWidth(), 0, titleName.getWidth(),
                titleName.getHeight());

        Button titleCredits = new Button("Số tín");
        titleCredits.setFontText(new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_SMALL_2));
        titleCredits.setEnable(false);
        titleCredits.setLocationText(0, 0);
        titleCredits.setStrokeWidth(0);
        titleCredits.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_4, Setting.GRADIENT_POINTS2_4,
                Setting.GRADIENT_COLORS_4);
        titleCredits.setSizeButton(headerPanel.getWidth() / 48 * 5,
                headerPanel.getHeight());
        titleCredits.setBounds(titleName.getX() + titleName.getWidth(), 0,
                titleCredits.getWidth(), titleCredits.getHeight());

        Button titleStatus = new Button("Trạng thái");
        titleStatus.setFontText(new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_SMALL_2));
        titleStatus.setEnable(false);
        titleStatus.setLocationText(0, 0);
        titleStatus.setStrokeWidth(0);
        titleStatus.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_4, Setting.GRADIENT_POINTS2_4,
                Setting.GRADIENT_COLORS_4);
        titleStatus.setSizeButton(
                headerPanel.getWidth() - titleOrder.getWidth() - titleCode.getWidth() - titleName.getWidth()
                        - titleCredits.getWidth(),
                headerPanel.getHeight());
        titleStatus.setBounds(titleCredits.getX() + titleCredits.getWidth(), 0,
                titleStatus.getWidth(), titleStatus.getHeight());

        // Create scrollPanel
        scrollPanel = new JPanel();
        int heightScroll = 0;
        int countSubjects = 0;
        panelSubjects = new PanelSubject3[plan.getSubjects().size()];

        // START Create panel for compulsory subjects (if have)
        for (Subject subject : plan.getSubjects()) {
            PanelSubject3 panelSubject = new PanelSubject3(0, heightScroll, subject, headerPanel.getWidth(),
                    null, countSubjects + 1);
            panelSubjects[countSubjects] = panelSubject;
            panelSubjects[countSubjects].addMouseListener(new MouseHandler());
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

        // Create content for summary panel
        panelInfors = new PanelString[8];
        int tempHeight = headerPanel.getHeight() * 2;
        panelInfors[0] = new PanelString(5, tempHeight, "Tổng số môn học: " + plan.getSubjects().size(),
                summaryPanel.getWidth(), null, PanelString.TOP_LEFT, 15);
        panelInfors[0].setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        tempHeight += panelInfors[0].getHeight() + 15;
        panelInfors[1] = new PanelString(5, tempHeight, "Tổng số tín chỉ: " + plan.getTotalCredits(),
                summaryPanel.getWidth(), null, PanelString.TOP_LEFT, 15);
        panelInfors[1].setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        tempHeight += panelInfors[1].getHeight() + 15;
        panelInfors[2] = new PanelString(5, tempHeight, "Số môn học đã hoàn thành: " + plan.getNumberSubjectCompleted(),
                summaryPanel.getWidth(), null, PanelString.TOP_LEFT, 15);
        panelInfors[2].setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        tempHeight += panelInfors[2].getHeight() + 15;
        panelInfors[3] = new PanelString(5, tempHeight, "Số tín chỉ đã hoàn thành: " + plan.getNumberCreditCompleted(),
                summaryPanel.getWidth(), null, PanelString.TOP_LEFT, 15);
        panelInfors[3].setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        tempHeight += panelInfors[3].getHeight() + 15;
        panelInfors[4] = new PanelString(5, tempHeight, "Số môn học đã đăng kí: " + plan.getNumberSubjectRegistered(),
                summaryPanel.getWidth(), null, PanelString.TOP_LEFT, 15);
        panelInfors[4].setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        tempHeight += panelInfors[4].getHeight() + 15;
        panelInfors[5] = new PanelString(5, tempHeight, "Số tín chỉ đã đăng kí: " + plan.getNumberCreditRegistered(),
                summaryPanel.getWidth(), null, PanelString.TOP_LEFT, 15);
        panelInfors[5].setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        tempHeight += panelInfors[5].getHeight() + 15;
        panelInfors[6] = new PanelString(5, tempHeight,
                "Số môn học dự tính đăng kí: " + plan.getNumberSubjectGoingToRegister(),
                summaryPanel.getWidth(), null, PanelString.TOP_LEFT, 15);
        panelInfors[6].setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        tempHeight += panelInfors[6].getHeight() + 15;
        panelInfors[7] = new PanelString(5, tempHeight,
                "Số tín chỉ dự tính đăng kí: " + plan.getNumberCreditGoingToRegister(),
                summaryPanel.getWidth(), null, PanelString.TOP_LEFT, 15);
        panelInfors[7].setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        tempHeight += panelInfors[7].getHeight() + 15;

        // summaryPanel.setBackground(Setting.COLOR_GREEN_03);

        // Add sub panels to this panel
        setBackground(COLOR_STROKE);
        add(headerPanel);
        add(contentPanel);
        add(summaryPanel);
        headerPanel.add(titleOrder);
        headerPanel.add(titleCode);
        headerPanel.add(titleName);
        headerPanel.add(titleCredits);
        headerPanel.add(titleStatus);
        contentPanel.add(scrollPanel);
        summaryPanel.add(panelInfors[0]);
        summaryPanel.add(panelInfors[1]);
        summaryPanel.add(panelInfors[2]);
        summaryPanel.add(panelInfors[3]);
        summaryPanel.add(panelInfors[4]);
        summaryPanel.add(panelInfors[5]);
        summaryPanel.add(panelInfors[6]);
        summaryPanel.add(panelInfors[7]);

        // Add MouseWheelListener to this panel
        addMouseWheelListener(new MouseWheelHandler());

    }

    // Update contentPanel
    public void updateContentShowing() {
        scrollPanel.setBounds(0, -cursorScroll, scrollPanel.getWidth(), scrollPanel.getHeight());
    }

    // Update content data
    public void updateContentData() {
        // Update contentPanel
        contentPanel.remove(scrollPanel);
        scrollPanel.removeAll();

        int heightScroll = 0;
        int countSubjects = 0;
        panelSubjects = new PanelSubject3[plan.getSubjects().size()];

        // START Create panel for compulsory subjects (if have)
        for (Subject subject : plan.getSubjects()) {
            PanelSubject3 panelSubject = new PanelSubject3(0, heightScroll, subject, headerPanel.getWidth(),
                    null, countSubjects + 1);
            panelSubjects[countSubjects] = panelSubject;
            panelSubjects[countSubjects].addMouseListener(new MouseHandler());
            if (countSubjects % 2 == 0) {
                panelSubject.setBackgroundColorPanelSubject(COLOR_SUBJECT_1);
            } else {
                panelSubject.setBackgroundColorPanelSubject(COLOR_SUBJECT_2);
            }
            countSubjects++;
            scrollPanel.add(panelSubject);
            heightScroll += panelSubject.getHeight();
        }

        contentPanel.add(scrollPanel);

        // Update summary panel
        summaryPanel.removeAll();
        int tempHeight = headerPanel.getHeight() * 2;
        panelInfors[0] = new PanelString(5, tempHeight, "Tổng số môn học: " + plan.getSubjects().size(),
                summaryPanel.getWidth(), null, PanelString.TOP_LEFT, 15);
        panelInfors[0].setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        tempHeight += panelInfors[0].getHeight() + 15;
        panelInfors[1] = new PanelString(5, tempHeight, "Tổng số tín chỉ: " + plan.getTotalCredits(),
                summaryPanel.getWidth(), null, PanelString.TOP_LEFT, 15);
        panelInfors[1].setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        tempHeight += panelInfors[1].getHeight() + 15;
        panelInfors[2] = new PanelString(5, tempHeight, "Số môn học đã hoàn thành: " + plan.getNumberSubjectCompleted(),
                summaryPanel.getWidth(), null, PanelString.TOP_LEFT, 15);
        panelInfors[2].setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        tempHeight += panelInfors[2].getHeight() + 15;
        panelInfors[3] = new PanelString(5, tempHeight, "Số tín chỉ đã hoàn thành: " + plan.getNumberCreditCompleted(),
                summaryPanel.getWidth(), null, PanelString.TOP_LEFT, 15);
        panelInfors[3].setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        tempHeight += panelInfors[3].getHeight() + 15;
        panelInfors[4] = new PanelString(5, tempHeight, "Số môn học đã đăng kí: " + plan.getNumberSubjectRegistered(),
                summaryPanel.getWidth(), null, PanelString.TOP_LEFT, 15);
        panelInfors[4].setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        tempHeight += panelInfors[4].getHeight() + 15;
        panelInfors[5] = new PanelString(5, tempHeight, "Số tín chỉ đã đăng kí: " + plan.getNumberCreditRegistered(),
                summaryPanel.getWidth(), null, PanelString.TOP_LEFT, 15);
        panelInfors[5].setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        tempHeight += panelInfors[5].getHeight() + 15;
        panelInfors[6] = new PanelString(5, tempHeight,
                "Số môn học dự tính đăng kí: " + plan.getNumberSubjectGoingToRegister(),
                summaryPanel.getWidth(), null, PanelString.TOP_LEFT, 15);
        panelInfors[6].setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        tempHeight += panelInfors[6].getHeight() + 15;
        panelInfors[7] = new PanelString(5, tempHeight,
                "Số tín chỉ dự tính đăng kí: " + plan.getNumberCreditGoingToRegister(),
                summaryPanel.getWidth(), null, PanelString.TOP_LEFT, 15);
        panelInfors[7].setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        tempHeight += panelInfors[7].getHeight() + 15;

        summaryPanel.add(panelInfors[0]);
        summaryPanel.add(panelInfors[1]);
        summaryPanel.add(panelInfors[2]);
        summaryPanel.add(panelInfors[3]);
        summaryPanel.add(panelInfors[4]);
        summaryPanel.add(panelInfors[5]);
        summaryPanel.add(panelInfors[6]);
        summaryPanel.add(panelInfors[7]);

        repaint();
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

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            for (int i = 0; i < panelSubjects.length; i++) {
                if (e.getSource() == panelSubjects[i]) {
                    if (plan.getSubjects().get(i).getState() != Subject.COMPLETED) {
                        DialogUpdateStatusSubject dialog = new DialogUpdateStatusSubject(Setting.WIDTH / 2,
                                Setting.HEIGHT / 2,
                                Setting.WIDTH / 3 * 2, Setting.HEIGHT / 5 * 4,
                                DialogUpdateStatusSubject.CENTER_CENTER, "Update status subject", (new String[] {}),
                                plan.getSubjects().get(i));
                        plan.getSubjects().set(i, dialog.getSubject());
                        WriteFile.editSubject(indexPlan, i, plan.getSubjects().get(i));
                    }
                }
            }
            updateContentData();
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            for (int count = 0; count < panelSubjects.length; count++) {
                if (e.getSource() == panelSubjects[count]) {
                    if (plan.getSubjects().get(count).getState() != Subject.COMPLETED) {
                        panelSubjects[count].setBackgroundColorPanelSubject(COLOR_SUBJECT_ENTERED);
                    }
                }
            }
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            for (int count = 0; count < panelSubjects.length; count++) {
                if (e.getSource() == panelSubjects[count]) {
                    if (count % 2 == 0) {
                        panelSubjects[count].setBackgroundColorPanelSubject(COLOR_SUBJECT_EXITED_1);
                    } else {
                        panelSubjects[count].setBackgroundColorPanelSubject(COLOR_SUBJECT_EXITED_2);
                    }
                }
            }
            repaint();
        }
    }
}
