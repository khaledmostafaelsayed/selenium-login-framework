
package com.automation.listeners;

import com.automation.utils.ScreenshotUtils;
import com.automation.testdata.LoginDataProvider;

import java.nio.file.Path;

import org.testng.ITestListener;
import org.testng.ITestResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestListener implements ITestListener {

        private static final Logger log = LoggerFactory.getLogger(TestListener.class);

        // Get Test Name with Scenario
        private static String getTestLabel(ITestResult result) {

                for (Object parameter : result.getParameters()) {

                        if (parameter instanceof LoginDataProvider.LoginCase data) {

                                return result.getName()
                                                + " [" + data.scenario() + "]";
                        }
                }

                return result.getName();
        }

        // Test Started
        @Override
        public void onTestStart(ITestResult result) {

                log.info("TEST STARTED: {}", getTestLabel(result));
        }

        // Test Passed
        @Override
        public void onTestSuccess(ITestResult result) {

                log.info("TEST PASSED: {}", getTestLabel(result));
        }

        // Test Failed
        @Override
        public void onTestFailure(ITestResult result) {

                log.error("TEST FAILED: {}", getTestLabel(result));

                // Log failure details
                if (result.getThrowable() != null) {

                        log.error(
                                        "Failure details for " + getTestLabel(result),
                                        result.getThrowable());
                }

                // Capture screenshot
                try {

                        Path screenshotPath = ScreenshotUtils.captureScreenshot(
                                        getTestLabel(result));

                        log.info(
                                        "Screenshot saved: {}",
                                        screenshotPath.toAbsolutePath());

                } catch (Exception e) {

                        log.error(
                                        "Failed to capture screenshot for: {}",
                                        getTestLabel(result),
                                        e);
                }
        }

        // Test Skipped
        @Override
        public void onTestSkipped(ITestResult result) {

                log.warn("TEST SKIPPED: {}", getTestLabel(result));
        }
}
