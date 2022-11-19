package code;

import javax.swing.JFrame;

public class Application extends JFrame {
    public static int width = 1000, height = 600;
    public ScreenMain screenMain;

    public Application() {
        super("GPA Plans - HUS");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        screenMain = new ScreenMain(width, height);
        add(screenMain);

    }

}
