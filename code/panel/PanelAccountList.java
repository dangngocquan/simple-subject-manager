package code.panel;

import java.util.List;

import javax.swing.JPanel;
import java.awt.Graphics;
import code.Setting;
import code.objects.Account;
import code.objects.Button;

public class PanelAccountList extends JPanel {
    // Properties
    private JPanel headerPanel;
    private JPanel contentPanel;

    public PanelAccountList(int x, int y, int width, int height, List<Account> accountList) {
        // Set properties ò thí panel
        setLayout(null);
        setSize(width, height);
        setBounds(x, y, width, height);

        // panel
        headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setSize(width, height / 12);
        headerPanel.setBounds(0, 0, headerPanel.getWidth(), headerPanel.getHeight());

        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setSize(width, height - headerPanel.getHeight());
        contentPanel.setBounds(0, headerPanel.getHeight(), contentPanel.getWidth(), contentPanel.getHeight());

        // Titles of headerPanel
        Button titleOrderNumber = new Button("STT");
        titleOrderNumber.setFont(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_MEDIUM);
        titleOrderNumber.setBorderPainted(false);
        titleOrderNumber.setSizeButton(headerPanel.getWidth() / 6, headerPanel.getHeight(), false);
        titleOrderNumber.setBounds(0, 0, titleOrderNumber.getWidth(), titleOrderNumber.getHeight());

        Button titleName = new Button("Tên tài khoản");
        titleName.setFont(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_MEDIUM);
        titleName.setBorderPainted(false);
        titleName.setSizeButton(headerPanel.getWidth() / 6 * 3, headerPanel.getHeight(), false);
        titleName.setBounds(titleOrderNumber.getWidth(), 0, titleName.getWidth(), titleName.getHeight());

        Button titleTimeAccountCreated = new Button("Ngày khởi tạo");
        titleTimeAccountCreated.setFont(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_MEDIUM);
        titleTimeAccountCreated.setBorderPainted(false);
        titleTimeAccountCreated.setSizeButton(
                headerPanel.getWidth() - titleOrderNumber.getWidth() - titleName.getWidth(), headerPanel.getHeight(),
                false);
        titleTimeAccountCreated.setBounds(titleOrderNumber.getWidth() + titleName.getWidth(), 0,
                titleTimeAccountCreated.getWidth(), titleTimeAccountCreated.getHeight());

        // Add title to headerPanel
        headerPanel.add(titleOrderNumber);
        headerPanel.add(titleName);
        headerPanel.add(titleTimeAccountCreated);

        // Add panels to thí panel
        add(headerPanel);
        add(contentPanel);

    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
