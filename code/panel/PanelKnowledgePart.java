package code.panel;

import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;
import java.awt.Font;
import code.Setting;
import code.objects.KnowledgePart;
import code.objects.Subject;

public class PanelKnowledgePart extends JPanel {
        public PanelKnowledgePart(int x, int y, KnowledgePart knowledgePart, int width, Font font) {
                // Set default font
                if (font == null) {
                        font = new Font(Setting.FONT_NAME_01,
                                        Setting.FONT_STYLE_01,
                                        Setting.FONT_SIZE_SMALL);
                }

                // Height of this panel
                int height = 0;
                int countSubjects = 0;

                // Add row of knowledge name
                PanelString knowledgeNamePanel = new PanelString(0, height, knowledgePart.getName(), width, font,
                                PanelString.TOP_LEFT, 0);
                knowledgeNamePanel.setBackground(Setting.COLOR_RED_02);
                add(knowledgeNamePanel);
                height += knowledgeNamePanel.getHeight();

                // Add description Compulsory (if have)
                if (!knowledgePart.getDescriptionCompulsory().isEmpty()) {
                        String str = knowledgePart.getDescriptionCompulsory() + " ("
                                        + knowledgePart.getMinCreditsCompulsorySubjects() + " tín chỉ)";
                        PanelString desCompulsoryPanel = new PanelString(0, height,
                                        str, width,
                                        new Font(Setting.FONT_NAME_01,
                                                        Setting.FONT_STYLE_03,
                                                        Setting.FONT_SIZE_SMALL),
                                        PanelString.TOP_LEFT, 0);
                        desCompulsoryPanel.setBackground(Setting.COLOR_GREEN_02);
                        add(desCompulsoryPanel);
                        height += desCompulsoryPanel.getHeight();
                }

                // Add compulsory subjects (if have)
                for (Subject subject : knowledgePart.getCompulsorySubjects()) {
                        PanelSubject panelSubject = new PanelSubject(0, height, subject, width,
                                        font);
                        if (countSubjects % 2 == 0) {
                                panelSubject.setBackgroundColorPanelSubject(Setting.COLOR_GRAY_03);
                        } else {
                                panelSubject.setBackgroundColorPanelSubject(Setting.COLOR_GRAY_04);
                        }
                        countSubjects++;
                        add(panelSubject);
                        height += panelSubject.getHeight();
                }

                // Add main description of optional subjects
                if (!knowledgePart.getMainDescriptionOptionalSubjects().isEmpty()) {
                        String str = knowledgePart.getMainDescriptionOptionalSubjects();
                        PanelString desMainOptionalSubjectPanel = new PanelString(0, height,
                                        str, width,
                                        new Font(Setting.FONT_NAME_01,
                                                        Setting.FONT_STYLE_03,
                                                        Setting.FONT_SIZE_SMALL),
                                        PanelString.TOP_LEFT, 0);
                        desMainOptionalSubjectPanel.setBackground(Setting.COLOR_GREEN_01);
                        add(desMainOptionalSubjectPanel);
                        height += desMainOptionalSubjectPanel.getHeight();
                }

                // Add description Optional and subjects optional (if have)
                for (int count = 0; count < knowledgePart.getNumberOfOptionalSubjectsList(); count++) {
                        List<Subject> optionalSubjectList = knowledgePart.getOptionalSubjects().get(count);
                        String optionalDescription = knowledgePart.getDescriptionOptionals().get(count) + " ("
                                        + knowledgePart.getMinCreditsOptionalSubjects() + " tín chỉ)";
                        // Add panel of description
                        PanelString desOptionalPanel = new PanelString(0, height,
                                        optionalDescription, width,
                                        new Font(Setting.FONT_NAME_01,
                                                        Setting.FONT_STYLE_03,
                                                        Setting.FONT_SIZE_SMALL),
                                        PanelString.TOP_LEFT, 0);
                        desOptionalPanel.setBackground(Setting.COLOR_GREEN_02);
                        add(desOptionalPanel);
                        height += desOptionalPanel.getHeight();

                        // Add panel of subjects
                        for (Subject subject : optionalSubjectList) {
                                PanelSubject panelSubject = new PanelSubject(0, height, subject, width,
                                                font);
                                if (countSubjects % 2 == 0) {
                                        panelSubject.setBackgroundColorPanelSubject(Setting.COLOR_GRAY_03);
                                } else {
                                        panelSubject.setBackgroundColorPanelSubject(Setting.COLOR_GRAY_04);
                                }
                                countSubjects++;
                                add(panelSubject);
                                height += panelSubject.getHeight();
                        }

                }

                // Set up properties for this panel
                setLayout(null);
                setSize(width, height);
                setBounds(x, y, width, height);

        }

        // Auto called method of JPanel
        public void paintComponent(Graphics g) {
                super.paintComponent(g);
        }
}
