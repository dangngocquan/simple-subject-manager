package code.screen;

import javax.swing.JPanel;

import code.Application;
import code.Setting;
import code.file_handler.ReadFile;
import code.objects.Button;
import code.objects.Plan;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.event.MouseWheelListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.MouseWheelEvent;

public class ScreenExistingPlans extends JPanel {
    // Properties
    private ScreenPlans parentScreen;
    private Application applicationFrame;
    private JPanel mainScreen, contentPanel, scrollPanel;
    private int cursorScroll = 0;
    private String[] buttonTexts = {
            "Quay lại"
    };
    private Button[] buttons;
    private Button[] buttonPlans;
    private Button[] buttonPlansSetting;
    private ScreenPlanView[] screenPlanViews;

    // Constructor
    public ScreenExistingPlans(int width, int height, ScreenPlans parentScreen, Application frame) {
        // set basic properties
        this.parentScreen = parentScreen;
        this.applicationFrame = frame;
        setLayout(null);
        setSize(width, height);
        setBounds(0, 0, width, height);

        // Create panel
        mainScreen = new JPanel();
        mainScreen.setLayout(null);
        mainScreen.setSize(width, height);
        mainScreen.setBounds(0, 0, width, height);

        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setSize(width / 4 * 3, height / 5 * 4);
        contentPanel.setBounds(width / 2 - contentPanel.getWidth() / 2, height / 12, contentPanel.getWidth(),
                contentPanel.getHeight());

        scrollPanel = new JPanel();

        // Create buttons
        buttons = new Button[buttonTexts.length];
        for (int count = 0; count < buttonTexts.length; count++) {
            buttons[count] = new Button(buttonTexts[count]);
            buttons[count].setFontText(Button.ARIAL_BOLD_24);
            buttons[count].setCorrectSizeButton();
            buttons[count].addMouseListener(new MouseHandler());
        }
        // Set location for each button
        buttons[0].setLocationButton(width / 2, height / 12 * 11, Button.TOP_CENTER);

        // Create button for plans and screen of each plan
        updateButton();
        scrollPanel.addMouseWheelListener(new MouseWheelHandler());

        // Add subpanels
        contentPanel.add(scrollPanel);
        mainScreen.add(contentPanel);
        mainScreen.add(buttons[0]);
        add(mainScreen);

        // Set visible of screens
        mainScreen.setVisible(true);
    }

    // Getter
    // Get mainScreen
    public JPanel getMainScreen() {
        return this.mainScreen;
    }

    // Get parent screen
    public ScreenPlans getParentScreen() {
        return this.parentScreen;
    }

    // Get screenPlanViews
    public ScreenPlanView[] getScreenPlanViews() {
        return this.screenPlanViews;
    }

    // Update button for plans
    public void updateButton() {
        scrollPanel.removeAll();
        List<Plan> plans = new LinkedList<>();
        if (ReadFile.getCurrentAccount() != null) {
            plans = ReadFile.getCurrentAccount().getPlans();
        }

        buttonPlans = new Button[plans.size()];
        buttonPlansSetting = new Button[plans.size()];
        int heightScroll = 0;
        for (int i = 0; i < plans.size(); i++) {
            // Create button with name of plan
            Button tempButton = new Button(plans.get(i).getName());
            tempButton.setLocationButton(contentPanel.getWidth() / 2, heightScroll, Button.TOP_CENTER);
            scrollPanel.add(tempButton);
            buttonPlans[i] = tempButton;
            buttonPlans[i].addMouseListener(new MouseHandler());
            heightScroll += tempButton.getHeight() + 120;

            Button buttonSetting = new Button("");
            buttonSetting.setLocationButton(tempButton.getX() + tempButton.getWidth() + 30, tempButton.getY(),
                    Button.TOP_LEFT);
            buttonSetting.setSizeButton(tempButton.getHeight(), tempButton.getHeight());
            buttonSetting.setStrokeWidth(0);
            buttonSetting.setBackgroundImage(Setting.SETTING);
            scrollPanel.add(buttonSetting);
            buttonPlansSetting[i] = buttonSetting;
        }
        scrollPanel.setLayout(null);
        scrollPanel.setSize(contentPanel.getWidth(), heightScroll);
        if (scrollPanel.getHeight() > contentPanel.getHeight()) {
            scrollPanel.setBounds(0, -cursorScroll, scrollPanel.getWidth(), scrollPanel.getHeight());
        } else {
            scrollPanel.setBounds(0, (contentPanel.getHeight() - scrollPanel.getHeight()) / 2, scrollPanel.getWidth(),
                    scrollPanel.getHeight());
        }

        screenPlanViews = new ScreenPlanView[plans.size()];
        for (int i = 0; i < plans.size(); i++) {
            screenPlanViews[i] = new ScreenPlanView(mainScreen.getWidth(), mainScreen.getHeight(), this,
                    applicationFrame, i, plans.get(i));
            screenPlanViews[i].setVisible(false);
            add(screenPlanViews[i]);
        }
    }

    // Update contentPanel
    public void updateContentShowing() {
        if (scrollPanel.getHeight() > contentPanel.getHeight()) {
            scrollPanel.setBounds(0, -cursorScroll, scrollPanel.getWidth(), scrollPanel.getHeight());
        }
    }

    // get CursorScroll
    public int getCursorScroll() {
        return this.cursorScroll;
    }

    // Get max cursorScroll
    public int getMaxCursorScroll() {
        return Math.max(0, this.scrollPanel.getHeight() - this.contentPanel.getHeight());
    }

    // set cursorScroll
    public void setCurscorScroll(int value) {
        if (value >= 0 && value <= getMaxCursorScroll()) {
            this.cursorScroll = value;
        }
        updateContentShowing();
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private class MouseWheelHandler implements MouseWheelListener {
        public void mouseWheelMoved(MouseWheelEvent event) {
            setCurscorScroll(getCursorScroll() + event.getWheelRotation() * 20);
            updateContentShowing();
        }
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            // Press at "Back" button
            if (event.getSource() == buttons[0]) {
                getParentScreen().getMainScreen().setVisible(true);
                getParentScreen().getScreenExistingPlans().setVisible(false);
            }
            // Press a button plan
            else {
                List<Plan> plans = ReadFile.getCurrentAccount().getPlans();
                for (int i = 0; i < plans.size(); i++) {
                    if (event.getSource() == buttonPlans[i]) {
                        getMainScreen().setVisible(false);
                        getScreenPlanViews()[i].setVisible(true);
                    }
                }
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
