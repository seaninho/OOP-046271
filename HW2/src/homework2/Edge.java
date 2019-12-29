package homework2;

/**
 * This class represents a directed edge in a Bipartite Graph.
 * There can be more than one edge with the same label in a graph.
 * Each Edge has a label, a source node label and a destination node label
 * of generic type L.
 */
public class Edge<L> {
    //	 Abstraction function:
    //	 An edge is represented by its label, and its source and destination
    //	 nodes' labels: srcNodeLabel, destNodeLabel.
    //
    //	 Representation Invariant:
    //	 An edge cannot connect a node to itself.
    //	 label, srcNodeLabel and dstNodeLabel cannot be null.

    private final L label;
    private L srcNodeLabel;
    private L dstNodeLabel;

    /**
     * Edge constructor.
     *
     * @modifies this
     * @effects Instantiates a new edge.
     * @throws NullPointerException when invoked with null parameters
     * @throws SrcNodeIsSameAsDstNodeException when invoked with null parameters
     *
     */
    public Edge(L label, L srcNodeLabel, L dstNodeLabel)
            throws SrcNodeIsSameAsDstNodeException {
        if (label == null || srcNodeLabel == null || dstNodeLabel == null) {
            throw new NullPointerException();
        }
        if (srcNodeLabel == dstNodeLabel) {
            throw new SrcNodeIsSameAsDstNodeException();
        }
        this.label = label;
        this.srcNodeLabel = srcNodeLabel;
        this.dstNodeLabel = dstNodeLabel;
        checkRep();
    }

    /**
     * Get edge label.
     *
     * @effects Returns the edge label
     */
    public L getLabel () {
        return this.label;
    }

    /**
     * Get edge source node label.
     *
     * @effects Returns the edge source node label
     */
    public L getSrcNodeLabel () {
        return this.srcNodeLabel;
    }

    /**
     * Get edge destination node label.
     *
     * @effects Returns the edge destination node label
     */
    public L getDstNodeLabel () {
        return this.dstNodeLabel;
    }


    /**
     * Check representation.
     *
     * @effects Checks the Representation Invariant is kept
     */
    private void checkRep() {
        assert (this.label != null) :
                "Edge label cannot be null";
        assert (this.srcNodeLabel != null) :
                "Edge Source Node label cannot be null";
        assert (this.dstNodeLabel != null) :
                "Edge Destination Node label cannot be null";
    }
}
