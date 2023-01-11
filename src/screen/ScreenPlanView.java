package src.screen;

import javax.swing.JPanel;
import src.animation.AnimationPanel;
import src.dialog.DialogGuideMapRelative;
import src.dialog.DialogGuideTimeTable;
import src.launcher.Application;
import src.launcher.Setting;
import src.objects.Button;
import src.objects.Plan;
import src.panel.PanelCPA;
import src.panel.PanelCalculateScoreLastTerm;
import src.panel.PanelMapRelativeSubjects;
import src.panel.PanelSubjectList;
import src.panel.PanelTimeTable;
import src.panel.PanelUpdateScoreSubject;
import src.panel.PanelUpdateStatusSubject;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;

public class ScreenPlanView extends JPanel {
        // Properties
        private ScreenExistingPlans parentScreen;
        private Application applicationFrame;
        private int indexPlan;
        private Plan plan;
        private JPanel mainScreen;
        private JPanel optionalPanel, viewPanel;
        private JPanel contentPanel;
        private PanelSubjectList panelSubjectList;
        private PanelUpdateScoreSubject panelUpdateScoreSubject;
        private PanelUpdateStatusSubject panelUpdateStatusSubject;
        private PanelMapRelativeSubjects panelMapRelativeSubjects;
        private PanelCalculateScoreLastTerm panelCalculateScoreLastTerm;
        private PanelTimeTable panelTimeTable;
        private PanelCPA panelCPA;
        private String[] buttonTexts = {
                        "Quay lại", "Danh sách môn học", "Cập nhật điểm số", "Sơ đồ liên hệ các môn học",
                        "Lịch học khả thi",
                        "Cập nhật trạng thái môn", "Tính toán điểm cuối kỳ", "CPA"
        };
        private Button[] buttons;
        private int indexButtonEntering = 1;
        private JPanel panelButtonEnetering;
        private Button buttonGuide;

