package code.panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import code.Setting;
import code.objects.ConversionTable;
import code.objects.Subject;
import java.awt.Graphics;
import java.awt.Font;

public class PanelCalculateLastTerm3 extends JPanel {
    // Properties
    private JFrame applicationFrame;
    private JPanel mainPanel;

    // Constructor
    public PanelCalculateLastTerm3(int x, int y, int width, int height, Font font, ConversionTable convertionTable,
            JFrame applicationFrame) {
        this.applicationFrame = applicationFrame;
        // Create defaulr font
        if (font == null) {
            font = new Font(Setting.FONT_NAME_01,
                    Setting.FONT_STYLE_01,
                    Setting.FONT_SIZE_SMALL);
        }

        // Create panels

        // mainPanel
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setSize(width, height);
        mainPanel.setBounds(0, 0, mainPanel.getWidth(), mainPanel.getHeight());

        // Relative between panels

        // Properties of this panel
        setLayout(null);
        setSize(width, height);
        setBounds(x, y, getWidth(), getHeight());

        // Add mainPanel to this panel
        add(mainPanel);
    }

    // Set background Color
    public void setBackgroundColorPanelSubject(Color color) {
        mainPanel.setBackground(color);

    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
