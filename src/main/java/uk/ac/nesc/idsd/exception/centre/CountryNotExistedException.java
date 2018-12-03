package uk.ac.nesc.idsd.exception.centre;

public class CountryNotExistedException extends Exception {

    public CountryNotExistedException() {

    }

    public CountryNotExistedException(String message) {
        super(message);

    }

    public CountryNotExistedException(Throwable cause) {
        super(cause);

    }

    public CountryNotExistedException(String message, Throwable cause) {
        super(message, cause);

    }

}
