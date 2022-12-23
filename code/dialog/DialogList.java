package code.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import code.Setting;
import code.objects.Button;
import code.objects.ListItemsDrop;
import code.panel.PanelString;

public class DialogList {
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
    JDialog dialog = null;
    private ListItemsDrop listItemsDrop = null;
    private Button buttonOk = null;
    private boolean pressedOk = false;

    // Constructor
    public DialogList(int x, int y, int width, int height, int rootLocationType, String title,
            String[] messageLines, String[] texts) {
        // Create frame and set propertis of this frame
        JFrame f = new JFrame();
        dialog = new JDialog(f, title, true);
        dialog.setLayout(null);
        if (width < Setting.WIDTH / 24 * 11) {
            width = Setting.WIDTH / 24 * 11;
        }
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

        // Create message lines
        int tempHeight = 30;
        PanelString messagePanel = new PanelString(width / 12, tempHeight, messageLines, width / 12 * 10, null,
                PanelString.TOP_LEFT, 0);
        tempHeight += messagePanel.getHeight() + 10;
        // Create list items
        listItemsDrop = new ListItemsDrop(messagePanel.getX(), tempHeight, messagePanel.getWidth(),
                Setting.HEIGHT / 5, ListItemsDrop.TOP_LEFT, texts);
        tempHeight += listItemsDrop.getHeight() + listItemsDrop.getListItems().getHeight() + 30;
        // Create button
        buttonOk = new Button("OK");
        buttonOk.setLocationButton(width / 2, tempHeight, Button.TOP_CENTER);
        buttonOk.addMouseListener(new MouseHandler());
        tempHeight += buttonOk.getHeight() + 100;

        dialog.add(messagePanel);
        dialog.add(listItemsDrop);
        dialog.add(buttonOk);

        dialog.setBounds(xPos, yPos, width, Math.max(height, tempHeight));
        dialog.addMouseListener(new MouseHandler());

        // Show dialog
        dialog.setVisible(true);
    }

    public String getText() {
        if (pressedOk == true) {
            return listItemsDrop.getText();
        }
        return null;
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            if (event.getSource() == buttonOk) {
                pressedOk = true;
                dialog.dispose();
            } else {
                listItemsDrop.getListItems().setVisible(false);
                listItemsDrop.setSizeListItemsDrop(listItemsDrop.getPanelHeader().getWidth(),
                        listItemsDrop.getPanelHeader().getHeight());
                listItemsDrop.getButtonArrow().setBackgroundIcon(Setting.ARROW_DOWN);
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
