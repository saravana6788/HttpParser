package com.bc.exception;

public class EmptyResponseException extends Exception{
    public EmptyResponseException(String errorMessage){
        super(errorMessage);
    }
}
