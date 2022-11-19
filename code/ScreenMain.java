package code;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class ScreenMain extends JPanel {
    private int width, height;
    private Button planButton;

    public ScreenMain(int width, int height) {
        this.width = width;
        this.height = height;
        setLayout(null);
        setSize(width, height);

        planButton = new Button("Plans");
        planButton.setFont("Arial", Button.PLAIN, 24);
        planButton.setLocation(width / 2, height / 3, Button.TOP_CENTER);
        planButton.addActionListener(new PlanButtonHandler());
        add(planButton);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private class PlanButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            repaint();
        }
    }
}
