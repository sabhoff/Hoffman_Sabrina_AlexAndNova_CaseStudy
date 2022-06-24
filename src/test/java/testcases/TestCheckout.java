package testcases;

import library.SelectBrowser;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

/**
 * TestCheckout class tests the checkout process.
 */

public class TestCheckout extends Base {

    HomePage homePage;
    BestSellersPage bestSellersPage;
    ItemDescriptionPage itemDescriptionPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    ShippingPage shippingPage;
    PaymentPage paymentPage;

    /**_________________________________________________________________________________
     * browserLaunch launches the browser and creates an implicit wait for all methods
     __________________________________________________________________________________*/
    @BeforeMethod
    public void browserLaunch() {
        driver = SelectBrowser.startBrowser("Chrome");
        driver.get("https://www.alexandnova.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    /**_______________________________________________________________________________________________________
     * tc0017_payment_mode_check_test tests if payment options for credit card, shop pay, and paypal show up.
     * Thread sleep is used to allow for elements to load properly
     * @throws InterruptedException
     ________________________________________________________________________________________________________*/
    @Test(priority = 17)
    public void tc0017_payment_mode_check_test() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickBestSellers();
        bestSellersPage = new BestSellersPage(driver);
        bestSellersPage.selectItem();
        itemDescriptionPage = new ItemDescriptionPage(driver);
        itemDescriptionPage.selectSize();
        itemDescriptionPage.selectColor();
        itemDescriptionPage.clickAddToCart();
        //Wait for item to be added to cart
        Thread.sleep(3000);
        itemDescriptionPage.goToCart();
        cartPage = new CartPage(driver);
        cartPage.clickCheckoutButton();
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterEmail("test008687@gmail.com");
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Fink");
        //Wait so entry fields don't get messed up
        Thread.sleep(900);
        checkoutPage.addAddress("123 Test Dr.");
        checkoutPage.addCity("Denver");
        checkoutPage.addZip("80123");
        checkoutPage.clickContinueToShipping();
        shippingPage = new ShippingPage(driver);
        //Wait for everything to load
        Thread.sleep(3000);
        shippingPage.clickContinueToPayment();
        paymentPage = new PaymentPage(driver);
        //Assertions
        //Wait for page to load
        Thread.sleep(2000);
        String creditActual = "Credit card";
        String creditExpected = paymentPage.verifyCCPayment();
        Assert.assertTrue(creditExpected.contains(creditActual));
        String shopActual = "Shop Pay";
        String shopExpected = paymentPage.verifyShopPayPayment();
        Assert.assertEquals(shopActual, shopExpected);
        String payActual = "PayPal";
        String payExpected = paymentPage.verifyPayPalPayment();
        Assert.assertEquals(payActual, payExpected);
    }

    /**___________________________________________________________________________________________________
     * tc0018_blank_mandatory_field_test tests if an error is shown when a mandatory field is left blank.
     * Thread sleep is used to allow for elements to load properly
     * @throws InterruptedException
     ____________________________________________________________________________________________________*/
    @Test(priority = 18)
    public void tc0018_blank_mandatory_field_test() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickBestSellers();
        bestSellersPage = new BestSellersPage(driver);
        bestSellersPage.selectItem();
        itemDescriptionPage = new ItemDescriptionPage(driver);
        itemDescriptionPage.selectSize();
        itemDescriptionPage.selectColor();
        itemDescriptionPage.clickAddToCart();
        //Wait for item to be added to cart
        Thread.sleep(3000);
        itemDescriptionPage.goToCart();
        cartPage = new CartPage(driver);
        cartPage.clickCheckoutButton();
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterEmail("test008687@gmail.com");
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Fink");
        //Wait so entry fields don't get messed up
        Thread.sleep(900);
        checkoutPage.addAddress("123 Test Dr.");
        checkoutPage.addCity("Denver");
        checkoutPage.addZip("80123");
        checkoutPage.clickContinueToShipping();
        shippingPage = new ShippingPage(driver);
        //Wait for everything to load
        Thread.sleep(3000);
        shippingPage.clickContinueToPayment();
        paymentPage = new PaymentPage(driver);
        paymentPage.switchToCardNumberFrame();
        paymentPage.enterCardNumber("3698");
        paymentPage.enterCardNumber("5214");
        paymentPage.enterCardNumber("769");
        paymentPage.enterCardNumber("874");
        paymentPage.switchToParentFrame();
        paymentPage.switchToNameFrame();
        paymentPage.enterName("John Fink");
        paymentPage.switchToParentFrame();
        paymentPage.switchToDateFrame();
        paymentPage.enterExpirationDate("06");
        paymentPage.enterExpirationDate("2005");
        paymentPage.switchToParentFrame();
        paymentPage.clickPayNow();
        //Assertion
        String actual = "Your payment details couldn’t be verified. Check your card details and try again.";
        String expected = paymentPage.showPaymentError();
        Assert.assertEquals(expected, actual);
    }

    /**_______________________________________________________________________________________________
     * tc0019_payment_details_test tests that an error is displayed when payment data is incorrect
     * Thread sleep is used to allow for elements to load properly
     * @throws InterruptedException
     ________________________________________________________________________________________________*/
    @Test(priority = 19)
    public void tc0019_payment_details_test() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickBestSellers();
        bestSellersPage = new BestSellersPage(driver);
        bestSellersPage.selectItem();
        itemDescriptionPage = new ItemDescriptionPage(driver);
        itemDescriptionPage.selectSize();
        itemDescriptionPage.selectColor();
        itemDescriptionPage.clickAddToCart();
        //Wait for item to be added to cart
        Thread.sleep(3000);
        itemDescriptionPage.goToCart();
        cartPage = new CartPage(driver);
        cartPage.clickCheckoutButton();
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterEmail("test008687@gmail.com");
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Fink");
        //Wait so entry fields don't get messed up
        Thread.sleep(900);
        checkoutPage.addAddress("123 Test Dr.");
        checkoutPage.addCity("Denver");
        checkoutPage.addZip("80123");
        checkoutPage.clickContinueToShipping();
        shippingPage = new ShippingPage(driver);
        //Wait for everything to load
        Thread.sleep(3000);
        shippingPage.clickContinueToPayment();
        paymentPage = new PaymentPage(driver);
        paymentPage.switchToCardNumberFrame();
        paymentPage.enterCardNumber("3698");
        paymentPage.enterCardNumber("5214");
        paymentPage.enterCardNumber("769");
        paymentPage.enterCardNumber("874");
        paymentPage.switchToParentFrame();
        paymentPage.switchToNameFrame();
        paymentPage.enterName("John Fink");
        paymentPage.switchToParentFrame();
        paymentPage.switchToDateFrame();
        paymentPage.enterExpirationDate("06");
        paymentPage.enterExpirationDate("2005");
        paymentPage.switchToParentFrame();
        paymentPage.switchToCodeFrame();
        paymentPage.enterSecurityCode("222");
        paymentPage.switchToParentFrame();
        paymentPage.clickPayNow();
        //Assertion
        String actual = "Your payment details couldn’t be verified. Check your card details and try again.";
        String expected = paymentPage.showPaymentError();
        Assert.assertEquals(expected, actual);
    }

}
