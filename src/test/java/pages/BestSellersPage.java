package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BestSellersPage {
    WebDriver driver;

    public BestSellersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //@FindBy(css = "article[id='product-list-item-4313722716213'] p[class='product-list-item-price']")
    //WebElement itemPrice;

    @FindBy(css = "article[id='product-list-item-4313722716213'] span[class='money'] span[class='money notranslate']")
    WebElement itemPrice;

    @FindBy(partialLinkText = "Sabrina Plush Skirt")
    WebElement sPlushSkirt;

    public String verifyItemPrice() {
        return itemPrice.getText();
    }

    public void selectItem() {
        sPlushSkirt.click();
    }
}
