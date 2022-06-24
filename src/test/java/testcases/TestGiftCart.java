package testcases;

import library.SelectBrowser;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

public class TestGiftCart extends Base {

    HomePage homePage;
    BestSellersPage bestSellersPage;
    ItemDescriptionPage itemDescriptionPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void browserLaunch() {
        driver = SelectBrowser.startBrowser("Chrome");
        driver.get("https://www.alexandnova.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test(priority = 1)
    public void tc0016_apply_coupon_code_test() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickBestSellers();
        bestSellersPage = new BestSellersPage(driver);
        bestSellersPage.selectItem();
        itemDescriptionPage = new ItemDescriptionPage(driver);
        itemDescriptionPage.selectSize();
        itemDescriptionPage.selectColor();
        itemDescriptionPage.clickAddToCart();
        Thread.sleep(3000);
        itemDescriptionPage.goToCart();
        cartPage = new CartPage(driver);
        cartPage.clickCheckoutButton();
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCouponCode("MASK15");
        checkoutPage.applyCouponCode();
        //Add Assertion
        String actual = "Discount";
        String expected = checkoutPage.discountVisible();
        Assert.assertEquals(expected, actual);
    }
}
