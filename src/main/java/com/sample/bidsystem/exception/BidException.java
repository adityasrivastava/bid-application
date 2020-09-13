package com.sample.bidsystem.exception;

public class BidException extends Exception {

    public BidException() {
        super();
    }

    public BidException(String message) {
        super(message);
    }

    public BidException(String message, Throwable cause) {
        super(message, cause);
    }

    public BidException(Throwable cause) {
        super(cause);
    }

    protected BidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
