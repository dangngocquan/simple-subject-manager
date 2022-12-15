package code.screen;

import java.awt.Graphics;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import java.awt.Font;
import code.Application;
import code.Setting;
import code.objects.Button;
import code.objects.Major;
import code.panel.PanelMajor;
import code.panel.PanelMajorHasOptions;
import code.panel.PanelString;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ScreenCreateNewPlan2 extends JPanel {
    // Properties, Objects and Screens
    private Application applicationFrame;
    private String[] buttonTexts = {
            "Quay lại", "Tiếp theo"
    };
    private Button[] buttons;
    private JCheckBox checkbox;
    private ScreenCreateNewPlan1 parentScreen;
    private ScreenCreateNewPlan3 screenCreateNewPlan3 = null;
    private JPanel mainScreen, contentPanel;
    private Major major;
    private PanelMajorHasOptions panelMajor;
    private int indexConversionTable = 0;

    // Constructor
    public ScreenCreateNewPlan2(int width, int height, ScreenCreateNewPlan1 parentScreen, Application frame,
            Major major) {
        // Set basic properties
        this.applicationFrame = frame;
        this.major = major;
        setLayout(null);
        setSize(width, height);
        setBounds(0, 0, width, height);
        this.parentScreen = parentScreen;

        // Create screens
        mainScreen = new JPanel();
        mainScreen.setLayout(null);
        mainScreen.setSize(width, height);
        mainScreen.setBounds(0, 0, width, height);

        PanelString title = new PanelString(width / 2, height / 64 * 3,
                "Tiếp theo, hãy chọn những môn học mà bạn dự định học trong ngành này", width,
                new Font(Setting.FONT_NAME_01,
                        Setting.FONT_STYLE_01,
                        Setting.FONT_SIZE_MEDIUM),
                PanelString.CENTER_CENTER, 0);

        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setSize(width / 20 * 19, height / 8 * 6);
        contentPanel.setBounds(width / 40, height / 32 * 3, contentPanel.getWidth(), contentPanel.getHeight());

        // Create buttons
        buttons = new Button[buttonTexts.length];
        for (int count = 0; count < buttonTexts.length; count++) {
            buttons[count] = new Button(buttonTexts[count]);
            buttons[count].setFontText(Button.SERIF_BOLD_28);
            buttons[count].setCorrectSizeButton();
            buttons[count].addMouseListener(new MouseHandler());
        }

        // Create checkbox
        checkbox = new JCheckBox("Tự động chọn những môn học bắt buộc");
        checkbox.setFont(buttons[0].getFont());
        checkbox.setFocusPainted(false);
        checkbox.addItemListener(new ItemHandler());

        // Update content
        updateContentPanel();

        // create screenCreateNewPlan3 (need panelMajor of screen 2)
        this.screenCreateNewPlan3 = new ScreenCreateNewPlan3(width, height, this, frame,
                panelMajor.getListSubjectSelected());

        // Set location for each button
        buttons[0].setLocationButton(width / 16, height / 32 * 31, Button.BOTTOM_LEFT);
        buttons[1].setLocationButton(width / 16 * 15, height / 32 * 31, Button.BOTTOM_RIGHT);
        checkbox.setSize(width / 3, buttons[0].getHeight());
        checkbox.setLocation(width / 24 * 9, buttons[0].getY());

        // Add buttons to mainScreen
        for (Button button : buttons) {
            mainScreen.add(button);
        }
        mainScreen.add(checkbox);

        // Add subpanels
        mainScreen.add(title);
        mainScreen.add(contentPanel);

        contentPanel.setBackground(Setting.COLOR_GREEN_02);

        // Add screens to this panel
        add(mainScreen);
        add(screenCreateNewPlan3);

        // Set visible of screens
        mainScreen.setVisible(true);
        screenCreateNewPlan3.setVisible(false);
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

    // Get parent screen of this panel
    public ScreenCreateNewPlan1 getParentScreen() {
        return this.parentScreen;
    }

    // Get parent screen create plan 3
    public ScreenCreateNewPlan3 getScreenCreateNewPlan3() {
        return this.screenCreateNewPlan3;
    }

    // Get index conversion
    public int getIndexConversion() {
        return this.indexConversionTable;
    }

    // Update content panels
    public void updateContentPanel() {
        contentPanel.removeAll();
        this.panelMajor = new PanelMajorHasOptions(0, 0, contentPanel.getWidth(), contentPanel.getHeight(),
                major, PanelMajor.TOP_LEFT, applicationFrame);
        contentPanel.add(panelMajor);
        repaint();
    }

    // Set index conversion table
    public void setIndexConversionTable(int index) {
        this.indexConversionTable = index;
    }

    // Set major
    public void setMajor(Major major) {
        this.major = major;
        updateContentPanel();
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    //
    private class ItemHandler implements ItemListener {
        public void itemStateChanged(ItemEvent event) {
            // Press checkbox "Auto selection"
            if (event.getSource() == checkbox) {
                panelMajor.setSelectedCompulsorySubject(checkbox.isSelected());
                panelMajor.updateBackgroundSelectedPanels();
            }
        }
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            // Press at "Back" in mainScreen
            if (event.getSource() == buttons[0]) {
                getParentScreen().getMainScreen().setVisible(true);
                getParentScreen().getScreenCreateNewPlan2().setVisible(false);
                checkbox.setSelected(false);

            }
            // Press at "Next" in mainScreen
            else if (event.getSource() == buttons[1]) {
                if (panelMajor.isValidPlan()) {
                    getScreenCreateNewPlan3().setListSubjectSelected(panelMajor.getListSubjectSelected());
                    getScreenCreateNewPlan3().setIndexConversionTable(indexConversionTable);
                    getScreenCreateNewPlan3().setVisible(true);
                    getMainScreen().setVisible(false);
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
