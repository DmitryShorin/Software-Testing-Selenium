package ru.stqa.training.selenium.task19.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.training.selenium.task19.pages.CartPage;
import ru.stqa.training.selenium.task19.pages.HomePage;
import ru.stqa.training.selenium.task19.pages.ProductPage;

public class Application {
    private WebDriver webDriver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;

    public Application() {
        webDriver = new ChromeDriver();
        homePage = new HomePage(webDriver);
        productPage = new ProductPage(webDriver);
        cartPage = new CartPage(webDriver);
    }

    public void quit() {
        webDriver.quit();
    }

    public void openHomePage() {
        homePage.open();
    }

    public void addProductToCart() {
        homePage.selectAnyProduct();
        productPage.addToCart();
    }

    public void removeAllFromCart() {
        homePage.checkout();
        cartPage.removeAll();
    }
}
