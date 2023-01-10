package src.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import src.launcher.Setting;
import src.objects.Button;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class DialogGuideTimeTable {
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
    public DialogGuideTimeTable(int x, int y, int width, int height, int rootLocationType, String title) {
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

        // Guide about time table
        Button button1 = new Button(new String[] {
                ".",
                "Đầu vào:",
                "       + Bạn cần phải tạo những môn học mà bạn muốn có trong lịch học của bạn.",
                "       + Mỗi môn học có thể có 1 hoặc nhiều mã lớp học khác nhau, mỗi mã lớp học có thể có thời gian học trùng nhau hoặc khác nhau.",
                ".",
                "Mục tiêu:",
                "       + Kiểm tra xem đối với danh sách môn học và số lượng mã lớp, thời gian của từng mã lớp học vừa nhập thì có tồn tại lịch học hợp lệ hay không.",
                "       + Nếu có tồn tại lịch học thỏa mãn thì sẽ liệt kê những lịch học đó.",
                ".",
                "Lưu ý:",
                "       + Một lịch học được coi là hợp lệ khi nó chứa đủ các môn học mà bạn đang xét, đồng thời các thời gian học của những môn học này không trùng nhau.",
                "       + Những môn học đang xét bao gồm những môn học có trong danh sách môn mà bạn nhập, đồng thời có trạng thái hoạt động là \"Đang sử dụng\", và có số mã lớp học (ở trạng thái \"Đang sử dụng\") lớn hơn 0.",
                "       + Tương tự, ứng dụng cũng chỉ xét những mã lớp học có trạng thái hoạt động là \"Đang sử dụng\"."
        });
        button1.setFontText(Button.ARIAL_BOLD_21);
        button1.setCorrectSizeButton();
        button1.setLocationText(15, 15);
        button1.setSizeButton(width / 20 * 19, button1.getHeight() * 6 / 5);
        button1.setLocationButton(width / 2, tempHeight, Button.TOP_CENTER);
        button1.setStrokeWidth(0);
        button1.setBackgroundColorButton(dialog.getBackground());
        button1.setEnable(false);
        panelScroll.add(button1);
        tempHeight += button1.getHeight();

        // Guide add new subject
        Button button2 = new Button(new String[] {
                "1. Thêm môn học vào danh sách",
                "+ Sau khi nhấn nút (như hình), bạn sẽ cần nhập Tên môn học và Mã môn học",
                "+ Môn học sẽ được coi là hợp lệ nếu mã môn không trùng với một trong những mã môn học đã có trong danh sách."
        });
        button2.setFontText(Button.ARIAL_BOLD_21);
        button2.setCorrectSizeButton();
        button2.setLocationText(15, 15);
        button2.setSizeButton(width / 20 * 19 / 3 - 10, button2.getHeight());
        button2.setLocationButton(width / 20 / 2, tempHeight, Button.TOP_LEFT);
        button2.setStrokeWidth(0);
        button2.setBackgroundColorButton(dialog.getBackground());
        button2.setEnable(false);
        panelScroll.add(button2);

        Button button3 = new Button("");
        button3.setSizeButton(width / 20 * 19 / 3 * 2,
                (int) (width / 20 * 19 * 1.0 * Setting.HEIGHT / Setting.WIDTH) / 3 * 2);
        button3.setLocationButton(button2.getX() + button2.getWidth(), tempHeight, Button.TOP_LEFT);
        button3.setStrokeWidth(0);
        button3.setGradientBackgroundColor(null, null, null);
        button3.setBackgroundImage(Setting.GUIDE_ADD_NEW_SUBJECT);
        button3.setEnable(false);
        button2.setSizeButton(button2.getWidth(), button3.getHeight());
        panelScroll.add(button3);
        tempHeight += button3.getHeight() + 50;

        // Guide turn on/ off subject
        Button button4 = new Button(new String[] {
                "2. Chỉnh sửa trạng thái hoạt động của một môn học",
                "+ Ở trạng thái \"Đang sử dụng\" (màu xanh), môn học sẽ được dùng khi xếp lịch học.",
                "+ Ở trạng thái \"Đang không sử dụng\" (màu đỏ), môn học sẽ không được dùng khi xếp lịch học."
        });
        button4.setFontText(Button.ARIAL_BOLD_21);
        button4.setCorrectSizeButton();
        button4.setLocationText(15, 15);
        button4.setSizeButton(width / 20 * 19 / 3 - 10, button4.getHeight());
        button4.setLocationButton(width / 20 / 2, tempHeight, Button.TOP_LEFT);
        button4.setStrokeWidth(0);
        button4.setBackgroundColorButton(dialog.getBackground());
        button4.setEnable(false);
        panelScroll.add(button4);

        Button button5 = new Button("");
        button5.setSizeButton(width / 20 * 19 / 3 * 2,
                (int) (width / 20 * 19 * 1.0 * Setting.HEIGHT / Setting.WIDTH) / 3 * 2);
        button5.setLocationButton(button4.getX() + button4.getWidth(), tempHeight, Button.TOP_LEFT);
        button5.setStrokeWidth(0);
        button5.setGradientBackgroundColor(null, null, null);
        button5.setBackgroundImage(Setting.GUIDE_TURN_OFF_SUBJECT);
        button5.setEnable(false);
        panelScroll.add(button5);
        button4.setSizeButton(button4.getWidth(), button5.getHeight());
        tempHeight += button5.getHeight() + 50;

        // Guide show inffor a subject
        Button button6 = new Button(new String[] {
                "3. Xem và chỉnh sửa dữ liệu của một môn học",
                "+ Nhấn vào ô của một môn học (như hình) để xem thông tin của môn học đó."
        });
        button6.setFontText(Button.ARIAL_BOLD_21);
        button6.setCorrectSizeButton();
        button6.setLocationText(15, 15);
        button6.setSizeButton(width / 20 * 19 / 3 - 10, button6.getHeight());
        button6.setLocationButton(width / 20 / 2, tempHeight, Button.TOP_LEFT);
        button6.setStrokeWidth(0);
        button6.setBackgroundColorButton(dialog.getBackground());
        button6.setEnable(false);
        panelScroll.add(button6);

        Button button7 = new Button("");
        button7.setSizeButton(width / 20 * 19 / 3 * 2,
                (int) (width / 20 * 19 * 1.0 * Setting.HEIGHT / Setting.WIDTH) / 3 * 2);
        button7.setLocationButton(button6.getX() + button6.getWidth(), tempHeight, Button.TOP_LEFT);
        button7.setStrokeWidth(0);
        button7.setGradientBackgroundColor(null, null, null);
        button7.setBackgroundImage(Setting.GUIDE_EDIT_SUBJECT);
        button7.setEnable(false);
        panelScroll.add(button7);
        button6.setSizeButton(button6.getWidth(), button7.getHeight());
        tempHeight += button7.getHeight() + 50;

        // Guide add new class of subject
        Button button8 = new Button(new String[] {
                "4. Thêm mã lớp học của một môn học",
                "+ Nhấn nút (như hình) để thêm một mã lớp học mới.",
                "+ Bạn sẽ cần phải nhập mã của lớp học (không trùng với những mã lớp học đã tồn tại của môn học đang xét), và chọn thời gian học tương ứng với mã lớp học này."
        });
        button8.setFontText(Button.ARIAL_BOLD_21);
        button8.setCorrectSizeButton();
        button8.setLocationText(15, 15);
        button8.setSizeButton(width / 20 * 19 / 3 - 10, button8.getHeight());
        button8.setLocationButton(width / 20 / 2, tempHeight, Button.TOP_LEFT);
        button8.setStrokeWidth(0);
        button8.setBackgroundColorButton(dialog.getBackground());
        button8.setEnable(false);
        panelScroll.add(button8);

        Button button9 = new Button("");
        button9.setSizeButton(width / 20 * 19 / 3 * 2,
                (int) (width / 20 * 19 * 1.0 * Setting.HEIGHT / Setting.WIDTH) / 3 * 2);
        button9.setLocationButton(button8.getX() + button8.getWidth(), tempHeight, Button.TOP_LEFT);
        button9.setStrokeWidth(0);
        button9.setGradientBackgroundColor(null, null, null);
        button9.setBackgroundImage(Setting.GUIDE_ADD_NEW_CLASS_OF_SUBJECT);
        button9.setEnable(false);
        panelScroll.add(button9);
        button8.setSizeButton(button8.getWidth(), button9.getHeight());
        tempHeight += button9.getHeight() + 50;

        // Guide turn on/ off class of subject
        Button button10 = new Button(new String[] {
                "5. Chỉnh sửa trạng thái hoạt động của một mã lớp học",
                "+ Ở trạng thái \"Đang sử dụng\" (màu xanh), mã lớp học sẽ được dùng khi xếp lịch học.",
                "+ Ở trạng thái \"Đang không sử dụng\" (màu đỏ), mã lớp học sẽ không được dùng khi xếp lịch học."
        });
        button10.setFontText(Button.ARIAL_BOLD_21);
        button10.setCorrectSizeButton();
        button10.setLocationText(15, 15);
        button10.setSizeButton(width / 20 * 19 / 3 - 10, button10.getHeight());
        button10.setLocationButton(width / 20 / 2, tempHeight, Button.TOP_LEFT);
        button10.setStrokeWidth(0);
        button10.setBackgroundColorButton(dialog.getBackground());
        button10.setEnable(false);
        panelScroll.add(button10);

        Button button11 = new Button("");
        button11.setSizeButton(width / 20 * 19 / 3 * 2,
                (int) (width / 20 * 19 * 1.0 * Setting.HEIGHT / Setting.WIDTH) / 3 * 2);
        button11.setLocationButton(button10.getX() + button10.getWidth(), tempHeight, Button.TOP_LEFT);
        button11.setStrokeWidth(0);
        button11.setGradientBackgroundColor(null, null, null);
        button11.setBackgroundImage(Setting.GUIDE_TURN_OFF_CLASS_OF_SUBJECT);
        button11.setEnable(false);
        panelScroll.add(button11);
        button10.setSizeButton(button10.getWidth(), button11.getHeight());
        tempHeight += button11.getHeight() + 50;

        // Guide Run app
        Button button12 = new Button(new String[] {
                "6. Kiểm tra lịch học khả thi",
                "+ Sau khi đã tạo các môn học và tạo các mã lớp học cho từng môn học, bạn có thể nhấn nút (như hình) để ứng dụng bắt đầu kiểm tra lịch học khả thi",
                "+ "
        });
        button12.setFontText(Button.ARIAL_BOLD_21);
        button12.setCorrectSizeButton();
        button12.setLocationText(15, 15);
        button12.setSizeButton(width / 20 * 19 / 3 - 10, button12.getHeight());
        button12.setLocationButton(width / 20 / 2, tempHeight, Button.TOP_LEFT);
        button12.setStrokeWidth(0);
        button12.setBackgroundColorButton(dialog.getBackground());
        button12.setEnable(false);
        panelScroll.add(button12);

        Button button13 = new Button("");
        button13.setSizeButton(width / 20 * 19 / 3 * 2,
                (int) (width / 20 * 19 * 1.0 * Setting.HEIGHT / Setting.WIDTH) / 3 * 2);
        button13.setLocationButton(button12.getX() + button12.getWidth(), tempHeight, Button.TOP_LEFT);
        button13.setStrokeWidth(0);
        button13.setGradientBackgroundColor(null, null, null);
        button13.setBackgroundImage(Setting.GUIDE_CHECK_VALID_TIME_TABLE_1);
        button13.setEnable(false);
        panelScroll.add(button13);
        button12.setSizeButton(button12.getWidth(), button13.getHeight());
        tempHeight += button13.getHeight() + 50;

        Button button14 = new Button(new String[] {
                "+ Bạn có thể nhấn các nút (như hình) để di chuyển giữa các lịch học.",
                "+ Bạn cũng có thể dùng con lăn chuột để di chuyển giữa các lịch học một cách nhanh chóng."
        });
        button14.setFontText(Button.ARIAL_BOLD_21);
        button14.setCorrectSizeButton();
        button14.setLocationText(15, 15);
        button14.setSizeButton(width / 20 * 19 / 3 - 10, button14.getHeight());
        button14.setLocationButton(width / 20 / 2, tempHeight, Button.TOP_LEFT);
        button14.setStrokeWidth(0);
        button14.setBackgroundColorButton(dialog.getBackground());
        button14.setEnable(false);
        panelScroll.add(button14);

        Button button15 = new Button("");
        button15.setSizeButton(width / 20 * 19 / 3 * 2,
                (int) (width / 20 * 19 * 1.0 * Setting.HEIGHT / Setting.WIDTH) / 3 * 2);
        button15.setLocationButton(button12.getX() + button12.getWidth(), tempHeight, Button.TOP_LEFT);
        button15.setStrokeWidth(0);
        button15.setGradientBackgroundColor(null, null, null);
        button15.setBackgroundImage(Setting.GUIDE_CHECK_VALID_TIME_TABLE_2);
        button15.setEnable(false);
        panelScroll.add(button15);
        button14.setSizeButton(button14.getWidth(), button15.getHeight());
        tempHeight += button15.getHeight() + 50;

        // Guide remove class subject
        Button button16 = new Button(new String[] {
                "7. Xóa mã lớp học",
                "+ Nhấn nút (như hình) để xóa một mã lớp học."
        });
        button16.setFontText(Button.ARIAL_BOLD_21);
        button16.setCorrectSizeButton();
        button16.setLocationText(15, 15);
        button16.setSizeButton(width / 20 * 19 / 3 - 10, button16.getHeight());
        button16.setLocationButton(width / 20 / 2, tempHeight, Button.TOP_LEFT);
        button16.setStrokeWidth(0);
        button16.setBackgroundColorButton(dialog.getBackground());
        button16.setEnable(false);
        panelScroll.add(button16);

        Button button17 = new Button("");
        button17.setSizeButton(width / 20 * 19 / 3 * 2,
                (int) (width / 20 * 19 * 1.0 * Setting.HEIGHT / Setting.WIDTH) / 3 * 2);
        button17.setLocationButton(button12.getX() + button12.getWidth(), tempHeight, Button.TOP_LEFT);
        button17.setStrokeWidth(0);
        button17.setGradientBackgroundColor(null, null, null);
        button17.setBackgroundImage(Setting.GUIDE_REMOVE_CLASS_OF_SUBJECT);
        button17.setEnable(false);
        panelScroll.add(button17);
        button16.setSizeButton(button16.getWidth(), button17.getHeight());
        tempHeight += button17.getHeight() + 50;

        // Guide remove subject
        Button button18 = new Button(new String[] {
                "8. Xóa một môn học",
                "+ Nhấn nút (như hình) để xóa một môn học."
        });
        button18.setFontText(Button.ARIAL_BOLD_21);
        button18.setCorrectSizeButton();
        button18.setLocationText(15, 15);
        button18.setSizeButton(width / 20 * 19 / 3 - 10, button18.getHeight());
        button18.setLocationButton(width / 20 / 2, tempHeight, Button.TOP_LEFT);
        button18.setStrokeWidth(0);
        button18.setBackgroundColorButton(dialog.getBackground());
        button18.setEnable(false);
        panelScroll.add(button18);

        Button button19 = new Button("");
        button19.setSizeButton(width / 20 * 19 / 3 * 2,
                (int) (width / 20 * 19 * 1.0 * Setting.HEIGHT / Setting.WIDTH) / 3 * 2);
        button19.setLocationButton(button12.getX() + button12.getWidth(), tempHeight, Button.TOP_LEFT);
        button19.setStrokeWidth(0);
        button19.setGradientBackgroundColor(null, null, null);
        button19.setBackgroundImage(Setting.GUIDE_REMOVE_SUBJECT);
        button19.setEnable(false);
        panelScroll.add(button19);
        button18.setSizeButton(button18.getWidth(), button19.getHeight());
        tempHeight += button19.getHeight() + 100;

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
