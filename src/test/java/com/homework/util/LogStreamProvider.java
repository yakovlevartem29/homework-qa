package com.homework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Utility class for RestAssured logging configuration.
 */
public class LogStreamProvider {

    /**
     * Logger name which is used by logger xml configuration file
     */
    private static final String IO_RESTASSURED_MESSAGES = "io.restassured.messages";

    private static final Logger LOGGER = LoggerFactory.getLogger(IO_RESTASSURED_MESSAGES);

    /**
     * Initiates new stream for RestAssured
     * @return PrintStream new stream
     */
    public static PrintStream initLoggingStream() {
        OutputStream output = new OutputStream() {
            private StringBuilder myStringBuilder = new StringBuilder();

            @Override
            public void write(int b) {
                myStringBuilder.append((char) b);
            }

            @Override
            public void flush() {
                synchronized (this) {
                    String print = myStringBuilder.toString();
                    if (!print.trim().isEmpty()) { // Ignore empty msgs
                        LOGGER.info(print);
                    }
                    myStringBuilder = new StringBuilder();
                }
            }
        };

        return new PrintStream(output, true);
    }
}
