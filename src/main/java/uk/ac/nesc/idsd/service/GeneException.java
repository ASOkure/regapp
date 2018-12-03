package uk.ac.nesc.idsd.service;

public class GeneException extends Exception {

    public GeneException() {
    }

    public GeneException(String message) {
        super(message);
    }

    public GeneException(Throwable cause) {
        super(cause);
    }

    public GeneException(String message, Throwable cause) {
        super(message, cause);
    }

}
