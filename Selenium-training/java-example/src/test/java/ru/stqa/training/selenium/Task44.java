package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task44 {
    // Запускаем Firefox Nightly
    public WebDriver firefoxDriver;
    public WebDriverWait wait;

    @Before
    public void start() {
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Firefox Nightly\\firefox.exe");

        firefoxDriver = new FirefoxDriver(options);
        wait = new WebDriverWait(firefoxDriver, 10);
    }
    @Test
    public void Login() throws InterruptedException {
        final String inputText = "admin";
        firefoxDriver.navigate().to("http://localhost/litecart/admin/");
        firefoxDriver.findElement(By.name("username")).sendKeys(inputText);
        firefoxDriver.findElement(By.name("password")).sendKeys(inputText);
        firefoxDriver.findElement(By.name("login")).click();
    }
    @After
    public void stop() {
        firefoxDriver.quit();
        firefoxDriver = null;
    }
}
