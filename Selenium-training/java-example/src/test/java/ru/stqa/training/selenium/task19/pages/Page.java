package ru.stqa.training.selenium.task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;

    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, 10);
    }

    public boolean isElementPresent(WebDriver webDriver, By locator) {
        return webDriver.findElements(locator).size() > 0;
    }
}
