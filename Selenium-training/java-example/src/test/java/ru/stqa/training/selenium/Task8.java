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
        List<WebElement> items = webDriver.findElements(By.cssSelector("[class='product column shadow hover-light']"));
        // Проверяем наличие у товара только одного стикера
        for (int i = 0; i < items.size(); i++) {
            //WebElement item = items.get(i).findElement(By.cssSelector("[class^=sticker]"));
            //System.out.println(item.getAttribute("class"));
            List<WebElement> stickers = items.get(i).findElements(By.cssSelector("[class^=sticker]"));
            //System.out.println(stickers);
            //for (WebElement element : stickers)
                //System.out.println("элемент " + (i+1) + " " + element.getAttribute("class"));
            if (stickers.size() != 1)
                Assert.fail("Найден товар с несколькими стикерами");
            //Assert.assertEquals(stickers.size(), 1);
        }
    }
}
