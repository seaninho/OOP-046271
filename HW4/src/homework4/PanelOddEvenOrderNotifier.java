package homework4;

/**
 * PanelOddEvenOrderNotifier represents a panel order notifier which dictates the
 * billboard's panels will change color in odd-first, even-second order.
 */
public class PanelOddEvenOrderNotifier implements PanelOrderNotifier {
    //  Abstraction function:
    //	A PanelOddEvenNotifier dictates the billboard's panels to changes
    //	their colors in a specific order, first odd numbered panels, then the
    //  even numbered ones.
    //
    //	Representation Invariant:
    //  0 <= nextPanel < 25

    private final int BOARDSIZE = 25;
    private int nextPanel;

    /**
     * PanelOddEvenOrderNotifier constructor.
     *
     * @modifies this
     * @effects Instantiates a new panels in odd-even order notifier.
     *
     */
    public PanelOddEvenOrderNotifier() {
        nextPanel = 0;
        checkRep();
    }

    /**
     * Next panel to change its color.
     *
     * @modifies this
     * @effects Determines the next panel to change its color, depending on a
     * preset logic.
     *
     */
    @Override
    public int getNextPanel() {
        int currentPanel = nextPanel;
        nextPanel = (nextPanel + 2) % BOARDSIZE;
        checkRep();
        return currentPanel;
    }

    /**
     * Verify next Panel to be updated has a valid index.
     *
     * @effects This method will fail if next panel index is out of range.
     */
    private void checkRep() {
        assert (nextPanel >= 0 && nextPanel < BOARDSIZE) :
                "Panel index is out of range!";
    }
}
