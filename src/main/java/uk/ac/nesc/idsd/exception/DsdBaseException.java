package uk.ac.nesc.idsd.exception;

public class DsdBaseException extends RuntimeException {

    private int id;
    private String message;

    public DsdBaseException(String msg) {
        super(msg);
    }

    public DsdBaseException(String msg, Exception e) {
        super(msg, e);
    }

    public DsdBaseException(ErrorCodes errorCode) {
        super(errorCode.toString());
        this.id = errorCode.getId();
        this.message = errorCode.getMessage();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
