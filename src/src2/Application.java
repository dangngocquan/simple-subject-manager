package src2;

import src.dialog.DialogCreateNewAccount;
import src.screen.ScreenMainMenu;
import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
        private ScreenMainMenu screenMainMenu;

        // Constructor
        public Application() {
                // Set properties
                super("Simple Subjects Manager");
                UIManager.put("OptionPane.messageFont", new Font(src.launcher.Setting.FONT_NAME_01,
                                src.launcher.Setting.FONT_STYLE_01, src.launcher.Setting.FONT_SIZE_SMALL));
                UIManager.put("OptionPane.buttonFont", new Font(src.launcher.Setting.FONT_NAME_01,
                                src.launcher.Setting.FONT_STYLE_01, src.launcher.Setting.FONT_SIZE_SMALL));
                UIManager.put("OptionPane.font", new Font(src.launcher.Setting.FONT_NAME_01,
                                src.launcher.Setting.FONT_STYLE_01, src.launcher.Setting.FONT_SIZE_SMALL));
                setSize(Setting.WIDTH, Setting.HEIGHT);
                setResizable(false);
                setAlwaysOnTop(false);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLayout(null);
                ImageIcon icon = Setting.LOGO;
                setIconImage(icon.getImage());

                // Create screens
                screenMainMenu = new ScreenMainMenu(Setting.WIDTH - 14, Setting.HEIGHT - 36, this);

                // Add screens to frame
                add(screenMainMenu);

                // Set screen visible
                screenMainMenu.setVisible(true);

                setVisible(true);

                // Check if don't have any account before
                if (ReadFile.getNumberExistingAccounts() == 0) {
                        String[] messages = {
                                        "Có lẽ đây là lần đầu bạn dùng ứng dụng này.",
                                        "Hãy tạo một tài khoản để dễ xưng hô nào ^^" };
                        new DialogCreateNewAccount(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                        Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                        DialogCreateNewAccount.CENTER_CENTER,
                                        "Create new account",
                                        messages);
                        screenMainMenu.updateDescriptionPanel();
                        screenMainMenu.repaint();
                }
                // Check again
                if (ReadFile.getNumberExistingAccounts() == 0) {
                        JOptionPane.showMessageDialog(this, "Ứng dụng sẽ đóng");
                        dispose();
                }
        }

        // Get screen main menu
        public ScreenMainMenu getScreenMainMenu() {
                return this.screenMainMenu;
        }
}
