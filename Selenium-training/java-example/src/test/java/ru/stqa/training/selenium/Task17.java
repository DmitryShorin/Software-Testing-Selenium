package ru.stqa.training.selenium;

import com.google.common.io.Files;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class Task17 {
    // Задание 17. Проверьте отсутствие сообщений в логе браузера
    EventFiringWebDriver webDriver;
    WebDriverWait webDriverWait;

/*    static public class Listener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println("Ищем элемент " + by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println("Нашли элемент " + by);
        }
    }*/

    @Before
    public void Start() {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        LoggingPreferences loggingPreferences = new LoggingPreferences();
        loggingPreferences.enable(LogType.BROWSER, Level.ALL);
        desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
        webDriver = new EventFiringWebDriver(new ChromeDriver(desiredCapabilities));

        //webDriver = new EventFiringWebDriver(new ChromeDriver());
        webDriverWait = new WebDriverWait(webDriver, 10);
        //webDriver.register(new Listener());
    }

    @After
    public void Stop() {
        webDriver.quit();
    }

    @Test
    public void BrowserLog() {
        final String admin = "admin";
        webDriver.navigate().to("http://localhost/litecart/admin");
        webDriver.findElement(By.name("username")).sendKeys(admin);
        webDriver.findElement(By.name("password")).sendKeys(admin);
        webDriver.findElement(By.name("login")).click();
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://localhost/litecart/admin/");

        webDriver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        List<WebElement> products = webDriver.findElements(By.cssSelector(".dataTable .row"));
        for (int i = 3; i < products.size(); i++) {
            // переходим на страницу редактирования товара
            products.get(i).findElement(By.cssSelector("td a")).click();
            // удаляем старые сообщения из лога браузера
            webDriver.manage().logs().get("browser").getAll();
            // ждем загрузки страницы
            webDriverWait.until(ExpectedConditions.titleContains("Edit Product:"));
            // проверяем отсутствие сообщений в логе браузера
            Assert.assertTrue("В логе браузера найдены сообщения", webDriver.manage().logs().get("browser").getAll().isEmpty());
            // снимаем скриншот
/*            File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(tempFile, new File(String.format("img\\screen%d.png", i)));
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            webDriver.navigate().back();
            products = webDriver.findElements(By.cssSelector(".dataTable .row"));
        }
    }
}
