package code.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import code.Setting;
import code.algorithm.CalculateScoreLastTerm;
import code.file_handler.ReadFile;
import code.file_handler.WriteFile;
import code.objects.Account;
import code.objects.Button;
import code.objects.ConversionTable;
import code.panel.PanelString;
import code.text_field.TextField;

public class DialogCalculateScoreLastTerm3 {
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
    PanelString panelContent;

    // Constructor
    public DialogCalculateScoreLastTerm3(int x, int y, int width, int height, int rootLocationType, String title,
            String[] messageLines, double score1, double coefficient1, double score2, double coefficient2,
            ConversionTable conversionTable) {
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

        // create content
        panelContent = new PanelString(width / 2, height / 2,
                CalculateScoreLastTerm.getStringResults(score1, coefficient1, score2, coefficient2, conversionTable),
                width / 5 * 4, null, PanelString.CENTER_CENTER, 0);
        dialog.add(panelContent);

        // Show dialog
        dialog.setVisible(true);
    }
}
