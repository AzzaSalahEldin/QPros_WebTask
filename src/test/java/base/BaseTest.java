package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.DriverManager;
import utils.ScreenshotUtil;

public class BaseTest {
    protected WebDriver driver;
    @BeforeMethod
    public void setUp() {
        driver = DriverManager.initDriver();
        ConfigReader.loadProperties();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
        DriverManager.quitDriver();
    }
}
