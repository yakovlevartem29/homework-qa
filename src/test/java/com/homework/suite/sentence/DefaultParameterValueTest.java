package com.homework.suite.sentence;

import io.restassured.response.Response;


import com.homework.util.ResponseChecker;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultParameterValueTest extends AbstractSentenceTest {

    @Test
    public void testSentenceServiceCanHandleMissingQueryParameter() {
        Response response = sentenceClient.getDuplicates(null);
        ResponseChecker.assertStatusCode(response, HttpStatus.SC_OK);
        List<String> actualDuplicates = response.jsonPath().getList("$", String.class);
        assertThat(actualDuplicates)
                .as("Check empty response for missing parameter")
                .isEmpty();
    }
}
