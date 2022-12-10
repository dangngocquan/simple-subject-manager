package code.panel;

import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.util.Random;
import java.awt.event.MouseEvent;
import code.Setting;
import code.dialog.DialogUpdateScoreSubject;
import code.file_handler.WriteFile;
import code.objects.Button;
import code.objects.Plan;
import code.objects.Subject;
import java.awt.Graphics;
import java.awt.Color;

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

    // Properties
    private int width, height; // size of this panel
    private int xPos, yPos, rootLocationType; // location of top-left point
    private Plan plan; // data plan
    private PanelSubject4[] panelSubjects = null;
    private int indexPlan;

    // Constructor
    public PanelMapRelativeSubjects(int x, int y, int width, int height, Plan plan, int indexPlan, int rootLocationType) {
        // Properties, Objects
        this.width = width;
        this.height = height;
        this.plan = plan;
        this.indexPlan = indexPlan;
        this.rootLocationType = rootLocationType;
        setLayout(null);
        setSize(width, height);
        setLocation(x, y, rootLocationType);

        // Draw panel
        int maxRow = plan.getMaxLevel() + 1;
        int maxColumn = plan.getMaxNumberSubjectInSameLevel();
        int heightPerSubjectPanel = height / maxRow;
        int widthPerSubjectPanel = width / maxColumn;

        // Draw panel subjects
        panelSubjects = new PanelSubject4[plan.getSubjects().size()];
        int count = 0;
        int[] tempLocation = new int[maxRow];
        for (Subject subject : plan.getSubjects()) {
            int level = subject.getLevel();
            panelSubjects[count] = new PanelSubject4(tempLocation[level] * widthPerSubjectPanel + 15, 
                                                    level * heightPerSubjectPanel + 15, 
                                                    subject, widthPerSubjectPanel/10*8, heightPerSubjectPanel/3, null,
                                                    PanelSubject4.TOP_LEFT);
            panelSubjects[count].setToolTipText(subject.getName());
            add(panelSubjects[count]);                                        
            count++;
            tempLocation[level]++;   
        }

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
        // Draw line between panel subject
        Random random = new Random();
        for (int i = 0; i < plan.getSubjects().size(); i++) {
            g.setColor(
                new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255))
            );
            for (Subject[] parentSubjects : plan.getSubjects().get(i).getParentSubjects()) {
                for (Subject parenSubject : parentSubjects) {
                    int j = plan.getIndexOfSubject(parenSubject);
                    g.drawLine(panelSubjects[i].getCenterX(), panelSubjects[i].getY(),
                                panelSubjects[j].getCenterX(), panelSubjects[j].getBottomY());
                }
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
                    DialogUpdateScoreSubject dialog = new DialogUpdateScoreSubject(Setting.WIDTH / 2,
                            Setting.HEIGHT / 2,
                            Setting.WIDTH / 3 * 2, Setting.HEIGHT / 5 * 4,
                            DialogUpdateScoreSubject.CENTER_CENTER, "Update subject", (new String[] {}),
                            plan.getSubjects().get(i), plan.getConversionTable());
                    plan.getSubjects().set(i, dialog.getSubject());
                    WriteFile.editSubject(indexPlan, i, plan.getSubjects().get(i));
                }
            }
            updateContentData();
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            for (int count = 0; count < panelSubjects.length; count++) {
                if (e.getSource() == panelSubjects[count]) {
                    panelSubjects[count].setBackgroundColorPanelSubject(COLOR_SUBJECT_ENTERED);
                }
            }
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            for (int count = 0; count < panelSubjects.length; count++) {
                if (e.getSource() == panelSubjects[count]) {
                        panelSubjects[count].setBackgroundColorPanelSubject(COLOR_SUBJECT_EXITED_1);
                }
            }
            repaint();
        }
    }
}
