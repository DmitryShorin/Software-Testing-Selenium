package ru.stqa.training.selenium.task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class ProductPage extends Page {
    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void addToCart() {
        // проверяем необходимость выбора размера товара
        if (isElementPresent(webDriver, By.name("options[Size]"))) {
            new Select(webDriver.findElement(By.name("options[Size]"))).selectByValue("Small");
        }
        // получаем кол-во товаров в корзине
        WebElement quantity = webDriver.findElement(By.className("quantity"));
        int count = Integer.parseInt(quantity.getText());
        webDriver.findElement(By.name("add_cart_product")).click();
        // ждем когда кол-во товаров увеличится
        webDriverWait.until(textToBePresentInElement(quantity, String.valueOf(++count)));
    }

    //public void back() { webDriver.navigate().back(); }
}
