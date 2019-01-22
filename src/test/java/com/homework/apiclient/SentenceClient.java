package com.homework.apiclient;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Sentence api client
 */
public class SentenceClient {

    private static final String BASE_PATH = "api/sentence/duplicates";

    /**
     * Sends GET request with sentence query parameter to find duplicated words.
     *
     * @param sentence String sentence parameter value
     * @return the response of the request
     */
    public Response getDuplicates(String sentence) {
        RequestSpecification requestSpecification = RestAssured.given()
                .basePath(BASE_PATH);
        if (sentence != null) {
            requestSpecification.queryParam("sentence", sentence);
        }
        return requestSpecification.get();
    }
}
