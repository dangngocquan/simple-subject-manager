package code.panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import code.Setting;
import code.dialog.DialogCalculateScoreLastTerm3;
import code.dialog.DialogCalculateScoreLastTerm4;
import code.dialog.DialogMessage;
import code.objects.Button;
import code.objects.ConversionTable;
import code.text_field.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics;
import java.awt.Font;

public class PanelCalculateLastTerm4 extends JPanel {
        // Properties
        private ConversionTable conversionTable;
        private JPanel mainPanel;
        private PanelString panelTitle;
        private PanelString panelScore1, panelScore2, panelScore3;
        private PanelString panelCoefficient1, panelCoefficient2, panelCoefficient3;
        private TextField textFieldScore1, textFieldScore2, textFieldScore3, textFieldCoefficient1,
                        textFieldCoefficient2,
                        textFieldCoefficient3;
        private Button buttonSubmit;

        // Constructor
        public PanelCalculateLastTerm4(int x, int y, int width, int height, Font font, ConversionTable convertionTable,
                        JFrame applicationFrame) {
                this.conversionTable = convertionTable;
                // Create defaulr font
                if (font == null) {
                        font = new Font(Setting.FONT_NAME_01,
                                        Setting.FONT_STYLE_01,
                                        Setting.FONT_SIZE_SMALL);
                }

                // Create panels
                panelTitle = new PanelString(0, 0, "Tính toán đối với môn 4 thành phần điểm", width,
                                new Font(Setting.FONT_NAME_01,
                                                Setting.FONT_STYLE_01,
                                                Setting.FONT_SIZE_MEDIUM),
                                PanelString.TOP_LEFT,
                                15);
                panelScore1 = new PanelString(0, panelTitle.getHeight() + 20, "Điểm thứ nhất: ", width / 8, font,
                                PanelString.TOP_LEFT, 15);
                panelScore2 = new PanelString(0, panelScore1.getY() + panelScore1.getHeight() + 10, "Điểm thứ hai: ",
                                width / 8, font,
                                PanelString.TOP_LEFT, 15);
                panelScore3 = new PanelString(0, panelScore2.getY() + panelScore2.getHeight() + 10, "Điểm thứ ba: ",
                                width / 8, font,
                                PanelString.TOP_LEFT, 15);
                textFieldScore1 = new TextField(panelScore1.getWidth(), panelScore1.getY(), width / 10,
                                panelScore1.getHeight(),
                                TextField.TOP_LEFT, "9.04", 2, 15, 15);
                textFieldScore2 = new TextField(panelScore2.getWidth(), panelScore2.getY(), width / 10,
                                panelScore2.getHeight(),
                                TextField.TOP_LEFT, "9.04", 2, 15, 15);
                textFieldScore3 = new TextField(panelScore3.getWidth(), panelScore3.getY(), width / 10,
                                panelScore3.getHeight(),
                                TextField.TOP_LEFT, "9.04", 2, 15, 15);
                panelCoefficient1 = new PanelString(textFieldScore1.getX() + textFieldScore1.getWidth() + width / 15,
                                panelScore1.getY(),
                                "Hệ số của điểm thứ nhất (%): ", width / 5 + 30, font, PanelString.TOP_LEFT, 15);
                panelCoefficient2 = new PanelString(textFieldScore2.getX() + textFieldScore2.getWidth() + width / 15,
                                panelScore2.getY(),
                                "Hệ số của điểm thứ hai (%): ", width / 5 + 30, font, PanelString.TOP_LEFT, 15);
                panelCoefficient3 = new PanelString(textFieldScore3.getX() + textFieldScore3.getWidth() + width / 15,
                                panelScore3.getY(),
                                "Hệ số của điểm thứ ba (%): ", width / 5 + 30, font, PanelString.TOP_LEFT, 15);
                textFieldCoefficient1 = new TextField(panelCoefficient1.getX() + panelCoefficient1.getWidth(),
                                panelCoefficient1.getY(),
                                width / 10, panelCoefficient1.getHeight(),
                                TextField.TOP_LEFT, "10", 2, 15, 15);
                textFieldCoefficient2 = new TextField(panelCoefficient2.getX() + panelCoefficient2.getWidth(),
                                panelCoefficient2.getY(),
                                width / 10, panelCoefficient2.getHeight(),
                                TextField.TOP_LEFT, "15", 2, 15, 15);
                textFieldCoefficient3 = new TextField(panelCoefficient3.getX() + panelCoefficient3.getWidth(),
                                panelCoefficient3.getY(),
                                width / 10, panelCoefficient3.getHeight(),
                                TextField.TOP_LEFT, "15", 2, 15, 15);

                buttonSubmit = new Button("Tính toán");
                buttonSubmit.setSizeButton(width / 10,
                                panelScore3.getY() + panelScore3.getHeight() - panelScore1.getY());
                buttonSubmit.setLocationButton(
                                textFieldCoefficient1.getX() + textFieldCoefficient1.getWidth() + width / 5,
                                panelScore1.getY(),
                                Button.TOP_LEFT);
                buttonSubmit.setFont(font);

                // mainPanel
                mainPanel = new JPanel();
                mainPanel.setLayout(null);
                mainPanel.setSize(width, height);
                mainPanel.setBounds(0, 0, mainPanel.getWidth(), mainPanel.getHeight());

                // Relative between panels
                mainPanel.add(panelTitle);
                mainPanel.add(panelScore1);
                mainPanel.add(panelScore2);
                mainPanel.add(panelScore3);
                mainPanel.add(textFieldScore1);
                mainPanel.add(textFieldScore2);
                mainPanel.add(textFieldScore3);
                mainPanel.add(panelCoefficient1);
                mainPanel.add(panelCoefficient2);
                mainPanel.add(panelCoefficient3);
                mainPanel.add(textFieldCoefficient1);
                mainPanel.add(textFieldCoefficient2);
                mainPanel.add(textFieldCoefficient3);
                mainPanel.add(buttonSubmit);
                buttonSubmit.addMouseListener(new MouseHandler());

                // Properties of this panel
                setLayout(null);
                setSize(width, height);
                setBounds(x, y, getWidth(), getHeight());

                // Add mainPanel to this panel
                add(mainPanel);
        }

