package homework2;


import static org.junit.Assert.*;
import org.junit.Test;

/**
 * SimulatorTest contains JUnit black-box unit tests for
 * homework2.Simulator.
 */
public class SimulatorTest {

    @Test
    public void testExample() throws
            PipeMaxCapacityIsNotPositive,
            NodeLabelAlreadyExistsException,
            NodeLabelDoesNotExistException,
            SrcNodeIsSameAsDstNodeException,
            EdgeLabelAlreadyExistsException,
            EdgeConnectingSameColorNodesException
    {
        SimulatorTestDriver driver = new SimulatorTestDriver();

        //create a graph
        driver.createSimulator("sim1");

        //add a pair of nodes
        driver.addChannel("sim1", "c1", 2);
        String product = "paper";
        driver.addParticipant("sim1", "p1", product, 300);

        //add an edge
        driver.addEdge("sim1", "c1", "p1", "edge");

        //check storage amount
        assertTrue("wrong storage amount", 0 ==
                driver.getParticipantStorageAmount("sim1", "p1"));
    }

    @Test(expected = NullPointerException.class)
    public void testAddChannelWithNullName() throws
            PipeMaxCapacityIsNotPositive,
            NodeLabelAlreadyExistsException,
            NullPointerException
    {
        SimulatorTestDriver driver = new SimulatorTestDriver();

        //create a graph
        driver.createSimulator("sim1");

        //add a node with null label
        driver.addChannel("sim1", null, 2);
    }

    @Test(expected = NullPointerException.class)
    public void testAddParticipantWithNullName() throws
            NodeLabelAlreadyExistsException {
        SimulatorTestDriver driver = new SimulatorTestDriver();

        //create a graph
        driver.createSimulator("sim1");

        //add a node with null label
        driver.addParticipant("sim1", null,
                "Glass", 2);
    }

    @Test(expected = EdgeLabelAlreadyExistsException.class)
    public void testAddEdgeWithLabelAlreadyExists() throws NodeLabelDoesNotExistException,
            SrcNodeIsSameAsDstNodeException,
            EdgeLabelAlreadyExistsException,
            EdgeConnectingSameColorNodesException,
            PipeMaxCapacityIsNotPositive,
            NodeLabelAlreadyExistsException{
        SimulatorTestDriver driver = new SimulatorTestDriver();

        //create a graph
        driver.createSimulator("sim1");

        //add a pair of nodes
        driver.addChannel("sim1", "c1", 2);
        String product = "paper";
        driver.addParticipant("sim1", "p1", product, 300);

        //add two edges with the same label
        driver.addEdge("sim1", "c1", "p1", "edge");
        driver.addEdge("sim1", "c1", "p1", "edge");
    }

    @Test(expected = NodeLabelDoesNotExistException.class)
    public void testAddEdgeNodeNotExist() throws NodeLabelDoesNotExistException,
            SrcNodeIsSameAsDstNodeException,
            EdgeLabelAlreadyExistsException,
            EdgeConnectingSameColorNodesException,
            PipeMaxCapacityIsNotPositive,
            NodeLabelAlreadyExistsException{
        SimulatorTestDriver driver = new SimulatorTestDriver();

        //create a graph
        driver.createSimulator("sim1");

        //add a pair of nodes
        driver.addChannel("sim1", "c1", 2);
        String product = "paper";
        driver.addParticipant("sim1", "p1", product, 300);

        //add an edge - participant not exist
        driver.addEdge("sim1", "c1", "p2", "edge");

        //add an edge - channel not exist
        driver.addEdge("sim1", "c2", "p1", "edge");
    }


    @Test(expected = PipeMaxCapacityReached.class)
    public void testChannelCapacityReached() throws
            NodeLabelDoesNotExistException,
            PipeMaxCapacityIsNotPositive,
            NodeLabelAlreadyExistsException,
            PipeMaxCapacityReached {
        SimulatorTestDriver driver = new SimulatorTestDriver();

        //create a graph
        driver.createSimulator("sim1");

        //add a pair of nodes
        driver.addChannel("sim1", "c1", 1);

        Transaction transaction1 = new Transaction("glass", 300);
        Transaction transaction2 = new Transaction("nylon", 120);

        driver.sendTransaction("sim1", "c1", transaction1);
        driver.sendTransaction("sim1", "c1", transaction2);
    }

    @Test(expected = NodeLabelDoesNotExistException.class)
    public void listContentsNodeLabelNotExist() throws
            NodeLabelDoesNotExistException {
        SimulatorTestDriver driver = new SimulatorTestDriver();
        //create a graph
        driver.createSimulator("sim1");

        driver.listContents("sim1", "c1");
    }

    @Test(expected = NodeLabelDoesNotExistException.class)
    public void getParticipantStorageAmountNodeLabelNotExist() throws
            NodeLabelDoesNotExistException {
        SimulatorTestDriver driver = new SimulatorTestDriver();
        //create a graph
        driver.createSimulator("sim1");

        driver.getParticipantStorageAmount("sim1", "p1");
    }

