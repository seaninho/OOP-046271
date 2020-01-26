package homework4;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * ColorGenerator responsible on notifying on color change.
 */
public class ColorGenerator extends observerUpdateOrder {
    //	 Abstraction function:
    //	 A ColorGenerator is a singleton represented by its color (currentColor).
    //	 The color initialized to white.
    //   It changes the color each 2 seconds.
    //
    //	 Representation Invariant:
    //   The color is valid ( 0 <= r,g,b <= 255)

    private static final int MAX_COLOR = 255;
    static final int DELAY = 40;
    private Color currentColor;

    // Eager Initialization of the instance
    private static ColorGenerator colorGenerator = new ColorGenerator();

    /**
     * ColorGenerator Constructor.
     *
     * @modifies this
     * @effects Instantiates a new color generator.
     */
    protected ColorGenerator() {
        currentColor = Color.PINK;
        checkRep();
    }

    /**
     * Gets the instance.
     *
     * @effects Gets the single instance of ColorGenerator.
     * @return the instance
     */
    public static ColorGenerator getInstance() {
        return colorGenerator;
    }

    /**
     * @return the currentColor;
     * @effect Returns the current color
     */
    public Color getColor() {
        return currentColor;
    }

    /**
     * @effects Change color update method.
     * @modifies this, the updating method
     * @param newMethod the new update method
     */
    public void changeUpdateMethod (observerUpdateMethod newMethod) {
        checkRep();
        this.updateMethod = newMethod;
        checkRep();
    }

    /**
     * @requires legal panel
     * @effects Adds a new panel.
     * @modifies this
     * @param panel the new panel
     */
    public void addPanel(Panel panel) {
        checkRep();
        observers.add(panel);
        checkRep();
    }

    /**
     * @effects Changes the current color
     */
    public void setColor() {
        checkRep();
        Random randomGenerator = new Random();
        int red = randomGenerator.nextInt(MAX_COLOR);
        int green = randomGenerator.nextInt(MAX_COLOR);
        int blue = randomGenerator.nextInt(MAX_COLOR);
        currentColor = new Color(red, green, blue);
        checkRep();
        setChanged();
        notifyObservers();
    }

    /**
     * @effects Checks if the color is valid according to Representation Invariant.
     * @return true, if the color valid. false otherwise.
     */
    private boolean isColorValid() {
        return ((currentColor.getBlue() >= 0 && currentColor.getBlue() <= 255)
             && (currentColor.getGreen() >= 0 && currentColor.getGreen() <= 255)
             && (currentColor.getRed() >= 0 && currentColor.getRed() <= 255));
    }

    /**
     * Check representation.
     *
     * @effects Checks the Representation Invariant is kept
     */
    private void checkRep() {
        assert(isColorValid());
    }


}
