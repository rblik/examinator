package com.db.schooolexaminator.controllers;

import com.db.schooolexaminator.model.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

/**
 * Created by fedor on 02.09.2016.
 */
public class Main {
    private static final String HARDCODED_JSON = "{\"title\":\"TestTitle\"," +
            "\"emails\":[\"abc@gmail.com\",\" def@adf.com\"]," +
            "\"frameRows\":\"4\"," +
            "\"frameCols\":\"3\"," +
            "\"operationConstraints\":[" +
            "{\"sign\":\"+\",\"minAnswer\":\"1\",\"maxAnswer\":\"10\",\"minA\":\"1\",\"maxA\":\"10\",\"exceptA\":[\"2\",\"3\"],\"minB\":\"1\",\"maxB\":\"10\"}," +
            "{\"sign\":\"-\",\"allowedNegativeAnswer\":\"true\",\"minA\":\"1\",\"maxA\":\"10\",\"exceptA\":[\"2\",\"3\"],\"minB\":\"1\",\"maxB\":\"10\"}," +
            "{\"sign\":\"*\",\"minAnswer\":\"1\",\"maxAnswer\":\"10\",\"minA\":\"1\",\"maxA\":\"10\",\"exceptA\":[\"2\",\"3\"],\"minB\":\"1\",\"maxB\":\"10\"}," +
            "{\"sign\":\"/\",\"minAnswer\":\"1\",\"maxAnswer\":\"10\",\"divisionWithoutRemainder\":\"true\",\"minA\":\"1\",\"maxA\":\"10\",\"exceptA\":[\"2\",\"3\"],\"minB\":\"1\",\"maxB\":\"10\"}" +
            "]}";

    @SneakyThrows
    public static void main(String[] args) {
        String configurationJson = HARDCODED_JSON;
        ObjectMapper mapper = new ObjectMapper();
        Configuration configuration = mapper.readValue(configurationJson, Configuration.class);
        System.out.println(configuration);
    }
}



//"{\"title\":\"TestTitle\",\"emails\":[\"abc@gmail.com\",\" def@adf.com\"],\"frameRows\":\"4\",\"frameCols\":\"3\",\"operationConstraints\":[{\"sign\":\"+\",\"minAnswer\":\"1\",\"maxAnswer\":\"10\",\"minA\":\"1\",\"maxA\":\"10\",\"exceptA\":[\"2\",\"3\"],\"minB\":\"1\",\"maxB\":\"10\"},{\"sign\":\"-\",\"allowedNegativeAnswer\":\"true\",\"minA\":\"1\",\"maxA\":\"10\",\"exceptA\":[\"2\",\"3\"],\"minB\":\"1\",\"maxB\":\"10\"},{\"sign\":\"*\",\"minAnswer\":\"1\",\"maxAnswer\":\"10\",\"minA\":\"1\",\"maxA\":\"10\",\"exceptA\":[\"2\",\"3\"],\"minB\":\"1\",\"maxB\":\"10\"},{\"sign\":\"/\",\"minAnswer\":\"1\",\"maxAnswer\":\"10\",\"divisionWithoutRemainder\":\"true\",\"minA\":\"1\",\"maxA\":\"10\",\"exceptA\":[\"2\",\"3\"],\"minB\":\"1\",\"maxB\":\"10\"}]}"
