package code.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import code.Setting;
import code.file_handler.ReadFile;
import code.file_handler.WriteFile;
import code.objects.Account;
import code.objects.Button;
import code.panel.PanelString;
import code.text_field.TextField;

public class DialogLoginAccount {
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
    private TextField fieldUsername = null, fieldPassword = null;
    private Button button = null;
    JDialog dialog = null;
    private Account avoidAccount;

    // Constructor
    public DialogLoginAccount(int x, int y, int width, int height, int rootLocationType, String title,
            String[] messageLines, Account avoidAccount) {
        this.avoidAccount = avoidAccount;
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
                PanelString.TOP_LEFT, width / 20);
        tempHeight += messagePanel.getHeight() + 10;
        fieldUsername = new TextField(width / 2, tempHeight,
                width / 20 * 18, 50, TextField.TOP_CENTER, "Tên đăng nhập", 2, 15, 15);
        tempHeight += fieldUsername.getHeight() + 10;
        fieldPassword = new TextField(width / 2, tempHeight,
                width / 20 * 18, 50, TextField.TOP_CENTER, "Mật khẩu", 2, 15, 15);
        tempHeight += fieldPassword.getHeight() + 10;
        button = new Button("Đăng nhập");
        button.setFont(
                Setting.FONT_NAME_01,
                Setting.FONT_STYLE_01,
                Setting.FONT_SIZE_SMALL);
        button.setLocation(width / 2, tempHeight, Button.TOP_CENTER);
        button.addActionListener(new ButtonHandler());
        dialog.add(messagePanel);
        dialog.add(fieldUsername);
        dialog.add(fieldPassword);
        dialog.add(button);

        // Show dialog
        dialog.setVisible(true);
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == button) {
                if (fieldUsername.getText().isEmpty() ||
                        (fieldUsername.getText().equals(fieldUsername.getDefaultText())
                                && fieldUsername.getForeground() == Setting.COLOR_GRAY_03)) {
                    JOptionPane.showMessageDialog(dialog, "Bạn chưa nhập Tên đăng nhập.",
                            "Invalid input",
                            JOptionPane.WARNING_MESSAGE);
                } else if (!ReadFile.isExistingUsername(fieldUsername.getText())) {
                    System.out.println();
                    JOptionPane.showMessageDialog(dialog, "Tên đăng nhập không tồn tại.",
                            "Invalid username",
                            JOptionPane.WARNING_MESSAGE);
                } else if (fieldPassword.getText().isEmpty() ||
                        (fieldPassword.getText().equals(fieldPassword.getDefaultText())
                                && fieldPassword.getForeground() == Setting.COLOR_GRAY_03)) {
                    JOptionPane.showMessageDialog(dialog, "Bạn chưa nhập mật khẩu.",
                            "Invalid input",
                            JOptionPane.WARNING_MESSAGE);
                } else if (!fieldPassword.getText()
                        .equals(ReadFile.findAccountByUsername(fieldUsername.getText()).getPassword())) {
                    JOptionPane.showMessageDialog(dialog, "Mật khẩu không chính xác.",
                            "Incorrect password",
                            JOptionPane.WARNING_MESSAGE);
                } else if (avoidAccount != null && avoidAccount.getUsername().equals(fieldUsername.getText())) {
                    JOptionPane.showMessageDialog(dialog,
                            "Đây là tài khoản bạn đang muốn xóa.\nVì vậy, bạn không thể đăng nhập tài khoản này.",
                            "Invalid account",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    WriteFile.writeStringToFile(ReadFile.PATH_DATA_TEMP_1, fieldUsername.getText(), false);
                    JOptionPane.showMessageDialog(dialog, "Đăng nhập tài khoản thành công.",
                            "Login account successed",
                            JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                }
            }
        }
    }
}
