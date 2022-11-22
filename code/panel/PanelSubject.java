package code.panel;

// import javax.swing.JLabel;
import javax.swing.JPanel;

import code.Setting;
import code.curriculum.Subject;

import java.awt.Graphics;
import java.awt.Font;

public class PanelSubject extends JPanel {
        // Properties
        private JPanel mainPanel;
        private PanelString panelCode;
        private PanelString panelName;
        private PanelString panelCredit;
        private PanelString panelParentSubjectCodes;

        // Constructor
        public PanelSubject(int x, int y, Subject subject, int width, Font font) {
                // Create defaulr font
                if (font == null) {
                        font = new Font(Setting.FONT_NAME_01,
                                        Setting.FONT_STYLE_01,
                                        Setting.FONT_SIZE_SMALL);
                }

                // Create panels
                panelParentSubjectCodes = new PanelString(width - width / 12 * 4, 0,
                                subject.getParentSubjectCodes(), width / 12 * 4, font);

                panelCredit = new PanelString((width - panelParentSubjectCodes.getWidth()) / 8 * 7, 0,
                                subject.getNumberCredits() + "", (width - panelParentSubjectCodes.getWidth()) / 8,
                                font);

                panelName = new PanelString((width - panelParentSubjectCodes.getWidth() - panelCredit.getWidth()) / 7,
                                0, subject.getName(),
                                (width - panelParentSubjectCodes.getWidth() - panelCredit.getWidth()) / 7 * 6, font);

                panelCode = new PanelString(0, 0, subject.getCode(),
                                (width - panelParentSubjectCodes.getWidth() - panelCredit.getWidth()) / 7, font);

                // Height of mainPanel
                int height = panelParentSubjectCodes.getHeight();

                // mainPanel
                mainPanel = new JPanel();
                mainPanel.setLayout(null);
                mainPanel.setSize(width, height);
                mainPanel.setBounds(0, 0, mainPanel.getWidth(), mainPanel.getHeight());

                // Relative between panels
                mainPanel.add(panelCode);
                mainPanel.add(panelName);
                mainPanel.add(panelCredit);
                mainPanel.add(panelParentSubjectCodes);

                // Properties of this panel
                setLayout(null);
                setSize(width, height);
                setBounds(x, y, getWidth(), getHeight());

                // Add mainPanel to this panel
                add(mainPanel);
        }

        // Auto called method of JPanel
        public void paintComponent(Graphics g) {
                super.paintComponent(g);
        }

}
