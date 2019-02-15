package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class TestBase {
    // Базовый класс для тестов
    public WebDriver webDriver;
    public WebDriverWait webDriverWait;

    public void printNoTitle(WebDriver webDriver) {
        // Выводим страницы без заголовка
        if (webDriver.getTitle().equals(""))
            System.out.println((format("Найдена страница без заголовка: %s", webDriver.getCurrentUrl())));
    }

    @Before
    public void start() {
        webDriver = new ChromeDriver();
        webDriverWait = new WebDriverWait(webDriver, 10);
    }

    @After
    public void stop() {
        webDriver.quit();
        webDriver = null;
    }
}
