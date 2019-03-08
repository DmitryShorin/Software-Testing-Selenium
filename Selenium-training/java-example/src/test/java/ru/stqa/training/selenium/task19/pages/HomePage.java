package ru.stqa.training.selenium.task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page {
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void open() {
        webDriver.navigate().to("http://litecart.stqa.ru/en/");
    }

    public void selectAnyProduct() {
        webDriver.findElements(By.cssSelector(".product")).get(0).click();
    }

    public void checkout() {
        webDriver.findElement(By.cssSelector("a[href$='checkout'")).click();
    }
}