        // Set background Color
        public void setBackgroundColorPanelSubject(Color color) {
                mainPanel.setBackground(color);

        }

        // Auto called method of JPanel
        public void paintComponent(Graphics g) {
                super.paintComponent(g);
        }

        // Check valid input
        public boolean isValidInput() {
                if (!textFieldScore1.getText().matches("[0-9]{1,}")
                                && !textFieldScore1.getText().matches("[0-9]{1,}\\.[0-9]{1,}")) {
                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                        DialogMessage.CENTER_CENTER,
                                        "Information", new String[] { "Điểm thứ nhất không hợp lệ." }, Setting.WARNING);
                        return false;
                } else if (Double.parseDouble(textFieldScore1.getText()) > 10) {
                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                        DialogMessage.CENTER_CENTER,
                                        "Information", new String[] { "Điểm thứ nhất đang lớn hơn 10." },
                                        Setting.WARNING);
                        return false;
                } else if (!textFieldScore2.getText().matches("[0-9]{1,}")
                                && !textFieldScore2.getText().matches("[0-9]{1,}\\.[0-9]{1,}")) {
                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                        DialogMessage.CENTER_CENTER,
                                        "Information", new String[] { "Điểm thứ hai không hợp lệ." }, Setting.WARNING);
                        return false;
                } else if (Double.parseDouble(textFieldScore2.getText()) > 10) {
                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                        DialogMessage.CENTER_CENTER,
                                        "Information", new String[] { "Điểm thứ hai đang lớn hơn 10." },
                                        Setting.WARNING);
                        return false;
                } else if (!textFieldScore3.getText().matches("[0-9]{1,}")
                                && !textFieldScore3.getText().matches("[0-9]{1,}\\.[0-9]{1,}")) {
                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                        DialogMessage.CENTER_CENTER,
                                        "Information", new String[] { "Điểm thứ ba không hợp lệ." }, Setting.WARNING);
                        return false;
                } else if (Double.parseDouble(textFieldScore3.getText()) > 10) {
                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                        DialogMessage.CENTER_CENTER,
                                        "Information", new String[] { "Điểm thứ ba đang lớn hơn 10." },
                                        Setting.WARNING);
                        return false;
                } else if (!textFieldCoefficient1.getText().matches("[0-9]{1,}")
                                && !textFieldCoefficient1.getText().matches("[0-9]{1,}\\.[0-9]{1,}")) {
                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                        DialogMessage.CENTER_CENTER,
                                        "Information", new String[] { "Hệ số của điểm thứ nhất không hợp lệ." },
                                        Setting.WARNING);
                        return false;
                } else if (Double.parseDouble(textFieldCoefficient1.getText()) > 100) {
                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                        DialogMessage.CENTER_CENTER,
                                        "Information", new String[] { "Hệ số của điểm thứ nhất đang lớn hơn 100%." },
                                        Setting.WARNING);
                        return false;
                } else if (!textFieldCoefficient2.getText().matches("[0-9]{1,}")
                                && !textFieldCoefficient2.getText().matches("[0-9]{1,}\\.[0-9]{1,}")) {
                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                        DialogMessage.CENTER_CENTER,
                                        "Information", new String[] { "Hệ số của điểm thứ hai không hợp lệ." },
                                        Setting.WARNING);
                        return false;
                } else if (Double.parseDouble(textFieldCoefficient2.getText()) > 100) {
                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                        DialogMessage.CENTER_CENTER,
                                        "Information", new String[] { "Hệ số của điểm thứ hai đang lớn hơn 100%." },
                                        Setting.WARNING);
                        return false;
                } else if (!textFieldCoefficient3.getText().matches("[0-9]{1,}")
                                && !textFieldCoefficient3.getText().matches("[0-9]{1,}\\.[0-9]{1,}")) {
                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                        DialogMessage.CENTER_CENTER,
                                        "Information", new String[] { "Hệ số của điểm thứ ba không hợp lệ." },
                                        Setting.WARNING);
                        return false;
                } else if (Double.parseDouble(textFieldCoefficient3.getText()) > 100) {
                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 3, Setting.HEIGHT / 3,
                                        DialogMessage.CENTER_CENTER,
                                        "Information", new String[] { "Hệ số của điểm thứ ba đang lớn hơn 100%." },
                                        Setting.WARNING);
                        return false;
                } else if (Double.parseDouble(textFieldCoefficient1.getText())
                                + Double.parseDouble(textFieldCoefficient2.getText())
                                + Double.parseDouble(textFieldCoefficient3.getText()) >= 100) {
                        new DialogMessage(Setting.WIDTH / 2, Setting.HEIGHT / 2, Setting.WIDTH / 2, Setting.HEIGHT / 3,
                                        DialogMessage.CENTER_CENTER,
                                        "Information",
                                        new String[] { "Tổng hệ số của điểm thứ nhất, thứ hai và thứ ba đang không hợp lý (>= 100%)." },
                                        Setting.WARNING);
                        return false;
                }
                return true;
        }

        private class MouseHandler implements MouseListener {

                @Override
                public void mouseClicked(MouseEvent event) {
                }

                @Override
                public void mousePressed(MouseEvent event) {
                        if (event.getSource() == buttonSubmit) {
                                if (isValidInput()) {
                                        new DialogCalculateScoreLastTerm4(Setting.WIDTH / 2,
                                                        Setting.HEIGHT / 2, Setting.WIDTH / 5 * 4,
                                                        Setting.HEIGHT / 5 * 4,
                                                        DialogCalculateScoreLastTerm3.CENTER_CENTER, "Result", null,
                                                        Double.parseDouble(textFieldScore1.getText()),
                                                        Double.parseDouble(textFieldCoefficient1.getText()) / 100.0,
                                                        Double.parseDouble(textFieldScore2.getText()),
                                                        Double.parseDouble(textFieldCoefficient2.getText()) / 100.0,
                                                        Double.parseDouble(textFieldScore3.getText()),
                                                        Double.parseDouble(textFieldCoefficient3.getText()) / 100.0,
                                                        conversionTable);
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
