package code.screen;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import code.Application;
import code.Setting;
import code.file_handler.WriteFile;
import code.objects.Button;
import code.objects.Plan;
import code.objects.Subject;
import code.panel.PanelString;
import code.text_field.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            buttons[count].setFont(
                    Setting.FONT_NAME_01,
                    Setting.FONT_STYLE_01,
                    Setting.FONT_SIZE_MEDIUM);
            buttons[count].addActionListener(new ButtonHandler());
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
        buttons[0].setLocation(width / 4, tempHeight, Button.TOP_CENTER);
        buttons[1].setLocation(width / 4 * 3, tempHeight, Button.TOP_CENTER);

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

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    // Handler buttons
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // Press at "Create" in mainScreen
            if (event.getSource() == buttons[1]) {
                if (fieldName.getText().isEmpty() ||
                        (fieldName.getText().equals(fieldName.getDefaultText())
                                && fieldName.getForeground() == Setting.COLOR_GRAY_03)) {
                    JOptionPane.showMessageDialog(applicationFrame, "Bạn chưa nhập Họ và tên",
                            "Invalid input",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    Plan plan = new Plan(fieldName.getText(), subjects, indexConversionTable);
                    WriteFile.createNewPlan(plan);
                    JOptionPane.showMessageDialog(applicationFrame, "Tạo kế hoạch thành công",
                            "Create plan successed",
                            JOptionPane.WARNING_MESSAGE);
                    // return screen create new plan 2
                    getParentScreen().getScreenCreateNewPlan3().setVisible(false);
                    getParentScreen().getMainScreen().setVisible(true);
                    // return screen create new plan 1
                    getParentScreen().getParentScreen().getMainScreen().setVisible(true);
                    getParentScreen().getParentScreen().getScreenCreateNewPlan2().setVisible(false);
                    // return screen plans
                    getParentScreen().getParentScreen().getParentScreen().getScreenCreateNewPlan1().setVisible(false);
                    getParentScreen().getParentScreen().getParentScreen().getMainScreen().setVisible(true);
                }
            }
            // Press at "Back" in mainScreen
            else if (event.getSource() == buttons[0]) {
                getParentScreen().getMainScreen().setVisible(true);
                getParentScreen().getScreenCreateNewPlan3().setVisible(false);
            }
        }
    }

}
