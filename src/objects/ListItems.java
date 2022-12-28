package src.objects;

import javax.swing.JPanel;

import src.Setting;

import java.awt.Graphics;
// import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
// import java.awt.GradientPaint;
// import java.awt.Canvas;
// import java.awt.FontMetrics;
// import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseAdapter;

public class ListItems extends JPanel {
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
    private JPanel panelScroll = null; // contains content
    private int scrollCursor = 0;
    private JPanel panelBarScroll = null;
    private JPanel panelBarScrollCursor = null;
    private int tempYOfBarScrollCursor;
    private String[] itemTexts = null;
    private Button[] itemPanels = null;
    private Color[] itemBackgroundColors = null;
    private Color[] itemTextColors = null;
    private Font itemTextFont = null;
    private Color backgroundColorBarScroll = null;
    private Color backgroundColorBarScrollCursor = null;
    private int indexPanelEntering = -1;
    private Color itemEnteredBackgroundColor = null;
    private Color itemExitedBackgroundColor = null;
    private Color itemSelectedBackgroundColor = null;

    // Constructor
    public ListItems(int x, int y, int width, int height, int rootLocationType, String[] texts) {
        // Basic properties
        this.width = width;
        this.height = height;
        setLayout(null);
        setSize(width, height);
        // Create panelScroll
        this.panelScroll = new JPanel();
        panelScroll.setLayout(null);
        // Create panelBarScroll and panelBarScrollCursor
        this.panelBarScroll = new JPanel();
        panelBarScroll.setLayout(null);
        this.panelBarScrollCursor = new JPanel();
        panelBarScrollCursor.setLayout(null);
        panelBarScroll.add(panelBarScrollCursor);
        // Set default item background colors
        setItemBackgroundColors(new Color[] { Setting.COLOR_WHITE });
        // Set location of this panel
        setLocationListItems(x, y, rootLocationType);
        // Set default item text font
        setItemTextFont(Button.ARIAL_BOLD_18);
        // Set default item text color
        setItemTextColors(new Color[] { Setting.COLOR_BLACK });
        // Set default item background colors when entered
        setItemEnteredBackgroundColor(Setting.COLOR_VIOLET_03);
        // Set default item background colors when exited
        setItemExitedBackgroundColor(Setting.COLOR_WHITE);
        // Set default item background colors when exited
        setItemSelectedBackgroundColor(Setting.COLOR_BLUE_03);
        // Set items
        setItems(texts);
        // Set default background color of bar scroll
        setBackgroundColorBarScroll(Setting.COLOR_GRAY_03);
        // Set default background color of bar scroll cursor
        setBackgroundColorBarScrollCursor(Setting.COLOR_GRAY_01);
        // Update list items panel
        updateDataListItemsPanel();
    }

    // Update panel
    public void updateDataListItemsPanel() {
        removeAll();
        updatePanelScroll();
        updatePanelBarScroll();
        add(this.panelScroll);
        add(this.panelBarScroll);
        this.panelScroll.addMouseWheelListener(new MouseWheelHandler());
        this.panelBarScroll.addMouseWheelListener(new MouseWheelHandler());
        this.panelBarScrollCursor.addMouseWheelListener(new MouseWheelHandler());
        this.panelBarScrollCursor.addMouseListener(new MouseAdapterHandler());
        this.panelBarScrollCursor.addMouseMotionListener(new MouseAdapterHandler());
    }

    // Update content showing
    public void updateContentShowing() {
        panelScroll.setBounds(0, -scrollCursor, panelScroll.getWidth(), panelScroll.getHeight());
        panelBarScrollCursor.setBounds(0,
                (int) (scrollCursor * height * 1.0 / panelScroll.getHeight()),
                panelBarScrollCursor.getWidth(),
                panelBarScrollCursor.getHeight());
    }

    // Create panelScroll contents
    public void updatePanelScroll() {
        // Create basic properties for panel
        panelScroll.removeAll();
        // Create item panels
        int heightScroll = 0;
        this.itemPanels = new Button[this.itemTexts.length];
        for (int i = 0; i < itemPanels.length; i++) {
            itemPanels[i] = new Button(itemTexts[i]);
            itemPanels[i].setFontText(this.itemTextFont);
            itemPanels[i].setCorrectSizeButton();
            panelScroll.setSize(width - itemPanels[i].getHeight(), 0);
            itemPanels[i].setSizeButton(panelScroll.getWidth(), itemPanels[i].getHeight());
            itemPanels[i].setTextColor(this.itemTextColors[i % itemTextColors.length]);
            itemPanels[i].setLocationButton(0, heightScroll, Button.TOP_LEFT);
            itemPanels[i].setStrokeWidth(0);
            itemPanels[i].setBackgroundColorEnteredButton(this.itemEnteredBackgroundColor);
            itemPanels[i].setBackgroundColorExitedButton(this.itemExitedBackgroundColor);
            itemPanels[i].setBackgroundColorButton(this.itemExitedBackgroundColor);
            itemPanels[i].addMouseListener(new MouseHandler());
            panelScroll.add(itemPanels[i]);
            heightScroll += itemPanels[i].getHeight();
        }
        // Set size and location of panelScroll
        panelScroll.setSize(panelScroll.getWidth(), heightScroll);
        // Set size of listItems
        setSizeListItems(getWidth(), Math.min(getHeight(), panelScroll.getHeight()));
        // Set new scrollCursor
        scrollCursor = Math.min(scrollCursor, getMaxScrollCursor());
        panelScroll.setBounds(0, -scrollCursor, panelScroll.getWidth(), panelScroll.getHeight());
    }

