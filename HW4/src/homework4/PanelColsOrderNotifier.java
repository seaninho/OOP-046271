package homework4;

/**
 * PanelColsOrderNotifier represents a panel order notifier which dictates the
 * billboard's panels will change color in all-panels-in-column order.
 */
public class PanelColsOrderNotifier implements PanelOrderNotifier {
    //  Abstraction function:
    //	A PanelColsNotifier dictates the billboard's panels to changes
    //	their colors in a specific order.
    //	First, all panels of the 1st column, then all panels of the 2nd column,
    //  etc...
    //
    //	Representation Invariant:
    //  0 <= nextPanel < 25

    private final int BOARDSIZE = 25;
    private final int COLUMNSIZE = 5;
    private int currentColumn;
    private int nextPanel;

    /**
     * PanelColsOrderNotifier constructor.
     *
     * @modifies this
     * @effects Instantiates a new panels in column-changing order notifier.
     *
     */
    public PanelColsOrderNotifier() {
        currentColumn = 0;
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
        if (nextPanel == (BOARDSIZE - COLUMNSIZE + currentColumn)) {
            nextPanel = (++currentColumn) % COLUMNSIZE;
        } else {
            nextPanel += COLUMNSIZE;
        }
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
        assert (currentColumn >= 0 && currentColumn < COLUMNSIZE) :
                "Column index is out of range!";
    }

}
