package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Task16 {
    // Облачный грид https://automate.browserstack.com/
    public static final String USERNAME = "";
    public static final String AUTOMATE_KEY = "";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @Test
    public void HubCloud() throws MalformedURLException {
        WebDriver webDriver = new RemoteWebDriver(new URL(URL), new InternetExplorerOptions());
        webDriver.navigate().to("http://google.com");
        webDriver.quit();
    }
}
