package src.panel;

import javax.swing.JPanel;

import src.Setting;
import src.dialog.DialogCreateNewSubject;
import src.file_handler.WriteFile;
import src.objects.Button;
import src.objects.Plan;
import src.objects.Subject;

import java.awt.event.MouseWheelListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.MouseWheelEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelSubjectList2 extends JPanel {
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
        public static final Color COLOR_STROKE = Setting.COLOR_BLACK;

        // Properties
        private int width, height; // size of this panel
        private int xPos, yPos, rootLocationType; // location of top-left point
        private Plan plan; // data plan
        private JPanel headerPanel; // contains title
        private JPanel contentPanel; // a part of scrollPanel wil be shown here
        private JPanel scrollPanel; // Contains all information of plan
        private int cursorScroll = 0; // to define where the contentPanel in scrolllPanel
        private List<Button> buttonSubjects;
        private List<Button> buttonCountTimeLessons;
        private List<Button> buttonSubjectEnable;
        private Button buttonAddNewSubject;
        private int indexPlan;
        private int indexPressing = -1;
        private PanelTimeTable parentPanel = null;

        // Constructor
        public PanelSubjectList2(int x, int y, int width, int height, Plan plan, int rootLocationType, int indexPlan,
                        PanelTimeTable parentPanel) {
                // Properties, Objects
                this.indexPlan = indexPlan;
                this.width = width;
                this.height = height;
                this.plan = plan;
                this.rootLocationType = rootLocationType;
                this.buttonSubjects = new LinkedList<>();
                this.buttonCountTimeLessons = new LinkedList<>();
                this.buttonSubjectEnable = new LinkedList<>();
                this.parentPanel = parentPanel;
                setLayout(null);
                setSize(width, height);
                setLocation(x, y, rootLocationType);

                // Create panels
                headerPanel = new JPanel();
                headerPanel.setLayout(null);
                headerPanel.setSize(width, height / 12);
                headerPanel.setBounds(0, 0, headerPanel.getWidth(), headerPanel.getHeight());

                contentPanel = new JPanel();
                contentPanel.setLayout(null);
                contentPanel.setSize(headerPanel.getWidth(), height - headerPanel.getHeight());
                contentPanel.setBounds(0, headerPanel.getHeight(), contentPanel.getWidth(),
                                contentPanel.getHeight());

                // Create titles for headerPanel
                Button title = new Button("Các môn học");
                title.setFontText(new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_MEDIUM));
                title.setEnable(false);
                title.setLocationText(0, 0);
                title.setStrokeWidth(0);
                title.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_4, Setting.GRADIENT_POINTS2_4,
                                Setting.GRADIENT_COLORS_4);
                title.setSizeButton(headerPanel.getWidth() / 2, headerPanel.getHeight() / 10 * 8);
                title.setBounds(0, 0, title.getWidth(), title.getHeight());

                // Create scrollPanel
                scrollPanel = new JPanel();
                int heightScroll = 10;
                buttonAddNewSubject = new Button("+");
                buttonAddNewSubject.setShapeType(1);
                buttonAddNewSubject.addMouseListener(new MouseHandler());
                buttonAddNewSubject.setFontText(
                                new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_BIG));
                buttonAddNewSubject.setLocationText(0, 0);
                buttonAddNewSubject.setStrokeWidth(0);
                buttonAddNewSubject.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_5, Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);
                buttonAddNewSubject.setGradientBackgroundColorExited(Setting.GRADIENT_POINTS1_5,
                                Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);
                buttonAddNewSubject.setGradientBackgroundColorEntered(Setting.GRADIENT_POINTS1_8,
                                Setting.GRADIENT_POINTS2_8,
                                Setting.GRADIENT_COLORS_8);
                buttonAddNewSubject.setSizeButton((int) (width / 7.0 * 4 * 3 / 5), buttonAddNewSubject.getHeight());
                buttonAddNewSubject.setSizeButton(buttonAddNewSubject.getWidth(),
                                (int) (buttonAddNewSubject.getWidth() * Math.sqrt(3) / 2));
                buttonAddNewSubject.setLocationButton(width / 2, heightScroll, Button.TOP_CENTER);
                buttonAddNewSubject.setToolTipText("Thêm môn học");
                scrollPanel.add(buttonAddNewSubject);
                heightScroll += buttonAddNewSubject.getHeight() + 20;

                int countSubject = 0;

                // START Create panel
                for (Subject subject : this.plan.getTimeTable().getSubjects()) {
                        Button buttonSubject = new Button(subject.getCode());
                        buttonSubject.setShapeType(1);
                        buttonSubject.addMouseListener(new MouseHandler());
                        Button buttonCountTimeLesson = new Button(subject.getListTimes().size() + "");
                        buttonCountTimeLesson.setShapeType(1);
                        buttonCountTimeLesson.addMouseListener(new MouseHandler());
                        Button buttonEnable = new Button("");
                        buttonEnable.setShapeType(1);
                        buttonEnable.addMouseListener(new MouseHandler());

                        buttonSubject.setFontText(
                                        new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01,
                                                        Setting.FONT_SIZE_VERY_SMALL));
                        buttonSubject.setLocationText(0, 0);
                        buttonSubject.setStrokeWidth(0);
                        buttonSubject.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_5, Setting.GRADIENT_POINTS2_5,
                                        Setting.GRADIENT_COLORS_5);
                        buttonSubject.setGradientBackgroundColorExited(Setting.GRADIENT_POINTS1_5,
                                        Setting.GRADIENT_POINTS2_5,
                                        Setting.GRADIENT_COLORS_5);
                        buttonSubject.setGradientBackgroundColorEntered(Setting.GRADIENT_POINTS1_8,
                                        Setting.GRADIENT_POINTS2_8,
                                        Setting.GRADIENT_COLORS_8);
                        buttonSubject.setSizeButton((int) (width / 7.0 * 4 * 3 / 5), buttonSubject.getHeight());
                        buttonSubject.setSizeButton(buttonSubject.getWidth(),
                                        (int) (buttonSubject.getWidth() * Math.sqrt(3) / 2));
                        buttonSubject.setBounds(width / 7, heightScroll, buttonSubject.getWidth(),
                                        buttonSubject.getHeight());
                        buttonSubject.setToolTipText(String.format("%s - %s",
                                        subject.getCode(), subject.getName()));

                        buttonCountTimeLesson
                                        .setFontText(new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01,
                                                        Setting.FONT_SIZE_VERY_SMALL));
                        buttonCountTimeLesson.setLocationText(0, 0);
                        buttonCountTimeLesson.setStrokeWidth(0);
                        buttonCountTimeLesson.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_5,
                                        Setting.GRADIENT_POINTS2_5,
                                        Setting.GRADIENT_COLORS_5);
                        buttonCountTimeLesson.setGradientBackgroundColorExited(Setting.GRADIENT_POINTS1_5,
                                        Setting.GRADIENT_POINTS2_5,
                                        Setting.GRADIENT_COLORS_5);
                        buttonCountTimeLesson.setGradientBackgroundColorEntered(Setting.GRADIENT_POINTS1_8,
                                        Setting.GRADIENT_POINTS2_8,
                                        Setting.GRADIENT_COLORS_8);
                        buttonCountTimeLesson.setSizeButton(buttonCountTimeLesson.getHeight(),
                                        buttonCountTimeLesson.getHeight());
                        buttonCountTimeLesson.setSizeButton(buttonCountTimeLesson.getWidth(),
                                        (int) (buttonCountTimeLesson.getWidth() * Math.sqrt(3) / 2));
                        buttonCountTimeLesson.setLocationButton(
                                        buttonSubject.getX() + (int) (buttonCountTimeLesson.getWidth() / 4.0),
                                        heightScroll,
                                        Button.TOP_RIGHT);
                        buttonCountTimeLesson.setToolTipText(String.format("Số mã lớp: %d",
                                        subject.getListTimes().size()));

                        buttonEnable
                                        .setFontText(new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01,
                                                        Setting.FONT_SIZE_SMALL));
                        buttonEnable.setLocationText(0, 0);
                        buttonEnable.setStrokeWidth(0);
                        buttonEnable.setBackgroundColorExitedButton(
                                        subject.getEnable() ? Setting.COLOR_GREEN_03 : Setting.COLOR_RED_08);
                        buttonEnable.setBackgroundColorButton(
                                        subject.getEnable() ? Setting.COLOR_GREEN_03 : Setting.COLOR_RED_08);
                        buttonEnable.setBackgroundColorEnteredButton(
                                        subject.getEnable() ? Setting.COLOR_GREEN_03 : Setting.COLOR_RED_08);
                        buttonEnable.setSizeButton(buttonEnable.getHeight(),
                                        buttonEnable.getHeight());
                        buttonEnable.setSizeButton(buttonEnable.getWidth(),
                                        (int) (buttonEnable.getWidth() * Math.sqrt(3) / 2));
                        buttonEnable.setLocationButton(
                                        buttonSubject.getX() + (int) (buttonEnable.getWidth() / 4.0),
                                        heightScroll + buttonSubject.getHeight(),
                                        Button.BOTTOM_RIGHT);
                        buttonEnable.setToolTipText(subject.getEnable() ? "Đang sử dụng" : "Đang không sử dụng");

                        if (countSubject % 2 == 0) {
                                buttonSubject.setLocationButton(
                                                (int) (width / 7 + buttonSubject.getWidth() * 3.0 / 4
                                                                + buttonSubject.getHeight() / 4.0 * Math.sqrt(3)),
                                                heightScroll,
                                                Button.TOP_LEFT);
                                buttonCountTimeLesson.setLocationButton(
                                                buttonSubject.getX() + buttonSubject.getWidth()
                                                                - (int) (buttonCountTimeLesson.getWidth() / 4.0),
                                                heightScroll,
                                                Button.TOP_LEFT);
                                buttonEnable.setLocationButton(
                                                buttonSubject.getX() + buttonSubject.getWidth()
                                                                - (int) (buttonEnable.getWidth() / 4.0),
                                                heightScroll + buttonSubject.getHeight(),
                                                Button.BOTTOM_LEFT);
                        }

                        buttonSubjects.add(buttonSubject);
                        buttonCountTimeLessons.add(buttonCountTimeLesson);
                        buttonSubjectEnable.add(buttonEnable);
                        scrollPanel.add(buttonSubject);
                        scrollPanel.add(buttonCountTimeLesson);
                        scrollPanel.add(buttonEnable);
                        heightScroll += (int) (buttonSubject.getHeight() * 3.0 / 4);
                        countSubject++;
                }

                scrollPanel.setLayout(null);
                scrollPanel.setSize(contentPanel.getWidth(), Math.max(heightScroll + 100, contentPanel.getHeight()));
                updateContentShowing();

                // Add sub panels to this panel
                add(headerPanel);
                add(contentPanel);
                headerPanel.add(title);
                contentPanel.add(scrollPanel);
                scrollPanel.setBackground(Setting.COLOR_ORANGE_02);

                // Add MouseWheelListener to this panel
                addMouseWheelListener(new MouseWheelHandler());

        }

        // Update data
        public void updateDataContent() {
                // Create scrollPanel
                scrollPanel.removeAll();
                int heightScroll = 10;
                scrollPanel.add(buttonAddNewSubject);
                heightScroll += buttonAddNewSubject.getHeight() + 20;

                int countSubject = 0;
                buttonSubjects.clear();
                buttonCountTimeLessons.clear();
                buttonSubjectEnable.clear();

                // START Create panel
                for (Subject subject : this.plan.getTimeTable().getSubjects()) {
                        Button buttonSubject = new Button(subject.getCode());
                        buttonSubject.setShapeType(1);
                        buttonSubject.addMouseListener(new MouseHandler());
                        Button buttonCountTimeLesson = new Button(subject.getListTimes().size() + "");
                        buttonCountTimeLesson.setShapeType(1);
                        buttonCountTimeLesson.addMouseListener(new MouseHandler());
                        Button buttonEnable = new Button("");
                        buttonEnable.setShapeType(1);
                        buttonEnable.addMouseListener(new MouseHandler());

                        buttonSubject.setFontText(
                                        new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01,
                                                        Setting.FONT_SIZE_VERY_SMALL));
                        buttonSubject.setLocationText(0, 0);
                        buttonSubject.setStrokeWidth(0);
                        buttonSubject.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_5, Setting.GRADIENT_POINTS2_5,
                                        Setting.GRADIENT_COLORS_5);
                        buttonSubject.setGradientBackgroundColorExited(Setting.GRADIENT_POINTS1_5,
                                        Setting.GRADIENT_POINTS2_5,
                                        Setting.GRADIENT_COLORS_5);
                        buttonSubject.setGradientBackgroundColorEntered(Setting.GRADIENT_POINTS1_8,
                                        Setting.GRADIENT_POINTS2_8,
                                        Setting.GRADIENT_COLORS_8);
                        buttonSubject.setSizeButton((int) (width / 7.0 * 4 * 3 / 5), buttonSubject.getHeight());
                        buttonSubject.setSizeButton(buttonSubject.getWidth(),
                                        (int) (buttonSubject.getWidth() * Math.sqrt(3) / 2));
                        buttonSubject.setBounds(width / 7, heightScroll, buttonSubject.getWidth(),
                                        buttonSubject.getHeight());
                        buttonSubject.setToolTipText(String.format("%s - %s",
                                        subject.getCode(), subject.getName()));

                        buttonCountTimeLesson
                                        .setFontText(new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01,
                                                        Setting.FONT_SIZE_VERY_SMALL));
                        buttonCountTimeLesson.setLocationText(0, 0);
                        buttonCountTimeLesson.setStrokeWidth(0);
                        buttonCountTimeLesson.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_5,
                                        Setting.GRADIENT_POINTS2_5,
                                        Setting.GRADIENT_COLORS_5);
                        buttonCountTimeLesson.setGradientBackgroundColorExited(Setting.GRADIENT_POINTS1_5,
                                        Setting.GRADIENT_POINTS2_5,
                                        Setting.GRADIENT_COLORS_5);
                        buttonCountTimeLesson.setGradientBackgroundColorEntered(Setting.GRADIENT_POINTS1_8,
                                        Setting.GRADIENT_POINTS2_8,
                                        Setting.GRADIENT_COLORS_8);
                        buttonCountTimeLesson.setSizeButton(buttonCountTimeLesson.getHeight(),
                                        buttonCountTimeLesson.getHeight());
                        buttonCountTimeLesson.setSizeButton(buttonCountTimeLesson.getWidth(),
                                        (int) (buttonCountTimeLesson.getWidth() * Math.sqrt(3) / 2));
                        buttonCountTimeLesson.setLocationButton(
                                        buttonSubject.getX() + (int) (buttonCountTimeLesson.getWidth() / 4.0),
                                        heightScroll,
                                        Button.TOP_RIGHT);
                        buttonCountTimeLesson.setToolTipText(String.format("Số mã lớp: %d",
                                        subject.getListTimes().size()));

                        buttonEnable
                                        .setFontText(new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01,
                                                        Setting.FONT_SIZE_SMALL));
                        buttonEnable.setLocationText(0, 0);
                        buttonEnable.setStrokeWidth(0);
                        buttonEnable.setBackgroundColorExitedButton(
                                        subject.getEnable() ? Setting.COLOR_GREEN_03 : Setting.COLOR_RED_08);
                        buttonEnable.setBackgroundColorButton(
                                        subject.getEnable() ? Setting.COLOR_GREEN_03 : Setting.COLOR_RED_08);
                        buttonEnable.setBackgroundColorEnteredButton(
                                        subject.getEnable() ? Setting.COLOR_GREEN_03 : Setting.COLOR_RED_08);
                        buttonEnable.setSizeButton(buttonEnable.getHeight(),
                                        buttonEnable.getHeight());
                        buttonEnable.setSizeButton(buttonEnable.getWidth(),
                                        (int) (buttonEnable.getWidth() * Math.sqrt(3) / 2));
                        buttonEnable.setLocationButton(
                                        buttonSubject.getX() + (int) (buttonEnable.getWidth() / 4.0),
                                        heightScroll + buttonSubject.getHeight(),
                                        Button.BOTTOM_RIGHT);
                        buttonEnable.setToolTipText(subject.getEnable() ? "Đang sử dụng" : "Đang không sử dụng");

                        if (countSubject % 2 == 0) {
                                buttonSubject.setLocationButton(
                                                (int) (width / 7 + buttonSubject.getWidth() * 3.0 / 4
                                                                + buttonSubject.getHeight() / 4.0 * Math.sqrt(3)),
                                                heightScroll,
                                                Button.TOP_LEFT);
                                buttonCountTimeLesson.setLocationButton(
                                                buttonSubject.getX() + buttonSubject.getWidth()
                                                                - (int) (buttonCountTimeLesson.getWidth() / 4.0),
                                                heightScroll,
                                                Button.TOP_LEFT);
                                buttonEnable.setLocationButton(
                                                buttonSubject.getX() + buttonSubject.getWidth()
                                                                - (int) (buttonEnable.getWidth() / 4.0),
                                                heightScroll + buttonSubject.getHeight(),
                                                Button.BOTTOM_LEFT);
                        }

                        buttonSubjects.add(buttonSubject);
                        buttonCountTimeLessons.add(buttonCountTimeLesson);
                        buttonSubjectEnable.add(buttonEnable);
                        scrollPanel.add(buttonSubject);
                        scrollPanel.add(buttonCountTimeLesson);
                        scrollPanel.add(buttonEnable);
                        heightScroll += (int) (buttonSubject.getHeight() * 3.0 / 4);
                        countSubject++;
                }

                scrollPanel.setLayout(null);
                scrollPanel.setSize(contentPanel.getWidth(), Math.max(heightScroll + 100, contentPanel.getHeight()));
                setCurscorScroll(0);
                updateContentShowing();
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

        // Get index subject pressing
        public int getIndexPressing() {
                return this.indexPressing;
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

        // Set index pressing
        public void setIndexPressing(int index) {
                this.indexPressing = index;
                parentPanel.updateContent();
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
                public void mouseClicked(MouseEvent event) {
                }

                @Override
                public void mousePressed(MouseEvent event) {
                        if (event.getSource() == buttonAddNewSubject) {
                                new DialogCreateNewSubject(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3,
                                                Setting.HEIGHT / 3,
                                                DialogCreateNewSubject.CENTER_CENTER, "Add new subject",
                                                new String[] { "Nhập mã môn học và tên môn học mà bạn muốn thêm:" },
                                                plan,
                                                indexPlan);
                                updateDataContent();
                        } else {
                                for (int index = 0; index < buttonSubjects.size(); index++) {
                                        Subject subject = plan.getTimeTable().getSubjects().get(index);
                                        if (event.getSource() == buttonSubjectEnable.get(index)) {
                                                subject.setEnable(!subject.getEnable());
                                                WriteFile.editSubjectTimeTable(indexPlan, index, subject);
                                                // setIndexPressing(index);
                                                updateDataContent();
                                        } else if (event.getSource() == buttonSubjects.get(index)) {
                                                setIndexPressing(index);
                                                updateDataContent();
                                        }
                                }
                        }
                }

                @Override
                public void mouseReleased(MouseEvent event) {

                }

                @Override
                public void mouseEntered(MouseEvent event) {
                        scrollPanel.repaint();
                }

                @Override
                public void mouseExited(MouseEvent event) {
                        scrollPanel.repaint();
                }
        }
}
