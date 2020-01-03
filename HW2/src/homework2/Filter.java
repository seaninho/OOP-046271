package homework2;

import java.util.ArrayList;
import java.util.List;

public abstract class Filter<L, O> implements Simulatable<L> {
    // Abstraction function:

    // Representation Invariant:

    private L label;
    private List<O> objectsBuffer;


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

    public List<Pipe<L, O>> listIncomingPipes(BipartiteGraph<L> graph) throws
            FilterLabelDoesNotExistException
    {
        try {
            Node<L, ?> node = graph.getNodeByLabel(this.label);
            if (node.getNodeType().getType() != this) {
                throw new
            }
            List<Pipe<L, O>> incomingPipes = new ArrayList<>();
            for (L pipe : graph.listParents(this.label)) {
                NodeType<?> nodeType = graph.getNodeByLabel(pipe).getNodeType();
                incomingPipes.add((Pipe<L, O>)nodeType.getType());
            }
            return incomingPipes;
        } catch (NodeLabelDoesNotExistException e) {
            throw new FilterLabelDoesNotExistException();
        }
    }

    public List<Pipe<L, O>> listOutgoingPipes(BipartiteGraph<L> graph) throws
            FilterLabelDoesNotExistException
    {
        try {
            Node<L, ?> node = graph.getNodeByLabel(this.label);
            if (node.getNodeType().getType() != this) {
                throw new
            }
            List<Pipe<L, O>> outgoingPipes = new ArrayList<>();
            for (L pipe : graph.listChildren(this.label)) {
                NodeType<?> nodeType = graph.getNodeByLabel(pipe).getNodeType();
                outgoingPipes.add((Pipe<L, O>)nodeType.getType());
            }
            return outgoingPipes;
        } catch (NodeLabelDoesNotExistException e) {
            throw new FilterLabelDoesNotExistException();
        }
    }

    /**
     * Check representation.
     *
     * @effects Checks the Representation Invariant is kept
     */
    private void checkRep() {
        assert (this.label != null) : "Filter label cannot be null";
    }

}
