package uk.ac.nesc.idsd.exception;

@SuppressWarnings("serial")
public class UserException extends DsdBaseException {

    public UserException(ErrorCodes errorCode) {
        super(errorCode);
    }
}
