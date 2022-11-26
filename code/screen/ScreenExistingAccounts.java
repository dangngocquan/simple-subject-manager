package code.screen;

import javax.swing.JPanel;

import code.Application;
import code.Setting;
import code.file_handler.ReadFile;
import code.objects.Account;
import code.objects.Button;
import code.panel.PanelAccount;
import java.awt.Graphics;

public class ScreenExistingAccounts extends JPanel {
    // Properties
    private Application applicationFrame;
    private ScreenAccounts parentScreen;
    private String[] buttonTexts = {
            "Quay lại"
    };
    private Button[] buttons;
    private JPanel panelListAccount, panelAccountInfor;
    private JPanel panelButton1, panelButton2;
    private JPanel panelGroup1, panelGroup2;

    public ScreenExistingAccounts(int width, int height, ScreenAccounts parentScreen, Application frame) {
        // Set basic properties
        this.parentScreen = parentScreen;
        this.applicationFrame = frame;
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

        panelAccountInfor.add(new PanelAccount(0, 0, panelAccountInfor.getWidth(),
                panelAccountInfor.getHeight(), ReadFile.getCurrentAccount()));

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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
