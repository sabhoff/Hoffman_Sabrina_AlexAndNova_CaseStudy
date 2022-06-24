package testcases;

import library.SelectBrowser;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

public class TestUserLogin extends Base {

    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void browserLaunch() {
        driver = SelectBrowser.startBrowser("Chrome");
        driver.get("https://www.alexandnova.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test(priority = 6)
    public void tc0006_login_user_test() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickAccountButton();
        loginPage = new LoginPage(driver);
        Thread.sleep(2000);
        loginPage.inputEmail("test345@gmail.com");
        loginPage.inputPassword("pass");
        loginPage.clickLoginButton();
        //Thread sleep to allow time for captcha submission
        Thread.sleep(20000);
        //Add Assertion
        String expected = loginPage.getAlertText();
        String actual = "Sorry! Please try that again.";
        Assert.assertEquals(expected, actual);

    }

    @Test(priority = 7)
    public void tc0007_invalid_email_login_test() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickAccountButton();
        loginPage = new LoginPage(driver);
        Thread.sleep(2000);
        loginPage.inputEmail("test345gmail.com");
        loginPage.inputPassword("abc1234");
        loginPage.clickLoginButton();
        //Thread sleep to allow time for captcha submission
        Thread.sleep(20000);
        //Add Assertion
        String expected = loginPage.getAlertText();
        String actual = "Sorry! Please try that again.";
        Assert.assertEquals(expected, actual);
    }
}
