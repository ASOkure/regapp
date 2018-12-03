package uk.ac.nesc.idsd.service.exception;

public class NoneCheckedServiceException extends RuntimeException {

    public NoneCheckedServiceException(String msg) {
        super(msg);
    }

    public NoneCheckedServiceException(String msg, Exception e) {
        super(msg, e);
    }

}
