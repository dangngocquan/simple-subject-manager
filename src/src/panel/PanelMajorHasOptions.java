package src.panel;

import javax.swing.JPanel;
import src.dialog.DialogMessage;
import src.launcher.Application;
import src.launcher.Setting;
import src.objects.Button;
import src.objects.KnowledgePart;
import src.objects.Major;
import src.objects.Subject;
import java.awt.event.MouseWheelListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics;
import java.awt.Color;

public class PanelMajorHasOptions extends JPanel {
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

    public static final Color COLOR_SUBJECT_ENTERED = Setting.COLOR_VIOLET_03;
    public static final Color COLOR_SUBJECT_EXITED_1 = Setting.COLOR_GRAY_05;
    public static final Color COLOR_SUBJECT_EXITED_2 = Setting.COLOR_GRAY_04;
    public static final Color COLOR_SUBJECT_PRESSED = Setting.COLOR_GREEN_03;

    // Properties
    private int width, height; // size of this panel
    private int xPos, yPos, rootLocationType; // location of top-left point
    private Major major; // data major
    private JPanel headerPanel; // contains title
    private JPanel contentPanel; // a part of scrollPanel wil be shown here
    private JPanel scrollPanel; // Contains all information of major
    private int cursorScroll = 0; // to define where the contentPanel in scrolllPanel
    private PanelSubject[] panelSubjects = null;
    private boolean[] isSelectedSubject = null;

    // Constructor
    public PanelMajorHasOptions(int x, int y, int width, int height, Major major, int rootLocationType,
            Application frame) {
        // Properties, Objects
        this.width = width;
        this.height = height;
        this.major = major;
        this.rootLocationType = rootLocationType;
        this.panelSubjects = new PanelSubject[major.getSubjects().size()];
        this.isSelectedSubject = new boolean[major.getSubjects().size()];
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
        titleOrder.setStrokeWidth(0);
        titleOrder.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_4, Setting.GRADIENT_POINTS2_4,
                Setting.GRADIENT_COLORS_4);
        titleOrder.setSizeButton(headerPanel.getWidth() / 12, headerPanel.getHeight());
        titleOrder.setBounds(0, 0, titleOrder.getWidth(), titleOrder.getHeight());

