package ru.stqa.training.selenium.task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBeLessThan;

public class CartPage extends Page {
    public CartPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void removeAll() {
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
