package com.homework.suite;

import io.restassured.RestAssured;
import io.restassured.response.Response;


import com.homework.util.ResponseChecker;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;

public class ApplicationHealthCheckTest extends BaseApiTest {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationHealthCheckTest.class);

    /**
     * Heroku platform stops unused apps, so wake it up before actual tests
     * https://devcenter.heroku.com/articles/free-dyno-hours#dyno-sleeping
     */
    @BeforeSuite
    public void wakeUpHerokuApp() {
        LOG.info("Checking if app is available [url={}]...", RestAssured.baseURI);
        Response response = RestAssured.get();
        ResponseChecker.assertStatusCode(response, HttpStatus.SC_OK);
        LOG.info("App is up and running");
    }
}
