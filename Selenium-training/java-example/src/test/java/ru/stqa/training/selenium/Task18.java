package ru.stqa.training.selenium;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Task18 {
    // Задание 18. Перенаправьте трафик в прокси-сервер
    public WebDriver webDriver;
    public BrowserMobProxy proxy;

    @Before
    public void Start() {
        // BrowserMobProxy
        proxy = new BrowserMobProxyServer();
        proxy.start(0);
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.PROXY, seleniumProxy);
        webDriver = new ChromeDriver(caps);
    }

    @After
    public void Stop() {
        webDriver.quit();
    }

    @Test
    public void Proxy() {
        proxy.newHar();
        webDriver.navigate().to("http://google.com");
        Har har = proxy.endHar();
        har.getLog().getEntries().forEach(l -> System.out.println(l.getResponse().getStatus() + " : " + l.getRequest().getUrl()));
    }
}
