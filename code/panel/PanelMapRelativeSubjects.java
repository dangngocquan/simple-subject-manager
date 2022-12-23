package code.panel;

import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import code.Setting;
import code.dialog.DialogUpdateMapRelative;
import code.file_handler.WriteFile;
import code.objects.ArrowVector;
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
import java.awt.event.MouseAdapter;

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
    private int widthContent, heightContent; // size of this panel showing
    private int width, height; // real size
    private int xPos, yPos, rootLocationType; // location of top-left point
    private Plan plan; // data plan
    private Button[] panelSubjects = null;
    private int xCursor = 0, yCursor = 0;
    private int tempX, tempY;
    private int indexPlan;
    private ArrayList<LinkedList<Line2D>> lines = null;
    private ArrayList<LinkedList<Integer>> indexes = null; // Save index of parent-subjects of each subject
    private int indexSubjectEntering = -1;
    private Color tempColorLineEntered = COLOR_LINE_ENTERED;
    private int[] rows, columns;

    // Constructor
    public PanelMapRelativeSubjects(int x, int y, int widthContent, int heightContent, Plan plan, int indexPlan,
            int rootLocationType) {
        // Properties, Objects
        this.widthContent = widthContent;
        this.heightContent = heightContent;
        this.plan = plan;
        this.indexPlan = indexPlan;
        this.rootLocationType = rootLocationType;
        setLayout(null);

        this.rows = new int[plan.getSubjects().size()];
        this.columns = new int[plan.getSubjects().size()];

        // Check valid map
        boolean isValidMap = plan.checkValidMap();

        // if invalid, create default coordinate, then sort
        if (!isValidMap) {
            // Create default coordiante
            int maxRow = Math.max(plan.getMaxLevel() + 1, plan.getMaxSemester() + 1);
            int maxColumn = plan.getMaxNumberSubjectInSameLevelAndSemester() + 1;

            int count = 0;
            int[] tempLocation = new int[maxRow];

            for (int level = 0; level < maxRow; level++) {
                tempLocation[level] = (maxColumn - plan.getNumberSubjectLevelXOrSemesterX(level)) / 2;
                tempLocation[level] = Math.max(tempLocation[level], 0);
            }

            for (Subject subject : plan.getSubjects()) {
                // Create location of this subject panel in map
                int row = subject.getRowIndexInMap();
                int column = tempLocation[row];

                this.rows[count] = row;
                this.columns[count] = column;

                count++;
                tempLocation[row]++;
            }

            // Create good coordinate by use 'sortMap' method
            this.plan.sortMap3(rows, columns, indexPlan);
        }

        // Draw panel subjects
        int maxRow = plan.getMaxIndexRowSorted() + 1;
        int maxColumn = plan.getMaxIndexColumnSorted() + 1;
        this.width = (int) (widthContent * 1.0 / 12 * maxColumn);
        this.height = (int) (heightContent * 1.0 / 5 * maxRow);
        int heightPerSubjectPanel = this.height / maxRow;
        int widthPerSubjectPanel = this.width / maxColumn;
        setSize(this.width, this.height);
        setBounds(-xCursor, -yCursor, getWidth(), getHeight());

        panelSubjects = new Button[plan.getSubjects().size()];
        int count = 0;
        for (Subject subject : plan.getSubjects()) {
            // Get coordinate of subject
            int row = subject.getRowIndexSorted();
            int column = subject.getColumnIndexSorted();

            panelSubjects[count] = new Button(subject.getCode());
            panelSubjects[count].setFontText(Button.ARIAL_BOLD_13);
            panelSubjects[count].setCorrectSizeButton();
            panelSubjects[count].setSizeButton((int) (widthPerSubjectPanel * 1.0 / 10 * 7),
                    (int) (heightPerSubjectPanel * 1.0 / 3));
            panelSubjects[count].setLocationButton(
                    column * widthPerSubjectPanel + (int) (widthPerSubjectPanel * 1.0 / 2),
                    row * heightPerSubjectPanel + (int) (heightPerSubjectPanel * 1.0 / 2), Button.CENTER_CENTER);
            panelSubjects[count].setBackgroundColorButton(subject.getColor());
            panelSubjects[count].setBackgroundColorExitedButton(subject.getColor());
            panelSubjects[count].setBackgroundColorEnteredButton(COLOR_SUBJECT_ENTERED);
            panelSubjects[count].setStrokeColor(COLOR_STROKE_PANEL_SUBJECT_EXITED);
            panelSubjects[count].setStrokeWidth(2);
            panelSubjects[count].setToolTipText(String.format("%s - %s", subject.getCode(), subject.getName()));
            panelSubjects[count].addMouseListener(new MouseHandler());
            add(panelSubjects[count]);
            count++;
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
                Line2D line = null;
                if (subject.getRowIndexSorted() < parentSubject.getRowIndexSorted()) {
                    line = new Line2D.Float(panelSubjects[j].getCenterX(), panelSubjects[j].getY(),
                            panelSubjects[i].getCenterX(), panelSubjects[i].getBottomY());
                } else if (subject.getRowIndexSorted() == parentSubject.getRowIndexSorted()) {
                    if (subject.getColumnIndexSorted() < parentSubject.getColumnIndexSorted()) {
                        line = new Line2D.Float(panelSubjects[j].getX(), panelSubjects[j].getCenterY(),
                                panelSubjects[i].getRightX(), panelSubjects[i].getCenterY());
                    } else {
                        line = new Line2D.Float(panelSubjects[j].getRightX(), panelSubjects[j].getCenterY(),
                                panelSubjects[i].getX(), panelSubjects[i].getCenterY());
                    }
                } else if (subject.getRowIndexSorted() > parentSubject.getRowIndexSorted()) {
                    line = new Line2D.Float(panelSubjects[j].getCenterX(), panelSubjects[j].getBottomY(),
                            panelSubjects[i].getCenterX(), panelSubjects[i].getY());
                }

                lines.get(i).add(line);
                lines.get(j).add(line);
                indexes.get(i).add(j);
                indexes.get(j).add(i);
            }
        }

        addMouseListener(new MouseAdapterHandler());
        addMouseMotionListener(new MouseAdapterHandler());
        addMouseWheelListener(new MouseWheelHandler());
    }

    // Update all
    public void updateDataContent() {
        // Create default coordiante
        int maxRow = Math.max(plan.getMaxLevel() + 1, plan.getMaxSemester() + 1);
        int maxColumn = plan.getMaxNumberSubjectInSameLevelAndSemester() + 1;
        if (!plan.checkValidMap()) {
            int count = 0;
            int[] tempLocation = new int[maxRow];

            for (int level = 0; level < maxRow; level++) {
                tempLocation[level] = (maxColumn - plan.getNumberSubjectLevelXOrSemesterX(level)) / 2;
                tempLocation[level] = Math.max(tempLocation[level], 0);
            }

            for (Subject subject : plan.getSubjects()) {
                // Create location of this subject panel in map
                int row = subject.getRowIndexInMap();
                int column = tempLocation[row];

                this.rows[count] = row;
                this.columns[count] = column;

                count++;
                tempLocation[row]++;
            }

            // Create good coordinate by use 'sortMap' method
            this.plan.sortMap3(rows, columns, indexPlan);
        }

        // Draw panel subjects
        maxRow = plan.getMaxIndexRowSorted() + 1;
        maxColumn = plan.getMaxIndexColumnSorted() + 1;
        int heightPerSubjectPanel = this.height / maxRow;
        int widthPerSubjectPanel = this.width / maxColumn;
        setSize(this.width, this.height);
        setBounds(-xCursor, -yCursor, getWidth(), getHeight());

        int count = 0;
        for (Subject subject : plan.getSubjects()) {
            // Get coordinate of subject
            int row = subject.getRowIndexSorted();
            int column = subject.getColumnIndexSorted();
            panelSubjects[count].setSizeButton((int) (widthPerSubjectPanel * 1.0 / 10 * 7),
                    (int) (heightPerSubjectPanel * 1.0 / 3));
            panelSubjects[count].setLocationButton(
                    column * widthPerSubjectPanel + (int) (widthPerSubjectPanel * 1.0 / 2),
                    row * heightPerSubjectPanel + (int) (heightPerSubjectPanel * 1.0 / 2), Button.CENTER_CENTER);
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
                Line2D line = null;
                if (subject.getRowIndexSorted() < parentSubject.getRowIndexSorted()) {
                    line = new Line2D.Float(panelSubjects[j].getCenterX(), panelSubjects[j].getY(),
                            panelSubjects[i].getCenterX(), panelSubjects[i].getBottomY());
                } else if (subject.getRowIndexSorted() == parentSubject.getRowIndexSorted()) {
                    if (subject.getColumnIndexSorted() < parentSubject.getColumnIndexSorted()) {
                        line = new Line2D.Float(panelSubjects[j].getX(), panelSubjects[j].getCenterY(),
                                panelSubjects[i].getRightX(), panelSubjects[i].getCenterY());
                    } else {
                        line = new Line2D.Float(panelSubjects[j].getRightX(), panelSubjects[j].getCenterY(),
                                panelSubjects[i].getX(), panelSubjects[i].getCenterY());
                    }
                } else if (subject.getRowIndexSorted() > parentSubject.getRowIndexSorted()) {
                    line = new Line2D.Float(panelSubjects[j].getCenterX(), panelSubjects[j].getBottomY(),
                            panelSubjects[i].getCenterX(), panelSubjects[i].getY());
                }
                lines.get(i).add(line);
                lines.get(j).add(line);
            }
        }

        // Repaint map
        repaint();
    }

    // Update content showing
    public void updateContentShowing() {
        setBounds(-xCursor, -yCursor, getWidth(), getHeight());
    }

    // Get rootLocationType
    public int getRootLocationType() {
        return this.rootLocationType;
    }

    // Set width
    public void setRealWidth(int width) {
        if (width < this.widthContent) {
            this.width = this.widthContent;
        } else {
            this.width = width;
        }
        setSize(this.width, getHeight());
    }

    // Set height
    public void setRealHeight(int height) {
        if (height < this.heightContent) {
            this.height = this.heightContent;
        } else {
            this.height = height;
        }
        setSize(getWidth(), this.height);
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
                xPos = x - widthContent / 2;
                yPos = y;
                break;
            case 2:
                xPos = x - widthContent;
                yPos = y;
                break;
            case 3:
                xPos = x;
                yPos = y - heightContent / 2;
                break;
            case 4:
                xPos = x - widthContent / 2;
                yPos = y - heightContent / 2;
                break;
            case 5:
                xPos = x - widthContent;
                yPos = y - heightContent / 2;
                break;
            case 6:
                xPos = x;
                yPos = y - heightContent;
                break;
            case 7:
                xPos = x - widthContent / 2;
                yPos = y - heightContent;
                break;
            case 8:
                xPos = x - widthContent;
                yPos = y - heightContent;
                break;
        }
        setBounds(xPos, yPos, widthContent, heightContent);
    }

    // Get xCursor
    public int getXCursor() {
        return this.xCursor;
    }

    // Get yCursor
    public int getYCursor() {
        return this.yCursor;
    }

    // Set xCursor
    public void setXCursor(int x) {
        if (getWidth() - widthContent < 0) {
            this.xCursor = 0;
            return;
        }
        this.xCursor = x;
        if (xCursor < 0) {
            this.xCursor = 0;
        } else if (xCursor > getWidth() - widthContent) {
            this.xCursor = getWidth() - widthContent;
        }
    }

    // Set yCursor
    public void setYCursor(int y) {
        if (getHeight() - heightContent < 0) {
            this.yCursor = 0;
            return;
        }
        this.yCursor = y;
        if (y < 0) {
            this.yCursor = 0;
        } else if (yCursor > getHeight() - heightContent) {
            this.yCursor = getHeight() - heightContent;
        }
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
                    // g2.draw(line);
                    ArrowVector.drawArrowLine(g2, (int) line.getX1(), (int) line.getY1(), (int) line.getX2(),
                            (int) line.getY2(), 12, 6);
                }
            }
        }

        if (indexSubjectEntering > -1) {
            g2.setStroke(new BasicStroke(2));
            g2.setColor(tempColorLineEntered);
            for (Line2D line : lines.get(indexSubjectEntering)) {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                // g2.draw(line);
                ArrowVector.drawArrowLine(g2, (int) line.getX1(), (int) line.getY1(), (int) line.getX2(),
                        (int) line.getY2(), 12, 6);
            }
        }

        g2.setStroke(new BasicStroke(4));
        g2.setColor(Setting.COLOR_BLACK);
        g2.drawRect(3, 3, getWidth() - 8, getHeight() - 8);
    }

    private class MouseAdapterHandler extends MouseAdapter {
        public void mouseDragged(MouseEvent e) {
            int modifiedX = e.getX() - tempX;
            int modifiedY = e.getY() - tempY;
            setXCursor(xCursor - modifiedX);
            setYCursor(yCursor - modifiedY);
            updateContentShowing();
        }

        public void mousePressed(MouseEvent e) {
            tempX = e.getX();
            tempY = e.getY();
        }

        public void mouseReleased(MouseEvent e) {
        }
    }

    private class MouseWheelHandler implements MouseWheelListener {
        public void mouseWheelMoved(MouseWheelEvent event) {
            if (event.isControlDown()) {
                double distanceLeft = event.getY() - yCursor;
                double fraction = (event.getY()) * 1.0 / height;
                int newHeight = height + event.getWheelRotation() * 200;
                double newYCursor = fraction * newHeight - distanceLeft;
                setRealHeight(newHeight);
                setYCursor((int) newYCursor);
                setBounds(-xCursor, -yCursor, getWidth(), getHeight());
                updateDataContent();
                repaint();
            } else if (event.isAltDown()) {
                double distanceLeft = event.getX() - xCursor;
                double fraction = (event.getX()) * 1.0 / width;
                int newWidth = width + event.getWheelRotation() * 200;
                double newXCursor = fraction * newWidth - distanceLeft;
                setRealWidth(newWidth);
                setXCursor((int) newXCursor);
                setBounds(-xCursor, -yCursor, getWidth(), getHeight());
                updateDataContent();
                repaint();
            } else {
                double distanceLeftX = event.getX() - xCursor;
                double fractionX = (event.getX()) * 1.0 / width;
                int newWidth = (int) (width * Math.pow(1.1, event.getWheelRotation()));
                double newXCursor = fractionX * newWidth - distanceLeftX;
                setRealWidth(newWidth);
                setXCursor((int) newXCursor);

                double distanceLeftY = event.getY() - yCursor;
                double fractionY = (event.getY()) * 1.0 / height;
                int newHeight = (int) (height * Math.pow(1.1, event.getWheelRotation()));
                double newYCursor = fractionY * newHeight - distanceLeftY;
                setRealHeight(newHeight);
                setYCursor((int) newYCursor);

                setBounds(-xCursor, -yCursor, getWidth(), getHeight());
                updateDataContent();
                repaint();

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
