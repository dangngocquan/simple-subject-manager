package src.dialog;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import src.Setting;
import src.objects.Button;
import src.objects.Subject;
import src.panel.PanelString;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.Font;
import java.awt.Color;

public class DialogUpdateMapRelative {
        // Constants dialog's root location
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
        private int width;
        private JDialog dialog = null;
        private Subject subject;
        private JPanel panelScroll = null;
        private int scrollCursor = 0;
        private PanelString panelTitle = null;
        private PanelString panelSubjectName = null;
        private PanelString panelSubjectCode = null;
        private PanelString panelSubjectCredits = null;
        private JPanel panelSubjectParentCodes = null;
        private PanelString panelSubjectParentCodes1 = null;
        private PanelString panelSubjectParentCodes2 = null;
        private PanelString panelStatus = null;
        private PanelString panelScore10 = null;
        private PanelString panelCharacterScore = null;
        private PanelString panelScore4 = null;
        private Button panelColor = null;
        private Button panelSemester = null;
        private JPanel panelColorDemo = null;
        private String[] buttonTexts = {
                        "", ""
        };
        private Button[] buttons;

        // Constructor
        public DialogUpdateMapRelative(int x, int y, int width, int height, int rootLocationType, String title,
                        String[] messageLines, Subject subject) {
                this.width = width;
                this.subject = subject;
                // Create frame and set propertis of this frame
                JFrame f = new JFrame();
                dialog = new JDialog(f, title, true);
                dialog.setLayout(null);
                dialog.setSize(width, height);
                dialog.setIconImage(Setting.LOGO.getImage());
                dialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                int xPos = x, yPos = y;
                switch (rootLocationType) {
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
                dialog.setBounds(xPos, yPos, width, height);

                // Create panelScroll
                this.panelScroll = new JPanel();
                panelScroll.setLayout(null);

                // Create objects in this panel (String, button, ...)
                int tempHeight = 30;
                String[] titles = {
                                "Thông tin môn học",
                                "=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:="
                };
                panelTitle = new PanelString(width / 2, tempHeight, titles, width,
                                new Font(Setting.FONT_NAME_01,
                                                Setting.FONT_STYLE_01,
                                                Setting.FONT_SIZE_MEDIUM),
                                PanelString.TOP_CENTER, 0);
                tempHeight += panelTitle.getHeight() + 20;
                panelSubjectName = new PanelString(0, tempHeight, "Tên môn học: " + subject.getName(),
                                width, null, PanelString.TOP_LEFT, width / 10);
                tempHeight += panelSubjectName.getHeight() + 5;
                panelSubjectCode = new PanelString(0, tempHeight, "Mã môn học: " + subject.getCode(),
                                width, null, PanelString.TOP_LEFT, width / 10);
                tempHeight += panelSubjectCode.getHeight() + 5;
                panelSubjectCredits = new PanelString(0, tempHeight, "Số tín chỉ: " + subject.getNumberCredits(),
                                width, null, PanelString.TOP_LEFT, width / 10);
                tempHeight += panelSubjectCredits.getHeight() + 5;
                panelSubjectParentCodes1 = new PanelString(0, 0,
                                "Các môn học tiên quyết: ",
                                width / 3, null, PanelString.TOP_LEFT, width / 10);
                panelSubjectParentCodes2 = new PanelString(panelSubjectParentCodes1.getWidth(), 0,
                                subject.getParentSubjectCodes(),
                                width - panelSubjectParentCodes1.getWidth(), null, PanelString.TOP_LEFT, 1);
                panelSubjectParentCodes = new JPanel();
                panelSubjectParentCodes.setLayout(null);
                panelSubjectParentCodes.setSize(width,
                                Math.max(panelSubjectParentCodes1.getHeight(),
                                                panelSubjectParentCodes2.getHeight()));
                panelSubjectParentCodes.setBounds(0, tempHeight, panelSubjectParentCodes.getWidth(),
                                panelSubjectParentCodes.getHeight());
                tempHeight += panelSubjectParentCodes.getHeight() + 5;
                panelStatus = new PanelString(0, tempHeight, "Trạng thái: " + subject.getStringStatus(),
                                width, null, PanelString.TOP_LEFT, width / 10);
                tempHeight += panelStatus.getHeight() + 5;
                panelScore10 = new PanelString(0, tempHeight, "Điểm hệ 10: " + subject.getStringScore10(),
                                width, null, PanelString.TOP_LEFT, width / 10);
                tempHeight += panelScore10.getHeight() + 5;
                panelCharacterScore = new PanelString(0, tempHeight, "Điểm chữ: " + subject.getCharacterScore(),
                                width, null, PanelString.TOP_LEFT, width / 10);
                tempHeight += panelCharacterScore.getHeight() + 5;
                panelScore4 = new PanelString(0, tempHeight, "Điểm hệ 4: " + subject.getStringScore4(),
                                width, null, PanelString.TOP_LEFT, width / 10);
                tempHeight += panelScore4.getHeight() + 5;

                panelColor = new Button("Màu sắc: " + subject.getStringColor());
                panelColor.setFontText(Button.ARIAL_BOLD_18);
                panelColor.setCorrectSizeButton();
                panelColor.setSizeButton(width / 3, panelColor.getHeight() / 7 * 8 + 10);
                panelColor.setLocationButton(0, tempHeight, Button.TOP_LEFT);
                panelColor.setLocationText(width / 10, 0);
                panelColor.setBackgroundColorButton(dialog.getBackground());
                panelColor.setStrokeWidth(0);
                panelColor.setEnable(false);
                tempHeight += panelColor.getHeight() + 5;

                panelSemester = new Button("Học kỳ: " + subject.getSemester());
                panelSemester.setFontText(Button.ARIAL_BOLD_18);
                panelSemester.setCorrectSizeButton();
                panelSemester.setSizeButton(width / 5, panelSemester.getHeight() / 7 * 8 + 10);
                panelSemester.setLocationButton(0, tempHeight, Button.TOP_LEFT);
                panelSemester.setLocationText(width / 10, 0);
                panelSemester.setBackgroundColorButton(dialog.getBackground());
                panelSemester.setStrokeWidth(0);
                panelSemester.setEnable(false);
                tempHeight += panelSemester.getHeight() + 100;

                // Create buttons
                buttons = new Button[buttonTexts.length];
                for (int count = 0; count < buttonTexts.length; count++) {
                        buttons[count] = new Button(buttonTexts[count]);
                        buttons[count].setFont(new Font(
                                        Setting.FONT_NAME_01,
                                        Setting.FONT_STYLE_01,
                                        Setting.FONT_SIZE_SMALL));
                        buttons[count].setSizeButton(buttons[count].getHeight(), buttons[count].getHeight());
                        buttons[count].setBackgroundIcon(Setting.EDIT);
                        buttons[count].addMouseListener(new MouseHandler());
                        panelScroll.add(buttons[count]);
                }

                // panel color demo
                panelColorDemo = new JPanel();
                panelColorDemo.setLayout(null);
                panelColorDemo.setSize(this.width / 10, panelColor.getHeight());
                panelColorDemo.setBounds(panelColor.getX() + panelColor.getWidth() + 20, panelColor.getY(),
                                panelColorDemo.getWidth(), panelColorDemo.getHeight());
                panelColorDemo.setBackground(subject.getColor());

                // Set location for each button
                buttons[0].setLocationButton(panelColorDemo.getX() + panelColorDemo.getWidth() + 20, panelColor.getY(),
                                Button.TOP_LEFT);
                buttons[1].setLocationButton(panelSemester.getX() + panelSemester.getWidth() + 20, panelSemester.getY(),
                                Button.TOP_LEFT);

                panelScroll.setSize(width, tempHeight);
                panelScroll.setBounds(0, -this.scrollCursor, panelScroll.getWidth(), panelScroll.getHeight());

                // add panel
                panelScroll.add(panelTitle);
                panelScroll.add(panelSubjectName);
                panelScroll.add(panelSubjectCode);
                panelScroll.add(panelSubjectCredits);
                panelScroll.add(panelSubjectParentCodes);
                panelSubjectParentCodes.add(panelSubjectParentCodes1);
                panelSubjectParentCodes.add(panelSubjectParentCodes2);
                panelScroll.add(panelStatus);
                panelScroll.add(panelScore10);
                panelScroll.add(panelCharacterScore);
                panelScroll.add(panelScore4);
                panelScroll.add(panelColor);
                panelScroll.add(panelSemester);
                panelScroll.add(panelColorDemo);

                panelScroll.addMouseWheelListener(new MouseWheelHandler());
                dialog.add(panelScroll);

                // Show dialog
                dialog.setVisible(true);
        }

        public void updateContent() {
                panelColor.setTextButton("Màu sắc: " + subject.getStringColor());
                panelColorDemo.setBackground(subject.getColor());
                panelSemester.setTextButton("Học kỳ: " + subject.getSemester());
                panelScroll.setBounds(panelScroll.getX(), -this.scrollCursor, panelScroll.getWidth(),
                                panelScroll.getHeight());
        }

        // Get max of scrollCursor
        public int getMaxScrollCursor() {
                return Math.max(0, panelScroll.getHeight() - dialog.getHeight());
        }

        // Set cursor
        public void setScrollCursor(int value) {
                if (value < 0) {
                        this.scrollCursor = 0;
                } else if (value > getMaxScrollCursor()) {
                        this.scrollCursor = getMaxScrollCursor();
                } else {
                        this.scrollCursor = value;
                }
        }

        // Get subject
        public Subject getSubject() {
                return this.subject;
        }

        private class MouseWheelHandler implements MouseWheelListener {
                public void mouseWheelMoved(MouseWheelEvent event) {
                        for (int count = 0; count < 20; count++) {
                                setScrollCursor(scrollCursor + event.getWheelRotation());
                        }
                        updateContent();
                }

        }

        private class MouseHandler implements MouseListener {

                @Override
                public void mouseClicked(MouseEvent event) {
                }

                @Override
                public void mousePressed(MouseEvent event) {
                        if (event.getSource() == buttons[0]) {
                                Color colorInput = JColorChooser.showDialog(dialog, "Choose color", subject.getColor());
                                if (colorInput != null) {
                                        subject.setColor(colorInput);
                                        updateContent();
                                }
                        } else if (event.getSource() == buttons[1]) {
                                int oldSemester = subject.getSemester();
                                DialogList dialog1 = new DialogList(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                                Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                                DialogList.CENTER_CENTER, "Edit semester", new String[] {
                                                                "Chọn học kỳ dành cho môn học này.",
                                                                "(Học kỳ 0 đồng nghĩa với việc không xét học kỳ,",
                                                                "không ảnh hưởng tới sơ đồ liên hệ môn)"
                                                },
                                                new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                                                                "11", "12", "13", "14" });
                                String semester = dialog1.getText();
                                if (semester != null && !semester.isEmpty()) {
                                        subject.setSemester(Integer.parseInt(semester));
                                        if (subject.getSemester() != oldSemester) {
                                                subject.setRowIndexSorted(-1);
                                                subject.setColumnIndexSorted(-1);
                                        }
                                        updateContent();
                                }
                        }
                }

                @Override
                public void mouseReleased(MouseEvent event) {

                }

                @Override
                public void mouseEntered(MouseEvent event) {

                }

                @Override
                public void mouseExited(MouseEvent event) {

                }
        }
}
