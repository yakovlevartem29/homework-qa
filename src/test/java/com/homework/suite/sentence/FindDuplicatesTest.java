package com.homework.suite.sentence;

import io.restassured.response.Response;


import com.homework.apiclient.SentenceClient;
import com.homework.suite.BaseApiTest;
import com.homework.util.ResponseChecker;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FindDuplicatesTest extends BaseApiTest {

    private SentenceClient sentenceClient = new SentenceClient();

    @Test(dataProviderClass = DuplicatesDataProvider.class, dataProvider = "getSentencesWithDuplicates")
    public void testSentenceServiceCanFindDuplicates(TestData data) {
        Response response = sentenceClient.getDuplicates(data.getText());
        ResponseChecker.assertStatusCode(response, HttpStatus.SC_OK);
        List<String> actualDuplicates = response.jsonPath().getList("$", String.class);
        assertThat(actualDuplicates)
                .as("Check service can find duplicates")
                .containsExactlyInAnyOrderElementsOf(data.getDuplicates());
    }

}
