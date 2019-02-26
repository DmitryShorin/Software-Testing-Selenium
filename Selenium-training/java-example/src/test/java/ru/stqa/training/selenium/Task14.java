package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import java.util.List;
import java.util.Set;

public class Task14 extends TestBase {
    // Задание 14. Проверьте, что ссылки открываются в новом окне
    @Before
    public void AdminLogin() {
        final String admin = "admin";
        webDriver.navigate().to("http://localhost/litecart/admin");
        webDriver.findElement(By.name("username")).sendKeys(admin);
        webDriver.findElement(By.name("password")).sendKeys(admin);
        webDriver.findElement(By.name("login")).click();
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://localhost/litecart/admin/");
    }

    @After
    public void AdminLogout() {
        webDriver.findElement(By.cssSelector("[class='fa fa-sign-out fa-lg']")).click();
    }

    @Test
    public void OpenInNewWindow() {
        webDriver.findElement(By.linkText("Countries")).click();
        webDriver.findElement(By.cssSelector("[href$=edit_country]")).click();
        List<WebElement> links = webDriver.findElements(By.cssSelector(".fa.fa-external-link"));
        for (WebElement link : links) {
            String mainWindow = webDriver.getWindowHandle();
            Set<String> oldWindows = webDriver.getWindowHandles();
            links.get(0).click();
            Set<String> newWindows = webDriver.getWindowHandles();
            String newWindow = webDriverWait.until(thereIsWindowOtherThan(oldWindows));
            webDriver.switchTo().window(newWindow);
            webDriver.close();
            webDriver.switchTo().window(mainWindow);
        }
    }

    public ExpectedCondition<String> thereIsWindowOtherThan(Set<String> oldWindows){
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver driver) {
                Set<String> newWindows = driver.getWindowHandles();
                newWindows.removeAll(oldWindows);
                return newWindows.size() > 0 ? newWindows.iterator().next() : null;
            }
        };
    }
}
