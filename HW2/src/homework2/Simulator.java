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
     */
    public void addPipe(Pipe<L, O> pipe) {

    }

    /**
     * Add a filter to the simulator.
     *
     * @modifies this
     * @effects Adds a new filter 'filter' to the simulator.
     */
    public void addFilter(Filter<L, O> filter) {

    }

    /**
     * Add a work object to a pipe in the simulator.
     *
     * @modifies this
     * @effects Adds a work object 'object' to a pipe 'pipe' in the simulator.
     */
    public void addWorkObject(L pipeLabel, O object) {

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
