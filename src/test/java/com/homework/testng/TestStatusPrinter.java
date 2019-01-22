package com.homework.testng;

import com.homework.util.DurationFormatterUtil;

import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.internal.IResultListener2;

/**
 * Listener logs test status when its start and finish
 */
public class TestStatusPrinter implements IResultListener2 {

    private static final Logger LOG = LoggerFactory.getLogger(TestStatusPrinter.class.getSimpleName());
    private static final String MSG_TEMPLATE = "[{}#{}] {}";
    private static final String MSG_TEMPLATE_WITH_TIME = MSG_TEMPLATE + " in {}";
    private static final int MAX_CLASS_NAME_LENGTH = 45;
    private static final String HAS_STARTED = "has just started";
    private static final String HAS_PASSED = "is passed";
    private static final String HAS_FAILED = "is failed";
    private static final String HAS_SKIPPED = "is skipped";

    @Override
    public void onTestStart(ITestResult result) {
        logWithoutDuration(result);
    }

    private String getAbbreviatedName(ITestResult result) {
        return ClassUtils.getAbbreviatedName(result.getInstanceName(), MAX_CLASS_NAME_LENGTH);
    }

    private void printResultOnAction(ITestResult result, String action) {
        String shortClassName = getAbbreviatedName(result);
        String duration = DurationFormatterUtil.toString(result.getEndMillis() - result.getStartMillis());
        if (result.getStatus() == ITestResult.FAILURE) {
            // log result with trace on debug level to forward it main log file.
            LOG.debug(MSG_TEMPLATE_WITH_TIME, shortClassName, result.getName(), action, duration, result.getThrowable());
        }
        LOG.info(MSG_TEMPLATE_WITH_TIME, shortClassName, result.getName(), action, duration);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        printResultOnAction(result, HAS_PASSED);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        printResultOnAction(result, HAS_FAILED);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        printResultOnAction(result, HAS_SKIPPED);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
        LOG.info("Test scope [{}] with [{}] test(s) has started", context.getName(), context.getAllTestMethods().length);
    }

    @Override
    public void onFinish(ITestContext context) {
        String duration = DurationFormatterUtil.toString(context.getEndDate().getTime() - context.getStartDate().getTime());
        int passed = context.getPassedTests().size();
        int skipped = context.getSkippedTests().size();
        int failed = context.getFailedTests().size();
        LOG.info("Test scope [{}] has finished in {} with result: Passed[{}] + Failed[{}] + Skipped[{}]",
                context.getName(), duration, passed, failed, skipped);
    }

    @Override
    public void beforeConfiguration(ITestResult result) {
        logWithoutDuration(result);
    }

    private void logWithoutDuration(ITestResult result) {
        final String shortClassName = getAbbreviatedName(result);
        LOG.info(MSG_TEMPLATE, shortClassName, result.getName(), HAS_STARTED);
    }

    @Override
    public void onConfigurationSuccess(ITestResult result) {
        printResultOnAction(result, HAS_PASSED);
    }

    @Override
    public void onConfigurationFailure(ITestResult result) {
        printResultOnAction(result, HAS_FAILED);
    }

    @Override
    public void onConfigurationSkip(ITestResult result) {

    }
}
