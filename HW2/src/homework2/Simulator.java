package homework2;

import java.util.List;

public class Simulator<L, O> extends BipartiteGraph<L> {
    // Abstraction function:

    // Representation Invariant:

    public Simulator() {}

    /**
     * Add a pipe to the simulator.
     *
     * @modifies this
     * @effects Adds a new pipe 'pipe' to the simulator.
     * @throws NullPointerException when pipe 'pipe' is null.
     * @throws NodeLabelAlreadyExistsException when a node label same as pipe's
     *          label already exists in graph.
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
     * @throws NullPointerException when filter 'filter' is null.
     * @throws NodeLabelAlreadyExistsException when a node label same as filter's
     *          label already exists in graph.
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
     * @throws NullPointerException when
     * @throws PipeMaxCapacityReached
     */
    public void addWorkObject(L pipeLabel, O object) throws
            NodeLabelDoesNotExistException,
            PipeMaxCapacityReached
    {
        if (pipeLabel == null) {

        }
        if (object == null) {

        }
        NodeType<?> pipeNode = getNodeByLabel(pipeLabel).getNodeType();
        Pipe<L, O> pipe = (Pipe<L, O>) pipeNode.getType();
        pipe.addWorkObject(object);
    }

    /**
     * Gets pipe by a given label.
     *
     * @return a pipe object labeled pipeLabel.
     */
    public Pipe<L, O> getPipeByLabel(L pipeLabel) {

    }

    /**
     * Gets filter by a given label.
     *
     * @return a filter object labeled filterLabel.
     */
    public Filter<L, O> getFilterByLabel(L filterLabel) {

    }

    /**
     * List all pipes in the simulator.
     *
     * @return a list containing all pipes in the simulator.
     */
    public List<L> listPipes() {

    }

    /**
     * List all filters in the simulator.
     *
     * @return a list containing all filters in the simulator.
     */
    public List<L> listFilters() {

    }

    /**
     * List all edges in the simulator.
     *
     * @return a list containing all edges in the simulator.
     */
    public List<L> listEdges() {

    }

}
