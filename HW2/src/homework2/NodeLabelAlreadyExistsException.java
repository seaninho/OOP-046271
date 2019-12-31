package homework2;

/**
 * A NodeLabelAlreadyExistsException indicates graph contains a node
 * holding same label (forbidden situation - each node has a unique label).
 */
public class NodeLabelAlreadyExistsException extends Throwable {
    /**
     * The Constructor.
     *
     * @requires
     * @modifies this
     * @effects Instantiates an node label already exists exception.
     */
    public NodeLabelAlreadyExistsException() {
        super("");
    }

    /**
     * The Constructor.
     *
     * @requires
     * @modifies this
     * @effects Instantiates an node label already exists exception.
     * @param msg - output string
     */
    public NodeLabelAlreadyExistsException(String msg) {
        super(msg);
    }
}
