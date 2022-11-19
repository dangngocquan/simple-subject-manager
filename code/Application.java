package code;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Application extends JFrame {
    // Properties and screens
    private static int width = 1000, height = 600;
    private ScreenMainMenu screenMainMenu;
    private ScreenPlans screenPlans;
    private ScreenCreateNewPlan1 screenCreateNewPlan1;

    // Constructor
    public Application() {
        super("GPA Plans - HUS");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Create screens
        screenMainMenu = new ScreenMainMenu(width, height);
        screenPlans = new ScreenPlans(width, height);
        screenCreateNewPlan1 = new ScreenCreateNewPlan1(width, height);

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

        // Add screens to frame
        add(screenMainMenu);
        add(screenPlans);
        add(screenCreateNewPlan1);

        // Set screen visible
        screenMainMenu.setVisible(true);
        screenPlans.setVisible(false);
        screenCreateNewPlan1.setVisible(false);

    }

    // Handler when press at button
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // Press "Plans" button on "screenMainMenu" screen
            if (event.getSource() == screenMainMenu.getButtons()[0]) {
                screenMainMenu.setVisible(false);
                screenPlans.setVisible(true);

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
        }
    }
}
