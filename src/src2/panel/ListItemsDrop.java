package src2.panel;

import src2.Setting;
import src2.panel.Button;
import src2.panel.ListItems;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListItemsDrop extends JPanel {
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

    // Properties
    private int width, height;
    private int xPos, yPos, rootLocationType;
    private JPanel panelHeader = null;
    private src2.panel.Button buttonArrow = null;
    private src2.panel.Button panelShowingText = null;
    private ListItems listItems = null;

    // Constructor
    public ListItemsDrop(int x, int y, int widthListItems, int heightListItems, int rootLocationType, String[] texts) {
        // basic properties
        setLayout(null);
        // Create panel list items
        listItems = new ListItems(0, 0, widthListItems, heightListItems, ListItems.TOP_LEFT, texts);
        // Cretate panel header
        panelHeader = new JPanel();
        panelHeader.setLayout(null);
        panelHeader.setSize(listItems.getWidth(), listItems.getItemPanels()[0].getHeight());
        panelHeader.setBounds(0, 0, panelHeader.getWidth(), panelHeader.getHeight());

        // Create panelShowingText
        panelShowingText = new src2.panel.Button(texts[0]);
        panelShowingText.setFontText(listItems.getItemPanels()[0].getFontText());
        panelShowingText.setCorrectSizeButton();
        panelShowingText.setSizeButton(panelHeader.getWidth() - panelHeader.getHeight(), panelHeader.getHeight());
        panelShowingText.setLocationButton(0, 0, src2.panel.Button.TOP_LEFT);
        panelShowingText.setGradientBackgroundColor(Setting.GRADIENT_POINTS1_10, Setting.GRADIENT_POINTS2_10,
                Setting.GRADIENT_COLORS_10);
        panelShowingText.setEnable(false);

        // Create buttonArrow
        buttonArrow = new src2.panel.Button("");
        buttonArrow.setSizeButton(panelHeader.getHeight(), panelHeader.getHeight());
        buttonArrow.setBackgroundIcon(Setting.ARROW_DOWN);
        buttonArrow.setLocationButton(panelShowingText.getWidth(), panelShowingText.getY(), src2.panel.Button.TOP_LEFT);
        buttonArrow.addMouseListener(new MouseHandler());

        // Set location of listitems
        listItems.setLocationListItems(0, panelHeader.getHeight(), ListItems.TOP_LEFT);

        // Add button to headerpanel
        add(panelHeader);
        add(listItems);
        panelHeader.add(panelShowingText);
        panelHeader.add(buttonArrow);

        // Set size and location panel
        setSizeListItemsDrop(panelHeader.getWidth(), panelHeader.getHeight());
        setLocationListItemsDrop(x, y, rootLocationType);

        // Add mouse listener for item panel of listItems
        for (int i = 0; i < listItems.getItemPanels().length; i++) {
            listItems.getItemPanels()[i].addMouseListener(new MouseHandler());
        }

    }

    // Set size of this panel
    public void setSizeListItemsDrop(int w, int h) {
        this.width = w;
        this.height = h;
        setSize(w, h);
    }

    // get list items
    public ListItems getListItems() {
        return this.listItems;
    }

    // get buttonArrow
    public Button getButtonArrow() {
        return this.buttonArrow;
    }

    // get panelHeader
    public JPanel getPanelHeader() {
        return this.panelHeader;
    }

    // Get text
    public String getText() {
        return panelShowingText.getTextButton();
    }

    // Set location and root location type
    public void setLocationListItemsDrop(int x, int y, int rootLocationType) {
        this.rootLocationType = rootLocationType;
        switch (this.rootLocationType) {
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
        repaint();
    }

    // Auto called method of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource() == buttonArrow) {
                if (height == panelHeader.getHeight()) {
                    listItems.setVisible(true);
                    setSizeListItemsDrop(panelHeader.getWidth(), panelHeader.getHeight() + listItems.getHeight());
                    buttonArrow.setBackgroundIcon(Setting.ARROW_UP);
                } else {
                    listItems.setVisible(false);
                    setSizeListItemsDrop(panelHeader.getWidth(), panelHeader.getHeight());
                    buttonArrow.setBackgroundIcon(Setting.ARROW_DOWN);
                }
            } else {
                for (int i = 0; i < listItems.getItemPanels().length; i++) {
                    if (e.getSource() == listItems.getItemPanels()[i]) {
                        panelShowingText.setTextButton(listItems.getItemPanels()[i].getTextButton());
                    }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
