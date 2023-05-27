package src2;

public class Launcher {
    public static void main(String[] args) {
        Setting.getInstance();
        new SplashScreen();
        new ReadFile();
        new Data();
        new Application();
    }
}
