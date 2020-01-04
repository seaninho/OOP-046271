package homework2;

import java.util.*;

/**
 * This class represents a directed colored graph, called Bipartite Graph.
 * Bipartite Graph is a set of graph nodes decomposed into two disjoint
 * sets, BLACK or WHITE, such that no two graph nodes within the same set
 * are connected.
 * Each node in a Bipartite Graph is either BLACK or WHITE.
 * Each node in a Bipartite Graph contains data of type D.
 * Each edge in a Bipartite Graph connects between a black node to a white node.
 * Each node and edge in the Bipartite Graph has a label of type L.
 *
 * Each Bipartite Graph upholds these following conventions:
 * 1. Nodes cannot be connected to themselves.
 * 2. Nodes cannot be connected to a same color node, meaning a BLACK colored
 *    node can only be connected to a WHITE color node.
 * 3. Parallel edges are not allowed.
 */
public class BipartiteGraph<L> {
    // Abstraction function:
    // Bipartite Graph is represented by a map of label-node pairs.
    // Each node has a color, type and a label.
    // Each node has its own parent and child nodes list.
    // Each node has its own incoming and outgoing edges list.
    // The graph size is determined by the number of nodes it contains.

    // Representation Invariant:
    // Each node label is a valid non-null class L object.
    // Each edge label is a valid non-null class L object.
    // Each node has a unique label.
    // Each pair of connected nodes must be of different color.
    // Each node incoming/outgoing edge labels are unique.
    // Nodes cannot be connected to themselves.
    // Parallel edges are not allowed.

    private Map<L, Node<L, ?>> graphNodes;

    /**
     * Bipartite Graph constructor.
     *
     * @modifies this
     * @effects Constructs a new graph.
     */
    public BipartiteGraph() {
        graphNodes = new HashMap<>();
        checkRep();
    }

    /**
     * Add a black node to graph.
     *
     * @modifies graph named graphName
     * @effects Adds a black node represented by the String nodeName to the
     * 			graph named graphName.
     * @throws NodeLabelAlreadyExistsException when node label 'nodeName'
     *          already exists in graph.
     */
    public void addBlackNode(L nodeName, NodeType<?> nodeType) throws
            NodeLabelAlreadyExistsException
    {
        this.addNode(NodeColor.BLACK, nodeType, nodeName);
    }

    /**
     * Add a white node to the graph.
     *
     * @modifies graph named graphName
     * @effects Adds a white node represented by the String nodeName to the
     * 			graph named graphName.
     * @throws NodeLabelAlreadyExistsException when node label 'nodeName'
     *          already exists in graph.
     */
    public void addWhiteNode(L nodeName, NodeType<?> nodeType) throws
            NodeLabelAlreadyExistsException
    {
        this.addNode(NodeColor.WHITE, nodeType, nodeName);
    }

