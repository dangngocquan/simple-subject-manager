package code.screen;

import javax.swing.JFrame;
// import javax.swing.JList;
import javax.swing.JPanel;

import code.Application;
import code.Setting;
import code.button.Button;
import code.curriculum.Data;
import code.curriculum.Department;
import code.curriculum.Major;
import code.curriculum.School;
import code.dialog.DialogList;
import code.panel.PanelMajor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;

public class ScreenCurriculumInformation extends JPanel {
        // Properties, Objects and Screens
        private Application applicationFrame;
        private ScreenInformation parentScreen;
        private JPanel mainScreen;
        private String[] buttonTexts = {
                        "Quay lại", "Thay đổi", "", "", ""
        };
        private Button[] buttons;
        private JPanel headPanel, bodyPanel;
        private JPanel headPanel1, headPanel2, headPanel3;
        private JPanel bodyPanel0;

        private School school = Data.SCHOOLS.getSchools().get(0);
        private Department department = school.getDepartments().get(0);
        private Major major = department.getMajors().get(0);

        // Constructor
        public ScreenCurriculumInformation(int width, int height, ScreenInformation parentScreen, Application frame) {
                // Set basic properties
                this.applicationFrame = frame;
                setLayout(null);
                setSize(width, height);
                setBounds(0, 0, width, height);
                this.parentScreen = parentScreen;

                // Create screens
                mainScreen = new JPanel();
                mainScreen.setLayout(null);
                mainScreen.setSize(width, height);
                mainScreen.setBounds(0, 0, width, height);

                // Create sub panels of "mainScreen"
                headPanel = new JPanel();
                headPanel.setLayout(null);
                headPanel.setSize(width, height / 8);
                headPanel.setBounds(0, 0, headPanel.getWidth(), headPanel.getHeight());

                bodyPanel = new JPanel();
                bodyPanel.setLayout(null);
                bodyPanel.setSize(width, height - headPanel.getHeight());
                bodyPanel.setBounds(0, headPanel.getHeight(), bodyPanel.getWidth(),
                                bodyPanel.getHeight());

                // Create sub panels of "headPanel"
                headPanel1 = new JPanel();
                headPanel1.setLayout(null);
                headPanel1.setSize(headPanel.getWidth() / 8, headPanel.getHeight());
                headPanel1.setBounds(0, 0, headPanel1.getWidth(), headPanel1.getHeight());

                headPanel3 = new JPanel();
                headPanel3.setLayout(null);
                headPanel3.setSize(headPanel.getWidth() / 8, headPanel.getHeight());
                headPanel3.setBounds(headPanel.getWidth() - headPanel3.getWidth(), 0,
                                headPanel3.getWidth(), headPanel3.getHeight());

                headPanel2 = new JPanel();
                headPanel2.setLayout(null);
                headPanel2.setSize(headPanel.getWidth() - headPanel1.getWidth() - headPanel3.getWidth(),
                                headPanel.getHeight());
                headPanel2.setBounds(headPanel1.getWidth(), 0, headPanel2.getWidth(),
                                headPanel2.getHeight());

                // Create sub panels of "bodyPanel"
                bodyPanel0 = new JPanel();
                bodyPanel0.setLayout(null);
                bodyPanel0.setSize(bodyPanel.getWidth() / 16 * 15, bodyPanel.getHeight() / 16
                                * 15);
                bodyPanel0.setBounds(bodyPanel.getWidth() / 2 - bodyPanel0.getWidth() / 2,
                                bodyPanel.getHeight() / 2 - bodyPanel0.getHeight() / 2,
                                bodyPanel0.getWidth(), bodyPanel0.getHeight());

                // Create buttons
                buttons = new Button[buttonTexts.length];
                for (int count = 0; count < buttonTexts.length; count++) {
                        buttons[count] = new Button(buttonTexts[count]);
                        buttons[count].setFont(
                                        Setting.FONT_NAME_01,
                                        Setting.FONT_STYLE_01,
                                        Setting.FONT_SIZE_SMALL);
                        buttons[count].addActionListener(new ButtonHandler());
                }

                // Update current curriculum
                updateCurriculum();

                // Set location for each button
                buttons[0].setLocation(headPanel1.getWidth() / 2, headPanel1.getHeight() / 2,
                                Button.CENTER_CENTER);
                buttons[1].setLocation(headPanel3.getWidth() / 2, headPanel3.getHeight() / 2,
                                Button.CENTER_CENTER);
                buttons[2].setLocation(headPanel2.getWidth() / 10, headPanel2.getHeight() / 4,
                                Button.CENTER_LEFT);
                buttons[3].setLocation(headPanel2.getWidth() / 10, headPanel2.getHeight() / 4 * 2,
                                Button.CENTER_LEFT);
                buttons[4].setLocation(headPanel2.getWidth() / 10, headPanel2.getHeight() / 4 * 3,
                                Button.CENTER_LEFT);

                // Add sub panels and buttons to "mainScreen"
                mainScreen.add(headPanel);
                mainScreen.add(bodyPanel);
                headPanel.add(headPanel1);
                headPanel.add(headPanel2);
                headPanel.add(headPanel3);
                headPanel1.add(buttons[0]);
                headPanel2.add(buttons[2]);
                headPanel2.add(buttons[3]);
                headPanel2.add(buttons[4]);
                headPanel3.add(buttons[1]);
                bodyPanel.add(bodyPanel0);
                bodyPanel0.add(
                                new PanelMajor(0, 0, bodyPanel0.getWidth(), bodyPanel0.getHeight(),
                                                Data.SCHOOLS.getSchools().get(0).getDepartments().get(0).getMajors()
                                                                .get(0),
                                                PanelMajor.TOP_LEFT));

                // Add screens to this panel
                add(mainScreen);

                // Set visible of screens
                mainScreen.setVisible(true);

                // Set color and other
                headPanel1.setBackground(Setting.COLOR_RED_02);
                headPanel2.setBackground(Setting.COLOR_RED_02);
                headPanel3.setBackground(Setting.COLOR_RED_02);
        }

