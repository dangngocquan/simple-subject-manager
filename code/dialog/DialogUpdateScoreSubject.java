package code.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.MouseEvent;
import code.Setting;
import code.objects.Button;
import code.objects.ConversionTable;
import code.objects.Subject;
import code.panel.PanelString;
import java.awt.Font;

public class DialogUpdateScoreSubject {
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
    private int width;
    private JDialog dialog = null;
    private Subject subject;
    private ConversionTable conversionTable;
    private PanelString panelTitle = null;
    private PanelString panelSubjectName = null;
    private PanelString panelSubjectCode = null;
    private PanelString panelSubjectCredits = null;
    private JPanel panelSubjectParentCodes = null;
    private PanelString panelSubjectParentCodes1 = null;
    private PanelString panelSubjectParentCodes2 = null;
    private PanelString panelScore10 = null;
    private PanelString panelCharacterScore = null;
    private PanelString panelScore4 = null;
    private String[] buttonTexts = {
            "", "", ""
    };
    private Button[] buttons;

    // Constructor
    public DialogUpdateScoreSubject(int x, int y, int width, int height, int rootLocationType, String title,
            String[] messageLines, Subject subject, ConversionTable conversionTable) {
        this.width = width;
        this.subject = subject;
        this.conversionTable = conversionTable;
        // Create frame and set propertis of this frame
        JFrame f = new JFrame();
        dialog = new JDialog(f, title, true);
        dialog.setLayout(null);
        dialog.setSize(width, height);
        dialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        int xPos = x, yPos = y;
        switch (rootLocationType) {
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

        // Create objects in this panel (String, button, ...)
        int tempHeight = 30;
        String[] titles = {
                "Thông tin môn học",
                "=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:="
        };
        panelTitle = new PanelString(width / 2, tempHeight, titles, width,
                new Font(Setting.FONT_NAME_01,
                        Setting.FONT_STYLE_01,
                        Setting.FONT_SIZE_MEDIUM),
                PanelString.TOP_CENTER, 0);
        tempHeight += panelTitle.getHeight() + 20;
        panelSubjectName = new PanelString(0, tempHeight, "Tên môn học: " + subject.getName(),
                width, null, PanelString.TOP_LEFT, width / 10);
        tempHeight += panelSubjectName.getHeight() + 5;
        panelSubjectCode = new PanelString(0, tempHeight, "Mã môn học: " + subject.getCode(),
                width, null, PanelString.TOP_LEFT, width / 10);
        tempHeight += panelSubjectCode.getHeight() + 5;
        panelSubjectCredits = new PanelString(0, tempHeight, "Số tín chỉ: " + subject.getNumberCredits(),
                width, null, PanelString.TOP_LEFT, width / 10);
        tempHeight += panelSubjectCredits.getHeight() + 5;
        panelSubjectParentCodes1 = new PanelString(0, 0,
                "Các môn học tiên quyết: ",
                width / 3, null, PanelString.TOP_LEFT, width / 10);
        panelSubjectParentCodes2 = new PanelString(panelSubjectParentCodes1.getWidth(), 0,
                subject.getParentSubjectCodes(),
                width - panelSubjectParentCodes1.getWidth(), null, PanelString.TOP_LEFT, 1);
        panelSubjectParentCodes = new JPanel();
        panelSubjectParentCodes.setLayout(null);
        panelSubjectParentCodes.setSize(width,
                Math.max(panelSubjectParentCodes1.getHeight(),
                        panelSubjectParentCodes2.getHeight()));
        panelSubjectParentCodes.setBounds(0, tempHeight, panelSubjectParentCodes.getWidth(),
                panelSubjectParentCodes.getHeight());
        tempHeight += panelSubjectParentCodes.getHeight() + 5;
        panelScore10 = new PanelString(0, tempHeight, "Điểm hệ 10: " + subject.getStringScore10(),
                width, null, PanelString.TOP_LEFT, width / 10);
        tempHeight += panelScore10.getHeight() + 5;
        panelCharacterScore = new PanelString(0, tempHeight, "Điểm chữ: " + subject.getCharacterScore(),
                width, null, PanelString.TOP_LEFT, width / 10);
        tempHeight += panelCharacterScore.getHeight() + 5;
        panelScore4 = new PanelString(0, tempHeight, "Điểm hệ 4: " + subject.getStringScore4(),
                width, null, PanelString.TOP_LEFT, width / 10);
        tempHeight += panelScore4.getHeight() + 5;

        // Create buttons
        buttons = new Button[buttonTexts.length];
        for (int count = 0; count < buttonTexts.length; count++) {
            buttons[count] = new Button(buttonTexts[count]);
            buttons[count].setFont(new Font(
                    Setting.FONT_NAME_01,
                    Setting.FONT_STYLE_01,
                    Setting.FONT_SIZE_SMALL));
            buttons[count].setSizeButton(buttons[count].getHeight(), buttons[count].getHeight());
            buttons[count].setBackgroundIcon(Setting.EDIT);
            buttons[count].addMouseListener(new MouseHandler());
            dialog.add(buttons[count]);
        }

        // Set location for each button
        buttons[0].setLocationButton(panelScore10.getX() + width / 4, panelScore10.getY(), Button.TOP_LEFT);
        buttons[1].setLocationButton(panelCharacterScore.getX() + width / 4, panelCharacterScore.getY(),
                Button.TOP_LEFT);
        buttons[2].setLocationButton(panelScore4.getX() + width / 4, panelScore4.getY(), Button.TOP_LEFT);

        // add panel
        dialog.add(panelTitle);
        dialog.add(panelSubjectName);
        dialog.add(panelSubjectCode);
        dialog.add(panelSubjectCredits);
        dialog.add(panelSubjectParentCodes);
        panelSubjectParentCodes.add(panelSubjectParentCodes1);
        panelSubjectParentCodes.add(panelSubjectParentCodes2);
        dialog.add(panelScore10);
        dialog.add(panelCharacterScore);
        dialog.add(panelScore4);

        // Show dialog
        dialog.setVisible(true);
    }

    public void updateContent() {
        dialog.setVisible(false);
        dialog.remove(panelScore10);
        dialog.remove(panelCharacterScore);
        dialog.remove(panelScore4);
        panelScore10 = new PanelString(0, panelScore10.getY(), "Điểm hệ 10: " + subject.getStringScore10(),
                width, null, PanelString.TOP_LEFT, width / 10);
        panelCharacterScore = new PanelString(0, panelCharacterScore.getY(), "Điểm chữ: " + subject.getCharacterScore(),
                width, null, PanelString.TOP_LEFT, width / 10);
        panelScore4 = new PanelString(0, panelScore4.getY(), "Điểm hệ 4: " + subject.getStringScore4(),
                width, null, PanelString.TOP_LEFT, width / 10);
        dialog.add(panelScore10);
        dialog.add(panelCharacterScore);
        dialog.add(panelScore4);

        // Show dialog;
        dialog.setVisible(true);
    }

    // Get subject
    public Subject getSubject() {
        return this.subject;
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            if (event.getSource() == buttons[0]) {
                List<String> values = new LinkedList<>();
                for (int i = 0; i <= 1000; i++) {
                    values.add(i / 100.0 + "");
                }
                DialogList dialog1 = new DialogList(dialog, "Chọn điểm mà bạn muốn cập nhật", "Edit score",
                        values.toArray(), values.get(0));
                String score = dialog1.run();
                if (score != null) {
                    Double score10 = Double.parseDouble(score);
                    subject.setScore10(score10);
                    subject.setCharacterScore(conversionTable.convert10ToAlpha(score10));
                    subject.setScore4(conversionTable.convertAlphaTo4(subject.getCharacterScore()));
                    if (((int) (subject.getScore10() * 100) < 400 && (int) (subject.getScore10() * 100) >= 0) ||
                            (subject.getCharacterScore().equals("F")) ||
                            ((int) (subject.getScore4() * 100) < 100) && (int) (subject.getScore4() * 100) >= 0) {
                        subject.setScore10(-1.0);
                        subject.setCharacterScore("");
                        subject.setScore4(-1.0);
                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                DialogMessage.CENTER_CENTER,
                                "Smile",
                                new String[] { "Điểm này không đủ qua môn.",
                                        "Thôi, bao giờ qua môn thì cập nhật điểm nhé." },
                                Setting.INFORMATION);
                    }
                    updateContent();
                }
            }
            // Edit character point
            else if (event.getSource() == buttons[1]) {
                DialogList dialog1 = new DialogList(dialog, "Chọn điểm mà bạn muốn cập nhật", "Edit score",
                        conversionTable.getCharacterScore().toArray(), conversionTable.getCharacterScore().get(0));
                String score = dialog1.run();
                if (score != null) {
                    subject.setCharacterScore(score);
                    if (!conversionTable.convert10ToAlpha(subject.getScore10()).equals(score)) {
                        subject.setScore10(-1.0);
                    }
                    if (!conversionTable.convert4ToAlpha(subject.getScore4()).equals(score)) {
                        subject.setScore4(conversionTable.convertAlphaTo4(score));
                    }
                    if (((int) (subject.getScore10() * 100) < 400 && (int) (subject.getScore10() * 100) >= 0) ||
                            (subject.getCharacterScore().equals("F")) ||
                            ((int) (subject.getScore4() * 100) < 100) && (int) (subject.getScore4() * 100) >= 0) {
                        subject.setScore10(-1.0);
                        subject.setCharacterScore("");
                        subject.setScore4(-1.0);
                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                DialogMessage.CENTER_CENTER,
                                "Smile",
                                new String[] { "Điểm này không đủ qua môn.",
                                        "Thôi, bao giờ qua môn thì cập nhật điểm nhé." },
                                Setting.WARNING);
                    }
                    updateContent();
                }
            }
            // Edit point 4
            else if (event.getSource() == buttons[2]) {
                DialogList dialog1 = new DialogList(dialog, "Chọn điểm mà bạn muốn cập nhật", "Edit score",
                        conversionTable.getStringScore4().toArray(), conversionTable.getStringScore4().get(0));
                String score = dialog1.run();
                if (score != null) {
                    Double score4 = Double.parseDouble(score);
                    subject.setScore4(score4);
                    subject.setCharacterScore(conversionTable.convert4ToAlpha(score4));
                    if (!conversionTable.convert10ToAlpha(subject.getScore10()).equals(subject.getCharacterScore())) {
                        subject.setScore10(-1.0);
                    }
                    if (((int) (subject.getScore10() * 100) < 400 && (int) (subject.getScore10() * 100) >= 0) ||
                            (subject.getCharacterScore().equals("F")) ||
                            ((int) (subject.getScore4() * 100) < 100) && (int) (subject.getScore4() * 100) >= 0) {
                        subject.setScore10(-1.0);
                        subject.setCharacterScore("");
                        subject.setScore4(-1.0);
                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                DialogMessage.CENTER_CENTER,
                                "Information", new String[] { "Điểm này không đủ qua môn.",
                                        "Thôi, bao giờ qua môn thì cập nhật điểm nhé." },
                                Setting.INFORMATION);
                    }
                    updateContent();
                }
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
