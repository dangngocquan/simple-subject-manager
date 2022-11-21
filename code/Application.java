package code;

import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;

import code.curriculum.Data;

public class Application extends JFrame {
    // Properties and screens
    private int width = (int) (GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getMaximumWindowBounds().getWidth());
    private int height = (int) ((GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getMaximumWindowBounds().getHeight()));
    private ScreenMainMenu screenMainMenu;

    // Constructor
    public Application() {
        // Set properties
        super("HUS");
        setSize(width, height);
        setResizable(false);
        setAlwaysOnTop(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        new Data();

        // Create screens
        screenMainMenu = new ScreenMainMenu(width, height - 36);

        // Add screens to frame
        add(screenMainMenu);

        // Set screen visible
        screenMainMenu.setVisible(true);
    }

}
