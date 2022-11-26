package code.screen;

import javax.swing.JPanel;
import code.Application;
import code.Setting;
import code.file_handler.ReadFile;
import code.objects.Account;
import code.objects.Button;
import code.panel.PanelAccount;
import code.panel.PanelAccountList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;

public class ScreenExistingAccounts extends JPanel {
    // Properties
    // private Application applicationFrame;
    private ScreenAccounts parentScreen;
    private String[] buttonTexts = {
            "Quay lại"
    };
    private Button[] buttons;
    private JPanel panelListAccount, panelAccountInfor;
    private JPanel panelButton1, panelButton2;
    private JPanel panelGroup1, panelGroup2;
    private Account accountShowing = ReadFile.getCurrentAccount();

    public ScreenExistingAccounts(int width, int height, ScreenAccounts parentScreen, Application frame) {
        // Set basic properties
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

        panelListAccount = new JPanel();
        panelListAccount.setLayout(null);
        panelListAccount.setSize(panelGroup1.getWidth(), height - panelButton1.getHeight());
        panelListAccount.setBounds(0, 0, panelListAccount.getWidth(), panelListAccount.getHeight());

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
        panelListAccount.add(new PanelAccountList(0, 0, panelListAccount.getWidth(), panelListAccount.getHeight(),
                ReadFile.getAccounts(), this));

        // Create buttons
        buttons = new Button[buttonTexts.length];
        for (int count = 0; count < buttonTexts.length; count++) {
            buttons[count] = new Button(buttonTexts[count]);
            buttons[count].setFont(
                    Setting.FONT_NAME_01,
                    Setting.FONT_STYLE_01,
                    Setting.FONT_SIZE_SMALL);
            buttons[count].addActionListener(new ButtonHandler());
        }

        // Set location of buttons
        buttons[0].setLocation(panelButton1.getWidth() / 2, panelButton1.getHeight() / 2, Button.CENTER_CENTER);
        panelButton1.add(buttons[0]);

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
        panelListAccount.removeAll();
        panelListAccount.add(new PanelAccountList(0, 0, panelListAccount.getWidth(), panelListAccount.getHeight(),
                ReadFile.getAccounts(), this));
        repaint();
    }

    public void updatePanelAccountInfor(Account account) {
        panelAccountInfor.removeAll();
        panelAccountInfor.add(new PanelAccount(0, 0, panelAccountInfor.getWidth(),
                panelAccountInfor.getHeight(), account));
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    // Handler when press at button
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // Press "Back" button on "panelButton1" screen
            if (event.getSource() == getButtons()[0]) {
                getParentScreen().getMainScreen().setVisible(true);
                getParentScreen().getScreenExistingAccounts().setVisible(false);
            }
            // // Press "Accounts" button on "screenMainMenu" screen
            // else if (event.getSource() == getButtons()[1]) {

            // }
            // // Press "Information" button on "screenMainMenu" screen
            // else if (event.getSource() == getButtons()[2]) {

            // }
        }
    }
}
