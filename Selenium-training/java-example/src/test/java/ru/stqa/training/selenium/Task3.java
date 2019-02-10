package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task3 {
    // Лекция 2, Задание 3. Сделайте сценарий логина

    // Инициализируем и настраиваем драйвер
    public WebDriver driver;
    public WebDriverWait wait;
    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }
    // Описываем сценарий для логина в панель администрирования учебного приложения
    // http://localhost/litecart/admin/
    @Test
    public void Login() {
        final String inputText = "admin";
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys(inputText);
        driver.findElement(By.name("password")).sendKeys(inputText);
        driver.findElement(By.name("login")).click();
    }
    // Останавливаем браузер
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
