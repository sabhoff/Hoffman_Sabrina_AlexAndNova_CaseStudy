package testcases;

import library.SelectBrowser;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

/**_____________________________________________________________________
 * TestAddToCart tests if items can be added and removed from cart
 ______________________________________________________________________*/
public class TestAddToCart extends Base {

    HomePage homePage;
    BestSellersPage bestSellersPage;
    ItemDescriptionPage itemDescriptionPage;
    CartPage cartPage;

    /**_________________________________________________________________________________
     * browserLaunch launches the browser and creates an implicit wait for all methods
     __________________________________________________________________________________*/
    @BeforeMethod
    public void browserLaunch() {
        driver = SelectBrowser.startBrowser("Chrome");
        driver.get("https://www.alexandnova.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    /**________________________________________________________________________________________________
     * tc0010_verify_price_display_test tests if the price is displayed on the item description page
     * Thread sleep used to allow time for page to load.
     * @throws InterruptedException
     _________________________________________________________________________________________________*/
    @Test(priority = 10)
    public void tc0010_verify_price_display_test() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickBestSellers();
        bestSellersPage = new BestSellersPage(driver);
        bestSellersPage.selectItem();
        itemDescriptionPage = new ItemDescriptionPage(driver);
        //Wait for page to load
        Thread.sleep(2000);
        //Add Assertion
        String actual = "$34.95 USD";
        String expected = itemDescriptionPage.getItemPrice();
        Assert.assertEquals(expected, actual);
    }

    /**____________________________________________________________________________________________________________
     * tc0011_verify_product_added_to_cart_test tests that an item shows in the cart once add to cart is selected.
     * Thread sleep used to allow time for page to load.
     * @throws InterruptedException
     _____________________________________________________________________________________________________________*/
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
        //Wait for item to be added to cart
        Thread.sleep(1000);
        //Add Assertion
        String actual = "1";
        String expected = itemDescriptionPage.checkQuantity();
        Assert.assertEquals(actual, expected);
    }

    /**
     * tc0012_refresh_page_test tests if item remains in cart after page is refreshed
     * Thread sleep used to allow time for page to load.
     * @throws InterruptedException
     */
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

    /**________________________________________________________________________________________________________________
     * tc0013_increase_item_quantity_test tests to see if quantity in cart is updated when item quantity is increased.
     * Thread sleep used to allow time for page to load.
     * @throws InterruptedException
     _________________________________________________________________________________________________________________*/
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
        //Wait for item to be added to cart
        Thread.sleep(2000);
        //Add Assertion
        String actual = "3";
        String expected = itemDescriptionPage.checkQuantity();
        Assert.assertEquals(actual, expected);
    }

    /**_______________________________________________________________________________________________________________________________
     * tc0014_verify_correct_quantity_displayed_test test to make sure that the quantity added to cart matches the total amount shown.
     ________________________________________________________________________________________________________________________________*/
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

    /**_______________________________________________________________________________________________________
     * tc0015_remove_item_from_cart_test tests to make sure that the cart is updated when an item is removed
     * Thread sleep used to allow time for page to load.
     * @throws InterruptedException
     ________________________________________________________________________________________________________*/
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
        //Wait for item to be added to cart
        Thread.sleep(3000);
        itemDescriptionPage.goToCart();
        cartPage = new CartPage(driver);
        cartPage.clearItem();
        Thread.sleep(2000);
        //Assertion
        String expected = "You don't have any items in your cart yet. Continue shopping .";
        String actual = cartPage.cartWarning();
        Assert.assertEquals(expected, actual);
    }
}
