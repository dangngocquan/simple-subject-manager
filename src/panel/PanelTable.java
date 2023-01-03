package src.panel;

import javax.swing.JPanel;
import src.Setting;
import src.objects.Button;
import src.objects.Subject;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.awt.Color;
import java.awt.Font;

public class PanelTable extends JPanel {
    // Constants panel's root location
    public static final int TOP_LEFT = 0;
    public static final int TOP_CENTER = 1;
    public static final int TOP_RIGHT = 2;
    public static final int CENTER_LEFT = 3;
    public static final int CENTER_CENTER = 4;
    public static final int CENTER_RIGHT = 5;
    public static final int BOTTOM_LEFT = 6;
    public static final int BOTTOM_CENTER = 7;
    public static final int BOTTOM_RIGHT = 8;

    public static final Color COLOR_STROKE = Setting.COLOR_BLACK;
    public static final Color COLOR_PANEL_SELECTED = Setting.COLOR_GREEN_03;
    public static final Color COLOR_PANEL_UNSELECTED = Setting.COLOR_WHITE;
    public static final Color COLOR_PANEL_ENTERED = Setting.COLOR_VIOLET_02;

    // Properties
    private int width, height; // size of this panel
    private int xPos, yPos, rootLocationType; // location of top-left point
    private int numberRows, numberColumns;
    private JPanel panelMain = null;
    private Button[] panelTimes = null;
    private boolean[] selected;
    private String[] textDays = {
            "", "Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy", "Chủ Nhật"
    };
    private Button[] panelDays = null;
    private Button[] panelTimeLessons = null;
    private boolean canEdit = true;
    private Subject subject;
    private int indexTimeLesson;

    // Constructor
    public PanelTable(int x, int y, int width, int height, Font font,
            int rootLocationType, int numberRows, int numberColumns, Subject subject, int indexTimeLesson) {
        // Properties, Objects
        this.width = width;
        this.height = height;
        this.rootLocationType = rootLocationType;
        this.numberRows = numberRows;
        this.numberColumns = numberColumns;
        this.subject = subject;
        this.indexTimeLesson = indexTimeLesson;
        setLayout(null);
        setSize(width, height);
        setLocation(x, y, rootLocationType);
        setBackground(COLOR_STROKE);

        // panels
        panelMain = new JPanel();
        panelMain.setLayout(null);
        panelMain.setSize(width, height);
        panelMain.setBounds(0, 0, width, height);

        int gapRow = 2;
        int gapColumn = 2;
        int heightPerRow = height / (numberRows + 1);
        int widthPerColumn = width / (numberColumns + 1);

        panelDays = new Button[textDays.length];
        for (int index = 0; index < textDays.length; index++) {
            panelDays[index] = new Button(textDays[index]);
            panelDays[index].setFontText(Button.ARIAL_BOLD_13);
            panelDays[index].setCorrectSizeButton();
            if (index == 0) {
                panelDays[index].setSizeButton(widthPerColumn - gapColumn, heightPerRow - gapRow);
                panelDays[index].setLocationButton(index * widthPerColumn, 0, Button.TOP_LEFT);
                panelDays[index].setGradientBackgroundColor(Setting.GRADIENT_POINTS1_11,
                        Setting.GRADIENT_POINTS2_11,
                        Setting.GRADIENT_COLORS_11);
            } else {
                panelDays[index].setSizeButton(widthPerColumn, heightPerRow - gapRow);
                panelDays[index].setLocationButton(index * widthPerColumn, 0, Button.TOP_LEFT);
                panelDays[index].setGradientBackgroundColor(Setting.GRADIENT_POINTS1_12,
                        Setting.GRADIENT_POINTS2_12,
                        Setting.GRADIENT_COLORS_12);
            }
            panelDays[index].setEnable(false);
            panelMain.add(panelDays[index]);
        }

        panelTimeLessons = new Button[numberRows];
        for (int index = 0; index < panelTimeLessons.length; index++) {
            panelTimeLessons[index] = new Button("Tiết " + (index + 1));
            panelTimeLessons[index].setFontText(Button.ARIAL_BOLD_13);
            panelTimeLessons[index].setCorrectSizeButton();
            panelTimeLessons[index].setSizeButton(widthPerColumn - gapColumn, heightPerRow);
            panelTimeLessons[index].setLocationButton(0, (index + 1) * heightPerRow, Button.TOP_LEFT);
            panelTimeLessons[index].setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                    Setting.GRADIENT_COLORS_8);
            panelTimeLessons[index].setEnable(false);
            panelMain.add(panelTimeLessons[index]);
        }

