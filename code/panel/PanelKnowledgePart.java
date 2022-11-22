package code.panel;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import java.awt.Font;

import code.Setting;
import code.button.Button;
import code.curriculum.KnowledgePart;
import code.curriculum.Subject;

public class PanelKnowledgePart extends JPanel {
    public PanelKnowledgePart(int x, int y, KnowledgePart knowledgePart, int width, Font font) {
        // Set default font
        if (font == null) {
            font = new Font(Setting.FONT_NAME_01,
                    Setting.FONT_STYLE_01,
                    Setting.FONT_SIZE_SMALL);
        }

        // Get height of a row
        // Create pattern button to get height
        Button button = new Button("A");
        button.setFont(font.getFontName(),
                font.getStyle(),
                font.getSize());
        int heightRow = button.getHeight();

        // Height of this panel
        int height = 0;
        List<PanelSubject> listPanelSubject = new LinkedList<PanelSubject>();

        // Add row of knowledge name
        PanelString knowledgeNamePanel = new PanelString(0, height, knowledgePart.getName(), width, font);
        add(knowledgeNamePanel);
        height += heightRow;

        // Add description Compulsory 1 (if have)
        if (!knowledgePart.getDescriptionCompulsory1().isEmpty()) {
            String str = knowledgePart.getDescriptionCompulsory1() + " ("
                    + knowledgePart.getMinCreditsCompulsorySubjects() + " tín chỉ)";
            PanelString desCompulsory1Panel = new PanelString(0, height,
                    str, width, font);
            add(desCompulsory1Panel);
            height += heightRow;
        }

        Subject subject = knowledgePart.getCompulsorySubjects1().get(1);
        PanelSubject panelSubject = new PanelSubject(0, height, subject, width,
                font);
        listPanelSubject.add(panelSubject);
        add(panelSubject);
        height += panelSubject.getHeight();

        // // Add compulsory subjects 1 (if have)
        // for (Subject subject : knowledgePart.getCompulsorySubjects1()) {
        // PanelSubject panelSubject = new PanelSubject(0, height, subject, width,
        // font);
        // listPanelSubject.add(panelSubject);
        // add(panelSubject);
        // height += panelSubject.getHeight();
        // System.out.println(panelSubject.getHeight());
        // }

        // Add description Compulsory 2 (if have)
        if (!knowledgePart.getDescriptionCompulsory2().isEmpty()) {
            String str = knowledgePart.getDescriptionCompulsory2() + " ("
                    + knowledgePart.getMinCreditsCompulsorySubjects() + " tín chỉ)";
            PanelString desCompulsory2Panel = new PanelString(0, height,
                    str, width, font);
            add(desCompulsory2Panel);
            height += heightRow;
        }

        // // Add compulsory subjects 2 (if have)
        // for (Subject subject : knowledgePart.getCompulsorySubjects2()) {
        // PanelSubject panelSubject = new PanelSubject(0, height, subject, width,
        // font);
        // listPanelSubject.add(panelSubject);
        // add(panelSubject);
        // height += panelSubject.getHeight();
        // }

        // Add description Optional 1 (if have)
        if (!knowledgePart.getDescriptionOptional1().isEmpty()) {
            String str = knowledgePart.getDescriptionOptional1() + " ("
                    + knowledgePart.getMinCreditsOptionalSubjects() + " tín chỉ)";
            PanelString desOptional1Panel = new PanelString(0, height,
                    str, width, font);
            add(desOptional1Panel);
            height += heightRow;
        }

        // // Add Optional subjects 1 (if have)
        // for (Subject subject : knowledgePart.getOptionalSubjects1()) {
        // PanelSubject panelSubject = new PanelSubject(0, height, subject, width,
        // font);
        // listPanelSubject.add(panelSubject);
        // add(panelSubject);
        // height += panelSubject.getHeight();
        // }

        // Add description Optional 2 (if have)
        if (!knowledgePart.getDescriptionOptional2().isEmpty()) {
            String str = knowledgePart.getDescriptionOptional2() + " ("
                    + knowledgePart.getMinCreditsOptionalSubjects() + " tín chỉ)";
            PanelString desOptional1Panel = new PanelString(0, height,
                    str, width, font);
            add(desOptional1Panel);
            height += heightRow;
        }

        // // Add Optional subjects 2 (if have)
        // for (Subject subject : knowledgePart.getOptionalSubjects2()) {
        // PanelSubject panelSubject = new PanelSubject(0, height, subject, width,
        // font);
        // listPanelSubject.add(panelSubject);
        // add(panelSubject);
        // height += panelSubject.getHeight();
        // }

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
