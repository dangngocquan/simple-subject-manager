package src.panel;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import src.algorithm.CalculateCPA;
import src.launcher.Setting;
import src.objects.ArrowVector;
import src.objects.Button;
import src.objects.Plan;
import java.util.LinkedList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.GradientPaint;

public class PanelCPA extends JPanel {
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

    public static final int DEFAULT_SPEED_ANIMATION = 5000;

    // Properties
    private int width, height; // size of this panel
    private int xPos, yPos, rootLocationType; // location of top-left point
    private Plan plan; // data plan
    private Button panelCurrentCPA;
    double[] minAndMaxCPA;
    List<Double> importantPoints;
    List<String> groups;
    private Button panelMinCPA;
    private Button panelMaxCPA;
    private JCheckBox checkbox1;
    private JPanel panelDetails;
    private JPanel panelDetails1;
    private int cursorInPanelDetails1 = 0;
    private Timer timer = null;
    private int speedAutoChangeCursorPanelDetails1 = DEFAULT_SPEED_ANIMATION;
    private JPanel panelDetails2;
    private double[] dataPanelDetails2 = null;
    private Button panelNumberCreditLeft, panelGPANeed;
    private Button panelScoreCount, panelScoreCount1, panelScoreCount2;

    // Constructor
    public PanelCPA(int x, int y, int width, int height, Plan plan, int rootLocationType) {
        // Properties, Objects
        this.width = width;
        this.height = height;
        this.plan = plan;
        this.rootLocationType = rootLocationType;
        setLayout(null);
        setSize(width, height);
        setLocation(x, y, rootLocationType);

        int tempHeight = 0;
        // panel current CPA
        panelCurrentCPA = new Button(String.format("CPA hiện tại: %.2f", CalculateCPA.getCurrentCPA(this.plan)));
        panelCurrentCPA.setFontText(Button.ARIAL_BOLD_18);
        panelCurrentCPA.setCorrectSizeButton();
        panelCurrentCPA.setLocationText(15, 0);
        panelCurrentCPA.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        panelCurrentCPA.setEnable(false);
        panelCurrentCPA.setStrokeWidth(0);
        tempHeight += panelCurrentCPA.getHeight() + 30;

        // panel min and max CPA
        minAndMaxCPA = CalculateCPA.getMinAndMaxCPA(plan, false);

        panelMinCPA = new Button(String.format("CPA tệ nhất có thể: %.2f", minAndMaxCPA[0]));
        panelMinCPA.setFontText(Button.ARIAL_BOLD_18);
        panelMinCPA.setCorrectSizeButton();
        panelMinCPA.setLocationText(15, 0);
        panelMinCPA.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        panelMinCPA.setEnable(false);
        panelMinCPA.setStrokeWidth(0);

        panelMaxCPA = new Button(String.format("CPA tốt nhất có thể: %.2f", minAndMaxCPA[1]));
        panelMaxCPA.setFontText(Button.ARIAL_BOLD_18);
        panelMaxCPA.setCorrectSizeButton();
        panelMaxCPA.setLocationText(15, 0);
        panelMaxCPA.setLocationButton(0, tempHeight, Button.TOP_LEFT);
        panelMaxCPA.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_8, Setting.GRADIENT_POINTS2_8,
                Setting.GRADIENT_COLORS_8);
        panelMaxCPA.setEnable(false);
        panelMaxCPA.setStrokeWidth(0);

        // Create data important pointsand groups
        double[] initialImportantPoints = new double[] {
                1.0, 2.0, 2.5, 3.2, 3.6, 4.0
        };
        String[] initialGroups = new String[] {
                "Yếu", "Trung bình", "Khá", "Giỏi", "Xuất sắc"
        };
        importantPoints = new LinkedList<>();
        importantPoints.add(minAndMaxCPA[0]);
        importantPoints.add(minAndMaxCPA[1]);
        groups = new LinkedList<>();
        for (int index = initialGroups.length; index >= 0; index--) {
            if (initialImportantPoints[index] > importantPoints.get(0)
                    && initialImportantPoints[index] < importantPoints.get(importantPoints.size() - 1)) {
                importantPoints.add(1, initialImportantPoints[index]);
            }
        }
        for (int index = 0; index < importantPoints.size() - 1; index++) {
            double value = importantPoints.get(index);
            int indexFounding = 0;
            for (int index1 = 0; index1 < initialImportantPoints.length; index1++) {
                if (value >= initialImportantPoints[index1]) {
                    indexFounding = index1;
                }
            }
            groups.add(initialGroups[indexFounding]);
        }

