package homework2;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * BipartiteGraphTest contains JUnit block-box unit tests for
 * homework2.BipartiteGraph.
 */
public class BipartiteGraphTest {

	@Test
    public void testExample() throws
            NodeLabelAlreadyExistsException,
            SrcNodeIsSameAsDstNodeException,
            EdgeLabelAlreadyExistsException,
            NodeLabelDoesNotExistException,
            EdgeConnectingSameColorNodesException
    {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        
        //create a graph
        driver.createGraph("graph1");
        
        //add a pair of nodes
        driver.addBlackNode("graph1", "n1");
        driver.addWhiteNode("graph1", "n2");
        
        //add an edge
        driver.addEdge("graph1", "n1", "n2", "edge");
        
        //check neighbors
        assertEquals("wrong black nodes", "n1", driver.listBlackNodes("graph1"));
        assertEquals("wrong white nodes", "n2", driver.listWhiteNodes("graph1"));
        assertEquals("wrong children", "n2", driver.listChildren ("graph1", "n1"));
        assertEquals("wrong children", "", driver.listChildren ("graph1", "n2"));
        assertEquals("wrong parents", "", driver.listParents ("graph1", "n1"));
        assertEquals("wrong parents", "n1", driver.listParents ("graph1", "n2"));
    }

    @Test(expected = NullPointerException.class)
    public void testAddNodeWithNullLabel() throws NodeLabelAlreadyExistsException {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a node with null label
        driver.addBlackNode("graph1", null);
    }

    @Test(expected = NodeLabelAlreadyExistsException.class)
    public void testAddSameLabelNode() throws NodeLabelAlreadyExistsException {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a pair of nodes with the same label
        driver.addBlackNode("graph1", "n1");
        driver.addWhiteNode("graph1", "n1");
    }

    @Test(expected = NodeLabelDoesNotExistException.class)
    public void testConnectParentDoesNotExist() throws
            NodeLabelAlreadyExistsException,
            SrcNodeIsSameAsDstNodeException,
            EdgeLabelAlreadyExistsException,
            NodeLabelDoesNotExistException,
            EdgeConnectingSameColorNodesException
    {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a black node
        driver.addBlackNode("graph1", "n1");

        //connect a node that does not exist
        driver.addEdge("graph1", "n2", "n1", "e1");
    }

    @Test(expected = NodeLabelDoesNotExistException.class)
    public void testConnectChildDoesNotExist() throws
            NodeLabelAlreadyExistsException,
            SrcNodeIsSameAsDstNodeException,
            EdgeLabelAlreadyExistsException,
            NodeLabelDoesNotExistException,
            EdgeConnectingSameColorNodesException
    {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a black node
        driver.addBlackNode("graph1", "n1");

        //connect a node that does not exist
        driver.addEdge("graph1", "n1", "n2", "e1");
    }

    @Test(expected = EdgeConnectingSameColorNodesException.class)
    public void testConnectBlackNodes() throws
            NodeLabelAlreadyExistsException,
            SrcNodeIsSameAsDstNodeException,
            EdgeLabelAlreadyExistsException,
            NodeLabelDoesNotExistException,
            EdgeConnectingSameColorNodesException
    {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a two black nodes
        driver.addBlackNode("graph1", "n1");
        driver.addBlackNode("graph1", "n2");

        //connect a pair of black nodes
        driver.addEdge("graph1", "n1", "n2", "e1");
    }

    @Test(expected = EdgeConnectingSameColorNodesException.class)
    public void testConnectWhiteNodes() throws
            NodeLabelAlreadyExistsException,
            SrcNodeIsSameAsDstNodeException,
            EdgeLabelAlreadyExistsException,
            NodeLabelDoesNotExistException,
            EdgeConnectingSameColorNodesException
    {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a two white nodes
        driver.addWhiteNode("graph1", "n1");
        driver.addWhiteNode("graph1", "n2");

        //connect a pair of white nodes
        driver.addEdge("graph1", "n1", "n2", "e1");
    }

    @Test(expected = EdgeLabelAlreadyExistsException.class)
    public void testConnectSameOutgoingEdgeLabel() throws
            NodeLabelAlreadyExistsException,
            SrcNodeIsSameAsDstNodeException,
            EdgeLabelAlreadyExistsException,
            NodeLabelDoesNotExistException,
            EdgeConnectingSameColorNodesException
    {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a black node
        driver.addBlackNode("graph1", "n1");
        //add a white node
        driver.addWhiteNode("graph1", "n2");
        //add a white node
        driver.addWhiteNode("graph1", "n3");

        //connect nodes
        driver.addEdge("graph1", "n1", "n2", "e1");
        //connect nodes again
        driver.addEdge("graph1", "n1", "n3", "e1");
    }

