package homework2;

/**
 * A EdgeLabelAlreadyExistsException indicates node contains an incoming /
 * outgoing edge holding same label.
 * This is a forbidden situation in a Bipartite Graph - each in/out going edge
 * has a unique label.
 */
public class EdgeLabelAlreadyExistsException extends Throwable {
    /**
     * The Constructor.
     *
     * @requires
     * @modifies this
     * @effects Instantiates an edge label already exists exception.
     */
    public EdgeLabelAlreadyExistsException() {
        super("");
    }

    /**
     * The Constructor.
     *
     * @requires
     * @modifies this
     * @effects Instantiates an edge label already exists exception.
     * @param msg - output string
     */
    public EdgeLabelAlreadyExistsException(String msg) {
        super(msg);
    }
}