        // set size again
        int maxWidth = Math.max(Math.max(panelCurrentCPA.getWidth(), panelMinCPA.getWidth()), panelMaxCPA.getWidth());
        panelCurrentCPA.setSizeButton(maxWidth, panelCurrentCPA.getHeight());
        panelMinCPA.setSizeButton(maxWidth, panelMinCPA.getHeight());
        panelMaxCPA.setSizeButton(maxWidth, panelMaxCPA.getHeight());

        panelCurrentCPA.setLocationButton(0, tempHeight, Button.TOP_LEFT);
        panelMinCPA.setLocationButton(width / 2, tempHeight,
                Button.TOP_CENTER);
        panelMaxCPA.setLocationButton(width, tempHeight, Button.TOP_RIGHT);

        tempHeight += panelCurrentCPA.getHeight() + 15;

        // Checkbox
        checkbox1 = new JCheckBox("Học cải thiện những môn D/D+");
        checkbox1.setFont(Button.ARIAL_BOLD_18);
        checkbox1.setFocusPainted(false);
        checkbox1.addItemListener(new ItemHandler());
        checkbox1.setSize(panelCurrentCPA.getWidth() * 3, panelCurrentCPA.getHeight());
        checkbox1.setLocation(0, tempHeight);
        tempHeight += checkbox1.getHeight() + 15;

