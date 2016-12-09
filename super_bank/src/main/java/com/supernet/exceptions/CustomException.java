package com.supernet.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Base class for custom exceptions
 */
public abstract class CustomException
        extends RuntimeException {

    public abstract String getKey();

    public abstract HttpStatus getStatus();
}
