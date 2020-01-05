package homework2;

import java.util.ArrayList;
import java.util.List;

/**
 * Participant represents a participant in a Participant-Channel system.
 * A participant extends Filter.
 */
public class Participant extends Filter<String, Transaction> {
    // Abstraction function:
    // A participant has a String to represent name and a buffer of Transaction
    // that holds all the transaction that the participant store to donate.
    // A participant has a Transaction called requiredDonation, it is the
    // transaction the participant waiting for, and DonationAmountAchieved is .
    // the amount the participant already got.
    // StepBuffer contains the Transactions from the incoming edge for the
    // current simulation step.

    // Representation Invariant:
    // A valid Filter.
    // requiredDonation != null
    // requiredDonation.amount > 0.
    // For each transaction in transactionsBuffer:
    //      1. transaction != null
    //      2. transaction.amount != 0.

    private Transaction requiredDonation;
    private int donationAmountAchieved;
    private ArrayList<Transaction> transactionsBuffer;

    /**
     * Participant constructor.
     *
     * @modifies this
     * @effects Constructs a participant.
     */
    public Participant(String label, Transaction requiredDonation) {
        super(label);
        if (requiredDonation == null) {
            throw new NullPointerException("Required donation cannot be null");
        }
        this.requiredDonation = new Transaction(requiredDonation.getProduct(),
                requiredDonation.getAmount());
        this.transactionsBuffer = new ArrayList<>();
    }

    /**
     * Participant constructor.
     * @param existTransactions is given Transactions to add to storage
     * @modifies this
     * @effects Constructs a participant with given Transactions.
     */
    public Participant(String label, Transaction requiredDonation,
                       ArrayList<Transaction> existTransactions) {
        super(label);
        if (requiredDonation == null) {
            throw new NullPointerException("Required donation cannot be null");
        }
        if (existTransactions == null) {
            throw new NullPointerException("Existing donations cannot be null");
        }
        this.requiredDonation = new Transaction(requiredDonation.getProduct(),
                requiredDonation.getAmount());
        this.donationAmountAchieved = 0;
        for (Transaction transaction : existTransactions) {
            this.addWorkObject(transaction);
        }
        this.transactionsBuffer = new ArrayList<Transaction>();
    }

    /**
     * Returns the amount of required transaction
     * the participant got.
     *
     * @return the donation amount achieved.
     */
    public int getDonationAmountAchieved() {
        return this.donationAmountAchieved;
    }

    /**
     * Stores transaction of current step (channel step).
     * The transaction is not the participants step
     * @requires transaction amount > 0
     * @param transaction Received transaction
     * @modifay this
     */
    public void addTransaction(Transaction transaction){
        if (transaction == null) {
            throw new NullPointerException("Transaction cannot be null");
        }
        this.transactionsBuffer.add(transaction);
    }

    /**
     * Simulates the participant step.
     *
     * @modifies this, graph
     * @effects Simulates a step of this participant.
     */
    @Override
    public void simulate(BipartiteGraph graph) {
        if (this.getFilterObjectsBuffer().size() == 0) { // No donations exist
            this.processTransactionsBuffer();
            return;
        }

        try {
            Node<String, Transaction> node = (Node<String, Transaction>)
                    graph.getNodeByLabel(this.getFilterLabel());
            Transaction transaction = this.removeWorkObject();
            boolean transactionMovedToChannel = false;
            List<String> childrenList = node.getNodeChildren();
            for (String nLabel : childrenList) {
                Channel channel = (Channel)
                        graph.getNodeByLabel(nLabel).getNodeType().getType();
                try {
                    channel.addWorkObject(transaction);
                    transactionMovedToChannel = true;
                } catch (PipeMaxCapacityReached e) {
                    // Channel is full, try next child
                }
            }
            if (!transactionMovedToChannel) {
                this.addWorkObject(transaction);
            }
        } catch (NodeLabelDoesNotExistException e) {
            // Participant node label does not exist
            // Should not happen since participant is a node in the graph
        }

        this.processTransactionsBuffer();
    }

    /**
     * Processes transaction.
     *
     * @modifies this
     * @effects Determines if transaction is needed and operates accordingly.
     */
    private void processTransaction(Transaction transaction) {
        // Transaction is needed
        if (this.requiredDonation.getAmount() > transaction.getAmount())
        {
            int newAmount =
                    this.requiredDonation.getAmount() - transaction.getAmount();
            this.donationAmountAchieved = transaction.getAmount();
            this.requiredDonation =
                    new Transaction(transaction.getProduct(), newAmount);
        }
        // Transaction is not needed
        else {
            int newAmount =
                    transaction.getAmount() - this.requiredDonation.getAmount();
            this.donationAmountAchieved = this.requiredDonation.getAmount();
            this.requiredDonation =
                    new Transaction(transaction.getProduct(), 0);
            if (newAmount > 0) {
                Transaction newTransaction =
                        new Transaction(transaction.getProduct(), newAmount);
                this.addWorkObject(newTransaction);
            }
        }
    }

    /**
     * Processes transactions in transactions buffer.
     *
     * @modifies this
     * @effects Goes through transactions buffer and processes each transaction.
     */
    private void processTransactionsBuffer() {
        for (Transaction transaction : transactionsBuffer) {
            String product = transaction.getProduct();
            if (this.requiredDonation.getProduct().equals(product))
            {
                this.processTransaction(transaction);
            } else {
                this.addWorkObject(transaction);
            }

        }

        transactionsBuffer.clear();
    }

//    private void checkRep {
//
//    }
}
