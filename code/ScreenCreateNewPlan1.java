package code;

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScreenCreateNewPlan1 extends JPanel {
    // Properties, Objects and Screens
    private String[] buttonTexts = {
            "Quay lại", "Tiếp theo"
    };
    private Button[] buttons;
    private ScreenPlans parentScreen;
    private JPanel mainScreen;

    // Constructor
    public ScreenCreateNewPlan1(int width, int height, ScreenPlans parentScreen) {
        // Set basic properties
        setLayout(null);
        setSize(width, height);
        setBounds(0, 0, width, height);
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

        // Set location for each button
        buttons[0].setLocation(width / 16, height / 8 * 7, Button.BOTTOM_LEFT);
        buttons[1].setLocation(width / 16 * 15, height / 8 * 7, Button.BOTTOM_RIGHT);

        // Add buttons to mainScreen
        for (Button button : buttons) {
            mainScreen.add(button);
        }

        // Add screens to this panel
        add(mainScreen);

        // Set visible of screens
        mainScreen.setVisible(true);
    }

    // Get buttons in mainScreen
    public Button[] getButtons() {
        return this.buttons;
    }

    // Get parent screen of this panel
    public ScreenPlans getParentScreen() {
        return this.parentScreen;
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    // Handler buttons
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // Press at "Back" in mainScreen
            if (event.getSource() == buttons[0]) {
                getParentScreen().getMainScreen().setVisible(true);
                getParentScreen().getScreenCreateNewPlan1().setVisible(false);
            }
            // Press at "Next" in mainScreen
            else if (event.getSource() == buttons[1]) {

            }
        }
    }
}
