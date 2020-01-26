package homework4;

import java.util.*;

/**
 * observerUpdateMethod is an class designed to notify the observers about updates.
 */
public class observerUpdateOrder extends Observable {

    //	 Abstraction Function:
    //	 DELAY defines the gap between the update of two observers.
    //	 The observers Vector contains all the observers that are subscribed.
    //	 The vector initialized an empty Vector (no observers at the beginning)
    //	 Order is an instance of PanelOrderNotifier class that determines the
    //	 update order of this observable class.
    //	 The default initialization is to InOrder class

    //	 Representation Invariant:
    //	 1. order != null
    //   2. order (the color update order) is one of four methods:
    //	    1) Ascending order - PanelAscOrderNotifier
    //      2) Change by columns - PanelColsNotifier
    //      3) Update in two steps - PanelOddEvenNotifier
    //      4) Update randomly - PanelRandOrderNotifier


    static final int DELAY = 40; // 40 milliseconds
    private PanelOrderNotifier panelOrderNotifier;
    private Vector<Observer> observers;

    /**
     * observerUpdateOrder constructor.
     *
     * @requires order != null
     * @modifies this
     * @effects Instantiates a new update order.
     *
     */
    public observerUpdateOrder() {
        checkRep();
        panelOrderNotifier = new PanelAscOrderNotifier();
        observers = new Vector<>();
        checkRep();
    }

    /**
     * Adds an observer to the set of observers for this object, provided
     * that it is not the same as some observer already in the set.
     * The order in which notifications will be delivered to multiple
     * observers is not specified. See the class comment.
     *
     * @param   observer   an observer to be added.
     * @throws NullPointerException   if the parameter o is null.
     */
    public synchronized void addObserver(Observer observer) {
        if (observer == null)
            throw new NullPointerException();
        if (!observers.contains(observer)) {
            observers.addElement(observer);
        }
    }

    /**
     * Deletes an observer from the set of observers of this object.
     * Passing {@code null} to this method will have no effect.
     * @param   observer   the observer to be deleted.
     */
    public synchronized void deleteObserver(Observer observer) {
        observers.removeElement(observer);
    }

    /**
     * @modifies: this
     * @effects: sets notification order policy to the one dictated by bO instance
     */
    public void setPanelOrderNotifier(PanelOrderNotifier newOrder) {
        panelOrderNotifier = newOrder;
        checkRep();
    }

    /**
     * If this object has changed, as indicated by the method,
     * then notify all of its observers
     *
     * @modifies: this
     * @effects: if there is a change - notifies the observers according to the
     * update order policy and resets the change flag afterwards
     */
    @Override
    public void notifyObservers() {
        observerUpdateOrder copyOfThis = this;
        if (hasChanged()) {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                int counter = 0;
                public void run() {
                    (observers.get(panelOrderNotifier.getNextPanel())).update(copyOfThis,
                            null);
                    counter++;
                    if (counter == observers.size()) {
                        this.cancel();
                    }
                }
            }, DELAY, DELAY);
            clearChanged();
        }
    }

    /**
     * @effects Checks if the method is valid according to
     *          Representation Invariant.
     * @return true, if the method is valid. false, otherwise.
     */
    private boolean isStrategyValid() {
        return (panelOrderNotifier.getClass() == PanelAscOrderNotifier.class ||
                panelOrderNotifier.getClass() == PanelColsNotifier.class ||
                panelOrderNotifier.getClass() == PanelOddEvenNotifier.class ||
                panelOrderNotifier.getClass() == PanelRandOrderNotifier.class);
    }

    /**
     * Check representation.
     *
     * @effects Checks the Representation Invariant is kept
     */
    private void checkRep() {
        assert((panelOrderNotifier != null) && isStrategyValid());
    }

}



