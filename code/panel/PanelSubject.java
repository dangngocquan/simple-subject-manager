package code.panel;

import javax.swing.JLabel;
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
    public PanelSubject(Subject subject, int width) {
        // Create pattern button to get height
        Button button = new Button("");
        button.setFont(new Font(Setting.FONT_NAME_01,
                Setting.FONT_STYLE_01,
                Setting.FONT_SIZE_VERY_SMALL));

        // Properties of this panel
        this.height = button.getHeight() * subject.getParentSubjectCodes().size();
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
        panelCode.setLayout(new BorderLayout());
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
        panelParentSubjectCodes.setBounds(panelCode.getWidth() + panelName.getWidth() + panelCredit.getWidth(), 0,
                panelParentSubjectCodes.getWidth(), panelParentSubjectCodes.getHeight());

        // Add String to panels
        panelCode.add(new JLabel(subject.getCode()), BorderLayout.CENTER);

        // Relative between panels
        mainPanel.add(panelCode);
        mainPanel.add(panelName);
        mainPanel.add(panelCredit);
        mainPanel.add(panelParentSubjectCodes);

        // Add mainPanel to this panel
        add(mainPanel);

    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
