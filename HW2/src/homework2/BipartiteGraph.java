package homework2;

import java.util.*;

/**
 * This class implements BipartiteGraph.
 * Each node and edge in the graph has a label of type L.
 * Each node's label is uniq.
 * Each edge can only connect nodes of different types.
 */
public class BipartiteGraph<L, D> {
    // Abstraction function:

    // Representation Invariant:

    private Map<L, Node<D>> nodes; // L for label
    /**
     * @modifies this
     * @effects Constructs a new graph.
     */
    public BipartiteGraph() {
        nodes = new HashMap<L, Node<D>>();
        checkRep();
    }


    /**
     * @requires nodeLabel != null
     * @modifies graph
     * @effects Adds a black or white node represented by nodeName label
     * to the graph.
     */
    public void addNode(Object nodeLabel, D data, NodeColor type) {
        // TODO: Implement this method
        checkRep();
        if (nodeLabel == null) {
            throw new BadGraphHandleException("Cannot add a node with " +
                    "null nodeName");
        }
        Node<D> newNode = new Node<D>(data, type);
        nodes.put((L)nodeLabel, newNode);
    }

    /**
     * @requires ((addBlackNode(parentLabel) && addWhiteNode(childLabel))
     *              || (addWhiteNode(parentLabel) && addBlackNode(childLabel)))
     *           && edgeLabel != null
     *           && node named parentLabel has no other outgoing edge labeled
     * 				edgeLabel
     *           && node named childLabel has no other incoming edge labeled
     * 				edgeLabel
     * @modifies graph named graphName
     * @effects Adds an edge from the node parentLabel to the node childLabel
     * 			in the graph. The new edge's label is the Object
     * 			edgeLabel.
     */
    public void addEdge(L parentLabel, L childLabel,
                        L edgeLabel) {
        //TODO: Implement this method

        if (edgeLabel == null || parentLabel == childLabel )  {

        }
        Node parentNode = nodes.get(parentLabel);
        Node childNode = nodes.get(childLabel);
        if (parentNode.getColor() == childNode.getColor()) {

        }
        if()
    }

    /**
     * @requires
     * @return a space-separated list of the names of all the black nodes
     * 		   in the graph, in alphabetical order.
     */
    public Object listBlackNodes() {
        //TODO: Implement this method

    }

    /**
     * @requires
     * @return a space-separated list of the names of all the white nodes
     * 		   in the graph, in alphabetical order.
     */
    public Object listWhiteNodes() {
        //TODO: Implement this method

    }

    /**
     * @requires createNode(parentName)
     * @return a space-separated list of the names of the children of
     * 		   parentName in the graph, in alphabetical order.
     */
    public Object listChildren(Object parentName) {
        //TODO: Implement this method
    }

    /**
     * @requires createNode(childName)
     * @return a space-separated list of the names of the parents of
     * 		   childName in the graph, in alphabetical order.
     */
    public Object listParents(Object childName) {
        //TODO: Implement this method

    }


    /**
     * @requires addEdge(graphName, parentName, str, edgeLabel) for some
     * 			 string str
     * @return the name of the child of parentName that is connected by the
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public Object getChildByEdgeLabel(Object parentName, Object edgeLabel) {
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

    /*--------------------------------ADD FUNCTIONS----------------------------------*/

    /**
     *
     */
    public boolean isConnected(Object srcNode, Object dstNode) {

    }

    /**
     * @return list of all the nodes between srcNode and dstNode.
     */
    public List<Object> getPath(Object srcNode, Object dstNode) {

    }

    /**
     * Check rep.
     */
    private void checkRep() {

    }
}
