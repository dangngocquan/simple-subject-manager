package code.dialog;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import code.Setting;
import code.objects.Button;
import code.panel.PanelString;
import java.awt.Font;

public class DialogMessage {
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
    private Button buttonOk = null;
    private Button buttonIcon = null;
    JDialog dialog = null;

    // Constructor
    public DialogMessage(int x, int y, int width, int height, int rootLocationType, String title,
            String[] messageLines, ImageIcon icon) {
        // Create frame and set propertis of this frame
        JFrame f = new JFrame();
        dialog = new JDialog(f, title, true);
        dialog.setLayout(null);
        dialog.setSize(width, height);
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
        PanelString messagePanel = new PanelString(width / 8, tempHeight, messageLines, width / 4 * 3, null,
                PanelString.TOP_LEFT, 0);
        tempHeight += messagePanel.getHeight() + 10;

        buttonIcon = new Button("");
        buttonIcon.setBackgroundIcon(icon);
        buttonIcon.setSizeButton(buttonIcon.getHeight(), buttonIcon.getHeight());
        buttonIcon.setLocationButton(messagePanel.getX(), messagePanel.getY() + messagePanel.getHeight() / 2,
                Button.CENTER_RIGHT);
        buttonIcon.setGradientBackgroundColorEntered(null, null, null);
        buttonIcon.setGradientBackgroundColorExited(null, null, null);
        buttonIcon.setGradientBackgroundColor(null, null, null);
        buttonIcon.setBackgroundColorButton(dialog.getBackground());
        buttonIcon.setEnable(false);

        buttonOk = new Button("OK");
        buttonOk.setFont(new Font(
                Setting.FONT_NAME_01,
                Setting.FONT_STYLE_01,
                Setting.FONT_SIZE_SMALL));
        buttonOk.setLocationButton(width / 2, tempHeight, Button.TOP_CENTER);
        buttonOk.addMouseListener(new MouseHandler());
        tempHeight += buttonOk.getHeight() + 100;

        dialog.add(messagePanel);
        dialog.add(buttonOk);
        dialog.add(buttonIcon);

        dialog.setSize(width, Math.min(height, tempHeight));

        // Show dialog
        dialog.setVisible(true);
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            if (event.getSource() == buttonOk) {
                dialog.dispose();
            }
        }

        @Override
        public void mouseReleased(MouseEvent event) {

        }

        @Override
        public void mouseEntered(MouseEvent event) {

        }

        @Override
        public void mouseExited(MouseEvent event) {

        }
    }
}
