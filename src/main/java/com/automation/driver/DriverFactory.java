
package com.automation.driver;

import java.util.Locale;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.automation.config.ConfigReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DriverFactory {

    private static final Logger log = LoggerFactory.getLogger(DriverFactory.class);

    private DriverFactory() {
    }

    public static WebDriver createDriver() {

        String browser = ConfigReader
                .getProperty("browser")
                .toLowerCase(Locale.ROOT);

        boolean headless = Boolean.parseBoolean(
                ConfigReader.getProperty("headless"));

        log.info(
                "Initializing browser: {}, headless: {}",
                browser,
                headless);

        WebDriver driver;

        switch (browser) {

            case "chrome":

                ChromeOptions chromeOptions = new ChromeOptions();

                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }

                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":

                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (headless) {
                    firefoxOptions.addArguments("-headless");
                }

                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":

                EdgeOptions edgeOptions = new EdgeOptions();

                if (headless) {
                    edgeOptions.addArguments("--headless=new");
                }

                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser);
        }

        if (!headless) {
            driver.manage().window().maximize();
        }

        log.info(
                "WebDriver created successfully for browser: {}",
                browser);

        return driver;
    }
}
