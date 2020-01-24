package homework4;

import javafx.beans.Observable;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observer;
import javax.swing.Timer;

/**
 * observerUpdateMethod is an interface designed to be implemented by
 * observable class.
 * The interface enables the observable class to have multiple methods for
 * updating all of its observers
 */
public interface observerUpdateMethod {
    public <E extends Observable,T extends Observer> void observerUpdateMethod(E observable, List<T> observers);
    static final int PERIOD = 30;
    static final int DELAY = 40;
}

/**
 * IncreaseOrder implements observerUpdateMethod.
 * The Increase Order method is updating the observers in an
 * increasing order (1,2, 3, ...), with a period of 30msec.
 */
class IncreaseOrder implements observerUpdateMethod {

    @Override
    public <E extends Observable,T extends Observer> void updateObservers(E colorGenerator, List<T> observers) {
        int delay = DELAY;
        int period = PERIOD;
        // enable animation timer (ticks 25 times per second)
        Timer timer = new Timer(delay, new ActionListener() {

        });
        timer.start();
    }
}
