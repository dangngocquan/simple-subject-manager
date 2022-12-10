package code.panel;

import javax.swing.JPanel;
import java.awt.Color;
import code.Setting;
import code.objects.Subject;
import java.awt.Graphics;
import java.awt.Font;

public class PanelSubject3 extends JPanel {
        // Properties
        private JPanel mainPanel;
        private PanelString panelOrder;
        private PanelString panelCode;
        private PanelString panelName;
        private PanelString panelCredit;
        private PanelString panelStatus;

        // Constructor
        public PanelSubject3(int x, int y, Subject subject, int width, Font font, int index) {
                // Create defaulr font
                if (font == null) {
                        font = new Font(Setting.FONT_NAME_01,
                                        Setting.FONT_STYLE_01,
                                        Setting.FONT_SIZE_SMALL);
                }

                // Create panels
                panelOrder = new PanelString(0, 0, index + "", width / 24 * 2, font,
                                PanelString.TOP_LEFT, 15);
                panelCode = new PanelString(panelOrder.getWidth(), 0, subject.getCode(), width / 24 * 3, font,
                                PanelString.TOP_LEFT, 15);
                panelName = new PanelString(panelCode.getX() + panelCode.getWidth(), 0, subject.getName(),
                                width / 24 * 12, font,
                                PanelString.TOP_LEFT, 15);
                panelCredit = new PanelString(panelName.getX() + panelName.getWidth(), 0,
                                subject.getNumberCredits() + "",
                                width / 48 * 5, font,
                                PanelString.TOP_LEFT, 0);
                panelStatus = new PanelString(panelCredit.getX() + panelCredit.getWidth(), 0,
                                subject.getStringStatus(),
                                width - panelOrder.getWidth() - panelCode.getWidth() - panelName.getWidth()
                                                - panelCredit.getWidth(),
                                font, PanelString.TOP_LEFT, 0);
                if (subject.getState() == Subject.COMPLETED) {
                        panelStatus = new PanelString(panelCredit.getX() + panelCredit.getWidth(), 0,
                                        subject.getStringStatus(),
                                        width - panelOrder.getWidth() - panelCode.getWidth() - panelName.getWidth()
                                                        - panelCredit.getWidth(),
                                        font, PanelString.TOP_LEFT, 0, Setting.COLOR_GREEN_02);
                } else {
                        panelStatus = new PanelString(panelCredit.getX() + panelCredit.getWidth(), 0,
                                        subject.getStringStatus(),
                                        width - panelOrder.getWidth() - panelCode.getWidth() - panelName.getWidth()
                                                        - panelCredit.getWidth(),
                                        font, PanelString.TOP_LEFT, 0);
                }

                // Height of mainPanel
                int height = panelStatus.getHeight();

                // mainPanel
                mainPanel = new JPanel();
                mainPanel.setLayout(null);
                mainPanel.setSize(width, height);
                mainPanel.setBounds(0, 0, mainPanel.getWidth(), mainPanel.getHeight());

                // Relative between panels
                mainPanel.add(panelOrder);
                mainPanel.add(panelCode);
                mainPanel.add(panelName);
                mainPanel.add(panelCredit);
                mainPanel.add(panelStatus);

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
                panelOrder.setBackground(color);
                panelCode.setBackground(color);
                panelName.setBackground(color);
                panelCredit.setBackground(color);
                panelStatus.setBackground(color);
        }

        // Auto called method of JPanel
        public void paintComponent(Graphics g) {
                super.paintComponent(g);
        }
}
