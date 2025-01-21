package com.google.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.google.helpers.TotpHelper;
import com.google.pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
    
    @Test
    void test_Login() {
        var username = "xxx";
        var password = "xxx";
        var secret = "xxx";

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        var driver = new ChromeDriver(options);
        
        var loginPage = new LoginPage(driver);
        loginPage.loadPage();        
        loginPage.inputUsername(username);
        loginPage.clickNextButton();
        loginPage.inputPassword(password);
        loginPage.clickNextButton();
        loginPage.clickGoogleAuthenticatorOption();
        
        var otp = TotpHelper.generateTOTP(secret);
        loginPage.inputOtp(otp);
        loginPage.clickNextButton();
        loginPage.waitToBeLoadedSuccessfully();
        driver.quit();
    }
}
