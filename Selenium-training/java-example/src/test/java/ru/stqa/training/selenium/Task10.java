package ru.stqa.training.selenium;

import com.google.common.base.Verify;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class Task10 extends TestBase {
    // Задание 10. Проверить, что открывается правильная страница товара
    @Test
    public void productProperties() {
        openOnlineStore();
        List<WebElement> itemsProperty = webDriver.findElements(By.cssSelector("#box-campaigns .link"));
        // Получаем свойства товара на главной странице
        ArrayList<Item> items = new ArrayList<>();
        Item homePageItem = new Item();
        homePageItem.setTitle(itemsProperty.get(0).getAttribute("title"));
        homePageItem.setRegular_price(itemsProperty.get(0).findElement(By.cssSelector(".regular-price")).getText());
        homePageItem.setRegular_price_size(itemsProperty.get(0).findElement(By.cssSelector(".regular-price")).getCssValue("font-size"));
        homePageItem.setRegular_price_striked(itemsProperty.get(0).findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration-line"));
        homePageItem.setRegular_price_grey(itemsProperty.get(0).findElement(By.cssSelector(".regular-price")).getCssValue("color"));
        homePageItem.setCompaign_price(itemsProperty.get(0).findElement(By.cssSelector(".campaign-price")).getText());
        homePageItem.setCompaign_price_size(itemsProperty.get(0).findElement(By.cssSelector(".campaign-price")).getCssValue("font-size"));
        homePageItem.setCompaign_price_bold(itemsProperty.get(0).findElement(By.cssSelector(".campaign-price")).getCssValue("font-weight"));
        homePageItem.setCompaign_price_red(itemsProperty.get(0).findElement(By.cssSelector(".campaign-price")).getCssValue("color"));
        items.add(homePageItem);
        // Переходим на страницу товара и получаем его свойства
        itemsProperty.get(0).click();
        Item PageItem = new Item();
        PageItem.setTitle(webDriver.findElement(By.cssSelector("#box-product .title")).getText());
        PageItem.setRegular_price(webDriver.findElement(By.cssSelector(".information .regular-price")).getText());
        PageItem.setRegular_price_size(webDriver.findElement(By.cssSelector(".information .regular-price")).getCssValue("font-size"));
        PageItem.setRegular_price_striked(webDriver.findElement(By.cssSelector(".information .regular-price")).getCssValue("text-decoration-line"));
        PageItem.setRegular_price_grey(webDriver.findElement(By.cssSelector(".information .regular-price")).getCssValue("color"));
        PageItem.setCompaign_price(webDriver.findElement(By.cssSelector(".information .campaign-price")).getText());
        PageItem.setCompaign_price_size(webDriver.findElement(By.cssSelector(".information .campaign-price")).getCssValue("font-size"));
        PageItem.setCompaign_price_bold(webDriver.findElement(By.cssSelector(".information .campaign-price")).getCssValue("font-weight"));
        PageItem.setCompaign_price_red(webDriver.findElement(By.cssSelector(".information .campaign-price")).getCssValue("color"));
        items.add(PageItem);
        // Сравниваем свойства товаров
        Assert.assertTrue("Названия товаров не совпадают", homePageItem.getTitle().equals(PageItem.getTitle()));
        Assert.assertTrue("Обычные цены не совпадают", homePageItem.getRegular_price().equals(PageItem.getRegular_price()));
        Assert.assertTrue("Акционные цены не совпадают", homePageItem.getCompaign_price().equals(PageItem.getCompaign_price()));
        // Проверяем отдельные свойства каждого товара
        for (Item item : items) {
            Assert.assertTrue("Обычная цена не зачеркнута", item.isRegular_price_striked());
            Assert.assertTrue("Обычная цена не серая", item.isRegular_price_grey());
            Assert.assertTrue("Акционная цена не жирная", item.isCompaign_price_bold());
            Assert.assertTrue("Акционная цена не красная", item.isCompaign_price_red());
            Assert.assertTrue("Акционная цена не крупнее обычной", item.isCompPriceSizeLarger());
        }
    }

    public static class Item {
        private String title;
        private String regular_price;
        private String regular_price_size;
        private boolean regular_price_striked;
        private boolean regular_price_grey;
        private String compaign_price;
        private String compaign_price_size;
        private boolean compaign_price_bold;
        private boolean compaign_price_red;

        public boolean isCompPriceSizeLarger() {
            double regular_price_size = Double.parseDouble(getRegular_price_size().replaceAll("px", ""));
            double compaign_price_size = Double.parseDouble(getCompaign_price_size().replaceAll("px", ""));
            return compaign_price_size > regular_price_size ? true : false;
        }

        public void print() {
            System.out.println(format("title: %s", getTitle()));
            System.out.println(format("regular-price: %s", getRegular_price()));
            System.out.println(format("regular-price-is-striked: %b", isRegular_price_striked()));
            System.out.println(format("regular-price-is-grey: %b", isRegular_price_grey()));
            System.out.println(format("regular-price-size: %s", getRegular_price_size()));
            System.out.println(format("compaign_price: %s", getCompaign_price()));
            System.out.println(format("compaign_price-is-bold: %s", isCompaign_price_bold()));
            System.out.println(format("compaign_price-is-red: %s", isCompaign_price_red()));
            System.out.println(format("compaign-price-size: %s", getCompaign_price_size()));
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isRegular_price_grey() {
            return regular_price_grey;
        }

        public void setCompaign_price_red(String cssProperty) {
            int endIndex = 18;
            if (cssProperty == null || cssProperty.length() < endIndex)
                return;
            String[] colors = cssProperty.substring(5, endIndex).split(", ");
            if (colors[0].equals("0") && colors[1].equals("0") && colors[2].equals("0"))
                compaign_price_red = true;
        }

        public String getRegular_price_size() {
            return regular_price_size;
        }

        public void setRegular_price_size(String regular_price_size) {
            this.regular_price_size = regular_price_size;
        }

        public String getCompaign_price_size() {
            return compaign_price_size;
        }

        public void setCompaign_price_size(String compaign_price_size) {
            this.compaign_price_size = compaign_price_size;
        }

        public boolean isCompaign_price_red() {
            return compaign_price_red;
        }

        public void setCompaign_price_bold(String cssProperty) {
            // значение 700 эквивалентно bold
            if (!(cssProperty == null) && cssProperty.equals("700"))
                compaign_price_bold = true;
        }

        public boolean isCompaign_price_bold() {
            return compaign_price_bold;
        }

        public void setRegular_price_grey(String cssProperty) {
            int endIndex = 18;
            if (cssProperty == null || cssProperty.length() < endIndex)
                return;
            String[] colors = cssProperty.substring(5, endIndex).split(", ");
            if (colors[0].equals(colors[1]) && colors[1].equals(colors[2]))
                regular_price_grey = true;
        }

        public boolean isRegular_price_striked() {
            return regular_price_striked;
        }

        public void setRegular_price_striked(String cssProperty) {
            if (!(cssProperty == null) && cssProperty.equals("line-through"))
                regular_price_striked = true;
        }

        public void setRegular_price(String regular_price) {
            this.regular_price = regular_price;
        }

        public void setCompaign_price(String compaign_price) {
            this.compaign_price = compaign_price;
        }

        public String getTitle() {
            return title;
        }

        public String getRegular_price() {
            return regular_price;
        }

        public String getCompaign_price() {
            return compaign_price;
        }
    }
}
