package be.pxl.hotel.domain;

public class UnsufficientMoneyException extends RuntimeException {
    public UnsufficientMoneyException(String message) {
        super(message);
    }
}