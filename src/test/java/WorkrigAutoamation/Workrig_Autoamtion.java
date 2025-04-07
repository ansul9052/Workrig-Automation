package WorkrigAutoamation;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import WorkrigAutoamation.pages.LoginPage;
import WorkrigAutoamation.utils.DriverManager;
import WorkrigAutoamation.constants.AppConstants;

/**
 * Main test class for Workrig Automation.
 * This class contains test methods for the Workrig application.
 */
public class Workrig_Autoamtion {
    private LoginPage loginPage;

    /**
     * Setup method that runs before each test
     */
    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver using DriverManager
        DriverManager.getInstance().getDriver();
        loginPage = new LoginPage(DriverManager.getInstance().getDriver());
    }

    /**
     * Test method for login and logout functionality
     */
    @Test(description = "Verify login and logout functionality")
    public void loginAndLogoutTest() {
        // Navigate to login page
        loginPage.navigateToLoginPage();
        
        // Perform login
        boolean loginSuccessful = loginPage.login();
        Assert.assertTrue(loginSuccessful, "Login failed");
        
        // Verify login success
        boolean loginVerified = loginPage.verifyLoginSuccess();
        Assert.assertTrue(loginVerified, "Login verification failed");
        
        // Perform logout
        boolean logoutSuccessful = loginPage.logout();
        Assert.assertTrue(logoutSuccessful, "Logout failed");
    }

    /**
     * Teardown method that runs after each test
     */
    @AfterMethod
    public void tearDown() {
        // Quit WebDriver using DriverManager
        DriverManager.getInstance().quitDriver();
    }
}
