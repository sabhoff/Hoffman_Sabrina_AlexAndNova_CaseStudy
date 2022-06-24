package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage {

    WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //.isp_title_search_term
    @FindBy(css = "div[class='search-results-count'] p:nth-child(1)")
    WebElement searchResult;

    @FindBy(className = "no-search")
    WebElement noResultsError;

    public String verifySearchResult() {
        return searchResult.getText();
    }

    public String getErrorMessage() {
        return noResultsError.getText();
    }
}

