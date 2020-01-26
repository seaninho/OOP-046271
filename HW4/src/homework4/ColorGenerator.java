package homework4;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * ColorGenerator responsible on notifying on color change.
 */
public class ColorGenerator {
    //	 Abstraction function:
    //	 A ColorGenerator is represented by its color and changing method.
    //   It changes the color each 2 seconds.
    //
    //	 Representation Invariant:
    //
    //  The color is valid ( 0 <= r,g,b <= 255)

    private static final int MAX_COLOR = 255;
    static final int DELAY = 40;
    private List<Panel> observers = new ArrayList<>();
    private Color currentColor;
    private observerUpdateMethod updateMethod;


    // Eager Initialization of the instance
    private static ColorGenerator colorGenerator = new ColorGenerator();

    /**
     * ColorGenerator Constructor.
     *
     * @requires
     * @modifies this
     * @effects Instantiates a new color generator.
     */
    protected ColorGenerator() {
        currentColor = new Color(0, 0, 0);
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
        setChanged();
        updateObservers();
        checkRep();
    }

    /**
     * @modifies All the observers
     * @effects Update all the observers of the new color
     */
    private void updateObservers() {
        updateMethod.updateObservers(this, observers);
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
     * @effects Checks if the method is valid according to
     *          Representation Invariant.
     * @return true, if the method is valid. false, otherwise.
     */
    private boolean isStrategyValid() {
        return (updateMethod.getClass() == IncreaseOrder.class ||
                updateMethod.getClass() == DecreaseOrder.class ||
                updateMethod.getClass() == TwoTimesOrder.class ||
                updateMethod.getClass() == RandomOrder.class);
    }

    /**
     * Check representation.
     *
     * @effects Checks the Representation Invariant is kept
     */
    private void checkRep() {
        assert(isColorValid() && isStrategyValid());
    }


}