    @Test(expected = NodeLabelDoesNotExistException.class)
    public void getParticipantToRecycleAmountNodeLabelNotExist() throws
            NodeLabelDoesNotExistException {
        SimulatorTestDriver driver = new SimulatorTestDriver();
        //create a graph
        driver.createSimulator("sim1");

        driver.getParticipantToRecycleAmount("sim1", "p1");
    }

    @Test()
    public void testValidScenario() throws
            NodeLabelAlreadyExistsException,
            PipeMaxCapacityIsNotPositive,
            NodeLabelDoesNotExistException,
            SrcNodeIsSameAsDstNodeException,
            EdgeLabelAlreadyExistsException,
            EdgeConnectingSameColorNodesException,
            PipeMaxCapacityReached{
        SimulatorTestDriver driver = new SimulatorTestDriver();
        //create a graph
        driver.createSimulator("sim1");
        /* sim2 simulation:
         *     c1 ------> p1 ------> c1
         *     400         400       350
         */
        driver.createSimulator("sim2");
        /* sim2 simulation:
         *     c1 ------> p1 ------> c1
         *     400         400       350
         */


        String product1 = "paper";
        String product2 = "glass";

        //add participants - sim1
        driver.addParticipant("sim1", "p1", product2, 200);
        driver.addParticipant("sim1", "p2", product1, 300);
        //add participant - sim2
        driver.addParticipant("sim2", "p1", product1, 50);

        //add channels - sim1
        driver.addChannel("sim1", "c1", 1);
        driver.addChannel("sim1", "c2", 2);
        //add channel - sim2
        driver.addChannel("sim2", "c1", 3);

        //add edges - sim1
        driver.addEdge("sim1", "c2", "p1", "edge1");
        driver.addEdge("sim1", "p1", "c1", "edge2");
        driver.addEdge("sim1", "c1", "p2", "edge1");
        //add edges - sim2
        driver.addEdge("sim2", "p1", "c1", "edge1");
        driver.addEdge("sim2", "c1", "p1", "edge3");

        Transaction transaction1 = new Transaction(product1, 400);
        Transaction transaction2 = new Transaction(product2, 500);
        // add transactions - sim1
        driver.sendTransaction("sim1", "c2", transaction1);
        driver.sendTransaction("sim1", "c2", transaction2);
        // add transactions - sim2
        driver.sendTransaction("sim2", "c1", transaction1);

        //check storage amount
        assertTrue("wrong storage amount", 0 ==
                driver.getParticipantStorageAmount("sim1", "p1"));
        assertTrue("wrong recycle amount", 0 ==
                driver.getParticipantToRecycleAmount("sim1", "p1"));

        driver.printAllEdges("sim1");
        driver.printAllEdges("sim2");

        assertEquals("wrong transactions", "Transaction:  " +
                "Product: paper Amount: 400 Transaction:  Product: glass Amount: " +
                "500", driver.listContents ("sim1", "c2"));

        // simulate sim2 - first step
        driver.simulate("sim2");
        assertEquals("wrong transactions", "",
                driver.listContents ("sim2", "c1"));
        assertTrue( "wrong participant storage amount", 350 ==
                driver.getParticipantStorageAmount("sim2", "p1"));
        assertTrue( "wrong participant recycle amount", 50 ==
                driver.getParticipantToRecycleAmount("sim2", "p1"));

        // simulate sim2 - second step
        driver.simulate("sim2");
        assertEquals("wrong transactions", "Transaction: " +
                        " Product: paper Amount: 350",
                driver.listContents ("sim2", "c1"));
        assertTrue( "wrong participant storage amount", 0 ==
                driver.getParticipantStorageAmount("sim2", "p1"));
        assertTrue( "wrong participant recycle amount", 50 ==
                driver.getParticipantToRecycleAmount("sim2", "p1"));

    }