        panelTimes = new Button[this.numberRows * this.numberColumns];
        this.selected = new boolean[panelTimes.length];
        int count = 0;
        for (int indexColumn = 1; indexColumn <= numberColumns; indexColumn++) {
            for (int indexRow = 1; indexRow <= numberRows; indexRow++) {
                panelTimes[count] = new Button("");
                panelTimes[count].addMouseListener(new MouseHandler());
                panelTimes[count].setFontText(Button.ARIAL_BOLD_13);
                panelTimes[count].setCorrectSizeButton();
                panelTimes[count].setSizeButton(widthPerColumn, heightPerRow);
                panelTimes[count].setLocationButton(indexColumn * widthPerColumn, indexRow * heightPerRow,
                        Button.TOP_LEFT);
                panelTimes[count].setBackgroundColorButton(COLOR_PANEL_UNSELECTED);
                panelTimes[count].setBackgroundColorEnteredButton(COLOR_PANEL_ENTERED);
                panelTimes[count].setBackgroundColorExitedButton(COLOR_PANEL_UNSELECTED);
                panelMain.add(panelTimes[count]);
                count++;
            }
        }

        if (subject != null) {
            setSelectedPanel(subject.getListTimes().get(indexTimeLesson));
        }

        // add panel
        panelMain.setBackground(COLOR_STROKE);
        add(panelMain);

    }

    // Update content data
    public void updateContent() {
        // Update backgroun color and editable of panels
        int count = 0;
        for (int indexColumn = 1; indexColumn <= numberColumns; indexColumn++) {
            for (int indexRow = 1; indexRow <= numberRows; indexRow++) {
                // Update color
                if (selected[count]) {
                    panelTimes[count].setBackgroundColorButton(COLOR_PANEL_SELECTED);
                    panelTimes[count].setBackgroundColorEnteredButton(COLOR_PANEL_ENTERED);
                    panelTimes[count].setBackgroundColorExitedButton(COLOR_PANEL_SELECTED);
                } else {
                    panelTimes[count].setBackgroundColorButton(COLOR_PANEL_UNSELECTED);
                    panelTimes[count].setBackgroundColorEnteredButton(COLOR_PANEL_ENTERED);
                    panelTimes[count].setBackgroundColorExitedButton(COLOR_PANEL_UNSELECTED);
                }
                // Update editable
                panelTimes[count].setEnable(canEdit);
                count++;
            }
        }
    }

    // Set panel pressed
    public void setSelectedPanel(List<Integer> indexs) {
        Arrays.fill(selected, false);
        for (int index = 0; index < panelTimes.length; index++) {
            if (indexs.contains(index)) {
                selected[index] = true;
            }
        }
    }

    // get selected panel
    public List<Integer> getSelectedIndexPanels() {
        List<Integer> selectedIndexes = new LinkedList<>();
        for (int index = 0; index < selected.length; index++) {
            if (selected[index]) {
                selectedIndexes.add(index);
            }
        }
        return selectedIndexes;
    }

    // Set can editable
    public void setEditable(boolean flag) {
        this.canEdit = flag;
        updateContent();
    }

    // Get rootLocationType
    public int getRootLocationType() {
        return this.rootLocationType;
    }

    // Set root location tyoe
    public void setLocation(int x, int y, int type) {
        this.rootLocationType = type;
        switch (type) {
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
        setBounds(xPos, yPos, width, height);
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            if (canEdit) {
                for (int index = 0; index < panelTimes.length; index++) {
                    if (event.getSource() == panelTimes[index]) {
                        selected[index] = !selected[index];
                        if (subject != null) {
                            subject.getListTimes().set(indexTimeLesson, getSelectedIndexPanels());
                        }
                        updateContent();
                    }
                }
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
