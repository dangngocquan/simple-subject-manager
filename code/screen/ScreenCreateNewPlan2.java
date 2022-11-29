package code.screen;

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Font;
import code.Application;
import code.Setting;
import code.objects.Button;
import code.objects.Major;
import code.panel.PanelMajor;
import code.panel.PanelMajorHasOptions;
import code.panel.PanelString;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScreenCreateNewPlan2 extends JPanel {
    // Properties, Objects and Screens
    private Application applicationFrame;
    private String[] buttonTexts = {
            "Quay lại", "Tiếp theo"
    };
    private Button[] buttons;
    private ScreenCreateNewPlan1 parentScreen;
    private JPanel mainScreen, contentPanel;
    private Major major;

    // Constructor
    public ScreenCreateNewPlan2(int width, int height, ScreenCreateNewPlan1 parentScreen, Application frame,
            Major major) {
        // Set basic properties
        this.applicationFrame = frame;
        this.major = major;
        setLayout(null);
        setSize(width, height);
        setBounds(0, 0, width, height);
        this.parentScreen = parentScreen;

        // Create screens
        mainScreen = new JPanel();
        mainScreen.setLayout(null);
        mainScreen.setSize(width, height);
        mainScreen.setBounds(0, 0, width, height);

        PanelString title = new PanelString(width / 2, height / 64 * 3,
                "Tiếp theo, hãy chọn những môn học mà bạn dự định học trong ngành này", width,
                new Font(Setting.FONT_NAME_01,
                        Setting.FONT_STYLE_01,
                        Setting.FONT_SIZE_MEDIUM),
                PanelString.CENTER_CENTER, 0);

        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setSize(width / 20 * 19, height / 8 * 6);
        contentPanel.setBounds(width / 40, height / 32 * 3, contentPanel.getWidth(), contentPanel.getHeight());

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

        // Update content
        updateContentPanel();

        // Set location for each button
        buttons[0].setLocation(width / 16, height / 32 * 31, Button.BOTTOM_LEFT);
        buttons[1].setLocation(width / 16 * 15, height / 32 * 31, Button.BOTTOM_RIGHT);

        // Add buttons to mainScreen
        for (Button button : buttons) {
            mainScreen.add(button);
        }

        // Add subpanels
        mainScreen.add(title);
        mainScreen.add(contentPanel);

        contentPanel.setBackground(Setting.COLOR_GREEN_02);

        // Add screens to this panel
        add(mainScreen);

        // Set visible of screens
        mainScreen.setVisible(true);
    }

    // Get application frame
    public Application getApplicationFrame() {
        return this.applicationFrame;
    }

    // Get buttons in mainScreen
    public Button[] getButtons() {
        return this.buttons;
    }

    // Get parent screen of this panel
    public ScreenCreateNewPlan1 getParentScreen() {
        return this.parentScreen;
    }

    // Update content panels
    public void updateContentPanel() {
        contentPanel.removeAll();
        contentPanel.add(new PanelMajorHasOptions(0, 0, contentPanel.getWidth(), contentPanel.getHeight(),
                major, PanelMajor.TOP_LEFT));
        repaint();
    }

    // Set major
    public void setMajor(Major major) {
        this.major = major;
        updateContentPanel();
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
                getParentScreen().getScreenCreateNewPlan2().setVisible(false);

            }
            // Press at "Next" in mainScreen
            else if (event.getSource() == buttons[1]) {

            }
        }
    }

}
