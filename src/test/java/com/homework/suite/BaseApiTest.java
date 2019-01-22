package com.homework.suite;

import io.restassured.RestAssured;

import com.homework.util.TestConfig;

import org.testng.annotations.BeforeSuite;

public class BaseApiTest {

    @BeforeSuite
    public void setUpRestAssured() {
        RestAssured.baseURI = TestConfig.getBaseUrl();
    }
}
