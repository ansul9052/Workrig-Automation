package WorkrigAutoamation;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import WorkrigAutoamation.pages.LoginPage;
import WorkrigAutoamation.utils.DriverManager;
import WorkrigAutoamation.constants.AppConstants;

/**
 * Main test class for Workrig Automation.
 * This class contains test methods for the Workrig application.
 */
public class Workrig_Autoamtion {
    private static final Logger logger = LoggerFactory.getLogger(Workrig_Autoamtion.class);
    private LoginPage loginPage;

    /**
     * Setup method that runs before each test
     */
    @BeforeMethod
    public void setUp() {
        try {
            logger.info("Initializing test setup");
            DriverManager.getInstance().getDriver();
            loginPage = new LoginPage(DriverManager.getInstance().getDriver());
            logger.info("Test setup completed successfully");
        } catch (Exception e) {
            logger.error("Failed to initialize test setup", e);
            throw e;
        }
    }

    /**
     * Test method for login and logout functionality
     */
    @Test(description = "Verify login and logout functionality")
    public void loginAndLogoutTest() {
        try {
            logger.info("Starting login and logout test");
            
            // Navigate to login page
            logger.debug("Navigating to login page");
            loginPage.navigateToLoginPage();
            
            // Perform login
            logger.debug("Attempting login");
            boolean loginSuccessful = loginPage.login();
            Assert.assertTrue(loginSuccessful, "Login failed");
            logger.info("Login successful");
            
            // Verify login success
            logger.debug("Verifying login success");
            boolean loginVerified = loginPage.verifyLoginSuccess();
            Assert.assertTrue(loginVerified, "Login verification failed");
            logger.info("Login verification successful");
            
            // Perform logout
            logger.debug("Attempting logout");
            boolean logoutSuccessful = loginPage.logout();
            Assert.assertTrue(logoutSuccessful, "Logout failed");
            logger.info("Logout successful");
            
            logger.info("Login and logout test completed successfully");
        } catch (Exception e) {
            logger.error("Test failed with error", e);
            throw e;
        }
    }

    /**
     * Teardown method that runs after each test
     */
    @AfterMethod
    public void tearDown() {
        try {
            logger.info("Starting test cleanup");
            DriverManager.getInstance().quitDriver();
            logger.info("Test cleanup completed successfully");
        } catch (Exception e) {
            logger.error("Failed to cleanup test resources", e);
            throw e;
        }
    }
}
