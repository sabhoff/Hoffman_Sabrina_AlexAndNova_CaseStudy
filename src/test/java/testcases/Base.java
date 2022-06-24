package testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

/**_____________________________________________________________________________________________
 * Base class includes methods for creating reports, screenshots, and tearing down the project
 _______________________________________________________________________________________________*/

public class Base {

    WebDriver driver;
    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;

    /**_________________________________________________________________________________
     * setUpReport sets up the HTML reporter
     ___________________________________________________________________________________*/
    @BeforeSuite
    public void setUpReport() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/CapstoneReports.html");
        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "SABS");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Sabrina Hoffman");

        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Alex and Nova Test Automation Report");
        htmlReporter.config().setReportName("Alex and Nova Test Automation Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
    }

    /**____________________________________________________________________________
     * setUpReports names the reports after the methods
     * @param method
     * @throws IOException
     _________________________________________________________________________________*/
    @BeforeMethod
    public void setupReports(Method method) throws IOException {
        String name = method.getName();
        test =  extent.createTest(name);
        test.addScreenCaptureFromPath(name + ".png");
    }

    /**_______________________________________________________________________________________________________________________
     * recordResults ensures that testNG reports if a test passes or fails, as well as closing the browser after each method
     * @param result
     * @throws IOException
     _________________________________________________________________________________________________________________________*/
    @AfterMethod
    public void recordResults(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            Files.move(screenshot, new File("test-output/xml/" + result.getName() + ".png"));
            test.log(Status.FAIL, result.getThrowable());
            driver.quit();
        }

        else if (result.getStatus() == ITestResult.SUCCESS) {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            Files.move(screenshot, new File("test-output/xml/" + result.getName() + ".png"));
            test.log(Status.PASS, result.getTestName());
            driver.quit();
        }

        else {
            test.log(Status.SKIP, result.getTestName());
            driver.quit();
        }
    }

    /**_________________________________________________________________________________
     * tearDown flushes once suites are runs and allows reports to be generated
     __________________________________________________________________________________*/
    @AfterSuite
    public void tearDown() {
        extent.flush();
    }
}
