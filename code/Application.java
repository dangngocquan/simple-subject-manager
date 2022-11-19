package code;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Application extends JFrame {
    // Properties and screens
    public static int width = 1000, height = 600;
    public ScreenMainMenu screenMainMenu;
    public ScreenPlans screenPlans;

    // Constructor
    public Application() {
        super("GPA Plans - HUS");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Create screens
        screenMainMenu = new ScreenMainMenu(width, height);
        screenPlans = new ScreenPlans(width, height);

        // Add ButtonHandler for all buttons
        for (Button button : screenMainMenu.getButtons()) {
            button.addActionListener(new ButtonHandler());
        }
        for (Button button : screenPlans.getButtons()) {
            button.addActionListener(new ButtonHandler());
        }

        // Add screens to frame
        add(screenMainMenu);
        add(screenPlans);

        // Set screen visible
        screenMainMenu.setVisible(true);
        screenPlans.setVisible(false);

    }

    // Handler when press at button
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == screenMainMenu.getButtons()[0]) {
                screenMainMenu.setVisible(false);
                screenPlans.setVisible(true);
            } else if (event.getSource() == screenPlans.getButtons()[2]) {
                screenMainMenu.setVisible(true);
                screenPlans.setVisible(false);
            }
        }
    }
}
