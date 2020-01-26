package homework4;

import javax.swing.*;

/**
 * Billboard represents an billboard of size (WINDOW_HEIGHT x WINDOW_WIDTH).
 * Billboard class is a JFrame and it contain a grid of
 * (NUM_OF_HEIGHT_PANELS x NUM_OF_WIDTH_PANELS) panels instances.
 * The panels color is set by colorGenerator
 */
public class Billboard extends JFrame {
    //	 Abstraction function:
    //	 A billboard is represented a set of BOARDSIZE panels, arranged in a
    //	 (NUM_OF_HEIGHT_PANELS X NUM_OF_WIDTH_PANELS) grid.
    //
    //	 Representation Invariant:
    //	 there are 25 panels.


    private static final int MAX_COLOR = 255;
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 600;
    private static final int NUM_OF_HEIGHT_PANELS = 5;
    private static final int NUM_OF_WIDTH_PANELS = 5;
    private final int BOARDSIZE = NUM_OF_HEIGHT_PANELS*NUM_OF_WIDTH_PANELS

    JPanel billboard = new JPanel();
    Panel panels[] = new Panel[BOARDSIZE];
    ColorGenerator colorGenerator = ColorGenerator.getInstance();


}
