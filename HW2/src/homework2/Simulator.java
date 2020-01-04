package homework2;

import java.util.ArrayList;
import java.util.List;

/**
 * Simulator represents a Pipe-Filter system.
 * In this system, there are 3 types of objects:
 * 1. Work objects - moving around within the system.
 * 2. Pipes - moving work objects around the system.
 * 3. Filters - process work objects arriving at their incoming pipes and
 *              then forwarding them to their outgoing pipes.
 * This system is represented by a Bipartite Graph in which each pipe is a black
 * node and each filter is a white node. This means each pipe can only be
 * connected to a filter and vice versa.
 */
public class Simulator<L, O> extends BipartiteGraph<L> {
    // Abstraction Function:
    // Simulator is a Bipartite Graph with Pipes as black nodes and
    // Filters as white nodes.

    // Representation Invariant:
    // Simulator is a valid Bipartite Graph.

    /**
     * Simulator constructor.
     *
     * @modifies this
     * @effects Constructs a new simulator.
     */
    public Simulator() {}

    /**
     * Add a pipe to the simulator.
     *
     * @modifies this
     * @effects Adds a new pipe 'pipe' to the simulator.
     * @throws NodeLabelAlreadyExistsException when a node label same as pipe's
     *         label already exists in graph.
     */
    public void addPipe(Pipe<L, O> pipe) throws
            NodeLabelAlreadyExistsException
    {
        if (pipe == null) {
            throw new NullPointerException("Pipe cannot be null");
        }
        NodeType<Pipe<L, O>> pipeNode = new NodeType<>(pipe);
        this.addBlackNode(pipe.getPipeLabel(), pipeNode);
    }

    /**
     * Add a filter to the simulator.
     *
     * @modifies this
     * @effects Adds a new filter 'filter' to the simulator.
     * @throws NodeLabelAlreadyExistsException when a node label same as filter's
     *         label already exists in graph.
     */
    public void addFilter(Filter<L, O> filter) throws
            NullPointerException,
            NodeLabelAlreadyExistsException
    {
        if (filter == null) {
            throw new NullPointerException("Filter cannot be null");
        }
        NodeType<Filter<L, O>> filterNode = new NodeType<>(filter);
        this.addWhiteNode(filter.getFilterLabel(), filterNode);
    }

    /**
     * Add a work object to a pipe in the simulator.
     *
     * @modifies this
     * @effects Adds a work object 'object' to a pipe 'pipe' in the simulator.
     * @throws PipeMaxCapacityReached when pipe's max capacity is reached and
     *         pipe can no longer hold work objects in its objects buffer.
     */
    public void addWorkObject(L pipeLabel, O object) throws
            NodeLabelDoesNotExistException,
            PipeMaxCapacityReached
    {
        if (pipeLabel == null) {
            throw new NullPointerException("Pipe label cannot be null");
        }
        if (object == null) {
            throw new NullPointerException("Work object cannot be null");
        }
        NodeType<?> pipeNode = getNodeByLabel(pipeLabel).getNodeType();
        Pipe<L, O> pipe = (Pipe<L, O>)pipeNode.getType();
        pipe.addWorkObject(object);
    }

    /**
     * Gets pipe by a given label.
     *
     * @return a pipe object labeled pLabel.
     * @throws NodeLabelDoesNotExistException when pipe label 'pLabel' does not
     *         exist in graph.
     */
    public Pipe<L, O> getPipeByLabel(L pLabel) throws
            NodeLabelDoesNotExistException
    {
        if (pLabel == null) {
            throw new NullPointerException("Pipe label cannot be null");
        }
        return (Pipe<L, O>)this.getNodeByLabel(pLabel).getNodeType().getType();
    }

    /**
     * Gets filter by a given label.
     *
     * @return a filter object labeled fLabel.
     * @throws NodeLabelDoesNotExistException when filter label 'fLabel' does
     *         not exist in graph.
     */
    public Filter<L, O> getFilterByLabel(L fLabel) throws
            NodeLabelDoesNotExistException
    {
        if (fLabel == null) {
            throw new NullPointerException("Filter label cannot be null");
        }
        return (Filter<L, O>)this.getNodeByLabel(fLabel).getNodeType().getType();
    }

    /**
     * List all pipes in the simulator.
     *
     * @return a list containing all pipes in the simulator.
     */
    public List<L> listPipes() {
        return new ArrayList<>(this.listBlackNodes());
    }

    /**
     * List all filters in the simulator.
     *
     * @return a list containing all filters in the simulator.
     */
    public List<L> listFilters() {
        return new ArrayList<>(this.listWhiteNodes());
    }

    /**
     * List all edges in the simulator.
     *
     * @return a list containing all edges in the simulator.
     * @throws NodeLabelDoesNotExistException when a pipe or filter label does
     *         not exist in graph.
     */
    public List<L> listEdges() throws NodeLabelDoesNotExistException {
        List<L> edges = new ArrayList<>();
        for (L pipeLabel : this.listPipes()) {
            Node<L, ?> pipeNode = this.getNodeByLabel(pipeLabel);
            // Add all filter->pipe edges
            edges.addAll(pipeNode.getIncomingEdges().keySet());
            // Add all pipe->filter edges
            edges.addAll(pipeNode.getOutgoingEdges().keySet());
        }
        return edges;
    }

    /**
     * Simulates one round.
     *
     * @modifies this
     * @effects Simulates one round of pipes and filter in the simulator.
     * @throws NodeLabelDoesNotExistException when a pipe or filter label does
     *         not exist in graph.
     */
    public void simulate() throws NodeLabelDoesNotExistException {
        // Simulate all pipes
        for (L pipeLabel : this.listPipes()) {
            NodeType<?> pipeNode = getNodeByLabel(pipeLabel).getNodeType();
            Pipe<L, O> pipe = (Pipe<L, O>) pipeNode.getType();
            pipe.simulate(this);
        }
        // Simulate all filters
        for (L filterLabel : this.listFilters()) {
            NodeType<?> filterNode = getNodeByLabel(filterLabel).getNodeType();
            Filter<L, O> filter = (Filter<L, O>) filterNode.getType();
            filter.simulate(this);
        }
    }

}