    @Test(expected = EdgeLabelAlreadyExistsException.class)
    public void testConnectSameIncomingEdgeLabel() throws
            NodeLabelAlreadyExistsException,
            SrcNodeIsSameAsDstNodeException,
            EdgeLabelAlreadyExistsException,
            NodeLabelDoesNotExistException,
            EdgeConnectingSameColorNodesException
    {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a black node
        driver.addBlackNode("graph1", "n1");
        //add a black node
        driver.addBlackNode("graph1", "n2");
        //add a white node
        driver.addWhiteNode("graph1", "n3");

        //connect nodes
        driver.addEdge("graph1", "n1", "n3", "e1");
        //connect nodes again
        driver.addEdge("graph1", "n2", "n3", "e1");
    }

    @Test(expected = EdgeLabelAlreadyExistsException.class)
    public void testConnectSameNodesTwice() throws
            NodeLabelAlreadyExistsException,
            SrcNodeIsSameAsDstNodeException,
            EdgeLabelAlreadyExistsException,
            NodeLabelDoesNotExistException,
            EdgeConnectingSameColorNodesException
    {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a black node
        driver.addBlackNode("graph1", "n1");
        //add a white node
        driver.addWhiteNode("graph1", "n2");

        //connect nodes
        driver.addEdge("graph1", "n1", "n2", "e1");
        //connect nodes again
        driver.addEdge("graph1", "n1", "n2", "e2");
    }

    @Test(expected = SrcNodeIsSameAsDstNodeException.class)
    public void testConnectNodeSelfLoop() throws
            NodeLabelAlreadyExistsException,
            SrcNodeIsSameAsDstNodeException,
            EdgeLabelAlreadyExistsException,
            NodeLabelDoesNotExistException,
            EdgeConnectingSameColorNodesException
    {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a black node
        driver.addBlackNode("graph1", "n1");

        //connect node to itself
        driver.addEdge("graph1", "n1", "n1", "e1");
    }

    @Test
    public void testListBlackNodes() throws NodeLabelAlreadyExistsException {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a black node
        driver.addBlackNode("graph1", "n1");
        //add a black node
        driver.addBlackNode("graph1", "n2");
        //add a black node
        driver.addBlackNode("graph1", "n3");

        assertEquals("wrong nodes", "n1 n2 n3", driver.listBlackNodes("graph1"));
    }

    @Test
    public void testListWhiteNodes() throws NodeLabelAlreadyExistsException {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a white node
        driver.addWhiteNode("graph1", "n1");
        //add a white node
        driver.addWhiteNode("graph1", "n2");
        //add a white node
        driver.addWhiteNode("graph1", "n3");

        assertEquals("wrong nodes", "n1 n2 n3", driver.listWhiteNodes("graph1"));
    }

    @Test
    public void testListChildNodes() throws
            NodeLabelAlreadyExistsException,
            EdgeLabelAlreadyExistsException,
            SrcNodeIsSameAsDstNodeException,
            NodeLabelDoesNotExistException,
            EdgeConnectingSameColorNodesException
    {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a white node
        driver.addWhiteNode("graph1", "n1");
        //add a black node
        driver.addBlackNode("graph1", "n2");
        //add a black node
        driver.addBlackNode("graph1", "n3");

        //connect nodes
        driver.addEdge("graph1", "n1", "n2", "e1");
        driver.addEdge("graph1", "n1", "n3", "e2");

        assertEquals("wrong children", "n2 n3", driver.listChildren("graph1", "n1"));
    }

    @Test
    public void testListParentNodes() throws
            NodeLabelAlreadyExistsException,
            EdgeLabelAlreadyExistsException,
            SrcNodeIsSameAsDstNodeException,
            NodeLabelDoesNotExistException,
            EdgeConnectingSameColorNodesException
    {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a black node
        driver.addBlackNode("graph1", "n1");
        //add a white node
        driver.addWhiteNode("graph1", "n2");
        //add a white node
        driver.addWhiteNode("graph1", "n3");

        //connect nodes
        driver.addEdge("graph1", "n1", "n2", "e1");
        driver.addEdge("graph1", "n1", "n3", "e2");

        assertEquals("wrong parents", "n1", driver.listParents("graph1", "n2"));
        assertEquals("wrong parents", "n1", driver.listParents("graph1", "n3"));
    }

    @Test(expected = EdgeIsNotConnectedToNodeException.class)
    public void testEdgeIsNotConnectedToChildNode() throws
            NodeLabelAlreadyExistsException,
            EdgeLabelAlreadyExistsException,
            SrcNodeIsSameAsDstNodeException,
            NodeLabelDoesNotExistException,
            EdgeConnectingSameColorNodesException,
            EdgeIsNotConnectedToNodeException
    {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a black node
        driver.addBlackNode("graph1", "n1");
        //add a black node
        driver.addBlackNode("graph1", "n2");
        //add a white node
        driver.addWhiteNode("graph1", "n3");

        //connect nodes
        driver.addEdge("graph1", "n1", "n3", "e1");
        driver.addEdge("graph1", "n2", "n3", "e2");

        driver.getParentByEdgeLabel("graph1", "n3", "e3");
    }

