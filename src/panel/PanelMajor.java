package src.panel;

import javax.swing.JPanel;
import src.launcher.Setting;
import src.objects.Button;
import src.objects.KnowledgePart;
import src.objects.Major;
import src.objects.Subject;
import java.awt.event.MouseWheelListener;
import java.util.List;
import java.awt.event.MouseWheelEvent;
import java.awt.Graphics;
import java.awt.Color;

public class PanelMajor extends JPanel {
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

    public static final Color COLOR_BACKGROUND_KNOWLEDGE_NAME = Setting.COLOR_BLUE_04;
    public static final Color COLOR_BACKGROUND_DESCRIPTION = Setting.COLOR_BLUE_04;
    public static final Color COLOR_BACKGROUND_DESCRIPTION_2 = Setting.COLOR_BLUE_04;
    public static final Color COLOR_SUBJECT_1 = Setting.COLOR_GRAY_05;
    public static final Color COLOR_SUBJECT_2 = Setting.COLOR_GRAY_04;

    // Properties
    private int width, height; // size of this panel
    private int xPos, yPos, rootLocationType; // location of top-left point
    private Major major; // data major
    private JPanel headerPanel; // contains title
    private JPanel contentPanel; // a part of scrollPanel wil be shown here
    private JPanel scrollPanel; // Contains all information of major
    private int cursorScroll = 0; // to define where the contentPanel in scrolllPanel

