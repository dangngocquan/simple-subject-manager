package code.panel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.MouseWheelListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.MouseWheelEvent;

import code.Application;
import code.Setting;
import code.objects.Button;
import code.objects.KnowledgePart;
import code.objects.Major;
import code.objects.Subject;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
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
    private Application applicationFrame;
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
        this.applicationFrame = frame;
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
        Button titleOrder = new Button("STT        ");
        titleOrder.setFont(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_MEDIUM);
        titleOrder.setBorderPainted(false);
        titleOrder.setSizeButton(headerPanel.getWidth() / 12, headerPanel.getHeight(), false);
        titleOrder.setBounds(0, 0, titleOrder.getWidth(), titleOrder.getHeight());

        Button titleCode = new Button("Mã          ");
        titleCode.setFont(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_MEDIUM);
        titleCode.setBorderPainted(false);
        titleCode.setSizeButton(headerPanel.getWidth() / 12, headerPanel.getHeight(), false);
        titleCode.setBounds(titleOrder.getWidth(), 0, titleCode.getWidth(), titleCode.getHeight());

        Button titleName = new Button(
                "Tên môn học                                                                            ");
        titleName.setFont(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_MEDIUM);
        titleName.setBorderPainted(false);
        titleName.setSizeButton(headerPanel.getWidth() / 12 * 5, headerPanel.getHeight(), false);
        titleName.setBounds(titleOrder.getWidth() + titleCode.getWidth(), 0, titleName.getWidth(),
                titleName.getHeight());

        Button titleCredits = new Button("Số tín     ");
        titleCredits.setFont(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_MEDIUM);
        titleCredits.setBorderPainted(false);
        titleCredits.setSizeButton(headerPanel.getWidth() / 12, headerPanel.getHeight(), false);
        titleCredits.setBounds(titleOrder.getWidth() + titleCode.getWidth() + titleName.getWidth(), 0,
                titleCredits.getWidth(), titleCredits.getHeight());

        Button titleParentCodes = new Button(
                "Mã học phần tiên quyết                                                                ");
        titleParentCodes.setFont(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_MEDIUM);
        titleParentCodes.setBorderPainted(false);
        titleParentCodes.setSizeButton(headerPanel.getWidth() / 12 * 4, headerPanel.getHeight(), false);
        titleParentCodes.setBounds(
                titleOrder.getWidth() + titleCode.getWidth() + titleName.getWidth() + titleCredits.getWidth(), 0,
                headerPanel.getWidth() - titleCode.getWidth() - titleName.getWidth() - titleCredits.getWidth(),
                titleParentCodes.getHeight());
        // Create scrollPanel
        scrollPanel = new JPanel();
        int heightScroll = 0;
        int countSubjects = 0;
        for (KnowledgePart knowledgePart : major.getKnowledgeParts()) {
            // START Create panel for knowledgePart

            // START Create panel for knowledge name
            PanelString knowledgeNamePanel = new PanelString(0, heightScroll, knowledgePart.getName(), width, null,
                    PanelString.TOP_LEFT, 0);
            knowledgeNamePanel.setBackground(COLOR_BACKGROUND_KNOWLEDGE_NAME);
            scrollPanel.add(knowledgeNamePanel);
            heightScroll += knowledgeNamePanel.getHeight();
            // FINISH Create panel for knowledge name

            // START Create panel for description of Compulsory subjects
            if (!knowledgePart.getDescriptionCompulsory().isEmpty()) {
                String str = knowledgePart.getDescriptionCompulsory() + " ("
                        + knowledgePart.getMinCreditsCompulsorySubjects() + " tín chỉ)";
                PanelString desCompulsoryPanel = new PanelString(0, heightScroll,
                        str, width,
                        new Font(Setting.FONT_NAME_01,
                                Setting.FONT_STYLE_03,
                                Setting.FONT_SIZE_SMALL),
                        PanelString.TOP_LEFT, 0);
                desCompulsoryPanel.setBackground(COLOR_BACKGROUND_DESCRIPTION);
                scrollPanel.add(desCompulsoryPanel);
                heightScroll += desCompulsoryPanel.getHeight();
            }
            // FINISH Create panel for description of Compulsory subjects

            // START Create panel for compulsory subjects (if have)
            for (Subject subject : knowledgePart.getCompulsorySubjects()) {
                PanelSubject panelSubject = new PanelSubject(0, heightScroll, subject, width,
                        null, countSubjects + 1);
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
            // FINISH Create panel for compulsory subjects (if have)

            // START Create panel for main description of optional subjects
            if (!knowledgePart.getMainDescriptionOptionalSubjects().isEmpty()) {
                String str = knowledgePart.getMainDescriptionOptionalSubjects();
                PanelString desMainOptionalSubjectPanel = new PanelString(0, heightScroll,
                        str, width,
                        new Font(Setting.FONT_NAME_01,
                                Setting.FONT_STYLE_03,
                                Setting.FONT_SIZE_SMALL),
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
                        + knowledgePart.getMinCreditsOptionalSubjects() + " tín chỉ)";
                // START Create panel for description
                PanelString desOptionalPanel = new PanelString(0, heightScroll,
                        optionalDescription, width,
                        new Font(Setting.FONT_NAME_01,
                                Setting.FONT_STYLE_03,
                                Setting.FONT_SIZE_SMALL),
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
                            null, countSubjects + 1);
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

    // Check if the plan has enought credit
    public boolean isValidPlan() {
        // Check each knowledgePart
        int indexSubject = 0;
        for (KnowledgePart knowledgePart : major.getKnowledgeParts()) {
            int tempCredits = 0;
            // START Check compulsory subjects (if have)
            for (Subject subject : knowledgePart.getCompulsorySubjects()) {
                if (isSelectedSubject[indexSubject]) {
                    tempCredits += subject.getNumberCredits();
                }
                indexSubject++;
            }
            if (tempCredits < knowledgePart.getMinCreditsCompulsorySubjects()) {
                JOptionPane.showMessageDialog(applicationFrame,
                        "Bạn chưa đăng kí đủ tín chỉ ở\n" + knowledgePart.getName(),
                        "Not enough credits",
                        JOptionPane.WARNING_MESSAGE);
                return false;
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
                if (tempCredits >= knowledgePart.getMinCreditsOptionalSubjects()) {
                    isEnough = true;
                }
                // FINISH Check a list optional subjects
            }

            if (!isEnough) {
                JOptionPane.showMessageDialog(applicationFrame,
                        "Bạn chưa đăng kí đủ tín chỉ ở\n" + knowledgePart.getName(),
                        "Not enough credits",
                        JOptionPane.WARNING_MESSAGE);
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

    // Get list subjects selecting
    public List<Subject> getListSubjectSelected() {
        List<Subject> ans = new LinkedList<Subject>();
        List<Subject> subjects = major.getSubjects();
        for (int count = 0; count < subjects.size(); count++) {
            if (isSelectedSubject[count]) {
                ans.add(subjects.get(count));
            }
        }
        return ans;
    }

    // Set selected for all compulsory subject
    public void setSelectedCompulsorySubject(boolean flag) {
        int index = 0;
        for (KnowledgePart knowledgePart : major.getKnowledgeParts()) {
            for (int i = 0; i < knowledgePart.getCompulsorySubjects().size(); i++) {
                isSelectedSubject[index] = flag;
                index++;
            }

            for (int count = 0; count < knowledgePart.getNumberOfOptionalSubjectsList(); count++) {
                // Get list subjects of this optional
                List<Subject> optionalSubjectList = knowledgePart.getOptionalSubjects().get(count);
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
