package com.bc.exception;

public class InvalidStatusLineException extends Exception{
    public InvalidStatusLineException(String errorMessage){
        super(errorMessage);
    }
}
