package com.supernet.exceptions;

/**
 * Exception for getInstance
 */
public class GetInstanceException
        extends RuntimeException {
    /**
     * Default constructor
     *
     * @param message message
     * @param e       exception
     */
    public GetInstanceException(String message, Exception e) {
        super(message, e);
    }
}
