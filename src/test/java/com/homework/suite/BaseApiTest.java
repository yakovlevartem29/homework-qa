package com.homework.suite;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;

import com.homework.util.LogStreamProvider;
import com.homework.util.TestConfig;

import org.testng.annotations.BeforeSuite;

import static io.restassured.config.LogConfig.logConfig;

public class BaseApiTest {

    @BeforeSuite
    public void setUpRestAssured() {
        RestAssured.baseURI = TestConfig.getBaseUrl();
        LogConfig logConf = logConfig()
                .defaultStream(LogStreamProvider.initLoggingStream())
                .enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.config = RestAssured.config()
                .logConfig(logConf);
    }
}
