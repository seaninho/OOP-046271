package homework2;

/**
 * NodeType represents the type of a Bipartite Graph node.
 * Node type can be of any type T.
 */
public class NodeType<T> {
    //	 Abstraction function:
    //	 A node type is represented by its type.
    //
    //	 Representation Invariant:
    //	 Node type cannot be null.

    private T type;

    /**
     * NodeType constructor.
     *
     * @modifies this
     * @effects Instantiates a new node type.
     */
    public NodeType(T type) {
        if (type == null) {
            throw new NullPointerException("Node type cannot be null");
        }
        this.type = type;
        checkRep();
    }

    /**
     * Get node type.
     *
     * @effects Returns the edge label
     */
    public T getType() {
        return this.type;
    }

    /**
     * Check representation.
     *
     * @effects Checks the Representation Invariant is kept
     */
    public void checkRep() {
        assert (this.type != null) :
                "Node type cannot be null";
    }
}
