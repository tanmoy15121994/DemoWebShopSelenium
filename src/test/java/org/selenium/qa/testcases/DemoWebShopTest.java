package org.selenium.qa.testcases;

import org.selenium.qa.pages.ElectronicsPage;
import org.selenium.qa.pages.HomePage;
import org.selenium.qa.pages.ShoppingCartPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.selenium.qa.baseclass.BaseClass;
import org.selenium.qa.pages.ApparelShoesPage;

public class DemoWebShopTest extends BaseClass {

    @Test
    public void testAddToCartAndCheckout() {
        HomePage home = new HomePage(driver);
        ApparelShoesPage apparel = new ApparelShoesPage(driver);
        ElectronicsPage electronics = new ElectronicsPage(driver);
        ShoppingCartPage cart = new ShoppingCartPage(driver);

        // Apparel → Blue Jeans
        home.clickApparelShoes();
        apparel.addBlueJeansToCart();

        // Electronics → Smartphone
        home.clickElectronics();
        electronics.addSmartphoneToCart();

        // Cart → Update → Checkout
        home.clickShoppingCart();
        cart.selectSmartphoneCheckbox();
        cart.updateCart();
        cart.acceptTermsAndCheckout();

        // ✅ Assertion: Checkout page is displayed
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"),
                "Checkout page not displayed!");
    }
}
