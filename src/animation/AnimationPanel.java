package src.animation;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AnimationPanel {
    // Properties
    private JPanel panel;
    private int xFrom, yFrom, xTo, yTo;
    private int runtime = 1000;
    private double x, y;
    private long startMillis;
    private int delay = 0;

    // Constructor
    public AnimationPanel(JPanel panel, int xFrom, int yFrom, int xTo, int yTo, int runtime) {
        panel.setBounds((int) xFrom, (int) yFrom, panel.getWidth(), panel.getHeight());
        this.panel = panel;
        this.xFrom = xFrom;
        this.yFrom = yFrom;
        this.xTo = xTo;
        this.yTo = yTo;
        this.x = xFrom;
        this.y = yFrom;
        this.runtime = runtime;
        this.delay = 0;
    }

    // Constructor
    public AnimationPanel(JPanel panel, int xFrom, int yFrom, int xTo, int yTo, int runtime, int timeDelay) {
        panel.setBounds((int) xFrom, (int) yFrom, panel.getWidth(), panel.getHeight());
        this.panel = panel;
        this.xFrom = xFrom;
        this.yFrom = yFrom;
        this.xTo = xTo;
        this.yTo = yTo;
        this.x = xFrom;
        this.y = yFrom;
        this.runtime = runtime;
        this.delay = timeDelay;
    }

    public void start() {
        int duration = 1;
        Timer timer = new Timer(duration,
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        double process = (System.currentTimeMillis() - startMillis - delay) * 1.0 / runtime;
                        if (process < 0) {
                            return;
                        }
                        if (process > 1f) {
                            process = 1f;
                            x = xTo;
                            y = yTo;
                            ((JPanel) panel).repaint();
                            ((Timer) event.getSource()).stop();
                        }
                        x = xFrom + (xTo - xFrom) * process;
                        y = yFrom + (yTo - yFrom) * process;
                        panel.setBounds((int) x, (int) y, panel.getWidth(), panel.getHeight());
                    }
                });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        this.startMillis = System.currentTimeMillis();
        timer.start();
    }
}
