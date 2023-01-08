package src.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import src.launcher.Setting;
import src.objects.Button;
import src.objects.Subject;
import src.panel.PanelString;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.Font;

public class DialogUpdateStatusSubject {
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
    private PanelString panelAdvice = null;
    private Button panelStatus = null;
    private String[] buttonTexts = {
            ""
    };
    private Button[] buttons;

    // Constructor
    public DialogUpdateStatusSubject(int x, int y, int width, int height, int rootLocationType, String title,
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
        panelAdvice = new PanelString(0, tempHeight, "Khả năng đăng kí: " + subject.getAdvice(),
                width, null, PanelString.TOP_LEFT, width / 10);
        tempHeight += panelAdvice.getHeight() + 5;

        panelStatus = new Button("Trạng thái: " + subject.getStringStatus());
        panelStatus.setFontText(Button.ARIAL_BOLD_18);
        panelStatus.setCorrectSizeButton();
        panelStatus.setSizeButton(this.width / 3, panelStatus.getHeight() / 7 * 8 + 10);
        panelStatus.setLocationButton(0, tempHeight, Button.TOP_LEFT);
        panelStatus.setLocationText(width / 10, 0);
        panelStatus.setBackgroundColorButton(dialog.getBackground());
        panelStatus.setStrokeWidth(0);
        panelStatus.setEnable(false);
        tempHeight += panelStatus.getHeight() + 100;

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
        buttons[0].setLocationButton(panelStatus.getX() + panelStatus.getWidth() + 20, panelStatus.getY(),
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
        panelScroll.add(panelAdvice);
        panelScroll.add(panelStatus);

        panelScroll.addMouseWheelListener(new MouseWheelHandler());
        dialog.add(panelScroll);

        // Show dialog
        dialog.setVisible(true);
    }

    public void updateContent() {
        panelStatus.setTextButton("Trạng thái: " + subject.getStringStatus());
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
                String[] values = {
                        "Chưa đăng kí",
                        "Dự định đăng kí",
                        "Đã đăng kí"
                };

                DialogList dialog1 = new DialogList(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                        Setting.WIDTH / 3, Setting.HEIGHT / 3, DialogList.CENTER_CENTER,
                        "Edit status", new String[] { "Chọn trạng thái môn mà bạn muốn cập nhật" },
                        values);
                String status = dialog1.getText();
                if (status != null) {
                    for (int i = 0; i < values.length; i++) {
                        if (values[i].equals(status)) {
                            subject.setState(i);
                        }
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
