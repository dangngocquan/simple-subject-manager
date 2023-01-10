package src.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import src.launcher.Setting;
import src.objects.Button;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class DialogGuideMapRelative {
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
    private JDialog dialog = null;
    private JPanel panelScroll = null;
    private int scrollCursor = 0;

    // Constructor
    public DialogGuideMapRelative(int x, int y, int width, int height, int rootLocationType, String title) {
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

        // Create panelScroll
        this.panelScroll = new JPanel();
        panelScroll.setLayout(null);

        // Create objects in this panel (String, button, ...)
        int tempHeight = 30;

        // Guide zoom map
        Button button2 = new Button(new String[] {
                "1. Phóng to / Thu nhỏ sơ đồ",
                "+ Bạn có thể sử dụng con lăn chuột để tăng / giảm kích thước của sơ đồ."
        });
        button2.setFontText(Button.ARIAL_BOLD_21);
        button2.setCorrectSizeButton();
        button2.setLocationText(15, 15);
        button2.setSizeButton(width / 20 * 19, button2.getHeight());
        button2.setLocationButton(width / 20 / 2, tempHeight, Button.TOP_LEFT);
        button2.setStrokeWidth(0);
        button2.setBackgroundColorButton(dialog.getBackground());
        button2.setEnable(false);
        panelScroll.add(button2);
        tempHeight += button2.getHeight() + 10;

        Button button3 = new Button("");
        button3.setSizeButton(width / 20 * 19 / 2,
                (int) (width / 20 * 19 * 1.0 * Setting.HEIGHT / Setting.WIDTH) / 2);
        button3.setLocationButton(button2.getX(), tempHeight, Button.TOP_LEFT);
        button3.setStrokeWidth(2);
        button3.setGradientBackgroundColor(null, null, null);
        button3.setBackgroundImage(Setting.GUIDE_ZOOM_MAP_1);
        button3.setEnable(false);
        panelScroll.add(button3);

        Button button4 = new Button("");
        button4.setSizeButton(width / 20 * 19 / 2,
                (int) (width / 20 * 19 * 1.0 * Setting.HEIGHT / Setting.WIDTH) / 2);
        button4.setLocationButton(button3.getX() + button3.getWidth() + 10, tempHeight, Button.TOP_LEFT);
        button4.setStrokeWidth(2);
        button4.setGradientBackgroundColor(null, null, null);
        button4.setBackgroundImage(Setting.GUIDE_ZOOM_MAP_2);
        button4.setEnable(false);
        panelScroll.add(button4);
        tempHeight += button4.getHeight() + 50;

        // Guide ctr zoom map
        Button button5 = new Button(new String[] {
                "2. Phóng to / Thu nhỏ sơ đồ theo chiều cao",
                "+ Bạn có thể nhấn giữ phím Ctrl, đồng thời sử dụng con lăn chuột để tăng / giảm chiều cao của sơ đồ."
        });
        button5.setFontText(Button.ARIAL_BOLD_21);
        button5.setCorrectSizeButton();
        button5.setLocationText(15, 15);
        button5.setSizeButton(width / 20 * 19, button5.getHeight());
        button5.setLocationButton(width / 20 / 2, tempHeight, Button.TOP_LEFT);
        button5.setStrokeWidth(0);
        button5.setBackgroundColorButton(dialog.getBackground());
        button5.setEnable(false);
        panelScroll.add(button5);
        tempHeight += button5.getHeight() + 10;

        Button button6 = new Button("");
        button6.setSizeButton(width / 20 * 19 / 2,
                (int) (width / 20 * 19 * 1.0 * Setting.HEIGHT / Setting.WIDTH) / 2);
        button6.setLocationButton(button5.getX(), tempHeight, Button.TOP_LEFT);
        button6.setStrokeWidth(2);
        button6.setGradientBackgroundColor(null, null, null);
        button6.setBackgroundImage(Setting.GUIDE_CTRL_ZOOM_MAP_1);
        button6.setEnable(false);
        panelScroll.add(button6);

        Button button7 = new Button("");
        button7.setSizeButton(width / 20 * 19 / 2,
                (int) (width / 20 * 19 * 1.0 * Setting.HEIGHT / Setting.WIDTH) / 2);
        button7.setLocationButton(button6.getX() + button6.getWidth() + 10, tempHeight, Button.TOP_LEFT);
        button7.setStrokeWidth(2);
        button7.setGradientBackgroundColor(null, null, null);
        button7.setBackgroundImage(Setting.GUIDE_CTRL_ZOOM_MAP_2);
        button7.setEnable(false);
        panelScroll.add(button7);
        tempHeight += button7.getHeight() + 50;

        // Guide shift zoom map
        Button button8 = new Button(new String[] {
                "3. Phóng to / Thu nhỏ sơ đồ theo chiều rộng",
                "+ Bạn có thể nhấn giữ phím Shift, đồng thời sử dụng con lăn chuột để tăng / giảm chiều rộng của sơ đồ."
        });
        button8.setFontText(Button.ARIAL_BOLD_21);
        button8.setCorrectSizeButton();
        button8.setLocationText(15, 15);
        button8.setSizeButton(width / 20 * 19, button8.getHeight());
        button8.setLocationButton(width / 20 / 2, tempHeight, Button.TOP_LEFT);
        button8.setStrokeWidth(0);
        button8.setBackgroundColorButton(dialog.getBackground());
        button8.setEnable(false);
        panelScroll.add(button8);
        tempHeight += button8.getHeight() + 10;

        Button button9 = new Button("");
        button9.setSizeButton(width / 20 * 19 / 2,
                (int) (width / 20 * 19 * 1.0 * Setting.HEIGHT / Setting.WIDTH) / 2);
        button9.setLocationButton(button8.getX(), tempHeight, Button.TOP_LEFT);
        button9.setStrokeWidth(2);
        button9.setGradientBackgroundColor(null, null, null);
        button9.setBackgroundImage(Setting.GUIDE_SHIFT_ZOOM_MAP_1);
        button9.setEnable(false);
        panelScroll.add(button9);

        Button button10 = new Button("");
        button10.setSizeButton(width / 20 * 19 / 2,
                (int) (width / 20 * 19 * 1.0 * Setting.HEIGHT / Setting.WIDTH) / 2);
        button10.setLocationButton(button9.getX() + button6.getWidth() + 10, tempHeight, Button.TOP_LEFT);
        button10.setStrokeWidth(2);
        button10.setGradientBackgroundColor(null, null, null);
        button10.setBackgroundImage(Setting.GUIDE_SHIFT_ZOOM_MAP_2);
        button10.setEnable(false);
        panelScroll.add(button10);
        tempHeight += button10.getHeight() + 50;

        // Guide dragged map
        Button button11 = new Button(new String[] {
                "4. Thay đổi khung nhìn",
                "+ Bạn có thể nhấn giữ chuột, đồng thời di chuyển chuột để thay đổi vị trí khung nhìn trên sơ đồ."
        });
        button11.setFontText(Button.ARIAL_BOLD_21);
        button11.setCorrectSizeButton();
        button11.setLocationText(15, 15);
        button11.setSizeButton(width / 20 * 19, button11.getHeight());
        button11.setLocationButton(width / 20 / 2, tempHeight, Button.TOP_LEFT);
        button11.setStrokeWidth(0);
        button11.setBackgroundColorButton(dialog.getBackground());
        button11.setEnable(false);
        panelScroll.add(button11);
        tempHeight += button11.getHeight() + 10;

        Button button12 = new Button("");
        button12.setSizeButton(width / 20 * 19 / 2,
                (int) (width / 20 * 19 * 1.0 * Setting.HEIGHT / Setting.WIDTH) / 2);
        button12.setLocationButton(button11.getX(), tempHeight, Button.TOP_LEFT);
        button12.setStrokeWidth(2);
        button12.setGradientBackgroundColor(null, null, null);
        button12.setBackgroundImage(Setting.GUIDE_DRAGGED_MAP_1);
        button12.setEnable(false);
        panelScroll.add(button12);

        Button button13 = new Button("");
        button13.setSizeButton(width / 20 * 19 / 2,
                (int) (width / 20 * 19 * 1.0 * Setting.HEIGHT / Setting.WIDTH) / 2);
        button13.setLocationButton(button12.getX() + button6.getWidth() + 10, tempHeight, Button.TOP_LEFT);
        button13.setStrokeWidth(2);
        button13.setGradientBackgroundColor(null, null, null);
        button13.setBackgroundImage(Setting.GUIDE_DRAGGED_MAP_2);
        button13.setEnable(false);
        panelScroll.add(button13);
        tempHeight += button13.getHeight() + 50;

        // Guide entered subject
        Button button14 = new Button(new String[] {
                "5. Xem các môn học liên quan",
                "+ Di chuyển chuột vào một môn học nào đó, sơ đồ sẽ hiện ra những môn học liên quan, đồng thời ẩn những môn học không liên quan."
        });
        button14.setFontText(Button.ARIAL_BOLD_21);
        button14.setCorrectSizeButton();
        button14.setLocationText(15, 15);
        button14.setSizeButton(width / 20 * 19, button14.getHeight());
        button14.setLocationButton(width / 20 / 2, tempHeight, Button.TOP_LEFT);
        button14.setStrokeWidth(0);
        button14.setBackgroundColorButton(dialog.getBackground());
        button14.setEnable(false);
        panelScroll.add(button14);
        tempHeight += button14.getHeight() + 10;

        Button button15 = new Button("");
        button15.setSizeButton(width / 20 * 19,
                (int) (width / 20 * 19 * 1.0 * Setting.HEIGHT / Setting.WIDTH));
        button15.setLocationButton(button14.getX(), tempHeight, Button.TOP_LEFT);
        button15.setStrokeWidth(2);
        button15.setGradientBackgroundColor(null, null, null);
        button15.setBackgroundImage(Setting.GUIDE_ENTERED_MAP);
        button15.setEnable(false);
        panelScroll.add(button15);
        tempHeight += button15.getHeight() + 50;

        // Guide edit subject
        Button button16 = new Button(new String[] {
                "6. Thay đổi màu sắc và vị trí  hàng của môn học",
                "+ Nhấn chuột vào môn học bất kì để thay đổi màu sắc và vị trí hàng của môn học đó",
                "+ Vị trí hàng của môn học sẽ tương ứng với học kỳ (thời điểm học) của môn học đó.",
                "+ Nếu học kỳ có giá trị là 0, thì môn học đó sẽ có vị trí hàng mặc định (được tính dựa theo mối liên hệ với các môn học khác)."
        });
        button16.setFontText(Button.ARIAL_BOLD_21);
        button16.setCorrectSizeButton();
        button16.setLocationText(15, 15);
        button16.setSizeButton(width / 20 * 19, button16.getHeight());
        button16.setLocationButton(width / 20 / 2, tempHeight, Button.TOP_LEFT);
        button16.setStrokeWidth(0);
        button16.setBackgroundColorButton(dialog.getBackground());
        button16.setEnable(false);
        panelScroll.add(button16);
        tempHeight += button16.getHeight() + 10;

        Button button17 = new Button("");
        button17.setSizeButton(width / 20 * 19,
                (int) (width / 20 * 19 * 1.0 * Setting.HEIGHT / Setting.WIDTH));
        button17.setLocationButton(button16.getX(), tempHeight, Button.TOP_LEFT);
        button17.setStrokeWidth(2);
        button17.setGradientBackgroundColor(null, null, null);
        button17.setBackgroundImage(Setting.GUIDE_PRESSED_MAP_1);
        button17.setEnable(false);
        panelScroll.add(button17);
        tempHeight += button17.getHeight() + 10;

        panelScroll.setSize(width, Math.max(tempHeight, height));
        panelScroll.setLocation(0, 0);
        updateContent();
        panelScroll.addMouseWheelListener(new MouseWheelHandler());
        dialog.add(panelScroll);

        // Show dialog
        dialog.setVisible(true);
    }

    public void updateContent() {
        panelScroll.setBounds(panelScroll.getX(), -this.scrollCursor, panelScroll.getWidth(),
                panelScroll.getHeight());
    }

    // Get max of scrollCursor
    public int getMaxScrollCursor() {
        return Math.max(0, panelScroll.getHeight() - dialog.getHeight());
    }

    // Set cursor
    public void setScrollCursor(int value) {
        if (value < 0) {
            this.scrollCursor = 0;
        } else if (value > getMaxScrollCursor()) {
            this.scrollCursor = getMaxScrollCursor();
        } else {
            this.scrollCursor = value;
        }
    }

    private class MouseWheelHandler implements MouseWheelListener {
        public void mouseWheelMoved(MouseWheelEvent event) {
            for (int count = 0; count < 20; count++) {
                setScrollCursor(scrollCursor + event.getWheelRotation());
            }
            updateContent();
        }

    }

}
