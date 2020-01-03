package homework2;

/**
 * A FilterLabelDoesNotExistException indicates graph does not contain a filter
 * holding the given label.
 */
public class FilterLabelDoesNotExistException extends Throwable {
    /**
     * The Constructor.
     *
     * @requires
     * @modifies this
     * @effects Instantiates an filter label does not exist exception.
     */
    public FilterLabelDoesNotExistException() {
        super("");
    }

    /**
     * The Constructor.
     *
     * @requires
     * @modifies this
     * @effects Instantiates an filter label does not exist exception.
     * @param msg - output string
     */
    public FilterLabelDoesNotExistException(String msg) {
        super(msg);
    }
}

