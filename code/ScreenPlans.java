package code;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScreenPlans extends JPanel {
    // Properties, Objects and Screens
    private String[] buttonTexts = {
            "Các kế hoạch hiện có", "Tạo kế hoạch mới", "Quay lại"
    };
    private Button[] buttons;
    private ScreenMainMenu parentScreen;
    private JPanel mainScreen;
    private ScreenCreateNewPlan1 screenCreateNewPlan1;

    // Constructor
    public ScreenPlans(int width, int height, ScreenMainMenu parentScreen) {
        // Set basic properties
        setLayout(null);
        setBounds(0, 0, width, height);
        setSize(width, height);
        this.parentScreen = parentScreen;

        // Create screens
        mainScreen = new JPanel();
        mainScreen.setLayout(null);
        mainScreen.setSize(width, height);
        mainScreen.setBounds(0, 0, width, height);
        screenCreateNewPlan1 = new ScreenCreateNewPlan1(width, height, this);

        // Create buttons
        buttons = new Button[buttonTexts.length];
        for (int count = 0; count < buttonTexts.length; count++) {
            buttons[count] = new Button(buttonTexts[count]);
            buttons[count].setFont(
                    Setting.FONT_NAME_01,
                    Setting.FONT_STYLE_01,
                    Setting.FONT_SIZE_MEDIUM);
            buttons[count].addActionListener(new ButtonHandle());
        }

        // Set location for each button
        buttons[0].setLocation(width / 2, height / 12 * 3, Button.TOP_CENTER);
        buttons[1].setLocation(width / 2, height / 12 * 5, Button.TOP_CENTER);
        buttons[2].setLocation(width / 2, height / 12 * 7, Button.TOP_CENTER);

        // Add buttons to mainScreen
        for (Button button : buttons) {
            mainScreen.add(button);
        }

        // Add screens to this panel
        add(mainScreen);
        add(screenCreateNewPlan1);

        // Set visible screens
        mainScreen.setVisible(true);
        screenCreateNewPlan1.setVisible(false);
    }

    // Get buttons
    public Button[] getButtons() {
        return this.buttons;
    }

    // Get parent screen
    public ScreenMainMenu getParentScreen() {
        return this.parentScreen;
    }

    // Get mainScreen
    public JPanel getMainScreen() {
        return this.mainScreen;
    }

    // Get ScreenCreateNewPlan1
    public ScreenCreateNewPlan1 getScreenCreateNewPlan1() {
        return this.screenCreateNewPlan1;
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    // Handler buttons in mainScreen
    private class ButtonHandle implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // Press at "Existing plans" button
            if (event.getSource() == buttons[0]) {

            }
            // Press at "Create new plan" button
            else if (event.getSource() == buttons[1]) {
                getScreenCreateNewPlan1().setVisible(true);
                getMainScreen().setVisible(false);
            }
            // Press at "Back" button
            else if (event.getSource() == buttons[2]) {
                getParentScreen().getMainScreen().setVisible(true);
                getParentScreen().getScreenPlans().setVisible(false);
            }
        }
    }
}
