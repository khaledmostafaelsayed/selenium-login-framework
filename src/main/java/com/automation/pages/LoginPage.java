
package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.base.BasePage;

public class LoginPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);

    // Locators
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Actions
    public void enterUsername(String username) {

        log.debug("Entering username");

        type(usernameField, username);
    }

    public void enterPassword(String password) {

        log.debug("Entering password");

        type(passwordField, password);
    }

    public void clickLoginButton() {

        log.info("Clicking login button");

        click(loginButton);
    }

    public void login(String username, String password) {

        log.info("Starting login process");

        enterUsername(username);

        enterPassword(password);

        clickLoginButton();

        log.info("Login form submitted");
    }

    public String getErrorMessage() {

        log.debug("Reading login error message");

        return getText(errorMessage);
    }

    public boolean isErrorMessageDisplayed() {

        log.debug("Checking login error message visibility");

        return isDisplayed(errorMessage);
    }
}
