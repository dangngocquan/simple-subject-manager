package code.panel;

import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import code.Setting;
import code.dialog.DialogUpdateMapRelative;
import code.file_handler.WriteFile;
import code.objects.Button;
import code.objects.Plan;
import code.objects.Subject;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

public class PanelMapRelativeSubjects extends JPanel {
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

    public static final Color COLOR_SUBJECT_ENTERED = Setting.COLOR_VIOLET_03;
    public static final Color COLOR_SUBJECT_EXITED_1 = Setting.COLOR_GRAY_05;
    public static final Color COLOR_LINE_ENTERED = Setting.COLOR_BLACK;
    public static final Color COLOR_LINE_EXITED = Setting.COLOR_BLACK;
    public static final Color COLOR_STROKE_PANEL_SUBJECT_EXITED = Setting.COLOR_BLACK;
    public static final Color COLOR_STROKE_PANEL_SUBJECT_ENTERED = COLOR_LINE_ENTERED;

    // Properties
    private int width, height; // size of this panel
    private int xPos, yPos, rootLocationType; // location of top-left point
    private Plan plan; // data plan
    private Button[] panelSubjects = null;
    private int indexPlan;
    private ArrayList<LinkedList<Line2D>> lines = null;
    private ArrayList<LinkedList<Integer>> indexes = null; // Save index of parent-subjects of each subject
    private int indexSubjectEntering = -1;
    private Color tempColorLineEntered = COLOR_LINE_ENTERED;
    private int[] rows, columns;

    // Constructor
    public PanelMapRelativeSubjects(int x, int y, int width, int height, Plan plan, int indexPlan,
            int rootLocationType) {
        // Properties, Objects
        this.width = width;
        this.height = height;
        this.plan = plan;
        this.indexPlan = indexPlan;
        this.rootLocationType = rootLocationType;
        setLayout(null);
        setSize(width, height);
        setLocation(x, y, rootLocationType);

        this.rows = new int[plan.getSubjects().size()];
        this.columns = new int[plan.getSubjects().size()];

        // Draw panel
        int maxRow = Math.max(plan.getMaxLevel() + 1, plan.getMaxSemester() + 1);
        int maxColumn = plan.getMaxNumberSubjectInSameLevelAndSemester() + 1;
        int heightPerSubjectPanel = height / maxRow;
        int widthPerSubjectPanel = width / maxColumn;

        // Draw panel subjects
        panelSubjects = new Button[plan.getSubjects().size()];
        int count = 0;
        int[] tempLocation = new int[maxRow];

        for (int level = 0; level < maxRow; level++) {
            tempLocation[level] = (maxColumn - plan.getNumberSubjectLevelXOrSemesterX(level)) / 2;
            tempLocation[level] = Math.max(tempLocation[level], 0);
        }

        boolean isValidMap = plan.checkValidMap();

        for (Subject subject : plan.getSubjects()) {
            // Create location of this subject panel in map
            int row = subject.getRowIndexInMap();
            int column = tempLocation[row];

            // Only use 'rowIndexSorted' and 'columnIndexSorted' if them valid
            if (isValidMap) {
                row = subject.getRowIndexSorted();
                column = subject.getColumnIndexSorted();
            }

            this.rows[count] = row;
            this.columns[count] = column;

            panelSubjects[count] = new Button(subject.getCode());
            panelSubjects[count].setFontText(Button.ARIAL_BOLD_13);
            panelSubjects[count].setCorrectSizeButton();
            panelSubjects[count].setSizeButton(widthPerSubjectPanel / 10 * 8,
                    Math.max(heightPerSubjectPanel / 3, panelSubjects[count].getHeight()));
            panelSubjects[count].setLocationButton(
                    column * widthPerSubjectPanel + 15 + (row % 4) / 2 * widthPerSubjectPanel / 4,
                    row * heightPerSubjectPanel + 15, Button.TOP_LEFT);
            panelSubjects[count].setBackgroundColorButton(subject.getColor());
            panelSubjects[count].setBackgroundColorExitedButton(subject.getColor());
            panelSubjects[count].setBackgroundColorEnteredButton(COLOR_SUBJECT_ENTERED);
            panelSubjects[count].setStrokeColor(COLOR_STROKE_PANEL_SUBJECT_EXITED);
            panelSubjects[count].setStrokeWidth(2);
            panelSubjects[count].setToolTipText(String.format("%s - %s", subject.getCode(), subject.getName()));
            panelSubjects[count].addMouseListener(new MouseHandler());
            add(panelSubjects[count]);
            count++;
            tempLocation[row]++;
        }

        // Create and draw lines
        lines = new ArrayList<LinkedList<Line2D>>(plan.getSubjects().size());
        indexes = new ArrayList<LinkedList<Integer>>(plan.getSubjects().size());
        for (int i = 0; i < plan.getSubjects().size(); i++) {
            lines.add(new LinkedList<Line2D>());
            indexes.add(new LinkedList<Integer>());
        }
        for (int i = 0; i < plan.getSubjects().size(); i++) {
            Subject subject = plan.getSubjects().get(i);
            for (Subject parentSubject : subject.getParentSubjectsByList()) {
                int j = plan.getIndexOfSubject(parentSubject);
                Line2D line = new Line2D.Float(panelSubjects[j].getCenterX(), panelSubjects[j].getBottomY(),
                        panelSubjects[i].getCenterX(), panelSubjects[i].getY());
                lines.get(i).add(line);
                lines.get(j).add(line);
                indexes.get(i).add(j);
                indexes.get(j).add(i);
            }
        }

        updateDataContent();
    }

