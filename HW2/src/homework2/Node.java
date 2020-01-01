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
    // A node has a color (BLACK or WHITE), a type (of type T) and a label
    // (of type L).
    // A Node has a parents list which contains all of its parent nodes.
    // A Node has a children list which contains all of its child nodes.
    // A node has a incoming edges map which contains all of its parent nodes
    // and incoming edges.
    // A node has a outgoing edges map which contains all of its child nodes
    // and outgoing edges.

    // Representation Invariant for every Node n:
    // Node color can only be BLACK or WHITE.
    // Node type cannot be null.
    // Node label cannot be null.
    // Node cannot be a child or a parent of itself.
    // Each node in parents or children list is unique (no parallel edges).

    private final NodeColor color;
    private final NodeType<T> type;
    private final L label;

    private List<L> parentsList;
    private List<L> childrenList;
    private Map<L, Edge<L>> inEdges;
    private Map<L, Edge<L>> outEdges;

    /**
     * Node constructor.
     *
     * @requires color != null && type != null && label != null
     * @modifies this
     * @effects Instantiates a new node.
     */
    public Node(NodeColor color, NodeType<T> type, L label) {
        parentsList = new ArrayList<L>();
        childrenList = new ArrayList<L>();
        inEdges = new HashMap<L, Edge<L>>();
        outEdges = new HashMap<L, Edge<L>>();
        this.color = color;
        this.type = type;
        this.label = label;
        checkRep();
    }

    /**
     * Gets node color.
     *
     * @return the nodes color.
     */
    public NodeColor getNodeColor() {
        return this.color;
    }

    /**
     * Gets node type.
     *
     * @return the node type.
     */
    public NodeType<T> getNodeType() {
        return this.type;
    }

    /**
     * Gets node label.
     *
     * @return the node label.
     */
    public L getNodeLabel() {
        return this.label;
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
    public List<L> getNodeParents() {
        return new ArrayList<L>(parentsList);
    }

    /**
     * Gets node's children list.
     *
     * @return copy of list of child nodes.
     */
    public List<L> getNodeChildren() {
        return new ArrayList<L>(childrenList);
    }

    /**
     * @requires cLabel != null && eLabel != null
     * @modifies this
     * @effects Adds the child node label to node's childrenList and
     * 			adds an edge connecting the node and its child to the
     * 			outgoing edge map.
     */
    public void addChildNode(L cLabel, L eLabel) {
        Edge<L> newEdge = new Edge<>(eLabel, this.label, cLabel);
        this.outEdges.put(eLabel, newEdge);
        this.childrenList.add(cLabel);
        checkRep();
    }

    /**
     * @requires pLabel != null && eLabel != null
     * @modifies this
     * @effects Adds the parent node label to node's parentsList and
     * 			adds an edge connecting the node and its parents to the
     * 			incoming edge map.
     */
    public void addParentNode(L pLabel, L eLabel) {
        Edge<L> newEdge = new Edge<>(eLabel, pLabel, this.label);
        this.inEdges.put(eLabel, newEdge);
        this.parentsList.add(pLabel);
        checkRep();
    }

    public boolean checkParallelEdges(List<L> nodeList) {
        List<L> tempList = new ArrayList<L>();
        for (L node : nodeList) {
            if (tempList.contains(node)) {
                return true;
            }
            tempList.add(node);
        }
        return false;
    }

    /**
     * Check representation.
     *
     * @effects Checks the Representation Invariant is kept
     */
    private void checkRep() {
        assert (this.color == NodeColor.BLACK || this.color == NodeColor.WHITE) :
                "Nodes color is illegal";
        assert (this.type != null) :
                "Node type cannot be null";
        assert (this.label != null) :
                "Node label cannot be null";
        assert (this.parentsList.contains(this.getNodeLabel())) :
                "Node cannot be its own parent";
        assert (this.childrenList.contains(this.getNodeLabel())) :
                "Node cannot be its own child";
        assert (!this.checkParallelEdges(parentsList)) :
                "Node has two different edges connected to same parent";
        assert (!this.checkParallelEdges(childrenList)) :
                "Node has two different edges connected to same child";
    }
}
