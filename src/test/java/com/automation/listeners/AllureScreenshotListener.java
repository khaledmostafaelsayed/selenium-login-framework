
package com.automation.listeners;

import com.automation.utils.ScreenshotUtils;

import io.qameta.allure.Allure;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureScreenshotListener
        implements TestLifecycleListener {

    @Override
    public void beforeTestStop(TestResult result) {

        if (result.getStatus() != Status.FAILED
                && result.getStatus() != Status.BROKEN) {
            return;
        }

        try {
            Path screenshot = ScreenshotUtils
                    .captureScreenshot(result.getName());

            try (InputStream input = Files.newInputStream(screenshot)) {

                Allure.addAttachment(
                        "Failure Screenshot",
                        "image/png",
                        input,
                        ".png");
            }

        } catch (Exception e) {
            System.err.println(
                    "Allure screenshot failed: " + e.getMessage());
        }
    }
}
