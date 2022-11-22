package code.screen;

import javax.swing.JPanel;

import code.Application;
import code.Setting;
import code.button.Button;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScreenMainMenu extends JPanel {
    // Propertie, Objects and Screens
    private Application applicationFrame;
    private String[] buttonTexts = {
            "Các kế hoạch", "Tài khoản", "Thông tin"
    };
    private Button[] buttons;
    private JPanel mainScreen;
    private ScreenPlans screenPlans;
    private ScreenInformation screenInformation;

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
        screenPlans = new ScreenPlans(width, height, this, applicationFrame);
        screenInformation = new ScreenInformation(width, height, this, applicationFrame);

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

        // Set location for each button
        buttons[0].setLocation(width / 2, height / 12 * 3, Button.TOP_CENTER);
        buttons[1].setLocation(width / 2, height / 12 * 5, Button.TOP_CENTER);
        buttons[2].setLocation(width / 2, height / 12 * 7, Button.TOP_CENTER);

        // Add buttons to mainScreen
        for (Button button : buttons) {
            mainScreen.add(button);
        }

        // Add screens to this screen
        add(mainScreen);
        add(screenPlans);
        add(screenInformation);

        // Set visible of screens
        mainScreen.setVisible(true);
        screenPlans.setVisible(false);
        screenInformation.setVisible(false);
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

    // Get JPanel screenInformation
    public ScreenInformation getScreenInformation() {
        return this.screenInformation;
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    // Handler when press at button
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // Press "Plans" button on "screenMainMenu" screen
            if (event.getSource() == getButtons()[0]) {
                getScreenPlans().setVisible(true);
                getMainScreen().setVisible(false);
            }
            // Press "Information" button on "screenMainMenu" screen
            else if (event.getSource() == getButtons()[2]) {
                getScreenInformation().setVisible(true);
                getMainScreen().setVisible(false);
            }
        }
    }

}
