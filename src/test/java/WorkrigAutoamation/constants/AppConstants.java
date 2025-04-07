package WorkrigAutoamation.constants;

/**
 * Constants class for application-specific values.
 * This class contains all the constants used in the application.
 */
public class AppConstants {
    
    // Application URLs
    public static final String BASE_URL = "https://quloi.myworkrig.com/";
    
    // Timeouts (in seconds)
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 30;
    public static final int PAGE_LOAD_TIMEOUT = 30;
    
    // File paths
    public static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";
    public static final String LOG_DIR = "test-output/logs";
    public static final String SCREENSHOT_DIR = "test-output/screenshots";
    
    // Browser types
    public static final String BROWSER_CHROME = "chrome";
    public static final String BROWSER_FIREFOX = "firefox";
    public static final String BROWSER_EDGE = "edge";
    public static final String BROWSER_SAFARI = "safari";
    
    // Test data
    public static final String DEFAULT_USERNAME = "anshul.gaur";
    public static final String DEFAULT_PASSWORD = "vd8m5791";
    
    // Locators
    public static final String USERNAME_FIELD_XPATH = "//input[@id='form-username']";
    public static final String PASSWORD_FIELD_XPATH = "//input[@id='form-password']";
    public static final String LOGIN_BUTTON_CSS = "button[type='submit']";
    public static final String LOGOUT_BUTTON_CSS = ".btn.btn-info";
    
    // Messages
    public static final String LOGIN_SUCCESS_MESSAGE = "Login successful";
    public static final String LOGIN_FAILURE_MESSAGE = "Login failed";
    public static final String LOGOUT_SUCCESS_MESSAGE = "Logout successful";
    
    // Private constructor to prevent instantiation
    private AppConstants() {
        // This constructor is intentionally empty
    }
} 