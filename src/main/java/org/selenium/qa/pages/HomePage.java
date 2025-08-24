package org.selenium.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By apparelShoesLink = By.xpath("//ul[@class='top-menu']//a[contains(text(),'Apparel & Shoes')]");
    By electronicsLink = By.xpath("//ul[@class='top-menu']//a[contains(text(),'Electronics')]");
    By shoppingCartLink = By.xpath("//span[@class='cart-label']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickApparelShoes() {
        wait.until(ExpectedConditions.elementToBeClickable(apparelShoesLink)).click();
    }

    public void clickElectronics() {
        wait.until(ExpectedConditions.elementToBeClickable(electronicsLink)).click();
    }

    public void clickShoppingCart() {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink)).click();
    }

}