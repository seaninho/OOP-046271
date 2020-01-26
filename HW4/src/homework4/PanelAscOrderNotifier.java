package homework4;

public class PanelAscOrderNotifier implements PanelOrderNotifier {
    //  Abstraction function:
    //	A PanelAscOrderNotifier dictates the billboard's panels to changes their
    //	colors in ascending order.
    //
    //	Representation Invariant:
    //  1 <= nextPanel <= 25

    private final int BOARDSIZE = 25;
    private int nextPanel;

    public PanelAscOrderNotifier() {
        nextPanel = 1;
        checkRep();
    }

    @Override
    public int getNextPanel() {
        int currentPanel = nextPanel;
        nextPanel = (nextPanel++) % BOARDSIZE;
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
