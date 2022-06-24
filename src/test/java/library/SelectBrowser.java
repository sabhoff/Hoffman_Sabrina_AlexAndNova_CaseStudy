package library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
* The SelectBrowser class is used for selecting a browser to run selenium tests on.
*/

public class SelectBrowser {
    static WebDriver driver;

    public static WebDriver startBrowser(String browserName) {
        //If browser is firefox
        if (browserName.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.firefox.marionette", "C:\\Users\\sabri\\SeleniumPractice\\Hoffman_Sabrina_AlexAndNova_CaseStudy\\src\\test\\resources\\geckodriver.exe");
            driver = new FirefoxDriver();
        }

        //If browser is Chrome
        else if (browserName.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\sabri\\SeleniumPractice\\Hoffman_Sabrina_AlexAndNova_CaseStudy\\src\\test\\resources\\chromedriver.exe");
            driver = new ChromeDriver();
        }

        //If browser is Edge
        else if (browserName.equalsIgnoreCase("Edge")) {
            System.setProperty("webdriver.edge.driver", "C:\\Users\\sabri\\SeleniumPractice\\Hoffman_Sabrina_AlexAndNova_CaseStudy\\src\\test\\resources\\msedgedriver.exe");
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);
        }

        driver.manage().window().maximize();
        return driver;
    }
}