    // Update all
    public void updateDataContent() {
        // Draw panel
        int maxRow = Math.max(plan.getMaxLevel() + 1, plan.getMaxSemester() + 1);
        int maxColumn = plan.getMaxNumberSubjectInSameLevelAndSemester() + 1;
        int heightPerSubjectPanel = height / maxRow;
        int widthPerSubjectPanel = width / maxColumn;

        // Draw panel subjects
        // First, get default (valid) coordinate for all subject
        int count = 0;
        int[] tempLocation = new int[maxRow];
        for (int level = 0; level < maxRow; level++) {
            tempLocation[level] = (maxColumn - plan.getNumberSubjectLevelXOrSemesterX(level)) / 2;
            tempLocation[level] = Math.max(tempLocation[level], 0);
        }

        boolean isValidMap = plan.checkValidMap();

        for (Subject subject : plan.getSubjects()) {
            int row = subject.getRowIndexInMap();
            int column = tempLocation[row];

            if (isValidMap) {
                row = subject.getRowIndexSorted();
                column = subject.getColumnIndexSorted();
            }

            // Save this to use for sort map
            this.rows[count] = row;
            this.columns[count] = column;

            count++;
            tempLocation[row]++;
        }

        // Then, get real (valid) coordinate for all subject, use 'rowIndexSortedInMap'
        // and 'columnIndexSortedInMap'
        plan.sortMatrixSubject(this.rows, this.columns, maxRow - 1, maxColumn - 1, indexPlan);
        count = 0;
        for (Subject subject : plan.getSubjects()) {
            int row = subject.getRowIndexSorted();
            int column = subject.getColumnIndexSorted();

            this.rows[count] = row;
            this.columns[count] = column;

            panelSubjects[count].setCorrectSizeButton();
            panelSubjects[count].setSizeButton(widthPerSubjectPanel / 10 * 8,
                    Math.max(heightPerSubjectPanel / 3, panelSubjects[count].getHeight()));
            panelSubjects[count].setLocationButton(
                    column * widthPerSubjectPanel + 15 + (row % 4) / 2 * widthPerSubjectPanel / 4,
                    row * heightPerSubjectPanel + 15, Button.TOP_LEFT);
            count++;
        }

        // Create and draw lines
        lines.clear();
        for (int i = 0; i < plan.getSubjects().size(); i++) {
            lines.add(new LinkedList<Line2D>());
        }
        for (int i = 0; i < plan.getSubjects().size(); i++) {
            Subject subject = plan.getSubjects().get(i);
            for (Subject parentSubject : subject.getParentSubjectsByList()) {
                int j = plan.getIndexOfSubject(parentSubject);
                Line2D line = new Line2D.Float(panelSubjects[j].getCenterX(), panelSubjects[j].getBottomY(),
                        panelSubjects[i].getCenterX(), panelSubjects[i].getY());
                lines.get(i).add(line);
                lines.get(j).add(line);
            }
        }

        // Repaint map
        repaint();
    }

