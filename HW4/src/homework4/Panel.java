package homework4;

import java.awt.*;

/**
 * Panel represents one electronic panel.
 * A typical Panel has location, color, shape and size.
 * A panel can be updated by an observable color generator
 */
public class Panel {
    //	 Abstraction function:
    //	 A panel is represented by its location and its has color.
    //
    //	 Representation Invariant:
    //	 location != null && color != null
    //	 1 <= location.x <=5
    //   1 <= location.y <=5

    private static final int size = 1;
    private Point location;
    private Color color;


    /**
     * Panel constructor.
     *
     * @requires location != null && color != null
     * @modifies this
     * @effects Instantiates a new panel.
     *
     */
    public Panel(Point location, Color color) {
        this.color = color;
        this.location = location;
        checkRep();
    }

    /**
     * Set panel color.
     *
     * @modifies this
     * @effects change the panel color to a new color.
     */
    public void setPanelColor (Color color) {
        this.color = color;
        checkRep();
    }

    /**
     * Check representation.
     *
     * @effects Checks the Representation Invariant is kept
     */
    private void checkRep() {
        assert (this.color != null) :
                "Panel color cannot be null";
        assert (this.location != null) :
                "Panel location cannot be null";
    }
}
