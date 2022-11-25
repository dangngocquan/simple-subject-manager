package code.screen;

import javax.swing.JPanel;
import code.Application;
import code.Setting;
import code.objects.Button;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScreenAccounts extends JPanel {
    // Properties, Objects and Screens
    private Application applicationFrame;
    private String[] buttonTexts = {
            "Các tài khoản hiện có", "Tạo tài khoản mới", "Quay lại"
    };
    private Button[] buttons;
    private ScreenMainMenu parentScreen;
    private JPanel mainScreen;
    // private ScreenCurriculumInformation screenCurriculumInformation;

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
        // screenCurriculumInformation = new ScreenCurriculumInformation(width, height,
        // this, applicationFrame);

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
    public ScreenMainMenu getParentScreen() {
        return this.parentScreen;
    }

    // Get mainScreen
    public JPanel getMainScreen() {
        return this.mainScreen;
    }

    // // Get screenCurriculumInformation
    // public ScreenCurriculumInformation getScreenCurriculumInformation() {
    // return this.screenCurriculumInformation;
    // }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    // Handler buttons
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // Press at "Existing Accounts" in mainScreen
            if (event.getSource() == buttons[0]) {
                // getScreenCurriculumInformation().setVisible(true);
                getMainScreen().setVisible(false);
            }
            // Press at "Create New Account" in mainScreen
            else if (event.getSource() == buttons[1]) {
                // getParentScreen().getMainScreen().setVisible(true);
                getMainScreen().setVisible(false);
            }
            // Press at "Back" in mainScreen
            else if (event.getSource() == buttons[2]) {
                getParentScreen().getMainScreen().setVisible(true);
                getParentScreen().getScreenAccounts().setVisible(false);
            }
        }
    }
}
