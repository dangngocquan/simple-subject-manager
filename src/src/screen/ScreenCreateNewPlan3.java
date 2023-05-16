package src.screen;

import javax.swing.JPanel;

import src.animation.AnimationPanel;
import src.dialog.DialogMessage;
import src.launcher.Application;
import src.launcher.Setting;
import src.launcher.WriteFile;
import src.objects.Button;
import src.objects.Plan;
import src.objects.Subject;
import src.objects.TextField;
import src.panel.PanelString;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.awt.Graphics;

public class ScreenCreateNewPlan3 extends JPanel {
    // Properties
    private JPanel mainScreen;
    private Application applicationFrame;
    private ScreenCreateNewPlan2 parentScreen;
    private TextField fieldName = null;
    private String[] buttonTexts = {
            "Quay lại", "Tạo"
    };
    private Button[] buttons;
    private List<Subject> subjects;
    private int indexConversionTable = 0;
    private String schoolName = "", departmentName = "", majorName = "";

    // Constructor
    public ScreenCreateNewPlan3(int width, int height, ScreenCreateNewPlan2 parentScreen, Application frame,
            List<Subject> subjects) {
        // Set basic properties for this screen
        this.applicationFrame = frame;
        setLayout(null);
        setBounds(0, 0, width, height);
        setSize(width, height);
        this.parentScreen = parentScreen;
        this.subjects = subjects;

        // Create screens
        mainScreen = new JPanel();
        mainScreen.setLayout(null);
        mainScreen.setSize(width, height);
        mainScreen.setBounds(0, 0, width, height);

        // Create buttons
        buttons = new Button[buttonTexts.length];
        for (int count = 0; count < buttonTexts.length; count++) {
            buttons[count] = new Button(buttonTexts[count]);
            buttons[count].setFontText(Button.ARIAL_BOLD_24);
            buttons[count].setCorrectSizeButton();
            buttons[count].addMouseListener(new MouseHandler());
        }

        // Create text fields
        int tempHeight = 30;
        String[] messageLines = {
                "Cuối cùng, hãy đặt tên cho kế hoạch của bạn."
        };
        PanelString messagePanel = new PanelString(width / 2, tempHeight, messageLines, width / 10 * 9,
                new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_BIG),
                PanelString.TOP_CENTER, 0);
        tempHeight += messagePanel.getHeight() + 30;
        fieldName = new TextField(width / 2, tempHeight,
                width / 3 * 2, 100, TextField.TOP_CENTER, "Tên kế hoạch", 2, 15, 15);
        fieldName.setFont(new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_MEDIUM));
        tempHeight += fieldName.getHeight() + 40;

        // Set location for each button
        buttons[0].setLocationButton(width / 4, tempHeight, Button.TOP_CENTER);
        buttons[1].setLocationButton(width / 4 * 3, tempHeight, Button.TOP_CENTER);

        // Add buttons to mainScreen
        for (Button button : buttons) {
            mainScreen.add(button);
        }

        mainScreen.add(messagePanel);
        mainScreen.add(fieldName);

        // Add screen to this panel
        add(mainScreen);
        // add(screenCurriculumInformation);

        // Set visible of screens
        mainScreen.setVisible(true);
        // screenCurriculumInformation.setVisible(false);
    }

    // Get application frame
    public Application getApplicationFrame() {
        return this.applicationFrame;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public String getMajorName() {
        return this.majorName;
    }

    // Get buttons
    public Button[] getButtons() {
        return this.buttons;
    }

    // Get parentScreen
    public ScreenCreateNewPlan2 getParentScreen() {
        return this.parentScreen;
    }

    // Get mainScreen
    public JPanel getMainScreen() {
        return this.mainScreen;
    }

    // Get index conversion table
    public int getIndexConversionTable() {
        return this.indexConversionTable;
    }

    // Set list subject
    public void setListSubjectSelected(List<Subject> subjects) {
        this.subjects = subjects;
    }

    // Set index conversion table
    public void setIndexConversionTable(int index) {
        this.indexConversionTable = index;
    }

    public void setSchoolName(String name) {
        this.schoolName = name;
    }

    public void setDepartmentName(String name) {
        this.departmentName = name;
    }

    public void setMajorName(String name) {
        this.majorName = name;
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            // Press at "Create" in mainScreen
            if (event.getSource() == buttons[1]) {
                if (fieldName.getText().isEmpty() ||
                        (fieldName.getText().equals(fieldName.getDefaultText())
                                && fieldName.getForeground() == Setting.COLOR_GRAY_03)) {
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER,
                            "Information", new String[] { "Bạn chưa nhập Họ và tên" }, Setting.WARNING);
                } else {
                    Plan plan = new Plan(fieldName.getText(), subjects, indexConversionTable);
                    plan.setSchoolName(schoolName);
                    plan.setDepartmentName(departmentName);
                    plan.setMajorName(majorName);
                    WriteFile.createNewPlan(plan);
                    new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                            DialogMessage.CENTER_CENTER,
                            "Information", new String[] { "Tạo kế hoạch thành công" }, Setting.INFORMATION);
                    ;
                    // return screen create new plan 2
                    getParentScreen().getScreenCreateNewPlan3().setVisible(false);
                    getParentScreen().getMainScreen().setVisible(true);
                    // return screen create new plan 1
                    getParentScreen().getParentScreen().getMainScreen().setVisible(true);
                    getParentScreen().getParentScreen().getScreenCreateNewPlan2().setVisible(false);
                    // return screen plans
                    getParentScreen().getParentScreen().getParentScreen().getScreenCreateNewPlan1().setVisible(false);
                    getParentScreen().getParentScreen().getParentScreen().getMainScreen().setVisible(true);
                    AnimationPanel animation = new AnimationPanel(
                            getParentScreen().getParentScreen().getParentScreen().getMainScreen(),
                            -getParentScreen().getParentScreen().getParentScreen().getMainScreen().getWidth(), 0, 0, 0,
                            300);
                    animation.start();
                }
            }
            // Press at "Back" in mainScreen
            else if (event.getSource() == buttons[0]) {
                getParentScreen().getMainScreen().setVisible(true);
                getParentScreen().getScreenCreateNewPlan3().setVisible(false);
                AnimationPanel animation = new AnimationPanel(getParentScreen().getMainScreen(),
                        -getParentScreen().getMainScreen().getWidth(), 0, 0, 0,
                        300);
                animation.start();
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
