package homework2;

/**
 * A SrcNodeIsSameAsDstNodeException indicates source node is the same as the
 * destination node (forbidden situation).
 */
public class SrcNodeIsSameAsDstNodeException extends Throwable {

    /**
     * The Constructor.
     *
     * @requires
     * @modifies this
     * @effects Instantiates a src node is same as dst node exception.
     */
    public SrcNodeIsSameAsDstNodeException() {
        super("");
    }

    /**
     * The Constructor.
     *
     * @requires
     * @modifies this
     * @effects Instantiates a src node is same as dst node exception.
     * @param msg - output string
     */
    public SrcNodeIsSameAsDstNodeException(String msg) {
        super(msg);
    }
}
