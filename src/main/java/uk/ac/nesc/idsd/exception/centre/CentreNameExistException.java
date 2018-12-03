package uk.ac.nesc.idsd.exception.centre;

public class CentreNameExistException extends Exception {

    public CentreNameExistException() {

    }

    public CentreNameExistException(String message) {
        super(message);

    }

    public CentreNameExistException(Throwable cause) {
        super(cause);

    }

    public CentreNameExistException(String message, Throwable cause) {
        super(message, cause);

    }

}
