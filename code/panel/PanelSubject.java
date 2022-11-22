package code.panel;

// import javax.swing.JLabel;
import javax.swing.JPanel;

import code.Setting;
import code.button.Button;
import code.curriculum.Subject;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.BorderLayout;

public class PanelSubject extends JPanel {
        // Properties
        private int width, height;
        private JPanel mainPanel;
        private JPanel panelCode;
        private JPanel panelName;
        private JPanel panelCredit;
        private JPanel panelParentSubjectCodes;

        // Constructor
        public PanelSubject(int x, int y, Subject subject, int width, Font font) {
                // Create pattern button to get height
                Button button = new Button("A");
                button.setFont(Setting.FONT_NAME_01,
                                Setting.FONT_STYLE_01,
                                Setting.FONT_SIZE_SMALL);

                // Properties of this panel
                this.height = button.getHeight() * (Math.max(subject.getParentSubjectCodes().size(), 1));
                this.width = width;
                setSize(this.width, this.height);
                setLayout(null);
                setBounds(0, 0, this.width, this.height);

                // Create panels
                mainPanel = new JPanel();
                mainPanel.setLayout(null);
                mainPanel.setSize(this.width, this.height);
                mainPanel.setBounds(0, 0, mainPanel.getWidth(), mainPanel.getHeight());

                panelCode = new JPanel();
                panelCode.setLayout(null);
                panelCode.setSize(this.width / 12, this.height);
                panelCode.setBounds(0, 0, panelCode.getWidth(), panelCode.getHeight());

                panelName = new JPanel();
                panelName.setLayout(new BorderLayout());
                panelName.setSize((this.width - panelCode.getWidth()) / 11 * 6,
                                this.height);
                panelName.setBounds(panelCode.getWidth(), 0, panelName.getWidth(), panelName.getHeight());

                panelCredit = new JPanel();
                panelCredit.setLayout(new BorderLayout());
                panelCredit.setSize((this.width - panelCode.getWidth() - panelName.getWidth()) / 5,
                                this.height);
                panelCredit.setBounds(panelCode.getWidth() + panelName.getWidth(), 0,
                                panelCredit.getWidth(), panelCredit.getHeight());

                panelParentSubjectCodes = new JPanel();
                panelParentSubjectCodes.setLayout(new BorderLayout());
                panelParentSubjectCodes.setSize(
                                this.width - panelCode.getWidth() - panelName.getWidth() - panelCredit.getWidth(),
                                this.height);
                panelParentSubjectCodes.setBounds(panelCode.getWidth() + panelName.getWidth() + panelCredit.getWidth(),
                                0,
                                panelParentSubjectCodes.getWidth(), panelParentSubjectCodes.getHeight());

                // Add String to panels
                updateText(subject, font);

                // Relative between panels
                mainPanel.add(panelCode);
                mainPanel.add(panelName);
                mainPanel.add(panelCredit);
                mainPanel.add(panelParentSubjectCodes);

                // Add mainPanel to this panel
                add(mainPanel);

                // Paint
                panelName.setBackground(Setting.COLOR_RED_04);

        }

        // Update text of Panel subject
        public void updateText(Subject subject, Font font) {
                panelCode.add(new PanelString(0, 0, subject.getCode(), panelCode.getWidth(), font));
                panelName.add(new PanelString(0, 0, subject.getName(), panelName.getWidth(), font));
                panelCredit.add(new PanelString(0, 0, subject.getNumberCredits() + "", panelCredit.getWidth(), font));
                panelParentSubjectCodes.add(new PanelString(0, 0, subject.getParentSubjectCodes(),
                                panelParentSubjectCodes.getWidth(), font));
        }

        // Auto called method of JPanel
        public void paintComponent(Graphics g) {
                super.paintComponent(g);
        }

}
