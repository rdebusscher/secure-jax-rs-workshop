package be.rubus.jaxrs.secure.workshop.exception;

public class UnexpectedException extends RuntimeException {
    public UnexpectedException(Exception e) {
        super(e);
    }
}