        Button titleCode = new Button("Mã");
        titleCode.setFontText(Button.ARIAL_BOLD_21);
        titleCode.setEnable(false);
        titleCode.setStrokeWidth(0);
        titleCode.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_4, Setting.GRADIENT_POINTS2_4,
                Setting.GRADIENT_COLORS_4);
        titleCode.setSizeButton(headerPanel.getWidth() / 12, headerPanel.getHeight());
        titleCode.setBounds(titleOrder.getX() + titleOrder.getWidth(), 0, titleCode.getWidth(), titleCode.getHeight());

        Button titleName = new Button("Tên môn học");
        titleName.setFontText(Button.ARIAL_BOLD_21);
        titleName.setEnable(false);
        titleName.setStrokeWidth(0);
        titleName.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_4, Setting.GRADIENT_POINTS2_4,
                Setting.GRADIENT_COLORS_4);
        titleName.setSizeButton(headerPanel.getWidth() / 12 * 5, headerPanel.getHeight());
        titleName.setBounds(titleCode.getX() + titleCode.getWidth(), 0, titleName.getWidth(),
                titleName.getHeight());

        Button titleCredits = new Button("Số tín");
        titleCredits.setFontText(Button.ARIAL_BOLD_21);
        titleCredits.setEnable(false);
        titleCredits.setStrokeWidth(0);
        titleCredits.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_4, Setting.GRADIENT_POINTS2_4,
                Setting.GRADIENT_COLORS_4);
        titleCredits.setSizeButton(headerPanel.getWidth() / 12, headerPanel.getHeight());
        titleCredits.setBounds(titleName.getX() + titleName.getWidth(), 0,
                titleCredits.getWidth(), titleCredits.getHeight());

        Button titleParentCodes = new Button("Mã học phần tiên quyết");
        titleParentCodes.setFontText(Button.ARIAL_BOLD_21);
        titleParentCodes.setEnable(false);
        titleParentCodes.setStrokeWidth(0);
        titleParentCodes.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_4, Setting.GRADIENT_POINTS2_4,
                Setting.GRADIENT_COLORS_4);
        titleParentCodes.setSizeButton(
                headerPanel.getWidth() - titleCode.getWidth() - titleName.getWidth() - titleCredits.getWidth(),
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
                    Button.ARIAL_BOLD_18,
                    PanelString.TOP_LEFT, 0);
            knowledgeNamePanel.setBackground(COLOR_BACKGROUND_KNOWLEDGE_NAME);
            knowledgeNamePanel.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_6, Setting.GRADIENT_POINTS2_6,
                    Setting.GRADIENT_COLORS_6);
            scrollPanel.add(knowledgeNamePanel);
            heightScroll += knowledgeNamePanel.getHeight();
            // FINISH Create panel for knowledge name

            // START Create panel for description Compulsory and subjects Compulsory (there
            // are
            // many description Compulsory)
            for (int count = 0; count < knowledgePart.getNumberOfCompulsorySubjectsList(); count++) {
                // Get String of description
                String compulsoryDescription = knowledgePart.getDescriptionCompulsory().get(count) + " ("
                        + knowledgePart.getMinCreditsCompulsorySubjects().get(count) + " tín chỉ)";
                // START Create panel for description
                PanelString desCompulsoryPanel = new PanelString(0, heightScroll,
                        compulsoryDescription, width,
                        Button.ARIAL_BOLD_18,
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
                            Button.ARIAL_BOLD_18, countSubjects + 1);
                    if (countSubjects % 2 == 0) {
                        panelSubject.setBackgroundColorPanelSubject(COLOR_SUBJECT_1);
                    } else {
                        panelSubject.setBackgroundColorPanelSubject(COLOR_SUBJECT_2);
                    }

                    panelSubjects[countSubjects] = panelSubject;
                    panelSubjects[countSubjects].addMouseListener(new MouseHandler());

                    countSubjects++;
                    scrollPanel.add(panelSubject);
                    heightScroll += panelSubject.getHeight();
                }
                // FINISH Create panel for subjects
            }
            // FINISH Create panel for description Compulsory and subjects Compulsory (there
            // are
            // many description Compulsory)

            // START Create panel for main description of optional subjects type OR
            if (!knowledgePart.getMainDescriptionOptionalSubjects().isEmpty()) {
                String str = knowledgePart.getMainDescriptionOptionalSubjects();
                PanelString desMainOptionalSubjectPanel = new PanelString(0, heightScroll,
                        str, width,
                        Button.ARIAL_BOLD_18,
                        PanelString.TOP_LEFT, 0);
                desMainOptionalSubjectPanel.setBackground(COLOR_BACKGROUND_DESCRIPTION_2);
                scrollPanel.add(desMainOptionalSubjectPanel);
                heightScroll += desMainOptionalSubjectPanel.getHeight();
            }
            // FINISH Create panel for main description of optional subjects type OR

            // START Create panel for description Optional and subjects optional type OR
            // (there are
            // many description optional)
            for (int count = 0; count < knowledgePart.getNumberOfOptionalSubjectsList(); count++) {
                // Get String of description
                String optionalDescription = knowledgePart.getDescriptionOptionals().get(count) + " ("
                        + knowledgePart.getMinCreditsOptionalSubjects().get(count) + " tín chỉ)";
                // START Create panel for description
                PanelString desOptionalPanel = new PanelString(0, heightScroll,
                        optionalDescription, width,
                        Button.ARIAL_BOLD_18,
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
                            Button.ARIAL_BOLD_18, countSubjects + 1);
                    if (countSubjects % 2 == 0) {
                        panelSubject.setBackgroundColorPanelSubject(COLOR_SUBJECT_1);
                    } else {
                        panelSubject.setBackgroundColorPanelSubject(COLOR_SUBJECT_2);
                    }

                    panelSubjects[countSubjects] = panelSubject;
                    panelSubjects[countSubjects].addMouseListener(new MouseHandler());

                    countSubjects++;
                    scrollPanel.add(panelSubject);
                    heightScroll += panelSubject.getHeight();
                }
                // FINISH Create panel for subjects
            }
            // FINISH Create panel for description Optional and subjects optional type OR
            // (there are
            // many description optional)

            // START Create panel for main description of optional subjects type AND
            if (!knowledgePart.getMainDescriptionOptionalSubjectsAND().isEmpty()) {
                String str = knowledgePart.getMainDescriptionOptionalSubjectsAND();
                PanelString desMainOptionalSubjectANDPanel = new PanelString(0, heightScroll,
                        str, width,
                        Button.ARIAL_BOLD_18,
                        PanelString.TOP_LEFT, 0);
                desMainOptionalSubjectANDPanel.setBackground(COLOR_BACKGROUND_DESCRIPTION_2);
                scrollPanel.add(desMainOptionalSubjectANDPanel);
                heightScroll += desMainOptionalSubjectANDPanel.getHeight();
            }
            // FINISH Create panel for main description of optional subjects type AND

            // START Create panel for description Optional and subjects optional type AND
            // (there are
            // many description optional)
            for (int count = 0; count < knowledgePart.getNumberOfOptionalSubjectsANDList(); count++) {
                // Get String of description
                String optionalDescriptionAND = knowledgePart.getDescriptionOptionalsAND().get(count) + " ("
                        + knowledgePart.getMinCreditsOptionalSubjectsAND().get(count) + " tín chỉ)";
                // START Create panel for description
                PanelString desOptionalPanelAND = new PanelString(0, heightScroll,
                        optionalDescriptionAND, width,
                        Button.ARIAL_BOLD_18,
                        PanelString.TOP_LEFT, 0);
                desOptionalPanelAND.setBackground(COLOR_BACKGROUND_DESCRIPTION);
                scrollPanel.add(desOptionalPanelAND);
                heightScroll += desOptionalPanelAND.getHeight();
                // FINISH Create panel for description

                // Get list subjects of this optional
                List<Subject> optionalSubjectANDList = knowledgePart.getOptionalSubjectsAND().get(count);
                // START Create panel for subjects
                for (Subject subject : optionalSubjectANDList) {
                    PanelSubject panelSubject = new PanelSubject(0, heightScroll, subject, width,
                            Button.ARIAL_BOLD_18, countSubjects + 1);
                    if (countSubjects % 2 == 0) {
                        panelSubject.setBackgroundColorPanelSubject(COLOR_SUBJECT_1);
                    } else {
                        panelSubject.setBackgroundColorPanelSubject(COLOR_SUBJECT_2);
                    }

                    panelSubjects[countSubjects] = panelSubject;
                    panelSubjects[countSubjects].addMouseListener(new MouseHandler());

                    countSubjects++;
                    scrollPanel.add(panelSubject);
                    heightScroll += panelSubject.getHeight();
                }
                // FINISH Create panel for subjects
            }
            // FINISH Create panel for description Optional and subjects optional type OR
            // (there are
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

    // Check if the plan has enought credit
    public boolean isValidPlan() {
        // Check each knowledgePart
        int indexSubject = 0;
        for (KnowledgePart knowledgePart : major.getKnowledgeParts()) {
            int tempCredits = 0;
            // START Check compulsory subjects (if have)
            for (int count = 0; count < knowledgePart.getNumberOfCompulsorySubjectsList(); count++) {
                // Get list subjects of this compulsory
                List<Subject> compulsorySubjectList = knowledgePart.getCompulsorySubjects().get(count);
                tempCredits = 0;
                // START Check a list compulsory subjects
                for (Subject subject : compulsorySubjectList) {
                    if (isSelectedSubject[indexSubject]) {
                        tempCredits += subject.getNumberCredits();
                    }
                    indexSubject++;
                }
                if (tempCredits < knowledgePart.getMinCreditsCompulsorySubjects().get(count)) {
                    System.out.println(tempCredits + " " + knowledgePart.getMinCreditsCompulsorySubjects().get(count));
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER,
                            "Information", new String[] { "Bạn chưa đăng kí đủ tín chỉ ở", knowledgePart.getName() },
                            Setting.WARNING);
                    return false;
                }
                // FINISH Check a list compulsory subjects
            }
            // FINISH Check compulsory subjects (if have)

            boolean isEnough = false;
            if (knowledgePart.getNumberOfOptionalSubjectsList() == 0) {
                isEnough = true;
            }
            // START Check each list optional subjects
            for (int count = 0; count < knowledgePart.getNumberOfOptionalSubjectsList(); count++) {
                // Get list subjects of this optional
                List<Subject> optionalSubjectList = knowledgePart.getOptionalSubjects().get(count);
                tempCredits = 0;
                // START Check a list optional subjects
                for (Subject subject : optionalSubjectList) {
                    if (isSelectedSubject[indexSubject]) {
                        tempCredits += subject.getNumberCredits();
                    }
                    indexSubject++;
                }
                if (tempCredits >= knowledgePart.getMinCreditsOptionalSubjects().get(count)) {
                    isEnough = true;
                }
                // FINISH Check a list optional subjects
            }

            if (!isEnough) {
                new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                        DialogMessage.CENTER_CENTER,
                        "Information", new String[] { "Bạn chưa đăng kí đủ tín chỉ ở", knowledgePart.getName() },
                        Setting.WARNING);
                return false;
            }
            // FINISH Check each list optional subjects

            boolean isEnoughAND = true;
            if (knowledgePart.getNumberOfOptionalSubjectsANDList() == 0) {
                isEnoughAND = true;
            }
            // START Check each list optional subjects type AND
            for (int count = 0; count < knowledgePart.getNumberOfOptionalSubjectsANDList(); count++) {
                // Get list subjects of this optional type AND
                List<Subject> optionalSubjectList = knowledgePart.getOptionalSubjectsAND().get(count);
                tempCredits = 0;
                // START Check a list optional subjects AND
                for (Subject subject : optionalSubjectList) {
                    if (isSelectedSubject[indexSubject]) {
                        tempCredits += subject.getNumberCredits();
                    }
                    indexSubject++;
                }
                if (tempCredits < knowledgePart.getMinCreditsOptionalSubjectsAND().get(count)) {
                    isEnoughAND = false;
                    break;
                }
                // FINISH Check a list optional subjects
            }

            if (!isEnoughAND) {
                new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                        DialogMessage.CENTER_CENTER,
                        "Information", new String[] { "Bạn chưa đăng kí đủ tín chỉ ở", knowledgePart.getName() },
                        Setting.WARNING);
                return false;
            }
            // FINISH Check each list optional subjects
        }
        return true;
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

    // Get list subjects selecting and has unique codes
    public List<Subject> getListSubjectSelected() {
        List<Subject> ans = new LinkedList<Subject>();
        List<Subject> subjects = major.getSubjects();
        for (int count = 0; count < subjects.size(); count++) {
            if (isSelectedSubject[count]) {
                boolean isExistedInList = false;
                // CHeck if this subject is existed in 'ans'
                for (Subject subject : ans) {
                    if (subject.getCode().equals(subjects.get(count).getCode())) {
                        isExistedInList = true;
                    }
                }
                if (!isExistedInList) {
                    ans.add(subjects.get(count));
                }
            }
        }
        return ans;
    }

    // Set selected for all compulsory subject
    public void setSelectedCompulsorySubject(boolean flag) {
        int index = 0;
        for (KnowledgePart knowledgePart : major.getKnowledgeParts()) {
            for (int i = 0; i < knowledgePart.getCompulsorySubjectsByList().size(); i++) {
                isSelectedSubject[index] = flag;
                index++;
            }

            for (int count = 0; count < knowledgePart.getNumberOfOptionalSubjectsList(); count++) {
                // Get list subjects of this optional
                List<Subject> optionalSubjectList = knowledgePart.getOptionalSubjects().get(count);
                index += optionalSubjectList.size();
            }

            for (int count = 0; count < knowledgePart.getNumberOfOptionalSubjectsANDList(); count++) {
                // Get list subjects of this optional
                List<Subject> optionalSubjectList = knowledgePart.getOptionalSubjectsAND().get(count);
                index += optionalSubjectList.size();
            }
        }
        updateBackgroundSelectedPanels();
    }

    // update background color for selected panels
    public void updateBackgroundSelectedPanels() {
        for (int count = 0; count < panelSubjects.length; count++) {
            if (isSelectedSubject[count]) {
                panelSubjects[count].setBackgroundColorPanelSubject(COLOR_SUBJECT_PRESSED);
            } else {
                if (count % 2 == 0) {
                    panelSubjects[count].setBackgroundColorPanelSubject(COLOR_SUBJECT_EXITED_1);
                } else {
                    panelSubjects[count].setBackgroundColorPanelSubject(COLOR_SUBJECT_EXITED_2);
                }
            }
        }
        repaint();
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
            for (int count = 0; count < panelSubjects.length; count++) {
                if (e.getSource() == panelSubjects[count]) {
                    isSelectedSubject[count] = !isSelectedSubject[count];
                    if (isSelectedSubject[count]) {
                        panelSubjects[count].setBackgroundColorPanelSubject(COLOR_SUBJECT_PRESSED);
                    } else {
                        if (count % 2 == 0) {
                            panelSubjects[count].setBackgroundColorPanelSubject(COLOR_SUBJECT_EXITED_1);
                        } else {
                            panelSubjects[count].setBackgroundColorPanelSubject(COLOR_SUBJECT_EXITED_2);
                        }
                    }
                }
            }
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            for (int count = 0; count < panelSubjects.length; count++) {
                if (e.getSource() == panelSubjects[count]) {
                    if (!isSelectedSubject[count]) {
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
                    if (!isSelectedSubject[count]) {
                        if (count % 2 == 0) {
                            panelSubjects[count].setBackgroundColorPanelSubject(COLOR_SUBJECT_EXITED_1);
                        } else {
                            panelSubjects[count].setBackgroundColorPanelSubject(COLOR_SUBJECT_EXITED_2);
                        }
                    }
                }
            }
            repaint();
        }
    }
}
