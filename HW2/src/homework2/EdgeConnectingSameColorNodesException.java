package homework2;

/**
 * A EdgeConnectingSameColorNodesException indicates edge is connecting between
 * two nodes of the same color (BLACK or WHITE).
 * This is a forbidden situation in a Bipartite Graph.
 */
public class EdgeConnectingSameColorNodesException extends Throwable {
    /**
     * The Constructor.
     *
     * @requires
     * @modifies this
     * @effects Instantiates an edge connecting same color nodes exception.
     */
    public EdgeConnectingSameColorNodesException() {
        super("");
    }

    /**
     * The Constructor.
     *
     * @requires
     * @modifies this
     * @effects Instantiates an edge connecting same color nodes exception.
     * @param msg - output string
     */
    public EdgeConnectingSameColorNodesException(String msg) {
        super(msg);
    }
}
