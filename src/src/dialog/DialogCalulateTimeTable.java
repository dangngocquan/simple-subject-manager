package src.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import src.algorithm.CalculateTimeTable;
import src.launcher.Setting;
import src.objects.Button;
import src.objects.Subject;
import src.objects.TimeTable;
import src.panel.PanelTable;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.MouseWheelEvent;

public class DialogCalulateTimeTable {
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
    private int width, height;
    private int xPos, yPos, rootLocationType;
    private JDialog dialog = null;
    private JPanel panelContent = null;
    private JPanel panelScroll = null;
    private int indexPageShowing = 0;
    private Button panelIndexPage = null;
    private Button buttonNext, buttonPrevious;
    private List<List<Integer>> data = null;

    // Constructor
    public DialogCalulateTimeTable(int x, int y, int width, int height, int rootLocationType, String title,
            String[] messageLines, TimeTable timeTable) {
        this.width = width;
        this.height = height;
        this.rootLocationType = rootLocationType;
        // Create frame and set propertis of this frame
        JFrame f = new JFrame();
        dialog = new JDialog(f, title, true);
        dialog.setLayout(null);
        dialog.setSize(width, height);
        dialog.setIconImage(Setting.LOGO.getImage());
        dialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocation(x, y, this.rootLocationType);

        // Create panel content
        panelContent = new JPanel();
        panelContent.setLayout(null);
        panelContent.setSize(width / 18 * 16,
                height / (timeTable.getMaxLessonPerDay() + 1 + 2) * (timeTable.getMaxLessonPerDay() + 1) + 2);
        panelContent.setBounds((width - panelContent.getWidth()) / 2, height / (timeTable.getMaxLessonPerDay() + 1 + 2),
                panelContent.getWidth(), panelContent.getHeight());

        // Create panelScroll
        this.panelScroll = new JPanel();
        panelScroll.setLayout(null);

        // Create button and panel
        panelIndexPage = new Button("Không có lịch học chứa đủ các môn đang xét.");
        panelIndexPage.setFontText(Button.ARIAL_BOLD_18);
        panelIndexPage.setStrokeWidth(0);
        panelIndexPage.setLocationButton(width / 2, panelContent.getY() - 10, Button.BOTTOM_CENTER);
        panelIndexPage.setEnable(false);
        panelIndexPage.setBackgroundColorButton(dialog.getBackground());

        buttonPrevious = new Button("");
        buttonPrevious.addMouseListener(new MouseHandler());
        buttonPrevious.setSizeButton(buttonPrevious.getHeight(), buttonPrevious.getHeight());
        buttonPrevious.setBackgroundIcon(Setting.ARROW_LEFT);
        buttonPrevious.setLocationButton(panelContent.getX() - 10,
                panelContent.getY() + panelContent.getHeight() / 2,
                Button.CENTER_RIGHT);

        buttonNext = new Button("");
        buttonNext.addMouseListener(new MouseHandler());
        buttonNext.setSizeButton(buttonNext.getHeight(), buttonNext.getHeight());
        buttonNext.setBackgroundIcon(Setting.ARROW_RIGHT);
        buttonNext.setLocationButton(panelContent.getX() + panelContent.getWidth() + 10,
                panelContent.getY() + panelContent.getHeight() / 2,
                Button.CENTER_LEFT);

        // Create data
        data = CalculateTimeTable.start(timeTable);

        List<Subject> enableSubjects = new LinkedList<>();
        for (Subject subject : timeTable.getSubjects()) {
            if (subject.getEnable() && subject.getListTimeNames().size() > 0) {
                boolean flag = false;
                for (Boolean enableTimeLesson : subject.getListEnableTimeLessons()) {
                    if (enableTimeLesson) {
                        flag = true;
                    }
                }
                if (flag) {
                    enableSubjects.add(subject);
                }
            }
        }

        // Create objects in this panel (String, button, ...)
        int tempHeight = 0;
        for (List<Integer> indexTimeEachSubject : data) {
            PanelTable panel = new PanelTable(0, tempHeight, panelContent.getWidth(), panelContent.getHeight(), null,
                    PanelTable.TOP_LEFT, timeTable.getMaxLessonPerDay(),
                    7, enableSubjects, indexTimeEachSubject, false);
            panel.setEditable(false);
            panelScroll.add(panel);
            tempHeight += panel.getHeight();
        }

        panelScroll.setSize(width, Math.max(tempHeight, panelContent.getHeight()));
        panelScroll.setBounds(0, -this.indexPageShowing * panelContent.getHeight(), panelScroll.getWidth(),
                panelScroll.getHeight());

        // add panel
        panelScroll.addMouseWheelListener(new MouseWheelHandler());
        dialog.add(panelContent);
        panelContent.add(panelScroll);
        dialog.add(panelIndexPage);
        dialog.add(buttonPrevious);
        dialog.add(buttonNext);

        updateContent();

        // Show dialog
        dialog.setVisible(true);
    }

    public void updateContent() {
        if (data.size() > 0) {
            panelIndexPage.setTextButton(String.format("==  %d / %d  ==", indexPageShowing + 1, data.size()));
        } else {
            panelIndexPage.setTextButton("Không có lịch học chứa đủ các môn đang xét.");
        }
        buttonPrevious.setVisible(true);
        buttonNext.setVisible(true);
        if (indexPageShowing == 0) {
            buttonPrevious.setVisible(false);
        }
        if (data.size() == 0 || indexPageShowing == data.size() - 1) {
            buttonNext.setVisible(false);
        }
        panelScroll.setBounds(0, -this.indexPageShowing * panelContent.getHeight(), panelScroll.getWidth(),
                panelScroll.getHeight());
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
        dialog.setBounds(xPos, yPos, width, height);
    }

    // Set cursor
    public void setIndexPageShowing(int value) {
        if (value < 0) {
            this.indexPageShowing = 0;
        } else if (value >= data.size()) {
            this.indexPageShowing = data.size() - 1;
        } else {
            this.indexPageShowing = value;
        }
    }

    private class MouseWheelHandler implements MouseWheelListener {
        public void mouseWheelMoved(MouseWheelEvent event) {
            setIndexPageShowing(indexPageShowing + event.getWheelRotation());
            updateContent();
        }

    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            if (event.getSource() == buttonPrevious) {
                setIndexPageShowing(indexPageShowing - 1);
                updateContent();
            } else if (event.getSource() == buttonNext) {
                setIndexPageShowing(indexPageShowing + 1);
                updateContent();
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
