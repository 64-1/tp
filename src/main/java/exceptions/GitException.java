package exceptions;

/**
 * Represents abstract superclass for GiT-specific exceptions.
 */
public abstract class GitException extends Exception {
    protected String message;

    /**
     * Constructs WallybotException.
     */
    public GitException() {}

    /**
     * Returns error message.
     */
    public String getMessage() {
        return message;
    }
}
