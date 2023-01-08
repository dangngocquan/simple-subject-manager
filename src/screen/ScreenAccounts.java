package src.screen;

import javax.swing.JPanel;

import src.animation.AnimationPanel;
import src.file_handler.ReadFile;
import src.launcher.Application;
import src.objects.Button;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ScreenAccounts extends JPanel {
    // Properties, Objects and Screens
    private Application applicationFrame;
    private String[] buttonTexts = {
            "Các tài khoản hiện có", "Tạo tài khoản mới", "Quay lại"
    };
    private Button[] buttons;
    private ScreenMainMenu parentScreen;
    private JPanel mainScreen;
    private ScreenExistingAccounts screenExistingAccounts;
    private ScreenCreateNewAccount screenCreateNewAccount;

    // Constructor
    public ScreenAccounts(int width, int height, ScreenMainMenu parentScreen, Application frame) {
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
        screenExistingAccounts = new ScreenExistingAccounts(width, height,
                this, applicationFrame);
        screenCreateNewAccount = new ScreenCreateNewAccount(width, height,
                this, applicationFrame);

        // Create buttons
        buttons = new Button[buttonTexts.length];
        for (int count = 0; count < buttonTexts.length; count++) {
            buttons[count] = new Button(buttonTexts[count]);
            buttons[count].setFontText(Button.ARIAL_BOLD_24);
            buttons[count].setCorrectSizeButton();
            buttons[count].addMouseListener(new MouseHandler());
        }

        // Set location for each button
        buttons[0].setLocationButton(width / 2, height / 12 * 3, Button.TOP_CENTER);
        buttons[1].setLocationButton(width / 2, height / 12 * 5, Button.TOP_CENTER);
        buttons[2].setLocationButton(width / 2, height / 12 * 7, Button.TOP_CENTER);

        // Add buttons to mainScreen
        for (Button button : buttons) {
            mainScreen.add(button);
        }

        // Add screen to this panel
        add(mainScreen);
        add(screenCreateNewAccount);
        add(screenExistingAccounts);

        // Set visible of screens
        mainScreen.setVisible(true);
        screenCreateNewAccount.setVisible(false);
        screenExistingAccounts.setVisible(false);
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
    public ScreenMainMenu getParentScreen() {
        return this.parentScreen;
    }

    // Get Screen create new account
    public ScreenCreateNewAccount getScreenCreateNewAccount() {
        return this.screenCreateNewAccount;
    }

    // Get mainScreen
    public JPanel getMainScreen() {
        return this.mainScreen;
    }

    // Get Screen Exisitng accounts
    public ScreenExistingAccounts getScreenExistingAccounts() {
        return this.screenExistingAccounts;
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            // Press at "Existing Accounts" in mainScreen
            if (event.getSource() == buttons[0]) {
                screenExistingAccounts.setVisible(true);
                screenExistingAccounts.updatePanelListAccount();
                screenExistingAccounts.updatePanelAccountInfor(ReadFile.getCurrentAccount());
                getMainScreen().setVisible(false);
                AnimationPanel animation = new AnimationPanel(screenExistingAccounts,
                        screenExistingAccounts.getWidth(), 0, 0, 0,
                        300);
                animation.start();
            }
            // Press at "Create New Account" in mainScreen
            else if (event.getSource() == buttons[1]) {
                getScreenCreateNewAccount().setVisible(true);
                getMainScreen().setVisible(false);
                AnimationPanel animation = new AnimationPanel(getScreenCreateNewAccount(),
                        getScreenCreateNewAccount().getWidth(), 0, 0, 0,
                        300);
                animation.start();
            }
            // Press at "Back" in mainScreen
            else if (event.getSource() == buttons[2]) {
                getParentScreen().getMainScreen().setVisible(true);
                getParentScreen().updateDescriptionPanel();
                getParentScreen().getScreenAccounts().setVisible(false);
                AnimationPanel animation = new AnimationPanel(getParentScreen().getMainScreen(),
                        -getParentScreen().getMainScreen().getWidth(), 0, 0, 0,
                        300);
                animation.start();
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
