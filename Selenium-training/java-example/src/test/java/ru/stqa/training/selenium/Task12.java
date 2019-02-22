package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class Task12 extends TestBase {
    // Задание 12. Сделайте сценарий добавления товара
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
    public void AddNewProduct() {
        final String productName = "Test Duck";
        webDriver.findElement(By.cssSelector("a[href$=catalog]")).click();
        webDriver.findElement(By.cssSelector(".button[href$=edit_product]")).click();

        // вкладка General
        webDriver.findElement(By.linkText("General")).click();
        //webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.findElement(By.cssSelector("input[name=status]")).click();
        webDriver.findElement(By.cssSelector("input[name^=name]")).sendKeys(productName);
        webDriver.findElement(By.cssSelector("input[name=code]")).sendKeys("td001");

        webDriver.findElement(By.cssSelector("input[data-name=Root]")).click();
        webDriver.findElement(By.cssSelector("input[data-name='Rubber Ducks']")).click();
        webDriver.findElement(By.cssSelector("input[value='1-3']")).click();

        webDriver.findElement(By.cssSelector("input[name=quantity]")).clear();
        webDriver.findElement(By.cssSelector("input[name=quantity]")).sendKeys("30,00");

        new Select(webDriver.findElement(By.cssSelector("select[name=quantity_unit_id]"))).selectByVisibleText("pcs");
        new Select(webDriver.findElement(By.cssSelector("select[name=delivery_status_id]"))).selectByIndex(1);
        new Select(webDriver.findElement(By.cssSelector("select[name=sold_out_status_id]"))).selectByValue("2");

        File image = new File("img//blue-duck.png");
        webDriver.findElement(By.name("new_images[]")).sendKeys(image.getAbsolutePath());

        webDriver.findElement(By.name("date_valid_from")).clear();
        webDriver.findElement(By.name("date_valid_from")).sendKeys("01.01.2019");
        webDriver.findElement(By.name("date_valid_to")).clear();
        webDriver.findElement(By.name("date_valid_to")).sendKeys("01.01.2020");

        // вкладка Information
        webDriver.findElement(By.linkText("Information")).click();
        //webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        new Select(webDriver.findElement(By.name("manufacturer_id"))).selectByIndex(1);
        webDriver.findElement(By.name("keywords")).sendKeys(productName);
        webDriver.findElement(By.name("short_description[en]")).sendKeys("Add new product: " + productName);
        webDriver.findElement(By.className("trumbowyg-editor")).sendKeys("Add new product: " + productName + " (td001)");
        webDriver.findElement(By.cssSelector("input[name^=head_title")).sendKeys(productName);
        webDriver.findElement(By.cssSelector("input[name^=meta_description")).sendKeys("td001");

        // вкладка Prices
        webDriver.findElement(By.linkText("Prices")).click();
        new Select(webDriver.findElement(By.name("purchase_price_currency_code"))).selectByValue("USD");
        webDriver.findElement(By.name("purchase_price")).click();
        webDriver.findElement(By.name("purchase_price")).clear();
        webDriver.findElement(By.name("purchase_price")).sendKeys("25,00");
        webDriver.findElement(By.cssSelector("[name='prices[USD]'")).sendKeys("87,00");

        // Сохраняем данные и проверяем добавление товара в каталог
        webDriver.findElement(By.cssSelector("button[name=save]")).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.findElement(By.linkText(productName)).click();
    }
}
