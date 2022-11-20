package code;

import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Application extends JFrame {
    // Properties and screens
    private int width = (int) (GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getMaximumWindowBounds().getWidth());
    private int height = (int) ((GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getMaximumWindowBounds().getHeight()));
    private ScreenMainMenu screenMainMenu;
    private ScreenPlans screenPlans;
    private ScreenCreateNewPlan1 screenCreateNewPlan1;
    private ScreenInformation screenInformation;
    private ScreenCurriculumInformation screenCurriculumInformation;

    // Constructor
    public Application() {
        super("GPA Plans - HUS");
        setSize(width, height);
        setResizable(false);
        setAlwaysOnTop(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Create screens
        screenMainMenu = new ScreenMainMenu(width, height - 36);
        screenPlans = new ScreenPlans(width, height - 20);
        screenCreateNewPlan1 = new ScreenCreateNewPlan1(width, height - 36);
        screenInformation = new ScreenInformation(width, height - 36);
        screenCurriculumInformation = new ScreenCurriculumInformation(width, height - 36);

        // Add ButtonHandler for all buttons
        for (Button button : screenMainMenu.getButtons()) {
            button.addActionListener(new ButtonHandler());
        }
        for (Button button : screenPlans.getButtons()) {
            button.addActionListener(new ButtonHandler());
        }
        for (Button button : screenCreateNewPlan1.getButtons()) {
            button.addActionListener(new ButtonHandler());
        }
        for (Button button : screenInformation.getButtons()) {
            button.addActionListener(new ButtonHandler());
        }
        for (Button button : screenCurriculumInformation.getButtons()) {
            button.addActionListener(new ButtonHandler());
        }

        // Add screens to frame
        add(screenMainMenu);
        add(screenPlans);
        add(screenCreateNewPlan1);
        add(screenInformation);
        add(screenCurriculumInformation);

        // Set screen visible
        screenMainMenu.setVisible(true);
        screenPlans.setVisible(false);
        screenCreateNewPlan1.setVisible(false);
        screenInformation.setVisible(false);
        screenCurriculumInformation.setVisible(false);
    }

    // Handler when press at button
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // Press "Plans" button on "screenMainMenu" screen
            if (event.getSource() == screenMainMenu.getButtons()[0]) {
                screenMainMenu.setVisible(false);
                screenPlans.setVisible(true);

            }
            // Press "Information" button on "screenMainMenu" screen
            else if (event.getSource() == screenMainMenu.getButtons()[2]) {
                screenMainMenu.setVisible(false);
                screenInformation.setVisible(true);

            }
            // Press "Create New Plan" button of "screenPlans" screen
            else if (event.getSource() == screenPlans.getButtons()[1]) {
                screenCreateNewPlan1.setVisible(true);
                screenPlans.setVisible(false);
            }
            // Press "Return Main Menu" button of "screenPlans" screen
            else if (event.getSource() == screenPlans.getButtons()[2]) {
                screenMainMenu.setVisible(true);
                screenPlans.setVisible(false);
            }
            // Press "Back" button of "screenCreateNewPlan1" screen
            else if (event.getSource() == screenCreateNewPlan1.getButtons()[0]) {
                screenPlans.setVisible(true);
                screenCreateNewPlan1.setVisible(false);
            }
            // Press "Curriculums" button of "screenInformation" screen
            else if (event.getSource() == screenInformation.getButtons()[0]) {
                screenInformation.setVisible(false);
                screenCurriculumInformation.setVisible(true);
            }
            // Press "Back" button of "screenInformation" screen
            else if (event.getSource() == screenInformation.getButtons()[1]) {
                screenInformation.setVisible(false);
                screenMainMenu.setVisible(true);
            }
            // Press "Back" button of "screenCurriculumInformation" screen
            else if (event.getSource() == screenCurriculumInformation.getButtons()[0]) {
                screenCurriculumInformation.setVisible(false);
                screenInformation.setVisible(true);
            }
        }
    }
}
