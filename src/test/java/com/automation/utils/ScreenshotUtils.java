
package com.automation.utils;

import com.automation.driver.DriverManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class ScreenshotUtils {

    private ScreenshotUtils() {
    }

    public static Path captureScreenshot(String testName)
            throws IOException {

        WebDriver driver = DriverManager.getDriver();

        // Create screenshots folder
        Path screenshotDirectory = Paths.get("target", "screenshots");

        Files.createDirectories(screenshotDirectory);

        // Generate unique screenshot name
        String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));

        String safeTestName = testName.replaceAll("[^a-zA-Z0-9_-]", "_");

        String fileName = safeTestName + "_"
                + timestamp + "_"
                + UUID.randomUUID().toString().substring(0, 8)
                + ".png";

        Path screenshotPath = screenshotDirectory.resolve(fileName);

        // Capture and save screenshot
        byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);

        Files.write(screenshotPath, screenshot);

        return screenshotPath;
    }
}