    // Update panelScrollBar
    public void updatePanelBarScroll() {
        panelBarScroll.setSize(width - this.panelScroll.getWidth(), height);
        if (this.backgroundColorBarScroll != null) {
            panelBarScroll.setBackground(this.backgroundColorBarScroll);
        }
        panelBarScroll.setBounds(this.panelScroll.getWidth(), 0, panelBarScroll.getWidth(), panelBarScroll.getHeight());

        panelBarScrollCursor.setSize(panelBarScroll.getWidth(),
                (int) (height * height * 1.0 / panelScroll.getHeight()));
        if (this.backgroundColorBarScrollCursor != null) {
            panelBarScrollCursor.setBackground(this.backgroundColorBarScrollCursor);
        }
        panelBarScrollCursor.setBounds(0, (int) (scrollCursor * height * 1.0 / panelScroll.getHeight()),
                panelBarScrollCursor.getWidth(),
                panelBarScrollCursor.getHeight());
    }

    // Get itemsPanel
    public Button[] getItemPanels() {
        return this.itemPanels;
    }

    // Get index panel entering
    public int getIndexPanelEntering() {
        return this.indexPanelEntering;
    }

    // Get max of scrollCursor
    public int getMaxScrollCursor() {
        return Math.max(0, panelScroll.getHeight() - this.height);
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

    // set background of panel when item entered
    public void setItemEnteredBackgroundColor(Color color) {
        this.itemEnteredBackgroundColor = color;
    }

    // set background of panel when item exited
    public void setItemExitedBackgroundColor(Color color) {
        this.itemExitedBackgroundColor = color;
    }

    // set background of panel when item exited
    public void setItemSelectedBackgroundColor(Color color) {
        this.itemSelectedBackgroundColor = color;
    }

    // Set color of BarScroll
    public void setBackgroundColorBarScroll(Color color) {
        this.backgroundColorBarScroll = color;
        updatePanelBarScroll();
    }

    // Set index panel entering
    public void setIndexPanelEntering(int index) {
        this.indexPanelEntering = index;
    }

    // Set color of BarScroll
    public void setBackgroundColorBarScrollCursor(Color color) {
        this.backgroundColorBarScrollCursor = color;
        updatePanelBarScroll();
    }

    // Set size of list items
    public void setSizeListItems(int w, int h) {
        this.width = w;
        this.height = h;
    }

    // Set items
    public void setItems(String[] items) {
        this.itemTexts = items;
        updateDataListItemsPanel();
    }

    // Set item background color
    public void setItemBackgroundColors(Color[] colors) {
        this.itemBackgroundColors = colors;
        repaint();
    }

    // Set item background color
    public void setItemTextColors(Color[] colors) {
        this.itemTextColors = colors;
        repaint();
    }

    // Set item texts font
    public void setItemTextFont(Font font) {
        this.itemTextFont = font;
        repaint();
    }

    // Set location and root location type
    public void setLocationListItems(int x, int y, int rootLocationType) {
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

    // Auto called methos of JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private class MouseWheelHandler implements MouseWheelListener {
        public void mouseWheelMoved(MouseWheelEvent event) {
            for (int count = 0; count < 20; count++) {
                setScrollCursor(scrollCursor + event.getWheelRotation());
            }
            updateContentShowing();
        }

    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            for (int i = 0; i < itemPanels.length; i++) {
                if (e.getSource() == itemPanels[i]) {
                    setIndexPanelEntering(i);
                }
            }
            for (int i = 0; i < itemPanels.length; i++) {
                if (i == indexPanelEntering) {
                    itemPanels[i].setBackgroundColorEnteredButton(itemSelectedBackgroundColor);
                    itemPanels[i].setBackgroundColorExitedButton(itemSelectedBackgroundColor);
                    itemPanels[i].setBackgroundColorButton(itemSelectedBackgroundColor);
                } else {
                    itemPanels[i].setBackgroundColorEnteredButton(itemEnteredBackgroundColor);
                    itemPanels[i].setBackgroundColorExitedButton(itemBackgroundColors[i % itemBackgroundColors.length]);
                    itemPanels[i].setBackgroundColorButton(itemBackgroundColors[i % itemBackgroundColors.length]);
                }
                itemPanels[i].repaint();
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

    private class MouseAdapterHandler extends MouseAdapter {
        public void mouseDragged(MouseEvent e) {
            int modifiedYScrollCursor = (int) ((e.getY() - tempYOfBarScrollCursor) * 1.0
                    * panelScroll.getHeight()
                    / height);
            setScrollCursor(scrollCursor + modifiedYScrollCursor);
            updateContentShowing();
        }

        public void mousePressed(MouseEvent e) {
            tempYOfBarScrollCursor = e.getY();
        }

        public void mouseReleased(MouseEvent e) {
        }
    }
}
