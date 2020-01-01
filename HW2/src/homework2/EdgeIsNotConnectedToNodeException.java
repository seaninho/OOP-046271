package homework2;

/**
 * A EdgeIsNotConnectedToNodeException indicates edge is not connected to given
 * node.
 */
public class EdgeIsNotConnectedToNodeException extends Throwable {
    /**
     * The Constructor.
     *
     * @requires
     * @modifies this
     * @effects Instantiates an edge is not connected to node exception.
     */
    public EdgeIsNotConnectedToNodeException() {
        super("");
    }

    /**
     * The Constructor.
     *
     * @requires
     * @modifies this
     * @effects Instantiates an edge is not connected to node exception.
     * @param msg - output string
     */
    public EdgeIsNotConnectedToNodeException(String msg) {
        super(msg);
    }
}
