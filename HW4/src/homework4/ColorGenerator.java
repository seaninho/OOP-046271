package homework4;

import java.awt.*;

/**
 * ColorGenerator responsible on notifying on color change.
 */
public class ColorGenerator {
    //	 Abstraction function:
    //	 A ColorGenerator is represented by its color and changing method.
    //   It changes the color each 2 seconds.
    //
    //	 Representation Invariant:
    //	 The color update method is one of four methods:
    //	1) Ascending order
    //  2) Change by columns
    //  3) Update in two steps
    //  4) Update randomly
    //  The color is valid ( 0 <= r,g,b <= 255)

    private static final int MAX_COLOR = 255;
    private Color currentColor;
}
