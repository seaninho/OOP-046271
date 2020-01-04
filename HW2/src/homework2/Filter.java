package homework2;

import java.util.ArrayList;
import java.util.List;

/**
 * Filter represents a filter in a Pipe-Filter system.
 * A filter is an abstract type.
 * A filter has a label of type L, and objects of type O.
 */
public abstract class Filter<L, O> implements Simulatable<L> {
    // Abstraction function:
    // A filter has a label of type L and an objects buffer that holds all work
    // objects waiting to be moved.

    // Representation Invariant:
    // Filter label cannot be null.
    // Filter's objects buffer does not contain a null object.

    private L label;
    private List<O> objectsBuffer;

    /**
     * Filter constructor.
     *
     * @modifies this
     * @effects Constructs a filter.
     */
    public Filter(L label) {
        if (label == null) {
            throw new NullPointerException("Filter label cannot be null");
        }
        this.label = label;
        this.objectsBuffer = new ArrayList<>();
        checkRep();
    }

    /**
     * Gets filter label.
     *
     * @return the filter label.
     */
    public L getFilterLabel() {
        return this.label;
    }

    /**
     * Gets filter work object buffer.
     *
     * @return the filter work object buffer.
     */
    public List<O> getFilterObjectsBuffer() {
        return new ArrayList<>(this.objectsBuffer);
    }

    /**
     * Adds work object to filter.
     *
     * @modifies this
     * @effects adds a new work object to filter.
     */
    public void addWorkObject(O object) {
        checkRep();
        if (object == null) {
            throw new NullPointerException("Work object cannot be null");
        }
        this.objectsBuffer.add(object);
        checkRep();
    }

    /**
     * Removes oldest work object from filter.
     *
     * @modifies this
     * @effects removes the oldest work object from filter.
     */
    public O removeWorkObject() {
        checkRep();
        if (!this.objectsBuffer.isEmpty()) {
            O workObject = this.objectsBuffer.remove(0);
            checkRep();
            return workObject;
        } else {
            return null;
        }
    }

    /**
     * Gets filter's incoming pipes list.
     *
     * @return a list containing filter's incoming pipes.
     * @throws NodeLabelDoesNotExistException when filter label does not exist
     *         in graph.
     */
    public List<Pipe<L, O>> listIncomingPipes(BipartiteGraph<L> graph) throws
            NodeLabelDoesNotExistException
    {
        if (graph == null) {
            throw new NullPointerException("Graph cannot be null");
        }
        Node<L, ?> node = graph.getNodeByLabel(this.label);
        List<Pipe<L, O>> incomingPipes = new ArrayList<>();
        for (L pipe : graph.listParents(node.getNodeLabel())) {
            NodeType<?> nodeType = graph.getNodeByLabel(pipe).getNodeType();
            incomingPipes.add((Pipe<L, O>)nodeType.getType());
        }
        return incomingPipes;
    }

    /**
     * Gets filter's outgoing pipes list.
     *
     * @return a list containing filter's outgoing pipes.
     * @throws NodeLabelDoesNotExistException when filter label does not exist
     *         in graph.
     */
    public List<Pipe<L, O>> listOutgoingPipes(BipartiteGraph<L> graph) throws
            NodeLabelDoesNotExistException
    {
        Node<L, ?> node = graph.getNodeByLabel(this.label);
        List<Pipe<L, O>> outgoingPipes = new ArrayList<>();
        for (L pipe : graph.listChildren(node.getNodeLabel())) {
            NodeType<?> nodeType = graph.getNodeByLabel(pipe).getNodeType();
            outgoingPipes.add((Pipe<L, O>)nodeType.getType());
        }
        return outgoingPipes;
    }

    /**
     * @modifies this, graph
     * @effects Simulates this filter in a system modeled by graph
     */
    @Override
    public abstract void simulate(BipartiteGraph graph);

    /**
     * Checks for a null work object.
     *
     * @effects Checks the Representation Invariant is kept.
     * @return true if there is a null work object.
     */
    private boolean IsThereANullWorkObject() {
        for (O workObject : objectsBuffer) {
            if (workObject == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check representation.
     *
     * @effects Checks the Representation Invariant is kept
     */
    private void checkRep() {
        assert (this.label != null) : "Filter label cannot be null";
        assert (!this.IsThereANullWorkObject()) : "Work object cannot be null";
    }

}
