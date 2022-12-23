package code.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.event.MouseListener;
import java.util.List;
import java.awt.event.MouseEvent;
import code.Setting;
import code.file_handler.ReadFile;
import code.file_handler.WriteFile;
import code.objects.Button;
import code.objects.Plan;
import code.objects.TextField;
import code.panel.PanelString;

import java.awt.Font;

public class DialogCopyPlan {
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
    private TextField fieldName = null;
    private Button button = null;
    JDialog dialog = null;
    private int indexPlan;

    // Constructor
    public DialogCopyPlan(int x, int y, int width, int height, int rootLocationType, String title,
            String[] messageLines, int indexPlan) {
        // Create frame and set propertis of this frame
        this.indexPlan = indexPlan;
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

        // Create objects in this panel (String, button, ...)
        int tempHeight = 30;
        PanelString messagePanel = new PanelString(width / 20 - 15, tempHeight, messageLines, width / 10 * 9, null,
                PanelString.TOP_LEFT, 0);
        tempHeight += messagePanel.getHeight() + 10;
        fieldName = new TextField(width / 2, tempHeight,
                width / 5 * 3, 50, TextField.TOP_CENTER, "Tên của bản sao kế hoạch", 2, 15, 15);
        tempHeight += fieldName.getHeight() + 10;

        button = new Button("Tạo bản sao");
        button.setFont(new Font(
                Setting.FONT_NAME_01,
                Setting.FONT_STYLE_01,
                Setting.FONT_SIZE_SMALL));
        button.setLocationButton(width / 2, tempHeight, Button.TOP_CENTER);
        button.addMouseListener(new MouseHandler());
        dialog.add(messagePanel);
        dialog.add(fieldName);
        dialog.add(button);

        // Show dialog
        dialog.setVisible(true);
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            if (event.getSource() == button) {
                if (fieldName.getText().isEmpty() ||
                        (fieldName.getText().equals(fieldName.getDefaultText())
                                && fieldName.getForeground() == Setting.COLOR_GRAY_03)) {
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER,
                            "Information", new String[] { "Bạn chưa nhập tên cho bản sao của kế hoạch này" },
                            Setting.WARNING);
                } else {
                    List<Plan> plans = ReadFile.getCurrentAccount().getPlans();
                    WriteFile.createCopyPlan(plans.get(indexPlan), fieldName.getText());
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER,
                            "Information", new String[] { "Tạo bản sao kế hoạch thành công" }, Setting.INFORMATION);
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
