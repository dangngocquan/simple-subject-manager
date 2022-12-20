package code.animation;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AnimationPanel {
    // Properties
    private JPanel panel, panel2;
    private int xFrom, yFrom, xTo, yTo;
    private int x2From, y2From, x2To, y2To;
    private int runtime = 1000;
    private double x, y;
    private double x2, y2;
    private long startMillis;
    private int numberPanel;

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
        this.numberPanel = 1;
    }

    // Constructor
    public AnimationPanel(JPanel oldPanel, JPanel newPanel, int x1From, int y1From, int x1To, int y1To,
            int x2From, int y2From, int x2To, int y2To, int runtime) {
        oldPanel.setVisible(true);
        newPanel.setVisible(true);
        panel.setBounds((int) xFrom, (int) yFrom, panel.getWidth(), panel.getHeight());
        panel2.setBounds((int) x2From, (int) y2From, panel2.getWidth(), panel2.getHeight());
        this.panel = oldPanel;
        this.panel2 = newPanel;
        this.xFrom = x1From;
        this.yFrom = y1From;
        this.xTo = x1To;
        this.yTo = y1To;
        this.x2From = x2From;
        this.y2From = y2From;
        this.x2To = x2To;
        this.y2To = y2To;
        this.x = xFrom;
        this.y = yFrom;
        this.x2 = x2From;
        this.y2 = y2From;
        this.runtime = runtime;
        this.numberPanel = 2;
    }

    public void start() {
        // 1 panel join animation
        if (numberPanel == 1) {
            int duration = 1;
            Timer timer = new Timer(duration,
                    new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            double process = (System.currentTimeMillis() - startMillis) * 1.0 / runtime;
                            if (process > 1f) {
                                process = 1f;
                                x = xTo;
                                y = yTo;
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

            // 2 panel join animation
        } else if (numberPanel == 2) {
            int duration = 1;
            Timer timer = new Timer(duration,
                    new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            double process = (System.currentTimeMillis() - startMillis) * 1.0 / runtime;
                            if (process > 1f) {
                                process = 1f;
                                x = xTo;
                                y = yTo;
                                x2 = x2To;
                                y2 = y2To;
                                ((Timer) event.getSource()).stop();
                            }
                            x = xFrom + (xTo - xFrom) * process;
                            y = yFrom + (yTo - yFrom) * process;
                            panel.setBounds((int) x, (int) y, panel.getWidth(), panel.getHeight());
                            x2 = x2From + (x2To - x2From) * process;
                            y2 = y2From + (y2To - y2From) * process;
                            panel2.setBounds((int) x2, (int) y2, panel2.getWidth(), panel2.getHeight());
                        }
                    });
            timer.setRepeats(true);
            timer.setCoalesce(true);
            timer.setInitialDelay(0);
            this.startMillis = System.currentTimeMillis();
            timer.start();
        }

    }
}
