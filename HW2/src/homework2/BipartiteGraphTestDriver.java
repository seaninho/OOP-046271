package homework2;

import java.util.*;

/**
 * This class implements a testing driver for homework2.BipartiteGraph.
 * The driver manages BipartiteGraphs whose nodes and edges are Strings.
 */
public class BipartiteGraphTestDriver {

    private Map<String, BipartiteGraph<String>> graphs;

    /**
     * @modifies this
     * @effects Constructs a new test driver.
     */
    public BipartiteGraphTestDriver () {
        this.graphs = new HashMap<>();
    }

    
    /**
     * @requires graphName != null
     * @modifies this
     * @effects Creates a new graph named graphName. The graph is initially
     * 			empty.
     */
    public void createGraph(String graphName) {
        BipartiteGraph<String> newGraph = new BipartiteGraph<>();
        graphs.put(graphName, newGraph);
    }

    
    /**
     * @requires createGraph(graphName)
     *           && nodeName != null
     *           && neither addBlackNode(graphName,nodeName) 
     *                  nor addWhiteNode(graphName,nodeName)
     *                      has already been called on this
     * @modifies graph named graphName
     * @effects Adds a black node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addBlackNode(String graphName, String nodeName) throws
            NodeLabelAlreadyExistsException
    {
        NodeType<String> nodeType = new NodeType<>(nodeName);
        graphs.get(graphName).addBlackNode(nodeName, nodeType);
    }

    
    /**K
     * @requires createGraph(graphName)
     *           && nodeName != null
     *           && neither addBlackNode(graphName,nodeName) 
     *                  nor addWhiteNode(graphName,nodeName)
     *                      has already been called on this
     * @modifies graph named graphName
     * @effects Adds a white node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addWhiteNode(String graphName, String nodeName) throws
            NodeLabelAlreadyExistsException
    {
        NodeType<?> nodeType = new NodeType<>(nodeName);
        graphs.get(graphName).addWhiteNode(nodeName, nodeType);
    }

    
    /**
     * @requires createGraph(graphName)
     *           && ((addBlackNode(parentName) && addWhiteNode(childName))
     *              || (addWhiteNode(parentName) && addBlackNode(childName)))
     *           && edgeLabel != null
     *           && node named parentName has no other outgoing edge labeled
     * 				edgeLabel
     *           && node named childName has no other incoming edge labeled
     * 				edgeLabel
     * @modifies graph named graphName
     * @effects Adds an edge from the node parentName to the node childName
     * 			in the graph graphName. The new edge's label is the String
     * 			edgeLabel.
     */
    public void addEdge(String graphName, String parentName, String childName,
                        String edgeLabel) throws
            EdgeIsNotConnectedToNodeException,
            SrcNodeIsSameAsDstNodeException,
            EdgeLabelAlreadyExistsException,
            NodeLabelDoesNotExistException
    {
        graphs.get(graphName).addEdge(parentName, childName, edgeLabel);
    }

    
    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the black nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listBlackNodes(String graphName) {
        // Create black only node list
        List<String> blackNodes =
                new ArrayList<>(this.graphs.get(graphName).listBlackNodes());
        // Sort alphabetically
        Collections.sort(blackNodes);
        // Return space-separated list
        return String.join(" ", blackNodes);
    }

    
    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the white nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listWhiteNodes(String graphName) {
        // Create white only node list
        List<String> whiteNodes =
                new ArrayList<>(this.graphs.get(graphName).listWhiteNodes());
        // Sort alphabetically
        Collections.sort(whiteNodes);
        // Return space-separated list
        return String.join(" ", whiteNodes);
    }

    
    /**
     * @requires createGraph(graphName) && createNode(parentName)
     * @return a space-separated list of the names of the children of
     * 		   parentName in the graph graphName, in alphabetical order.
     */
    public String listChildren(String graphName, String parentName) throws
            NodeLabelDoesNotExistException
    {
        // Create child only node list
        List<String> childNodes = new ArrayList<>
                (this.graphs.get(graphName).listChildren(parentName));
        // Sort alphabetically
        Collections.sort(childNodes);
        // Return space-separated list
        return String.join(" ", childNodes);
    }

    
    /**
     * @requires createGraph(graphName) && createNode(childName)
     * @return a space-separated list of the names of the parents of
     * 		   childName in the graph graphName, in alphabetical order.
     */
    public String listParents(String graphName, String childName) throws
            NodeLabelDoesNotExistException
    {
        // Create child only node list
        List<String> parentNodes = new ArrayList<>
                (this.graphs.get(graphName).listParents(childName));
        // Sort alphabetically
        Collections.sort(parentNodes);
        // Return space-separated list
        return String.join(" ", parentNodes);
    }

    
    /**
     * @requires addEdge(graphName, parentName, str, edgeLabel) for some
     * 			 string str
     * @return the name of the child of parentName that is connected by the
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public String getChildByEdgeLabel(String graphName, String parentName,
                                        String edgeLabel) throws
            EdgeIsNotConnectedToNodeException, NodeLabelDoesNotExistException
    {
        return this.graphs.get(graphName).
                                    getChildByEdgeLabel(parentName, edgeLabel);

    }

    
    /**
     * @requires addEdge(graphName, str, childName, edgeLabel) for some
     * 			 string str
     * @return the name of the parent of childName that is connected by the 
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public String getParentByEdgeLabel(String graphName, String childName,
    									String edgeLabel) throws
            EdgeIsNotConnectedToNodeException, NodeLabelDoesNotExistException {
        return this.graphs.get(graphName).
                                    getParentByEdgeLabel(childName, edgeLabel);
    }
}
