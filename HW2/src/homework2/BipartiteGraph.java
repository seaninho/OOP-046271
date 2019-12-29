package homework2;

import java.util.*;

/**
 * This class represents a directed colored graph, called Bipartite Graph.
 * Each node in a Bipartite Graph is either BLACK or WHITE.
 * Each node in a Bipartite Graph contains data of type D.
 * Each edge in a Bipartite Graph connects between a black node to a white node.
 * Each node and edge in the Bipartite Graph has a label of type L.
 *
 * Each Bipartite Graph upholds these following conventions:
 * 1. Nodes cannot be connected to themselves.
 * 2. Nodes cannot be connected to a same color node, meaning a BLACK colored
 *    node can only be connected to a WHITE color node.
 */
public class BipartiteGraph<L> {
    // Abstraction function:

    // Representation Invariant:

    private Map<L, Node<L, ?>> nodes;

    /**
     * Bipartite Graph constructor.
     *
     * @modifies this
     * @effects Constructs a new graph.
     */
    public BipartiteGraph() {
        nodes = new HashMap<>();
        checkRep();
    }


    /**
     * Add a black node to graph.
     *
     * @modifies graph named graphName
     * @effects Adds a black node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addBlackNode(L nodeName, NodeType<?> nodeType) {
        this.addNode();
    }

    /**
     * Adds a white node to the graph.
     *
     * @modifies graph named graphName
     * @effects Adds a white node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addWhiteNode(L nodeName) {

    }

    /**
     * Adds an edge to the graph.
     *
     * @modifies graph named graphName
     * @effects Adds an edge from the node parentLabel to the node childLabel
     * 			in the graph. The new edge's label is the Object
     * 			edgeLabel.
     */
    public void addEdge(L parentLabel, L childLabel, L edgeLabel) {

    }

    /**
     * @requires
     * @return a space-separated list of the names of all the black nodes
     * 		   in the graph, in alphabetical order.
     */
    public L listBlackNodes() {
        //TODO: Implement this method
    }

    /**
     * @requires
     * @return a space-separated list of the names of all the white nodes
     * 		   in the graph, in alphabetical order.
     */
    public L listWhiteNodes() {
        //TODO: Implement this method
    }

    /**
     * @requires createNode(parentName)
     * @return a space-separated list of the names of the children of
     * 		   parentName in the graph, in alphabetical order.
     */
    public L listChildren(L parentName) {
        //TODO: Implement this method
    }

    /**
     * @requires createNode(childName)
     * @return a space-separated list of the names of the parents of
     * 		   childName in the graph, in alphabetical order.
     */
    public L listParents(L childName) {
        //TODO: Implement this method

    }


    /**
     * @requires addEdge(graphName, parentName, str, edgeLabel) for some
     * 			 string str
     * @return the name of the child of parentName that is connected by the
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public L getChildByEdgeLabel(Object parentName, Object edgeLabel) {
        //TODO: Implement this method


    }


    /**
     * @requires addEdge(graphName, str, childName, edgeLabel) for some
     * 			 Object obj
     * @return the name of the parent of childName that is connected by the
     * 		   edge labeled edgeLabel, in the graph.
     */
    public Object getParentByEdgeLabel(Object childName, Object edgeLabel) {
        //TODO: Implement this method


    }

    /**
     *
     */
    private boolean isConnected(L srcNodeLabel, L dstNodeLabel) {

    }

    /**
     * @return list of all the nodes between srcNode and dstNode.
     */
    private List<L> getPath(L srcNode, L dstNode) {

    }

    /**
     * Adds a node to the graph.
     *
     * @modifies graph named graphName
     * @effects Adds a white node represented by the String nodeName to the
     * 			graph named graphName.
     */
    private void addNode(L nodeLabel, D nodeData) {

    }

    /**
     * Check rep.
     */
    private void checkRep() {

    }
}
