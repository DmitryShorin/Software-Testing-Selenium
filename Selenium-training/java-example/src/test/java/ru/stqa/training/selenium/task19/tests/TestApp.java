package ru.stqa.training.selenium.task19.tests;

import org.junit.Test;

public class TestApp extends TestBase {
    @Test
    public void TestCart() {
        app.openHomePage();

        for (int i = 0; i < 3; i++) {
            app.addProductToCart();
            app.openHomePage();
        }

        app.removeAllFromCart();
    }
}