    /**
     * Add an edge to the graph.
     *
     * @modifies graph named graphName.
     * @effects Adds an edge from the node pLabel to the node cLabel
     * 			in the graph. The new edge's label is the Object
     * 			eLabel.
     * @throws NodeLabelDoesNotExistException when node label 'pLabel' or
     *          'cLabel' does not exists in graph.
     * @throws SrcNodeIsSameAsDstNodeException when attempting to connect a node
     *          to itself, thereby creating a self-loop
     * @throws EdgeLabelAlreadyExistsException when edge label 'eLabel' already
     *          exists in graph.
     * @throws EdgeConnectingSameColorNodesException when attempting to connect
     *          same color nodes.
     * @param pLabel - parent label.
     * @param cLabel - child label.
     * @param eLabel - edge label.
     */
    public void addEdge(L pLabel, L cLabel, L eLabel) throws
            NodeLabelDoesNotExistException,
            SrcNodeIsSameAsDstNodeException,
            EdgeLabelAlreadyExistsException,
            EdgeConnectingSameColorNodesException
    {
        checkRep();
        if (pLabel == null) {
            throw new NullPointerException("Parent node label cannot be null");
        }
        if (cLabel == null) {
            throw new NullPointerException("Child node label cannot be null");
        }
        if (eLabel == null) {
            throw new NullPointerException("Edge label cannot be null");
        }
        if (!this.graphNodes.containsKey(pLabel)) {
            throw new NodeLabelDoesNotExistException("Parent node does not" +
                    " exist");
        }
        if (!this.graphNodes.containsKey(cLabel)) {
            throw new NodeLabelDoesNotExistException("Child node does not" +
                    " exist");
        }
        if (pLabel == cLabel) {
            throw new SrcNodeIsSameAsDstNodeException();
        }
        if (this.graphNodes.get(pLabel).getNodeColor() ==
                this.graphNodes.get(cLabel).getNodeColor()) {
            throw new EdgeConnectingSameColorNodesException();
        }
        if (this.graphNodes.get(pLabel).getOutgoingEdges().containsKey(eLabel)) {
            throw new EdgeLabelAlreadyExistsException("Each outgoing edge has" +
                    " a unique label");
        }
        if (this.graphNodes.get(cLabel).getIncomingEdges().containsKey(eLabel)) {
            throw new EdgeLabelAlreadyExistsException("Each incoming edge has" +
                    " a unique label");
        }
        if (this.graphNodes.get(pLabel).getNodeChildren().contains(cLabel)) {
            throw new EdgeLabelAlreadyExistsException("Parallel edge are not " +
                    "allowed");
        }

        this.graphNodes.get(pLabel).addChildNode(cLabel, eLabel);
        this.graphNodes.get(cLabel).addParentNode(pLabel, eLabel);
        checkRep();
    }

    /**
     * Gets node by a given label.
     *
     * @return a node object labeled nodeLabel.
     * @throws NodeLabelAlreadyExistsException when node label 'nodeLabel'
     *          already exists in graph.
     */
    public Node<L, ?> getNodeByLabel(L nodeLabel) throws
            NodeLabelDoesNotExistException
    {
        if (nodeLabel == null) {
            throw new NullPointerException("Node label cannot be null");
        }
        if (!this.graphNodes.containsKey(nodeLabel)) {
            throw new NodeLabelDoesNotExistException();
        }

        return this.graphNodes.get(nodeLabel);
    }

    /**
     * List all black nodes in graph.
     *
     * @return a list of labels of all black nodes in graph.
     */
    public List<L> listBlackNodes() {
        return this.listNodesByColor(NodeColor.BLACK);
    }

    /**
     * List all white nodes in graph.
     *
     * @return a list of labels of all white nodes in graph.
     */
    public List<L> listWhiteNodes() {
        return this.listNodesByColor(NodeColor.WHITE);
    }

    /**
     * List all nodes in graph.
     *
     * @return a list of labels of all nodes in graph.
     */
    public List<L> listAllNodes() {
        return new ArrayList<>(this.graphNodes.keySet());
    }

    /**
     * Gets graph size.
     *
     * @return number of nodes in graph.
     */
    public int getGraphSize() {
        return this.graphNodes.size();
    }

    /**
     * List all children of a specific node.
     *
     * @return a list containing all of parentName children in the graph.
     * @throws NodeLabelDoesNotExistException when node label 'nodeLabel'
     *          does not exist in graph.
     */
    public List<L> listChildren(L parentName) throws
            NodeLabelDoesNotExistException
    {
        if (parentName == null) {
            throw new NullPointerException("Node label cannot be null");
        }
        if (!this.graphNodes.containsKey(parentName)) {
            throw new NodeLabelDoesNotExistException("Parent node doesn't exist");
        }

        return this.graphNodes.get(parentName).getNodeChildren();
    }

