package homework2;

import java.util.ArrayList;
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
    public Channel(String label, int limit)throws PipeMaxCapacityIsNotPositive {
        super(label, limit);
    }

    /**
     * @modifies this, graph
     * @effects Simulates this channel in the graph
     */
    @Override
    public void simulate(BipartiteGraph<String> graph) {
        if (this.getWorkObjects().size() == 0) {
            return; // No donation exist
        }
        try {
            Node<String, Transaction> node = (Node<String, Transaction>)
                    graph.getNodeByLabel(this.getPipeLabel());
            // Get participant to forward transaction to
            ArrayList<String> children = (ArrayList<String>)
                    node.getNodeChildren();
            if (children.size() == 0) {
                return;
            }
            // Forward transaction to random participant
            Random random = new Random();
            String participantName =
                    (String)children.get(random.nextInt(children.size()));
            Participant participant = (Participant)
                    graph.getNodeByLabel(participantName).getNodeType().getType();

            // Deliver object to participant
            Transaction transaction = this.removeWorkObject();
            participant.addToStepBuffer(transaction);
        } catch (NodeLabelDoesNotExistException e){
            System.out.println("simulate channel exception");
        }
    }
}
