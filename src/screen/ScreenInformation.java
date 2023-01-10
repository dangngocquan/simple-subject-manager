package src.screen;

import javax.swing.JPanel;

import src.animation.AnimationPanel;
import src.launcher.Application;
import src.objects.Button;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ScreenInformation extends JPanel {
    // Properties, Objects and Screens
    private Application applicationFrame;
    private String[] buttonTexts = {
            "Khung chương trình đào tạo", "Quay lại", "Bảng quy đổi điểm"
    };
    private Button[] buttons;
    private ScreenMainMenu parentScreen;
    private JPanel mainScreen;
    private ScreenCurriculumInformation screenCurriculumInformation;
    private ScreenConvertionInformation screenConvertionInformation;

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
        buttons[1].setLocationButton(width / 2, height / 12 * 7, Button.TOP_CENTER);
        buttons[2].setLocationButton(width / 2, height / 12 * 5, Button.TOP_CENTER);

        // Add buttons to mainScreen
        for (Button button : buttons) {
            mainScreen.add(button);
        }

        // Add screen to this panel
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

    // Get screenConvetionInformation
    public ScreenConvertionInformation getScreenConvertionInformation() {
        return this.screenConvertionInformation;
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
                getMainScreen().setVisible(false);
                if (screenCurriculumInformation == null) {
                    screenCurriculumInformation = new ScreenCurriculumInformation(mainScreen.getWidth(),
                            mainScreen.getHeight(), ScreenInformation.this, applicationFrame);
                    ScreenInformation.this.add(screenCurriculumInformation);
                } else {
                    screenCurriculumInformation.setVisible(true);
                }
                if (screenConvertionInformation != null) {
                    screenConvertionInformation.setVisible(false);
                }
                AnimationPanel animation = new AnimationPanel(getScreenCurriculumInformation(),
                        getScreenCurriculumInformation().getWidth(), 0, 0, 0,
                        300);
                animation.start();
            }
            // Press at "Convertion" in mainScreen
            else if (event.getSource() == buttons[2]) {
                getMainScreen().setVisible(false);
                if (screenCurriculumInformation != null) {
                    screenCurriculumInformation.setVisible(false);
                }
                if (screenConvertionInformation == null) {
                    screenConvertionInformation = new ScreenConvertionInformation(mainScreen.getWidth(),
                            mainScreen.getHeight(), ScreenInformation.this, applicationFrame);
                    ScreenInformation.this.add(screenConvertionInformation);
                } else {
                    screenConvertionInformation.setVisible(true);
                }
                AnimationPanel animation = new AnimationPanel(getScreenConvertionInformation(),
                        getScreenConvertionInformation().getWidth(), 0, 0, 0,
                        300);
                animation.start();
            }
            // Press at "Back" in mainScreen
            else if (event.getSource() == buttons[1]) {
                getParentScreen().getScreenInformation().setVisible(false);
                getParentScreen().getMainScreen().setVisible(true);
                if (screenCurriculumInformation != null) {
                    screenCurriculumInformation.setVisible(false);
                }
                if (screenConvertionInformation != null) {
                    screenConvertionInformation.setVisible(false);
                }
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
