package uk.ac.nesc.idsd.exception.centre;

public class CentreNotExistedException extends Exception {

    public CentreNotExistedException() {

    }

    public CentreNotExistedException(String message) {
        super(message);

    }

    public CentreNotExistedException(Throwable cause) {
        super(cause);

    }

    public CentreNotExistedException(String message, Throwable cause) {
        super(message, cause);

    }

}
