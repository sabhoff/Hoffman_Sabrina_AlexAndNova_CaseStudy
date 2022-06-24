package testcases;

import library.SelectBrowser;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;

import java.time.Duration;

public class TestSearchFunction extends Base {

    HomePage homePage;
    SearchResultsPage searchResultsPage;

    @BeforeMethod
    public void browserLaunch() {
        driver = SelectBrowser.startBrowser("Chrome");
        driver.get("https://www.alexandnova.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test(priority = 8)
    public void tc0008_search_results_test() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.inputSearch("baby shoes");
        homePage.clickSearchButton();
        //Assertion
        searchResultsPage = new SearchResultsPage(driver);
        String expected = searchResultsPage.verifySearchResult();
        String actual = "'baby shoes'";
        Assert.assertTrue(expected.contains(actual));
    }

    @Test(priority = 9)
    public void tc0009_no_search_input_test() {
        homePage = new HomePage(driver);
        homePage.clickSearchButton();
        //Assertion
        searchResultsPage = new SearchResultsPage(driver);
        String expected = searchResultsPage.getErrorMessage();
        String actual = "You haven't performed a search. Use the search box at the top of the page to search for a product.";
    }
}
