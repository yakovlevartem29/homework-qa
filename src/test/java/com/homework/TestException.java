package com.homework;

/**
 * Signals that a test exception of some sort has occurred.
 */
public class TestException extends RuntimeException {

    public TestException(String msg) {
        super(msg);
    }

    public TestException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
