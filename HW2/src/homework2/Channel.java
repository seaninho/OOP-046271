package homework2;

import java.util.List;
import java.util.Random;

/**
 * Channel represents a channel in a Participant-Channel system.
 * Channel extends Pipe and therefore have a maximum capacity of work object
 * it can hold.
 */
public class Channel extends Pipe<String, Transaction> {
    // Abstraction function:
    // A channel extends Pipe and therefore has a label and object buffer.
    // the label is of type String and an objects buffer holds Transactions.

    // Representation Invariant:
    // A valid Pipe.

    /**
     * Channel constructor.
     *
     * @modifies this
     * @effects Constructs a channel.
     */
    public Channel(String label, int limit) throws
            PipeMaxCapacityIsNotPositive
    {
        super(label, limit);
    }

    /**
     * Simulates the channel step.
     *
     * @modifies this, graph
     * @effects Simulates a step of this channel.
     */
    @Override
    public void simulate(BipartiteGraph<String> graph) {
        if (this.getWorkObjects().size() == 0) { // No donations exist
            return;
        }

        try {
            Node<String, Transaction> node = (Node<String, Transaction>)
                    graph.getNodeByLabel(this.getPipeLabel());
            // Get participant to forward transaction to
            List<String> children = node.getNodeChildren();
            if (children.size() == 0) {
                return;
            }
            // Forward transaction to random participant
            Random random = new Random();
            String pName = children.get(random.nextInt(children.size()));
            Participant participant = (Participant)
                    graph.getNodeByLabel(pName).getNodeType().getType();

            // Deliver object to participant
            Transaction transaction = this.removeWorkObject();
            participant.addTransaction(transaction);
        } catch (NodeLabelDoesNotExistException e){
            // Channel node label does not exist
            // Should not happen since channel is a node in the graph
        }
    }
}