    @Test(expected = EdgeIsNotConnectedToNodeException.class)
    public void testEdgeIsNotConnectedToParentNode() throws
            NodeLabelAlreadyExistsException,
            EdgeLabelAlreadyExistsException,
            SrcNodeIsSameAsDstNodeException,
            NodeLabelDoesNotExistException,
            EdgeConnectingSameColorNodesException,
            EdgeIsNotConnectedToNodeException
    {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a black node
        driver.addBlackNode("graph1", "n1");
        //add a black node
        driver.addBlackNode("graph1", "n2");
        //add a white node
        driver.addWhiteNode("graph1", "n3");
        //add a white node
        driver.addWhiteNode("graph1", "n4");

        //connect nodes
        driver.addEdge("graph1", "n1", "n3", "e1");
        driver.addEdge("graph1", "n2", "n4", "e2");

        driver.getChildByEdgeLabel("graph1", "n1", "e2");
    }

    @Test
    public void testGetChildByEdgeLabel() throws
            NodeLabelAlreadyExistsException,
            EdgeLabelAlreadyExistsException,
            SrcNodeIsSameAsDstNodeException,
            NodeLabelDoesNotExistException,
            EdgeConnectingSameColorNodesException,
            EdgeIsNotConnectedToNodeException
    {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a white node
        driver.addWhiteNode("graph1", "n1");
        //add a white node
        driver.addWhiteNode("graph1", "n2");
        //add a black node
        driver.addBlackNode("graph1", "n3");
        //add a black node
        driver.addBlackNode("graph1", "n4");

        //connect nodes
        driver.addEdge("graph1", "n1", "n3", "e1");
        driver.addEdge("graph1", "n2", "n4", "e2");

        assertEquals("wrong node", "n3", driver.getChildByEdgeLabel("graph1", "n1", "e1"));
        assertEquals("wrong node", "n4", driver.getChildByEdgeLabel("graph1", "n2", "e2"));
    }

    @Test
    public void testGetParentByEdgeLabel() throws
            NodeLabelAlreadyExistsException,
            EdgeLabelAlreadyExistsException,
            SrcNodeIsSameAsDstNodeException,
            NodeLabelDoesNotExistException,
            EdgeConnectingSameColorNodesException,
            EdgeIsNotConnectedToNodeException
    {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a white node
        driver.addWhiteNode("graph1", "n1");
        //add a black node
        driver.addBlackNode("graph1", "n2");
        //add a black node
        driver.addBlackNode("graph1", "n3");
        //add a white node
        driver.addWhiteNode("graph1", "n4");

        //connect nodes
        driver.addEdge("graph1", "n1", "n2", "e1");
        driver.addEdge("graph1", "n1", "n3", "e2");
        driver.addEdge("graph1", "n2", "n4", "e2");
        driver.addEdge("graph1", "n3", "n4", "e1");

        assertEquals("wrong node", "n1", driver.getParentByEdgeLabel("graph1", "n2", "e1"));
        assertEquals("wrong node", "n1", driver.getParentByEdgeLabel("graph1", "n3", "e2"));
        assertEquals("wrong node", "n2", driver.getParentByEdgeLabel("graph1", "n4", "e2"));
        assertEquals("wrong node", "n3", driver.getParentByEdgeLabel("graph1", "n4", "e1"));
    }

    @Test
    public void testGetGraphSize() throws NodeLabelAlreadyExistsException {
        //create a graph
        BipartiteGraph<String> graph = new BipartiteGraph<>();

        //add a white node
        graph.addWhiteNode("n1", new NodeType<>("n1"));
        //add a black node
        graph.addBlackNode("n2", new NodeType<>("n2"));
        //add a black node
        graph.addBlackNode("n3", new NodeType<>("n3"));
        //add a white node
        graph.addWhiteNode("n4", new NodeType<>("n4"));
        //add a black node
        graph.addBlackNode("n5", new NodeType<>("n5"));
        //add a white node
        graph.addWhiteNode("n6", new NodeType<>("n6"));

        assertEquals("wrong size", 6, graph.getGraphSize());
    }

    @Test
    public void testListAllNodes() throws NodeLabelAlreadyExistsException {
        //create a graph
        BipartiteGraph<String> graph = new BipartiteGraph<>();

        //add a black node
        graph.addBlackNode("n1", new NodeType<>("n1"));
        //add a white node
        graph.addWhiteNode("n2", new NodeType<>("n2"));
        //add a white node
        graph.addWhiteNode("n3", new NodeType<>("n3"));

        //create node list
        List<String> nodeList = new ArrayList<>(List.of("n1", "n2", "n3"));

        assertEquals("wrong nodes", nodeList, graph.listAllNodes());
    }

