package src.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import src.launcher.Setting;
import src.objects.Button;
import src.objects.ConversionTable;
import src.objects.Subject;
import src.panel.PanelString;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
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
        private JPanel panelScroll = null;
        private int scrollCursor = 0;
        private PanelString panelTitle = null;
        private PanelString panelSubjectName = null;
        private PanelString panelSubjectCode = null;
        private PanelString panelSubjectCredits = null;
        private JPanel panelSubjectParentCodes = null;
        private PanelString panelSubjectParentCodes1 = null;
        private PanelString panelSubjectParentCodes2 = null;
        private Button panelScore10 = null;
        private Button panelCharacterScore = null;
        private Button panelScore4 = null;
        private String[] buttonTexts = {
                        "", "", "", "", "", ""
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
                dialog.setIconImage(Setting.LOGO.getImage());
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

                // Create panelScroll
                this.panelScroll = new JPanel();
                panelScroll.setLayout(null);

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
                                width, null, PanelString.TOP_LEFT, this.width / 10);
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

                panelScore10 = new Button("Điểm hệ 10: " + subject.getStringScore10());
                panelScore10.setFontText(Button.ARIAL_BOLD_18);
                panelScore10.setCorrectSizeButton();
                panelScore10.setSizeButton(width / 4, panelScore10.getHeight() / 7 * 8 + 10);
                panelScore10.setLocationButton(0, tempHeight, Button.TOP_LEFT);
                panelScore10.setLocationText(width / 10, 0);
                panelScore10.setBackgroundColorButton(dialog.getBackground());
                panelScore10.setStrokeWidth(0);
                panelScore10.setEnable(false);
                tempHeight += panelScore10.getHeight() + 5;

                panelCharacterScore = new Button("Điểm chữ: " + subject.getCharacterScore());
                panelCharacterScore.setFontText(Button.ARIAL_BOLD_18);
                panelCharacterScore.setCorrectSizeButton();
                panelCharacterScore.setSizeButton(width / 4, panelCharacterScore.getHeight() / 7 * 8 + 10);
                panelCharacterScore.setLocationButton(0, tempHeight, Button.TOP_LEFT);
                panelCharacterScore.setLocationText(width / 10, 0);
                panelCharacterScore.setBackgroundColorButton(dialog.getBackground());
                panelCharacterScore.setStrokeWidth(0);
                panelCharacterScore.setEnable(false);
                tempHeight += panelCharacterScore.getHeight() + 5;

                panelScore4 = new Button("Điểm hệ 4: " + subject.getStringScore4());
                panelScore4.setFontText(Button.ARIAL_BOLD_18);
                panelScore4.setCorrectSizeButton();
                panelScore4.setSizeButton(width / 4, panelScore4.getHeight() / 7 * 8 + 10);
                panelScore4.setLocationButton(0, tempHeight, Button.TOP_LEFT);
                panelScore4.setLocationText(width / 10, 0);
                panelScore4.setBackgroundColorButton(dialog.getBackground());
                panelScore4.setStrokeWidth(0);
                panelScore4.setEnable(false);
                tempHeight += panelScore4.getHeight() + 100;

                // Create buttons
                buttons = new Button[buttonTexts.length];
                for (int count = 0; count < buttonTexts.length; count++) {
                        buttons[count] = new Button(buttonTexts[count]);
                        buttons[count].setFont(new Font(
                                        Setting.FONT_NAME_01,
                                        Setting.FONT_STYLE_01,
                                        Setting.FONT_SIZE_SMALL));
                        buttons[count].setSizeButton(buttons[count].getHeight(), buttons[count].getHeight());
                        if (count < 3) {
                                buttons[count].setBackgroundIcon(Setting.EDIT);
                        } else {
                                buttons[count].setBackgroundIcon(Setting.REMOVE);
                        }

                        buttons[count].addMouseListener(new MouseHandler());
                        panelScroll.add(buttons[count]);
                }

                // Set location for each button
                buttons[0].setLocationButton(panelScore10.getX() + panelScore10.getWidth() + 20, panelScore10.getY(),
                                Button.TOP_LEFT);
                buttons[1].setLocationButton(panelCharacterScore.getX() + panelCharacterScore.getWidth() + 20,
                                panelCharacterScore.getY(),
                                Button.TOP_LEFT);
                buttons[2].setLocationButton(panelScore4.getX() + panelScore4.getWidth() + 20, panelScore4.getY(),
                                Button.TOP_LEFT);
                buttons[3].setLocationButton(buttons[0].getX() + buttons[0].getWidth() + 20, buttons[0].getY(),
                                Button.TOP_LEFT);
                buttons[4].setLocationButton(buttons[1].getX() + buttons[1].getWidth() + 20, buttons[1].getY(),
                                Button.TOP_LEFT);
                buttons[5].setLocationButton(buttons[2].getX() + buttons[2].getWidth() + 20, buttons[2].getY(),
                                Button.TOP_LEFT);

                panelScroll.setSize(width, tempHeight);
                panelScroll.setBounds(0, -this.scrollCursor, panelScroll.getWidth(), panelScroll.getHeight());

                // add panel
                panelScroll.add(panelTitle);
                panelScroll.add(panelSubjectName);
                panelScroll.add(panelSubjectCode);
                panelScroll.add(panelSubjectCredits);
                panelScroll.add(panelSubjectParentCodes);
                panelSubjectParentCodes.add(panelSubjectParentCodes1);
                panelSubjectParentCodes.add(panelSubjectParentCodes2);
                panelScroll.add(panelScore10);
                panelScroll.add(panelCharacterScore);
                panelScroll.add(panelScore4);

                panelScroll.addMouseWheelListener(new MouseWheelHandler());
                dialog.add(panelScroll);

                // Show dialog
                dialog.setVisible(true);
        }

        public void updateContent() {
                panelScore10.setTextButton("Điểm hệ 10: " + subject.getStringScore10());
                panelCharacterScore.setTextButton("Điểm chữ: " + subject.getCharacterScore());
                panelScore4.setTextButton("Điểm hệ 4: " + subject.getStringScore4());
                panelScroll.setBounds(panelScroll.getX(), -this.scrollCursor, panelScroll.getWidth(),
                                panelScroll.getHeight());
        }

        // Get max of scrollCursor
        public int getMaxScrollCursor() {
                return Math.max(0, panelScroll.getHeight() - dialog.getHeight());
        }

        // Set cursor
        public void setScrollCursor(int value) {
                if (value < 0) {
                        this.scrollCursor = 0;
                } else if (value > getMaxScrollCursor()) {
                        this.scrollCursor = getMaxScrollCursor();
                } else {
                        this.scrollCursor = value;
                }
        }

        // Get subject
        public Subject getSubject() {
                return this.subject;
        }

        private class MouseWheelHandler implements MouseWheelListener {
                public void mouseWheelMoved(MouseWheelEvent event) {
                        for (int count = 0; count < 20; count++) {
                                setScrollCursor(scrollCursor + event.getWheelRotation());
                        }
                        updateContent();
                }

        }

        private class MouseHandler implements MouseListener {

                @Override
                public void mouseClicked(MouseEvent event) {
                }

                @Override
                public void mousePressed(MouseEvent event) {
                        // Edit score 10
                        if (event.getSource() == buttons[0]) {
                                DialogInput inputs = new DialogInput(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                                Setting.WIDTH / 2,
                                                Setting.HEIGHT / 3,
                                                DialogInput.CENTER_CENTER, "Get password",
                                                new String[] { "Hãy nhập điểm mà bạn muốn cập nhật:" },
                                                Setting.INFORMATION,
                                                new String[] { "9.04" });
                                String score = inputs.getInputString()[0];
                                if (score != null && !score.isEmpty()) {
                                        if (!score.matches("[0-9]{1,}") && !score.matches("[0-9]{1,}\\.[0-9]{1,}")) {
                                                new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                                                Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                                                DialogMessage.CENTER_CENTER,
                                                                "Information",
                                                                new String[] { "Điểm bạn vừa nhập không hợp lệ." },
                                                                Setting.WARNING);
                                        } else if (Double.parseDouble(score) > 10) {
                                                new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                                                Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                                                DialogMessage.CENTER_CENTER,
                                                                "Information",
                                                                new String[] { "Điểm bạn vừa nhập lớn hơn 10." },
                                                                Setting.WARNING);
                                        } else {
                                                Double score10 = Double.parseDouble(score);
                                                subject.setScore10(score10);
                                                subject.setCharacterScore(conversionTable.convert10ToAlpha(score10));
                                                subject.setScore4(conversionTable
                                                                .convertAlphaTo4(subject.getCharacterScore()));
                                                if (((int) (subject.getScore10() * 100) < 400
                                                                && (int) (subject.getScore10() * 100) >= 0) ||
                                                                (subject.getCharacterScore().equals("F")) ||
                                                                ((int) (subject.getScore4() * 100) < 100)
                                                                                && (int) (subject.getScore4()
                                                                                                * 100) >= 0) {
                                                        subject.setScore10(-1.0);
                                                        subject.setCharacterScore("");
                                                        subject.setScore4(-1.0);
                                                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                                                        Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                                                        DialogMessage.CENTER_CENTER,
                                                                        "Smile",
                                                                        new String[] { "Điểm này không đủ qua môn.",
                                                                                        "Thôi, bao giờ qua môn thì cập nhật điểm nhé." },
                                                                        Setting.INFORMATION);
                                                }

                                        }
                                        updateContent();
                                }
                        }
                        // Edit character point
                        else if (event.getSource() == buttons[1]) {
                                DialogList dialog1 = new DialogList(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                                Setting.WIDTH / 3, Setting.HEIGHT / 2, DialogList.CENTER_CENTER,
                                                "Edit score", new String[] { "Chọn điểm mà bạn muốn cập nhật" },
                                                conversionTable.getArrayCharacterScore());
                                String score = dialog1.getText();
                                if (score != null) {
                                        subject.setCharacterScore(score);
                                        if (!conversionTable.convert10ToAlpha(subject.getScore10()).equals(score)) {
                                                subject.setScore10(-1.0);
                                        }
                                        if (!conversionTable.convert4ToAlpha(subject.getScore4()).equals(score)) {
                                                subject.setScore4(conversionTable.convertAlphaTo4(score));
                                        }
                                        if (((int) (subject.getScore10() * 100) < 400
                                                        && (int) (subject.getScore10() * 100) >= 0) ||
                                                        (subject.getCharacterScore().equals("F")) ||
                                                        ((int) (subject.getScore4() * 100) < 100)
                                                                        && (int) (subject.getScore4() * 100) >= 0) {
                                                subject.setScore10(-1.0);
                                                subject.setCharacterScore("");
                                                subject.setScore4(-1.0);
                                                new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                                                Setting.WIDTH / 3, Setting.HEIGHT / 3,
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
                                DialogList dialog1 = new DialogList(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                                Setting.WIDTH / 3, Setting.HEIGHT / 2, DialogList.CENTER_CENTER,
                                                "Edit score", new String[] { "Chọn điểm mà bạn muốn cập nhật" },
                                                conversionTable.getArrayStringScore4());
                                String score = dialog1.getText();
                                if (score != null) {
                                        Double score4 = Double.parseDouble(score);
                                        subject.setScore4(score4);
                                        subject.setCharacterScore(conversionTable.convert4ToAlpha(score4));
                                        if (!conversionTable.convert10ToAlpha(subject.getScore10())
                                                        .equals(subject.getCharacterScore())) {
                                                subject.setScore10(-1.0);
                                        }
                                        if (((int) (subject.getScore10() * 100) < 400
                                                        && (int) (subject.getScore10() * 100) >= 0) ||
                                                        (subject.getCharacterScore().equals("F")) ||
                                                        ((int) (subject.getScore4() * 100) < 100)
                                                                        && (int) (subject.getScore4() * 100) >= 0) {
                                                subject.setScore10(-1.0);
                                                subject.setCharacterScore("");
                                                subject.setScore4(-1.0);
                                                new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                                                Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                                                DialogMessage.CENTER_CENTER,
                                                                "Information",
                                                                new String[] { "Điểm này không đủ qua môn.",
                                                                                "Thôi, bao giờ qua môn thì cập nhật điểm nhé." },
                                                                Setting.INFORMATION);
                                        }
                                        updateContent();
                                }
                        } else if (event.getSource() == buttons[3]
                                        || event.getSource() == buttons[4]
                                        || event.getSource() == buttons[5]) {
                                subject.setScore10(-1.0);
                                subject.setCharacterScore("");
                                subject.setScore4(-1.0);
                                subject.setState(Subject.STILL_NOT_REGISTER);
                                new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2,
                                                Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                                DialogMessage.CENTER_CENTER,
                                                "Information",
                                                new String[] { "Xóa thông tin điểm thành công." },
                                                Setting.INFORMATION);
                                updateContent();
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
