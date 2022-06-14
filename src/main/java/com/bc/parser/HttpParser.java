package com.bc.parser;

import com.bc.exception.EmptyResponseException;
import com.bc.exception.InvalidStatusLineException;
import com.bc.pojo.Response;

import java.util.List;

/**
 * Interface to be called to parse the HTTP response.
 */
public interface HttpParser {

     /**
      *  Method to be called to parse the HTTP response
      * @param httpLines list of the lines in the HTTP response
      * @return response object that contains the required HTTP contents
      * @throws InvalidStatusLineException Exception raised when the first
      * line of the HTTP response is not valid
      * @throws EmptyResponseException Exception raised when the HTTPLines list is empty
      */
     Response parseResponse(List<String> httpLines) throws InvalidStatusLineException, EmptyResponseException;
}
