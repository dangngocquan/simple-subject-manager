package src.panel;

import javax.swing.JPanel;

import src.Setting;
import src.objects.Subject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class PanelSubject2 extends JPanel {
        // Properties
        private JPanel mainPanel;
        private PanelString panelOrder;
        private PanelString panelCode;
        private PanelString panelName;
        private PanelString panelCredit;
        private PanelString panelScore10;
        private PanelString panelCharacterScore;
        private PanelString panelScore4;

        // Constructor
        public PanelSubject2(int x, int y, Subject subject, int width, Font font, int index) {
                // Create defaulr font
                if (font == null) {
                        font = new Font(Setting.FONT_NAME_01,
                                        Setting.FONT_STYLE_01,
                                        Setting.FONT_SIZE_SMALL);
                }

                // Create panels
                panelOrder = new PanelString(0, 0, index + "", width / 24 * 2, font,
                                PanelString.TOP_LEFT, 15);
                panelCode = new PanelString(panelOrder.getWidth(), 0, subject.getCode(), width / 24 * 2, font,
                                PanelString.TOP_LEFT, 15);
                panelName = new PanelString(panelCode.getX() + panelCode.getWidth(), 0, subject.getName(),
                                width / 24 * 9, font,
                                PanelString.TOP_LEFT, 15);
                panelCredit = new PanelString(panelName.getX() + panelName.getWidth(), 0,
                                subject.getNumberCredits() + "",
                                width / 24 * 2, font,
                                PanelString.TOP_LEFT, 0);
                panelScore10 = new PanelString(panelCredit.getX() + panelCredit.getWidth(), 0,
                                subject.getStringScore10(),
                                width / 24 * 3, font,
                                PanelString.TOP_LEFT, 0);
                panelCharacterScore = new PanelString(panelScore10.getX() + panelScore10.getWidth(), 0,
                                subject.getCharacterScore(),
                                width / 24 * 3, font,
                                PanelString.TOP_LEFT, 0);
                panelScore4 = new PanelString(panelCharacterScore.getX() + panelCharacterScore.getWidth(), 0,
                                subject.getStringScore4(),
                                width - panelOrder.getWidth() - panelCode.getWidth() - panelName.getWidth()
                                                - panelCredit.getWidth() - panelScore10.getWidth()
                                                - panelCharacterScore.getWidth(),
                                font, PanelString.TOP_LEFT, 0);

                // Height of mainPanel
                int height = panelCharacterScore.getHeight();

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
                mainPanel.add(panelScore10);
                mainPanel.add(panelCharacterScore);
                mainPanel.add(panelScore4);

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
                panelScore10.setBackground(color);
                panelCharacterScore.setBackground(color);
                panelScore4.setBackground(color);
        }

        // Auto called method of JPanel
        public void paintComponent(Graphics g) {
                super.paintComponent(g);
        }
}
