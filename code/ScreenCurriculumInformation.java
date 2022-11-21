package code;

// import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Color;

public class ScreenCurriculumInformation extends JPanel {
    // Properties, Objects and Screens
    private ScreenInformation parentScreen;
    private JPanel mainScreen;
    private String[] buttonTexts = {
            "Quay lại"
    };
    private Button[] buttons;
    private JPanel headPanel, bodyPanel;
    private JPanel headPanel1, headPanel2;
    private JPanel bodyPanel0;

    // Constructor
    public ScreenCurriculumInformation(int width, int height, ScreenInformation parentScreen) {
        // Set basic properties
        setLayout(null);
        setSize(width, height);
        setBounds(0, 0, width, height);
        this.parentScreen = parentScreen;

        // Create screens
        mainScreen = new JPanel();
        mainScreen.setLayout(null);
        mainScreen.setSize(width, height);
        mainScreen.setBounds(0, 0, width, height);

        // Create sub panels of "mainScreen"
        headPanel = new JPanel();
        headPanel.setLayout(null);
        headPanel.setSize(width, height / 8);
        headPanel.setBounds(0, 0, headPanel.getWidth(), headPanel.getHeight());

        bodyPanel = new JPanel();
        bodyPanel.setLayout(null);
        bodyPanel.setSize(width, height - headPanel.getHeight());
        bodyPanel.setBounds(0, headPanel.getHeight(), bodyPanel.getWidth(),
                bodyPanel.getHeight());

        // Create sub panels of "headPanel"
        headPanel1 = new JPanel();
        headPanel1.setLayout(null);
        headPanel1.setSize(headPanel.getWidth() / 16 * 3, headPanel.getHeight());
        headPanel1.setBounds(0, 0, headPanel1.getWidth(), headPanel1.getHeight());

        headPanel2 = new JPanel();
        headPanel2.setLayout(null);
        headPanel2.setSize(headPanel.getWidth() - headPanel1.getWidth(),
                headPanel.getHeight());
        headPanel2.setBounds(headPanel1.getWidth(), 0, headPanel2.getWidth(),
                headPanel2.getHeight());

        // Create sub panels of "bodyPanel"
        bodyPanel0 = new JPanel();
        bodyPanel0.setLayout(null);
        bodyPanel0.setSize(bodyPanel.getWidth() / 16 * 15, bodyPanel.getHeight() / 16
                * 15);
        bodyPanel0.setBounds(bodyPanel.getWidth() / 2 - bodyPanel0.getWidth() / 2,
                bodyPanel.getHeight() / 2 - bodyPanel0.getHeight() / 2,
                bodyPanel0.getWidth(), bodyPanel0.getHeight());

        // Create buttons
        buttons = new Button[buttonTexts.length];
        for (int count = 0; count < buttonTexts.length; count++) {
            buttons[count] = new Button(buttonTexts[count]);
            buttons[count].setFont(
                    Setting.FONT_NAME_01,
                    Setting.FONT_STYLE_01,
                    Setting.FONT_SIZE_MEDIUM);
            buttons[count].addActionListener(new ButtonHandler());
        }

        // Set location for each button
        buttons[0].setLocation(headPanel1.getWidth() / 2, headPanel1.getHeight() / 2,
                Button.CENTER_CENTER);

        // Add sub panels and buttons to "mainScreen"
        mainScreen.add(headPanel);
        mainScreen.add(bodyPanel);
        headPanel.add(headPanel1);
        headPanel.add(headPanel2);
        headPanel1.add(buttons[0]);
        bodyPanel.add(bodyPanel0);
        bodyPanel0.add(
                new PanelMajor(0, 0, bodyPanel0.getWidth(), bodyPanel0.getHeight(), null,
                        PanelMajor.TOP_LEFT));

        // Add screens to this panel
        add(mainScreen);

        // Set visible of screens
        mainScreen.setVisible(true);

        // Set color and other
        headPanel1.setBackground(Color.BLACK);
        headPanel2.setBackground(Color.BLACK);
    }

    // Get buttons
    public Button[] getButtons() {
        return this.buttons;
    }

    // Get parentScreen
    public ScreenInformation getParentScreen() {
        return this.parentScreen;
    }

    // Get mainScreen
    public JPanel getMainScreen() {
        return this.mainScreen;
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    // Handler buttons
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // Press at "Back" of headPanel1 of headPanel of mainScreen
            if (event.getSource() == buttons[0]) {
                getParentScreen().getMainScreen().setVisible(true);
                getParentScreen().getScreenCurriculumInformation().setVisible(false);
            }
        }
    }
}
