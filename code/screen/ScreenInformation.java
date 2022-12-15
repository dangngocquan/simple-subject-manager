package code.screen;

import javax.swing.JPanel;
import code.Application;
import code.objects.Button;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ScreenInformation extends JPanel {
    // Properties, Objects and Screens
    private Application applicationFrame;
    private String[] buttonTexts = {
            "Khung chương trình đào tạo", "Quay lại"
    };
    private Button[] buttons;
    private ScreenMainMenu parentScreen;
    private JPanel mainScreen;
    private ScreenCurriculumInformation screenCurriculumInformation;

    // Constructor
    public ScreenInformation(int width, int height, ScreenMainMenu parentScreen, Application frame) {
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
        screenCurriculumInformation = new ScreenCurriculumInformation(width, height, this, applicationFrame);

        // Create buttons
        buttons = new Button[buttonTexts.length];
        for (int count = 0; count < buttonTexts.length; count++) {
            buttons[count] = new Button(buttonTexts[count]);
            buttons[count].setFontText(Button.ARIAL_BOLD_28);
            buttons[count].setCorrectSizeButton();
            buttons[count].addMouseListener(new MouseHandler());
        }

        // Set location for each button
        buttons[0].setLocationButton(width / 2, height / 12 * 5, Button.TOP_CENTER);
        buttons[1].setLocationButton(width / 2, height / 12 * 7, Button.TOP_CENTER);

        // Add buttons to mainScreen
        for (Button button : buttons) {
            mainScreen.add(button);
        }

        // Add screen to this panel
        add(mainScreen);
        add(screenCurriculumInformation);

        // Set visible of screens
        mainScreen.setVisible(true);
        screenCurriculumInformation.setVisible(false);
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

    // Get screenCurriculumInformation
    public ScreenCurriculumInformation getScreenCurriculumInformation() {
        return this.screenCurriculumInformation;
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
            // Press at "Curriculums" in mainScreen
            if (event.getSource() == buttons[0]) {
                getScreenCurriculumInformation().setVisible(true);
                getMainScreen().setVisible(false);
            }
            // Press at "Back" in mainScreen
            else if (event.getSource() == buttons[1]) {
                getParentScreen().getMainScreen().setVisible(true);
                getParentScreen().getScreenInformation().setVisible(false);
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
