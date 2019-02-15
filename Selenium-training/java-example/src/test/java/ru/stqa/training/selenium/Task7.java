package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Task7 extends TestBase {
    /* Лекция 4, Задание 7:
    Сделайте сценарий, который выполняет следующие действия в учебном приложении litecart.
    1) входит в панель администратора http://localhost/litecart/admin
    2) прокликивает последовательно все пункты меню слева, включая вложенные пункты
    3) для каждой страницы проверяет наличие заголовка (то есть элемента с тегом h1) */

    @Test
    public void AdminMenuClick() {
        // Входим в админку
        final String inputText = "admin";
        webDriver.navigate().to("http://localhost/litecart/admin");
        webDriver.findElement(By.name("username")).sendKeys(inputText);
        webDriver.findElement(By.name("password")).sendKeys(inputText);
        webDriver.findElement(By.name("login")).click();
        List<WebElement> menuList = webDriver.findElements(By.cssSelector("ul#box-apps-menu li#app-"));
        for (int i = 0; i < menuList.size(); i++) {
            menuList = webDriver.findElements(By.cssSelector("ul#box-apps-menu li#app-"));
            // Кликаем по меню и проверяем наличие заголовка
            WebElement menuItem = menuList.get(i);
            menuItem.click();
            // Выводим страницы без заголовка
            printNoTitle(webDriver);
            // Проверяем наличие у элемента меню вложенных элементов
            if ((webDriver.findElements(By.cssSelector("ul.docs li")).size() > 0)) {
                List<WebElement> subMenuList = webDriver.findElements(By.cssSelector("ul#box-apps-menu li li"));
                for (int j = 1; j < subMenuList.size(); j++) {
                    subMenuList = webDriver.findElements(By.cssSelector("ul#box-apps-menu li li"));
                    WebElement subMenuItem = subMenuList.get(j);
                    subMenuItem.click();
                    // Выводим страницы без заголовка
                    printNoTitle(webDriver);
                }
            }
        }
    }
}
