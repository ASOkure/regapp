package uk.ac.nesc.idsd.exception.centre;

public class CountryNameExistException extends Exception {

    public CountryNameExistException() {

    }

    public CountryNameExistException(String message) {
        super(message);

    }

    public CountryNameExistException(Throwable cause) {
        super(cause);

    }

    public CountryNameExistException(String message, Throwable cause) {
        super(message, cause);

    }

}
