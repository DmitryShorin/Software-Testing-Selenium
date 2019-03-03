package ru.stqa.training.selenium;


import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Task15 {
    // Грид
    @Test
    public void Test() throws MalformedURLException {
        final URL LOCALHOST = new URL("http://localhost:4444/wd/hub");

        //WebDriver webDriver = new RemoteWebDriver(LOCALHOST, new InternetExplorerOptions().ignoreZoomSettings());
        WebDriver webDriver = new RemoteWebDriver(LOCALHOST, new ChromeOptions());

        webDriver.navigate().to("http://google.com");
        webDriver.quit();
    }
}
