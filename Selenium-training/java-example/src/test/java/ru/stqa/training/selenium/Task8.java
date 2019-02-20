package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task8 extends TestBase {
    // Задание 8. Сделайте сценарий, проверяющий наличие стикеров у товаров
    @Test
    public void CheckSticker() {
        webDriver.navigate().to("http://localhost/litecart/en/");
        // Получаем список товаров
        //List<WebElement> items = webDriver.findElements(By.cssSelector("div.content li.product.column.shadow.hover-light"));
        List<WebElement> items = webDriver.findElements(By.cssSelector(".product"));
        // Проверяем кол-во стикеров у товара
        for (int i = 0; i < items.size(); i++) {
            List<WebElement> stickers = items.get(i).findElements(By.cssSelector("[class^=sticker]"));
            if (stickers.size() != 1)
                Assert.fail("Найден товар с несколькими стикерами");// + items.get(i).getAttribute("title"));
            else if (stickers.size() < 1)
                Assert.fail("Найден товар без стикера");// + items.get(i).getAttribute("title"));
        }
    }
}
