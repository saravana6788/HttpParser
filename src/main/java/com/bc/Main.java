package com.bc;

import com.bc.exception.EmptyResponseException;
import com.bc.exception.InvalidStatusLineException;
import com.bc.parser.HttpParser;
import com.bc.parser.HttpParserImpl;
import com.bc.pojo.Response;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final String FILE_PATH = "./src/main/java/com/bc/file/content.txt";
    public static void main(String[] args) {

        BufferedReader reader;
        String currentLine;
        List<String> httpLines = new ArrayList<>();
        try{
            reader = new BufferedReader(new FileReader(FILE_PATH));
            while((currentLine = reader.readLine()) != null){
                httpLines.add(currentLine);
            }

            HttpParser parser = new HttpParserImpl();
            Response response = parser.parseResponse(httpLines);
            System.out.printf("HTTP version: %s%n",response.getHttpVersion());
            System.out.printf("Status: %d%n",response.getHttpStatusCode());
            System.out.printf("Number of valid headers: %d%n",response.getHeaders().size());
            System.out.printf("Number of invalid headers: %d%n",(httpLines.size() - response.getHeaders().size()));

        } catch (IOException | InvalidStatusLineException | EmptyResponseException e) {
            System.out.println(e.getMessage());
        }

    }
}