        // Constructor
        public ScreenPlanView(int width, int height, ScreenExistingPlans parentScreen, Application frame, int indexPlan,
                        Plan plan) {
                // set basic properties
                this.indexPlan = indexPlan;
                this.plan = plan;
                this.parentScreen = parentScreen;
                this.applicationFrame = frame;
                setLayout(null);
                setSize(width, height);
                setBounds(0, 0, width, height);

                // Create panel
                mainScreen = new JPanel();
                mainScreen.setLayout(null);
                mainScreen.setSize(width, height);
                mainScreen.setBounds(0, 0, width, height);

                optionalPanel = new JPanel() {
                        protected void paintComponent(Graphics g) {
                                super.paintComponent(g);
                                Graphics2D g2 = (Graphics2D) g;
                                Color[][] gradientBackgroundColor = Setting.GRADIENT_COLORS_7;
                                double[][] gradientPoint1 = Setting.GRADIENT_POINTS1_7;
                                double[][] gradientPoint2 = Setting.GRADIENT_POINTS2_7;

                                // Draw background of button
                                int heightPerRow = getHeight() / gradientBackgroundColor.length;
                                for (int i = 0; i < gradientBackgroundColor.length; i++) {
                                        GradientPaint gradientPaint = new GradientPaint(
                                                        (int) gradientPoint1[i][0] * width,
                                                        (int) gradientPoint1[i][1] * heightPerRow,
                                                        gradientBackgroundColor[i][0],
                                                        (int) gradientPoint2[i][0] * width,
                                                        (int) gradientPoint2[i][1] * heightPerRow,
                                                        gradientBackgroundColor[i][1]);
                                        g2.setPaint(gradientPaint);
                                        g2.fillRect(0, 0, getWidth(), heightPerRow);
                                        g2.translate(0, heightPerRow);
                                }
                                g2.translate(0, -heightPerRow * gradientBackgroundColor.length);

                        }
                };
                optionalPanel.setLayout(null);
                optionalPanel.setSize(width / 4, height);
                optionalPanel.setBounds(0, 0, optionalPanel.getWidth(), optionalPanel.getHeight());

                viewPanel = new JPanel();
                viewPanel.setLayout(null);
                viewPanel.setSize(width - optionalPanel.getWidth(), height);
                viewPanel.setBounds(optionalPanel.getWidth(), 0, viewPanel.getWidth(), viewPanel.getHeight());

                contentPanel = new JPanel();
                contentPanel.setLayout(null);
                contentPanel.setSize(viewPanel.getWidth() / 32 * 31, viewPanel.getHeight() / 5 * 4);
                contentPanel.setBounds(viewPanel.getWidth() / 2 - contentPanel.getWidth() / 2,
                                viewPanel.getHeight() - contentPanel.getHeight() - 50,
                                contentPanel.getWidth(),
                                contentPanel.getHeight());

                buttons = new Button[buttonTexts.length];
                for (int count = 0; count < buttonTexts.length; count++) {
                        buttons[count] = new Button(buttonTexts[count]);
                        buttons[count].setFontText(Button.ARIAL_BOLD_21);
                        buttons[count].setCorrectSizeButton();
                        buttons[count].addMouseListener(new MouseHandler());
                }

                buttonGuide = new Button("?");
                buttonGuide.setFontText(Button.ARIAL_BOLD_21);
                buttonGuide.setCorrectSizeButton();
                buttonGuide.setSizeButton(buttonGuide.getHeight(), buttonGuide.getHeight());
                buttonGuide.addMouseListener(new MouseHandler());

                // Set size button
                buttons[1].setSizeButton(width / 4 - (buttonGuide.getWidth() + 10) * 2, buttons[1].getHeight() * 6 / 5);
                buttons[2].setSizeButton(width / 4 - (buttonGuide.getWidth() + 10) * 2, buttons[2].getHeight() * 6 / 5);
                buttons[3].setSizeButton(width / 4 - (buttonGuide.getWidth() + 10) * 2, buttons[3].getHeight() * 6 / 5);
                buttons[4].setSizeButton(width / 4 - (buttonGuide.getWidth() + 10) * 2, buttons[4].getHeight() * 6 / 5);
                buttons[5].setSizeButton(width / 4 - (buttonGuide.getWidth() + 10) * 2, buttons[5].getHeight() * 6 / 5);
                buttons[6].setSizeButton(width / 4 - (buttonGuide.getWidth() + 10) * 2, buttons[6].getHeight() * 6 / 5);
                buttons[7].setSizeButton(width / 4 - (buttonGuide.getWidth() + 10) * 2, buttons[7].getHeight() * 6 / 5);

                // Set location for each button
                buttons[0].setLocationButton(optionalPanel.getWidth() / 12, optionalPanel.getHeight() / 27 * 1,
                                Button.TOP_LEFT);
                buttons[1].setLocationButton(optionalPanel.getWidth() / 12, optionalPanel.getHeight() / 27 * 5,
                                Button.TOP_LEFT);
                buttons[2].setLocationButton(optionalPanel.getWidth() / 12, optionalPanel.getHeight() / 27 * 8,
                                Button.TOP_LEFT);
                buttons[5].setLocationButton(optionalPanel.getWidth() / 12, optionalPanel.getHeight() / 27 * 11,
                                Button.TOP_LEFT);
                buttons[3].setLocationButton(optionalPanel.getWidth() / 12, optionalPanel.getHeight() / 27 * 14,
                                Button.TOP_LEFT);
                buttons[6].setLocationButton(optionalPanel.getWidth() / 12, optionalPanel.getHeight() / 27 * 17,
                                Button.TOP_LEFT);
                buttons[4].setLocationButton(optionalPanel.getWidth() / 12, optionalPanel.getHeight() / 27 * 20,
                                Button.TOP_LEFT);
                buttons[7].setLocationButton(optionalPanel.getWidth() / 12, optionalPanel.getHeight() / 27 * 23,
                                Button.TOP_LEFT);
                buttonGuide.setLocationButton(
                                buttons[indexButtonEntering].getX() + buttons[indexButtonEntering].getWidth() + 10,
                                buttons[indexButtonEntering].getY() + buttons[indexButtonEntering].getHeight() / 2,
                                Button.CENTER_LEFT);

                // Create panels
                panelSubjectList = new PanelSubjectList(0, 0, contentPanel.getWidth(), contentPanel.getHeight(),
                                this.plan,
                                PanelSubjectList.TOP_LEFT);

                panelButtonEnetering = new JPanel();
                panelButtonEnetering.setLayout(null);
                panelButtonEnetering.setSize(optionalPanel.getWidth(), optionalPanel.getHeight() / 27 * 3);
                panelButtonEnetering.setBackground(contentPanel.getBackground());
                setIndexButtonEntering(1);

                // Add subpanels
                add(mainScreen);
                mainScreen.add(optionalPanel);
                mainScreen.add(viewPanel);
                optionalPanel.add(buttons[0]);
                optionalPanel.add(buttons[1]);
                optionalPanel.add(buttons[2]);
                optionalPanel.add(buttons[3]);
                optionalPanel.add(buttons[4]);
                optionalPanel.add(buttons[5]);
                optionalPanel.add(buttons[6]);
                optionalPanel.add(buttons[7]);
                optionalPanel.add(buttonGuide);
                optionalPanel.add(panelButtonEnetering);
                viewPanel.add(contentPanel);
                contentPanel.add(panelSubjectList);

                // Set visible of screens
                mainScreen.setVisible(true);
                panelSubjectList.setVisible(true);
        }

