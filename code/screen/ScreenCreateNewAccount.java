package code.screen;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import code.Application;
import code.Setting;
import code.file_handler.ReadFile;
import code.file_handler.WriteFile;
import code.objects.Account;
import code.objects.Button;
import code.panel.PanelString;
import code.text_field.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;

public class ScreenCreateNewAccount extends JPanel {
    // Properties
    private JPanel mainScreen;
    private Application applicationFrame;
    private ScreenAccounts parentScreen;
    private TextField fieldName = null, fieldUsername = null, fieldPassword = null;
    private String[] buttonTexts = {
            "Tạo", "Hủy"
    };
    private Button[] buttons;

    // Constructor
    public ScreenCreateNewAccount(int width, int height, ScreenAccounts parentScreen, Application frame) {
        // Set basic properties for this screen
        this.applicationFrame = frame;
        setLayout(null);
        setBounds(0, 0, width, height);
        setSize(width, height);
        this.parentScreen = parentScreen;

        // Create screens
        mainScreen = new JPanel();
        mainScreen.setLayout(null);
        mainScreen.setSize(width, height);
        mainScreen.setBounds(0, 0, width, height);

        // Create buttons
        buttons = new Button[buttonTexts.length];
        for (int count = 0; count < buttonTexts.length; count++) {
            buttons[count] = new Button(buttonTexts[count]);
            buttons[count].setFont(
                    Setting.FONT_NAME_01,
                    Setting.FONT_STYLE_01,
                    Setting.FONT_SIZE_MEDIUM);
            buttons[count].addActionListener(new ButtonHandler());
        }

        // Create text fields
        int tempHeight = 30;
        String[] messageLines = {
                "TẠO TÀI KHOẢN MỚI"
        };
        PanelString messagePanel = new PanelString(width / 2, tempHeight, messageLines, width / 10 * 9,
                new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_BIG),
                PanelString.TOP_CENTER, 0);
        tempHeight += messagePanel.getHeight() + 30;
        fieldName = new TextField(width / 2, tempHeight,
                width / 20 * 15, 50, TextField.TOP_CENTER, "Họ và tên", 2, 15, 15);
        tempHeight += fieldName.getHeight() + 20;
        fieldUsername = new TextField(width / 2, tempHeight,
                width / 20 * 15, 50, TextField.TOP_CENTER, "Tên đăng nhập", 2, 15, 15);
        tempHeight += fieldUsername.getHeight() + 20;
        fieldPassword = new TextField(width / 2, tempHeight,
                width / 20 * 15, 50, TextField.TOP_CENTER, "Mật khẩu", 2, 15, 15);
        tempHeight += fieldPassword.getHeight() + 20;

        // Set location for each button
        buttons[0].setLocation(width / 4, tempHeight, Button.TOP_CENTER);
        buttons[1].setLocation(width / 4 * 3, tempHeight, Button.TOP_CENTER);

        // Add buttons to mainScreen
        for (Button button : buttons) {
            mainScreen.add(button);
        }

        mainScreen.add(messagePanel);
        mainScreen.add(fieldName);
        mainScreen.add(fieldUsername);
        mainScreen.add(fieldPassword);

        // Add screen to this panel
        add(mainScreen);
        // add(screenCurriculumInformation);

        // Set visible of screens
        mainScreen.setVisible(true);
        // screenCurriculumInformation.setVisible(false);
    }

    // Get application frame
    public Application getApplicationFrame() {
        return this.applicationFrame;
    }

    // Get buttons
    public Button[] getButtons() {
        return this.buttons;
    }

    // Get parentScreen
    public ScreenAccounts getParentScreen() {
        return this.parentScreen;
    }

    // Get mainScreen
    public JPanel getMainScreen() {
        return this.mainScreen;
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    // Handler buttons
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // Press at "Create Accounts" in mainScreen
            if (event.getSource() == buttons[0]) {
                if (fieldName.getText().isEmpty() ||
                        (fieldName.getText().equals(fieldName.getDefaultText())
                                && fieldName.getForeground() == Setting.COLOR_GRAY_03)) {
                    JOptionPane.showMessageDialog(applicationFrame, "Bạn chưa nhập Họ và tên",
                            "Invalid input",
                            JOptionPane.WARNING_MESSAGE);
                } else if (fieldUsername.getText().isEmpty() ||
                        (fieldUsername.getText().equals(fieldUsername.getDefaultText())
                                && fieldUsername.getForeground() == Setting.COLOR_GRAY_03)) {
                    JOptionPane.showMessageDialog(applicationFrame, "Bạn chưa nhập Tên đăng nhập",
                            "Invalid input",
                            JOptionPane.WARNING_MESSAGE);
                } else if (ReadFile.isExistingUsername(fieldUsername.getText())) {
                    JOptionPane.showMessageDialog(applicationFrame, "Tên đăng nhập đã được sử dụng",
                            "Invalid input",
                            JOptionPane.WARNING_MESSAGE);
                } else if (fieldPassword.getText().isEmpty() ||
                        (fieldPassword.getText().equals(fieldPassword.getDefaultText())
                                && fieldPassword.getForeground() == Setting.COLOR_GRAY_03)) {
                    JOptionPane.showMessageDialog(applicationFrame, "Bạn chưa thiết lập mật khẩu",
                            "Invalid input",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    WriteFile.createNewAccount(new Account(
                            fieldName.getText(), fieldUsername.getText(), fieldPassword.getText()));
                    WriteFile.writeStringToFile(ReadFile.PATH_DATA_TEMP_1, fieldUsername.getText(), false);
                    JOptionPane.showMessageDialog(applicationFrame,
                            "Tạo tài khoản thành công.\nTài khoản hiện tại đang sử dụng: " + fieldName.getText(),
                            "Create account successed",
                            JOptionPane.INFORMATION_MESSAGE);
                    // Set default text
                    fieldName.setText("Họ và tên");
                    fieldName.setForeground(Setting.COLOR_GRAY_03);
                    fieldUsername.setText("Tên đăng nhập");
                    fieldUsername.setForeground(Setting.COLOR_GRAY_03);
                    fieldPassword.setText("Mật khẩu");
                    fieldPassword.setForeground(Setting.COLOR_GRAY_03);
                    // Return parent screen
                    getParentScreen().getMainScreen().setVisible(true);
                    getParentScreen().getScreenCreateNewAccount().setVisible(false);
                }
            }
            // Press at "Cancer" in mainScreen
            else if (event.getSource() == buttons[1]) {
                getParentScreen().getMainScreen().setVisible(true);
                getParentScreen().getScreenCreateNewAccount().setVisible(false);
            }
        }
    }

}
