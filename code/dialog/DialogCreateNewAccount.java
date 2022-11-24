package code.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
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

    // Constructor
    public DialogCreateNewAccount(int x, int y, int width, int height, int rootLocationType, String title,
            String[] messageLines) {
        // Create frame and set propertis of this frame
        JFrame f = new JFrame();
        JDialog dialog = new JDialog(f, title, true);
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
        dialog.add(messagePanel);
        dialog.add(new TextField(width / 20, messagePanel.getY() + messagePanel.getHeight(),
                width / 20 * 18, 50));

        // Show dialog
        dialog.setVisible(true);
    }

}
