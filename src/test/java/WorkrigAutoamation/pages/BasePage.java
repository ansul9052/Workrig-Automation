package WorkrigAutoamation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);

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
        logger.info("BasePage initialized with WebDriver");
    }

    /**
     * Navigate to a URL
     * @param url URL to navigate to
     */
    protected void navigateTo(String url) {
        try {
            logger.debug("Navigating to URL: {}", url);
            driver.get(url);
        } catch (Exception e) {
            logger.error("Failed to navigate to URL: {}", url, e);
            throw e;
        }
    }

    /**
     * Wait for element to be visible
     * @param element WebElement to wait for
     * @return WebElement if found, null otherwise
     */
    protected WebElement waitForElementVisible(WebElement element) {
        try {
            logger.debug("Waiting for element to be visible: {}", element);
            return TestUtils.waitForElementVisible(driver, element, 
                configManager.getIntProperty("explicit.wait", AppConstants.EXPLICIT_WAIT));
        } catch (Exception e) {
            logger.error("Element not visible after wait: {}", element, e);
            return null;
        }
    }

    /**
     * Wait for element to be clickable
     * @param element WebElement to wait for
     * @return WebElement if found, null otherwise
     */
    protected WebElement waitForElementClickable(WebElement element) {
        try {
            logger.debug("Waiting for element to be clickable: {}", element);
            return TestUtils.waitForElementClickable(driver, element, 
                configManager.getIntProperty("explicit.wait", AppConstants.EXPLICIT_WAIT));
        } catch (Exception e) {
            logger.error("Element not clickable after wait: {}", element, e);
            return null;
        }
    }

    /**
     * Click on an element
     * @param element WebElement to click
     * @return true if successful, false otherwise
     */
    protected boolean clickElement(WebElement element) {
        try {
            logger.debug("Attempting to click element: {}", element);
            WebElement clickableElement = waitForElementClickable(element);
            if (clickableElement != null) {
                clickableElement.click();
                logger.info("Successfully clicked element: {}", element);
                return true;
            }
            logger.warn("Element not clickable: {}", element);
            return false;
        } catch (Exception e) {
            logger.error("Failed to click element: {}", element, e);
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
            logger.debug("Attempting to enter text '{}' into element: {}", text, element);
            WebElement visibleElement = waitForElementVisible(element);
            if (visibleElement != null) {
                visibleElement.clear();
                visibleElement.sendKeys(text);
                logger.info("Successfully entered text into element: {}", element);
                return true;
            }
            logger.warn("Element not visible for text entry: {}", element);
            return false;
        } catch (Exception e) {
            logger.error("Failed to enter text into element: {}", element, e);
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
            logger.debug("Getting text from element: {}", element);
            WebElement visibleElement = waitForElementVisible(element);
            if (visibleElement != null) {
                String text = visibleElement.getText();
                logger.debug("Retrieved text '{}' from element: {}", text, element);
                return text;
            }
            logger.warn("Element not visible for text retrieval: {}", element);
            return null;
        } catch (Exception e) {
            logger.error("Failed to get text from element: {}", element, e);
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
            logger.debug("Checking if element is displayed: {}", element);
            WebElement visibleElement = waitForElementVisible(element);
            boolean isDisplayed = visibleElement != null && visibleElement.isDisplayed();
            logger.debug("Element display status: {} for element: {}", isDisplayed, element);
            return isDisplayed;
        } catch (Exception e) {
            logger.error("Failed to check element display status: {}", element, e);
            return false;
        }
    }

    /**
     * Handle alert and return its text
     * @param accept Whether to accept or dismiss the alert
     * @return Alert text
     */
    protected String handleAlert(boolean accept) {
        try {
            logger.debug("Handling alert with accept={}", accept);
            String alertText = TestUtils.handleAlert(driver, accept);
            logger.info("Alert handled successfully. Text: {}", alertText);
            return alertText;
        } catch (Exception e) {
            logger.error("Failed to handle alert", e);
            return null;
        }
    }

    /**
     * Scroll element into view
     * @param element WebElement to scroll to
     */
    protected void scrollToElement(WebElement element) {
        try {
            logger.debug("Scrolling to element: {}", element);
            TestUtils.scrollToElement(driver, element);
            logger.info("Successfully scrolled to element: {}", element);
        } catch (Exception e) {
            logger.error("Failed to scroll to element: {}", element, e);
        }
    }
} 