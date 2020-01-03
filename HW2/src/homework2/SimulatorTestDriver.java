package homework2;

import java.util.Map;

/**
 * This class implements a testing driver for Simulator. The driver manages
 * Simulators for recycling channels
 */
public class SimulatorTestDriver {

	private Map<String, Filter<String, Transaction>> simulators;

	/**
	 * @modifies this
	 * @effects Constructs a new test driver.
	 */
	public SimulatorTestDriver() {
        // TODO: Implement this constructor
	}

	/**
	 * @requires simName != null
	 * @modifies this
	 * @effects Creates a new simulator named simName. The simulator's graph is
	 *          initially empty.
	 */
	public void createSimulator(String simName) {
	    // TODO: Implement this method
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
	public void addChannel(String simName, String channelName, int limit) {
	    // TODO: Implement this method
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
	public void addParticipant(String simName, String participantName, String product, int amount) {
        // TODO: Implement this method
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
	public void addEdge(String simName, String parentName, String childName, String edgeLabel) {
        // TODO: Implement this method
	}

	/**
	 * @requires createSimulator(simName) && addChannel(channelName)
	 *           A transaction Transaction != null
	 * @modifies channel named channelName
	 * @effects pushes the Transaction into the channel named channelName in the
	 *          simulator named simName.
	 */
	public void sendTransaction(String simName, String channelName, Transaction tx) {
        // TODO: Implement this method
    }


	/**
	 * @requires addChannel(channelName)
	 * @return a space-separated list of the Transaction values currently in the
	 *         channel named channelName in the simulator named simName.
	 */
	public String listContents(String simName, String channelName) {
        // TODO: Implement this method
	}


	/**
	 * @requires addParticipant(participantName)
	 * @return The sum of all Transaction amount of stored products that one has in his storage buffer.
	 */
	public double getParticipantStorageAmount(String simName, String participantName) {
        // TODO: Implement this method
	}


	/**
	 * @requires addParticipant(participantName)
	 * @return The sum of all Transaction amount of waiting to be recycled products that one has.
	 */
	public double getParticipantToRecycleAmount(String simName, String participantName) {
        // TODO: Implement this method
	}



	/**
	 * @requires createSimulator(simName)
	 * @modifies simulator named simName
	 * @effects runs simulator named simName for a single time slice.
	 */
	public void simulate(String simName) {
        // TODO: Implement this method
	}

	/**
	 * Prints the all edges.
	 *
	 * @requires simName the sim name
	 * @effects Prints the all edges.
	 */
	public void printAllEdges(String simName) {
        // TODO: Implement this method
	}

}
