package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Task13 extends TestBase{
    // Задание 13. Сделайте сценарий работы с корзиной
    @Test
    public void workingWithCart() {
        webDriver.navigate().to("http://litecart.stqa.ru/en/");
        for (int i = 0; i < 3; i++) {
            webDriver.findElements(By.cssSelector(".product")).get(0).click();
            // проверяем неоходимость ввода размера
            if (isElementPresent(webDriver, By.name("options[Size]"))) {
                new Select(webDriver.findElement(By.name("options[Size]"))).selectByValue("Small");
            }
            WebElement quantity = webDriver.findElement(By.className("quantity"));
            webDriver.findElement(By.name("add_cart_product")).click();
            // ждем когда увеличится счетчик
            webDriverWait.until(textToBePresentInElement(quantity, String.valueOf(i+1)));
            webDriver.navigate().back();
        }
        webDriver.findElement(By.cssSelector("a[href$='checkout'")).click();
        List<WebElement> items = webDriver.findElements(By.cssSelector(".items .item"));
        // если товар не один, то останавливаем проматывание списка
        if (items.size() > 1)
            webDriver.findElement(By.id("box-checkout-cart")).click();
        // удаляем товары
        while (!items.isEmpty()) {
            int count = webDriver.findElements(By.cssSelector(".box td.item")).size();
            items.get(0).findElement(By.name("remove_cart_item")).click();
            // ждем удаления товара из таблицы
            webDriverWait.until(numberOfElementsToBeLessThan(By.cssSelector(".box td.item"), count));
            items = webDriver.findElements(By.cssSelector(".items .item"));
        }
    }
}
