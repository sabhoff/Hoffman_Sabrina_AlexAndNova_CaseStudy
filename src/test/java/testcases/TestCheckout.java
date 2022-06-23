package testcases;

import library.SelectBrowser;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

public class TestCheckout {

    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    BestSellersPage bestSellersPage;
    ItemDescriptionPage itemDescriptionPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    ShippingPage shippingPage;
    PaymentPage paymentPage;

    @BeforeTest
    public void browserLaunch() {
        driver = SelectBrowser.startBrowser("Chrome");
        driver.get("https://www.alexandnova.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

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
        Thread.sleep(900);
        checkoutPage.addAddress("123 Test Dr.");
        checkoutPage.addCity("Denver");
        checkoutPage.addZip("80123");
        checkoutPage.clickContinueToShipping();
        shippingPage = new ShippingPage(driver);
        Thread.sleep(3000);
        shippingPage.clickContinueToPayment();
        //Assertions
        String creditActual = "Credit card";
        String creditExpected = paymentPage.verifyCCPayment();
        Assert.assertEquals(creditActual, creditExpected);
        String shopActual = "Shop Pay";
        String shopExpected = paymentPage.verifyShopPayPayment();
        Assert.assertEquals(shopActual, shopExpected);
        String payActual = "PayPal";
        String payExpected = paymentPage.verifyPayPalPayment();
    }

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
        Thread.sleep(3000);
        itemDescriptionPage.goToCart();
        cartPage = new CartPage(driver);
        cartPage.clickCheckoutButton();
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterEmail("test008687@gmail.com");
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Fink");
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
