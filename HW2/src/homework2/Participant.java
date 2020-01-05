package homework2;

import java.util.ArrayList;

/**
 * Participant represents a participant in a Participant-Channel system.
 * A participant is a player in the game.
 * A participant is an extend to Filter.
 */
public class Participant extends Filter<String, Transaction> {
    // Abstraction function:
    // A participant has a String to represent name and an buffer of Transaction
    // that holds all the transaction that the participant store to donate.
    // A participant has a Transaction called requiredDonation, it is the
    // transaction the participant waiting for, and DonationAmountAchieved is .
    // the amount the participant already got.
    // StepBuffer contains the Transactions from the incoming edge for the
    // current simulation step.

    // Representation Invariant:
    // requiredDonation cannot be null and requiredDonation.amount > 0.
    // StepBuffer does not contain a null object or a transaction amount of 0.

    Transaction requiredDonation;
    private int DonationAmountAchieved;
    ArrayList<Transaction> StepBuffer;



    /**
     * Participant constructor.
     *
     * @modifies this
     * @effects Constructs a participant.
     */
    public Participant(String label, Transaction requiredDonation) {
        super(label);
        this.requiredDonation = new Transaction(requiredDonation.getProduct(),
                requiredDonation.getAmount());
        this.StepBuffer = new ArrayList<>();
    }

    /**
     * Participant constructor.
     *
     * @modifies this
     * @effects Constructs a participant with exist Transactions.
     */
    public Participant(String label, Transaction requiredDonation,
                       ArrayList<Transaction> existTransactions) {
        super(label);
        this.requiredDonation = new Transaction(requiredDonation.getProduct(),
                requiredDonation.getAmount());
        this.DonationAmountAchieved = 0;
        for (Transaction transaction : existTransactions) {
            this.addWorkObject(transaction);
        }
        this.StepBuffer = new ArrayList<Transaction>();
    }

    /**
     * Stores transaction of current step (channel step).
     * The transaction is not the participants step
     * @requires transaction amount > 0
     * @param transaction Received transaction
     * @modifay this
     */
    void addToStepBuffer(Transaction transaction){
        this.StepBuffer.add(transaction);
    }

    /**
     * Load transaction of current step to ObjectBuffer
     * @modifay this
     */
    void loadStepToBuffer(){
        if (this.requiredDonation == null) {
            return;
        }
        for (Transaction transaction : StepBuffer) {
            if (this.requiredDonation.getProduct().equals(transaction.getProduct()))
            {
                /* If the transaction is needed */
                if (this.requiredDonation.getAmount() > transaction.getAmount())
                {
                    int newAmount = this.requiredDonation.getAmount() -
                            transaction.getAmount();
                    Transaction newTransaction = new
                            Transaction(transaction.getProduct(), newAmount);
                    this.requiredDonation = newTransaction;
                    this.DonationAmountAchieved =
                            transaction.getAmount();
                } else {
                    int newAmount = transaction.getAmount() -
                            this.requiredDonation.getAmount();
                    DonationAmountAchieved =
                            this.requiredDonation.getAmount();
                    this.requiredDonation = null;
                    if (newAmount > 0) {
                        Transaction newTransaction = new Transaction(
                                transaction.getProduct(), newAmount);
                        this.addWorkObject(newTransaction);
                    }
                }
            } else {
                this.addWorkObject(transaction);
            }
        }
    }

    /**
     * makeDonationSimulation simulate participants step of transfer a transaction.
     * @modifay this
     */
    private void makeDonationSimulation (BipartiteGraph graph) {
        Node<String, Transaction> currentNode;
        try {
            currentNode = graph.getNodeByLabel(this.getFilterLabel());
            ArrayList<String> childrenList;
            childrenList = (ArrayList<String>) currentNode.getNodeChildren();
            Transaction transaction = this.removeWorkObject();
            boolean transactionMovedToChannel = false;
            for (String nodeLabel : childrenList) {
                Channel channel = (Channel)
                        graph.getNodeByLabel(nodeLabel).getNodeType().getType();
                try {
                    channel.addWorkObject(transaction);
                    transactionMovedToChannel = true;
                } catch (PipeMaxCapacityReached e) {
                    /* Chanel is full, try next child */
                }
            }
            if (!transactionMovedToChannel) {
                this.addWorkObject(transaction);
            }
        } catch (NodeLabelDoesNotExistException e) {
        }
    }

    /**
     * getDonationAmountAchieved returns the amount of required transaction
     * the participant got.
     */
    public int getDonationAmountAchieved () {
        return this.DonationAmountAchieved;
    }

    /**
     * Simulates the participant step
     * @modifies this, graph
     * @effects Simulates this participant donated and received transaction
     */
    @Override
    public void simulate(BipartiteGraph graph) {
        if(this.getFilterObjectsBuffer().size() != 0) {
            makeDonationSimulation(graph);
        }
        this.loadStepToBuffer();
    }
}
