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
    // A Node n contains data (of type D) and color of type NodeColor.

    // Representation Invariant for every Node n:
    // (n.color == BLACK || n.color == WHITE) &&
    //

    private final NodeColor color;
    private final D data;

    private List<Node<L, D>> parentsList = new ArrayList<Node<L, D>>();
    private List<Node<L, D>> childrenList = new ArrayList<Node<L, D>>();
    private Map<L, Edge<L>> inEdges = new HashMap<L, Edge<L>>();
    private Map<L, Edge<L>> outEdges = new HashMap<L, Edge<L>>();

    /**
     * Constructs a new node
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
     * @return the nodes color
     */
    public NodeColor getColor() {
        return this.color;
    }

    /**
     * @return the node data
     */
    public D getNodeData() {
        return this.data;
    }

    /**
     * @return copy of the map of outgoing edges.
     */
    public HashMap<L, Edge<L>> getOutgoingEdges() {
        HashMap<L, Edge<L>>copyOfOutEdges = new HashMap<L, Edge<L>>(outEdges);
        return copyOfOutEdges;
    }

    /**
     * @return copy of the map of ingoing edges.
     */
    public HashMap<L, Edge<L>> getIncomingEdges() {
        HashMap<L, Edge<L>>copyOfInEdges = new HashMap<L, Edge<L>>(inEdges);
        return copyOfInEdges;
    }

    /**
     * @return copy of list of parent nodes.
     */
    public List<Node<L, D>> getNodesParents() {
        ArrayList<Node<L, D>> copyOfParentsList =
                new ArrayList<Node<L, D>>(parentsList);
        return copyOfParentsList;
    }

    /**
     * @return copy of list of child nodes.
     */
    public List<Node<L, D>> getNodesChildren() {
        ArrayList<Node<L, D>> copyOfChildrenList =
                new ArrayList<Node<L, D>>(childrenList);
        return copyOfChildrenList;
    }

    /**
     * Check rep.
     */
    private void checkRep() {
        assert (this.color == NodeColor.BLACK || this.color == NodeColor.WHITE) :
                "Nodes color is illegal";

    }
}
