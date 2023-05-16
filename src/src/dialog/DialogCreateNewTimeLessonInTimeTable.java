package src.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import src.launcher.Setting;
import src.objects.Button;
import src.objects.Subject;
import src.objects.TextField;
import src.panel.PanelTable;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.MouseWheelEvent;

public class DialogCreateNewTimeLessonInTimeTable {
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
    private int indexTimeLesson;
    private JPanel panelScroll = null;
    private int scrollCursor = 0;
    private Button panelTimeLessonName = null;
    private TextField fieldTimeLessonName = null;
    private Button panelTimeInfor = null;
    private PanelTable panelTimeTable = null;
    private Button buttonOK = null;
    private boolean pressedOK = false;
    private Subject rootSubject = null;

    // Constructor
    public DialogCreateNewTimeLessonInTimeTable(int x, int y, int width, int height, int rootLocationType, String title,
            String[] messageLines, Subject rootSubject) {
        this.width = width;
        this.rootSubject = rootSubject;
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

        panelTimeLessonName = new Button("Mã lớp học: ");
        panelTimeLessonName.setFontText(Button.ARIAL_BOLD_18);
        panelTimeLessonName.setCorrectSizeButton();
        panelTimeLessonName.setSizeButton(Math.min(width / 3 * 2, panelTimeLessonName.getWidth() + width / 10),
                panelTimeLessonName.getHeight() / 7 * 8 + 10);
        panelTimeLessonName.setLocationButton(0, tempHeight, Button.TOP_LEFT);
        panelTimeLessonName.setLocationText(width / 10, 0);
        panelTimeLessonName.setBackgroundColorButton(dialog.getBackground());
        panelTimeLessonName.setStrokeWidth(0);
        panelTimeLessonName.setEnable(false);
        tempHeight += panelTimeLessonName.getHeight() + 10;

        // Create text field to get time lesson name
        fieldTimeLessonName = new TextField(panelTimeLessonName.getX() + panelTimeLessonName.getWidth(),
                panelTimeLessonName.getY(), width / 2, panelTimeLessonName.getHeight(), TextField.TOP_LEFT,
                "Mã lớp học", 2, 15, 15);

        // Panel time information
        panelTimeInfor = new Button("Thời gian:");
        panelTimeInfor.setFontText(Button.ARIAL_BOLD_18);
        panelTimeInfor.setCorrectSizeButton();
        panelTimeInfor.setSizeButton(Math.min(width / 3 * 2, panelTimeInfor.getWidth() + width / 10),
                panelTimeInfor.getHeight() / 7 * 8 + 10);
        panelTimeInfor.setLocationButton(0, tempHeight, Button.TOP_LEFT);
        panelTimeInfor.setLocationText(width / 10, 0);
        panelTimeInfor.setBackgroundColorButton(dialog.getBackground());
        panelTimeInfor.setStrokeWidth(0);
        panelTimeInfor.setEnable(false);
        tempHeight += panelTimeInfor.getHeight() + 10;

        // Panel Time table
        List<Subject> subjects = new LinkedList<>();
        subjects.add(null);
        List<Integer> indexTimeLessons = new LinkedList<>();
        indexTimeLessons.add(indexTimeLesson);
        panelTimeTable = new PanelTable(width / 10, tempHeight, width / 10 * 8, height / 18 * 11 + 2, null,
                PanelTable.TOP_LEFT, 10, 7, subjects, indexTimeLessons, true);
        panelTimeTable.setEditable(true);
        tempHeight += panelTimeTable.getHeight() + 30;

        // Button OK
        buttonOK = new Button("OK");
        buttonOK.addMouseListener(new MouseHandler());
        buttonOK.setLocationButton(width / 2, tempHeight, Button.TOP_CENTER);
        tempHeight += buttonOK.getHeight() + 10;

        panelScroll.setSize(width, Math.max(tempHeight, height));
        panelScroll.setBounds(0, -this.scrollCursor, panelScroll.getWidth(), panelScroll.getHeight());

        // add panel
        panelScroll.add(panelTimeLessonName);
        panelScroll.add(fieldTimeLessonName);
        panelScroll.add(panelTimeInfor);
        panelScroll.add(panelTimeTable);
        panelScroll.add(buttonOK);

        panelScroll.addMouseWheelListener(new MouseWheelHandler());
        dialog.add(panelScroll);

        // Show dialog
        dialog.setVisible(true);
    }

    public void updateContent() {
        panelTimeLessonName.setTextButton("Mã lớp học: " + rootSubject.getListTimeNames().get(indexTimeLesson));
        panelTimeLessonName.setCorrectSizeButton();
        panelTimeLessonName.setSizeButton(Math.min(width / 3 * 2, panelTimeLessonName.getWidth() + width / 10),
                panelTimeLessonName.getHeight() / 7 * 8 + 10);

        panelScroll.setBounds(panelScroll.getX(), -this.scrollCursor, panelScroll.getWidth(),
                panelScroll.getHeight());

    }

    // Get subject
    public Subject getSubject() {
        return this.rootSubject;
    }

    // Get boolean pressedOK
    public boolean pressedOK() {
        return this.pressedOK;
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
            if (event.getSource() == buttonOK) {
                String inputText = fieldTimeLessonName.getText();
                if (inputText == null || inputText.isEmpty()) {
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                            Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER, "Information",
                            new String[] { "Mã lớp học không được để trống." }, Setting.WARNING);
                } else if (rootSubject.getListTimeNames().contains(inputText)) {
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                            Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER, "Information",
                            new String[] { "Mã lớp học này đã tồn tại." }, Setting.WARNING);
                } else {
                    rootSubject.getListTimeNames().add(0, inputText);
                    rootSubject.getListTimes().add(0, panelTimeTable.getSelectedIndexPanels());
                    rootSubject.getListEnableTimeLessons().add(0, true);
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                            Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER, "Information",
                            new String[] { "Tạo mã lớp học mới thành công." }, Setting.WARNING);
                    pressedOK = true;
                    dialog.dispose();
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
