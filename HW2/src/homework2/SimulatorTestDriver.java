package homework2;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements a testing driver for Simulator. The driver manages
 * Simulators for recycling channels
 */
public class SimulatorTestDriver {

	private Map<String, Simulator<String, Transaction>> simulators;

	/**
	 * @modifies this
	 * @effects Constructs a new test driver.
	 */
	public SimulatorTestDriver() {
        this.simulators = new HashMap<>();
	}

	/**
	 * @requires simName != null
	 * @modifies this
	 * @effects Creates a new simulator named simName. The simulator's graph is
	 *          initially empty.
	 */
	public void createSimulator(String simName) {
	    // TODO: Implement this method
		Simulator<String, Transaction> simulator = new Simulator<>();
		simulators.put(simName, simulator);
	}

	/**
	 * @requires createSimulator(simName)
     *           && channelName != null && channelName has
	 *           not been used in a previous addChannel()  or
	 *           addParticipant() call on this object
	 *           limit > 0
	 * @modifies simulator named simName
	 * @effects Creates a new Channel named by the String channelName, with a limit, and add it to
	 *          the simulator named simName.
	 */
	public void addChannel(String simName, String channelName, int limit) throws
			PipeMaxCapacityIsNotPositive,
			NodeLabelAlreadyExistsException {
	    // TODO: Implement this method
		Simulator<String, Transaction> simulator = simulators.get(simName);
		Channel channel = new Channel(channelName, limit);
		simulator.addPipe(channel);
	}

	/**
	 * @requires createSimulator(simName) && participantName != null
	 *           && participantName has not been used in a previous addParticipant(), addChannel()
	 *           call on this object
	 *			 amount > 0
	 *			 product must be a single word, without special characters/number and also in lowercase
	 * @modifies simulator named simName
	 * @effects Creates a new Participant named by the String participantName and add
	 *          it to the simulator named simName.
	 */
	public void addParticipant(String simName, String participantName, String product, int amount) throws
			NodeLabelAlreadyExistsException {
        // TODO: Implement this method
		Simulator<String, Transaction> simulator = simulators.get(simName);
		Transaction transaction = new Transaction(product, amount);
		Participant participant = new Participant(participantName, transaction);
		simulator.addFilter(participant);
	}

	/**
	 * @requires createSimulator(simName) && ((addPipe(parentName) &&
	 *           addFilter(childName)) || (addFilter(parentName) &&
	 *           addPipe(childName))) && edgeLabel != null && node named
	 *           parentName has no other outgoing edge labeled edgeLabel
	 *           && node named childName has no other incoming edge labeled edgeLabel
	 * @modifies simulator named simName
	 * @effects Adds an edge from the node named parentName to the node named
	 *          childName in the simulator named simName. The new edge's label
	 *          is the String edgeLabel.
	 */
	public void addEdge(String simName, String parentName, String childName, String edgeLabel) throws
			NodeLabelDoesNotExistException,
			SrcNodeIsSameAsDstNodeException,
			EdgeLabelAlreadyExistsException,
			EdgeConnectingSameColorNodesException {
        // TODO: Implement this method
		Simulator<String, Transaction> simulator = simulators.get(simName);
		simulator.addEdge(parentName, childName, edgeLabel);
	}

	/**
	 * @requires createSimulator(simName) && addChannel(channelName)
	 *           A transaction Transaction != null
	 * @modifies channel named channelName
	 * @effects pushes the Transaction into the channel named channelName in the
	 *          simulator named simName.
	 */
	public void sendTransaction(String simName, String channelName, Transaction tx) throws
			NodeLabelDoesNotExistException,
			PipeMaxCapacityReached {
        // TODO: Implement this method
		Simulator<String, Transaction> simulator = simulators.get(simName);
		simulator.addWorkObject(channelName, tx);
    }


	/**
	 * @requires addChannel(channelName)
	 * @return a space-separated list of the Transaction values currently in the
	 *         channel named channelName in the simulator named simName.
	 */
	public String listContents(String simName, String channelName) throws
			NodeLabelDoesNotExistException {
        // TODO: Implement this method
		Simulator<String, Transaction> simulator = simulators.get(simName);
		List<Transaction> transactionValues  = simulator.getPipeByLabel(channelName).getPipeObjectsBuffer();
		String[] str = transactionValues.stream().map(Transaction::toString).toArray(String[]::new);
		// Return space-separated list
		return String.join(" ", str);
	}


	/**
	 * @requires addParticipant(participantName)
	 * @return The sum of all Transaction amount of stored products that one has in his storage buffer.
	 */
	public double getParticipantStorageAmount(String simName,
		  String participantName) throws NodeLabelDoesNotExistException {
        // TODO: Implement this method
		Simulator<String, Transaction> simulator = simulators.get(simName);
		Participant participant = (Participant)simulator.getFilterByLabel(participantName);
		int amountOfProducts = 0;
		for (Transaction transaction : participant.getFilterObjectsBuffer()) {
			amountOfProducts += transaction.getAmount();
		}
		return (double)amountOfProducts;
	}


	/**
	 * @requires addParticipant(participantName)
	 * @return The sum of all Transaction amount of waiting to be recycled products that one has.
	 */
	public double getParticipantToRecycleAmount(String simName,
			String participantName) throws NodeLabelDoesNotExistException {
        // TODO: Implement this method
		Simulator<String, Transaction> simulator = simulators.get(simName);
		Participant participant = (Participant)simulator.getFilterByLabel(participantName);
		return (double) participant.getDonationAmountAchieved();
	}



	/**
	 * @requires createSimulator(simName)
	 * @modifies simulator named simName
	 * @effects runs simulator named simName for a single time slice.
	 */
	public void simulate(String simName) throws NodeLabelDoesNotExistException {
        // TODO: Implement this method
		Simulator<String, Transaction> simulator = simulators.get(simName);
		simulator.simulate();
	}

	/**
	 * Prints the all edges.
	 *
	 * @requires simName the sim name
	 * @effects Prints the all edges.
	 */
	public void printAllEdges(String simName) throws NodeLabelDoesNotExistException {
        // TODO: Implement this method
		Simulator<String, Transaction> simulator = simulators.get(simName);
		List<String> listEdges = simulator.listEdges();
		for (String edgeLabel : listEdges) {
			System.out.print(edgeLabel + ", ");
		}
		System.out.println(" ");
	}

}
