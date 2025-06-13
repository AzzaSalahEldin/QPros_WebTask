package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import utils.ConfigReader;
import utils.DriverManager;
import utils.ScreenshotUtil;

import java.io.File;
import java.util.Objects;

public class BaseTest {
    protected WebDriver driver;
    @BeforeSuite
    public void cleanAllureResults() {
        File allureResults = new File("allure-results");
        if (allureResults.exists()) {
            for (File file : Objects.requireNonNull(allureResults.listFiles())) {
                file.delete();
            }
        }
    }
    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {
        ConfigReader.loadProperties();
        driver = DriverManager.initDriver(browser);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
            DriverManager.quitDriver();
        } else {
            System.out.println("❌ WebDriver is null in tearDown(). Skipping screenshot and quit.");
        }
    }
}
