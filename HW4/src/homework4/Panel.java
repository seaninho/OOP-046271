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
    //	 1 <= panel number <= 25 .

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
