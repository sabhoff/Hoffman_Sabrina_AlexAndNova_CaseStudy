package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object Model used as demonstration of knowledge in LoginPage class
 */
public class LoginPage {

    WebDriver driver;

    By registerButton = By.xpath("//*[@id=\"keyboard-nav-3\"]/div[2]/div[3]/a");
    By tryAgainAlert = By.cssSelector(".error-message.banner");
    By myAccountButton = By.cssSelector("body > div:nth-child(4) > section:nth-child(2) > header:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > a:nth-child(1)");
    By email = By.id("ispbxii_1");
    By password = By.cssSelector("input[placeholder='Password']");
    By loginButton = By.xpath("//*[@id=\"customer_login\"]/div[3]/input");
    By bestSellers = By.id("navigation-best-sellers");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public String getAlertText() {
        return driver.findElement(tryAgainAlert).getText();
    }

    public String getMyAccount() {
        return driver.findElement(myAccountButton).getText();
    }

    public void inputEmail(String userEmail) {
        driver.findElement(email).sendKeys(userEmail);
    }

    public void inputPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickBestSellers() {
        driver.findElement(bestSellers).click();
    }
}
