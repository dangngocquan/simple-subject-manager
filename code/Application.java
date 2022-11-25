package code;

import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import code.curriculum.Data;
import code.dialog.DialogCreateNewAccount;
import code.file_handler.ReadFile;
import code.screen.ScreenMainMenu;

public class Application extends JFrame {
        // Properties and screens
        private int width = (int) (GraphicsEnvironment.getLocalGraphicsEnvironment()
                        .getMaximumWindowBounds().getWidth());
        private int height = (int) ((GraphicsEnvironment.getLocalGraphicsEnvironment()
                        .getMaximumWindowBounds().getHeight()));
        private ScreenMainMenu screenMainMenu;

        // Constructor
        public Application() {
                // Set properties
                super("HUS");
                setSize(width, height);
                setResizable(false);
                setAlwaysOnTop(false);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLayout(null);
                new Data();

                // Create screens
                screenMainMenu = new ScreenMainMenu(width - 14, height - 36, this);

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
                        new DialogCreateNewAccount(width / 2, height / 2,
                                        width / 2, height / 2, DialogCreateNewAccount.CENTER_CENTER,
                                        "Create new account",
                                        messages);
                }

                // Check again
                if (ReadFile.getNumberExistingAccounts() == 0) {
                        JOptionPane.showMessageDialog(this, "Ứng dụng sẽ đóng");
                        dispose();
                }

        }

}
