package WorkrigAutoamation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import WorkrigAutoamation.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Workrig_Autoamtion {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginAndLogoutTest() {
        // Navigate to login page
        loginPage.navigateToLoginPage();
        
        // Perform login
        loginPage.login();
        
        // Verify login success
        loginPage.verifyLoginSuccess();
        
        // Perform logout
        loginPage.logout();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
