package homework2;

/**
 * A PipeMaxCapacityReached indicates pipe has reached its capacity limit and
 * can no longer holds work objects.
 */
public class PipeMaxCapacityReached extends Throwable {
    /**
     * The Constructor.
     *
     * @requires
     * @modifies this
     * @effects Instantiates an pipe max capacity reached exception.
     */
    public PipeMaxCapacityReached() {
        super("");
    }

    /**
     * The Constructor.
     *
     * @requires
     * @modifies this
     * @effects Instantiates an pipe max capacity reached exception.
     * @param msg - output string
     */
    public PipeMaxCapacityReached(String msg) {
        super(msg);
    }
}