        // panel details
        panelDetails = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Setting.COLOR_GRAY_02);
                g2.fillRect(0, 0, panelDetails.getWidth(), panelDetails.getHeight());
                // Draw cursor panel entering
                if (cursorInPanelDetails1 > -1 && cursorInPanelDetails1 < groups.size()) {
                    int widthCursor = (int) ((importantPoints.get(cursorInPanelDetails1 + 1)
                            - importantPoints.get(cursorInPanelDetails1)) / 4.0
                            * (panelDetails1.getWidth() / 400 * 400));
                    int heightCursor = panelDetails1.getHeight() + 5;
                    int xStart = (int) (importantPoints.get(cursorInPanelDetails1) / 4.0
                            * (panelDetails1.getWidth() / 400 * 400));
                    int yStart = 5;
                    g2.setPaint(new GradientPaint(xStart, 0, Setting.COLOR_GREEN_03,
                            xStart, heightCursor, Setting.COLOR_GRAY_05));
                    g2.fillRect(xStart, yStart, widthCursor, heightCursor);

                }
            }
        };
        panelDetails.setLayout(null);
        panelDetails.setSize(width, height - tempHeight);
        panelDetails.setBounds(0, tempHeight, panelDetails.getWidth(),
                panelDetails.getHeight());

        // Panel details 1 - sub panel of 'panelDetails'
        panelDetails1 = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int tempWidth = panelDetails1.getWidth() / 400 * 400;
                int yMainLine = panelDetails1.getHeight() / 2;

                // Draw cursor panel entering
                if (cursorInPanelDetails1 > -1 && cursorInPanelDetails1 < groups.size()) {
                    int widthCursor = (int) ((importantPoints.get(cursorInPanelDetails1 + 1)
                            - importantPoints.get(cursorInPanelDetails1)) / 4.0
                            * (panelDetails1.getWidth() / 400 * 400));
                    int heightCursor = panelDetails1.getHeight();
                    int xStart = (int) (importantPoints.get(cursorInPanelDetails1) / 4.0
                            * (panelDetails1.getWidth() / 400 * 400));
                    int yStart = 0;
                    g2.setPaint(new GradientPaint(xStart, 0, Setting.COLOR_VIOLET_03,
                            xStart, heightCursor, Setting.COLOR_GRAY_05));
                    g2.fillRect(xStart, yStart, widthCursor, heightCursor);

                }

                // Out fisrt
                int widthOutCursor = (int) ((importantPoints.get(0) - 0.0) / 4.0
                        * (panelDetails1.getWidth() / 400 * 400));
                int heightOutCursor = panelDetails1.getHeight();
                int xStart = (int) (0.0 / 4.0 * (panelDetails1.getWidth() / 400 * 400));
                int yStart = 0;
                g2.setPaint(new GradientPaint(xStart, 0, Setting.COLOR_RED_06,
                        xStart, heightOutCursor, Setting.COLOR_GRAY_05));
                g2.fillRect(xStart, yStart, widthOutCursor, heightOutCursor);

                // Out last
                widthOutCursor = (int) ((6.0 - (importantPoints.get(importantPoints.size() - 1))) / 4.0
                        * (panelDetails1.getWidth() / 400 * 400));
                heightOutCursor = panelDetails1.getHeight();
                xStart = (int) (importantPoints.get(importantPoints.size() - 1) / 4.0
                        * (panelDetails1.getWidth() / 400 * 400));
                yStart = 0;
                g2.setPaint(new GradientPaint(xStart, 0, Setting.COLOR_RED_06,
                        xStart, heightOutCursor, Setting.COLOR_GRAY_05));
                g2.fillRect(xStart, yStart, widthOutCursor, heightOutCursor);

                // Draw main line
                g2.setColor(Setting.COLOR_BLACK);
                g2.setStroke(new BasicStroke(3));
                ArrowVector.drawArrowLine(g2, 0, yMainLine, panelDetails1.getWidth(),
                        yMainLine, 20, 10);
                double[] x0 = new double[] {
                        0.0,
                        0.5,
                        1.0,
                        1.5,
                        2.0,
                        2.5,
                        3.0,
                        3.5,
                        4.0
                };

                g2.setStroke(new BasicStroke(1));
                g2.setFont(Button.ARIAL_BOLD_12);
                for (double x1 : x0) {
                    g2.drawString(x1 + "", (int) (x1 * tempWidth / 4 + 1), yMainLine - 7);
                    g2.drawLine((int) (x1 * tempWidth / 4), yMainLine - 5, (int) (x1 * tempWidth / 4), yMainLine + 5);
                }

                // Draw line for min and max CPA
                x0 = new double[] {
                        minAndMaxCPA[0],
                        minAndMaxCPA[1]
                };
                g2.setStroke(new BasicStroke(1));
                g2.setFont(Button.ARIAL_BOLD_12);
                for (double x1 : x0) {
                    g2.drawString(String.format("%.2f", x1), (int) (x1 * tempWidth / 4 + 1),
                            yMainLine + panelDetails1.getHeight() / 4 + 12);
                    g2.drawLine((int) (x1 * tempWidth / 4), yMainLine - 20, (int) (x1 * tempWidth / 4),
                            yMainLine + panelDetails1.getHeight() / 4);
                }

                // Draw line for 3.6, 3.2, 2.5, 2.0, 0.0
                double[] xx0 = new double[] { 1.0, 2.0, 2.5, 3.2, 3.6 };
                String[] texts = new String[] {
                        "Yếu", "Trung bình", "Khá", "Giỏi", "Xuất sắc"
                };
                Color[] colors = new Color[] {
                        new Color(255, 0, 0),
                        new Color(255, 193, 85),
                        new Color(255, 255, 229),
                        new Color(137, 209, 153),
                        new Color(0, 100, 255)

                };
                g2.setStroke(new BasicStroke(1));
                g2.setFont(Button.ARIAL_BOLD_12);
                for (int index = 0; index < xx0.length; index++) {
                    int x1 = (int) (xx0[index] * tempWidth / 4);
                    g2.drawString(xx0[index] + "", x1 + 1, yMainLine - panelDetails1.getHeight() / 4 - 2);
                    g2.setColor(colors[index]);
                    g2.drawString(texts[index], x1 + 1, yMainLine - panelDetails1.getHeight() / 4 - 2 - 15);
                    g2.setColor(Setting.COLOR_BLACK);
                    g2.drawLine(x1, yMainLine - panelDetails1.getHeight() / 4, x1,
                            yMainLine + 20);
                }
            }
        };
        panelDetails1.addMouseListener(new MouseHandler());
        panelDetails1.setLayout(null);
        panelDetails1.setSize(panelDetails.getWidth(), panelDetails.getHeight() / 3);
        panelDetails1.setBounds(0, 5, panelDetails1.getWidth(),
                panelDetails1.getHeight());

        // Panel details 2 - sub panel of 'panelDetails'
        panelDetails2 = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(new GradientPaint(0, 0, Setting.COLOR_GRAY_05,
                        0, panelDetails2.getHeight() * 2, Setting.COLOR_GREEN_03));
                g2.fillRect(0, 0, panelDetails2.getWidth(), panelDetails2.getHeight());
            }
        };
        panelDetails2.addMouseListener(new MouseHandler());
        panelDetails2.setLayout(null);
        panelDetails2.setSize(panelDetails.getWidth(),
                panelDetails.getHeight() - panelDetails1.getHeight() - panelDetails1.getX() - 5);
        panelDetails2.setBounds(0, panelDetails1.getY() + panelDetails1.getHeight() + 5, panelDetails2.getWidth(),
                panelDetails2.getHeight());

        // Data panel details 2
        dataPanelDetails2 = CalculateCPA.getWorstCase(plan, importantPoints.get(cursorInPanelDetails1),
                checkbox1.isSelected());

        int tempHeightDetails2 = 30;
        panelNumberCreditLeft = new Button(String.format("Số tín chỉ chưa hoàn thành %s: %d",
                checkbox1.isSelected() ? "hoặc chưa học cải thiện" : "", (int) dataPanelDetails2[0]));
        panelNumberCreditLeft.setFontText(Button.ARIAL_BOLD_18);
        panelNumberCreditLeft.setCorrectSizeButton();
        panelNumberCreditLeft.setLocationButton(panelDetails2.getWidth() / 7, tempHeightDetails2,
                Button.TOP_LEFT);
        panelNumberCreditLeft.setEnable(false);
        tempHeightDetails2 += panelNumberCreditLeft.getHeight() + 10;

        panelGPANeed = new Button(
                String.format("Để đạt Bằng loại %s thì Điểm trung bình tối thiểu đối với %d tín chỉ còn lại là: %s",
                        groups.get(cursorInPanelDetails1), (int) dataPanelDetails2[0],
                        Math.ceil(dataPanelDetails2[1] * 100) / 100.0));
        panelGPANeed.setFontText(Button.ARIAL_BOLD_18);
        panelGPANeed.setCorrectSizeButton();
        panelGPANeed.setLocationButton(panelNumberCreditLeft.getX(), tempHeightDetails2,
                Button.TOP_LEFT);
        panelGPANeed.setEnable(false);
        tempHeightDetails2 += panelGPANeed.getHeight() + 10;

        panelScoreCount = new Button("Tương đương với trường hợp tối thiểu:");
        panelScoreCount.setFontText(Button.ARIAL_BOLD_18);
        panelScoreCount.setCorrectSizeButton();
        panelScoreCount.setLocationButton(panelNumberCreditLeft.getX(), tempHeightDetails2,
                Button.TOP_LEFT);
        panelScoreCount.setEnable(false);
        tempHeightDetails2 += panelScoreCount.getHeight() + 10;

        panelScoreCount1 = new Button(
                String.format("%d / %d tín chỉ còn lại đạt điểm %s",
                        (int) dataPanelDetails2[3], (int) dataPanelDetails2[0],
                        plan.getConversionTable().convert4ToAlpha(dataPanelDetails2[2])));
        panelScoreCount1.setFontText(Button.ARIAL_BOLD_18);
        panelScoreCount1.setCorrectSizeButton();
        panelScoreCount1.setLocationButton(panelNumberCreditLeft.getX() + 100, tempHeightDetails2,
                Button.TOP_LEFT);
        panelScoreCount1.setEnable(false);
        tempHeightDetails2 += panelScoreCount1.getHeight() + 10;

        panelScoreCount2 = new Button(
                String.format("%d / %d tín chỉ còn lại đạt điểm %s",
                        (int) dataPanelDetails2[5], (int) dataPanelDetails2[0],
                        plan.getConversionTable().convert4ToAlpha(dataPanelDetails2[4])));
        panelScoreCount2.setFontText(Button.ARIAL_BOLD_18);
        panelScoreCount2.setCorrectSizeButton();
        panelScoreCount2.setLocationButton(panelNumberCreditLeft.getX() + 100, tempHeightDetails2,
                Button.TOP_LEFT);
        panelScoreCount2.setEnable(false);
        tempHeightDetails2 += panelScoreCount2.getHeight() + 10;

        // Add panel
        add(panelCurrentCPA);
        add(panelMinCPA);
        add(panelMaxCPA);
        add(checkbox1);
        add(panelDetails);
        panelDetails.add(panelDetails1);
        panelDetails.add(panelDetails2);
        panelDetails2.add(panelNumberCreditLeft);
        panelDetails2.add(panelGPANeed);
        panelDetails2.add(panelScoreCount);
        panelDetails2.add(panelScoreCount1);
        panelDetails2.add(panelScoreCount2);

        // Auto change value of cursor panel details1
        timer = new Timer(speedAutoChangeCursorPanelDetails1,
                new ActionListener() {
                    long process = 0;
                    long startMillis = System.currentTimeMillis();

                    public void actionPerformed(ActionEvent event) {
                        process = System.currentTimeMillis() - startMillis;
                        if (process >= speedAutoChangeCursorPanelDetails1) {
                            process -= speedAutoChangeCursorPanelDetails1;
                            startMillis = System.currentTimeMillis();
                            cursorInPanelDetails1++;
                            cursorInPanelDetails1 %= groups.size();
                            updateCalculate();
                            speedAutoChangeCursorPanelDetails1 = DEFAULT_SPEED_ANIMATION;
                        }
                    }
                });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);

    }

    // Get timer
    public Timer getTimer() {
        return this.timer;
    }

    // calculate again
    public void updateCalculate() {
        panelCurrentCPA.setTextButton(String.format("CPA hiện tại: %.2f", CalculateCPA.getCurrentCPA(this.plan)));

        minAndMaxCPA = CalculateCPA.getMinAndMaxCPA(plan, checkbox1.isSelected());
        panelMinCPA.setTextButton(String.format("CPA tệ nhất có thể: %.2f", minAndMaxCPA[0]));
        panelMaxCPA.setTextButton(String.format("CPA tốt nhất có thể: %.2f", minAndMaxCPA[1]));

        double[] initialImportantPoints = new double[] {
                1.0, 2.0, 2.5, 3.2, 3.6, 4.0
        };
        String[] initialGroups = new String[] {
                "Yếu", "Trung bình", "Khá", "Giỏi", "Xuất sắc"
        };
        importantPoints = new LinkedList<>();
        importantPoints.add(minAndMaxCPA[0]);
        importantPoints.add(minAndMaxCPA[1]);
        groups = new LinkedList<>();
        for (int index = initialGroups.length; index >= 0; index--) {
            if (initialImportantPoints[index] > importantPoints.get(0)
                    && initialImportantPoints[index] < importantPoints.get(importantPoints.size() - 1)) {
                importantPoints.add(1, initialImportantPoints[index]);
            }
        }
        for (int index = 0; index < importantPoints.size() - 1; index++) {
            double value = importantPoints.get(index);
            int indexFounding = 0;
            for (int index1 = 0; index1 < initialImportantPoints.length; index1++) {
                if (value >= initialImportantPoints[index1]) {
                    indexFounding = index1;
                }
            }
            groups.add(initialGroups[indexFounding]);
        }

        dataPanelDetails2 = CalculateCPA.getWorstCase(plan, importantPoints.get(cursorInPanelDetails1),
                checkbox1.isSelected());
        panelNumberCreditLeft.setTextButton(String.format("Số tín chỉ chưa hoàn thành %s: %d",
                checkbox1.isSelected() ? "hoặc chưa học cải thiện" : "", (int) dataPanelDetails2[0]));
        panelNumberCreditLeft.setCorrectSizeButton();
        panelGPANeed.setTextButton(
                String.format("Để đạt Bằng loại %s thì Điểm trung bình tối thiểu đối với %d tín chỉ còn lại là: %s",
                        groups.get(cursorInPanelDetails1), (int) dataPanelDetails2[0],
                        Math.ceil(dataPanelDetails2[1] * 100) / 100.0));
        panelGPANeed.setCorrectSizeButton();
        panelScoreCount1.setTextButton(String.format("%d / %d tín chỉ còn lại đạt điểm %s",
                (int) dataPanelDetails2[3], (int) dataPanelDetails2[0],
                plan.getConversionTable().convert4ToAlpha(dataPanelDetails2[2])));
        panelScoreCount1.setCorrectSizeButton();
        panelScoreCount2.setTextButton(String.format("%d / %d tín chỉ còn lại đạt điểm %s",
                (int) dataPanelDetails2[5], (int) dataPanelDetails2[0],
                plan.getConversionTable().convert4ToAlpha(dataPanelDetails2[4])));
        panelScoreCount2.setCorrectSizeButton();

        panelDetails1.repaint();
        panelDetails.repaint();

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

    private class ItemHandler implements ItemListener {
        public void itemStateChanged(ItemEvent event) {
            // Press checkbox "Auto selection"
            if (event.getSource() == checkbox1) {
                updateCalculate();
            }
        }
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            if (event.getSource() == panelDetails1) {
                for (int index = 0; index < importantPoints.size() - 1; index++) {
                    int xMouse = event.getX();
                    int xStart = (int) (importantPoints.get(index) / 4.0 * (panelDetails1.getWidth() / 400 * 400));
                    int xEnd = xStart + (int) ((importantPoints.get(index + 1) - importantPoints.get(index)) / 4.0
                            * (panelDetails1.getWidth() / 400 * 400));
                    if (xMouse >= xStart && xMouse < xEnd) {
                        cursorInPanelDetails1 = index;
                        speedAutoChangeCursorPanelDetails1 = 20000;
                    }
                }
                updateCalculate();
            }
        }

        @Override
        public void mouseReleased(MouseEvent event) {

        }

        @Override
        public void mouseEntered(MouseEvent event) {
            if (event.getSource() == panelDetails2) {
                timer.stop();
            }
        }

        @Override
        public void mouseExited(MouseEvent event) {
            if (event.getSource() == panelDetails2) {
                timer.start();
            }
        }
    }
}
