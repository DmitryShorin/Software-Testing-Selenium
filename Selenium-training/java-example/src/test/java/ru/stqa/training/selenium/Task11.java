package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;


public class Task11 extends TestBase {
    @Test
    public void UserRegistration() {
        webDriver.navigate().to("http://localhost/litecart/en/");
        // Create account
        webDriver.findElement(By.cssSelector(".account a[href*=create_account]")).click();
        User user = new User();
        webDriver.findElement(By.cssSelector("input[name=firstname]")).sendKeys(user.getFirstName());
        webDriver.findElement(By.cssSelector("input[name=lastname]")).sendKeys(user.getLastName());
        webDriver.findElement(By.cssSelector("input[name=address1]")).sendKeys(user.getAddress());
        webDriver.findElement(By.cssSelector("input[name=postcode]")).sendKeys(user.getPostcode());
        webDriver.findElement(By.cssSelector("input[name=city]")).sendKeys(user.getCity());
        webDriver.findElement(By.cssSelector("input[name=email]")).sendKeys(user.getEmail());
        webDriver.findElement(By.cssSelector("input[name=phone]")).sendKeys(user.getPhone());
        webDriver.findElement(By.cssSelector("input[name=password]")).sendKeys(user.getPassword());
        webDriver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys(user.getPassword());
        // Country
        Select countrySelect = new Select(webDriver.findElement(By.cssSelector(".select2-hidden-accessible")));
        countrySelect.selectByVisibleText(user.getCountry());
        // Zone
        WebElement select_zone_code = webDriver.findElement(By.cssSelector("select[name=zone_code]"));
        // если список штатов недоступен, то делаем попытку сохранить данные
        if (Boolean.parseBoolean(select_zone_code.getAttribute("disabled"))) {
            webDriver.findElement(By.cssSelector("button[name=create_account]")).click();
            webDriver.findElement(By.cssSelector("input[name=password]")).sendKeys(user.getPassword());
            webDriver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys(user.getPassword());
        }
        Select zoneSelect = new Select(webDriver.findElement(By.cssSelector("select[name=zone_code]")));
        zoneSelect.selectByIndex(0);
        webDriver.findElement(By.cssSelector("button[name=create_account]")).click();
        // Logout
        webDriver.findElement(By.cssSelector(".account a[href*=logout]")).click();
        // Login
        webDriver.findElement(By.cssSelector("input[name=email]")).sendKeys(user.getEmail());
        webDriver.findElement(By.cssSelector("input[name=password]")).sendKeys(user.getPassword());
        webDriver.findElement(By.cssSelector("button[name=login]")).click();
        // Logout
        webDriver.findElement(By.cssSelector(".account a[href*=logout]")).click();
    }
}

class User {
    private String firstName;
    private String lastName;
    private String address;
    private String postcode;
    private String city;
    private String country;
    private String email;
    private String phone;
    private String password;

    User(){
        this.firstName = "Anthony";
        this.lastName = "Horton";
        this.address = "775 Westminster Avenue";
        this.postcode = "94163";
        this.city = "San Francisco";
        this.country = "United States";
        this.email = String.format("%s.%s%d@mail.com", firstName.toLowerCase(), lastName.toLowerCase(), new Random().nextInt(100) + 1);
        this.phone = "+18004699269";
        this.password = "user";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}