        // Update curriculum, school name, department name and major name
        public void updateCurriculum() {
                buttons[2].setText("Trường: " + school.getName(), Setting.FONT_SIZE_VERY_SMALL, true);
                buttons[3].setText("Khoa: " + department.getName(), Setting.FONT_SIZE_VERY_SMALL, true);
                buttons[4].setText("Ngành: " + major.getName(), Setting.FONT_SIZE_VERY_SMALL, true);
                buttons[2].setSizeButton(headPanel2.getWidth() / 10 * 8, buttons[2].getHeight(), true);
                buttons[3].setSizeButton(headPanel2.getWidth() / 10 * 8, buttons[2].getHeight(), true);
                buttons[4].setSizeButton(headPanel2.getWidth() / 10 * 8, buttons[2].getHeight(), true);
                buttons[2].setEnabled(false);
                buttons[3].setEnabled(false);
                buttons[4].setEnabled(false);
        }

        // Get application frame
        public Application getApplicationFrame() {
                return this.applicationFrame;
        }

        // Get buttons
        public Button[] getButtons() {
                return this.buttons;
        }

        // Get parentScreen
        public ScreenInformation getParentScreen() {
                return this.parentScreen;
        }

        // Get mainScreen
        public JPanel getMainScreen() {
                return this.mainScreen;
        }

        // Auto called method of JPanel
        public void paintComponent(Graphics g) {
                super.paintComponent(g);
        }

        // Handler buttons
        private class ButtonHandler implements ActionListener {
                public void actionPerformed(ActionEvent event) {
                        // Press at "Back" of headPanel1 of headPanel of mainScreen
                        if (event.getSource() == buttons[0]) {
                                getParentScreen().getMainScreen().setVisible(true);
                                getParentScreen().getScreenCurriculumInformation().setVisible(false);
                        }
                        // Press at "Change" of headPanel3 of headPanel of mainScreen
                        else if (event.getSource() == buttons[1]) {
                                // Dialog for user to choose School
                                DialogList dialog1 = new DialogList((JFrame) applicationFrame, "Chọn trường đại học",
                                                "Chọn trường đại học", Data.SCHOOLS.getSchoolNames().toArray(),
                                                Data.SCHOOLS.getSchoolNames().get(0));
                                String schoolName = dialog1.run();
                                if (schoolName == null || schoolName.isEmpty()) {

                                } else {
                                        school = Data.getSchool(schoolName);
                                        department = school.getDepartments().get(0);
                                        major = department.getMajors().get(0);

                                        // Dialog for user to choose Department
                                        DialogList dialog2 = new DialogList((JFrame) applicationFrame, "Chọn Khoa",
                                                        "Chọn khoa", school.getDepartmentNames().toArray(),
                                                        school.getDepartmentNames().get(0));
                                        String departmentName = dialog2.run();
                                        if (departmentName == null || departmentName.isEmpty()) {

                                        } else {
                                                department = Data.getDepartment(departmentName, school);
                                                major = department.getMajors().get(0);

                                                // Dialog for user to choose Major
                                                DialogList dialog3 = new DialogList((JFrame) applicationFrame,
                                                                "Chọn ngành",
                                                                "Chọn ngành", department.getMajorNames().toArray(),
                                                                department.getMajorNames().get(0));
                                                String majorName = dialog3.run();
                                                if (majorName == null || majorName.isEmpty()) {

                                                } else {
                                                        major = Data.getMajor(majorName, department);
                                                }
                                        }
                                }
                                updateCurriculum();
                        }
                }
        }
}
