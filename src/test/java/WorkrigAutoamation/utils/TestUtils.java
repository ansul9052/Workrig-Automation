package WorkrigAutoamation.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Utility class for common test operations.
 * This class provides reusable methods for test automation.
 */
public class TestUtils {
    
    /**
     * Wait for element to be visible
     * @param driver WebDriver instance
     * @param element WebElement to wait for
     * @param timeoutInSeconds Timeout in seconds
     * @return WebElement if found, null otherwise
     */
    public static WebElement waitForElementVisible(WebDriver driver, WebElement element, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Wait for element to be clickable
     * @param driver WebDriver instance
     * @param element WebElement to wait for
     * @param timeoutInSeconds Timeout in seconds
     * @return WebElement if found, null otherwise
     */
    public static WebElement waitForElementClickable(WebDriver driver, WebElement element, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Handle alert and return its text
     * @param driver WebDriver instance
     * @param accept Whether to accept or dismiss the alert
     * @return Alert text
     */
    public static String handleAlert(WebDriver driver, boolean accept) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            
            if (accept) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            
            return alertText;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Scroll element into view
     * @param driver WebDriver instance
     * @param element WebElement to scroll to
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            // Ignore exception
        }
    }
    
    /**
     * Generate random string of specified length
     * @param length Length of string to generate
     * @return Random string
     */
    public static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        
        return sb.toString();
    }
    
    /**
     * Get current timestamp in specified format
     * @param format Date format (e.g., "yyyy-MM-dd HH:mm:ss")
     * @return Formatted timestamp
     */
    public static String getCurrentTimestamp(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.now().format(formatter);
    }
    
    /**
     * Take screenshot of the current page
     * @param driver WebDriver instance
     * @param fileName Name of the screenshot file
     * @return Path to the screenshot file
     */
    public static String takeScreenshot(WebDriver driver, String fileName) {
        // This is a placeholder. In a real implementation, you would use a screenshot utility
        // like TakesScreenshot or a third-party library
        return "Screenshot functionality to be implemented";
    }
} 