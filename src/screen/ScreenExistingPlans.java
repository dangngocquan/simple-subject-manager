package src.screen;

import javax.swing.JPanel;

import src.animation.AnimationPanel;
import src.dialog.DialogCopyPlan;
import src.dialog.DialogMessage;
import src.dialog.DialogRemovePlan;
import src.dialog.DialogRenamePlan;
import src.file_handler.ReadFile;
import src.file_handler.WriteFile;
import src.launcher.Application;
import src.launcher.Setting;
import src.objects.Button;
import src.objects.Plan;

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
    private Button[] buttonPlansEdit;
    private Button[] buttonPlansCopy;
    private Button[] buttonPlansRemove;
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
        buttonPlansEdit = new Button[plans.size()];
        buttonPlansCopy = new Button[plans.size()];
        buttonPlansRemove = new Button[plans.size()];
        int heightScroll = 0;
        int maxWidth = 0;

        for (int i = 0; i < plans.size(); i++) {
            Button tempButton = new Button(plans.get(i).getName());
            maxWidth = Math.max(maxWidth, tempButton.getWidth());
        }
        maxWidth = Math.min(maxWidth, contentPanel.getWidth() / 5 * 3);

        for (int i = 0; i < plans.size(); i++) {
            // Create button with name of plan
            Button tempButton = new Button(plans.get(i).getName());
            tempButton.setSizeButton(maxWidth, tempButton.getHeight() * 3 / 2);
            tempButton.setLocationButton(contentPanel.getWidth() / 2, heightScroll, Button.TOP_CENTER);
            scrollPanel.add(tempButton);
            buttonPlans[i] = tempButton;
            buttonPlans[i].setToolTipText(String.format("%s | %s | %s", plans.get(i).getSchoolName(),
                    plans.get(i).getDepartmentName(), plans.get(i).getMajorName()));
            buttonPlans[i].addMouseListener(new MouseHandler());
            heightScroll += tempButton.getHeight() + 120;

            Button buttonEdit = new Button("");
            buttonEdit.setLocationButton(tempButton.getX() + tempButton.getWidth() + 30,
                    tempButton.getY() + tempButton.getHeight() / 2,
                    Button.CENTER_LEFT);
            buttonEdit.setSizeButton(tempButton.getHeight() / 2, tempButton.getHeight() / 2);
            buttonEdit.setBackgroundIcon(Setting.EDIT);
            buttonEdit.setToolTipText("Sửa tên kế hoạch này");
            scrollPanel.add(buttonEdit);
            buttonPlansEdit[i] = buttonEdit;
            buttonPlansEdit[i].addMouseListener(new MouseHandler());

            Button buttonCopy = new Button("");
            buttonCopy.setLocationButton(buttonEdit.getX() + buttonEdit.getWidth() + 15,
                    tempButton.getY() + tempButton.getHeight() / 2,
                    Button.CENTER_LEFT);
            buttonCopy.setSizeButton(tempButton.getHeight() / 2, tempButton.getHeight() / 2);
            buttonCopy.setBackgroundIcon(Setting.COPY);
            buttonCopy.setToolTipText("Tạo bản sao của kế hoạch này");
            scrollPanel.add(buttonCopy);
            buttonPlansCopy[i] = buttonCopy;
            buttonPlansCopy[i].addMouseListener(new MouseHandler());

            Button buttonRemove = new Button("");
            buttonRemove.setLocationButton(buttonCopy.getX() + buttonCopy.getWidth() + 15,
                    tempButton.getY() + tempButton.getHeight() / 2,
                    Button.CENTER_LEFT);
            buttonRemove.setSizeButton(tempButton.getHeight() / 2, tempButton.getHeight() / 2);
            buttonRemove.setBackgroundIcon(Setting.REMOVE);
            buttonRemove.setToolTipText("Xóa kế hoạch này");
            scrollPanel.add(buttonRemove);
            buttonPlansRemove[i] = buttonRemove;
            buttonPlansRemove[i].addMouseListener(new MouseHandler());
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
        if (value > getMaxCursorScroll()) {
            this.cursorScroll = getMaxCursorScroll();
        }
        if (value < 0) {
            this.cursorScroll = 0;
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
                AnimationPanel animation = new AnimationPanel(getParentScreen().getMainScreen(),
                        -getParentScreen().getMainScreen().getWidth(), 0, 0, 0,
                        300);
                animation.start();
            }
            // Press a button plan
            else {
                List<Plan> plans = ReadFile.getCurrentAccount().getPlans();
                for (int i = 0; i < plans.size(); i++) {
                    if (event.getSource() == buttonPlans[i]) {
                        getMainScreen().setVisible(false);
                        if (screenPlanViews[i] == null) {
                            screenPlanViews[i] = new ScreenPlanView(mainScreen.getWidth(), mainScreen.getHeight(),
                                    ScreenExistingPlans.this,
                                    applicationFrame, i, plans.get(i));
                            add(screenPlanViews[i]);
                        } else {
                            screenPlanViews[i].setVisible(true);
                        }
                        AnimationPanel animation = new AnimationPanel(getScreenPlanViews()[i],
                                getScreenPlanViews()[i].getWidth(), 0, 0, 0,
                                300);
                        animation.start();
                    } else if (event.getSource() == buttonPlansEdit[i]) {
                        new DialogRenamePlan(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3,
                                Setting.HEIGHT / 3, DialogRenamePlan.CENTER_CENTER, "Rename plan",
                                new String[] { "Nhập tên mới cho kế hoạch này:" }, i);
                        updateButton();
                        repaint();
                    } else if (event.getSource() == buttonPlansCopy[i]) {
                        new DialogCopyPlan(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3,
                                Setting.HEIGHT / 3, DialogCopyPlan.CENTER_CENTER, "Copy plan",
                                new String[] { "Nhập tên cho bản sao của kế hoạch này:" }, i);
                        updateButton();
                        repaint();
                    } else if (event.getSource() == buttonPlansRemove[i]) {
                        DialogRemovePlan dialog1 = new DialogRemovePlan(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                Setting.WIDTH / 3,
                                Setting.HEIGHT / 3, DialogCopyPlan.CENTER_CENTER, "Remove plan",
                                new String[] { "Bạn có chắc chắn muốn xóa kế hoạch " + "\"" + plans.get(i).getName()
                                        + "\" " + "không?" });
                        if (dialog1.getResult() == true) {
                            WriteFile.removePlan(i);
                            new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3,
                                    Setting.HEIGHT / 3,
                                    DialogMessage.CENTER_CENTER,
                                    "Information", new String[] { "Xóa kế hoạch thành công" }, Setting.INFORMATION);
                            updateButton();
                            setCurscorScroll(getCursorScroll());
                            repaint();
                            break;
                        }

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
