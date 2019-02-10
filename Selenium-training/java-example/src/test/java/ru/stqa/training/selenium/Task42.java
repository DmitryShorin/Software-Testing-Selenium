package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task42 {
    // Запускаем Edge
    public WebDriver edgeDriver;
    public WebDriverWait wait;

    @Before
    public void start() {
        edgeDriver = new EdgeDriver();
        wait = new WebDriverWait(edgeDriver, 10);
    }

    @Test
    public void Login() {
        final String inputText = "admin";
        edgeDriver.navigate().to("http://localhost/litecart/admin/");
        edgeDriver.findElement(By.name("username")).sendKeys(inputText);
        edgeDriver.findElement(By.name("password")).sendKeys(inputText);
        edgeDriver.findElement(By.name("login")).click();
    }

    @After
    public void stop() {
        edgeDriver.quit();
        edgeDriver = null;
    }
}
