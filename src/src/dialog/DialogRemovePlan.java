package src.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;

import src.launcher.Setting;
import src.objects.Button;
import src.panel.PanelString;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class DialogRemovePlan {
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
    private Button buttonYes = null;
    private Button buttonNo = null;
    JDialog dialog = null;
    private boolean result = false;

    // Constructor
    public DialogRemovePlan(int x, int y, int width, int height, int rootLocationType, String title,
            String[] messageLines) {
        // Create frame and set propertis of this frame
        JFrame f = new JFrame();
        dialog = new JDialog(f, title, true);
        dialog.setLayout(null);
        dialog.setSize(width, height);
        dialog.setIconImage(Setting.LOGO.getImage());
        dialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
        int tempHeight = 30;
        PanelString messagePanel = new PanelString(width / 20 - 15, tempHeight, messageLines, width / 10 * 9, null,
                PanelString.TOP_LEFT, 0);
        tempHeight += messagePanel.getHeight() + 10;

        buttonNo = new Button("Hủy");
        buttonNo.setFont(new Font(
                Setting.FONT_NAME_01,
                Setting.FONT_STYLE_01,
                Setting.FONT_SIZE_SMALL));
        buttonNo.setLocationButton(width / 4, tempHeight, Button.TOP_CENTER);
        buttonNo.addMouseListener(new MouseHandler());

        buttonYes = new Button("Xóa");
        buttonYes.setFont(new Font(
                Setting.FONT_NAME_01,
                Setting.FONT_STYLE_01,
                Setting.FONT_SIZE_SMALL));
        buttonYes.setLocationButton(width / 4 * 3, tempHeight, Button.TOP_CENTER);
        buttonYes.addMouseListener(new MouseHandler());

        dialog.add(messagePanel);
        dialog.add(buttonYes);
        dialog.add(buttonNo);

        // Show dialog
        dialog.setVisible(true);
    }

    // Get status
    public boolean getResult() {
        return this.result;
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            if (event.getSource() == buttonNo) {
                result = false;
                dialog.dispose();
            } else if (event.getSource() == buttonYes) {
                result = true;
                dialog.dispose();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }
}
