package code.panel;

import javax.swing.JPanel;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import code.Setting;
import code.dialog.DialogUpdateScoreSubject;
import code.file_handler.WriteFile;
import code.objects.Button;
import code.objects.Plan;
import code.objects.Subject;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class PanelSubject4 extends JPanel {
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

    public static final Color COLOR_SUBJECT_1 = Setting.COLOR_GRAY_05;
    public static final Color COLOR_SUBJECT_2 = Setting.COLOR_GRAY_04;

    public static final Color COLOR_SUBJECT_ENTERED = Setting.COLOR_VIOLET_03;
    public static final Color COLOR_SUBJECT_EXITED_1 = Setting.COLOR_GRAY_05;
    public static final Color COLOR_SUBJECT_EXITED_2 = Setting.COLOR_GRAY_04;
    public static final Color COLOR_SUBJECT_PRESSED = Setting.COLOR_GREEN_03;
    public static final Color COLOR_STROKE = Setting.COLOR_BLACK;

    // Properties
    private int width, height; // size of this panel
    private int xPos, yPos, rootLocationType; // location of top-left point
    private Plan plan; // data plan
    private PanelSubject2[] panelSubjects = null;
    private int indexPlan;
    private int indexSubject;
    private Button panelMainSubject;
    private Button panelSubjectCode;
    private Button panelSubjectName;

    // Constructor
    public PanelSubject4(int x, int y, int width, int height, Plan plan, int indexPlan, int indexSubject,
            int rootLocationType) {
        // Properties, Objects
        this.width = width;
        this.height = height;
        this.plan = plan;
        this.indexPlan = indexPlan;
        this.indexSubject = indexSubject;
        this.rootLocationType = rootLocationType;
        setLayout(null);
        setSize(width, height);
        setLocation(x, y, rootLocationType);

        // Create panels
        if (indexSubject < 0 || indexSubject >= plan.getTimeTable().getSubjects().size()) {
            // Panel main subject
            panelMainSubject = new Button("~");
            panelMainSubject.setShapeType(1);
            panelMainSubject.addMouseListener(new MouseHandler());
            panelMainSubject.setFontText(
                    new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_SMALL));
            panelMainSubject.setLocationText(0, 0);
            panelMainSubject.setStrokeWidth(0);
            panelMainSubject.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_5, Setting.GRADIENT_POINTS2_5,
                    Setting.GRADIENT_COLORS_5);
            panelMainSubject.setGradientBackgroundColorExited(Setting.GRADIENT_POINTS1_5,
                    Setting.GRADIENT_POINTS2_5,
                    Setting.GRADIENT_COLORS_5);
            panelMainSubject.setGradientBackgroundColorEntered(Setting.GRADIENT_POINTS1_5,
                    Setting.GRADIENT_POINTS2_5,
                    Setting.GRADIENT_COLORS_5);
            panelMainSubject.setSizeButton((int) (width / 7.0 * 4 * 3 / 5), panelMainSubject.getHeight());
            panelMainSubject.setSizeButton(panelMainSubject.getWidth(),
                    (int) (panelMainSubject.getWidth() * Math.sqrt(3) / 2));
            panelMainSubject.setLocationButton(width / 2, height / 2, Button.CENTER_CENTER);
        } else {
            // Panel main subject
            panelMainSubject = new Button("");
            panelMainSubject.setShapeType(Button.HEXAGON);
            panelMainSubject.addMouseListener(new MouseHandler());
            panelMainSubject.setFontText(
                    new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_SMALL));
            panelMainSubject.setLocationText(0, 0);
            panelMainSubject.setStrokeWidth(0);
            panelMainSubject.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_5, Setting.GRADIENT_POINTS2_5,
                    Setting.GRADIENT_COLORS_5);
            panelMainSubject.setGradientBackgroundColorExited(Setting.GRADIENT_POINTS1_5,
                    Setting.GRADIENT_POINTS2_5,
                    Setting.GRADIENT_COLORS_5);
            panelMainSubject.setGradientBackgroundColorEntered(Setting.GRADIENT_POINTS1_5,
                    Setting.GRADIENT_POINTS2_5,
                    Setting.GRADIENT_COLORS_5);
            panelMainSubject.setSizeButton((int) (width / 7.0 * 4 * 3 / 5), panelMainSubject.getHeight());
            panelMainSubject.setSizeButton(panelMainSubject.getWidth(),
                    (int) (panelMainSubject.getWidth() * Math.sqrt(3) / 2));
            panelMainSubject.setLocationButton(width / 2, height / 2, Button.CENTER_CENTER);

            // Panel subject code
            panelSubjectCode = new Button(plan.getTimeTable().getSubjects().get(indexSubject).getCode());
            panelSubjectCode.setShapeType(Button.RECT_SLANTED_RIGHT);
            panelSubjectCode.addMouseListener(new MouseHandler());
            panelSubjectCode.setFontText(
                    new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_SMALL));
            panelSubjectCode.setLocationText(0, 0);
            panelSubjectCode.setStrokeWidth(0);
            panelSubjectCode.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_5, Setting.GRADIENT_POINTS2_5,
                    Setting.GRADIENT_COLORS_5);
            panelSubjectCode.setGradientBackgroundColorExited(Setting.GRADIENT_POINTS1_5,
                    Setting.GRADIENT_POINTS2_5,
                    Setting.GRADIENT_COLORS_5);
            panelSubjectCode.setGradientBackgroundColorEntered(Setting.GRADIENT_POINTS1_5,
                    Setting.GRADIENT_POINTS2_5,
                    Setting.GRADIENT_COLORS_5);

            // Panel subject Name
            panelSubjectName = new Button(plan.getTimeTable().getSubjects().get(indexSubject).getName());
            panelSubjectName.setShapeType(Button.RECT_SLANTED_RIGHT);
            panelSubjectName.addMouseListener(new MouseHandler());
            panelSubjectName.setFontText(
                    new Font(Setting.FONT_NAME_01, Setting.FONT_STYLE_01, Setting.FONT_SIZE_SMALL));
            panelSubjectName.setLocationText(0, 0);
            panelSubjectName.setStrokeWidth(0);
            panelSubjectName.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_5, Setting.GRADIENT_POINTS2_5,
                    Setting.GRADIENT_COLORS_5);
            panelSubjectName.setGradientBackgroundColorExited(Setting.GRADIENT_POINTS1_5,
                    Setting.GRADIENT_POINTS2_5,
                    Setting.GRADIENT_COLORS_5);
            panelSubjectName.setGradientBackgroundColorEntered(Setting.GRADIENT_POINTS1_5,
                    Setting.GRADIENT_POINTS2_5,
                    Setting.GRADIENT_COLORS_5);

            // Set location of panel subject code and name
            int widthCodeAndName = panelSubjectCode.getWidth() + panelSubjectName.getWidth();
            panelSubjectCode.setLocationButton(
                    (width - widthCodeAndName) / 2,
                    panelMainSubject.getY() - 10,
                    Button.BOTTOM_LEFT);
            panelSubjectName.setLocationButton(
                    panelSubjectCode.getX() + panelSubjectCode.getWidth()
                            - (int) (panelSubjectCode.getHeight() / Math.sqrt(3)) + 10,
                    panelSubjectCode.getY() + panelSubjectCode.getHeight(), Button.BOTTOM_LEFT);

            // Add panel
            add(panelSubjectCode);
            add(panelSubjectName);

        }

        // Add panel
        add(panelMainSubject);

        // Add MouseWheelListener to this panel
        addMouseWheelListener(new MouseWheelHandler());

    }

    // Update contentPanel
    public void updateContentShowing() {

    }

    // Update content data
    public void updateContentData() {

    }

    // Get rootLocationType
    public int getRootLocationType() {
        return this.rootLocationType;
    }

    // Get plan
    public Plan getplan() {
        return this.plan;
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

    private class MouseWheelHandler implements MouseWheelListener {
        public void mouseWheelMoved(MouseWheelEvent event) {

        }
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            repaint();
        }
    }
}
