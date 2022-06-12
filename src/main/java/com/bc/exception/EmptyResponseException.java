package com.bc.exception;

/**
 *  Exception that is raised when an empty response is received
 */

public class EmptyResponseException extends Exception{
    public EmptyResponseException(String errorMessage){
        super(errorMessage);
    }
}
