package org.selenium.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ElectronicsPage {
    WebDriver driver;
    WebDriverWait wait;

    By cellPhonesLink = By.xpath("//h2[@class='title']//a[contains(text(),'Cell phones')]");
    By smartphoneAddToCart = By.xpath("//a[text()='Smartphone']/../..//input[@value='Add to cart']");

    public ElectronicsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addSmartphoneToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cellPhonesLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(smartphoneAddToCart)).click();
    }
}
