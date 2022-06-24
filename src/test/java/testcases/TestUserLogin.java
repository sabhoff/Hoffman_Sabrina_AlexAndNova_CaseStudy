package testcases;

import library.SelectBrowser;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

/**__________________________________________________________________________________________________
 * TestUserLogin tests the login function and makes sure valid and invalid data is handled properly.
 ___________________________________________________________________________________________________*/
public class TestUserLogin extends Base {

    HomePage homePage;
    LoginPage loginPage;

    /**_________________________________________________________________________________
     * browserLaunch launches the browser and creates an implicit wait for all methods
     __________________________________________________________________________________*/
    @BeforeMethod
    public void browserLaunch() {
        driver = SelectBrowser.startBrowser("Chrome");
        driver.get("https://www.alexandnova.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    /**_________________________________________________________________________
     * tc0006_login_user_test tests if a registered user can successfully login.
     * Thread sleep added to allow time for captcha submission.
     * @throws InterruptedException
     __________________________________________________________________________*/
    @Test(priority = 6)
    public void tc0006_login_user_test() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickAccountButton();
        loginPage = new LoginPage(driver);
        Thread.sleep(2000);
        loginPage.inputEmail("aieofds@gmail.com");
        loginPage.inputPassword("abcd123");
        loginPage.clickLoginButton();
        //Thread sleep to allow time for captcha submission
        Thread.sleep(20000);
        //Add Assertion
        String expected = loginPage.getMyAccount();
        String actual = "My account";
        Assert.assertEquals(expected, actual);

    }

    /**____________________________________________________________________________________________________________
     * tc0007_invalid_email_login_test tests to make sure error is given when incorrect info is login is provided
     * Thread sleep added to allow time for captcha submission.
     * @throws InterruptedException
     ______________________________________________________________________________________________________________*/
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
