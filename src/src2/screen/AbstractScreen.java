package src2.screen;

import src2.Application;
import src2.Setting;
import src2.panel.Button;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class AbstractScreen extends JPanel {
    private Application application;
    private String[] buttonTexts;
    private Button[] buttons;

    private AbstractScreen parentScreen;
    private JPanel mainScreen;
    private List<AbstractScreen> childrenScreens;

    public AbstractScreen(Application application, AbstractScreen parentScreen) {
        this.application = application;
        this.parentScreen = parentScreen;
        this.childrenScreens = new LinkedList<AbstractScreen>();
        build();
    }

    public void build() {
        setLayout(null);
        setBounds(0, 0, Setting.WIDTH, Setting.HEIGHT);
        setSize(Setting.WIDTH, Setting.HEIGHT);

        mainScreen = new JPanel();
        mainScreen.setLayout(null);
        mainScreen.setSize(Setting.WIDTH, Setting.HEIGHT);
        mainScreen.setBounds(0, 0, mainScreen.getWidth(), mainScreen.getHeight());

        buildAnother();
    }

    public abstract void buildAnother();

    public abstract void createButtonTexts();
    public abstract void createButtons();

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    // Getter and Setter
    public String[] getButtonTexts() {
        return buttonTexts;
    }

    public void setButtonTexts(String... buttonTexts) {
        this.buttonTexts = buttonTexts;
    }

    public Button[] getButtons() {
        return buttons;
    }

    public void setButtons(Button[] buttons) {
        this.buttons = buttons;
    }

    public AbstractScreen getParentScreen() {
        return parentScreen;
    }

    public void setParentScreen(AbstractScreen parentScreen) {
        this.parentScreen = parentScreen;
    }

    public JPanel getMainScreen() {
        return mainScreen;
    }

    public void setMainScreen(JPanel mainScreen) {
        this.mainScreen = mainScreen;
    }


}
