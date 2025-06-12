package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver() {
        String browser = ConfigReader.getBrowser();
        String runMode = ConfigReader.getRunMode();
        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (ConfigReader.isHeadless()) chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    if (runMode.equalsIgnoreCase("grid")) {
                        driver.set(new RemoteWebDriver(new URL(ConfigReader.getGridUrl()), chromeOptions));
                    } else {
                        WebDriverManager.chromedriver().setup();
                        driver.set(new org.openqa.selenium.chrome.ChromeDriver(chromeOptions));
                    }
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (ConfigReader.isHeadless()) firefoxOptions.addArguments("--headless");
                    if (runMode.equalsIgnoreCase("grid")) {
                        driver.set(new RemoteWebDriver(new URL(ConfigReader.getGridUrl()), firefoxOptions));
                    } else {
                        WebDriverManager.firefoxdriver().setup();
                        driver.set(new org.openqa.selenium.firefox.FirefoxDriver(firefoxOptions));
                    }
                    break;
                default:
                    throw new RuntimeException("Unsupported browser: " + browser);
            }
            getDriver().manage().window().maximize();
            getDriver().get(ConfigReader.getBaseUrl());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize driver: " + e.getMessage());
        }
        return driver.get();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
