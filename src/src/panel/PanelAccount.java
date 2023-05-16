package src.panel;

import javax.swing.JPanel;

import src.launcher.Setting;
import src.objects.Account;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class PanelAccount extends JPanel {
    // Properties
    private JPanel headerPanel;
    private JPanel contentPanel;
    private JPanel scrollPanel;
    private int cursorScroll = 0;

    public PanelAccount(int x, int y, int width, int height, Account account) {
        setLayout(null);
        setSize(width, height);
        setBounds(x, y, width, height);

        if (account == null) {
            account = new Account("", "", "", "");
        }

        // panels
        headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setSize(width, height / 6);
        headerPanel.setBounds(0, 0, width, headerPanel.getHeight());

        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setSize(width, height - headerPanel.getHeight());
        contentPanel.setBounds(0, headerPanel.getHeight(), width, contentPanel.getHeight());

        // panels in headerPanel
        String[] titles = {
                "Thông tin tài khoản",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
        };
        PanelString panelTitle = new PanelString(width / 2, headerPanel.getHeight() / 2, titles, width,
                new Font(Setting.FONT_NAME_01,
                        Setting.FONT_STYLE_01,
                        Setting.FONT_SIZE_MEDIUM),
                PanelString.CENTER_CENTER, 0);

        // Panels in scrollPanel
        int tempHeight = 10;
        PanelString panelName = new PanelString(width / 2, tempHeight, "Họ và tên:      " + account.getName(),
                width, new Font(Setting.FONT_NAME_01,
                        Setting.FONT_STYLE_01,
                        Setting.FONT_SIZE_SMALL),
                PanelString.TOP_CENTER, width / 8);
        tempHeight += panelName.getHeight() + 10;
        PanelString panelUsername = new PanelString(width / 2, tempHeight,
                "Tên đăng nhập: " + account.getUsername(),
                width,
                new Font(Setting.FONT_NAME_01,
                        Setting.FONT_STYLE_01,
                        Setting.FONT_SIZE_SMALL),
                PanelString.TOP_CENTER, width / 8);
        tempHeight += panelUsername.getHeight() + 10;
        PanelString panelTimeAccountCreated = new PanelString(width / 2, tempHeight,
                "Thời gian khởi tạo: " + account.getTimeAccountCreated(),
                width,
                new Font(Setting.FONT_NAME_01,
                        Setting.FONT_STYLE_01,
                        Setting.FONT_SIZE_SMALL),
                PanelString.TOP_CENTER, width / 8);
        tempHeight += panelTimeAccountCreated.getHeight() + 10;
        PanelString panelNumberPlans = new PanelString(width / 2, tempHeight,
                "Số lượng kế hoạch: " + account.getPlans().size(),
                width,
                new Font(Setting.FONT_NAME_01,
                        Setting.FONT_STYLE_01,
                        Setting.FONT_SIZE_SMALL),
                PanelString.TOP_CENTER, width / 8);
        tempHeight += panelNumberPlans.getHeight() + 10;

        // set background color for panel
        panelName.setBackground(Setting.COLOR_GRAY_03);
        panelUsername.setBackground(Setting.COLOR_GRAY_03);
        panelTimeAccountCreated.setBackground(Setting.COLOR_GRAY_03);
        panelNumberPlans.setBackground(Setting.COLOR_GRAY_03);

        // panels
        scrollPanel = new JPanel();
        scrollPanel.setLayout(null);
        scrollPanel.setSize(width, Math.max(tempHeight, contentPanel.getHeight()));
        scrollPanel.setBounds(0, 0 - cursorScroll, width, scrollPanel.getHeight());

        setBackground(Setting.COLOR_GRAY_04);
        scrollPanel.setBackground(getBackground());
        headerPanel.setBackground(getBackground());
        panelTitle.setBackground(headerPanel.getBackground());

        // add panels
        scrollPanel.add(panelName);
        scrollPanel.add(panelUsername);
        scrollPanel.add(panelTimeAccountCreated);
        scrollPanel.add(panelNumberPlans);

        headerPanel.add(panelTitle);
        contentPanel.add(scrollPanel);

        add(headerPanel);
        add(contentPanel);

        // Add MouseWheelListener to this panel
        addMouseWheelListener(new MouseWheelHandler());
    }

    // Update contentPanel
    public void updateContentShowing() {
        scrollPanel.setBounds(0, -cursorScroll, scrollPanel.getWidth(), scrollPanel.getHeight());
    }

    // get CursorScroll
    public int getCursorScroll() {
        return this.cursorScroll;
    }

    // Get max cursorScroll
    public int getMaxCursorScroll() {
        return Math.max(0, this.scrollPanel.getHeight() - this.contentPanel.getHeight());
    }

    // set cursorScroll
    public void setCurscorScroll(int value) {
        if (value >= 0 && value <= getMaxCursorScroll()) {
            this.cursorScroll = value;
        }
        updateContentShowing();
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private class MouseWheelHandler implements MouseWheelListener {
        public void mouseWheelMoved(MouseWheelEvent event) {
            setCurscorScroll(getCursorScroll() + event.getWheelRotation() * 20);
            updateContentShowing();
        }
    }
}
