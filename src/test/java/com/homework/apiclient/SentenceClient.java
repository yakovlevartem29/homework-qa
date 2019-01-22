package com.homework.apiclient;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * Sentence api client
 */
public class SentenceClient {

    private static final String BASE_PATH = "api/sentence/";

    /**
     * Sends GET request with sentence query parameter to find duplicated words.
     *
     * @param sentence String sentence parameter value
     * @return the response of the request
     */
    public Response getDuplicates(String sentence) {
        return RestAssured.given()
                .basePath(BASE_PATH).queryParam("sentence", sentence)
                .get("duplicates");
    }
}