        // Getter
        // Get mainScreen
        public JPanel getMainScreen() {
                return this.mainScreen;
        }

        // Get parent screen
        public ScreenExistingPlans getParentScreen() {
                return this.parentScreen;
        }

        // Setter
        public void setIndexButtonEntering(int index) {
                int x1 = panelButtonEnetering.getX();
                int y1 = panelButtonEnetering.getY();
                int x2 = 0;
                int y2 = buttons[index].getY()
                                - (optionalPanel.getHeight() / 27 * 3 - buttons[this.indexButtonEntering].getHeight())
                                                / 2;
                this.indexButtonEntering = index;

                int x3 = buttonGuide.getX();
                int y3 = buttonGuide.getY();
                int x4 = buttons[indexButtonEntering].getX() + buttons[indexButtonEntering].getWidth() + 10;
                int y4 = buttons[indexButtonEntering].getY() + buttons[indexButtonEntering].getHeight() / 2
                                - buttonGuide.getHeight() / 2;
                buttonGuide.setLocationButton(
                                x4, y4,
                                Button.TOP_LEFT);
                AnimationPanel animation = new AnimationPanel(panelButtonEnetering, x1, y1, x2, y2, 300);
                AnimationPanel animation2 = new AnimationPanel(buttonGuide, x3, y3, x4, y4, 300);
                animation.start();
                animation2.start();
                if (index == 3 || index == 4) {
                        buttonGuide.setVisible(true);
                } else {
                        buttonGuide.setVisible(false);
                }

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
                        // Press at "Back" button
                        if (event.getSource() == buttons[0]) {
                                getParentScreen().getMainScreen().setVisible(true);
                                setIndexButtonEntering(1);
                                panelSubjectList.setVisible(true);
                                if (panelCPA != null) {
                                        panelCPA.setVisible(false);
                                        panelCPA.getTimer().stop();
                                }
                                if (panelUpdateScoreSubject != null) {
                                        panelUpdateScoreSubject.setVisible(false);
                                }
                                if (panelMapRelativeSubjects != null) {
                                        panelMapRelativeSubjects.setVisible(false);
                                }
                                if (panelUpdateStatusSubject != null) {
                                        panelUpdateStatusSubject.setVisible(false);
                                }
                                if (panelCalculateScoreLastTerm != null) {
                                        panelCalculateScoreLastTerm.setVisible(false);
                                }
                                if (panelTimeTable != null) {
                                        panelTimeTable.setVisible(false);
                                }
                                getParentScreen().getScreenPlanViews()[indexPlan].setVisible(false);
                                AnimationPanel animation = new AnimationPanel(getParentScreen().getMainScreen(),
                                                -getParentScreen().getMainScreen().getWidth(), 0, 0, 0,
                                                300);
                                animation.start();
                        }
                        // Press buttonGuide
                        else if (event.getSource() == buttonGuide) {
                                if (indexButtonEntering == 4) {
                                        new DialogGuideTimeTable(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                                        Setting.WIDTH / 10 * 9, Setting.HEIGHT / 10 * 9,
                                                        DialogGuideTimeTable.CENTER_CENTER, "Guide");
                                } else if (indexButtonEntering == 3) {
                                        new DialogGuideMapRelative(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                                        Setting.WIDTH / 10 * 9, Setting.HEIGHT / 10 * 9,
                                                        DialogGuideTimeTable.CENTER_CENTER, "Guide");
                                }
                        }
                        // Press at "List subject" button
                        else if (event.getSource() == buttons[1]) {
                                setIndexButtonEntering(1);
                                panelSubjectList.setVisible(true);
                                if (panelUpdateScoreSubject != null) {
                                        panelUpdateScoreSubject.setVisible(false);
                                }
                                if (panelUpdateStatusSubject != null) {
                                        panelUpdateStatusSubject.setVisible(false);
                                }
                                if (panelMapRelativeSubjects != null) {
                                        panelMapRelativeSubjects.setVisible(false);
                                }
                                if (panelCalculateScoreLastTerm != null) {
                                        panelCalculateScoreLastTerm.setVisible(false);
                                }
                                if (panelTimeTable != null) {
                                        panelTimeTable.setVisible(false);
                                }
                                if (panelCPA != null) {
                                        panelCPA.setVisible(false);
                                        panelCPA.getTimer().stop();
                                }
                                AnimationPanel animation = new AnimationPanel(panelSubjectList,
                                                panelSubjectList.getWidth(), 0, 0, 0,
                                                300);
                                animation.start();
                        }
                        // Press at "Update score of subjects" button
                        else if (event.getSource() == buttons[2]) {
                                setIndexButtonEntering(2);
                                panelSubjectList.setVisible(false);
                                if (panelUpdateScoreSubject == null) {
                                        panelUpdateScoreSubject = new PanelUpdateScoreSubject(0, 0,
                                                        contentPanel.getWidth(),
                                                        contentPanel.getHeight(),
                                                        plan, indexPlan, PanelUpdateScoreSubject.TOP_LEFT);
                                        panelUpdateScoreSubject.updateContentData();
                                        contentPanel.add(panelUpdateScoreSubject);
                                } else {
                                        panelUpdateScoreSubject.updateContentData();
                                        panelUpdateScoreSubject.setVisible(true);
                                }
                                if (panelUpdateStatusSubject != null) {
                                        panelUpdateStatusSubject.setVisible(false);
                                }
                                if (panelMapRelativeSubjects != null) {
                                        panelMapRelativeSubjects.setVisible(false);
                                }
                                if (panelCalculateScoreLastTerm != null) {
                                        panelCalculateScoreLastTerm.setVisible(false);
                                }
                                if (panelTimeTable != null) {
                                        panelTimeTable.setVisible(false);
                                }
                                if (panelCPA != null) {
                                        panelCPA.setVisible(false);
                                        panelCPA.getTimer().stop();
                                }
                                AnimationPanel animation = new AnimationPanel(panelUpdateScoreSubject,
                                                panelUpdateScoreSubject.getWidth(), 0, 0, 0,
                                                300);
                                animation.start();
                        }
                        // Press at "Update status subjects" button
                        else if (event.getSource() == buttons[5]) {
                                setIndexButtonEntering(5);
                                panelSubjectList.setVisible(false);
                                if (panelUpdateScoreSubject != null) {
                                        panelUpdateScoreSubject.setVisible(false);
                                }
                                if (panelUpdateStatusSubject == null) {
                                        panelUpdateStatusSubject = new PanelUpdateStatusSubject(0, 0,
                                                        contentPanel.getWidth(),
                                                        contentPanel.getHeight(),
                                                        plan, indexPlan, PanelUpdateStatusSubject.TOP_LEFT);
                                        contentPanel.add(panelUpdateStatusSubject);
                                        panelUpdateStatusSubject.updateContentData();
                                } else {
                                        panelUpdateStatusSubject.updateContentData();
                                        panelUpdateStatusSubject.setVisible(true);
                                }
                                if (panelMapRelativeSubjects != null) {
                                        panelMapRelativeSubjects.setVisible(false);
                                }
                                if (panelCalculateScoreLastTerm != null) {
                                        panelCalculateScoreLastTerm.setVisible(false);
                                }
                                if (panelTimeTable != null) {
                                        panelTimeTable.setVisible(false);
                                }
                                if (panelCPA != null) {
                                        panelCPA.setVisible(false);
                                        panelCPA.getTimer().stop();
                                }
                                AnimationPanel animation = new AnimationPanel(panelUpdateStatusSubject,
                                                panelUpdateStatusSubject.getWidth(), 0, 0, 0,
                                                300);
                                animation.start();
                        }
                        // Press at "Map relative subjects" button
                        else if (event.getSource() == buttons[3]) {
                                setIndexButtonEntering(3);
                                panelSubjectList.setVisible(false);
                                if (panelUpdateScoreSubject != null) {
                                        panelUpdateScoreSubject.setVisible(false);
                                }
                                if (panelUpdateStatusSubject != null) {
                                        panelUpdateStatusSubject.setVisible(false);
                                }
                                if (panelMapRelativeSubjects == null) {
                                        panelMapRelativeSubjects = new PanelMapRelativeSubjects(0, 0,
                                                        contentPanel.getWidth(),
                                                        contentPanel.getHeight(),
                                                        plan, indexPlan, PanelMapRelativeSubjects.TOP_LEFT);
                                        contentPanel.add(panelMapRelativeSubjects);
                                } else {
                                        panelMapRelativeSubjects.setVisible(true);
                                }
                                if (panelCalculateScoreLastTerm != null) {
                                        panelCalculateScoreLastTerm.setVisible(false);
                                }
                                if (panelTimeTable != null) {
                                        panelTimeTable.setVisible(false);
                                }
                                if (panelCPA != null) {
                                        panelCPA.setVisible(false);
                                        panelCPA.getTimer().stop();
                                }
                                AnimationPanel animation = new AnimationPanel(panelMapRelativeSubjects,
                                                panelMapRelativeSubjects.getWidth(), 0, 0, 0,
                                                300);
                                animation.start();
                        }
                        // Press at "Calculate score last term" button
                        else if (event.getSource() == buttons[6]) {
                                setIndexButtonEntering(6);
                                panelSubjectList.setVisible(false);
                                if (panelUpdateScoreSubject != null) {
                                        panelUpdateScoreSubject.setVisible(false);
                                }
                                if (panelUpdateStatusSubject != null) {
                                        panelUpdateStatusSubject.setVisible(false);
                                }
                                if (panelMapRelativeSubjects != null) {
                                        panelMapRelativeSubjects.setVisible(false);
                                }
                                if (panelCalculateScoreLastTerm == null) {
                                        panelCalculateScoreLastTerm = new PanelCalculateScoreLastTerm(0, 0,
                                                        contentPanel.getWidth(),
                                                        contentPanel.getHeight(), Button.ARIAL_BOLD_18,
                                                        PanelCalculateScoreLastTerm.TOP_LEFT,
                                                        plan.getConversionTable(),
                                                        applicationFrame);
                                        contentPanel.add(panelCalculateScoreLastTerm);
                                } else {
                                        panelCalculateScoreLastTerm.setVisible(true);
                                }
                                if (panelTimeTable != null) {
                                        panelTimeTable.setVisible(false);
                                }
                                if (panelCPA != null) {
                                        panelCPA.setVisible(false);
                                        panelCPA.getTimer().stop();
                                }
                                AnimationPanel animation = new AnimationPanel(panelCalculateScoreLastTerm,
                                                panelCalculateScoreLastTerm.getWidth(), 0, 0, 0,
                                                300);
                                animation.start();
                        }

                        // Press at "Time Table" button
                        else if (event.getSource() == buttons[4]) {
                                setIndexButtonEntering(4);
                                panelSubjectList.setVisible(false);
                                if (panelUpdateScoreSubject != null) {
                                        panelUpdateScoreSubject.setVisible(false);
                                }
                                if (panelUpdateStatusSubject != null) {
                                        panelUpdateStatusSubject.setVisible(false);
                                }
                                if (panelMapRelativeSubjects != null) {
                                        panelMapRelativeSubjects.setVisible(false);
                                }
                                if (panelCalculateScoreLastTerm != null) {
                                        panelCalculateScoreLastTerm.setVisible(false);
                                }
                                if (panelTimeTable == null) {
                                        panelTimeTable = new PanelTimeTable(0, 0, contentPanel.getWidth(),
                                                        contentPanel.getHeight(), null, PanelTimeTable.TOP_LEFT,
                                                        plan, indexPlan, applicationFrame);
                                        contentPanel.add(panelTimeTable);
                                } else {
                                        panelTimeTable.setVisible(true);
                                }
                                if (panelCPA != null) {
                                        panelCPA.setVisible(false);
                                        panelCPA.getTimer().stop();
                                }
                                AnimationPanel animation = new AnimationPanel(panelTimeTable,
                                                panelTimeTable.getWidth(), 0, 0, 0,
                                                300);
                                animation.start();
                        }
                        // Press at "CPA" button
                        else if (event.getSource() == buttons[7]) {
                                setIndexButtonEntering(7);
                                panelSubjectList.setVisible(false);
                                if (panelUpdateScoreSubject != null) {
                                        panelUpdateScoreSubject.setVisible(false);
                                }
                                if (panelUpdateStatusSubject != null) {
                                        panelUpdateStatusSubject.setVisible(false);
                                }
                                if (panelMapRelativeSubjects != null) {
                                        panelMapRelativeSubjects.setVisible(false);
                                }
                                if (panelCalculateScoreLastTerm != null) {
                                        panelCalculateScoreLastTerm.setVisible(false);
                                }
                                if (panelTimeTable != null) {
                                        panelTimeTable.setVisible(false);
                                }
                                if (panelCPA == null) {
                                        panelCPA = new PanelCPA(0, 0, contentPanel.getWidth(),
                                                        contentPanel.getHeight(), plan, PanelTimeTable.TOP_LEFT);
                                        contentPanel.add(panelCPA);
                                        panelCPA.updateCalculate();
                                        panelCPA.getTimer().start();
                                } else {
                                        panelCPA.setVisible(true);
                                        panelCPA.updateCalculate();
                                        panelCPA.getTimer().start();
                                }
                                AnimationPanel animation = new AnimationPanel(panelCPA,
                                                panelCPA.getWidth(), 0, 0, 0,
                                                300);
                                animation.start();
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
