package WorkrigAutoamation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;
import WorkrigAutoamation.config.ConfigManager;

/**
 * Driver Manager class to handle WebDriver initialization and management.
 * This class follows the Singleton pattern to ensure only one WebDriver instance exists.
 */
public class DriverManager {
    private static DriverManager instance;
    private WebDriver driver;
    private WebDriverWait wait;
    private ConfigManager configManager;

    private DriverManager() {
        configManager = ConfigManager.getInstance();
    }

    /**
     * Get the singleton instance of DriverManager
     * @return DriverManager instance
     */
    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    /**
     * Initialize WebDriver based on browser type from configuration
     * @return WebDriver instance
     */
    public WebDriver getDriver() {
        if (driver == null) {
            String browserType = configManager.getProperty("browser.type", "chrome").toLowerCase();
            
            switch (browserType) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }
            
            // Configure driver settings
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                configManager.getIntProperty("implicit.wait", 10)));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(
                configManager.getIntProperty("page.load.timeout", 30)));
            
            // Initialize WebDriverWait
            wait = new WebDriverWait(driver, Duration.ofSeconds(
                configManager.getIntProperty("explicit.wait", 30)));
        }
        return driver;
    }

    /**
     * Get WebDriverWait instance
     * @return WebDriverWait instance
     */
    public WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(
                configManager.getIntProperty("explicit.wait", 30)));
        }
        return wait;
    }

    /**
     * Quit the WebDriver instance
     */
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }
} 