    @Test()
    public void testValidScenario2() throws
            NodeLabelAlreadyExistsException,
            PipeMaxCapacityIsNotPositive,
            NodeLabelDoesNotExistException,
            SrcNodeIsSameAsDstNodeException,
            EdgeLabelAlreadyExistsException,
            EdgeConnectingSameColorNodesException,
            PipeMaxCapacityReached{
        SimulatorTestDriver driver = new SimulatorTestDriver();
        //create a graph
        driver.createSimulator("sim1");
        /* sim2 simulation:
         *     c2 ------> p1 ------> c1 ------> p2
         *     400        400       400         100
         *                          p1:0
         */

        String product1 = "paper";
        String product2 = "glass";

        //add participants - sim1
        driver.addParticipant("sim1", "p1", product2, 200);
        driver.addParticipant("sim1", "p2", product1, 300);

        //add channels - sim1
        driver.addChannel("sim1", "c1", 1);
        driver.addChannel("sim1", "c2", 2);

        //add edges - sim1
        driver.addEdge("sim1", "c2", "p1", "edge1");
        driver.addEdge("sim1", "p1", "c1", "edge2");
        driver.addEdge("sim1", "c1", "p2", "edge1");

        Transaction transaction1 = new Transaction(product1, 400);
        Transaction transaction2 = new Transaction(product2, 500);
        // add transactions - sim1
        driver.sendTransaction("sim1", "c2", transaction1);


        //check storage amount
        assertTrue("wrong storage amount", 0 ==
                driver.getParticipantStorageAmount("sim1", "p1"));
        assertTrue("wrong recycle amount", 0 ==
                driver.getParticipantToRecycleAmount("sim1", "p1"));
        assertEquals("wrong transactions", "Transaction: " +
                        " Product: paper Amount: 400",
                driver.listContents ("sim1", "c2"));

        // simulate sim1 - first step
        driver.simulate("sim1");
        assertTrue( "wrong participant storage amount", 400 ==
                driver.getParticipantStorageAmount("sim1", "p1"));
        assertTrue( "wrong participant recycle amount", 0 ==
                driver.getParticipantToRecycleAmount("sim1", "p1"));

        // simulate sim2 - second step
        driver.simulate("sim1");
        assertEquals("wrong transactions", "Transaction: " +
                        " Product: paper Amount: 400",
                driver.listContents ("sim1", "c1"));
        assertTrue( "wrong participant storage amount", 0 ==
                driver.getParticipantStorageAmount("sim1", "p1"));

        // simulate sim2 - Step three
        driver.simulate("sim1");
        assertEquals("wrong transactions", "",
                driver.listContents ("sim1", "c1"));
        assertTrue( "wrong participant storage amount", 0 ==
                driver.getParticipantStorageAmount("sim1", "p1"));
        assertTrue( "wrong participant storage amount", 100 ==
                driver.getParticipantStorageAmount("sim1", "p2"));
        assertTrue( "wrong participant recycle amount", 300 ==
                driver.getParticipantToRecycleAmount("sim1", "p2"));
    }

    @Test()
    public void testValidScenario3() throws
            NodeLabelAlreadyExistsException,
            PipeMaxCapacityIsNotPositive,
            NodeLabelDoesNotExistException,
            SrcNodeIsSameAsDstNodeException,
            EdgeLabelAlreadyExistsException,
            EdgeConnectingSameColorNodesException,
            PipeMaxCapacityReached{
        SimulatorTestDriver driver = new SimulatorTestDriver();
        //create a graph
        driver.createSimulator("sim1");
        /* sim2 simulation:
         *         c1 -------------
         *       / 100pr2          \
         *      /                   \
         *     c2 ------> p1 ------> c1
         *     400pr1     400       100pr2
         *                          p1:400pr1
         */

        String product1 = "paper";
        String product2 = "glass";

        //add participants - sim1
        driver.addParticipant("sim1", "p1", product2, 200);


        //add channels - sim1
        driver.addChannel("sim1", "c1", 1);
        driver.addChannel("sim1", "c2", 2);

        //add edges - sim1
        driver.addEdge("sim1", "c2", "p1", "edge1");
        driver.addEdge("sim1", "p1", "c1", "edge2");

        Transaction transaction1 = new Transaction(product1, 300);
        Transaction transaction2 = new Transaction(product2, 200);
        // add transaction - sim1
        driver.sendTransaction("sim1", "c2", transaction1);

        //check storage amount
        assertTrue("wrong storage amount", 0 ==
                driver.getParticipantStorageAmount("sim1", "p1"));
        assertTrue("wrong recycle amount", 0 ==
                driver.getParticipantToRecycleAmount("sim1", "p1"));
        assertEquals("wrong transactions", "Transaction: " +
                        " Product: paper Amount: 300",
                driver.listContents ("sim1", "c2"));

        // simulate sim1 - first step
        driver.simulate("sim1");
        assertTrue( "wrong participant storage amount", 300 ==
                driver.getParticipantStorageAmount("sim1", "p1"));
        assertTrue( "wrong participant recycle amount", 0 ==
                driver.getParticipantToRecycleAmount("sim1", "p1"));

        // add transaction - sim1
        driver.sendTransaction("sim1", "c1", transaction2);

        // simulate sim2 - second step
        driver.simulate("sim1");
        assertEquals("wrong transactions", "",
                driver.listContents ("sim1", "c2"));
        assertTrue( "wrong participant storage amount", 300 ==
                driver.getParticipantStorageAmount("sim1", "p1"));
        assertEquals("wrong transactions", "",
                driver.listContents ("sim1", "c2"));
        assertEquals("wrong transactions", "Transaction: " +
                        " Product: glass Amount: 200",
                driver.listContents ("sim1", "c1"));
    }
}
