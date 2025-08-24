package org.selenium.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ShoppingCartPage {
    WebDriver driver;
    WebDriverWait wait;

    By smartphoneCheckbox = By.xpath(
    	    "//tr[td//a[contains(text(),'Smartphone')]]//input[@name='removefromcart']");
    By updateCartButton = By.name("updatecart");
    By termsCheckbox = By.id("termsofservice");
    By checkoutButton = By.id("checkout");

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectSmartphoneCheckbox() {
        wait.until(ExpectedConditions.elementToBeClickable(smartphoneCheckbox)).click();
    }

    public void updateCart() {
        wait.until(ExpectedConditions.elementToBeClickable(updateCartButton)).click();
    }

    public void acceptTermsAndCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(termsCheckbox)).click();
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
}
