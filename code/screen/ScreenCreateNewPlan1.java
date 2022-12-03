package code.screen;

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Font;
import code.Application;
import code.Setting;
import code.curriculum.Data;
import code.dialog.DialogList;
import code.objects.Button;
import code.objects.Department;
import code.objects.Major;
import code.objects.School;
import code.panel.PanelString;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScreenCreateNewPlan1 extends JPanel {
    // Properties, Objects and Screens
    private Application applicationFrame;
    private String[] buttonTexts = {
            "Quay lại", "Tiếp theo", "", "", "", "Thay đổi", "Thay đổi", "Thay đổi"
    };
    private Button[] buttons;
    private ScreenPlans parentScreen;
    private JPanel mainScreen, contentPanel, optionPanel;

    private School school = Data.SCHOOLS.getSchools().get(0);
    private Department department = school.getDepartments().get(0);
    private Major major = department.getMajors().get(0);

    private ScreenCreateNewPlan2 screenCreateNewPlan2;

    // Constructor
    public ScreenCreateNewPlan1(int width, int height, ScreenPlans parentScreen, Application frame) {
        // Set basic properties
        this.applicationFrame = frame;
        setLayout(null);
        setSize(width, height);
        setBounds(0, 0, width, height);
        this.parentScreen = parentScreen;
        this.screenCreateNewPlan2 = new ScreenCreateNewPlan2(width, height, this, frame, major);

        // Create screens
        mainScreen = new JPanel();
        mainScreen.setLayout(null);
        mainScreen.setSize(width, height);
        mainScreen.setBounds(0, 0, width, height);

        PanelString title = new PanelString(width / 2, height / 16, "Trước hết, hãy chọn một ngành học", width,
                new Font(Setting.FONT_NAME_01,
                        Setting.FONT_STYLE_01,
                        Setting.FONT_SIZE_BIG),
                PanelString.TOP_CENTER, 0);

        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setSize(width / 20 * 16, height / 8 * 3);
        contentPanel.setBounds(width / 20, height / 4, contentPanel.getWidth(), contentPanel.getHeight());

        optionPanel = new JPanel();
        optionPanel.setLayout(null);
        optionPanel.setSize(width / 10, contentPanel.getHeight());
        optionPanel.setBounds(contentPanel.getX() + contentPanel.getWidth() + width / 60,
                contentPanel.getY(), optionPanel.getWidth(), optionPanel.getHeight());

        // Create buttons
        buttons = new Button[buttonTexts.length];
        for (int count = 0; count < buttonTexts.length; count++) {
            buttons[count] = new Button(buttonTexts[count]);
            buttons[count].setFont(
                    Setting.FONT_NAME_01,
                    Setting.FONT_STYLE_01,
                    Setting.FONT_SIZE_MEDIUM);
            buttons[count].addActionListener(new ButtonHandler());
        }

        // Change size of some button
        buttons[5].setSizeButton(optionPanel.getWidth(), buttons[1].getHeight() * 2, true);
        buttons[6].setSizeButton(optionPanel.getWidth(), buttons[1].getHeight() * 2, true);
        buttons[7].setSizeButton(optionPanel.getWidth(), buttons[1].getHeight() * 2, true);

        // Update content
        updateContentPanel();

        // Set location for each button
        buttons[0].setLocation(width / 16, height / 8 * 7, Button.BOTTOM_LEFT);
        buttons[1].setLocation(width / 16 * 15, height / 8 * 7, Button.BOTTOM_RIGHT);
        buttons[2].setLocation(0, 0, Button.TOP_LEFT);
        buttons[3].setLocation(0, contentPanel.getHeight() / 2, Button.CENTER_LEFT);
        buttons[4].setLocation(0, contentPanel.getHeight(), Button.BOTTOM_LEFT);
        buttons[5].setLocation(optionPanel.getWidth() / 2, 0, Button.TOP_CENTER);
        buttons[6].setLocation(optionPanel.getWidth() / 2, optionPanel.getHeight() / 2, Button.CENTER_CENTER);
        buttons[7].setLocation(optionPanel.getWidth() / 2, optionPanel.getHeight(), Button.BOTTOM_CENTER);

        // Add buttons to mainScreen
        for (Button button : buttons) {
            mainScreen.add(button);
        }

        // Add subpanels
        mainScreen.add(title);
        mainScreen.add(contentPanel);
        mainScreen.add(optionPanel);

        contentPanel.add(buttons[2]);
        contentPanel.add(buttons[3]);
        contentPanel.add(buttons[4]);
        optionPanel.add(buttons[5]);
        optionPanel.add(buttons[6]);
        optionPanel.add(buttons[7]);

        // Add screens to this panel
        add(mainScreen);
        add(screenCreateNewPlan2);

        // Set visible of screens
        mainScreen.setVisible(true);
        screenCreateNewPlan2.setVisible(false);
    }

    // Get application frame
    public Application getApplicationFrame() {
        return this.applicationFrame;
    }

    // Get buttons in mainScreen
    public Button[] getButtons() {
        return this.buttons;
    }

    // Get mainScreen
    public JPanel getMainScreen() {
        return this.mainScreen;
    }

    // Get screen create new plan 2
    public ScreenCreateNewPlan2 getScreenCreateNewPlan2() {
        return this.screenCreateNewPlan2;
    }

    // Get parent screen of this panel
    public ScreenPlans getParentScreen() {
        return this.parentScreen;
    }

    // Update content panels
    public void updateContentPanel() {
        buttons[2].setText("Trường: " + school.getName(), Setting.FONT_SIZE_MEDIUM, true);
        buttons[3].setText("Khoa: " + department.getName(), Setting.FONT_SIZE_MEDIUM, true);
        buttons[4].setText("Ngành: " + major.getName(), Setting.FONT_SIZE_MEDIUM, true);
        buttons[2].setSizeButton(contentPanel.getWidth(), buttons[1].getHeight() * 2, true);
        buttons[3].setSizeButton(contentPanel.getWidth(), buttons[1].getHeight() * 2, true);
        buttons[4].setSizeButton(contentPanel.getWidth(), buttons[1].getHeight() * 2, true);
        buttons[2].setEnabled(false);
        buttons[3].setEnabled(false);
        buttons[4].setEnabled(false);
        repaint();
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    // Handler buttons
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // Press at "Back" in mainScreen
            if (event.getSource() == buttons[0]) {
                getParentScreen().getMainScreen().setVisible(true);
                getParentScreen().getScreenCreateNewPlan1().setVisible(false);
            }
            // Press at "Next" in mainScreen
            else if (event.getSource() == buttons[1]) {
                screenCreateNewPlan2.setMajor(major);
                screenCreateNewPlan2.setIndexConversionTable(Data.getIndexConnversion(school.getName()));
                screenCreateNewPlan2.setVisible(true);
                mainScreen.setVisible(false);
            }
            // Press "Change" of School
            else if (event.getSource() == buttons[5]) {
                // Dialog for user to choose School
                DialogList dialog1 = new DialogList(applicationFrame, "Chọn trường đại học",
                        "Chọn trường đại học", Data.SCHOOLS.getSchoolNames().toArray(),
                        Data.SCHOOLS.getSchoolNames().get(0));
                String schoolName = dialog1.run();
                if (schoolName == null || schoolName.isEmpty()) {

                } else {
                    school = Data.getSchool(schoolName);
                    department = school.getDepartments().get(0);
                    major = department.getMajors().get(0);
                }

            }
            // Press "Change" of Department
            else if (event.getSource() == buttons[6]) {
                // Dialog for user to choose Department
                DialogList dialog2 = new DialogList(applicationFrame,
                        "Chọn khoa của trường " + school.getName(),
                        "Chọn khoa của trường " + school.getName(),
                        school.getDepartmentNames().toArray(),
                        school.getDepartmentNames().get(0));
                String departmentName = dialog2.run();
                if (departmentName == null || departmentName.isEmpty()) {

                } else {
                    department = Data.getDepartment(departmentName, school);
                    major = department.getMajors().get(0);
                    updateContentPanel();
                }
            }
            // Press "Change" of major
            else if (event.getSource() == buttons[7]) {
                // Dialog for user to choose Major
                DialogList dialog3 = new DialogList(applicationFrame,
                        "Chọn ngành của khoa " + department.getName(),
                        "Chọn ngành của khoa " + department.getName(),
                        department.getMajorNames().toArray(),
                        department.getMajorNames().get(0));
                String majorName = dialog3.run();
                if (majorName == null || majorName.isEmpty()) {

                } else {
                    major = Data.getMajor(majorName, department);
                    updateContentPanel();
                }
            }
        }
    }
}
