package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".remove")
    WebElement clearButton;

    @FindBy(css = "p[class='empty']")
    WebElement emptyCartWarning;

    @FindBy(css = "button[value='Checkout']")
    WebElement checkoutButton;

    @FindBy(css = "td[class='total last'] span[class='money notranslate']")
    WebElement totalPrice;

    public void clearItem() {
        clearButton.click();
    }

    public String cartWarning() {
        return emptyCartWarning.getText();
    }

    public void clickCheckoutButton() {
        checkoutButton.click();
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

}
