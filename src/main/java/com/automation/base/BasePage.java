
package com.automation.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automation.config.ConfigReader;

public class BasePage {

    protected final WebDriver driver;
    private final WebDriverWait wait;

    // Constructor
    public BasePage(WebDriver driver) {

        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }

        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(
                        Long.parseLong(
                                ConfigReader.getProperty("explicit.wait"))));
    }

    // Wait for Element Visibility
    protected WebElement waitForVisible(By locator) {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for Element Clickable
    protected WebElement waitForClickable(By locator) {

        return wait.until(
                ExpectedConditions.elementToBeClickable(locator));
    }

    // Click Action
    protected void click(By locator) {

        waitForClickable(locator).click();
    }

    // Type Action
    protected void type(By locator, String text) {

        WebElement element = waitForVisible(locator);

        element.clear();
        element.sendKeys(text);
    }

    // Get Text Action
    protected String getText(By locator) {

        return waitForVisible(locator).getText();
    }

    // Check Element Visibility
    protected boolean isDisplayed(By locator) {

        try {
            return waitForVisible(locator).isDisplayed();

        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
}
