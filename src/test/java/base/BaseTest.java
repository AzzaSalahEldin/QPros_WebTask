package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.DriverManager;

public class BaseTest {
    protected WebDriver driver;
    @BeforeMethod
    public void setUp() {
        driver = DriverManager.initDriver();
        ConfigReader.loadProperties();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
