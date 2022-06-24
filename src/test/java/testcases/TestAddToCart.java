package testcases;

import library.SelectBrowser;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

public class TestAddToCart extends Base {

    HomePage homePage;
    BestSellersPage bestSellersPage;
    ItemDescriptionPage itemDescriptionPage;
    CartPage cartPage;

    @BeforeMethod
    public void browserLaunch() {
        driver = SelectBrowser.startBrowser("Chrome");
        driver.get("https://www.alexandnova.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test(priority = 10)
    public void tc0010_verify_price_display_test() {
        homePage = new HomePage(driver);
        homePage.clickBestSellers();
        bestSellersPage = new BestSellersPage(driver);
        bestSellersPage.selectItem();
        itemDescriptionPage = new ItemDescriptionPage(driver);
        //Add Assertion
        String actual = "$34.95 USD";
        String expected = itemDescriptionPage.getItemPrice();
        Assert.assertEquals(expected, actual);
    }

    @Test(priority = 11)
    public void tc0011_verify_product_added_to_cart_test() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickBestSellers();
        bestSellersPage = new BestSellersPage(driver);
        bestSellersPage.selectItem();
        itemDescriptionPage = new ItemDescriptionPage(driver);
        itemDescriptionPage.selectSize();
        itemDescriptionPage.selectColor();
        itemDescriptionPage.clickAddToCart();
        Thread.sleep(1000);
        //Add Assertion
        String actual = "1";
        String expected = itemDescriptionPage.checkQuantity();
        Assert.assertEquals(actual, expected);
    }

    @Test(priority = 12)
    public void tc0012_refresh_page_test() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickBestSellers();
        bestSellersPage = new BestSellersPage(driver);
        bestSellersPage.selectItem();
        itemDescriptionPage = new ItemDescriptionPage(driver);
        itemDescriptionPage.selectSize();
        itemDescriptionPage.selectColor();
        itemDescriptionPage.clickAddToCart();
        //Sleep to allow time for item to be added to cart before page refreshes
        Thread.sleep(2000);
        driver.navigate().refresh();
        //Add Assertion
        String actual = "1";
        String expected = itemDescriptionPage.checkQuantity();
        Assert.assertEquals(actual, expected);
    }

    @Test(priority = 13)
    public void tc0013_increase_item_quantity_test() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickBestSellers();
        bestSellersPage = new BestSellersPage(driver);
        bestSellersPage.selectItem();
        itemDescriptionPage = new ItemDescriptionPage(driver);
        itemDescriptionPage.selectSize();
        itemDescriptionPage.selectColor();
        itemDescriptionPage.clearQuantity();
        itemDescriptionPage.changeQuantity("3");
        itemDescriptionPage.clickAddToCart();
        Thread.sleep(1000);
        //Add Assertion
        String actual = "3";
        String expected = itemDescriptionPage.checkQuantity();
        Assert.assertEquals(actual, expected);
    }

    @Test(priority = 14)
    public void tc0014_verify_correct_quantity_displayed_test() {
        homePage = new HomePage(driver);
        homePage.clickBestSellers();
        bestSellersPage = new BestSellersPage(driver);
        bestSellersPage.selectItem();
        itemDescriptionPage = new ItemDescriptionPage(driver);
        itemDescriptionPage.selectSize();
        itemDescriptionPage.selectColor();
        itemDescriptionPage.clickAddToCart();
        itemDescriptionPage.goToCart();
        cartPage = new CartPage(driver);
        //Add Assertion
        String actual = "$34.95 USD";
        String expected = cartPage.getTotalPrice();
        Assert.assertEquals(actual, expected);

    }

    @Test(priority = 15)
    public void tc0015_remove_item_from_cart_test() throws InterruptedException {
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
        cartPage.clearItem();
        //Assertion
        String expected = "You don't have any items in your cart yet. Continue shopping .";
        String actual = cartPage.cartWarning();
        Assert.assertEquals(expected, actual);
    }
}
