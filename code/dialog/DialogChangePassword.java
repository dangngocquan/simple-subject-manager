package code.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import code.Setting;
import code.file_handler.WriteFile;
import code.objects.Account;
import code.objects.Button;
import code.panel.PanelString;
import code.text_field.TextField;

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
        button.setFont(
                Setting.FONT_NAME_01,
                Setting.FONT_STYLE_01,
                Setting.FONT_SIZE_SMALL);
        button.setLocation(width / 2, tempHeight, Button.TOP_CENTER);
        button.addActionListener(new ButtonHandler());
        dialog.add(messagePanel);
        dialog.add(oldPassword);
        dialog.add(newPassword);
        dialog.add(confirmNewPassword);
        dialog.add(button);

        // Show dialog
        dialog.setVisible(true);
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == button) {
                if (oldPassword.getText().isEmpty() ||
                        (oldPassword.getText().equals(oldPassword.getDefaultText())
                                && oldPassword.getForeground() == Setting.COLOR_GRAY_03)) {
                    JOptionPane.showMessageDialog(dialog, "Bạn chưa nhập mật khẩu hiện tại",
                            "Invalid input",
                            JOptionPane.WARNING_MESSAGE);
                } else if (!oldPassword.getText().equals(account.getPassword())) {
                    JOptionPane.showMessageDialog(dialog, "Mật khẩu hiện tại không chính xác",
                            "Incorrect pasword",
                            JOptionPane.WARNING_MESSAGE);
                } else if (newPassword.getText().isEmpty() ||
                        (newPassword.getText().equals(newPassword.getDefaultText())
                                && newPassword.getForeground() == Setting.COLOR_GRAY_03)) {
                    JOptionPane.showMessageDialog(dialog, "Bạn chưa nhập mật khẩu mới",
                            "Invalid input",
                            JOptionPane.WARNING_MESSAGE);
                } else if (confirmNewPassword.getText().isEmpty() ||
                        (confirmNewPassword.getText().equals(confirmNewPassword.getDefaultText())
                                && confirmNewPassword.getForeground() == Setting.COLOR_GRAY_03)) {
                    JOptionPane.showMessageDialog(dialog, "Bạn chưa xác nhận mật khẩu mới",
                            "Invalid input",
                            JOptionPane.WARNING_MESSAGE);
                } else if (!newPassword.getText().equals(confirmNewPassword.getText())) {
                    JOptionPane.showMessageDialog(dialog, "Mật khẩu không khớp",
                            "Invalid input",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    Account newAcccount = new Account("", "", confirmNewPassword.getText());
                    WriteFile.changeInformationAccount(account, newAcccount, "password");
                    JOptionPane.showMessageDialog(dialog, "Thay đổi mật khẩu thành công",
                            "Change password successed",
                            JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                }
            }
        }
    }
}
