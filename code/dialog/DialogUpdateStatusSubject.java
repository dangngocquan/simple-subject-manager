package code.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.MouseEvent;
import code.Setting;
import code.objects.Button;
import code.objects.Subject;
import code.panel.PanelString;
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
    private PanelString panelTitle = null;
    private PanelString panelSubjectName = null;
    private PanelString panelSubjectCode = null;
    private PanelString panelSubjectCredits = null;
    private JPanel panelSubjectParentCodes = null;
    private PanelString panelSubjectParentCodes1 = null;
    private PanelString panelSubjectParentCodes2 = null;
    private PanelString panelAdvice = null;
    private PanelString panelStatus = null;
    private String[] buttonTexts = {
            "Sửa"
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
        panelStatus = new PanelString(0, tempHeight, "Trạng thái: " + subject.getStringStatus(),
                width, null, PanelString.TOP_LEFT, width / 10);
        tempHeight += panelStatus.getHeight() + 5;

        // Create buttons
        buttons = new Button[buttonTexts.length];
        for (int count = 0; count < buttonTexts.length; count++) {
            buttons[count] = new Button(buttonTexts[count]);
            buttons[count].setFont(new Font(
                    Setting.FONT_NAME_01,
                    Setting.FONT_STYLE_01,
                    Setting.FONT_SIZE_SMALL));
            buttons[count].addMouseListener(new MouseHandler());
            dialog.add(buttons[count]);
        }

        // Set location for each button
        buttons[0].setLocationButton(panelStatus.getX() + width / 3, panelStatus.getY(), Button.TOP_LEFT);

        // add panel
        dialog.add(panelTitle);
        dialog.add(panelSubjectName);
        dialog.add(panelSubjectCode);
        dialog.add(panelSubjectCredits);
        dialog.add(panelSubjectParentCodes);
        panelSubjectParentCodes.add(panelSubjectParentCodes1);
        panelSubjectParentCodes.add(panelSubjectParentCodes2);
        dialog.add(panelAdvice);
        dialog.add(panelStatus);

        // Show dialog
        dialog.setVisible(true);
    }

    public void updateContent() {
        dialog.setVisible(false);

        dialog.remove(panelStatus);
        panelStatus = new PanelString(0, panelStatus.getY(), "Trạng thái: " + subject.getStringStatus(),
                width, null, PanelString.TOP_LEFT, width / 10);
        dialog.add(panelStatus);
        // Show dialog;
        dialog.setVisible(true);
    }

    // Get subject
    public Subject getSubject() {
        return this.subject;
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            if (event.getSource() == buttons[0]) {
                List<String> values = new LinkedList<>();
                values.add("Chưa đăng kí");
                values.add("Dự định đăng kí");
                values.add("Đã đăng kí");

                DialogList dialog1 = new DialogList(dialog, "Chọn trạng thái môn mà bạn muốn cập nhật", "Edit status",
                        values.toArray(), values.get(0));
                String status = dialog1.run();
                if (status != null) {
                    for (int i = 0; i < values.size(); i++) {
                        if (values.get(i).equals(status)) {
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
