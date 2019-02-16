package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task9 extends TestBase {
    // Задание 9. Проверить сортировку стран и геозон в админке
    @Test
    public void sortСountries() {
        // Входим в админку
        StroreAdminLogin();
        // Переходим в раздел Countries и получаем список строк
        webDriver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> rows = webDriver.findElements(By.cssSelector("form[name='countries_form'] .row"));
        // Проходим список строк
        ArrayList<String> countries = new ArrayList<>(rows.size());
        ArrayList<String> pages = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            // Сохраняем страны в список
            WebElement countryName = rows.get(i).findElement(By.cssSelector("a:first-child"));
            countries.add(countryName.getAttribute("innerText"));
            // Если у страны несколько зон, то добавляем в список ссылку на страну
            String[] innerText = rows.get(i).getAttribute("innerText").trim().split("\\s");
            int countZone = Integer.parseInt(innerText[innerText.length-1]);
            if (countZone > 0)
                pages.add(countryName.getAttribute("href"));
        }
        // Проверяем, что станы расположены в алфавитном порядке
        if (!isSortedList(countries))
            Assert.fail("Страны расположены не в алфавитном порядке");
        // Проверяем на каждой странице расположение зон в алфавитном порядке
        if (pages.size() > 0) {
            ArrayList<String> zones = new ArrayList<>();
            rows.clear();
            for (String pageLink : pages) {
                webDriver.navigate().to(pageLink);
                rows = webDriver.findElements(By.cssSelector("#table-zones tr"));
                for (int i = 1; i < rows.size()-1; i++) {
                    // первую и последнюю строку пропускаем
                    String zone = rows.get(i).findElements(By.cssSelector("td")).get(2).getText();
                    zones.add(zone);
                }
                // Проверяем, что зоны расположены в алфавитном порядке
                if (!isSortedList(zones))
                    Assert.fail("Зоны расположены не в алфавитном порядке");
                zones.clear();
                webDriver.navigate().back();
            }
        }
        // Выходим из админки
        StoreAdminLogout();
    }

    @Test
    public void sortGeoZones() {
        // Входим в админку
        StroreAdminLogin();

        webDriver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> rows = webDriver.findElements(By.cssSelector(".dataTable tbody tr.row"));
        for (int i = 0; i < rows.size(); i++) {
            rows = webDriver.findElements(By.cssSelector(".dataTable tr.row"));
            WebElement link = rows.get(i).findElement(By.cssSelector("td a"));
            link.click();
            List<WebElement> table_zones = webDriver.findElements(By.cssSelector("#table-zones tr"));
            ArrayList<String> zones = new ArrayList<>();
            for (int j = 1; j < table_zones.size()-1; j++) {
                // первую и последнюю строку пропускаем
                String zone = table_zones.get(j).findElements(By.cssSelector("td")).get(2).findElement(By.cssSelector("select option[selected='selected']")).getText();
                zones.add(zone);
            }
            // Проверяем, что зоны расположены в алфавитном порядке
            if (!isSortedList(zones))
                Assert.fail("Зоны расположены не в алфавитном порядке");
            zones.clear();
            webDriver.navigate().back();
        }

        // Выходим из админки
        StoreAdminLogout();
    }

    public void StroreAdminLogin() {
        final String inputText = "admin";
        webDriver.navigate().to("http://localhost/litecart/admin");
        webDriver.findElement(By.name("username")).sendKeys(inputText);
        webDriver.findElement(By.name("password")).sendKeys(inputText);
        webDriver.findElement(By.name("login")).click();
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://localhost/litecart/admin/");
    }

    public void StoreAdminLogout() {
        webDriver.findElement(By.cssSelector("[class='fa fa-sign-out fa-lg']")).click();
    }

    public boolean isSortedList(List<String> list) {
        if (list == null || list.size() == 0) return false;
        else {
            List<String> tmp = new ArrayList<>(list);
            Collections.sort(tmp);
            return tmp.equals(list);
        }
    }
}