    // Constructor
    public PanelMajor(int x, int y, int width, int height, Major major, int rootLocationType) {
        // Properties, Objects
        this.width = width;
        this.height = height;
        this.major = major;
        this.rootLocationType = rootLocationType;
        setLayout(null);
        setSize(width, height);
        setLocation(x, y, rootLocationType);

        // Create panels
        headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setSize(width, height / 10);
        headerPanel.setBounds(0, 0, headerPanel.getWidth(), headerPanel.getHeight());

        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setSize(width, height - headerPanel.getHeight());
        contentPanel.setBounds(0, headerPanel.getHeight(), contentPanel.getWidth(), contentPanel.getHeight());

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
        titleName.setBounds(titleCode.getX() + +titleCode.getWidth(), 0, titleName.getWidth(),
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
        titleParentCodes.setBounds(
                titleCredits.getX() + titleCredits.getWidth(), 0,
                titleParentCodes.getWidth(), titleParentCodes.getHeight());

        // Create scrollPanel
        scrollPanel = new JPanel();
        int heightScroll = 0;
        int countSubjects = 0;
        for (KnowledgePart knowledgePart : major.getKnowledgeParts()) {
            // START Create panel for knowledgePart

            // START Create panel for knowledge name
            PanelString knowledgeNamePanel = new PanelString(0, heightScroll, knowledgePart.getName(), width,
                    Button.ARIAL_BOLD_15,
                    PanelString.TOP_LEFT, 0);
            knowledgeNamePanel.setBackground(COLOR_BACKGROUND_KNOWLEDGE_NAME);
            knowledgeNamePanel.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_6, Setting.GRADIENT_POINTS2_6,
                    Setting.GRADIENT_COLORS_6);
            scrollPanel.add(knowledgeNamePanel);
            heightScroll += knowledgeNamePanel.getHeight();
            // FINISH Create panel for knowledge name

            // START Create panel for description Compulsory and subjects Compulsory (there
            // are many description Compulsory)
            for (int count = 0; count < knowledgePart.getNumberOfCompulsorySubjectsList(); count++) {
                // Get String of description
                String compulsoryDescription = knowledgePart.getDescriptionCompulsory().get(count) + " ("
                        + knowledgePart.getMinCreditsCompulsorySubjects().get(count) + " tín chỉ)";
                // START Create panel for description
                PanelString desCompulsoryPanel = new PanelString(0, heightScroll,
                        compulsoryDescription, width,
                        Button.ARIAL_BOLD_15,
                        PanelString.TOP_LEFT, 0);
                desCompulsoryPanel.setBackground(COLOR_BACKGROUND_DESCRIPTION);
                scrollPanel.add(desCompulsoryPanel);
                heightScroll += desCompulsoryPanel.getHeight();
                // FINISH Create panel for description

                // Get list subjects of this compulsory
                List<Subject> compulsorySubjectList = knowledgePart.getCompulsorySubjects().get(count);
                // START Create panel for subjects
                for (Subject subject : compulsorySubjectList) {
                    PanelSubject panelSubject = new PanelSubject(0, heightScroll, subject, width,
                            Button.ARIAL_BOLD_15, countSubjects + 1);
                    if (countSubjects % 2 == 0) {
                        panelSubject.setBackgroundColorPanelSubject(COLOR_SUBJECT_1);
                    } else {
                        panelSubject.setBackgroundColorPanelSubject(COLOR_SUBJECT_2);
                    }
                    countSubjects++;
                    scrollPanel.add(panelSubject);
                    heightScroll += panelSubject.getHeight();
                }
                // FINISH Create panel for subjects
            }
            // FINISH Create panel for description Compulsory and subjects Compulsory (there
            // are
            // many description Compulsory)

            // START Create panel for main description of optional subjects
            if (!knowledgePart.getMainDescriptionOptionalSubjects().isEmpty()) {
                String str = knowledgePart.getMainDescriptionOptionalSubjects();
                PanelString desMainOptionalSubjectPanel = new PanelString(0, heightScroll,
                        str, width,
                        Button.ARIAL_BOLD_15,
                        PanelString.TOP_LEFT, 0);
                desMainOptionalSubjectPanel.setBackground(COLOR_BACKGROUND_DESCRIPTION_2);
                scrollPanel.add(desMainOptionalSubjectPanel);
                heightScroll += desMainOptionalSubjectPanel.getHeight();
            }
            // FINISH Create panel for main description of optional subjects

            // START Create panel for description Optional and subjects optional (there are
            // many description optional)
            for (int count = 0; count < knowledgePart.getNumberOfOptionalSubjectsList(); count++) {
                // Get String of description
                String optionalDescription = knowledgePart.getDescriptionOptionals().get(count) + " ("
                        + knowledgePart.getMinCreditsOptionalSubjects().get(count) + " tín chỉ)";
                // START Create panel for description
                PanelString desOptionalPanel = new PanelString(0, heightScroll,
                        optionalDescription, width,
                        Button.ARIAL_BOLD_15,
                        PanelString.TOP_LEFT, 0);
                desOptionalPanel.setBackground(COLOR_BACKGROUND_DESCRIPTION);
                scrollPanel.add(desOptionalPanel);
                heightScroll += desOptionalPanel.getHeight();
                // FINISH Create panel for description

                // Get list subjects of this optional
                List<Subject> optionalSubjectList = knowledgePart.getOptionalSubjects().get(count);
                // START Create panel for subjects
                for (Subject subject : optionalSubjectList) {
                    PanelSubject panelSubject = new PanelSubject(0, heightScroll, subject, width,
                            Button.ARIAL_BOLD_15, countSubjects + 1);
                    if (countSubjects % 2 == 0) {
                        panelSubject.setBackgroundColorPanelSubject(COLOR_SUBJECT_1);
                    } else {
                        panelSubject.setBackgroundColorPanelSubject(COLOR_SUBJECT_2);
                    }
                    countSubjects++;
                    scrollPanel.add(panelSubject);
                    heightScroll += panelSubject.getHeight();
                }
                // FINISH Create panel for subjects
            }
            // FINISH Create panel for description Optional and subjects optional (there are
            // many description optional)

            // FINISH Create panel for 'knowledgePart'
        }

        scrollPanel.setLayout(null);
        scrollPanel.setSize(contentPanel.getWidth(), heightScroll);
        updateContentShowing();

        // Add sub panels to this panel
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

    // Get major
    public Major getMajor() {
        return this.major;
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
