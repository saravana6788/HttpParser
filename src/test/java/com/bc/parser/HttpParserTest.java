package com.bc.parser;

import com.bc.exception.EmptyResponseException;
import com.bc.exception.InvalidStatusLineException;
import com.bc.pojo.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HttpParserTest {

    private HttpParser parser;
    @BeforeEach
    void setUp() {
        parser = new HttpParser();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void parseResponseSuccessTest() throws Exception {
        List<String> httpLines = new ArrayList<>();
        httpLines.add("HTTP/1.1 302 Found");
        httpLines.add("cache-control: public");
        httpLines.add("content-length: 0");
        httpLines.add("content-type: image/svg+xml");
        httpLines.add("date: Tue, 22 Jun 2021 22:24:42 GMT");

        Response response = parser.parseResponse(httpLines);
        assertEquals("1.1",response.getHttpVersion());
        assertEquals(302,response.getHttpStatusCode());
        assertEquals(4,response.getHeaders().size());
    }

    @Test
    void invalidStatusLineTest(){
        List<String> httpLines = new ArrayList<>();
        httpLines.add("Invalid_status_line");
        httpLines.add("cache-control: public");
        httpLines.add("content-length: 0");
        httpLines.add("content-type: image/svg+xml");
        httpLines.add("date: Tue, 22 Jun 2021 22:24:42 GMT");
        assertThrows(InvalidStatusLineException.class,()->parser.parseResponse(httpLines));
    }

    @Test
    void emptyHttpResponseTest(){
        List<String> httpLines = new ArrayList<>();
        assertThrows(EmptyResponseException.class,()->parser.parseResponse(httpLines));
    }

    @Test
    void noHeaderTest() throws Exception {
        List<String> httpLines = new ArrayList<>();
        httpLines.add("HTTP/1.1 302 Found");
        Response response = parser.parseResponse(httpLines);
        assertEquals(0, response.getHeaders().size());
        assertEquals("1.1", response.getHttpVersion());
        assertEquals(302, response.getHttpStatusCode());
    }

    @Test
    void noHttpStatusReasonTest() throws Exception {
        List<String> httpLines = new ArrayList<>();
        httpLines.add("HTTP/1.1 302");
        Response response = parser.parseResponse(httpLines);
        assertEquals(0, response.getHeaders().size());
        assertEquals("1.1", response.getHttpVersion());
        assertEquals(302, response.getHttpStatusCode());
        assertEquals(null,response.getHttpStatusReason());
    }


}