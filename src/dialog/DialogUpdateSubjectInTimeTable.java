package src.dialog;

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

public class DialogUpdateSubjectInTimeTable {
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
    private int indexTimeLesson;
    private JPanel panelScroll = null;
    private int scrollCursor = 0;
    private Button panelTimeLessonName = null;
    private String[] buttonTexts = {
            ""
    };
    private Button[] buttons;

    // Constructor
    public DialogUpdateSubjectInTimeTable(int x, int y, int width, int height, int rootLocationType, String title,
            String[] messageLines, Subject subject, int indexTimeLesson) {
        this.width = width;
        this.subject = subject;
        this.indexTimeLesson = indexTimeLesson;
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

        panelTimeLessonName = new Button("Mã lớp học: " + subject.getListTimeNames().get(indexTimeLesson));
        panelTimeLessonName.setFontText(Button.ARIAL_BOLD_18);
        panelTimeLessonName.setCorrectSizeButton();
        panelTimeLessonName.setSizeButton(Math.min(width / 3 * 2, panelTimeLessonName.getWidth() + width / 10),
                panelTimeLessonName.getHeight() / 7 * 8 + 10);
        panelTimeLessonName.setLocationButton(0, tempHeight, Button.TOP_LEFT);
        panelTimeLessonName.setLocationText(width / 10, 0);
        panelTimeLessonName.setBackgroundColorButton(dialog.getBackground());
        panelTimeLessonName.setStrokeWidth(0);
        panelTimeLessonName.setEnable(false);
        tempHeight += panelTimeLessonName.getHeight() + 100;

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

        // Set location for each button
        buttons[0].setLocationButton(panelTimeLessonName.getX() + panelTimeLessonName.getWidth() + 20,
                panelTimeLessonName.getY(),
                Button.TOP_LEFT);

        panelScroll.setSize(width, Math.max(tempHeight, height));
        panelScroll.setBounds(0, -this.scrollCursor, panelScroll.getWidth(), panelScroll.getHeight());

        // add panel
        panelScroll.add(panelTimeLessonName);

        panelScroll.addMouseWheelListener(new MouseWheelHandler());
        dialog.add(panelScroll);

        // Show dialog
        dialog.setVisible(true);
    }

    public void updateContent() {
        panelTimeLessonName.setTextButton("Mã lớp học: " + subject.getListTimeNames().get(indexTimeLesson));
        panelTimeLessonName.setCorrectSizeButton();
        panelTimeLessonName.setSizeButton(Math.min(width / 3 * 2, panelTimeLessonName.getWidth() + width / 10),
                panelTimeLessonName.getHeight() / 7 * 8 + 10);
        buttons[0].setLocationButton(panelTimeLessonName.getX() + panelTimeLessonName.getWidth() + 20,
                panelTimeLessonName.getY(),
                Button.TOP_LEFT);
        panelScroll.setBounds(panelScroll.getX(), -this.scrollCursor, panelScroll.getWidth(),
                panelScroll.getHeight());

    }

    // Get subject
    public Subject getSubject() {
        return this.subject;
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
                DialogInput dialog = new DialogInput(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                        Setting.WIDTH / 3, Setting.HEIGHT / 3, DialogInput.CENTER_CENTER,
                        "Edit", new String[] { "Nhập mã lớp học mới:" },
                        Setting.INFORMATION, new String[] { "Tên môn học" });
                String[] input = dialog.getInputString();
                if (input[0].isEmpty()) {
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                            Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER, "Information",
                            new String[] { "Sửa mã lớp học thất bại" }, Setting.WARNING);
                } else {
                    subject.getListTimeNames().set(indexTimeLesson, input[0]);
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                            Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER, "Information",
                            new String[] { "Sửa mã lớp học thành công" },
                            Setting.INFORMATION);
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
