package testcases;

import library.SelectBrowser;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

/**___________________________________________________________
 * TestGiftCart class tests the coupon code field in checkout
 ____________________________________________________________*/

public class TestGiftCart extends Base {

    HomePage homePage;
    BestSellersPage bestSellersPage;
    ItemDescriptionPage itemDescriptionPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    /**_________________________________________________________________________________
     * browserLaunch launches the browser and creates an implicit wait for all methods
     __________________________________________________________________________________*/
    @BeforeMethod
    public void browserLaunch() {
        driver = SelectBrowser.startBrowser("Chrome");
        driver.get("https://www.alexandnova.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    /**______________________________________________________________________________________________________________
     * tc0016_apply_coupon_code_test tests to make sure that a discount is added when a valid coupon code is entered
     * Thread sleep is used to allow for elements to load properly
     * @throws InterruptedException
     _______________________________________________________________________________________________________________*/
    @Test(priority = 16)
    public void tc0016_apply_coupon_code_test() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickBestSellers();
        bestSellersPage = new BestSellersPage(driver);
        bestSellersPage.selectItem();
        itemDescriptionPage = new ItemDescriptionPage(driver);
        itemDescriptionPage.selectSize();
        itemDescriptionPage.selectColor();
        itemDescriptionPage.clickAddToCart();
        //Allow for item to be added to cart
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
