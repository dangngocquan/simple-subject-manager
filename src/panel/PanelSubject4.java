package src.panel;

import javax.swing.JPanel;

import src.Setting;
import src.animation.AnimationPanel;
import src.dialog.DialogInput;
import src.dialog.DialogMessage;
import src.file_handler.WriteFile;
import src.objects.Button;
import src.objects.Plan;
import src.objects.Subject;

import java.util.LinkedList;
import java.util.List;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class PanelSubject4 extends JPanel {
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

        public static final Color COLOR_SUBJECT_1 = Setting.COLOR_GRAY_05;
        public static final Color COLOR_SUBJECT_2 = Setting.COLOR_GRAY_04;

        public static final Color COLOR_SUBJECT_ENTERED = Setting.COLOR_VIOLET_03;
        public static final Color COLOR_SUBJECT_EXITED_1 = Setting.COLOR_GRAY_05;
        public static final Color COLOR_SUBJECT_EXITED_2 = Setting.COLOR_GRAY_04;
        public static final Color COLOR_SUBJECT_PRESSED = Setting.COLOR_GREEN_03;
        public static final Color COLOR_STROKE = Setting.COLOR_BLACK;

        // Properties
        private PanelTimeTable parentPanel = null;
        private int width, height; // size of this panel
        private int xPos, yPos, rootLocationType; // location of top-left point
        private Plan plan; // data plan
        private PanelSubject2[] panelSubjects = null;
        private int indexPlan;
        private int indexSubject;
        private Button panelMainSubject;
        private Button buttonUp;
        private Button buttonRemove;
        private Button buttonDown;
        private Button panelSubjectCode;
        private Button panelSubjectName;
        private int indexTimeLessonStart = 0;
        private List<Button> panelTimeNames = null;

        // Constructor
        public PanelSubject4(int x, int y, int width, int height, Plan plan, int indexPlan, int indexSubject,
                        int rootLocationType, PanelTimeTable parentPanel) {
                // Properties, Objects
                this.parentPanel = parentPanel;
                this.width = width;
                this.height = height;
                this.plan = plan;
                this.indexPlan = indexPlan;
                this.indexSubject = indexSubject;
                this.rootLocationType = rootLocationType;
                setLayout(null);
                setSize(width, height);
                setLocation(x, y, rootLocationType);

                // Create panels
                if (indexSubject < 0 || indexSubject >= plan.getTimeTable().getSubjects().size()) {
                        // Panel main subject
                        panelMainSubject = new Button("~");
                        panelMainSubject.setShapeType(1);
                        panelMainSubject.addMouseListener(new MouseHandler());
                        panelMainSubject.setFontText(
                                        new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_SMALL));
                        panelMainSubject.setLocationText(0, 0);
                        panelMainSubject.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_5,
                                        Setting.GRADIENT_POINTS2_5,
                                        Setting.GRADIENT_COLORS_5);
                        panelMainSubject.setGradientBackgroundColorExited(Setting.GRADIENT_POINTS1_5,
                                        Setting.GRADIENT_POINTS2_5,
                                        Setting.GRADIENT_COLORS_5);
                        panelMainSubject.setGradientBackgroundColorEntered(Setting.GRADIENT_POINTS1_5,
                                        Setting.GRADIENT_POINTS2_5,
                                        Setting.GRADIENT_COLORS_5);
                        panelMainSubject.setSizeButton((int) (width / 7.0 * 4 * 3 / 5), panelMainSubject.getHeight());
                        panelMainSubject.setSizeButton(panelMainSubject.getWidth(),
                                        (int) (panelMainSubject.getWidth() * Math.sqrt(3) / 2));
                        panelMainSubject.setLocationButton(width / 2, height / 2, Button.CENTER_CENTER);
                } else {
                        updateContentData();
                }

                // Add panel
                add(panelMainSubject);

        }

        // Update contentPanel
        public void updateContentShowing() {
                // Set visible to false
                for (Button panelTimeName : panelTimeNames) {
                        panelTimeName.setVisible(false);
                }

                // Set location and shape of some panelTimeName
                for (int index = indexTimeLessonStart; index < indexTimeLessonStart + 8; index++) {
                        if (index >= 0 && index < panelTimeNames.size()) {
                                Button panel = panelTimeNames.get(index);
                                panel.setVisible(true);
                                switch (index - indexTimeLessonStart) {
                                        case 0:
                                                panel.setShapeType(Button.RECT_SLANTED_RIGHT);
                                                panel.setLocationButton(panelMainSubject.getX() + panel.getHeight() - 6,
                                                                panelMainSubject.getY(), Button.TOP_RIGHT);
                                                panel.setLocationText(-panel.getHeight(), 0);
                                                break;
                                        case 1:
                                                panel.setShapeType(Button.RECT_SLANTED_LEFT);
                                                panel.setLocationButton(
                                                                panelMainSubject.getX() + panelMainSubject.getWidth()
                                                                                - panel.getHeight() + 6,
                                                                panelMainSubject.getY(), Button.TOP_LEFT);
                                                panel.setLocationText(panel.getHeight(), 0);
                                                break;
                                        case 2:
                                                panel.setShapeType(Button.RECT_SLANTED_RIGHT);
                                                panel.setLocationButton(panelMainSubject.getX(),
                                                                panelMainSubject.getY()
                                                                                + panelMainSubject.getHeight() / 4,
                                                                Button.TOP_RIGHT);
                                                panel.setLocationText(-panel.getHeight(), 0);
                                                break;
                                        case 3:
                                                panel.setShapeType(Button.RECT_SLANTED_LEFT);
                                                panel.setLocationButton(
                                                                panelMainSubject.getX() + panelMainSubject.getWidth(),
                                                                panelMainSubject.getY()
                                                                                + panelMainSubject.getHeight() / 4,
                                                                Button.TOP_LEFT);
                                                panel.setLocationText(panel.getHeight(), 0);
                                                break;
                                        case 4:
                                                panel.setShapeType(Button.RECT_SLANTED_LEFT);
                                                panel.setLocationButton(panelMainSubject.getX(),
                                                                panelMainSubject.getY() + panelMainSubject.getHeight()
                                                                                - panelMainSubject.getHeight() / 4,
                                                                Button.BOTTOM_RIGHT);
                                                panel.setLocationText(-panel.getHeight(), 0);
                                                break;
                                        case 5:
                                                panel.setShapeType(Button.RECT_SLANTED_RIGHT);
                                                panel.setLocationButton(
                                                                panelMainSubject.getX() + panelMainSubject.getWidth(),
                                                                panelMainSubject.getY() + panelMainSubject.getHeight()
                                                                                - panelMainSubject.getHeight() / 4,
                                                                Button.BOTTOM_LEFT);
                                                panel.setLocationText(panel.getHeight(), 0);
                                                break;
                                        case 6:
                                                panel.setShapeType(Button.RECT_SLANTED_LEFT);
                                                panel.setLocationButton(panelMainSubject.getX() + panel.getHeight() - 6,
                                                                panelMainSubject.getY() + panelMainSubject.getHeight(),
                                                                Button.BOTTOM_RIGHT);
                                                panel.setLocationText(-panel.getHeight(), 0);
                                                break;
                                        case 7:
                                                panel.setShapeType(Button.RECT_SLANTED_RIGHT);
                                                panel.setLocationButton(
                                                                panelMainSubject.getX() + panelMainSubject.getWidth()
                                                                                - panel.getHeight() + 6,
                                                                panelMainSubject.getY() + panelMainSubject.getHeight(),
                                                                Button.BOTTOM_LEFT);
                                                panel.setLocationText(panel.getHeight(), 0);
                                                break;

                                }

                                if (panel.getX() < 0 || (panel.getX() + panel.getWidth() > this.width)) {
                                        panel.setSizeButton(panel.getWidth() - 50, panel.getHeight());
                                        index--;
                                }
                        }
                }

                for (int index = indexTimeLessonStart; index < indexTimeLessonStart + 8; index++) {
                        if (index >= 0 && index < this.panelTimeNames.size()) {
                                Button panel = this.panelTimeNames.get(index);
                                int index1 = (index - indexTimeLessonStart);
                                if (index1 % 2 == 0) {
                                        AnimationPanel animation = new AnimationPanel(panel, -panel.getWidth(),
                                                        panel.getY(),
                                                        panel.getX(), panel.getY(),
                                                        300, index1 / 2 * 100);
                                        animation.start();
                                } else {
                                        AnimationPanel animation = new AnimationPanel(panel, this.width, panel.getY(),
                                                        panel.getX(), panel.getY(),
                                                        300, index1 / 2 * 100);
                                        animation.start();
                                }
                        }
                }

                repaint();
        }

        // Update content data
        public void updateContentData() {
                removeAll();
                // Panel main subject
                panelMainSubject = new Button("");
                panelMainSubject.setShapeType(Button.HEXAGON);
                panelMainSubject.addMouseListener(new MouseHandler());
                panelMainSubject.setFontText(
                                new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_SMALL));
                panelMainSubject.setLocationText(0, 0);
                panelMainSubject.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_5,
                                Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);
                panelMainSubject.setGradientBackgroundColorExited(Setting.GRADIENT_POINTS1_5,
                                Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);
                panelMainSubject.setGradientBackgroundColorEntered(Setting.GRADIENT_POINTS1_5,
                                Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);
                panelMainSubject.setSizeButton((int) (width / 7.0 * 4 * 3 / 5), panelMainSubject.getHeight());
                panelMainSubject.setSizeButton(panelMainSubject.getWidth(),
                                (int) (panelMainSubject.getWidth() * Math.sqrt(3) / 2));
                panelMainSubject.setLocationButton(width / 2, height / 2, Button.CENTER_CENTER);

                // Panel button up
                buttonUp = new Button("");
                buttonUp.addMouseListener(new MouseHandler());
                buttonUp.setFontText(
                                new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_BIG));
                buttonUp.setLocationText(0, 0);
                buttonUp.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_5,
                                Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);
                buttonUp.setGradientBackgroundColorExited(Setting.GRADIENT_POINTS1_5,
                                Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);
                buttonUp.setGradientBackgroundColorEntered(Setting.GRADIENT_POINTS1_5,
                                Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);
                buttonUp.setSizeButton(buttonUp.getHeight(), buttonUp.getHeight());
                buttonUp.setBackgroundIcon(Setting.ARROW_UP);
                buttonUp.setLocationButton(panelMainSubject.getWidth() / 2, panelMainSubject.getHeight() / 4,
                                Button.CENTER_CENTER);
                buttonUp.setToolTipText("Các mã lớp trước");

                panelMainSubject.add(buttonUp);

                // Panel button remove subject
                buttonRemove = new Button("");
                buttonRemove.addMouseListener(new MouseHandler());
                buttonRemove.setFontText(
                                new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_BIG));
                buttonRemove.setLocationText(0, 0);
                buttonRemove.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_5,
                                Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);
                buttonRemove.setGradientBackgroundColorExited(Setting.GRADIENT_POINTS1_5,
                                Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);
                buttonRemove.setGradientBackgroundColorEntered(Setting.GRADIENT_POINTS1_5,
                                Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);
                buttonRemove.setSizeButton(buttonRemove.getHeight(), buttonRemove.getHeight());
                buttonRemove.setBackgroundIcon(Setting.REMOVE);
                buttonRemove.setLocationButton(panelMainSubject.getWidth() / 2,
                                panelMainSubject.getHeight() / 2,
                                Button.CENTER_CENTER);
                buttonRemove.setToolTipText("Xóa môn học");

                panelMainSubject.add(buttonRemove);

                // Panel button down
                buttonDown = new Button("");
                buttonDown.addMouseListener(new MouseHandler());
                buttonDown.setFontText(
                                new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_BIG));
                buttonDown.setLocationText(0, 0);
                buttonDown.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_5,
                                Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);
                buttonDown.setGradientBackgroundColorExited(Setting.GRADIENT_POINTS1_5,
                                Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);
                buttonDown.setGradientBackgroundColorEntered(Setting.GRADIENT_POINTS1_5,
                                Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);
                buttonDown.setSizeButton(buttonDown.getHeight(), buttonDown.getHeight());
                buttonDown.setBackgroundIcon(Setting.ARROW_DOWN);
                buttonDown.setLocationButton(panelMainSubject.getWidth() / 2,
                                panelMainSubject.getHeight() / 4 * 3,
                                Button.CENTER_CENTER);
                buttonDown.setToolTipText("Các mã lớp sau");

                panelMainSubject.add(buttonDown);

                // Subject
                Subject subject = plan.getTimeTable().getSubjects().get(indexSubject);

                // Panel subject code
                panelSubjectCode = new Button(subject.getCode());
                panelSubjectCode.setShapeType(Button.RECT_SLANTED_RIGHT);
                panelSubjectCode.addMouseListener(new MouseHandler());
                panelSubjectCode.setFontText(
                                new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_SMALL));
                panelSubjectCode.setLocationText(-panelSubjectCode.getHeight(), 0);
                panelSubjectCode.setStrokeWidth(0);
                panelSubjectCode.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_5,
                                Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);
                panelSubjectCode.setGradientBackgroundColorExited(Setting.GRADIENT_POINTS1_5,
                                Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);
                panelSubjectCode.setGradientBackgroundColorEntered(Setting.GRADIENT_POINTS1_5,
                                Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);

                // Panel subject Name
                panelSubjectName = new Button(subject.getName());
                panelSubjectName.setShapeType(Button.RECT_SLANTED_RIGHT);
                panelSubjectName.addMouseListener(new MouseHandler());
                panelSubjectName.setFontText(
                                new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_SMALL));
                panelSubjectName.setLocationText(panelSubjectName.getHeight(), 0);
                panelSubjectName.setStrokeWidth(0);
                panelSubjectName.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_5,
                                Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);
                panelSubjectName.setGradientBackgroundColorExited(Setting.GRADIENT_POINTS1_5,
                                Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);
                panelSubjectName.setGradientBackgroundColorEntered(Setting.GRADIENT_POINTS1_5,
                                Setting.GRADIENT_POINTS2_5,
                                Setting.GRADIENT_COLORS_5);

                // Set location of panel subject code and name
                while (panelSubjectCode.getWidth() > width / 2) {
                        panelSubjectCode.setSizeButton(panelSubjectCode.getWidth() - 50,
                                        panelSubjectCode.getHeight());

                }
                while (panelSubjectName.getWidth() > width / 2) {
                        panelSubjectName.setSizeButton(panelSubjectName.getWidth() - 50,
                                        panelSubjectName.getHeight());

                }
                int widthCodeAndName = panelSubjectCode.getWidth() + panelSubjectName.getWidth();

                panelSubjectCode.setLocationButton(
                                (width - widthCodeAndName) / 2,
                                panelMainSubject.getY()
                                                - (int) (panelSubjectCode.getHeight() * 3.0 / 4),
                                Button.BOTTOM_LEFT);
                panelSubjectName.setLocationButton(
                                panelSubjectCode.getX() + panelSubjectCode.getWidth()
                                                - (int) (panelSubjectCode.getHeight() / Math.sqrt(3)) + 10,
                                panelSubjectCode.getY() + panelSubjectCode.getHeight(), Button.BOTTOM_LEFT);

                // Animation for subject code and subject name
                AnimationPanel animation = new AnimationPanel(panelSubjectCode, 0, panelSubjectCode.getY(),
                                panelSubjectCode.getX(),
                                panelSubjectCode.getY(),
                                300);
                animation.start();
                animation = new AnimationPanel(panelSubjectName, width, panelSubjectName.getY(),
                                panelSubjectName.getX(),
                                panelSubjectName.getY(),
                                300);
                animation.start();

                // Create panel time names
                panelTimeNames = new LinkedList<>();
                for (String timeName : subject.getListTimeNames()) {
                        Button panelTimeName = new Button(timeName);
                        panelTimeName.addMouseListener(new MouseHandler());
                        panelTimeName.setFontText(
                                        new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01,
                                                        Setting.FONT_SIZE_SMALL));
                        panelTimeName.setLocationText(0, 0);
                        panelTimeName.setStrokeWidth(0);
                        panelTimeName.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_5,
                                        Setting.GRADIENT_POINTS2_5,
                                        Setting.GRADIENT_COLORS_5);
                        panelTimeName.setGradientBackgroundColorExited(Setting.GRADIENT_POINTS1_5,
                                        Setting.GRADIENT_POINTS2_5,
                                        Setting.GRADIENT_COLORS_5);
                        panelTimeName.setGradientBackgroundColorEntered(Setting.GRADIENT_POINTS1_5,
                                        Setting.GRADIENT_POINTS2_5,
                                        Setting.GRADIENT_COLORS_5);
                        panelTimeNames.add(panelTimeName);
                        add(panelTimeName);
                }

                updateContentShowing();

                setIndexTimeLessonStart(0);

                // Animation for panel main subject
                animation = new AnimationPanel(panelMainSubject, panelMainSubject.getX(), height,
                                panelMainSubject.getX(),
                                panelMainSubject.getY(),
                                300);
                animation.start();

                // Add panel
                add(panelSubjectCode);
                add(panelSubjectName);
        }

        // Set indexTimeLessonStart
        public void setIndexTimeLessonStart(int index) {
                if (index >= 0 && index / 8 <= (panelTimeNames.size() + 7) / 8) {
                        this.indexTimeLessonStart = index;
                        updateContentShowing();
                }
                buttonUp.setVisible(true);
                buttonDown.setVisible(true);
                if (this.panelTimeNames.size() <= 8) {
                        buttonUp.setVisible(false);
                        buttonDown.setVisible(false);
                } else if (indexTimeLessonStart == 0) {
                        buttonUp.setVisible(false);
                } else if (indexTimeLessonStart / 8 == (panelTimeNames.size() + 7) / 8 - 1) {
                        buttonDown.setVisible(false);
                }
        }

        // Get rootLocationType
        public int getRootLocationType() {
                return this.rootLocationType;
        }

        // Get plan
        public Plan getplan() {
                return this.plan;
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

        private class MouseHandler implements MouseListener {

                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                        if (e.getSource() == buttonUp) {
                                setIndexTimeLessonStart(indexTimeLessonStart - 8);
                        } else if (e.getSource() == buttonDown) {
                                setIndexTimeLessonStart(indexTimeLessonStart + 8);
                        } else if (e.getSource() == buttonRemove) {
                                parentPanel.removeSubject(indexSubject);
                        } else if (e.getSource() == panelSubjectCode) {
                                DialogInput dialog = new DialogInput(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                                Setting.WIDTH / 3, Setting.HEIGHT / 3, DialogInput.CENTER_CENTER,
                                                "Edit code of subject", new String[] { "Nhập mã môn học mới:" },
                                                Setting.INFORMATION, new String[] { "Mã môn học" });
                                String[] input = dialog.getInputString();
                                if (input[0].isEmpty()) {
                                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                                        Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                                        DialogMessage.CENTER_CENTER, "Information",
                                                        new String[] { "Sửa mã môn học thất bại" }, Setting.WARNING);
                                } else {
                                        plan.getTimeTable().getSubjects().get(indexSubject).setCode(input[0]);
                                        WriteFile.editSubjectTimeTable(indexPlan, indexSubject,
                                                        plan.getTimeTable().getSubjects().get(indexSubject));
                                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                                        Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                                        DialogMessage.CENTER_CENTER, "Information",
                                                        new String[] { "Sửa mã môn học thành công" },
                                                        Setting.INFORMATION);
                                        parentPanel.updateDataSubjectList();
                                        parentPanel.updateContent();
                                }

                        } else if (e.getSource() == panelSubjectName) {
                                DialogInput dialog = new DialogInput(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                                Setting.WIDTH / 3, Setting.HEIGHT / 3, DialogInput.CENTER_CENTER,
                                                "Edit name of subject", new String[] { "Nhập tên môn học mới:" },
                                                Setting.INFORMATION, new String[] { "Tên môn học" });
                                String[] input = dialog.getInputString();
                                if (input[0].isEmpty()) {
                                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                                        Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                                        DialogMessage.CENTER_CENTER, "Information",
                                                        new String[] { "Sửa tên môn học thất bại" }, Setting.WARNING);
                                } else {
                                        plan.getTimeTable().getSubjects().get(indexSubject).setName(input[0]);
                                        WriteFile.editSubjectTimeTable(indexPlan, indexSubject,
                                                        plan.getTimeTable().getSubjects().get(indexSubject));
                                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                                        Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                                        DialogMessage.CENTER_CENTER, "Information",
                                                        new String[] { "Sửa tên môn học thành công" },
                                                        Setting.INFORMATION);
                                        parentPanel.updateDataSubjectList();
                                        parentPanel.updateContent();
                                }

                        }
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                        repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                        repaint();
                }
        }
}
