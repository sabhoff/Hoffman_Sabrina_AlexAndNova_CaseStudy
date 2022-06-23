package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;

public class CheckoutPage {

    WebDriver driver;

    public CheckoutPage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "checkout_reduction_code")
    WebElement coupon;

    @FindBy(css = "tbody tr[class='total-line total-line--reduction '] th[class='total-line__name'] span:nth-child(1)")
    WebElement discount;

    @FindBy(id = "checkout_submit")
    WebElement applyButton;

    @FindBy(id = "checkout_email")
    WebElement email;

    @FindBy(id = "checkout_shipping_address_first_name")
    WebElement firstName;

    @FindBy(id = "checkout_shipping_address_last_name")
    WebElement lastName;

    @FindBy(id = "checkout_shipping_address_address1")
    WebElement address;

    @FindBy(id = "checkout_shipping_address_city")
    WebElement city;

    @FindBy(id = "checkout_shipping_address_zip")
    WebElement zipCode;

    @FindBy(id = "continue_button")
    WebElement continueToShipping;


    public void enterCouponCode(String code) {
        coupon.sendKeys(code);
    }
    public String discountVisible() {
        return discount.getText();
    }

    public void applyCouponCode() {
        applyButton.click();
    }

    public void enterEmail(String emailAddress) {
        email.sendKeys(emailAddress);
    }

    public void enterFirstName(String fName) {
        firstName.sendKeys(fName);
    }

    public void enterLastName(String lName) {
        lastName.sendKeys(lName);
    }

    public void addAddress(String streetAddress) {
        address.sendKeys(streetAddress);
    }

    public void addCity(String addressCity) {
        city.sendKeys(addressCity);
    }

    public void addZip(String addressZip) {
        zipCode.sendKeys(addressZip);
    }

    public void clickContinueToShipping() {
        continueToShipping.click();
    }

}
