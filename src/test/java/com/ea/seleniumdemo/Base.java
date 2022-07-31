package com.ea.seleniumdemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Base {

    public  WebDriver driver;
    public MainPage mainPage;

    @BeforeTest
    public void InitializeBrowser() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-dev-shm-usage");
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/")
                    ,chromeOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://www.jetbrains.com/");

        mainPage = new MainPage(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
