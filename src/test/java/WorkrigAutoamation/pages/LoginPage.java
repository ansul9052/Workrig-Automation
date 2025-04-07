package WorkrigAutoamation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import WorkrigAutoamation.constants.AppConstants;

/**
 * Login Page class that contains login page elements and methods.
 * This class follows the Page Object Model pattern.
 */
public class LoginPage extends BasePage {
    
    @FindBy(xpath = AppConstants.USERNAME_FIELD_XPATH)
    private WebElement usernameInput;
    
    @FindBy(xpath = AppConstants.PASSWORD_FIELD_XPATH)
    private WebElement passwordInput;
    
    @FindBy(css = AppConstants.LOGIN_BUTTON_CSS)
    private WebElement loginButton;
    
    @FindBy(css = AppConstants.LOGOUT_BUTTON_CSS)
    private WebElement logoutButton;

    /**
     * Constructor for LoginPage
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to login page
     */
    public void navigateToLoginPage() {
        navigateTo(configManager.getProperty("base.url", AppConstants.BASE_URL));
    }

    /**
     * Login with default credentials
     * @return true if login successful, false otherwise
     */
    public boolean login() {
        return login(
            configManager.getProperty("username", AppConstants.DEFAULT_USERNAME),
            configManager.getProperty("password", AppConstants.DEFAULT_PASSWORD)
        );
    }

    /**
     * Login with specified credentials
     * @param username Username to login with
     * @param password Password to login with
     * @return true if login successful, false otherwise
     */
    public boolean login(String username, String password) {
        boolean usernameEntered = enterText(usernameInput, username);
        boolean passwordEntered = enterText(passwordInput, password);
        boolean loginClicked = clickElement(loginButton);
        
        return usernameEntered && passwordEntered && loginClicked;
    }

    /**
     * Verify login was successful
     * @return true if login successful, false otherwise
     */
    public boolean verifyLoginSuccess() {
        boolean isLoggedIn = isElementDisplayed(logoutButton);
        Assert.assertTrue(isLoggedIn, AppConstants.LOGIN_FAILURE_MESSAGE);
        return isLoggedIn;
    }

    /**
     * Logout from the application
     * @return true if logout successful, false otherwise
     */
    public boolean logout() {
        boolean logoutClicked = clickElement(logoutButton);
        if (logoutClicked) {
            String alertText = handleAlert(true);
            return alertText != null;
        }
        return false;
    }
} 