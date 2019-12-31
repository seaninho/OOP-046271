package homework2;

/**
 * A NodeLabelDoesNotExistException indicates graph does not contain a node
 * holding required label.
 */
public class NodeLabelDoesNotExistException extends Throwable {
    /**
     * The Constructor.
     *
     * @requires
     * @modifies this
     * @effects Instantiates an node label does not exist exception.
     */
    public NodeLabelDoesNotExistException() {
        super("");
    }

    /**
     * The Constructor.
     *
     * @requires
     * @modifies this
     * @effects Instantiates an node label does not exist exception.
     * @param msg - output string
     */
    public NodeLabelDoesNotExistException(String msg) {
        super(msg);
    }
}

