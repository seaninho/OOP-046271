package homework4;

/**
 * PanelAscOrderNotifier represents a panel order notifier which dictates the
 * billboard's panels will change color in ascending order.
 */
public class PanelAscOrderNotifier implements PanelOrderNotifier {
    //  Abstraction function:
    //	A PanelAscOrderNotifier dictates the billboard's panels to changes their
    //	colors in ascending order.
    //
    //	Representation Invariant:
    //  1 <= nextPanel <= 25

    private final int BOARDSIZE = 25;
    private int nextPanel;

    /**
     * PanelAscOrderNotifier constructor.
     *
     * @modifies this
     * @effects Instantiates a new panels in ascending order notifier.
     *
     */
    public PanelAscOrderNotifier() {
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
        int currentPanel = nextPanel++;
        nextPanel = nextPanel % BOARDSIZE;
        checkRep();
        return currentPanel;
    }

    /**
     * Verify next Panel to be updated has a valid index.
     *
     * @effects This method will fail if next panel index is out of range.
     */
    private void checkRep() {
        assert (nextPanel >= 1 && nextPanel <= BOARDSIZE) :
                "Panel index is out of range!";
    }

}