    /**
     * List all parents of a specific node.
     *
     * @return a list containing all of childName parents in the graph.
     * @throws NodeLabelDoesNotExistException when node label 'nodeLabel'
     *          does not exist in graph.
     */
    public List<L> listParents(L childName) throws
            NodeLabelDoesNotExistException
    {
        if (childName == null) {
            throw new NullPointerException("Node label cannot be null");
        }
        if (!this.graphNodes.containsKey(childName)) {
            throw new NodeLabelDoesNotExistException("Child node doesn't exist");
        }

        return this.graphNodes.get(childName).getNodeParents();
    }

    /**
     * Gets a node by its parent node and the edge connecting them.
     *
     * @return parent's (pLabel) child, connected by the edge labeled eLabel.
     * @throws NodeLabelDoesNotExistException when node label 'pLabel' does not
     *          exist in graph.
     * @throws EdgeIsNotConnectedToNodeException when edge label 'eLabel' is not
     *          a part of node 'pLabel' outgoing edges.
     * @param pLabel - parent label
     * @param eLabel - edge label
     */
    public L getChildByEdgeLabel(L pLabel, L eLabel) throws
            NodeLabelDoesNotExistException,
            EdgeIsNotConnectedToNodeException
    {
        if (pLabel == null) {
            throw new NullPointerException("Parent node label cannot be null");
        }
        if (eLabel == null) {
            throw new NullPointerException("Edge label cannot be null");
        }
        if (!this.graphNodes.containsKey(pLabel)) {
            throw new NodeLabelDoesNotExistException("Parent node doesn't exist");
        }

        Node<L, ?> parentNode = this.graphNodes.get(pLabel);
        if(!parentNode.getOutgoingEdges().containsKey(eLabel)) {
            throw new EdgeIsNotConnectedToNodeException();
        }

        return parentNode.getOutgoingEdges().get(eLabel).getDstNodeLabel();
    }


    /**
     * Gets a node by its child node and the edge connecting them.
     *
     * @return child's (cLabel) parent, connected by the edge labeled eLabel.
     * @throws NodeLabelDoesNotExistException when node label 'cLabel' does not
     *          exist in graph.
     * @throws EdgeIsNotConnectedToNodeException when edge label 'eLabel' is not
     *          a part of node 'cLabel' incoming edges.
     * @param cLabel - child label
     * @param eLabel - edge label
     */
    public L getParentByEdgeLabel(L cLabel, L eLabel) throws
            NodeLabelDoesNotExistException,
            EdgeIsNotConnectedToNodeException
    {
        if (cLabel == null) {
            throw new NullPointerException("Child node label cannot be null");
        }
        if (eLabel == null) {
            throw new NullPointerException("Edge label cannot be null");
        }
        if (!this.graphNodes.containsKey(cLabel)) {
            throw new NodeLabelDoesNotExistException("Child node doesn't exist");
        }

        Node<L, ?> childNode = this.graphNodes.get(cLabel);
        if(!childNode.getIncomingEdges().containsKey(eLabel)) {
            throw new EdgeIsNotConnectedToNodeException();
        }

        return childNode.getIncomingEdges().get(eLabel).getSrcNodeLabel();
    }

    /**
     * Adds a node to the graph.
     *
     * @modifies this
     * @effects Adds a node colored 'color', of type 'type' and labeled 'label'
     *          to the graph.
     * @throws NodeLabelAlreadyExistsException when node label 'label'
     *          already exists in graph.
     */
    private void addNode(NodeColor color, NodeType<?> type, L label)
            throws NodeLabelAlreadyExistsException
    {
        checkRep();
        if (color == null) {
            throw new NullPointerException("Node color cannot be null");
        }
        if (type == null) {
            throw new NullPointerException("Node type cannot be null");
        }
        if (label == null) {
            throw new NullPointerException("Node label cannot be null");
        }
        if (this.graphNodes.containsKey(label)) {
            throw new NodeLabelAlreadyExistsException();
        }

        Node<L, ?> newNode = new Node<>(color, type, label);
        this.graphNodes.put(label, newNode);
        checkRep();
    }

