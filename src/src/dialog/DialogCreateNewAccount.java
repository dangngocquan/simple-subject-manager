package src.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;

import src.launcher.ReadFile;
import src.launcher.Setting;
import src.launcher.WriteFile;
import src.objects.Account;
import src.objects.Button;
import src.objects.TextField;
import src.panel.PanelString;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class DialogCreateNewAccount {
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
    private TextField fieldName = null, fieldUsername = null, fieldPassword = null;
    private Button button = null;
    JDialog dialog = null;

    // Constructor
    public DialogCreateNewAccount(int x, int y, int width, int height, int rootLocationType, String title,
            String[] messageLines) {
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

        // Create objects in this panel (String, button, ...)
        int tempHeight = 30;
        PanelString messagePanel = new PanelString(width / 20 - 15, tempHeight, messageLines, width / 10 * 9, null,
                PanelString.TOP_LEFT, 0);
        tempHeight += messagePanel.getHeight() + 10;
        fieldName = new TextField(width / 2, tempHeight,
                width / 20 * 18, 50, TextField.TOP_CENTER, "Họ và tên", 2, 15, 15);
        tempHeight += fieldName.getHeight() + 10;
        fieldUsername = new TextField(width / 2, tempHeight,
                width / 20 * 18, 50, TextField.TOP_CENTER, "Tên đăng nhập", 2, 15, 15);
        tempHeight += fieldUsername.getHeight() + 10;
        fieldPassword = new TextField(width / 2, tempHeight,
                width / 20 * 18, 50, TextField.TOP_CENTER, "Mật khẩu", 2, 15, 15);
        tempHeight += fieldPassword.getHeight() + 10;
        button = new Button("Tạo");
        button.setFont(new Font(
                Setting.FONT_NAME_01,
                Setting.FONT_STYLE_01,
                Setting.FONT_SIZE_SMALL));
        button.setLocationButton(width / 2, tempHeight, Button.TOP_CENTER);
        button.addMouseListener(new MouseHandler());
        tempHeight += button.getHeight() + 100;

        dialog.add(messagePanel);
        dialog.add(fieldName);
        dialog.add(fieldUsername);
        dialog.add(fieldPassword);
        dialog.add(button);

        dialog.setSize(width, Math.min(height, tempHeight));

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
                            "Information", new String[] { "Bạn chưa nhập Họ và tên" }, Setting.WARNING);
                } else if (fieldUsername.getText().isEmpty() ||
                        (fieldUsername.getText().equals(fieldUsername.getDefaultText())
                                && fieldUsername.getForeground() == Setting.COLOR_GRAY_03)) {
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER,
                            "Information", new String[] { "Bạn chưa nhập Tên đăng nhập" }, Setting.WARNING);
                } else if (ReadFile.isExistingUsername(fieldUsername.getText())) {
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER,
                            "Information", new String[] { "Tên đăng nhập đã tồn tại" }, Setting.WARNING);
                } else if (fieldPassword.getText().isEmpty() ||
                        (fieldPassword.getText().equals(fieldPassword.getDefaultText())
                                && fieldPassword.getForeground() == Setting.COLOR_GRAY_03)) {
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER,
                            "Information", new String[] { "Bạn chưa thiết lập mật khẩu" }, Setting.WARNING);
                } else {
                    WriteFile.createNewAccount(new Account(
                            fieldName.getText(), fieldUsername.getText(), fieldPassword.getText()));
                    WriteFile.writeStringToFile(ReadFile.PATH_DATA_TEMP_1, fieldUsername.getText(), false);
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER,
                            "Information", new String[] { "Tạo tài khoản thành công" }, Setting.INFORMATION);
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
