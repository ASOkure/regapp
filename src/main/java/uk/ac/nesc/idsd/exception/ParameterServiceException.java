package uk.ac.nesc.idsd.exception;

public class ParameterServiceException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -4492328073238914455L;

    public ParameterServiceException() {

    }

    public ParameterServiceException(String message) {
        super(message);

    }

    public ParameterServiceException(Throwable cause) {
        super(cause);

    }

    public ParameterServiceException(String message, Throwable cause) {
        super(message, cause);

    }

}
