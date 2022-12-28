package src.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;

import src.Setting;
import src.file_handler.WriteFile;
import src.objects.Account;
import src.objects.Button;
import src.objects.TextField;
import src.panel.PanelString;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class DialogChangePassword {
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
    private TextField oldPassword = null, newPassword = null, confirmNewPassword = null;
    private Button button = null;
    private Account account;
    JDialog dialog = null;

    // Constructor
    public DialogChangePassword(int x, int y, int width, int height, int rootLocationType, String title,
            String[] messageLines, Account account) {
        this.account = account;
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
        oldPassword = new TextField(width / 2, tempHeight,
                width / 20 * 18, 50, TextField.TOP_CENTER, "Mật khẩu hiện tại", 2, 15, 15);
        tempHeight += oldPassword.getHeight() + 10;
        newPassword = new TextField(width / 2, tempHeight,
                width / 20 * 18, 50, TextField.TOP_CENTER, "Mật khẩu mới", 2, 15, 15);
        tempHeight += newPassword.getHeight() + 10;
        confirmNewPassword = new TextField(width / 2, tempHeight,
                width / 20 * 18, 50, TextField.TOP_CENTER, "Xác nhận mật khẩu mới", 2, 15, 15);
        tempHeight += confirmNewPassword.getHeight() + 10;
        button = new Button("Xác nhận");
        button.setFont(new Font(
                Setting.FONT_NAME_01,
                Setting.FONT_STYLE_01,
                Setting.FONT_SIZE_SMALL));
        button.setLocationButton(width / 2, tempHeight, Button.TOP_CENTER);
        button.addMouseListener(new MouseHandler());
        tempHeight += button.getHeight() + 100;

        dialog.add(messagePanel);
        dialog.add(oldPassword);
        dialog.add(newPassword);
        dialog.add(confirmNewPassword);
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
                if (oldPassword.getText().isEmpty() ||
                        (oldPassword.getText().equals(oldPassword.getDefaultText())
                                && oldPassword.getForeground() == Setting.COLOR_GRAY_03)) {
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER,
                            "Information", new String[] { "Bạn chưa nhập mật khẩu hiện tại" }, Setting.WARNING);
                } else if (!oldPassword.getText().equals(account.getPassword())) {
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER,
                            "Information", new String[] { "Mật khẩu hiện tại không chính xác" }, Setting.WARNING);
                } else if (newPassword.getText().isEmpty() ||
                        (newPassword.getText().equals(newPassword.getDefaultText())
                                && newPassword.getForeground() == Setting.COLOR_GRAY_03)) {
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER,
                            "Information", new String[] { "Bạn chưa nhập mật khẩu mới" }, Setting.WARNING);
                } else if (confirmNewPassword.getText().isEmpty() ||
                        (confirmNewPassword.getText().equals(confirmNewPassword.getDefaultText())
                                && confirmNewPassword.getForeground() == Setting.COLOR_GRAY_03)) {
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER,
                            "Information", new String[] { "Bạn chưa xác nhận mật khẩu mới" }, Setting.WARNING);
                } else if (!newPassword.getText().equals(confirmNewPassword.getText())) {
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER,
                            "Information", new String[] { "Mật khẩu không khớp" }, Setting.WARNING);
                } else {
                    Account newAcccount = new Account("", "", confirmNewPassword.getText());
                    WriteFile.changeInformationAccount(account, newAcccount, "password");
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER,
                            "Information", new String[] { "Thay đổi mật khẩu thành công" }, Setting.INFORMATION);
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
