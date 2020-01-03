package homework2;

import java.util.ArrayList;
import java.util.List;

public abstract class Pipe<L, O> implements Simulatable {
    // Abstraction function:

    // Representation Invariant:

    private L label;
    private int maxCapacity;
    private int currCapacity;
    private List<O> objectsBuffer;

    /**
     * Pipe constructor.
     *
     * @modifies this
     * @effects Constructs a pipe.
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
     * Check representation.
     *
     * @effects Checks the Representation Invariant is kept
     */
    private void checkRep() {
        assert (this.label != null) : "Pipe label cannot be null";
        assert (this.maxCapacity > 0) : "Pipe capacity limit is not positive";
    }

}
