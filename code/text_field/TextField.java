package code.text_field;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Shape;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import code.Setting;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.geom.RoundRectangle2D;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class TextField extends JTextField {
    private Shape shape;
    private int thickness;
    private int arcWidth, arcHeight;

    // Constructor
    public TextField(int x, int y, int width, int height, String defaultText, int thickness, int arcWidth,
            int arcHeight) {
        super(defaultText);
        this.thickness = thickness;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setOpaque(false);
        setLayout(null);
        setFont(new Font(
                Setting.FONT_NAME_01,
                Setting.FONT_STYLE_01,
                Setting.FONT_SIZE_SMALL));
        setSize(width, height);
        setBounds(x, y, width, height);
        setForeground(Setting.COLOR_GRAY_03);
        setBorder(new LineBorder(Setting.COLOR_GRAY_03, thickness, true));
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent event) {
                if (getText().equals(defaultText) && getForeground() == Setting.COLOR_GRAY_03) {
                    setForeground(Setting.COLOR_BLACK);
                    setText("");
                }
                setBorder(new LineBorder(Setting.COLOR_BLACK, thickness, true));
                super.focusGained(event);
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (getText().isEmpty()) {
                    setForeground(Setting.COLOR_GRAY_03);
                    setText(defaultText);
                }
                setBorder(new LineBorder(Setting.COLOR_GRAY_03, thickness, true));
                super.focusLost(event);
            }
        });
    }

    // Get input data
    public String getInputString() {
        String input = "";
        if (getForeground() == Setting.COLOR_BLACK) {
            input = getText();
        }
        return input;
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(thickness - 1, thickness - 1, getWidth() - 2 * thickness, getHeight() - 2 * thickness, arcWidth,
                15);
        super.paintComponent(g);
    }

    public void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getForeground());
        g2.setStroke(new BasicStroke(thickness));
        g.drawRoundRect(thickness - 1, thickness - 1, getWidth() - 2 * thickness, getHeight() - 2 * thickness, arcWidth,
                arcHeight);
    }

    public boolean contains(int x0, int y0) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth(),
                    getHeight(), arcWidth, arcHeight);
        }
        return shape.contains(x0, y0);
    }
}
