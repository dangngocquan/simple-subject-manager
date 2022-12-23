package code.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import code.Setting;
import code.algorithm.CalculateScoreLastTerm;
import code.objects.ConversionTable;
import code.panel.PanelString;

public class DialogCalculateScoreLastTerm4 {
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

    public static final Color COLOR_HEADER = Setting.COLOR_BLUE_02;
    public static final Color COLOR_STROKE = Setting.COLOR_BLACK;
    public static final Color COLOR_ROW_1 = Setting.COLOR_GRAY_05;
    public static final Color COLOR_ROW_2 = Setting.COLOR_GRAY_04;

    // Properties
    JDialog dialog = null;
    PanelString title0;
    JPanel panelContent;
    JPanel panelHeader;
    PanelString title1, title2, title3;

    // Constructor
    public DialogCalculateScoreLastTerm4(int x, int y, int width, int height, int rootLocationType, String title,
            String[] messageLines, double score1, double coefficient1, double score2, double coefficient2,
            double score3, double coefficient3,
            ConversionTable conversionTable) {
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

        // Title
        title0 = new PanelString(width / 2, height / 8, "Kết quả tính toán", width,
                new Font(Setting.FONT_NAME_01,
                        Setting.FONT_STYLE_01,
                        Setting.FONT_SIZE_MEDIUM),
                PanelString.CENTER_CENTER, 0);

        int tempHeight = height / 4;

        // Panel header
        panelHeader = new JPanel();
        panelHeader.setLayout(null);
        panelHeader.setSize(width / 5 * 4, 0);

        // Create titles
        int strokeWidth = 3;
        title1 = new PanelString(strokeWidth, 0, "Điểm chữ", panelHeader.getWidth() / 8, null, PanelString.TOP_LEFT,
                0);
        title2 = new PanelString(title1.getX() + title1.getWidth() + strokeWidth, title1.getY(), "Điểm hệ 4",
                panelHeader.getWidth() / 8, null,
                PanelString.TOP_LEFT, 0);
        title3 = new PanelString(title2.getX() + title2.getWidth() + strokeWidth, title1.getY(), "Điều kiện",
                panelHeader.getWidth() - title1.getWidth() - title2.getWidth() - strokeWidth * 4, null,
                PanelString.TOP_LEFT, 0);

        panelHeader.setSize(panelHeader.getWidth(), title1.getHeight() + strokeWidth);
        panelHeader.setBounds(width / 10, tempHeight, panelHeader.getWidth(), panelHeader.getHeight());
        tempHeight += panelHeader.getHeight();

        // Set background color of header panel
        title1.setBackground(COLOR_HEADER);
        title2.setBackground(COLOR_HEADER);
        title3.setBackground(COLOR_HEADER);
        panelHeader.setBackground(COLOR_STROKE);

        // create content
        panelContent = new JPanel();
        panelContent.setLayout(null);
        int contentHeight = 0;
        String[] results = CalculateScoreLastTerm.getStringResults(score1, coefficient1, score2, coefficient2, score3,
                coefficient3, conversionTable);
        for (int i = results.length - 1; i >= 0; i--) {
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setSize(panelHeader.getWidth(), 0);

            PanelString temp1 = new PanelString(strokeWidth, 0, conversionTable.getCharacterScore().get(i + 1),
                    panel.getWidth() / 8, null, PanelString.TOP_LEFT,
                    0);
            PanelString temp2 = new PanelString(temp1.getX() + temp1.getWidth() + strokeWidth, temp1.getY(),
                    conversionTable.getScore4().get(i + 1) + "",
                    panel.getWidth() / 8, null,
                    PanelString.TOP_LEFT, 0);
            PanelString temp3 = new PanelString(temp2.getX() + temp2.getWidth() + strokeWidth, title1.getY(),
                    results[i],
                    panel.getWidth() - temp1.getWidth() - temp2.getWidth() - strokeWidth * 4, null,
                    PanelString.TOP_LEFT, 0);

            panel.add(temp1);
            panel.add(temp2);
            panel.add(temp3);
            panel.setSize(panel.getWidth(), temp1.getHeight());
            panel.setBounds(0, contentHeight, panel.getWidth(), panel.getHeight());
            panel.setBackground(COLOR_STROKE);

            if (i % 2 == 0) {
                temp1.setBackground(COLOR_ROW_1);
                temp2.setBackground(COLOR_ROW_1);
                temp3.setBackground(COLOR_ROW_1);
            } else {
                temp1.setBackground(COLOR_ROW_2);
                temp2.setBackground(COLOR_ROW_2);
                temp3.setBackground(COLOR_ROW_2);
            }
            panelContent.add(panel);
            contentHeight += panel.getHeight();
        }

        panelContent.setSize(panelHeader.getWidth(), contentHeight);
        panelContent.setBounds(panelHeader.getX(), panelHeader.getY() + panelHeader.getHeight(),
                panelContent.getWidth(), panelContent.getHeight());

        // Add panel
        panelHeader.add(title1);
        panelHeader.add(title2);
        panelHeader.add(title3);
        dialog.add(panelHeader);
        dialog.add(panelContent);
        dialog.add(title0);

        // Show dialog
        dialog.setVisible(true);
    }
}
