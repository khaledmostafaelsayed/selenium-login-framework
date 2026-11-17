
package com.automation.tests.login;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

import com.automation.listeners.TestListener;
import com.automation.pages.LoginPage;
import com.automation.testdata.LoginDataProvider;
import com.automation.tests.base.BaseTest;

import io.qameta.allure.Allure;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

        @Test(dataProvider = "validLogins", dataProviderClass = LoginDataProvider.class)
        public void validLoginTest(
                        LoginDataProvider.LoginCase data) {

                Allure.getLifecycle().updateTestCase(
                                test -> test.setName("Valid Login - " + data.scenario()));

                LoginPage loginPage = new LoginPage(getDriver());

                loginPage.login(
                                data.username(),
                                data.password());

                Assert.assertTrue(
                                getDriver().getCurrentUrl()
                                                .contains(data.expected()),
                                "Valid login failed: " + data.scenario());
        }

        @Test(dataProvider = "invalidLogins", dataProviderClass = LoginDataProvider.class)
        public void invalidLoginTest(
                        LoginDataProvider.LoginCase data) {

                Allure.getLifecycle().updateTestCase(
                                test -> test.setName("Invalid Login - " + data.scenario()));

                LoginPage loginPage = new LoginPage(getDriver());

                loginPage.login(
                                data.username(),
                                data.password());

                Assert.assertTrue(
                                loginPage.isErrorMessageDisplayed(),
                                "Error message was not displayed: "
                                                + data.scenario());

                Assert.assertTrue(
                                loginPage.getErrorMessage()
                                                .contains(data.expected()),
                                "Unexpected error message: "
                                                + data.scenario());
        }
}
