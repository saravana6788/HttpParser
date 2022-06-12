package com.bc.exception;

/**
 * Exception raised when there is an invalid Status line in the HTTP response
 */
public class InvalidStatusLineException extends Exception{
    public InvalidStatusLineException(String errorMessage){
        super(errorMessage);
    }
}
