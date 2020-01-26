package homework4;

import java.awt.*;
import java.util.Random;

/**
 * ColorGenerator responsible on notifying on color change.
 */
public class ColorGenerator extends observerUpdateOrder {
    //	 Abstraction function:
    //	 A ColorGenerator is a singleton represented by its color (currentColor).
    //	 The color initialized to Pink.
    //
    //	 Representation Invariant:
    //   The color is valid ( 0 <= r,g,b <= 255)

    private static final int MIN_COLOR = 0;
    private static final int MAX_COLOR = 255;
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
     * @return the currentColor;
     * @effect Returns the current color
     */
    public Color getColor() {
        return currentColor;
    }


    /**
     * @effects Checks if the color is valid according to Representation Invariant.
     * @return true, if the color valid. false otherwise.
     */
    private boolean isColorValid() {
        return ((currentColor.getBlue() >= MIN_COLOR &&
                currentColor.getBlue() <= MAX_COLOR)
             && (currentColor.getGreen() >= MIN_COLOR &&
                currentColor.getGreen() <= MAX_COLOR)
             && (currentColor.getRed() >= MIN_COLOR &&
                currentColor.getRed() <= MAX_COLOR));
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
