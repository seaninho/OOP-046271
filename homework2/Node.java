package homework2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum NodeColor {
    BLACK,
    WHITE
}

/**
 * This class implements NodesData, the data stored in a node in a bipartite
 * graph.
 * Each NodesData is either of type "BLACK" or type "WHITE".
 */
public class Node<L,D> {
    // Abstraction function:

    // Representation Invariant:

    private final NodeColor color;
    private final D data;

    private List<Node<L, D>> parentsList = new ArrayList<>();
    private List<Node<L, D>> childrenList = new ArrayList<>();
    private Map<D, Edge<L>> inEdges = new HashMap<D, Edge<L>>();
    private Map<D, Edge<L>> outEdges = new HashMap<D, Edge<L>>();

    /**
     * Constructor.
     *
     * @modifies this
     * @effects Instantiates a new node.
     * @throws BadGraphHandleException
     *             when invoked with null parameters
     */
    public Node(D data, NodeColor color) throws BadGraphHandleException {
        if (data == null || color == null) {
            throw new BadGraphHandleException();
        }
        this.color = color;
        this.data = data;
        checkRep();
    }

    /**
     *
     */
    public NodeColor getColor() {
        return this.color;
    }

    /**
     *
     */
    public D getNodeData() {
        return this.data;
    }

    /**
     *
     */
    public NodeColor getOutgoingEdges() {
        return this.color;
    }

    /**
     *
     */
    public NodeColor getIncomingEdges() {
        return this.color;
    }

    /**
     * Check rep.
     */
    private void checkRep() {

    }
}
