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
 * Each node is either "BLACK" or "WHITE".
 */
public class Node<L,D> {
    // Abstraction function:
    // Node n contains data (of type D) and color (of type NodeColor).

    // Representation Invariant for every Node n:
    // For each node n, n.color == BLACK || n.color == WHITE

    private final NodeColor color;
    private final D data;

    private List<Node<L, D>> parentsList = new ArrayList<Node<L, D>>();
    private List<Node<L, D>> childrenList = new ArrayList<Node<L, D>>();
    private Map<L, Edge<L>> inEdges = new HashMap<L, Edge<L>>();
    private Map<L, Edge<L>> outEdges = new HashMap<L, Edge<L>>();

    /**
     * Node constructor.
     *
     * @modifies this
     * @effects Instantiates a new node.
     * @throws NullPointerException when invoked with null parameters
     */
    public Node(D data, NodeColor color) throws NullPointerException {
        if (data == null || color == null) {
            throw new NullPointerException();
        }
        this.color = color;
        this.data = data;
        checkRep();
    }

    /**
     * Gets node color.
     *
     * @return the nodes color
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
        HashMap<L, Edge<L>>copyOfOutEdges = new HashMap<L, Edge<L>>(outEdges);
        return copyOfOutEdges;
    }

    /**
     * Gets ingoing edges map.
     *
     * @return copy of ingoing edges map.
     */
    public HashMap<L, Edge<L>> getIncomingEdges() {
        HashMap<L, Edge<L>>copyOfInEdges = new HashMap<L, Edge<L>>(inEdges);
        return copyOfInEdges;
    }

    /**
     * Gets node's parents list.
     *
     * @return copy of list of parents nodes.
     */
    public List<Node<L, D>> getNodesParents() {
        ArrayList<Node<L, D>> copyOfParentsList =
                new ArrayList<Node<L, D>>(parentsList);
        return copyOfParentsList;
    }

    /**
     * Gets node's children list.
     *
     * @return copy of list of child nodes.
     */
    public List<Node<L, D>> getNodesChildren() {
        ArrayList<Node<L, D>> copyOfChildrenList =
                new ArrayList<Node<L, D>>(childrenList);
        return copyOfChildrenList;
    }

    /**
     * Check representation.
     *
     * @effects Checks the Representation Invariant is kept
     */
    private void checkRep() {
        assert (this.color == NodeColor.BLACK || this.color == NodeColor.WHITE) :
                "Nodes color is illegal";

    }
}
