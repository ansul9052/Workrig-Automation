package WorkrigAutoamation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import WorkrigAutoamation.config.ConfigManager;
import WorkrigAutoamation.utils.TestUtils;
import WorkrigAutoamation.constants.AppConstants;

/**
 * Base Page class that contains common functionality for all page objects.
 * This class follows the Page Object Model pattern.
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ConfigManager configManager;

    /**
     * Constructor for BasePage
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.configManager = ConfigManager.getInstance();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(
            configManager.getIntProperty("explicit.wait", AppConstants.EXPLICIT_WAIT)));
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigate to a URL
     * @param url URL to navigate to
     */
    protected void navigateTo(String url) {
        driver.get(url);
    }

    /**
     * Wait for element to be visible
     * @param element WebElement to wait for
     * @return WebElement if found, null otherwise
     */
    protected WebElement waitForElementVisible(WebElement element) {
        return TestUtils.waitForElementVisible(driver, element, 
            configManager.getIntProperty("explicit.wait", AppConstants.EXPLICIT_WAIT));
    }

    /**
     * Wait for element to be clickable
     * @param element WebElement to wait for
     * @return WebElement if found, null otherwise
     */
    protected WebElement waitForElementClickable(WebElement element) {
        return TestUtils.waitForElementClickable(driver, element, 
            configManager.getIntProperty("explicit.wait", AppConstants.EXPLICIT_WAIT));
    }

    /**
     * Click on an element
     * @param element WebElement to click
     * @return true if successful, false otherwise
     */
    protected boolean clickElement(WebElement element) {
        try {
            WebElement clickableElement = waitForElementClickable(element);
            if (clickableElement != null) {
                clickableElement.click();
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Enter text into an element
     * @param element WebElement to enter text into
     * @param text Text to enter
     * @return true if successful, false otherwise
     */
    protected boolean enterText(WebElement element, String text) {
        try {
            WebElement visibleElement = waitForElementVisible(element);
            if (visibleElement != null) {
                visibleElement.clear();
                visibleElement.sendKeys(text);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get text from an element
     * @param element WebElement to get text from
     * @return Text from the element, or null if not found
     */
    protected String getElementText(WebElement element) {
        try {
            WebElement visibleElement = waitForElementVisible(element);
            if (visibleElement != null) {
                return visibleElement.getText();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Check if element is displayed
     * @param element WebElement to check
     * @return true if displayed, false otherwise
     */
    protected boolean isElementDisplayed(WebElement element) {
        try {
            WebElement visibleElement = waitForElementVisible(element);
            return visibleElement != null && visibleElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Handle alert and return its text
     * @param accept Whether to accept or dismiss the alert
     * @return Alert text
     */
    protected String handleAlert(boolean accept) {
        return TestUtils.handleAlert(driver, accept);
    }

    /**
     * Scroll element into view
     * @param element WebElement to scroll to
     */
    protected void scrollToElement(WebElement element) {
        TestUtils.scrollToElement(driver, element);
    }
} 