    @Test
    public void testGetNodeByLabel() throws
            NodeLabelAlreadyExistsException, NodeLabelDoesNotExistException
    {
        //create a graph
        BipartiteGraph<String> graph = new BipartiteGraph<>();

        //add a white node
        graph.addWhiteNode("n1", new NodeType<>("n1"));

        assertEquals("wrong node color", NodeColor.WHITE, graph.getNodeByLabel("n1").getNodeColor());
        assertEquals("wrong node type", "n1", graph.getNodeByLabel("n1").getNodeType().getType());
        assertEquals("wrong node label", "n1", graph.getNodeByLabel("n1").getNodeLabel());
    }


    @Test
    public void testValidScenario() throws
            NodeLabelAlreadyExistsException,
            EdgeLabelAlreadyExistsException,
            SrcNodeIsSameAsDstNodeException,
            NodeLabelDoesNotExistException,
            EdgeConnectingSameColorNodesException,
            EdgeIsNotConnectedToNodeException
    {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a black node
        driver.addBlackNode("graph1", "n1");
        //add a white node
        driver.addWhiteNode("graph1", "n2");
        //add a black node
        driver.addBlackNode("graph1", "n3");
        //add a white
        driver.addWhiteNode("graph1", "n4");

        //add edges
        driver.addEdge("graph1", "n1", "n2", "e12");
        driver.addEdge("graph1", "n1", "n4", "e14");
        driver.addEdge("graph1", "n3", "n2", "e32");
        driver.addEdge("graph1", "n3", "n4", "e34");
        driver.addEdge("graph1", "n2", "n1", "e21");
        driver.addEdge("graph1", "n2", "n3", "e23");
        driver.addEdge("graph1", "n4", "n1", "e41");
        driver.addEdge("graph1", "n4", "n3", "e43");

        //check neighbors
        assertEquals("wrong black nodes", "n1 n3", driver.listBlackNodes("graph1"));
        assertEquals("wrong white nodes", "n2 n4", driver.listWhiteNodes("graph1"));
        assertEquals("wrong children", "n2 n4", driver.listChildren ("graph1", "n1"));
        assertEquals("wrong children", "n1 n3", driver.listChildren ("graph1", "n2"));
        assertEquals("wrong children", "n2 n4", driver.listChildren ("graph1", "n3"));
        assertEquals("wrong children", "n1 n3", driver.listChildren ("graph1", "n4"));
        assertEquals("wrong parents", "n2 n4", driver.listParents ("graph1", "n1"));
        assertEquals("wrong parents", "n1 n3", driver.listParents ("graph1", "n2"));
        assertEquals("wrong parents", "n2 n4", driver.listParents ("graph1", "n3"));
        assertEquals("wrong parents", "n1 n3", driver.listParents ("graph1", "n4"));
        assertEquals("wrong child", "n2", driver.getChildByEdgeLabel("graph1", "n1", "e12"));
        assertEquals("wrong parent", "n2", driver.getParentByEdgeLabel("graph1", "n1", "e21"));
        assertEquals("wrong child", "n4", driver.getChildByEdgeLabel("graph1", "n1", "e14"));
        assertEquals("wrong parent", "n4", driver.getParentByEdgeLabel("graph1", "n1", "e41"));
        assertEquals("wrong child", "n2", driver.getChildByEdgeLabel("graph1", "n3", "e32"));
        assertEquals("wrong parent", "n2", driver.getParentByEdgeLabel("graph1", "n3", "e23"));
        assertEquals("wrong child", "n4", driver.getChildByEdgeLabel("graph1", "n3", "e34"));
        assertEquals("wrong parent", "n4", driver.getParentByEdgeLabel("graph1", "n3", "e43"));
        assertEquals("wrong child", "n1", driver.getChildByEdgeLabel("graph1", "n2", "e21"));
        assertEquals("wrong parent", "n1", driver.getParentByEdgeLabel("graph1", "n2", "e12"));
        assertEquals("wrong child", "n3", driver.getChildByEdgeLabel("graph1", "n2", "e23"));
        assertEquals("wrong parent", "n3", driver.getParentByEdgeLabel("graph1", "n2", "e32"));
        assertEquals("wrong child", "n1", driver.getChildByEdgeLabel("graph1", "n4", "e41"));
        assertEquals("wrong parent", "n1", driver.getParentByEdgeLabel("graph1", "n4", "e14"));
        assertEquals("wrong child", "n3", driver.getChildByEdgeLabel("graph1", "n4", "e43"));
        assertEquals("wrong parent", "n3", driver.getParentByEdgeLabel("graph1", "n4", "e34"));
    }
  
}
