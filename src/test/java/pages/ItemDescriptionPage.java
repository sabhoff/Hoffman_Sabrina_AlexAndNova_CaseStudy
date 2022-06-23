package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemDescriptionPage {

    WebDriver driver;

    public ItemDescriptionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "money")
    WebElement productPrice;

    @FindBy(css = "label[for='val431372271621301']")
    WebElement size;

    @FindBy(css = "label[for='val43137227162131-5']")
    WebElement color;

    @FindBy(className = "add-to-cart")
    WebElement addToCartButton;

    @FindBy(css = "a[class='cart-count navigable'] span[class='cart-count-number']")
    WebElement cartCount;

    @FindBy(className = "product-option-quantity")
    WebElement itemQuantity;

    @FindBy(css = ".cart-count.navigable")
    WebElement goToCartButton;

    public String getItemPrice() {
        return productPrice.getText();
    }

    public void selectSize() {
        size.click();
    }

    public void selectColor() {
        color.click();
    }

    public void clickAddToCart() {
        addToCartButton.click();
    }

    public void changeQuantity(String quantity) {
        itemQuantity.sendKeys(quantity);
    }

    public String checkQuantity() {
        return cartCount.getText();
    }

    public void clearQuantity() {
        itemQuantity.clear();
    }

    public void goToCart() {
        goToCartButton.click();
    }
}
