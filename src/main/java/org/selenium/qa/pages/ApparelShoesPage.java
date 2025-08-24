package org.selenium.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ApparelShoesPage {
    WebDriver driver;
    WebDriverWait wait;

    By blueJeansAddToCart = By.xpath("//a[text()='Blue Jeans']/../..//input[@value='Add to cart']");

    public ApparelShoesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addBlueJeansToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(blueJeansAddToCart)).click();
    }
}