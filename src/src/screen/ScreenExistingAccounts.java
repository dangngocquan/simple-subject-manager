package src.screen;

import javax.swing.JPanel;

import src.animation.AnimationPanel;
import src.dialog.DialogChangePassword;
import src.dialog.DialogInput;
import src.dialog.DialogLoginAccount;
import src.dialog.DialogMessage;
import src.launcher.Application;
import src.launcher.ReadFile;
import src.launcher.Setting;
import src.launcher.WriteFile;
import src.objects.Account;
import src.objects.Button;
import src.panel.PanelAccount;
import src.panel.PanelAccountList;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Graphics;

public class ScreenExistingAccounts extends JPanel {
    // Properties
    // private Application applicationFrame;
    private int width, height;
    private ScreenAccounts parentScreen;
    private String[] buttonTexts = {
            "Quay lại", "Sử dụng", "Xóa", "Đổi mật khẩu"
    };
    private Button[] buttons;
    private PanelAccountList panelListAccount;
    private JPanel panelAccountInfor;
    private JPanel panelButton1, panelButton2;
    private JPanel panelGroup1, panelGroup2;
    private Account accountShowing = ReadFile.getCurrentAccount();

    public ScreenExistingAccounts(int width, int height, ScreenAccounts parentScreen, Application frame) {
        // Set basic properties
        this.width = width;
        this.height = height;
        this.parentScreen = parentScreen;
        // this.applicationFrame = frame;
        setLayout(null);
        setSize(width, height);
        setBounds(0, 0, width, height);

        // Create panels
        panelGroup1 = new JPanel();
        panelGroup1.setLayout(null);
        panelGroup1.setSize(width / 8 * 3, height);
        panelGroup1.setBounds(0, 0, panelGroup1.getWidth(), panelGroup1.getHeight());

        panelGroup2 = new JPanel();
        panelGroup2.setLayout(null);
        panelGroup2.setSize(width - panelGroup1.getWidth(), height);
        panelGroup2.setBounds(panelGroup1.getWidth(), 0, panelGroup2.getWidth(), panelGroup2.getHeight());

        panelButton1 = new JPanel();
        panelButton1.setLayout(null);
        panelButton1.setSize(panelGroup1.getWidth(), panelGroup1.getHeight() / 10);
        panelButton1.setBounds(0, panelGroup1.getHeight() - panelButton1.getHeight(),
                panelButton1.getWidth(), panelButton1.getHeight());

        panelListAccount = new PanelAccountList(0, 0, panelGroup1.getWidth(), height - panelButton1.getHeight(),
                ReadFile.getAccounts(), this);

        panelButton2 = new JPanel();
        panelButton2.setLayout(null);
        panelButton2.setSize(panelGroup2.getWidth(), panelGroup2.getHeight() / 10);
        panelButton2.setBounds(0, height -
                panelButton2.getHeight(), panelButton2.getWidth(),
                panelButton2.getHeight());

        panelAccountInfor = new JPanel();
        panelAccountInfor.setLayout(null);
        panelAccountInfor.setSize(panelGroup2.getWidth(), panelGroup2.getHeight() - panelButton2.getHeight());
        panelAccountInfor.setBounds(0, 0, panelAccountInfor.getWidth(),
                panelAccountInfor.getHeight());

        // Add information to panels
        panelAccountInfor.add(new PanelAccount(0, 0, panelAccountInfor.getWidth(),
                panelAccountInfor.getHeight(), accountShowing));

        // Create buttons
        buttons = new Button[buttonTexts.length];
        for (int count = 0; count < buttonTexts.length; count++) {
            buttons[count] = new Button(buttonTexts[count]);
            buttons[count].setFontText(Button.ARIAL_BOLD_24);
            buttons[count].setCorrectSizeButton();
            buttons[count].addMouseListener(new MouseHandler());
        }

        // Set location of buttons
        buttons[0].setLocationButton(panelButton1.getWidth() / 2, panelButton1.getHeight() / 2, Button.CENTER_CENTER);
        panelButton1.add(buttons[0]);
        buttons[1].setLocationButton(panelButton2.getWidth() / 2, panelButton2.getHeight() / 2, Button.CENTER_CENTER);
        panelButton2.add(buttons[1]);
        buttons[2].setLocationButton(panelButton2.getWidth() / 4 * 3, panelButton2.getHeight() / 2,
                Button.CENTER_CENTER);
        panelButton2.add(buttons[2]);
        buttons[3].setLocationButton(panelButton2.getWidth() / 4, panelButton2.getHeight() / 2, Button.CENTER_CENTER);
        panelButton2.add(buttons[3]);

        // add panels
        panelGroup1.add(panelListAccount);
        panelGroup1.add(panelButton1);
        panelGroup2.add(panelButton2);
        panelGroup2.add(panelAccountInfor);

        panelButton1.setBackground(Setting.COLOR_ORANGE_02);
        panelListAccount.setBackground(Setting.COLOR_ORANGE_02);
        panelAccountInfor.setBackground(Setting.COLOR_GRAY_04);
        panelButton2.setBackground(Setting.COLOR_GRAY_04);

        add(panelGroup1);
        add(panelGroup2);
    }

