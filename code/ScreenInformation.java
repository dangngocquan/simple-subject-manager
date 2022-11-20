package code;

import javax.swing.JPanel;
import java.awt.Graphics;

public class ScreenInformation extends JPanel {
    // Properties and Objects
    private String[] buttonTexts = {
            "Khung chương trình đào tạo", "Quay lại"
    };
    private Button[] buttons;

    // Constructor
    public ScreenInformation(int width, int height) {
        // Set basic properties for this screen
        setLayout(null);
        setBounds(0, 0, width, height);
        setSize(width, height);

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
        buttons[0].setLocation(width / 2, height / 12 * 5, Button.TOP_CENTER);
        buttons[1].setLocation(width / 2, height / 12 * 7, Button.TOP_CENTER);
    }

    // Get buttons
    public Button[] getButtons() {
        return this.buttons;
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
