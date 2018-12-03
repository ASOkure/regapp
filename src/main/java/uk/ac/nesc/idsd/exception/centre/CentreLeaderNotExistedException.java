package uk.ac.nesc.idsd.exception.centre;

public class CentreLeaderNotExistedException extends Exception {

    public CentreLeaderNotExistedException() {

    }

    public CentreLeaderNotExistedException(String message) {
        super(message);

    }

    public CentreLeaderNotExistedException(Throwable cause) {
        super(cause);

    }

    public CentreLeaderNotExistedException(String message, Throwable cause) {
        super(message, cause);

    }

}