    // Get rootLocationType
    public int getRootLocationType() {
        return this.rootLocationType;
    }

    // Set index subject entering
    public void setIndexSubjectPanelEntering(int index) {
        this.indexSubjectEntering = index;
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
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setColor(COLOR_LINE_EXITED);
        if (indexSubjectEntering == -1) {
            for (int i = 0; i < lines.size(); i++) {
                for (Line2D line : lines.get(i)) {
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.draw(line);
                }
            }
        }

        if (indexSubjectEntering > -1) {
            g2.setStroke(new BasicStroke(4));
            g2.setColor(tempColorLineEntered);
            for (Line2D line : lines.get(indexSubjectEntering)) {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.draw(line);
            }
        }
    }

    private class MouseHandler implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            for (int i = 0; i < panelSubjects.length; i++) {
                if (e.getSource() == panelSubjects[i]) {
                    int oldSemester = plan.getSubjects().get(i).getSemester();
                    DialogUpdateMapRelative dialog = new DialogUpdateMapRelative(Setting.WIDTH / 2,
                            Setting.HEIGHT / 2,
                            Setting.WIDTH / 3 * 2, Setting.HEIGHT / 7 * 6,
                            DialogUpdateMapRelative.CENTER_CENTER,
                            "Update subject " + plan.getSubjects().get(i).getCode() + " - "
                                    + plan.getSubjects().get(i).getName(),
                            (new String[] {}),
                            plan.getSubjects().get(i));
                    plan.getSubjects().set(i, dialog.getSubject());
                    WriteFile.editSubject(indexPlan, i, plan.getSubjects().get(i));
                    panelSubjects[i].setBackgroundColorButton(plan.getSubjects().get(i).getColor());
                    panelSubjects[i].setBackgroundColorExitedButton(plan.getSubjects().get(i).getColor());
                    if (oldSemester != plan.getSubjects().get(i).getSemester()) {
                        updateDataContent();
                    }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            for (int count = 0; count < panelSubjects.length; count++) {
                if (e.getSource() == panelSubjects[count]) {
                    setIndexSubjectPanelEntering(count);
                    tempColorLineEntered = COLOR_LINE_ENTERED;
                    if (!plan.getSubjects().get(count).getColor().equals(Setting.COLOR_WHITE)) {
                        tempColorLineEntered = plan.getSubjects().get(count).getColor();
                    }
                    panelSubjects[count].setStrokeColor(tempColorLineEntered);
                    for (int indexParentSubject : indexes.get(indexSubjectEntering)) {
                        panelSubjects[indexParentSubject].setStrokeColor(tempColorLineEntered);
                    }
                    for (int i = 0; i < panelSubjects.length; i++) {
                        if (!indexes.get(indexSubjectEntering).contains(i) && i != indexSubjectEntering) {
                            panelSubjects[i].setVisible(false);
                        }
                    }
                }
            }
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            for (int count = 0; count < panelSubjects.length; count++) {
                if (e.getSource() == panelSubjects[count]) {
                    tempColorLineEntered = COLOR_LINE_ENTERED;
                    for (int i = 0; i < panelSubjects.length; i++) {
                        if (!indexes.get(indexSubjectEntering).contains(i) && i != indexSubjectEntering) {
                            panelSubjects[i].setVisible(true);
                        }
                    }
                    panelSubjects[count].setStrokeColor(COLOR_STROKE_PANEL_SUBJECT_EXITED);
                    for (int indexParentSubject : indexes.get(count)) {
                        panelSubjects[indexParentSubject].setStrokeColor(COLOR_STROKE_PANEL_SUBJECT_EXITED);
                    }
                }
            }
            setIndexSubjectPanelEntering(-1);
            repaint();
        }
    }
}
