package uk.ac.nesc.idsd.service.exception;

import uk.ac.nesc.idsd.exception.DsdBaseException;
import uk.ac.nesc.idsd.exception.ErrorCodes;

public class ServiceException extends DsdBaseException {

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Exception e) {
        super(msg, e);
    }

    public ServiceException(ErrorCodes errorCode) {
        super(errorCode);
    }

}
