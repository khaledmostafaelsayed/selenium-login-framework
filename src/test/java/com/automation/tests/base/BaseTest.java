
package com.automation.tests.base;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.config.ConfigReader;
import com.automation.driver.DriverFactory;
import com.automation.driver.DriverManager;

public class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {

        log.info("Starting browser setup");

        WebDriver driver = DriverFactory.createDriver();

        DriverManager.setDriver(driver);

        log.info("WebDriver initialized successfully");

        String baseUrl = ConfigReader.getProperty("base.url");

        log.info("Navigating to application URL");

        getDriver().get(baseUrl);

        log.info("Application opened successfully");
    }

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        log.info("Closing browser");

        DriverManager.quitDriver();

        log.info("Browser closed successfully");
    }
}
