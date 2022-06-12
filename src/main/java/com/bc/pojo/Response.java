package com.bc.pojo;

import java.util.Hashtable;

public class Response {
    private String httpVersion;
    private int httpStatusCode;
    private String httpStatusReason;
    private Hashtable<String,String> headers;


    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }



    public Hashtable<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Hashtable<String, String> headers) {
        this.headers = headers;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public String getHttpStatusReason() {
        return httpStatusReason;
    }

    public void setHttpStatusReason(String httpStatusReason) {
        this.httpStatusReason = httpStatusReason;
    }
}
