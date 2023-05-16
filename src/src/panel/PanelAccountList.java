package src.panel;

import java.util.LinkedList;
import java.util.List;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;

import src.launcher.ReadFile;
import src.launcher.Setting;
import src.objects.Account;
import src.objects.Button;
import src.screen.ScreenExistingAccounts;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class PanelAccountList extends JPanel {
    public static final Color COLOR_ACCOUNT_1 = Setting.COLOR_GRAY_05;
    public static final Color COLOR_ACCOUNT_2 = Setting.COLOR_GRAY_04;

    public static final Color COLOR_ACCOUNT_ENTERED = Setting.COLOR_VIOLET_03;
    public static final Color COLOR_ACCOUNT_EXITED_1 = Setting.COLOR_GRAY_05;
    public static final Color COLOR_ACCOUNT_EXITED_2 = Setting.COLOR_GRAY_04;
    public static final Color COLOR_ACCOUNT_PRESSED = Setting.COLOR_GREEN_03;
    // Properties
    private ScreenExistingAccounts parentScreen;
    private JPanel headerPanel;
    private JPanel contentPanel;
    private JPanel scrollPanel;
    private int cursorScroll = 0;
    private List<JPanel> accounts = new LinkedList<>();
    private List<JPanel> orderNumbers = new LinkedList<>();
    private List<JPanel> times = new LinkedList<>();
    private List<JPanel> names = new LinkedList<>();
    private List<Account> accountList;
    private int indexPanelAccountPressing = ReadFile.getIndexCurrentAccount();

    public PanelAccountList(int x, int y, int width, int height, List<Account> accountList,
            ScreenExistingAccounts parentScreen) {
        this.accountList = accountList;
        this.parentScreen = parentScreen;
        // Set properties of this panel
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
        titleOrderNumber.setFontText(new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_SMALL));
        titleOrderNumber.setLocationText(0, 0);
        titleOrderNumber.setEnable(false);
        titleOrderNumber.setStrokeWidth(0);
        titleOrderNumber.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_4, Setting.GRADIENT_POINTS2_4,
                Setting.GRADIENT_COLORS_4);
        titleOrderNumber.setSizeButton(headerPanel.getWidth() / 8, headerPanel.getHeight());
        titleOrderNumber.setBounds(0, 0, titleOrderNumber.getWidth(), titleOrderNumber.getHeight());

        Button titleName = new Button("Tên tài khoản");
        titleName.setFontText(new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_SMALL));
        titleName.setLocationText(15, 0);
        titleName.setEnable(false);
        titleName.setStrokeWidth(0);
        titleName.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_4, Setting.GRADIENT_POINTS2_4,
                Setting.GRADIENT_COLORS_4);
        titleName.setSizeButton(headerPanel.getWidth() / 8 * 4, headerPanel.getHeight());
        titleName.setBounds(titleOrderNumber.getWidth(), 0, titleName.getWidth(), titleName.getHeight());

        Button titleTimeAccountCreated = new Button("Ngày khởi tạo");
        titleTimeAccountCreated
                .setFontText(new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_SMALL));
        titleTimeAccountCreated.setLocationText(10, 0);
        titleTimeAccountCreated.setEnable(false);
        titleTimeAccountCreated.setStrokeWidth(0);
        titleTimeAccountCreated.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_4, Setting.GRADIENT_POINTS2_4,
                Setting.GRADIENT_COLORS_4);
        titleTimeAccountCreated.setSizeButton(
                headerPanel.getWidth() - titleOrderNumber.getWidth() - titleName.getWidth(), headerPanel.getHeight());
        titleTimeAccountCreated.setBounds(titleOrderNumber.getWidth() + titleName.getWidth(), 0,
                titleTimeAccountCreated.getWidth(), titleTimeAccountCreated.getHeight());

        // Add title to headerPanel
        headerPanel.add(titleOrderNumber);
        headerPanel.add(titleName);
        headerPanel.add(titleTimeAccountCreated);

        // Scroll panel
        scrollPanel = new JPanel();
        contentPanel.add(scrollPanel);
        updateContentPanel(accountList);

        // Add panels to this panel
        add(headerPanel);
        add(contentPanel);

        addMouseWheelListener(new MouseWheelHandler());

    }

    // Get parentScreen
    public ScreenExistingAccounts getParentScreen() {
        return this.parentScreen;
    }

    // Update content
    public void updateContentPanel(List<Account> accountList) {
        contentPanel.remove(scrollPanel);
        accounts.clear();
        orderNumbers.clear();
        names.clear();
        times.clear();
        this.accountList = accountList;
        // Create contents for contentPanel
        int tempHeight = 0;
        accounts = new LinkedList<JPanel>();
        int count = 0;
        for (Account account : accountList) {
            PanelString numberOrder = new PanelString(0, 0, (count + 1) + "",
                    headerPanel.getWidth() / 8, null, PanelString.TOP_LEFT, 0);
            PanelString name = new PanelString(numberOrder.getWidth(), 0, account.getName(),
                    headerPanel.getWidth() / 8 * 4, null, PanelString.TOP_LEFT, 10);
            PanelString timeCreated = new PanelString(numberOrder.getWidth() + name.getWidth(), 0,
                    account.getTimeAccountCreated(),
                    headerPanel.getWidth() / 8 * 3, null, PanelString.TOP_LEFT, 10);
            // Set background color
            if (count == indexPanelAccountPressing) {
                numberOrder.setBackground(COLOR_ACCOUNT_PRESSED);
                name.setBackground(COLOR_ACCOUNT_PRESSED);
                timeCreated.setBackground(COLOR_ACCOUNT_PRESSED);
            } else {
                if (count % 2 == 0) {
                    numberOrder.setBackground(COLOR_ACCOUNT_1);
                    name.setBackground(COLOR_ACCOUNT_1);
                    timeCreated.setBackground(COLOR_ACCOUNT_1);
                } else {
                    numberOrder.setBackground(COLOR_ACCOUNT_2);
                    name.setBackground(COLOR_ACCOUNT_2);
                    timeCreated.setBackground(COLOR_ACCOUNT_2);
                }
            }

            // Add handler
            numberOrder.addMouseListener(new MouseHandler());
            name.addMouseListener(new MouseHandler());
            timeCreated.addMouseListener(new MouseHandler());

            // Create panel row
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setSize(headerPanel.getWidth(), numberOrder.getHeight());
            panel.setBounds(0, tempHeight, panel.getWidth(), panel.getHeight());
            panel.add(numberOrder);
            panel.add(name);
            panel.add(timeCreated);
            accounts.add(panel);
            orderNumbers.add(numberOrder);
            names.add(name);
            times.add(timeCreated);
            tempHeight += panel.getHeight();
            count++;
        }
        // Create scrollPanel
        scrollPanel = new JPanel();
        scrollPanel.setLayout(null);
        scrollPanel.setSize(headerPanel.getWidth(), tempHeight);
        scrollPanel.setBounds(0, 0 - cursorScroll, scrollPanel.getWidth(), scrollPanel.getHeight());

        // Add row accounts to scrollPanel
        for (JPanel panel : accounts) {
            scrollPanel.add(panel);
        }

        contentPanel.add(scrollPanel);
        repaint();
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

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            for (int count = 0; count < accounts.size(); count++) {
                if (e.getSource() == orderNumbers.get(count) ||
                        e.getSource() == names.get(count) ||
                        e.getSource() == times.get(count)) {
                    getParentScreen().updatePanelAccountInfor(accountList.get(count));
                    indexPanelAccountPressing = count;
                    orderNumbers.get(count).setBackground(COLOR_ACCOUNT_PRESSED);
                    names.get(count).setBackground(COLOR_ACCOUNT_PRESSED);
                    times.get(count).setBackground(COLOR_ACCOUNT_PRESSED);
                } else {
                    if (count % 2 == 0) {
                        orderNumbers.get(count).setBackground(COLOR_ACCOUNT_1);
                        names.get(count).setBackground(COLOR_ACCOUNT_1);
                        times.get(count).setBackground(COLOR_ACCOUNT_1);
                    } else {
                        orderNumbers.get(count).setBackground(COLOR_ACCOUNT_2);
                        names.get(count).setBackground(COLOR_ACCOUNT_2);
                        times.get(count).setBackground(COLOR_ACCOUNT_2);
                    }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            for (int count = 0; count < accounts.size(); count++) {
                if (e.getSource() == orderNumbers.get(count) ||
                        e.getSource() == names.get(count) ||
                        e.getSource() == times.get(count)) {
                    if (count == indexPanelAccountPressing) {
                        orderNumbers.get(count).setBackground(COLOR_ACCOUNT_PRESSED);
                        names.get(count).setBackground(COLOR_ACCOUNT_PRESSED);
                        times.get(count).setBackground(COLOR_ACCOUNT_PRESSED);
                    } else {
                        orderNumbers.get(count).setBackground(COLOR_ACCOUNT_ENTERED);
                        names.get(count).setBackground(COLOR_ACCOUNT_ENTERED);
                        times.get(count).setBackground(COLOR_ACCOUNT_ENTERED);
                    }

                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            for (int count = 0; count < accounts.size(); count++) {
                if (e.getSource() == orderNumbers.get(count) ||
                        e.getSource() == names.get(count) ||
                        e.getSource() == times.get(count)) {
                    if (count == indexPanelAccountPressing) {
                        orderNumbers.get(count).setBackground(COLOR_ACCOUNT_PRESSED);
                        names.get(count).setBackground(COLOR_ACCOUNT_PRESSED);
                        times.get(count).setBackground(COLOR_ACCOUNT_PRESSED);
                    } else {
                        if (count % 2 == 0) {
                            orderNumbers.get(count).setBackground(COLOR_ACCOUNT_EXITED_1);
                            names.get(count).setBackground(COLOR_ACCOUNT_EXITED_1);
                            times.get(count).setBackground(COLOR_ACCOUNT_EXITED_1);
                        } else {
                            orderNumbers.get(count).setBackground(COLOR_ACCOUNT_EXITED_2);
                            names.get(count).setBackground(COLOR_ACCOUNT_EXITED_2);
                            times.get(count).setBackground(COLOR_ACCOUNT_EXITED_2);
                        }
                    }

                }
            }
        }
    }
}
