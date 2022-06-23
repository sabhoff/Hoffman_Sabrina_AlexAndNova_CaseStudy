package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {

    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "label[for='checkout_payment_gateway_14059208761']")
    WebElement cardPaymentOption;

    @FindBy(css = "label[for='checkout_payment_gateway_66130411573'] span[class='visually-hidden']")
    WebElement shopPayPaymentOption;

    @FindBy(css = "label[for='checkout_payment_gateway_14059175993'] span[class='visually-hidden']")
    WebElement payPalPaymentOption;

    @FindBy(className = "card-fields-iframe")
    WebElement cardNumberFrame;
    @FindBy(id = "number")
    WebElement cardNumber;
    @FindBy(xpath = "*//iframe[contains(@id,'card-fields-name')]")
    WebElement nameFrame;
    @FindBy(id = "name")
    WebElement name;
    @FindBy(xpath = "*//iframe[contains(@id,'card-fields-expiry')]")
    WebElement dateFrame;
    @FindBy(id = "expiry")
    WebElement expiry;
    @FindBy(xpath = "*//iframe[contains(@id,'card-fields-verification_value')]")
    WebElement codeFrame;
    @FindBy(id = "verification_value")
    WebElement securityCode;
    @FindBy(id = "continue_button")
    WebElement payNowButton;
    @FindBy(css = "div[class='notice notice--error default-background'] p[class='notice__text']")
    WebElement paymentError;

    public String verifyCCPayment() {
        return cardPaymentOption.getText();
    }

    public String verifyShopPayPayment() {
        return shopPayPaymentOption.getText();
    }

    public String verifyPayPalPayment() {
        return payPalPaymentOption.getText();
    }
    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }
    public void switchToCardNumberFrame() {
        driver.switchTo().frame(cardNumberFrame);
    }
    public void enterCardNumber(String number) {
        cardNumber.sendKeys(number);
    }
    public void switchToNameFrame() {
        driver.switchTo().frame(nameFrame);
    }
    public void enterName(String fullName) {
        name.sendKeys(fullName);
    }
    public void switchToDateFrame() {
        driver.switchTo().frame(dateFrame);
    }
    public void enterExpirationDate(String date) {
        expiry.sendKeys(date);
    }
    public void switchToCodeFrame() {
        driver.switchTo().frame(codeFrame);
    }
    public void enterSecurityCode(String code) {
        securityCode.sendKeys(code);
    }
    public void clickPayNow() {
        payNowButton.click();
    }
    public String showPaymentError() {
        return paymentError.getText();
    }
}
