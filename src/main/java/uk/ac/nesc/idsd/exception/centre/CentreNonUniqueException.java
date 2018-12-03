package uk.ac.nesc.idsd.exception.centre;

public class CentreNonUniqueException extends Exception {

    public CentreNonUniqueException() {

    }

    public CentreNonUniqueException(String message) {
        super(message);

    }

    public CentreNonUniqueException(Throwable cause) {
        super(cause);

    }

    public CentreNonUniqueException(String message, Throwable cause) {
        super(message, cause);

    }

}
