package com.homework.util;

import io.restassured.response.Response;


import com.homework.TestException;

import org.hamcrest.collection.IsIn;

/**
 * Utility class for response validation
 */
public class ResponseChecker {

    private ResponseChecker() {
    }

    /**
     * Checks if response code is expected one
     *
     * @throws TestException with response body if response code is unexpected
     */
    public static void assertStatusCode(Response response, Integer... expectedCodes) {
        response.then().statusCode(IsIn.isOneOf(expectedCodes));
    }
}
