package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "customer_login_link")
    WebElement accountButton;

    @FindBy(name = "q")
    WebElement searchBar;

    @FindBy(className = "header-search-button")
    WebElement searchButton;

    @FindBy(id = "navigation-best-sellers")
    WebElement bestSellers;

    public void clickAccountButton() {
        accountButton.click();
    }

    public void inputSearch(String search) {
        searchBar.sendKeys(search);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickBestSellers() {
        bestSellers.click();
    }
}
