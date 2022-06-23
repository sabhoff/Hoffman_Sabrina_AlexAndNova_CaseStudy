package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

    WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "page-title")
    WebElement actualTitle;

    @FindBy(id = "ispbxii_1")
    WebElement firstName;

    @FindBy(id = "ispbxii_2")
    WebElement lastName;

    @FindBy(id = "ispbxii_3")
    WebElement email;

    @FindBy(name = "customer[password]")
    WebElement password;

    @FindBy(css = "input[value='Register']")
    WebElement registerButton;

    public String getTitleText() {
        return actualTitle.getText();
    }

    public void inputFirstName(String fName) {
        firstName.sendKeys(fName);
    }

    public void inputLastName(String lName) {
        lastName.sendKeys(lName);
    }

    public void inputEmail(String emailInput) {
        email.sendKeys(emailInput);
    }

    public void inputPassword(String pass) {
        password.sendKeys(pass);
    }

    public void clickRegisterButton() {
        registerButton.click();
    }
}
