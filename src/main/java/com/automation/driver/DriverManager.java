
package com.automation.driver;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    // Store WebDriver for the current thread
    public static void setDriver(WebDriver driver) {

        if (driver == null) {
            throw new IllegalArgumentException(
                    "WebDriver cannot be null");
        }

        if (DRIVER.get() != null) {
            throw new IllegalStateException(
                    "WebDriver already exists for this thread");
        }

        DRIVER.set(driver);
    }

    // Get WebDriver for the current thread
    public static WebDriver getDriver() {

        WebDriver driver = DRIVER.get();

        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver is not initialized");
        }

        return driver;
    }

    // Close WebDriver and clear ThreadLocal
    public static void quitDriver() {

        WebDriver driver = DRIVER.get();

        try {
            if (driver != null) {
                driver.quit();
            }
        } finally {
            DRIVER.remove();
        }
    }
}
