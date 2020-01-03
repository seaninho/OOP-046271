package homework2;

/**
 * A PipeMaxCapacityIsNotPositive indicates pipe capacity limit is not positive,
 * i.e. limit <= 0.
 */
public class PipeMaxCapacityIsNotPositive extends Throwable {
    /**
     * The Constructor.
     *
     * @requires
     * @modifies this
     * @effects Instantiates an pipe max capacity is not positive exception.
     */
    public PipeMaxCapacityIsNotPositive() {
        super("");
    }

    /**
     * The Constructor.
     *
     * @requires
     * @modifies this
     * @effects Instantiates an pipe max capacity is not positive exception.
     * @param msg - output string
     */
    public PipeMaxCapacityIsNotPositive(String msg) {
        super(msg);
    }
}
