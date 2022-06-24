package testcases;

import library.SelectBrowser;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SignUpPage;

import java.time.Duration;

/**______________________________________________________________________________________________________________
 * TestUserRegistration tests the register function and makes sure valid and invalid data is handled properly.
 ______________________________________________________________________________________________________________*/
public class TestUserRegistration extends Base{

    HomePage homePage;
    SignUpPage signUpPage;
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

    /**_____________________________________________________________________________________________________________
     * tc0001_open_registration_page_test tests to make sure sign up page is opened when register button is clicked
     _____________________________________________________________________________________________________________*/
    @Test(priority = 1)
    public void tc0001_open_registration_page_test() {
        homePage = new HomePage(driver);
        homePage.clickAccountButton();
        loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();
        signUpPage = new SignUpPage(driver);
        String expected = "Sign up";
        String actual = signUpPage.getTitleText();
        Assert.assertEquals(expected, actual);
    }

    /**______________________________________________________________________________
     * tc0002_register_new_user_test tests to make sure a new user can be registered
     * Thread sleep added to allow for captcha submission
     * @throws InterruptedException
     _______________________________________________________________________________*/
    @Test(priority = 2)
    public void tc0002_register_new_user_test() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickAccountButton();
        loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();
        signUpPage = new SignUpPage(driver);
        signUpPage.inputFirstName("Mike");
        signUpPage.inputLastName("Mountain");
        signUpPage.inputEmail("aeiou369@gmail.com");
        signUpPage.inputPassword("abc1234");
        signUpPage.clickRegisterButton();
        //Thread sleep to allow time for captcha submission
        Thread.sleep(20000);
        //Add assertion
        String expected = loginPage.getMyAccount();
        String actual = "My account";
        Assert.assertEquals(expected, actual);
    }

    /**_________________________________________________________________________________________
     * tc0003_email_validation_no_at_test tests to make sure an invalid email is not accepted.
     * Thread sleep added to allow for captcha submission
     * @throws InterruptedException
     __________________________________________________________________________________________*/
    @Test(priority = 3)
    public void tc0003_email_validation_no_at_test() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickAccountButton();
        loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();
        signUpPage = new SignUpPage(driver);
        signUpPage.inputFirstName("Jane");
        signUpPage.inputLastName("Doe");
        //Test email without @ included
        signUpPage.inputEmail("test543gmail.com");
        signUpPage.inputPassword("abc1234");
        signUpPage.clickRegisterButton();
        //Thread sleep to allow time for captcha submission
        Thread.sleep(20000);
        //Add Assertion
        String expected = "Sorry! Please try that again.";
        String actual = loginPage.getAlertText();
        Assert.assertEquals(expected, actual);
    }

    /**________________________________________________________________________________________________________________
     * tc0004_omit_registration_fields_test tests to make sure an account is not created when no fields are filled out.
     * Thread sleep added to allow for captcha submission.
     * @throws InterruptedException
     ________________________________________________________________________________________________________________*/
    @Test(priority = 4)
    public void tc0004_omit_registration_fields_test() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickAccountButton();
        loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();
        signUpPage = new SignUpPage(driver);
        signUpPage.clickRegisterButton();
        //Thread sleep to allow time for captcha submission
        Thread.sleep(20000);
        //Assertion
        String expected = "Sorry! Please try that again.";
        String actual = loginPage.getAlertText();
        Assert.assertEquals(expected, actual);
    }

    /**
     * tc0005_invalid_password_test tests to make sure an invalid password is not accepted.
     * Thread sleep added to allow for captcha submission.
     * @throws InterruptedException
     */
    @Test(priority = 5)
    public void tc0005_invalid_password_test() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickAccountButton();
        loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();
        signUpPage = new SignUpPage(driver);
        signUpPage.inputFirstName("Jane");
        signUpPage.inputLastName("Doe");
        signUpPage.inputEmail("test543@gmail.com");
        signUpPage.inputPassword("pass");
        signUpPage.clickRegisterButton();
        //Thread sleep to allow time for captcha submission
        Thread.sleep(20000);
        //Assertion
        String expected = "Sorry! Please try that again.";
        String actual = loginPage.getAlertText();
        Assert.assertEquals(expected, actual);
    }
}
