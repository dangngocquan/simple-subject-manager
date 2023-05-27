package src2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen extends JWindow {
    Image splashScreen;
    ImageIcon imageIcon;
    public static int maxCount = 6, tempCount = 0;
    public static int speedLevel = 4;

    public SplashScreen() {
        Setting.getInstance();
        splashScreen = src.launcher.Setting.SPLASH[0].getImage();
        // Create ImageIcon from Image
        imageIcon = new ImageIcon(splashScreen);
        // Set JWindow size from image size
        setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        // Get current screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Get x coordinate on screen for make JWindow locate at center
        int x = (screenSize.width - getSize().width) / 2;
        // Get y coordinate on screen for make JWindow locate at center
        int y = (screenSize.height - getSize().height) / 2;
        // Set new location for JWindow
        setLocation(x, y);
        // Make JWindow visible
        setVisible(true);
        int duration = 10;
        Timer timer = new Timer(duration,
                new ActionListener() {
                    int temp = 0;

                    public void actionPerformed(ActionEvent event) {
                        if (tempCount > temp) {
                            splashScreen = src.launcher.Setting.SPLASH[tempCount % Setting.SPLASH.length].getImage();
                            temp = tempCount;
                            repaint();
                        }

                        if (tempCount >= maxCount) {
                            ((Timer) event.getSource()).stop();
                            SplashScreen.this.dispose();
                        }
                    }
                });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
    }

    // Paint image onto JWindow
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(splashScreen, 0, 0, null);
    }
}