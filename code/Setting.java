package code;

import java.awt.GraphicsEnvironment;

import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;

public class Setting {
        public static int WIDTH = (int) (GraphicsEnvironment.getLocalGraphicsEnvironment()
                        .getMaximumWindowBounds().getWidth());
        public static int HEIGHT = (int) ((GraphicsEnvironment.getLocalGraphicsEnvironment()
                        .getMaximumWindowBounds().getHeight()));
        public static String FONT_NAME_01 = "Arial";
        public static int FONT_STYLE_00 = Font.PLAIN;
        public static int FONT_STYLE_01 = Font.BOLD;
        public static int FONT_STYLE_02 = Font.ITALIC;
        public static int FONT_STYLE_03 = Font.BOLD + Font.ITALIC;
        public static int FONT_SIZE_BIG = 36;
        public static int FONT_SIZE_MEDIUM = 24;
        public static int FONT_SIZE_SMALL_2 = 21;
        public static int FONT_SIZE_SMALL = 18;
        public static int FONT_SIZE_VERY_SMALL = 13;
        public static Color COLOR_NULL = new Color(0, 0, 0, 0);
        public static Color COLOR_BLACK = new Color(0, 0, 0);
        public static Color COLOR_GREEN_01 = new Color(133, 186, 149);
        public static Color COLOR_GREEN_02 = new Color(153, 214, 171);
        public static Color COLOR_GREEN_03 = new Color(231, 255, 229);
        public static Color COLOR_ORANGE_01 = new Color(255, 209, 165);
        public static Color COLOR_ORANGE_02 = new Color(255, 231, 194);
        public static Color COLOR_GRAY_01 = new Color(70, 70, 70);
        public static Color COLOR_GRAY_02 = new Color(111, 111, 111);
        public static Color COLOR_GRAY_03 = new Color(200, 200, 200);
        public static Color COLOR_GRAY_04 = new Color(220, 220, 220);
        public static Color COLOR_GRAY_05 = new Color(232, 232, 232);
        public static Color COLOR_GRAY_06 = new Color(242, 242, 242);
        public static Color COLOR_WHITE = new Color(255, 255, 255);
        public static Color COLOR_RED_01 = new Color(50, 0, 0);
        public static Color COLOR_RED_02 = new Color(130, 0, 0);
        public static Color COLOR_RED_03 = new Color(150, 0, 0);
        public static Color COLOR_RED_04 = new Color(255, 240, 240);
        public static Color COLOR_RED_05 = new Color(235, 188, 188);
        public static Color COLOR_RED_06 = new Color(235, 201, 201);
        public static Color COLOR_RED_07 = new Color(255, 218, 218);
        public static Color COLOR_VIOLET_01 = new Color(230, 228, 255);
        public static Color COLOR_VIOLET_02 = new Color(211, 202, 255);
        public static Color COLOR_VIOLET_03 = new Color(240, 237, 255);
        public static Color COLOR_BLUE_01 = new Color(241, 250, 255);
        public static Color COLOR_BLUE_02 = new Color(222, 255, 253);
        public static Color COLOR_BLUE_03 = new Color(189, 239, 255);
        public static Color COLOR_BLUE_04 = new Color(168, 200, 200);
        // Gradien color combo
        // Combo 1 // No color
        public static final Color[][] GRADIENT_COLORS_1 = {
                        new Color[] { COLOR_WHITE, COLOR_WHITE }
        };
        public static final double[][] GRADIENT_POINTS1_1 = {
                        new double[] { 0, 0 }
        };
        public static final double[][] GRADIENT_POINTS2_1 = {
                        new double[] { 0, 1 }
        };
        // Combo 2 // Deafault button
        public static final Color[][] GRADIENT_COLORS_2 = {
                        new Color[] { COLOR_BLUE_03, COLOR_GRAY_06 },
                        new Color[] { COLOR_GRAY_06, COLOR_BLUE_03 }

        };
        public static final double[][] GRADIENT_POINTS1_2 = {
                        new double[] { 1, 0 },
                        new double[] { 0, 0 }
        };
        public static final double[][] GRADIENT_POINTS2_2 = {
                        new double[] { 0, 1 },
                        new double[] { 2, 0 }
        };
        // Combo 3 // Use for enable button (information bar)
        public static final Color[][] GRADIENT_COLORS_3 = {
                        new Color[] { COLOR_GRAY_03, COLOR_WHITE }
        };
        public static final double[][] GRADIENT_POINTS1_3 = {
                        new double[] { 0, 0 }
        };
        public static final double[][] GRADIENT_POINTS2_3 = {
                        new double[] { 0, 1 }
        };
        // Combo 4 // use for title bar of table (use button)
        public static final Color[][] GRADIENT_COLORS_4 = {
                        new Color[] { COLOR_RED_06, COLOR_GRAY_06 },
                        new Color[] { COLOR_GRAY_06, COLOR_RED_06 }
        };
        public static final double[][] GRADIENT_POINTS1_4 = {
                        new double[] { 0, 0 },
                        new double[] { 0, 0 }
        };
        public static final double[][] GRADIENT_POINTS2_4 = {
                        new double[] { 0, 1 },
                        new double[] { 0, 1 }
        };
        // Combo 5 // Use for button
        public static final Color[][] GRADIENT_COLORS_5 = {
                        new Color[] { COLOR_GRAY_03, COLOR_GRAY_06 },
                        new Color[] { COLOR_GRAY_06, COLOR_GRAY_04 }
        };
        public static final double[][] GRADIENT_POINTS1_5 = {
                        new double[] { 0, 0 },
                        new double[] { 0, 0 }
        };
        public static final double[][] GRADIENT_POINTS2_5 = {
                        new double[] { 0, 1 },
                        new double[] { 0, 1 }
        };
        // Combo 6 // Use for panelString
        public static final Color[][] GRADIENT_COLORS_6 = {
                        new Color[] { COLOR_BLUE_02, COLOR_RED_04 },
                        new Color[] { COLOR_RED_04, COLOR_BLUE_02 }
        };
        public static final double[][] GRADIENT_POINTS1_6 = {
                        new double[] { 0, 0 },
                        new double[] { 0, 0 }
        };
        public static final double[][] GRADIENT_POINTS2_6 = {
                        new double[] { 1, 0 },
                        new double[] { 1, 0 }
        };
        // Combo 7 // Use for panel
        public static final Color[][] GRADIENT_COLORS_7 = {
                        new Color[] { COLOR_GRAY_04, COLOR_GRAY_02 }
        };
        public static final double[][] GRADIENT_POINTS1_7 = {
                        new double[] { 0, 0 }
        };
        public static final double[][] GRADIENT_POINTS2_7 = {
                        new double[] { 1, 0 }
        };
        // Combo 8 // Use for panel
        public static final Color[][] GRADIENT_COLORS_8 = {
                        new Color[] { COLOR_GRAY_04, COLOR_GREEN_03 }
        };
        public static final double[][] GRADIENT_POINTS1_8 = {
                        new double[] { 1, 0 }
        };
        public static final double[][] GRADIENT_POINTS2_8 = {
                        new double[] { 0, 0 }
        };
        // Combo 9 // Deafault button when entered
        public static final Color[][] GRADIENT_COLORS_9 = {
                        new Color[] { COLOR_BLUE_02, COLOR_GRAY_06 },
                        new Color[] { COLOR_GRAY_06, COLOR_BLUE_02 }

        };
        public static final double[][] GRADIENT_POINTS1_9 = {
                        new double[] { 1, 0 },
                        new double[] { 0, 0 }
        };
        public static final double[][] GRADIENT_POINTS2_9 = {
                        new double[] { 0, 1 },
                        new double[] { 2, 0 }
        };
        // Combo 10 // Use for panel
        public static final Color[][] GRADIENT_COLORS_10 = {
                        new Color[] { COLOR_WHITE, COLOR_GRAY_06 },
                        new Color[] { COLOR_GRAY_06, COLOR_GREEN_03 }
        };
        public static final double[][] GRADIENT_POINTS1_10 = {
                        new double[] { 0, 0 },
                        new double[] { 0, 0 }
        };
        public static final double[][] GRADIENT_POINTS2_10 = {
                        new double[] { 0, 1 },
                        new double[] { 0, 1 }
        };

        // ImageIcon
        public static final ImageIcon LOGO = new ImageIcon("assets/icon/logo.png");
        public static final ImageIcon ARROW_DOWN = new ImageIcon("assets/icon/arrow_down.png");
        public static final ImageIcon ARROW_UP = new ImageIcon("assets/icon/arrow_up.png");
        public static final ImageIcon SETTING = new ImageIcon("assets/icon/setting.png");
        public static final ImageIcon EDIT = new ImageIcon("assets/icon/edit.png");
        public static final ImageIcon REMOVE = new ImageIcon("assets/icon/remove.png");
        public static final ImageIcon COPY = new ImageIcon("assets/icon/copy.png");
        public static final ImageIcon CHANGE = new ImageIcon("assets/icon/change.png");
        public static final ImageIcon INFORMATION = new ImageIcon("assets/icon/information.png");
        public static final ImageIcon WARNING = new ImageIcon("assets/icon/warning.png");

}
