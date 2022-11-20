package code;

import java.awt.Graphics;
import javax.swing.JPanel;

public class ScreenCreateNewPlan1 extends JPanel {
    // Properties and Objects
    private String[] buttonTexts = {
            "Quay lại", "Tiếp theo"
    };
    private Button[] buttons;

    // Constructor
    public ScreenCreateNewPlan1(int width, int height) {
        // Set basic properties
        setLayout(null);
        setSize(width, height);
        setBounds(0, 0, width, height);

        // Create buttons
        buttons = new Button[buttonTexts.length];
        for (int count = 0; count < buttonTexts.length; count++) {
            buttons[count] = new Button(buttonTexts[count]);
            buttons[count].setFont(
                    Setting.FONT_NAME_01,
                    Setting.FONT_STYLE_01,
                    Setting.FONT_SIZE_MEDIUM);
            add(buttons[count]);
        }

        // Set location for each button
        buttons[0].setLocation(width / 16, height / 8 * 7, Button.BOTTOM_LEFT);
        buttons[1].setLocation(width / 16 * 15, height / 8 * 7, Button.BOTTOM_RIGHT);
    }

    public Button[] getButtons() {
        return this.buttons;
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
