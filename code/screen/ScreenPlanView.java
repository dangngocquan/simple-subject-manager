package code.screen;

import javax.swing.JPanel;
import code.Application;
import code.Setting;
import code.objects.Button;
import code.objects.Plan;
import code.panel.PanelCalculateScoreLastTerm;
import code.panel.PanelMapRelativeSubjects;
import code.panel.PanelSubjectList;
import code.panel.PanelUpdateStatusSubject;
import code.panel.PanelUpdateScoreSubject;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Graphics;

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
        private String[] buttonTexts = {
                        "Quay lại", "Danh sách môn học", "Cập nhật điểm số", "Sơ đồ liên hệ các môn học",
                        "Sắp xếp lịch học tạm thời của 1 học kỳ",
                        "Cập nhật trạng thái môn", "Tính toán điểm cuối kỳ"
        };
        private Button[] buttons;
        private int indexButtonEntering = 1;
        private JPanel panelButtonEnetering;

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

                optionalPanel = new JPanel();
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
                        buttons[count].setFontText(Button.SERIF_BOLD_28);
                        buttons[count].setCorrectSizeButton();
                        buttons[count].addMouseListener(new MouseHandler());
                }

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

                // Create panels
                panelSubjectList = new PanelSubjectList(0, 0, contentPanel.getWidth(), contentPanel.getHeight(),
                                this.plan,
                                PanelSubjectList.TOP_LEFT);
                panelUpdateScoreSubject = new PanelUpdateScoreSubject(0, 0, contentPanel.getWidth(),
                                contentPanel.getHeight(),
                                this.plan, indexPlan, PanelUpdateScoreSubject.TOP_LEFT);
                panelUpdateStatusSubject = new PanelUpdateStatusSubject(0, 0, contentPanel.getWidth(),
                                contentPanel.getHeight(),
                                plan, indexPlan, PanelUpdateStatusSubject.TOP_LEFT);
                panelMapRelativeSubjects = new PanelMapRelativeSubjects(0, 0, contentPanel.getWidth(),
                                contentPanel.getHeight(),
                                plan, indexPlan, PanelMapRelativeSubjects.TOP_LEFT);
                panelCalculateScoreLastTerm = new PanelCalculateScoreLastTerm(0, 0, contentPanel.getWidth(),
                                contentPanel.getHeight(), null, PanelMapRelativeSubjects.TOP_LEFT,
                                this.plan.getConversionTable(),
                                applicationFrame);

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
                optionalPanel.add(panelButtonEnetering);
                viewPanel.add(contentPanel);
                contentPanel.add(panelSubjectList);
                contentPanel.add(panelUpdateScoreSubject);
                contentPanel.add(panelUpdateStatusSubject);
                contentPanel.add(panelMapRelativeSubjects);
                contentPanel.add(panelCalculateScoreLastTerm);

                // Set visible of screens
                mainScreen.setVisible(true);
                panelSubjectList.setVisible(true);
                panelUpdateScoreSubject.setVisible(false);
                panelUpdateStatusSubject.setVisible(false);
                panelMapRelativeSubjects.setVisible(false);
                optionalPanel.setBackground(Setting.COLOR_BLUE_03);
                contentPanel.setBackground(Setting.COLOR_GRAY_03);
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
                this.indexButtonEntering = index;
                optionalPanel.remove(panelButtonEnetering);
                panelButtonEnetering.setBounds(0,
                                buttons[index].getY()
                                                - (optionalPanel.getHeight() / 27 * 3
                                                                - buttons[this.indexButtonEntering].getHeight()) / 2,
                                panelButtonEnetering.getWidth(),
                                panelButtonEnetering.getHeight());
                optionalPanel.add(panelButtonEnetering);
                optionalPanel.repaint();
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
                                getParentScreen().getScreenPlanViews()[indexPlan].setVisible(false);
                        }
                        // Press at "List subject" button
                        else if (event.getSource() == buttons[1]) {
                                setIndexButtonEntering(1);
                                panelSubjectList.setVisible(true);
                                panelUpdateScoreSubject.setVisible(false);
                                panelUpdateStatusSubject.setVisible(false);
                                panelMapRelativeSubjects.setVisible(false);
                                panelCalculateScoreLastTerm.setVisible(false);
                        }
                        // Press at "Update score of subjects" button
                        else if (event.getSource() == buttons[2]) {
                                setIndexButtonEntering(2);
                                panelSubjectList.setVisible(false);
                                panelUpdateScoreSubject.updateContentData();
                                panelUpdateScoreSubject.setVisible(true);
                                panelUpdateStatusSubject.setVisible(false);
                                panelMapRelativeSubjects.setVisible(false);
                                panelCalculateScoreLastTerm.setVisible(false);
                        }
                        // Press at "Update status subjects" button
                        else if (event.getSource() == buttons[5]) {
                                setIndexButtonEntering(5);
                                panelSubjectList.setVisible(false);
                                panelUpdateScoreSubject.setVisible(false);
                                panelUpdateStatusSubject.updateContentData();
                                panelUpdateStatusSubject.setVisible(true);
                                panelMapRelativeSubjects.setVisible(false);
                                panelCalculateScoreLastTerm.setVisible(false);
                        }
                        // Press at "Map relative subjects" button
                        else if (event.getSource() == buttons[3]) {
                                setIndexButtonEntering(3);
                                panelSubjectList.setVisible(false);
                                panelUpdateScoreSubject.setVisible(false);
                                panelUpdateStatusSubject.setVisible(false);
                                panelMapRelativeSubjects.setVisible(true);
                                panelCalculateScoreLastTerm.setVisible(false);
                        }
                        // Press at "Calculate score last term" button
                        else if (event.getSource() == buttons[6]) {
                                setIndexButtonEntering(6);
                                panelSubjectList.setVisible(false);
                                panelUpdateScoreSubject.setVisible(false);
                                panelUpdateStatusSubject.setVisible(false);
                                panelMapRelativeSubjects.setVisible(false);
                                panelCalculateScoreLastTerm.setVisible(true);
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
