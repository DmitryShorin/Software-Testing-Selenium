package ru.stqa.training.selenium;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task43 {
    // Запускаем Firefox 45 ESR по старой схеме с capabilities
    public WebDriver firefoxDriver;
    public WebDriverWait wait;

    @Before
    public void start() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(FirefoxDriver.MARIONETTE, false);
        firefoxDriver = new FirefoxDriver(caps);
        System.out.println(((HasCapabilities)firefoxDriver).getCapabilities());
        wait = new WebDriverWait(firefoxDriver, 10);
    }
    @Test
    public void Login() {
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