    // Get buttons
    public Button[] getButtons() {
        return this.buttons;
    }

    // Get parent screen of this panel
    public ScreenAccounts getParentScreen() {
        return this.parentScreen;
    }

    // Update the lastest account list
    public void updatePanelListAccount() {
        panelGroup1.remove(panelListAccount);
        panelListAccount = new PanelAccountList(0, 0, panelListAccount.getWidth(), panelListAccount.getHeight(),
                ReadFile.getAccounts(), this);
        panelGroup1.add(panelListAccount);
        repaint();
    }

    public void updateDataListAccount() {
        panelListAccount.updateContentPanel(ReadFile.getAccounts());
    }

    public void updatePanelAccountInfor(Account account) {
        panelAccountInfor.removeAll();
        this.accountShowing = account;
        panelAccountInfor.add(new PanelAccount(0, 0, panelAccountInfor.getWidth(),
                panelAccountInfor.getHeight(), account));
        if (accountShowing.getUsername().equals(ReadFile.getCurrentAccount().getUsername())) {
            buttons[1].setVisible(false);
            buttons[2].setVisible(true);
            buttons[3].setVisible(true);
        } else {
            buttons[1].setVisible(true);
            buttons[2].setVisible(false);
            buttons[3].setVisible(false);
        }
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            // Press "Back" button on "panelButton1"
            if (event.getSource() == getButtons()[0]) {
                getParentScreen().getMainScreen().setVisible(true);
                getParentScreen().getScreenExistingAccounts().setVisible(false);
                AnimationPanel animation = new AnimationPanel(getParentScreen().getMainScreen(),
                        -getParentScreen().getMainScreen().getWidth(), 0, 0, 0,
                        300);
                animation.start();
            }
            // Press "Use" button on "panelbutton2"
            else if (event.getSource() == getButtons()[1]) {
                DialogInput inputs = new DialogInput(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 2,
                        Setting.HEIGHT / 3,
                        DialogInput.CENTER_CENTER, "Get password",
                        new String[] { "Bạn cần nhập mật khẩu của tài khoản này" }, Setting.INFORMATION,
                        new String[] { "Mật khẩu" });
                String passwordInput = inputs.getInputString()[0];
                if (passwordInput != null) {
                    if (passwordInput.equals(accountShowing.getPassword())) {
                        WriteFile.writeStringToFile(ReadFile.PATH_DATA_TEMP_1, accountShowing.getUsername(), false);
                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                DialogMessage.CENTER_CENTER,
                                "Information", new String[] { "Đăng nhập tài khoản thành công." }, Setting.INFORMATION);
                        updatePanelAccountInfor(accountShowing);
                    } else {
                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                DialogMessage.CENTER_CENTER,
                                "Information", new String[] { "Mật khẩu không chính xác." }, Setting.WARNING);
                    }
                }

            }
            // Press "Change password" button on "panelButton2"
            else if (event.getSource() == getButtons()[3]) {
                String[] messageLines = {};
                new DialogChangePassword(width / 2, height / 2, width / 2, height / 2,
                        DialogChangePassword.CENTER_CENTER,
                        "Change password", messageLines, accountShowing);
                updateDataListAccount();
                accountShowing = ReadFile.getCurrentAccount();
            }
            // Press "Delete" button on "panelButton2"
            else if (event.getSource() == getButtons()[2]) {
                String[] messageLines = {
                        "Trước khi xóa tài khoản này, bạn cần đăng nhập một tài khoản khác.",
                };
                new DialogLoginAccount(width / 2, height / 2, width / 2, height / 2,
                        DialogChangePassword.CENTER_CENTER,
                        "Login account", messageLines, accountShowing);
                if (!accountShowing.getUsername().equals(ReadFile.getCurrentAccount().getUsername())) {
                    WriteFile.removeAccount(accountShowing);
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER,
                            "Information", new String[] {
                                    String.format("Tài khoản \"%s\" đã được xóa thành công.", accountShowing.getName()),
                                    String.format("Hiện tại bạn đang ở tài khoản \"%s\".",
                                            ReadFile.getCurrentAccount().getName()) },
                            Setting.INFORMATION);
                    accountShowing = ReadFile.getCurrentAccount();
                    updateDataListAccount();
                    updatePanelListAccount();
                    updatePanelAccountInfor(accountShowing);
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
