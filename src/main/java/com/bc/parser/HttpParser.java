package com.bc.parser;

import com.bc.constants.ErrorConstants;
import com.bc.exception.EmptyResponseException;
import com.bc.exception.InvalidStatusLineException;
import com.bc.pojo.Response;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class HttpParser {

    private final Response response = new Response();

    public static final String STATUS_LINE_REGEX = "^HTTP/\\d\\.\\d\\s\\d\\d\\d\\s?\\S*";

    public Response parseResponse(List<String> httpLines) throws InvalidStatusLineException, EmptyResponseException {
        if (httpLines.isEmpty()) {
            throw new EmptyResponseException(ErrorConstants.EMPTY_RESPONSE_ERROR_MESSAGE);
        }

        if (validateStatusLine(httpLines)) {
            validateHeaders(httpLines);
        } else {
            throw new InvalidStatusLineException(ErrorConstants.INVALID_STATUS_LINE_ERROR_MESSAGE);
        }
        return response;
    }

    public boolean validateStatusLine(List<String> httpLines) {
        String statusLine = httpLines.get(0);
        if (!statusLine.matches(STATUS_LINE_REGEX)) {
            return false;
        }

        List<String> statusLineContents = Arrays.asList(statusLine.split("\\s"));
        response.setHttpVersion(statusLineContents.get(0).substring((statusLineContents.get(0).indexOf("/")) + 1));
        response.setHttpStatusCode(Integer.parseInt(statusLineContents.get(1)));
        if (statusLineContents.size()>2) response.setHttpStatusReason(statusLineContents.get(2));
        return true;
    }

    private void validateHeaders(List<String> httpLines) {
        Hashtable<String, String> validHeaders = new Hashtable<>();
        httpLines.remove(0);    // First line in the list is HTTP Status Line. Hence it can be removed.
        for (String httpLine : httpLines) {
            if (httpLine.contains(":")) {
                String key = httpLine.substring(0, httpLine.indexOf(":"));
                String value = httpLine.substring(httpLine.indexOf(":") + 1);
                validHeaders.put(key, value);
            }
        }
        response.setHeaders(validHeaders);
    }
}
