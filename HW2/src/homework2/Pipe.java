package homework2;

import java.util.ArrayList;
import java.util.List;


/**
 * Pipe represents a pipe in a Pipe-Filter system.
 * A Pipe is an abstract type.
 * Pipes have a maximum capacity of work object they can hold.
 * Pipes have FIFO behavior and do not modify the items passing through them.
 * A pipe has a label of type L, and objects of type O.
 */
public abstract class Pipe<L, O> implements Simulatable {
    // Abstraction function:
    // A pipe has a label of type L, a positive max capacity of work object it
    // can hold in its objects buffer given in initialization, a current
    // capacity that is affected from the number of work objects currently in
    // its buffer and an objects buffer that holds all work objects currently in
    // the pipe waiting to be moved.

    // Representation Invariant:
    // Pipe label cannot be null.
    // Pipe's max capacity must be positive, i.e. maxCapacity > 0.
    // Pipe's objects buffer does not contain a null object.

    private L label;
    private int maxCapacity;
    private int currCapacity;
    private List<O> objectsBuffer;

    /**
     * Pipe constructor.
     *
     * @modifies this
     * @effects Constructs a pipe.
     * @throws PipeMaxCapacityIsNotPositive when pipe's limit capacity is not
     *         a positive int.
     */
    public Pipe(L label, int limit) throws PipeMaxCapacityIsNotPositive {
        if (label == null) {
            throw new NullPointerException("Pipe label cannot be null");
        }
        if (limit <= 0) {
            throw new PipeMaxCapacityIsNotPositive();
        }
        this.label = label;
        this.maxCapacity = limit;
        this.currCapacity = limit;
        this.objectsBuffer = new ArrayList<>();
        checkRep();
    }

    /**
     * Gets pipe label.
     *
     * @return the pipe label.
     */
    public L getPipeLabel() {
        return this.label;
    }

    /**
     * Gets pipe current capacity.
     *
     * @return the pipe current capacity.
     */
    public int getPipeCurrentCapacity() {
        return this.currCapacity;
    }

    /**
     * Gets pipe work object buffer.
     *
     * @return the pipe work object buffer.
     */
    public List<O> getPipeObjectsBuffer() {
        return new ArrayList<>(objectsBuffer);
    }

    /**
     * Adds work object to pipe.
     *
     * @modifies this
     * @effects adds a new work object to pipe.
     * @throws PipeMaxCapacityReached when pipe's max capacity is reached and
     *         pipe can no longer hold work objects in its objects buffer.
     */
    public void addWorkObject(O object) throws PipeMaxCapacityReached {
        checkRep();
        if (object == null) {
            throw new NullPointerException("Work object cannot be null");
        }
        if (this.currCapacity == 0) {
            throw new PipeMaxCapacityReached();
        }
        this.objectsBuffer.add(object);
        this.currCapacity--;
        checkRep();
    }

    /**
     * Removes oldest work object from pipe.
     *
     * @modifies this
     * @effects removes the oldest work object from pipe.
     */
    public O removeWorkObject() {
        checkRep();
        if (!this.objectsBuffer.isEmpty()) {
            O workObject = this.objectsBuffer.remove(0);
            this.currCapacity++;
            checkRep();
            return workObject;
        } else {
            return null;
        }
    }

    /**
     * @modifies this, graph
     * @effects Simulates this pipe in a system modeled by graph
     */
    @Override
    public abstract void simulate(BipartiteGraph graph);

    /**
     * Check representation.
     *
     * @effects Checks the Representation Invariant is kept
     */
    private void checkRep() {
        assert (this.label != null) : "Pipe label cannot be null";
        assert (this.maxCapacity > 0) : "Pipe capacity limit is not positive";
    }

}
