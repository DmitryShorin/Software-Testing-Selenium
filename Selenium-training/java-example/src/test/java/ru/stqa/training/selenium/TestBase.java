package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class TestBase {
    // Базовый класс для тестов
    public WebDriver webDriver;
    public WebDriverWait webDriverWait;

    public void openOnlineStore() {
        webDriver.navigate().to("http://localhost/litecart/en/");
    }

    @Before
    public void start() {
        webDriver = new ChromeDriver();
        //FirefoxOptions options = new FirefoxOptions().setLegacy(true);
        //webDriver = new FirefoxDriver(options);
        //webDriver = new EdgeDriver();
        webDriverWait = new WebDriverWait(webDriver, 10);
    }

    @After
    public void stop() {
        webDriver.quit();
        webDriver = null;
    }
}
