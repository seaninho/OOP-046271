package homework2;

public class Edge<L> {
    // Abstraction function:

    // Representation Invariant:

    private final L label;
    private L srcNodeLabel;
    private L dstNodeLabel;

    /**
     * Constructor.
     *
     * @modifies this
     * @effects Instantiates a new edge.
     * @throws BadGraphHandleException
     *             when invoked with null parameters
     */
    public Edge(L label, L srcNodeLabel, L dstNodeLabel) {
        if (label == null || srcNodeLabel == null || dstNodeLabel == null) {
            throw new BadGraphHandleException("Edge constractor faild, " +
                    "arguments cannot be null");
        }
        this.label = label;
        this.srcNodeLabel = srcNodeLabel;
        this.dstNodeLabel = dstNodeLabel;
        checkRep();
    }

    public L getLabel () {
        return this.label;
    }

    public L getSrcNodeLabel () {
        return this.srcNodeLabel;
    }

    public L getDstNodeLabel () {
        return this.dstNodeLabel;
    }


    /**
     * Check rep.
     *
     * @effects Checks the Representation Invariant is kept
     */
    private void checkRep() {

    }
}
