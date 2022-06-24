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

public class Base {

    WebDriver driver;
    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;

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

    @BeforeMethod
    public void setupReports(Method method) throws IOException {
        String name = method.getName();
        test =  extent.createTest(name);
        test.addScreenCaptureFromPath(name + ".png");
    }

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

    @AfterSuite
    public void tearDown() {
        extent.flush();
    }
}
