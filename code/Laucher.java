package code;

import javax.swing.JOptionPane;

import code.file_handler.ReadFile;
import code.file_handler.WriteFile;
import code.objects.Account;

public class Laucher {
    public static void main(String[] args) {
        Application application = new Application();
        application.setVisible(true);

        // Check if don't have any account before
        int count = 0;
        String message = "Đây là lần đầu bạn truy cập ứng dụng.\nHãy tạo một tài khoản.\nHọ và tên của bạn là gì thế?";
        while (ReadFile.getNumberExistingAccounts() == 0 && count < 3) {
            if (count > 0) {
                message = "Tên không được để trống !!!\nHọ và tên của bạn là gì vậy?";
            }
            String name = "";
            name = JOptionPane.showInputDialog(application,
                    message, "Create new account", JOptionPane.WARNING_MESSAGE);
            if (name == null || name.isEmpty()) {
                count++;
            } else {
                Account account = new Account(name);
                WriteFile.createNewAccount(account);
                break;
            }
        }
        if (count >= 3) {
            application.dispose();
        }
    }
}
