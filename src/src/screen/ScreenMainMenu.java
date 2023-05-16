package src.screen;

import javax.swing.JPanel;
import src.animation.AnimationPanel;
import src.launcher.Application;
import src.launcher.ReadFile;
import src.objects.Account;
import src.objects.Button;
import src.panel.PanelString;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class ScreenMainMenu extends JPanel {
    // Propertie, Objects and Screens
    private Application applicationFrame;
    private String[] buttonTexts = {
            "Các kế hoạch", "Tài khoản", "Thông tin"
    };
    private Button[] buttons;
    private JPanel mainScreen;
    private PanelString descriptionPanel;
    private ScreenPlans screenPlans;
    private ScreenInformation screenInformation;
    private ScreenAccounts screenAccounts;

    // Constructor
    public ScreenMainMenu(int width, int height, Application frame) {
        // Set basic properties for this screen
        this.applicationFrame = frame;
        setLayout(null);
        setBounds(0, 0, width, height);
        setSize(width, height);

        // Create screens
        mainScreen = new JPanel();
        mainScreen.setLayout(null);
        mainScreen.setSize(width, height);
        mainScreen.setBounds(0, 0, mainScreen.getWidth(), mainScreen.getHeight());
        // mainScreen.addMouseMotionListener(new MouseMotionHandler());

        // Create description (Who is using app?)
        descriptionPanel = new PanelString(getWidth() / 2, getHeight() / 12 * 2,
                "", getWidth(),
                null, PanelString.BOTTOM_CENTER, 0);
        mainScreen.add(descriptionPanel);
        updateDescriptionPanel();

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

        // Add screens to this screen
        add(mainScreen);

        // Set visible of screens
        mainScreen.setVisible(true);
    }

    // Get application frame
    public Application getApplicationFrame() {
        return this.applicationFrame;
    }

    // Get buttons
    public Button[] getButtons() {
        return this.buttons;
    }

    // Get mainScreen
    public JPanel getMainScreen() {
        return this.mainScreen;
    }

    // Get JPanel screenPlans
    public ScreenPlans getScreenPlans() {
        return this.screenPlans;
    }

    // Get Screen Accounts
    public ScreenAccounts getScreenAccounts() {
        return this.screenAccounts;
    }

    // Get JPanel screenInformation
    public ScreenInformation getScreenInformation() {
        return this.screenInformation;
    }

    // Update description
    public void updateDescriptionPanel() {
        mainScreen.remove(descriptionPanel);
        String currentName = "";
        Account account = ReadFile.getCurrentAccount();
        if (account != null) {
            currentName = "Xin chào, " + account.getName();
        }
        this.descriptionPanel = new PanelString(getWidth() / 2, getHeight() / 12 * 2,
                currentName, getWidth(),
                null, PanelString.BOTTOM_CENTER, 0);
        mainScreen.add(descriptionPanel);

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
            // Press "Plans" button on "screenMainMenu" screen
            if (event.getSource() == getButtons()[0]) {
                if (screenPlans == null) {
                    screenPlans = new ScreenPlans(mainScreen.getWidth(), mainScreen.getHeight(), ScreenMainMenu.this,
                            applicationFrame);
                    ScreenMainMenu.this.add(screenPlans);
                }
                getScreenPlans().setVisible(true);
                getMainScreen().setVisible(false);
                AnimationPanel animation = new AnimationPanel(getScreenPlans(),
                        getScreenPlans().getWidth(), 0, 0, 0,
                        300);
                animation.start();
            }
            // Press "Accounts" button on "screenMainMenu" screen
            else if (event.getSource() == getButtons()[1]) {
                if (screenAccounts == null) {
                    screenAccounts = new ScreenAccounts(mainScreen.getWidth(), mainScreen.getHeight(),
                            ScreenMainMenu.this, applicationFrame);
                    ScreenMainMenu.this.add(screenAccounts);
                }
                getScreenAccounts().setVisible(true);
                getMainScreen().setVisible(false);
                AnimationPanel animation = new AnimationPanel(getScreenAccounts(),
                        getScreenAccounts().getWidth(), 0, 0, 0,
                        300);
                animation.start();
            }
            // Press "Information" button on "screenMainMenu" screen
            else if (event.getSource() == getButtons()[2]) {
                if (screenInformation == null) {
                    screenInformation = new ScreenInformation(mainScreen.getWidth(), mainScreen.getHeight(),
                            ScreenMainMenu.this, applicationFrame);
                    ScreenMainMenu.this.add(screenInformation);
                }
                getScreenInformation().setVisible(true);
                getMainScreen().setVisible(false);
                AnimationPanel animation = new AnimationPanel(getScreenInformation(),
                        getScreenInformation().getWidth(), 0, 0, 0,
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
