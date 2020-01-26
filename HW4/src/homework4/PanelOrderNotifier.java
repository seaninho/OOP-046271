package homework4;

/**
 * PanelOrderNotifier represents the panel color changing order notifier.
 */
public interface PanelOrderNotifier {
    //  Abstraction function:
    //	A PanelOrderNotifier defines the order in which the billboard's panels
    //  changes their color.
    //
    //	Representation Invariant:
    //

    /**
     * Next panel to change its color.
     *
     * @effects Determines the next panel to change its color, depending on a
     * preset logic.
     *
     */
    int nextPanel();
}
