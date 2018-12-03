package uk.ac.nesc.idsd.exception;

public class DataException extends DsdBaseException {

    public DataException(String msg) {
        super(msg);
    }

    public DataException(String msg, Exception e) {
        super(msg, e);
    }

    public DataException(ErrorCodes errorCode) {
        super(errorCode);
    }
}
