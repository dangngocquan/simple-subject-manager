package code.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import code.Setting;
import code.objects.Button;
import code.panel.PanelString;
import code.text_field.TextField;

public class DialogCreateNewAccount {
    // Constants dialog's root location
    public static final int TOP_LEFT = 0;
    public static final int TOP_CENTER = 1;
    public static final int TOP_RIGHT = 2;
    public static final int CENTER_LEFT = 3;
    public static final int CENTER_CENTER = 4;
    public static final int CENTER_RIGHT = 5;
    public static final int BOTTOM_LEFT = 6;
    public static final int BOTTOM_CENTER = 7;
    public static final int BOTTOM_RIGHT = 8;

    // Properties
    private TextField fieldName = null, fieldUsername = null, fieldPassword = null;
    private Button button = null;
    JDialog dialog = null;

    // Constructor
    public DialogCreateNewAccount(int x, int y, int width, int height, int rootLocationType, String title,
            String[] messageLines) {
        // Create frame and set propertis of this frame
        JFrame f = new JFrame();
        dialog = new JDialog(f, title, true);
        dialog.setLayout(null);
        dialog.setSize(width, height);
        int xPos = x, yPos = y;
        switch (rootLocationType) {
            case 0:
                xPos = x;
                yPos = y;
                break;
            case 1:
                xPos = x - width / 2;
                yPos = y;
                break;
            case 2:
                xPos = x - width;
                yPos = y;
                break;
            case 3:
                xPos = x;
                yPos = y - height / 2;
                break;
            case 4:
                xPos = x - width / 2;
                yPos = y - height / 2;
                break;
            case 5:
                xPos = x - width;
                yPos = y - height / 2;
                break;
            case 6:
                xPos = x;
                yPos = y - height;
                break;
            case 7:
                xPos = x - width / 2;
                yPos = y - height;
                break;
            case 8:
                xPos = x - width;
                yPos = y - height;
                break;
        }
        dialog.setBounds(xPos, yPos, width, height);

        // Create objects in this panel (String, button, ...)
        PanelString messagePanel = new PanelString(width / 20, height / 20, messageLines, width / 10 * 9, null);
        fieldName = new TextField(width / 20, messagePanel.getY() + messagePanel.getHeight() + 10,
                width / 20 * 18, 50, "Họ và tên");
        fieldUsername = new TextField(width / 20, messagePanel.getY() + messagePanel.getHeight() + 70,
                width / 20 * 18, 50, "Tên đăng nhập");
        fieldPassword = new TextField(width / 20, messagePanel.getY() + messagePanel.getHeight() + 130,
                width / 20 * 18, 50, "Mật khẩu");
        button = new Button("Create");
        button.setFont(
                Setting.FONT_NAME_01,
                Setting.FONT_STYLE_01,
                Setting.FONT_SIZE_SMALL);
        button.setLocation(width / 2, messagePanel.getY() + messagePanel.getHeight() + 190, Button.TOP_CENTER);
        button.addActionListener(new ButtonHandler());
        dialog.add(messagePanel);
        dialog.add(fieldName);
        dialog.add(fieldUsername);
        dialog.add(fieldPassword);
        dialog.add(button);

        // Show dialog
        dialog.setVisible(true);
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == button) {
                dialog.dispose();
            }
        }
    }
}
