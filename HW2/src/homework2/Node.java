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
 * This class implements a graph node.
 * Each node has a label of type L.
 * Each node is of type T.
 * Each node is either "BLACK" or "WHITE".
 */
public class Node<L, T> {
    // Abstraction function:
    // Node n contains nodeType (of type T), label of type L
    // and color (of type NodeColor).

    // Representation Invariant for every Node n:
    // For each node n, n.data != NULL, n.label != NULL
    // and n.color == BLACK || n.color == WHITE

    private final NodeColor color;
    private final NodeType<T> type;
    private final L label;

    private List<Node<L, T>> parentsList;
    private List<Node<L, T>> childrenList;
    private Map<L, Edge<L>> inEdges;
    private Map<L, Edge<L>> outEdges;

    /**
     * Node constructor.
     *
     * @modifies this
     * @effects Instantiates a new node.
     * @throws NullPointerException when invoked with null parameters
     */
    public Node(NodeColor color, NodeType<T> type, L label) throws NullPointerException {
        if (color == null || type == null || label == null) {
            throw new NullPointerException();
        }
        parentsList = new ArrayList<Node<L, T>>();
        childrenList = new ArrayList<Node<L, T>>();
        inEdges = new HashMap<L, Edge<L>>();
        outEdges = new HashMap<L, Edge<L>>();
        this.color = color;
        this.type = NodeType<T>;
        this.label = label;
        checkRep();
    }

    /**
     * Gets node color.
     *
     * @return the nodes color.
     */
    public NodeColor getColor() {
        return this.color;
    }

    /**
     * Gets node data.
     *
     * @return the node data.
     */
    public D getNodeData() {
        return this.data;
    }

    /**
     * Gets outgoing edges map.
     *
     * @return copy of outgoing edges map.
     */
    public HashMap<L, Edge<L>> getOutgoingEdges() {
        return new HashMap<L, Edge<L>>(outEdges);
    }

    /**
     * Gets ingoing edges map.
     *
     * @return copy of ingoing edges map.
     */
    public HashMap<L, Edge<L>> getIncomingEdges() {
        return new HashMap<L, Edge<L>>(inEdges);
    }

    /**
     * Gets node's parents list.
     *
     * @return copy of list of parents nodes.
     */
    public List<Node<L, D>> getNodesParents() {
        return new ArrayList<Node<L, D>>(parentsList);
    }

    /**
     * Gets node's children list.
     *
     * @return copy of list of child nodes.
     */
    public List<Node<L, D>> getNodesChildren() {
        return new ArrayList<Node<L, D>>(childrenList);
    }

    /**
     * Check representation.
     *
     * @effects Checks the Representation Invariant is kept
     */
    private void checkRep() {
        assert (this.label != null) :
                "Node label cannot be null";
        assert (this.data != null) :
                "Node data cannot be null";
        assert (this.color == NodeColor.BLACK || this.color == NodeColor.WHITE) :
                "Nodes color is illegal";

    }
}
