package homework4;

public class PanelColsNotifier implements PanelOrderNotifier {
    //  Abstraction function:
    //	A PanelColsNotifier dictates the billboard's panels to changes
    //	their colors in a specific order.
    //	First, all panels of the 1st column, then all panels of the 2nd column,
    //  etc...
    //
    //	Representation Invariant:
    //  1 <= nextPanel <= 25

    private final int BOARDSIZE = 25;
    private final int COLUMNSIZE = 5;
    private int currentColumn;
    private int nextPanel;

    public PanelColsNotifier() {
        currentColumn = 1;
        nextPanel = 1;
    }

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
        assert (nextPanel >= 1 && nextPanel <= BOARDSIZE) :
                "Panel index is out of range!";
        assert (currentColumn >= 1 && currentColumn <= COLUMNSIZE) :
                "Column index is out of range!";
    }

}
