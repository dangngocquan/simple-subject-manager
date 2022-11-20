package code;

import javax.swing.JList;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;

public class ScreenCurriculumInformation extends JPanel {
    // Properties and Objects
    private String[] buttonTexts = {
            "Quay lại"
    };
    private Button[] buttons;
    private JList subjectList;
    private JPanel headPanel, bodyPanel;
    private JPanel headPanel1, headPanel2;
    private JPanel bodyPanel0, bodyPanel1, bodyPanel2, bodyPanel3;

    // Constructor
    public ScreenCurriculumInformation(int width, int height) {
        // Set basic properties
        setLayout(null);
        setSize(width, height);
        setBounds(0, 0, width, height);

        // Create panels and basic properties for them
        headPanel = new JPanel();
        headPanel.setLayout(null);
        headPanel.setSize(width, height / 8);
        headPanel.setBounds(0, 0, headPanel.getWidth(), headPanel.getHeight());

        bodyPanel = new JPanel();
        bodyPanel.setLayout(null);
        bodyPanel.setSize(width, height - headPanel.getHeight());
        bodyPanel.setBounds(0, headPanel.getHeight(), bodyPanel.getWidth(), bodyPanel.getHeight());

        headPanel1 = new JPanel();
        headPanel1.setLayout(null);
        headPanel1.setSize(headPanel.getWidth() / 16 * 3, headPanel.getHeight());
        headPanel1.setBounds(0, 0, headPanel1.getWidth(), headPanel1.getHeight());

        headPanel2 = new JPanel();
        headPanel2.setLayout(null);
        headPanel2.setSize(headPanel.getWidth() - headPanel1.getWidth(), headPanel.getHeight());
        headPanel2.setBounds(headPanel1.getWidth(), 0, headPanel2.getWidth(), headPanel2.getHeight());

        bodyPanel0 = new JPanel();
        bodyPanel0.setLayout(null);
        bodyPanel0.setSize(bodyPanel.getWidth() / 16 * 15, bodyPanel.getHeight() / 16 * 15);
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
                    Setting.FONT_SIZE_MEDIUM);
        }

        // Set location for each button
        buttons[0].setLocation(headPanel1.getWidth() / 2, headPanel1.getHeight() / 2, Button.CENTER_CENTER);

        // Relative between panels and buttons
        add(headPanel);
        add(bodyPanel);
        headPanel.add(headPanel1);
        headPanel.add(headPanel2);
        headPanel1.add(buttons[0]);
        bodyPanel.add(bodyPanel0);
    }

    // Get buttons
    public Button[] getButtons() {
        return this.buttons;
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        headPanel1.setBackground(new Color(0, 0, 0));
        bodyPanel.setBackground(new Color(100, 0, 0));
    }
}
