package homework4;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Panel represents one electronic panel.
 * A typical Panel has location, color, shape and size.
 * A panel can be updated by an observable color generator.
 */
public class Panel extends JPanel implements Observer {
    //  Abstraction function:
    //	A Panel is a JPanel that implements Observer.
    //	A Panel observes the ColorGenerator and changes its background color
    //	accordingly.
    //
    //	Representation Invariant:
    //  A valid JPanel.

    /**
     * Panel constructor.
     *
     * @modifies this
     * @effects Instantiates a new panel.
     *
     */
    public Panel() {
        setBackground(Color.WHITE);
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an {@code Observable} object's
     * {@code notifyObservers} method to have all the object's
     * observers notified of the change.
     *
     * @param   o     the observable object.
     * @param   arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        ColorGenerator colorGenerator = (ColorGenerator)o;
        setBackground(colorGenerator.getColor());
        repaint();
    }

}