    /**
     * List all nodes colored the same
     *
     * @return a list of labels of all the same color nodes in the graph.
     */
    private List<L> listNodesByColor(NodeColor color) {
        List<L> sameColorNodes = new ArrayList<>();
        for (Node<L, ?> node : this.graphNodes.values()) {
            if (node.getNodeColor() == color) {
                sameColorNodes.add(node.getNodeLabel());
            }
        }

        return sameColorNodes;
    }

    /**
     * Checks for null node labels.
     *
     * @effects Checks the Representation Invariant is kept.
     * @return true if restrictions are met, false otherwise.
     */
    private boolean checkNodeLabelRestrictions() {
        for (L nodeLabel : this.graphNodes.keySet()) {
            if (nodeLabel == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks for null node colors.
     *
     * @effects Checks the Representation Invariant is kept.
     * @return true if restrictions are met, false otherwise.
     */
    private boolean checkNodeColorRestrictions() {
        for (Node<L, ?> node : this.graphNodes.values()) {
            if (node.getNodeColor() == null ) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks for null node types.
     *
     * @effects Checks the Representation Invariant is kept.
     * @return true if restrictions are met, false otherwise.
     */
    private boolean checkNodeTypeRestrictions() {
        for (Node<L, ?> node : this.graphNodes.values()) {
            if (node.getNodeType() == null ) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks for same color connections in graph,
     * e.g. BLACK node connected to a BLACK node.
     *
     * @effects Checks the Representation Invariant is kept.
     * @return true if restrictions are met, false otherwise.
     */
    private boolean checkSameNodeColorConnectionRestrictions() {
        for (Node<L, ?> node : this.graphNodes.values()) {
            // Check child nodes
            for (L cLabel : node.getNodeChildren()) {
                NodeColor cColor = this.graphNodes.get(cLabel).getNodeColor();
                if (node.getNodeColor() == cColor) {
                    return false;
                }
            }
            // Check parent nodes
            for (L pLabel : node.getNodeParents()) {
                NodeColor pColor = this.graphNodes.get(pLabel).getNodeColor();
                if (node.getNodeColor() == pColor) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks for self loops in graph.
     *
     * @effects Checks the Representation Invariant is kept.
     * @return true if restrictions are met, false otherwise.
     */
    private boolean checkSelfLoopsRestrictions() {
        for (Node<L, ?> node : this.graphNodes.values()) {
            // Check child nodes
            if (node.getNodeChildren().size() > 0) {
                for (L cLabel : node.getNodeChildren()) {
                    if (node.getNodeLabel() == cLabel) {
                        return false;
                    }
                }
            }
            // Check parent nodes
            if (node.getNodeParents().size() > 0) {
                for (L pLabel : node.getNodeParents()) {
                    if (node.getNodeLabel() == pLabel) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Checks for parallel edges in graph.
     *
     * @effects Checks the Representation Invariant is kept.
     * @return true if restrictions are met, false otherwise.
     */
    private boolean checkParallelEdgesRestrictions() {
        for (Node<L, ?> node : this.graphNodes.values()) {
            if (node.checkParallelEdges(node.getNodeChildren()) ||
                node.checkParallelEdges(node.getNodeParents())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check representation.
     *
     * @effects Checks the Representation Invariant is kept.
     */
    private void checkRep() {
        assert (this.checkNodeLabelRestrictions()) :
                "Node label cannot be null!";
        assert (this.checkNodeColorRestrictions()) :
                "Node color cannot be null";
        assert (this.checkNodeTypeRestrictions()) :
                "Node type cannot be null";
        assert (this.checkSameNodeColorConnectionRestrictions()) :
                "Connected nodes must be of a different color!";
        assert (this.checkSelfLoopsRestrictions()) :
                "Self loops are not allowed!";
        assert (this.checkParallelEdgesRestrictions()) :
                "Parallel edges are not allowed!";
    }
}
